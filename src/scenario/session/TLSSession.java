package scenario.session;

import de.rub.nds.protocol.constants.SignatureAlgorithm;
import de.rub.nds.tlsattacker.core.connection.AliasedConnection;
import de.rub.nds.tlsattacker.core.connection.InboundConnection;
import de.rub.nds.tlsattacker.core.connection.OutboundConnection;
import de.rub.nds.tlsattacker.core.constants.*;
import de.rub.nds.tlsattacker.core.crypto.MessageDigestCollector;
import de.rub.nds.tlsattacker.core.protocol.ProtocolMessage;
import de.rub.nds.tlsattacker.core.protocol.message.*;
import de.rub.nds.tlsattacker.core.protocol.message.cert.CertificateEntry;
import de.rub.nds.tlsattacker.core.protocol.message.extension.*;
import de.rub.nds.tlsattacker.core.protocol.message.extension.keyshare.KeyShareEntry;
import de.rub.nds.tlsattacker.core.record.Record;
import de.rub.nds.tlsattacker.core.record.cipher.cryptohelper.KeySet;
import de.rub.nds.tlsattacker.core.workflow.WorkflowTrace;
import de.rub.nds.tlsattacker.core.workflow.action.*;
import de.rub.nds.tlsattacker.core.workflow.action.custom.*;
import de.rub.nds.tlsattacker.core.workflow.action.custom.extension.AddKeyShareAction;
import de.rub.nds.tlsattacker.core.workflow.action.custom.extension.AddSignatureAndHashAlgorithmAction;
import de.rub.nds.tlsattacker.core.workflow.action.custom.extension.AddSupportedGroupAction;
import de.rub.nds.tlsattacker.core.workflow.action.custom.extension.AddSupportedVersionAction;
import de.rub.nds.x509attacker.config.X509CertificateConfig;
import de.rub.nds.x509attacker.constants.X509NamedCurve;
import de.rub.nds.x509attacker.context.X509Context;
import de.rub.nds.x509attacker.x509.model.X509Certificate;
import de.rub.nds.x509attacker.x509.model.publickey.PublicKeyContent;
import de.rub.nds.x509attacker.x509.model.publickey.X509EcdhEcdsaPublicKey;
import de.rub.nds.x509attacker.x509.model.publickey.X509RsaPublicKey;
import maude.MessageSize;
import maude.Random;
import maude.SupportedVersion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.util.io.pem.PemReader;
import org.xbill.DNS.Message;
import protocol.Protocol;
import protocol.Variable;
import scenario.*;
import scenario.variable.CertificateVariable;
import scenario.variable.ConstantVariable;
import scenario.variable.MessageVariable;
import scenario.variable.ProtocolMessageVariable;

import java.io.FileReader;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TLSSession implements Protocol {

    protected AliasedConnection connection;
    private WorkflowTrace trace;
    protected String alias;
    private SecureRandom secureRandom;
    protected TLSAttacker attacker;

    private static Logger LOGGER = LogManager.getLogger();

    public TLSSession(){}

    public TLSSession(String alias){
        this.alias = alias;
    }

    public void setExecutor(TLSAttacker attacker){
        this.attacker = attacker;
        this.trace = attacker.getWorkFlowTrace();
    }


    @Override
    public void setRandomPrivateKey(String group) {
        byte[] privateKey;
        try{
            privateKey = new byte[32];
            this.secureRandom = SecureRandom.getInstanceStrong();
            this.secureRandom.nextBytes(privateKey);
            LOGGER.info("Random privatekey: " + new BigInteger(privateKey));
            attacker.getConfig().setDefaultKeySharePrivateKey(NamedGroup.valueOf(group), new BigInteger(privateKey));
        } catch(NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void accept(String alias, String ip, int port){
        this.alias = alias;
        connection = new InboundConnection(alias, port, ip);
        connection.setConnectionTimeout(30000);
        attacker.addAliasedConnection(connection);
    }

    @Override
    public void connect(String alias, String ip, int port){
        this.alias = alias;
        connection = new OutboundConnection(alias, port, ip);
        connection.setConnectionTimeout(30000);
        attacker.addAliasedConnection(connection);
    }

    @Override
    public void close() { }

    @Override
    public Variable recv(String alias) {
        List<ProtocolMessage> protocolMessages = new ArrayList<>();
        List<Record> recordMessages = new ArrayList<>();
        trace.addTlsAction(new ReceiveOneAction(alias, recordMessages, protocolMessages));
        return new MessageVariable(recordMessages, protocolMessages);
    }

    @Override
    public void send(String alias, Variable variable) {
        MessageVariable messageVariable = (MessageVariable) variable;
        SendAction action = new SendAction(alias, messageVariable.getProtocolMessages());
        action.setConfiguredRecords(messageVariable.getRecordMessages());
        trace.addTlsAction(action);
    }

    @Override
    public void checkConnection(String alias) {
        CheckConnectionAction action = new CheckConnectionAction(alias);
        trace.addTlsAction(action);
    }

    @Override
    public Variable encrypt(String alias, Variable message) {
        MessageVariable messageVariable = (MessageVariable) message;

        EncryptAction action = new EncryptAction(alias);
        action.setRecord((List<Record>)messageVariable.getRecordMessages());
        trace.addTlsAction(action);

        return messageVariable;
    }

    @Override
    public Variable encrypt(Variable message, Variable key) {
        MessageVariable messageVariable = (MessageVariable) message;

        EncryptAction action = new EncryptAction(alias);
        action.setRecord((List<Record>)messageVariable.getRecordMessages());
        action.setKeySet((List<KeySet>)key.getValue());
        trace.addTlsAction(action);

        return messageVariable;
    }

    @Override
    public Variable decrypt(String alias, Variable message) {
        MessageVariable messageVariable = (MessageVariable) message;
        return messageVariable;
    }

    @Override
    public Variable decrypt(Variable message, Variable key) {
        MessageVariable messageVariable = (MessageVariable) message;

        DecryptAction action = new DecryptAction(alias);
        action.setRecordMessage((List<Record>)messageVariable.getRecordMessages());
        action.setProtocolMessage((List<ProtocolMessage>)messageVariable.getProtocolMessages());
        action.setKeySet((List<KeySet>)key.getValue());
        trace.addTlsAction(action);

        return messageVariable;
    }

    @Override
    public Variable genCertificate(String pemFilePath) {
        List<CertificateEntry> container = new ArrayList<>();
        try{
            PemReader pemReader = new PemReader(new FileReader(pemFilePath));
            byte[] derBytes = pemReader.readPemObject().getContent();
            CertificateEntry entry = new CertificateEntry(derBytes);
            container.add(entry);
        } catch (Exception E){
            LOGGER.warn("Could not parse a valid certificate fom provided certificate path: " + pemFilePath);
            return null;
        }
        return new CertificateVariable(container);
    }

    @Override
    public Variable genCertificatePrivateKey(String privateKeyPath){
        byte[] derBytes = new byte[0];
        try{
            PemReader pemReader = new PemReader(new FileReader(privateKeyPath));
            derBytes = pemReader.readPemObject().getContent();
        } catch (Exception e){
            LOGGER.warn("Could not parse a valid certificate fom provided private key: " + privateKeyPath);
        }
        List<byte[]> privateKeyContainer = new ArrayList<byte[]>();
        privateKeyContainer.add(derBytes);
        return new ConstantVariable<byte[]>(privateKeyContainer);
    }

    @Override
    public void setCertificateEcPrivateKey(String keyPath, String namedCurve){
        try{
            PemReader pemReader = new PemReader(new FileReader(keyPath));
            byte[] derBytes = pemReader.readPemObject().getContent();
            X509CertificateConfig x509CertificateConfig = new X509CertificateConfig();
            x509CertificateConfig.setEcPrivateKey(new BigInteger(derBytes));
            x509CertificateConfig.setDefaultSubjectNamedCurve(X509NamedCurve.valueOf(namedCurve));
            X509Context x509Context = new X509Context(x509CertificateConfig);

        } catch (Exception e){
            LOGGER.warn("Could not parse a valid certificate fom provided private key: " + keyPath);
        }
    }

    @Override
    public void assertEqual(Variable var1, Variable var2) {
        AssertEqualAction<?> action = new AssertEqualAction<>(var1::getValue, var2::getValue);
        trace.addTlsAction(action);
    }

    @Override
    public Variable constant(maude.ProtocolMessageType msgType) {
        List<ProtocolMessageType> container = new ArrayList<>();
        container.add(msgType.transform());
        return new ConstantVariable<ProtocolMessageType>(container);
    }

    @Override
    public Variable constant(maude.ProtocolVersion version) {
        List<ProtocolVersion> container = new ArrayList<>();
        container.add(version.transform());
        return new ConstantVariable<ProtocolVersion>(container);
    }

    @Override
    public Variable constant(maude.HandshakeMessageType msgType) {
        List<HandshakeMessageType> container = new ArrayList<>();
        container.add(msgType.transform());
        return new ConstantVariable<HandshakeMessageType>(container);
    }

    @Override
    public Variable constant(maude.CipherSuite... ciphers) {
        List<CipherSuite> container = new ArrayList<>();
        for(maude.CipherSuite cipher: ciphers){
            container.add(cipher.transform());
        }
        return new ConstantVariable<CipherSuite>(container);
    }

    @Override
    public Variable constant(maude.CompressionMethod... methods) {
        List<CompressionMethod> container = new ArrayList<>();
        for(maude.CompressionMethod method : methods){
            container.add(method.transform());
        }
        return new ConstantVariable<CompressionMethod>(container);
    }

    @Override
    public Variable constant(MessageSize size) {
        List<Boolean> container = new ArrayList<>();
        container.add(size.equals(MessageSize.VALID));
        return new ConstantVariable<Boolean>(container);
    }

    @Override
    public Variable constant(Random random) {
        List<byte[]> container = new ArrayList<>();
        if(random == Random.EMPTY){
            container.add(new byte[]{});
        }else{
            byte[] randomByte = new byte[32];
            try {
                this.secureRandom = SecureRandom.getInstanceStrong();
                this.secureRandom.nextBytes(randomByte);
            } catch (NoSuchAlgorithmException e) {
                LOGGER.error("Secure random generator algorithm is not found");
            }
            container.add(randomByte);
        }
        return new ConstantVariable<byte[]>(container);
    }

    @Override
    public Variable constant(maude.AlertLevel level) {
        List<AlertLevel> container = new ArrayList<>();
        container.add(level.transform());
        return new ConstantVariable<AlertLevel>(container);
    }

    @Override
    public Variable constant(maude.AlertDescription description) {
        List<AlertDescription> container = new ArrayList<>();
        container.add(description.transform());
        return new ConstantVariable<AlertDescription>(container);
    }

    @Override
    public Variable constant(maude.NamedGroup group) {
        List<NamedGroup> container = new ArrayList<>();
        container.add(group.transform());
        return new ConstantVariable<NamedGroup>(container);
    }

    @Override
    public Variable constant(maude.SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        List<SignatureAndHashAlgorithm> container = new ArrayList<>();
        container.add(signatureAndHashAlgorithm.transform());
        return new ConstantVariable<SignatureAndHashAlgorithm>(container);
    }

    @Override
    public Variable constant(SupportedVersion... supportedVersions) {
        List<ProtocolVersion> container = new ArrayList<>();
        for(SupportedVersion version : supportedVersions){
            container.add(version.transform());
        }
        return new ConstantVariable<ProtocolVersion>(container);
    }

    @Override
    public Variable getContentType(Variable msg){
        MessageVariable messageVariable = (MessageVariable) msg;
        List<ProtocolMessageType> container = new ArrayList<>();
        FieldAction action = new FieldAction<ProtocolMessageType>(container,
                () -> {
                    if(!((MessageVariable) msg).getRecordMessages().isEmpty()){
                        Record record = ((MessageVariable) msg).getRecordMessages().get(0);
                        return record.getContentMessageType();
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<ProtocolMessageType>(container);
    }

    @Override
    public Variable getRecordVersion(Variable msg) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<ProtocolVersion> container = new ArrayList<>();
        FieldAction action = new FieldAction<ProtocolVersion>(container,
                () -> {
                    if(!((MessageVariable) msg).getRecordMessages().isEmpty()){
                        Record record = ((MessageVariable) msg).getRecordMessages().get(0);
                        return ProtocolVersion.getProtocolVersion(record.getProtocolVersion().getValue());
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<ProtocolVersion>(container);
    }

    @Override
    public Variable getHandshakeMessageType(Variable msg) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<HandshakeMessageType> container = new ArrayList<>();
        FieldAction action = new FieldAction<HandshakeMessageType>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        HandshakeMessage handshakeMessage = (HandshakeMessage) ((MessageVariable) msg).getProtocolMessages().get(0);
                        return handshakeMessage.getHandshakeMessageType();
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<HandshakeMessageType>(container);
    }

    @Override
    public Variable getProtocolVersion(Variable msg) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<ProtocolVersion> container = new ArrayList<>();
        FieldAction action = new FieldAction<ProtocolVersion>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                        if(message instanceof ClientHelloMessage){
                            return ProtocolVersion.getProtocolVersion(((ClientHelloMessage) message).getProtocolVersion().getValue());
                        }
                        else if (message instanceof ServerHelloMessage){
                            return ProtocolVersion.getProtocolVersion(((ServerHelloMessage) message).getProtocolVersion().getValue());
                        }
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<ProtocolVersion>(container);
    }

    @Override
    public Variable getCipherSuite(Variable msg, int idx) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<CipherSuite> container = new ArrayList<>();
        FieldAction action = new FieldAction<CipherSuite>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                        if(message instanceof ClientHelloMessage){
                            ClientHelloMessage clientHelloMessage = (ClientHelloMessage) message;
                            byte[] cipherSuites = clientHelloMessage.getCipherSuites().getValue();
                            return CipherSuite.getCipherSuite(extractPair(cipherSuites, idx - 1));
                        } else if (message instanceof ServerHelloMessage){
                            ServerHelloMessage serverHelloMessage = (ServerHelloMessage) message;
                            return CipherSuite.getCipherSuite(serverHelloMessage.getSelectedCipherSuite().getValue());
                        }
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<CipherSuite>(container);
    }

    private byte[] extractPair(byte[] input, int n) {
        if (input == null)
            throw new IllegalArgumentException("Input byte array cannot be null.");
        int start = 2 * n;
        if (start + 1 >= input.length) {
            throw new IndexOutOfBoundsException("Requested pair exceeds byte array bounds.");
        }
        return new byte[] { input[start], input[start + 1] };
    }

    @Override
    public Variable getRandom(Variable msg) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<byte[]> container = new ArrayList<>();
        FieldAction action = new FieldAction<byte[]>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                        if(message instanceof ClientHelloMessage){
                            ClientHelloMessage clientHelloMessage = (ClientHelloMessage) message;
                            LOGGER.info("ClientRandom: " + Arrays.toString(clientHelloMessage.getRandom().getValue()));
                            return clientHelloMessage.getRandom().getValue();
                        } else if (message instanceof ServerHelloMessage){
                            ServerHelloMessage serverHelloMessage = (ServerHelloMessage) message;
                            LOGGER.info("ServerRandom: " + Arrays.toString(serverHelloMessage.getRandom().getValue()));
                            return serverHelloMessage.getRandom().getValue();
                        }
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<byte[]>(container);
    }

    @Override
    public Variable getSessionId(Variable msg) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<byte[]> container = new ArrayList<>();
        FieldAction action = new FieldAction<byte[]>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                        if(message instanceof ClientHelloMessage){
                            ClientHelloMessage clientHelloMessage = (ClientHelloMessage) message;
                            LOGGER.info("ClientSessionId: " + Arrays.toString(clientHelloMessage.getSessionId().getValue()));
                            return clientHelloMessage.getSessionId().getValue();
                        } else if (message instanceof ServerHelloMessage){
                            ServerHelloMessage serverHelloMessage = (ServerHelloMessage) message;
                            LOGGER.info("ServerSessionId: " + Arrays.toString(serverHelloMessage.getSessionId().getValue()));
                            return serverHelloMessage.getSessionId().getValue();
                        }
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<byte[]>(container);
    }

    @Override
    public Variable getCompressionMethod(Variable msg, int idx) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<CompressionMethod> container = new ArrayList<>();
        FieldAction action = new FieldAction<CompressionMethod>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                        if(message instanceof ClientHelloMessage){
                            ClientHelloMessage clientHelloMessage = (ClientHelloMessage) message;
                            return CompressionMethod.getCompressionMethod(clientHelloMessage.getCompressions().getValue()[0]);
                        } else if (message instanceof ServerHelloMessage){
                            ServerHelloMessage serverHelloMessage = (ServerHelloMessage) message;
                            return CompressionMethod.getCompressionMethod(serverHelloMessage.getSelectedCompressionMethod().getValue());
                        }
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<CompressionMethod>(container);
    }

    @Override
    public Variable getCertificate(Variable msg){
        MessageVariable messageVariable = (MessageVariable) msg;
        List<CertificateEntry> container = new ArrayList<>();
        FieldAction action = new FieldAction<CertificateEntry>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                        if(message instanceof CertificateMessage){
                            return ((CertificateMessage) message).getCertificateEntryList().get(0);
                        }
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new CertificateVariable(container);
    }

    @Override
    public Variable getPublicKeyFromCertificate(Variable msg) {
        CertificateVariable certificateVariable = (CertificateVariable) msg;
        List<PublicKeyContent> container = new ArrayList<>();
        FieldAction action = new FieldAction<PublicKeyContent>(container,
                () -> {
                    CertificateEntry entry = certificateVariable.getValue().get(0);
                    X509Certificate certificate = entry.getX509certificate();
                    PublicKeyContent publicKey = certificate.getPublicKey();
                    if(publicKey instanceof X509EcdhEcdsaPublicKey){
                        return publicKey;
                    } else if (publicKey instanceof X509RsaPublicKey) {
                       return publicKey;
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<PublicKeyContent>(container);
    }

    @Override
    public Variable getAlertLevel(Variable msg) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<AlertLevel> container = new ArrayList<>();
        FieldAction action = new FieldAction<AlertLevel>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                        if(message instanceof AlertMessage){
                            AlertMessage alertMessage = (AlertMessage) message;
                            return AlertLevel.getAlertLevel(alertMessage.getLevel().getValue());
                        }
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<AlertLevel>(container);
    }

    @Override
    public Variable getAlertDescription(Variable msg) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<AlertDescription> container = new ArrayList<>();
        FieldAction action = new FieldAction<AlertDescription>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                        if(message instanceof AlertMessage){
                            AlertMessage alertMessage = (AlertMessage) message;
                            return AlertDescription.getAlertDescription(alertMessage.getDescription().getValue());
                        }
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<AlertDescription>(container);
    }

    @Override
    public Variable getSupportedVersion(Variable msg, int idx) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<ProtocolVersion> container = new ArrayList<>();
        FieldAction action = new FieldAction<ProtocolVersion>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                        if(message instanceof HelloMessage){
                            HelloMessage helloMessage = (HelloMessage) message;
                            for(ExtensionMessage extMsg : helloMessage.getExtensions()){
                                if(extMsg instanceof SupportedVersionsExtensionMessage){
                                    SupportedVersionsExtensionMessage svExtMsg = (SupportedVersionsExtensionMessage) extMsg;
                                    byte[] versions = svExtMsg.getSupportedVersions().getValue();
                                    return ProtocolVersion.getProtocolVersion(extractPair(versions, idx - 1 ));
                                }
                            }
                        }
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<ProtocolVersion>(container);
    }

    @Override
    public Variable getSignatureAndHashAlgorithm(Variable msg, int idx) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<SignatureAndHashAlgorithm> container = new ArrayList<>();
        FieldAction action = new FieldAction<SignatureAndHashAlgorithm>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                        if(message instanceof HelloMessage){
                            HelloMessage helloMessage = (HelloMessage) message;
                            for(ExtensionMessage extMsg : helloMessage.getExtensions()){
                                if(extMsg instanceof SignatureAndHashAlgorithmsExtensionMessage){
                                    SignatureAndHashAlgorithmsExtensionMessage sahExtMsg = (SignatureAndHashAlgorithmsExtensionMessage) extMsg;
                                    byte[] signatureAlgorithms = sahExtMsg.getSignatureAndHashAlgorithms().getValue();
                                    return SignatureAndHashAlgorithm.getSignatureAndHashAlgorithm(extractPair(signatureAlgorithms, idx - 1 ));
                                }
                            }
                        }
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<SignatureAndHashAlgorithm>(container);
    }

    @Override
    public Variable getSignatureAlgorithm(Variable msg, int idx) {
        return getSignatureAndHashAlgorithm(msg, idx);
    }

    @Override
    public Variable getNamedGroup(Variable msg, int idx) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<NamedGroup> container = new ArrayList<>();
        FieldAction action = new FieldAction<NamedGroup>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                        if(message instanceof HelloMessage){
                            HelloMessage helloMessage = (HelloMessage) message;
                            for(ExtensionMessage extMsg : helloMessage.getExtensions()){
                                if(extMsg instanceof EllipticCurvesExtensionMessage){
                                    EllipticCurvesExtensionMessage ecExtMsg = (EllipticCurvesExtensionMessage) extMsg;
                                    byte[] namedGruops = ecExtMsg.getSupportedGroups().getValue();
                                    return NamedGroup.getNamedGroup(extractPair(namedGruops, idx - 1 ));
                                }
                            }
                        }
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<NamedGroup>(container);
    }

    @Override
    public Variable getKeyShareEntry(Variable msg, int idx) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<List<KeyShareEntry>> container = new ArrayList<>();
        FieldAction action = new FieldAction<List<KeyShareEntry>>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                        if(message instanceof HelloMessage){
                            HelloMessage helloMessage = (HelloMessage) message;
                            for(ExtensionMessage extMsg : helloMessage.getExtensions()){
                                if(ExtensionType.getExtensionType(extMsg.getExtensionType().getValue()) == ExtensionType.KEY_SHARE){
                                    KeyShareExtensionMessage keyShareExtensionMessage = (KeyShareExtensionMessage) extMsg;
                                    return keyShareExtensionMessage.getKeyShareList().subList(idx-1, idx);
                                }
                            }
                        }
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<List<KeyShareEntry>>(container);
    }

    @Override
    public Variable getKeyShareNamedGroup(Variable keyShares){
        ConstantVariable<List<KeyShareEntry>> fieldVariable = (ConstantVariable<List<KeyShareEntry>>) keyShares;
        List<NamedGroup> container = new ArrayList<>();
        FieldAction action = new FieldAction<NamedGroup>(container,
                ()->{
                    if(!(fieldVariable.getValue().isEmpty())){
                        KeyShareEntry entry = fieldVariable.getValue().get(0).get(0);
                        return entry.getGroupConfig();
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<NamedGroup>(container);
    }

    @Override
    public Variable getPublicKeyFromKeyShare(Variable msg) {
        ConstantVariable fieldVariable = (ConstantVariable) msg;
        List<byte[]> container = new ArrayList<>();
        FieldAction action = new FieldAction<byte[]>(container,
                ()-> {
                    List<List<KeyShareEntry>> fieldVariableValue = fieldVariable.getValue();
                    List<KeyShareEntry> entryList = fieldVariableValue.get(0);
                    KeyShareEntry entry = entryList.get(0);
                    return entry.getPublicKey().getValue();
                });
        trace.addTlsAction(action);
        return new ConstantVariable<byte[]>(container);
    }

    @Override
    public Variable getHandshakeBody(Variable msg){
        MessageVariable messageVariable = (MessageVariable) msg;
        List<ProtocolMessage> container = new ArrayList<>();
        FieldAction action = new FieldAction<ProtocolMessage>(container,
                () -> messageVariable.getProtocolMessages().get(0));
        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable getRSAPreMasterSecret(String alias, Variable msg, Variable privateKey){
        List<byte[]> container = new ArrayList<>();

        MessageVariable messageVariable = (MessageVariable) msg;

        GetPreMasterSecretAction getMasterSecretAction = new GetPreMasterSecretAction(alias, container);
        getMasterSecretAction.setMessage_container(messageVariable.getProtocolMessages());
        getMasterSecretAction.setPrivateKey_container((List<byte[]>) privateKey.getValue());
        trace.addTlsAction(getMasterSecretAction);

        return new ConstantVariable<byte[]>(container);
    }

    @Override
    public Variable buildRecord(String alias, Variable content_type, Variable record_version, Variable message) {
        List<Record> container = new ArrayList<>();

        BuildRecordAction action = new BuildRecordAction(container);
        action.setProtocolMessageType((List<ProtocolMessageType>) content_type.getValue());
        action.setProtocolVersion((List<ProtocolVersion>) record_version.getValue());
        action.setProtocolMessage((List<ProtocolMessage>) message.getValue());

        trace.addTlsAction(action);

        return new MessageVariable(container, (List<ProtocolMessage>) message.getValue());
    }

    @Override
    public Variable buildClientHello(String alias, Variable versions, Variable ciphers, Variable random, Variable sessionId, Variable methods){
        List<ProtocolMessage> container = new ArrayList<>();

        BuildClientHelloAction action = new BuildClientHelloAction(alias, container);
        action.setVersion((List<ProtocolVersion>) versions.getValue());
        action.setCipherSuites((List<CipherSuite>) ciphers.getValue());
        action.setRandom((List<byte[]>) random.getValue());
        action.setSessionId((List<byte[]>) sessionId.getValue());
        action.setCompressions((List<CompressionMethod>) methods.getValue());
        trace.addTlsAction(action);

        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildServerHello(String alias, Variable version, Variable suite, Variable random, Variable sessionId, Variable compression){
        List<ProtocolMessage> container = new ArrayList<>();

        BuildServerHelloAction action = new BuildServerHelloAction(alias, container);
        action.setVersion((List<ProtocolVersion>) version.getValue());
        action.setCipherSuite((List<CipherSuite>) suite.getValue());
        action.setRandom((List<byte[]>) random.getValue());
        action.setSessionId((List<byte[]>) sessionId.getValue());
        action.setCompression((List<CompressionMethod>) compression.getValue());
        trace.addTlsAction(action);

        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildCertificate(String alias, Variable certificate) {
        List<ProtocolMessage> container = new ArrayList<>();
        BuildCertificateAction action = new BuildCertificateAction(alias, container);
        action.setCertificate((List<CertificateEntry>) certificate.getValue());

        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildCertificate(String alias){
        List<ProtocolMessage> container = new ArrayList<>();
        BuildCertificateAction action = new BuildCertificateAction(alias, container);
        List<CertificateEntry> emptyCertificate = new ArrayList<>();
        action.setCertificate(emptyCertificate);

        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildEncryptedExtension(String alias) {
        List<ProtocolMessage> container = new ArrayList<>();
        BuildEncryptedExtensionAction action = new BuildEncryptedExtensionAction(alias, container);

        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildCertificateRequest(String alias, Variable certificate_context) {
        List<ProtocolMessage> container = new ArrayList<>();
        BuildCertificateRequestAction action = new BuildCertificateRequestAction(alias, container);
        action.setCertificateRequestContext((List<byte[]>) certificate_context.getValue());

        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildCertificateVerify(String alias, Variable signatureHashAlgorithm, Variable certificate_private_key) {
        List<ProtocolMessage> container = new ArrayList<>();
        BuildCertificateVerifyAction action = new BuildCertificateVerifyAction(alias, container);
        action.setSignature_and_hash_algorithm_container((List<SignatureAndHashAlgorithm>)signatureHashAlgorithm.getValue());

        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildChangeCipher(String alias) {
        List<ProtocolMessage> container = new ArrayList<>();
        BuildChangeCipherSpecAction action = new BuildChangeCipherSpecAction(alias, container);

        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildFinished(String alias) {
        List<ProtocolMessage> container = new ArrayList<>();
        BuildFinishedAction action = new BuildFinishedAction(alias, container);

        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildAlert(String alias, Variable level, Variable description) {
        List<ProtocolMessage> container = new ArrayList<>();
        BuildAlertAction action = new BuildAlertAction(alias, container);
        action.setAlertLevel((List<AlertLevel>) level.getValue());
        action.setAlertDescription((List<AlertDescription>) description.getValue());

        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public void addSupportedVersionExtension(String alias, Variable handshake_message, Variable supported_versions){
        AddSupportedVersionAction action = new AddSupportedVersionAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        action.setExtensions((List<ProtocolVersion>) supported_versions.getValue());
        trace.addTlsAction(action);
    }

    @Override
    public void addSignatureAndHashAlgorithmExtension(String alias, Variable handshake_message, Variable algorithms){
        AddSignatureAndHashAlgorithmAction action = new AddSignatureAndHashAlgorithmAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        action.setExtensions((List<SignatureAndHashAlgorithm>) algorithms.getValue());
        trace.addTlsAction(action);
    }

    @Override
    public void addSupportedGroupExtension(String alias, Variable handshake_message, Variable supported_groups){
        AddSupportedGroupAction action = new AddSupportedGroupAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        action.setExtensions((List<NamedGroup>) supported_groups.getValue());
        trace.addTlsAction(action);
    }

    @Override
    public void addKeyShareExtension(String alias, Variable handshake_message, Variable named_group, Variable private_key){
        List<KeyShareEntry> keyshareEntryList = new ArrayList<>();
        BuildKeyShareEntryAction keyShareEntryAction = new BuildKeyShareEntryAction(alias, keyshareEntryList);
        keyShareEntryAction.setNamedGroup((List<NamedGroup>) named_group.getValue());
        keyShareEntryAction.setPrivateKey((List<byte[]>) private_key.getValue());
        trace.addTlsAction(keyShareEntryAction);

        AddKeyShareAction action = new AddKeyShareAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        action.setExtensions((List<KeyShareEntry>) keyshareEntryList);
        trace.addTlsAction(action);
    }


    @Override
    public Variable buildInvalidPaddingRSAClientKeyExchange(Variable publicKey, Variable serverRandom, Variable clientRandom, Variable nonce, String alias) {
        List<ProtocolMessage> container = new ArrayList<>();
        BuildInvalidPaddingRSAClientKeyExchangeAction action = new BuildInvalidPaddingRSAClientKeyExchangeAction(alias, container);
        action.setPublicKeyContainer((List<PublicKeyContent>) publicKey.getValue());
        action.setServerRandom((List<byte[]>) serverRandom.getValue());
        action.setClientRandom((List<byte[]>) clientRandom.getValue());
        action.setNonce((List<byte[]>) nonce.getValue());


        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    @Deprecated
    public Variable buildKeyShareEntry(Variable group) {
        List<KeyShareEntry> container = new ArrayList<>();
        BuildKeyShareEntryAction action = new BuildKeyShareEntryAction(alias, container);
        action.setNamedGroup((List<NamedGroup>) group.getValue());
        trace.addTlsAction(action);

        return new ConstantVariable<KeyShareEntry>(container);
    }

    @Override
    public Variable buildKeyShareEntry(Variable group, Variable privateKey) {
        List<KeyShareEntry> container = new ArrayList<>();
        BuildKeyShareEntryAction action = new BuildKeyShareEntryAction(alias, container);
        action.setNamedGroup((List<NamedGroup>) group.getValue());
        action.setPrivateKey((List<byte[]>) privateKey.getValue());
        trace.addTlsAction(action);

        return new ConstantVariable<KeyShareEntry>(container);
    }

    @Override
    public Variable calculateMessageDigest(Variable... messages) {
        List<MessageDigestCollector> container = new ArrayList<>();
        CalculateMessageDigestAction action = new CalculateMessageDigestAction(container);
        for(Variable message : messages) {
            action.appendMessage(((MessageVariable)message).getProtocolMessages());
        }

        trace.addTlsAction(action);
        return new ConstantVariable<MessageDigestCollector>(container);
    }

    @Override
    public Variable changeCertificate(Variable variable, Variable after_certificate, String alias) {
        MessageVariable messageVariable = (MessageVariable) variable;
        CertificateVariable after_variable = (CertificateVariable) after_certificate;
        ChangeCertificateAction action = new ChangeCertificateAction(alias, messageVariable.getProtocolMessages(), messageVariable.getRecordMessages());
        action.setAfter_entries(after_variable.getValue());

        trace.addTlsAction(action);

        return variable;
    }

    @Override
    public Variable changeVerifyData(Variable message, Variable masterSecret, String alias){
        MessageVariable messageVariable = (MessageVariable) message;
        ChangeVerifyDataAction action = new ChangeVerifyDataAction(alias, messageVariable.getProtocolMessages(), messageVariable.getRecordMessages());
        action.setMasterSecret_container((List<byte[]>) masterSecret.getValue());

        trace.addTlsAction(action);

        return message;
    }

    @Override
    public Variable calculateMasterSecret(String clientAlias, String serverAlias, Variable preMasterSecret, Variable clientRandom, Variable serverRandom){
        List<byte[]> masterSecret = new ArrayList<>();

        CalculateMasterSecret action = new CalculateMasterSecret(clientAlias, serverAlias, masterSecret);
        action.setPreMasterSecret_container((List<byte[]>) preMasterSecret.getValue());
        action.setClientRandom_container((List<byte[]>) clientRandom.getValue());
        action.setServerRandom_container((List<byte[]>) serverRandom.getValue());

        trace.addTlsAction(action);

        return new ConstantVariable<byte[]>(masterSecret);
    }

    @Override
    public Variable reEncryptRSAClientKeyExchange(Variable msg, Variable premasterSecret, Variable encryptKey, String alias){
        MessageVariable messageVariable = (MessageVariable) msg;

        ReEncryptRSAClientKeyExchange reEncryptRSAClientKeyExchange = new ReEncryptRSAClientKeyExchange(alias, messageVariable.getProtocolMessages(), messageVariable.getRecordMessages());
        reEncryptRSAClientKeyExchange.setEncryptKey_container((List<PublicKeyContent>) encryptKey.getValue());
        reEncryptRSAClientKeyExchange.setPreMasterSecret_container((List<byte[]>) premasterSecret.getValue());
        trace.addTlsAction(reEncryptRSAClientKeyExchange);

        return msg;
    }

    @Override
    public void updateContext(String alias, Variable msg, boolean isSent){
        MessageVariable messageVariable = (MessageVariable) msg;
        UpdateContextAction action = new UpdateContextAction(alias, ((List<ProtocolMessage>) messageVariable.getProtocolMessages()), isSent);
        trace.addTlsAction(action);
    }

}
