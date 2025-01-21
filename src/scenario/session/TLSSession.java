package scenario.session;

import de.rub.nds.tlsattacker.core.connection.AliasedConnection;
import de.rub.nds.tlsattacker.core.constants.*;
import de.rub.nds.tlsattacker.core.crypto.KeyShareCalculator;
import de.rub.nds.tlsattacker.core.crypto.MessageDigestCollector;
import de.rub.nds.tlsattacker.core.layer.Message;
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
import maude.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.util.io.pem.PemReader;
import protocol.Protocol;
import protocol.Variable;
import scenario.*;

import java.io.FileReader;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public abstract class TLSSession implements Protocol {

    protected AliasedConnection connection;
    private WorkflowTrace trace;
    protected String alias;
    private SecureRandom secureRandom;
    protected TLSAttacker attacker;

    private static Logger LOGGER = LogManager.getLogger();


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

    public abstract void accept(int port, String ip);
    public abstract void connect(int port, String ip);

    @Override
    public void close() { }

    @Override
    public Variable recv() {
        List<ProtocolMessage> protocolMessages = new ArrayList<>();
        List<Record> recordMessages = new ArrayList<>();
        trace.addTlsAction(new ReceiveOneAction(alias, recordMessages, protocolMessages));
        return new MessageVariable(recordMessages, protocolMessages);
    }

    @Override
    public void send(Variable variable) {
        MessageVariable messageVariable = (MessageVariable) variable;
        SendAction action = new SendAction(alias, messageVariable.getProtocolMessages());
        action.setConfiguredRecords(messageVariable.getRecordMessages());
        trace.addTlsAction(action);
    }

    @Override
    public Variable encrypt(Variable message) {
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
    public Variable decrypt(Variable message) {
        MessageVariable messageVariable = (MessageVariable) message;

        DecryptAction action = new DecryptAction(alias);
        action.setRecordMessage((List<Record>)messageVariable.getRecordMessages());
        action.setProtocolMessage((List<ProtocolMessage>)messageVariable.getProtocolMessages());
        trace.addTlsAction(action);

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
    public Variable emptyCertificate(){
        List<CertificateEntry> container = new ArrayList<>();
        return new CertificateVariable(container);
    }

    @Override
    public Variable makeCertificate(String pemFilePath) {
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
        List<de.rub.nds.tlsattacker.core.constants.ProtocolMessageType> container = new ArrayList<>();
        container.add(msgType.transform());
        return new ConstantVariable<de.rub.nds.tlsattacker.core.constants.ProtocolMessageType>(container);
    }

    @Override
    public Variable constant(maude.ProtocolVersion version) {
        List<de.rub.nds.tlsattacker.core.constants.ProtocolVersion> container = new ArrayList<>();
        container.add(version.transform());
        return new ConstantVariable<de.rub.nds.tlsattacker.core.constants.ProtocolVersion>(container);
    }

    @Override
    public Variable constant(maude.HandshakeMessageType msgType) {
        List<de.rub.nds.tlsattacker.core.constants.HandshakeMessageType> container = new ArrayList<>();
        container.add(msgType.transform());
        return new ConstantVariable<de.rub.nds.tlsattacker.core.constants.HandshakeMessageType>(container);
    }

    @Override
    public Variable constant(maude.CipherSuite... ciphers) {
        List<de.rub.nds.tlsattacker.core.constants.CipherSuite> container = new ArrayList<>();
        for(maude.CipherSuite cipher: ciphers){
            container.add(cipher.transform());
        }
        return new ConstantVariable<de.rub.nds.tlsattacker.core.constants.CipherSuite>(container);
    }

    @Override
    public Variable constant(maude.CompressionMethod... methods) {
        List<de.rub.nds.tlsattacker.core.constants.CompressionMethod> container = new ArrayList<>();
        for(maude.CompressionMethod method : methods){
            container.add(method.transform());
        }
        return new ConstantVariable<de.rub.nds.tlsattacker.core.constants.CompressionMethod>(container);
    }

    @Override
    public Variable constant(maude.MessageSize size) {
        List<Boolean> container = new ArrayList<>();
        container.add(size.equals(maude.MessageSize.VALID));
        return new ConstantVariable<Boolean>(container);
    }

    @Override
    public Variable constant(maude.Random random) {
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
        List<de.rub.nds.tlsattacker.core.constants.AlertLevel> container = new ArrayList<>();
        container.add(level.transform());
        return new ConstantVariable<de.rub.nds.tlsattacker.core.constants.AlertLevel>(container);
    }

    @Override
    public Variable constant(maude.AlertDescription description) {
        List<de.rub.nds.tlsattacker.core.constants.AlertDescription> container = new ArrayList<>();
        container.add(description.transform());
        return new ConstantVariable<de.rub.nds.tlsattacker.core.constants.AlertDescription>(container);
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
    public Variable constant(maude.SupportedVersion... supportedVersions) {
        List<ProtocolVersion> container = new ArrayList<>();
        for(maude.SupportedVersion version : supportedVersions){
            container.add(version.transform());
        }
        return new ConstantVariable<ProtocolVersion>(container);
    }

    @Override
    public Variable getContentType(Variable msg){
        MessageVariable messageVariable = (MessageVariable) msg;
        List<de.rub.nds.tlsattacker.core.constants.ProtocolMessageType> container = new ArrayList<>();
        FieldAction action = new FieldAction<de.rub.nds.tlsattacker.core.constants.ProtocolMessageType>(container,
                () -> {
                    if(!((MessageVariable) msg).getRecordMessages().isEmpty()){
                        Record record = ((MessageVariable) msg).getRecordMessages().get(0);
                        return record.getContentMessageType();
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<de.rub.nds.tlsattacker.core.constants.ProtocolMessageType>(container);
    }

    @Override
    public Variable getRecordVersion(Variable msg) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<de.rub.nds.tlsattacker.core.constants.ProtocolVersion> container = new ArrayList<>();
        FieldAction action = new FieldAction<de.rub.nds.tlsattacker.core.constants.ProtocolVersion>(container,
                () -> {
                    if(!((MessageVariable) msg).getRecordMessages().isEmpty()){
                        Record record = ((MessageVariable) msg).getRecordMessages().get(0);
                        return de.rub.nds.tlsattacker.core.constants.ProtocolVersion.getProtocolVersion(record.getProtocolVersion().getValue());
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<de.rub.nds.tlsattacker.core.constants.ProtocolVersion>(container);
    }

    @Override
    public Variable getHandshakeMessageType(Variable msg) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<de.rub.nds.tlsattacker.core.constants.HandshakeMessageType> container = new ArrayList<>();
        FieldAction action = new FieldAction<de.rub.nds.tlsattacker.core.constants.HandshakeMessageType>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        HandshakeMessage handshakeMessage = (HandshakeMessage) ((MessageVariable) msg).getProtocolMessages().get(0);
                        return handshakeMessage.getHandshakeMessageType();
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<de.rub.nds.tlsattacker.core.constants.HandshakeMessageType>(container);
    }

    @Override
    public Variable getProtocolVersion(Variable msg) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<de.rub.nds.tlsattacker.core.constants.ProtocolVersion> container = new ArrayList<>();
        FieldAction action = new FieldAction<de.rub.nds.tlsattacker.core.constants.ProtocolVersion>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                        if(message instanceof ClientHelloMessage){
                            return de.rub.nds.tlsattacker.core.constants.ProtocolVersion.getProtocolVersion(((ClientHelloMessage) message).getProtocolVersion().getValue());
                        }
                        else if (message instanceof ServerHelloMessage){
                            return de.rub.nds.tlsattacker.core.constants.ProtocolVersion.getProtocolVersion(((ServerHelloMessage) message).getProtocolVersion().getValue());
                        }
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<de.rub.nds.tlsattacker.core.constants.ProtocolVersion>(container);
    }

    @Override
    public Variable getCipherSuite(Variable msg) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<de.rub.nds.tlsattacker.core.constants.CipherSuite> container = new ArrayList<>();
        FieldAction action = new FieldAction<de.rub.nds.tlsattacker.core.constants.CipherSuite>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                        if(message instanceof ClientHelloMessage){
                            ClientHelloMessage clientHelloMessage = (ClientHelloMessage) message;
                            return de.rub.nds.tlsattacker.core.constants.CipherSuite.getCipherSuite(clientHelloMessage.getCipherSuites().getValue());
                        } else if (message instanceof ServerHelloMessage){
                            ServerHelloMessage serverHelloMessage = (ServerHelloMessage) message;
                            return de.rub.nds.tlsattacker.core.constants.CipherSuite.getCipherSuite(serverHelloMessage.getSelectedCipherSuite().getValue());
                        }
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<de.rub.nds.tlsattacker.core.constants.CipherSuite>(container);
    }

    @Override
    public Variable getCompressionMethod(Variable msg) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<de.rub.nds.tlsattacker.core.constants.CompressionMethod> container = new ArrayList<>();
        FieldAction action = new FieldAction<de.rub.nds.tlsattacker.core.constants.CompressionMethod>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                        if(message instanceof ClientHelloMessage){
                            ClientHelloMessage clientHelloMessage = (ClientHelloMessage) message;
                            return de.rub.nds.tlsattacker.core.constants.CompressionMethod.getCompressionMethod(clientHelloMessage.getCompressions().getValue()[0]);
                        } else if (message instanceof ServerHelloMessage){
                            ServerHelloMessage serverHelloMessage = (ServerHelloMessage) message;
                            return de.rub.nds.tlsattacker.core.constants.CompressionMethod.getCompressionMethod(serverHelloMessage.getSelectedCompressionMethod().getValue());
                        }
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<de.rub.nds.tlsattacker.core.constants.CompressionMethod>(container);
    }

    @Override
    public Variable getAlertLevel(Variable msg) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<de.rub.nds.tlsattacker.core.constants.AlertLevel> container = new ArrayList<>();
        FieldAction action = new FieldAction<de.rub.nds.tlsattacker.core.constants.AlertLevel>(container,
                () -> {
                    ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                    if((message instanceof AlertMessage) && !((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        AlertMessage alertMessage = (AlertMessage) message;
                        return de.rub.nds.tlsattacker.core.constants.AlertLevel.getAlertLevel(alertMessage.getLevel().getValue());
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<de.rub.nds.tlsattacker.core.constants.AlertLevel>(container);
    }

    @Override
    public Variable getAlertDescription(Variable msg) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<de.rub.nds.tlsattacker.core.constants.AlertDescription> container = new ArrayList<>();
        FieldAction action = new FieldAction<de.rub.nds.tlsattacker.core.constants.AlertDescription>(container,
                () -> {
                    ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                    if((message instanceof AlertMessage) && !((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        AlertMessage alertMessage = (AlertMessage) ((MessageVariable) msg).getProtocolMessages().get(0);
                        return de.rub.nds.tlsattacker.core.constants.AlertDescription.getAlertDescription(alertMessage.getDescription().getValue());
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<de.rub.nds.tlsattacker.core.constants.AlertDescription>(container);
    }

    @Override
    public Variable getSupportedVersion(Variable msg) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<de.rub.nds.tlsattacker.core.constants.ProtocolVersion> container = new ArrayList<>();
        FieldAction action = new FieldAction<de.rub.nds.tlsattacker.core.constants.ProtocolVersion>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                        if(message instanceof HelloMessage){
                            HelloMessage helloMessage = (HelloMessage) message;
                            for(ExtensionMessage extMsg : helloMessage.getExtensions()){
                                if(ExtensionType.getExtensionType(extMsg.getExtensionType().getValue()) == ExtensionType.SUPPORTED_VERSIONS){
                                    return de.rub.nds.tlsattacker.core.constants.ProtocolVersion.getProtocolVersion(extMsg.getExtensionContent().getValue());
                                }
                            }
                        }
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<de.rub.nds.tlsattacker.core.constants.ProtocolVersion>(container);
    }

    @Override
    public Variable getSignatureAndHashAlgorithm(Variable msg) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm> container = new ArrayList<>();
        FieldAction action = new FieldAction<de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                        if(message instanceof HelloMessage){
                            HelloMessage helloMessage = (HelloMessage) message;
                            for(ExtensionMessage extMsg : helloMessage.getExtensions()){
                                if(ExtensionType.getExtensionType(extMsg.getExtensionType().getValue()) == ExtensionType.SIGNATURE_AND_HASH_ALGORITHMS){
                                    return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.getSignatureAndHashAlgorithm(extMsg.getExtensionContent().getValue());
                                }
                            }
                        }
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm>(container);
    }

    @Override
    public Variable getNamedGroupFromKeyShares(Variable keyShares){
        ConstantVariable<List<KeyShareEntry>> fieldVariable = (ConstantVariable<List<KeyShareEntry>>) keyShares;
        List<de.rub.nds.tlsattacker.core.constants.NamedGroup> container = new ArrayList<>();
        FieldAction action = new FieldAction<de.rub.nds.tlsattacker.core.constants.NamedGroup>(container,
                ()->{
                    if(!(fieldVariable.getValue().isEmpty())){
                        KeyShareEntry entry = fieldVariable.getValue().get(0).get(0);
                        return entry.getGroupConfig();
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<de.rub.nds.tlsattacker.core.constants.NamedGroup>(container);
    }


    @Override
    public Variable getNamedGroup(Variable msg) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<de.rub.nds.tlsattacker.core.constants.NamedGroup> container = new ArrayList<>();
        FieldAction action = new FieldAction<de.rub.nds.tlsattacker.core.constants.NamedGroup>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                        if(message instanceof HelloMessage){
                            HelloMessage helloMessage = (HelloMessage) message;
                            for(ExtensionMessage extMsg : helloMessage.getExtensions()){
                                if(ExtensionType.getExtensionType(extMsg.getExtensionType().getValue()) == ExtensionType.ELLIPTIC_CURVES){
                                    return de.rub.nds.tlsattacker.core.constants.NamedGroup.getNamedGroup(extMsg.getExtensionContent().getValue());
                                }
                            }
                        }
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<de.rub.nds.tlsattacker.core.constants.NamedGroup>(container);
    }

    @Override
    public Variable getKeyShareEntries(Variable msg) {
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
                                    return keyShareExtensionMessage.getKeyShareList();
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
    public Variable buildMessage(Variable record, Variable protocol_message){
        return new MessageVariable(
                (List<Record>) record.getValue(),
                (List<ProtocolMessage>) protocol_message.getValue()
        );
    }

    @Override
    public Variable buildRecord(Variable content_type, Variable record_version, Variable message) {
        List<Record> container = new ArrayList<>();

        BuildRecordAction action = new BuildRecordAction(container);
        action.setProtocolMessageType((List<de.rub.nds.tlsattacker.core.constants.ProtocolMessageType>) content_type.getValue());
        action.setProtocolVersion((List<de.rub.nds.tlsattacker.core.constants.ProtocolVersion>) record_version.getValue());
        action.setProtocolMessage((List<ProtocolMessage>) message.getValue());

        trace.addTlsAction(action);

        return new RecordVariable(container);
    }

    @Override
    public Variable buildClientHello(Variable versions, Variable ciphers, Variable random, Variable sessionId, Variable methods){
        List<ProtocolMessage> container = new ArrayList<>();

        BuildClientHelloAction action = new BuildClientHelloAction(alias, container);
        action.setVersion((List<de.rub.nds.tlsattacker.core.constants.ProtocolVersion>) versions.getValue());
        action.setCipherSuites((List<de.rub.nds.tlsattacker.core.constants.CipherSuite>) ciphers.getValue());
        action.setRandom((List<byte[]>) random.getValue());
        action.setSessionId((List<byte[]>) sessionId.getValue());
        action.setCompressions((List<de.rub.nds.tlsattacker.core.constants.CompressionMethod>) methods.getValue());
        trace.addTlsAction(action);

        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildServerHello(Variable version, Variable suite, Variable random, Variable sessionId, Variable compression){
        List<ProtocolMessage> container = new ArrayList<>();

        BuildServerHelloAction action = new BuildServerHelloAction(alias, container);
        action.setVersion((List<de.rub.nds.tlsattacker.core.constants.ProtocolVersion>) version.getValue());
        action.setCipherSuite((List<de.rub.nds.tlsattacker.core.constants.CipherSuite>) suite.getValue());
        action.setRandom((List<byte[]>) random.getValue());
        action.setSessionId((List<byte[]>) sessionId.getValue());
        action.setCompression((List<de.rub.nds.tlsattacker.core.constants.CompressionMethod>) compression.getValue());
        trace.addTlsAction(action);

        return new ProtocolMessageVariable(container);
    }

    @Override
    public void addSupportedVersionExtension(Variable handshake_message, Variable supported_versions){
        AddSupportedVersionAction action = new AddSupportedVersionAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        action.setExtensions((List<ProtocolVersion>) supported_versions.getValue());
        trace.addTlsAction(action);
    }

    @Override
    public void addSignatureAndHashAlgorithmExtension(Variable handshake_message, Variable algorithms){
        AddSignatureAndHashAlgorithmAction action = new AddSignatureAndHashAlgorithmAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        action.setExtensions((List<SignatureAndHashAlgorithm>) algorithms.getValue());
        trace.addTlsAction(action);
    }

    @Override
    public void addSupportedGroupExtension(Variable handshake_message, Variable supported_groups){
        AddSupportedGroupAction action = new AddSupportedGroupAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        action.setExtensions((List<NamedGroup>) supported_groups.getValue());
        trace.addTlsAction(action);
    }

    @Override
    public void addKeyShareExtension(Variable handshake_message, Variable key_shares){
        AddKeyShareAction action = new AddKeyShareAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        action.setExtensions((List<KeyShareEntry>) key_shares.getValue());
        trace.addTlsAction(action);
    }

    @Override
    public Variable buildCertificate(Variable certificate) {
        List<ProtocolMessage> container = new ArrayList<>();
        BuildCertificateAction action = new BuildCertificateAction(alias, container);
        action.setCertificate((List<CertificateEntry>) certificate.getValue());

        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildEncryptedExtension() {
        List<ProtocolMessage> container = new ArrayList<>();
        BuildEncryptedExtensionAction action = new BuildEncryptedExtensionAction(alias, container);

        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildCertificateRequest(Variable certificate_context) {
        List<ProtocolMessage> container = new ArrayList<>();
        BuildCertificateRequestAction action = new BuildCertificateRequestAction(alias, container);
        action.setCertificateRequestContext((List<byte[]>) certificate_context.getValue());

        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildCertificateVerify(Variable signatureHashAlgorithm) {
        List<ProtocolMessage> container = new ArrayList<>();
        BuildCertificateVerifyAction action = new BuildCertificateVerifyAction(alias, container);
        action.setSignature_and_hash_algorithm_container((List<SignatureAndHashAlgorithm>)signatureHashAlgorithm.getValue());

        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildFinished() {
        List<ProtocolMessage> container = new ArrayList<>();
        BuildFinishedAction action = new BuildFinishedAction(alias, container);

        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildKeyShareEntry(Variable group) {
        List<KeyShareEntry> container = new ArrayList<>();
        BuildKeyShareEntryAction action = new BuildKeyShareEntryAction(alias, container);
        action.setNamedGroup((List<NamedGroup>) group.getValue());
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
    public Variable changeCertificate(Variable variable, Variable before_certificate, Variable after_certificate) {
        MessageVariable messageVariable = (MessageVariable) variable;
        CertificateVariable before_variable = (CertificateVariable) before_certificate;
        CertificateVariable after_variable = (CertificateVariable) after_certificate;
        ChangeCertificateAction action = new ChangeCertificateAction(messageVariable.getProtocolMessages(), before_variable.getValue(), after_variable.getValue());

        trace.addTlsAction(action);

        return variable;
    }

    @Override
    public void updateDigest(Variable msg){
        UpdateDigestAction action = new UpdateDigestAction(alias, (List<ProtocolMessage>)msg.getValue());
        trace.addTlsAction(action);
    }


}
