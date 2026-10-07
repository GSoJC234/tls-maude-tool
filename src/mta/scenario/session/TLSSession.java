package mta.scenario.session;

import de.rub.nds.tlsattacker.core.config.Config;
import de.rub.nds.tlsattacker.core.connection.AliasedConnection;
import de.rub.nds.tlsattacker.core.constants.*;
import de.rub.nds.tlsattacker.core.constants.AlertDescription;
import de.rub.nds.tlsattacker.core.constants.AlertLevel;
import de.rub.nds.tlsattacker.core.constants.CipherSuite;
import de.rub.nds.tlsattacker.core.constants.CompressionMethod;
import de.rub.nds.tlsattacker.core.constants.HandshakeMessageType;
import de.rub.nds.tlsattacker.core.constants.NamedGroup;
import de.rub.nds.tlsattacker.core.constants.ProtocolMessageType;
import de.rub.nds.tlsattacker.core.constants.ProtocolVersion;
import de.rub.nds.tlsattacker.core.constants.PskKeyExchangeMode;
import de.rub.nds.tlsattacker.core.crypto.MessageDigestCollector;
import de.rub.nds.tlsattacker.core.exceptions.SkipActionException;
import de.rub.nds.tlsattacker.core.protocol.ProtocolMessage;
import de.rub.nds.tlsattacker.core.protocol.message.*;
import de.rub.nds.tlsattacker.core.protocol.message.cert.CertificateEntry;
import de.rub.nds.tlsattacker.core.protocol.message.extension.*;
import de.rub.nds.tlsattacker.core.protocol.message.extension.keyshare.KeyShareEntry;
import de.rub.nds.tlsattacker.core.protocol.message.extension.psk.PskSet;
import de.rub.nds.tlsattacker.core.record.Record;
import de.rub.nds.tlsattacker.core.record.cipher.cryptohelper.KeySet;
import de.rub.nds.tlsattacker.core.state.SessionTicket;
import de.rub.nds.tlsattacker.core.state.State;
import de.rub.nds.tlsattacker.core.util.ProviderUtil;
import de.rub.nds.tlsattacker.core.workflow.WorkflowExecutor;
import de.rub.nds.tlsattacker.core.workflow.WorkflowTrace;
import de.rub.nds.tlsattacker.core.workflow.action.*;
import de.rub.nds.tlsattacker.core.workflow.action.custom.*;
import de.rub.nds.tlsattacker.core.workflow.action.custom.extension.*;
import de.rub.nds.tlsattacker.core.workflow.observation.ExecutionEventWorkflowExecutor;
import de.rub.nds.x509attacker.config.X509CertificateConfig;
import de.rub.nds.x509attacker.constants.X509NamedCurve;
import de.rub.nds.x509attacker.context.X509Context;
import de.rub.nds.x509attacker.x509.model.X509Certificate;
import de.rub.nds.x509attacker.x509.model.publickey.PublicKeyContent;
import de.rub.nds.x509attacker.x509.model.publickey.X509EcdhEcdsaPublicKey;
import de.rub.nds.x509attacker.x509.model.publickey.X509RsaPublicKey;
import mta.maude.constant.*;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.RSAPrivateKey;
import org.bouncycastle.asn1.sec.ECPrivateKey;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import mta.protocol.Protocol;
import mta.protocol.Variable;
import mta.scenario.TargetExecutionMetadata;
import mta.scenario.variable.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TLSSession implements Protocol {
    private static final Logger LOGGER = LogManager.getLogger(TLSSession.class);
    private static final int SESSION_ID_BASELINE_SIZE = 32;

    protected AliasedConnection connection;
    private WorkflowTrace trace;
    protected String alias;
    private SecureRandom secureRandom;
    private Config config = null;
    private WorkflowExecutor executor = null;
    private State state = null;
    private boolean reifyServerCertificateClientHello = false;
    private final String ocspResponseFixtureDirectory;

    public TLSSession(String configPath){
        this(configPath, TargetExecutionMetadata.empty());
    }

    public TLSSession(String configPath, TargetExecutionMetadata targetExecutionMetadata){
        ProviderUtil.addBouncyCastleProvider();
        config = Config.createConfig(new File(configPath));
        applyTargetExecutionMetadata(config, targetExecutionMetadata);
        ocspResponseFixtureDirectory = targetExecutionMetadata == null
                ? "" : targetExecutionMetadata.ocspResponseFixtureDirectory();
        reifyServerCertificateClientHello =
                "Server".equalsIgnoreCase(targetExecutionMetadata.testerTlsRole())
                        && "certificate_to_client_hello".equalsIgnoreCase(
                                targetExecutionMetadata.testerMessageConcretization());
        trace = new WorkflowTrace();

        state = new State();
        state.addWorkflowTrace(trace);
        state.addConfig(config);

        executor = new ExecutionEventWorkflowExecutor(state);
    }

    private void applyTargetExecutionMetadata(Config config, TargetExecutionMetadata metadata) {
        if (metadata == null) {
            return;
        }
        config.setTargetLibraryName(metadata.libraryName());
        config.setTargetLibraryVersion(metadata.libraryVersion());
        config.setTargetLibraryPath(metadata.libraryPath());
        config.setTargetTlsRole(metadata.tlsRole());
        config.setTargetExecutionMode(metadata.executionMode());
        config.setTargetRuntimePlatform(metadata.runtimePlatform());
        config.setTargetDockerImage(metadata.dockerImage());
        config.setTargetBuildProfile(metadata.buildProfile());
        config.setTargetBinaryPath(metadata.binaryPath());
    }

    public void execute(){
        executor.executeWorkflow();
    }

    public void exit() {
        executor.closeConnection();
    }

    @Override
    public void setRandomPrivateKey(String group) {
        byte[] privateKey;
        try{
            privateKey = new byte[32];
            this.secureRandom = SecureRandom.getInstanceStrong();
            this.secureRandom.nextBytes(privateKey);
            config.setDefaultKeySharePrivateKey(NamedGroup.valueOf(group), new BigInteger(privateKey));
        } catch(NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void accept(String alias, String ip, int port){
        AcceptAction action = new AcceptAction(alias);
        action.setIp(ip);
        action.setPort(port);
        action.setConnectionTimeOut(300000);
        trace.addTlsAction(action);
        trace.addTlsAction(new ClearDigestAction(alias));
    }

    @Override
    public void connect(String alias, String ip, int port){
        ConnectAction action = new ConnectAction(alias);
        action.setIp(ip);
        action.setPort(port);
        action.setConnectionTimeOut(300000);
        trace.addTlsAction(action);
        trace.addTlsAction(new ClearDigestAction(alias));
    }

    @Override
    public void close(String alias) {
        CloseAction action = new CloseAction(alias);
        trace.addTlsAction(action);
    }

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
    public void echoApplicationData(String alias) {
        trace.addTlsAction(new SendAction(alias, new ApplicationMessage()));
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
    public Variable getCertificate(String pemFilePath) {
        List<CertificateEntry> container = new ArrayList<>();
        try{
            PemReader pemReader = new PemReader(new FileReader(pemFilePath));
            byte[] derBytes = pemReader.readPemObject().getContent();
            CertificateEntry entry = new CertificateEntry(derBytes);
            container.add(entry);
        } catch (Exception E){
            return null;
        }
        return new CertificateVariable(container);
    }

    @Override
    public Variable generateEmptyCertificate() {
        List<CertificateEntry> container = new ArrayList<>();
        return new CertificateVariable(container);
    }

    @Override
    public Variable genCertificatePrivateKey(String privateKeyPath){
        try (PemReader pemReader = new PemReader(new FileReader(privateKeyPath))) {
            PemObject po = pemReader.readPemObject();
            if (po == null) {
                throw new IllegalArgumentException("Empty PEM: " + privateKeyPath);
            }
            String type = po.getType();
            byte[] der = po.getContent();

            List<byte[]> privateKeyContainer = new ArrayList<>();

            if ("PRIVATE KEY".equalsIgnoreCase(type)) {
                addPkcs8PrivateKey(der, privateKeyContainer);
            } else if ("RSA PRIVATE KEY".equalsIgnoreCase(type)) {
                RSAPrivateKey rsa = RSAPrivateKey.getInstance(ASN1Primitive.fromByteArray(der));
                privateKeyContainer.add(rsa.getPrivateExponent().toByteArray());
                privateKeyContainer.add(rsa.getModulus().toByteArray());
            } else if ("EC PRIVATE KEY".equalsIgnoreCase(type)) {
                ECPrivateKey ec = ECPrivateKey.getInstance(ASN1Primitive.fromByteArray(der));
                privateKeyContainer.add(ec.getKey().toByteArray());
            } else {
                throw new IllegalArgumentException("Unsupported PEM type: " + type);
            }
            return new ConstantVariable<byte[]>(privateKeyContainer);

        } catch (Exception e){
            throw new RuntimeException("Could not parse private key from: " + privateKeyPath, e);
        }
    }

    private void addPkcs8PrivateKey(byte[] der, List<byte[]> privateKeyContainer) throws IOException {
        PrivateKeyInfo privateKeyInfo = PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(der));
        ASN1ObjectIdentifier algorithm = privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm();
        ASN1Primitive privateKey = privateKeyInfo.parsePrivateKey().toASN1Primitive();

        if (PKCSObjectIdentifiers.rsaEncryption.equals(algorithm)) {
            RSAPrivateKey rsa = RSAPrivateKey.getInstance(privateKey);
            privateKeyContainer.add(rsa.getPrivateExponent().toByteArray());
            privateKeyContainer.add(rsa.getModulus().toByteArray());
            return;
        }
        if (X9ObjectIdentifiers.id_ecPublicKey.equals(algorithm)) {
            privateKeyContainer.add(ECPrivateKey.getInstance(privateKey).getKey().toByteArray());
            return;
        }
        throw new IllegalArgumentException("Unsupported PKCS#8 private key algorithm: " + algorithm.getId());
    }



    @Override
    public Variable generateRandom(Random random) {
        List<byte[]> container = new ArrayList<>();
        if(random == Random.EMPTY){
            container.add(new byte[]{});
        } else if(random == Random.HRR_NONCE){
            container.add(ServerHelloMessage.getHelloRetryRequestRandom());
        } else{
            byte[] randomByte = new byte[32];
            try {
                this.secureRandom = SecureRandom.getInstanceStrong();
                this.secureRandom.nextBytes(randomByte);
            } catch (NoSuchAlgorithmException e) {
            }
            container.add(randomByte);
        }
        return new ConstantVariable<byte[]>(container);
    }

    @Override
    public Variable earlyDataPayload() {
        List<byte[]> container = new ArrayList<>();
        container.add(new byte[] {(byte) 0x00});
        return new ConstantVariable<>(container);
    }

    @Override
    public Variable generateTicket(Variable ticketSize, Variable nonce) {
        List<SessionTicket> container = new ArrayList<>();

        SessionTicket sessionTicket = new SessionTicket();

        sessionTicket.setTicketNonce((byte[]) nonce.getValue().get(0));
        sessionTicket.setTicketNonceLength(sessionTicket.getTicketNonce().getValue().length);

        Object configuredTicketSize = ticketSize.getValue().get(0);
        if (!(configuredTicketSize instanceof Number)) {
            throw new IllegalArgumentException("Ticket size must be numeric");
        }

        int identitySize = ((Number) configuredTicketSize).intValue();
        if (identitySize < 0 || identitySize > 0xFFFF) {
            throw new IllegalArgumentException(
                    "Ticket size must fit the TLS uint16 length field: " + identitySize);
        }

        SecureRandom rnd = new SecureRandom();
        byte[] identity = new byte[identitySize];
        rnd.nextBytes(identity);
        sessionTicket.setIdentity(identity);
        sessionTicket.setIdentityLength(identity.length);

        byte[] ticketAgeAdd = new byte[4];
        rnd.nextBytes(ticketAgeAdd);
        sessionTicket.setTicketAgeAdd(ticketAgeAdd);

        container.add(sessionTicket);

        return new ConstantVariable<SessionTicket>(container);
    }

    @Override
    public Variable generatePSK(String alias, Variable ticket) {
        List<PskSet> container = new ArrayList<>();

        GeneratePSKAction action = new GeneratePSKAction(alias, container);
        action.setSessionTicket((List<SessionTicket>) ticket.getValue());
        trace.addTlsAction(action);

        return new ConstantVariable<PskSet>(container);
    }

    @Override
    public void setTicketAgeMillis(String alias, Variable ticket, Variable ageMillis) {
        if (ticket == null || ticket.getValue() == null) {
            throw new IllegalArgumentException("Session ticket must be provided");
        }
        if (ageMillis == null || ageMillis.getValue() == null || ageMillis.getValue().isEmpty()) {
            throw new IllegalArgumentException("Ticket age in milliseconds must be provided");
        }
        Object value = ageMillis.getValue().get(0);
        if (!(value instanceof Number)) {
            throw new IllegalArgumentException("Ticket age in milliseconds must be numeric");
        }
        long millis = ((Number) value).longValue();
        if (millis < 0) {
            throw new IllegalArgumentException("Ticket age in milliseconds must not be negative");
        }

        SetTicketAgeMillisAction action = new SetTicketAgeMillisAction(alias);
        action.setSessionTickets((List<SessionTicket>) ticket.getValue());
        action.setTicketAgeMillis(millis);
        trace.addTlsAction(action);
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
        }
    }

    @Override
    public void assertEqual(Variable var1, Variable var2) {
        AssertEqualAction<?> action = new AssertEqualAction<>(var1::getValue, var2::getValue);
        trace.addTlsAction(action);
    }

    @Override
    public Variable constant(int n) {
        List<Integer> container = new ArrayList<>();
        container.add(n);
        return new ConstantVariable<Integer>(container);
    }

    @Override
    public Variable constant(boolean value) {
        return new ConstantVariable<>(List.of(value));
    }

    @Override
    public Variable sessionId(int size) {
        if (size < 0 || size > 255) {
            throw new IllegalArgumentException("SessionID payload size must be between 0 and 255 bytes");
        }
        byte[] sessionId = new byte[size];
        new SecureRandom().nextBytes(sessionId);
        return new ConstantVariable<>(List.of(sessionId));
    }

    @Override
    public Variable sessionId(mta.maude.constant.MessageSize size) {
        if (size == null) {
            throw new IllegalArgumentException("SessionID size category must not be null");
        }
        int resolvedSize = switch (size) {
            case VALID -> SESSION_ID_BASELINE_SIZE;
            case SMALLER -> SESSION_ID_BASELINE_SIZE - 1;
            case LARGER -> SESSION_ID_BASELINE_SIZE + 1;
            case MINSIZE -> 0;
            case MAXSIZE -> 255;
        };
        return sessionId(resolvedSize);
    }

    @Override
    public Variable sessionIdSize(int size) {
        if (size < 0 || size > 255) {
            throw new IllegalArgumentException("SessionID length field must be between 0 and 255");
        }
        return constant(size);
    }

    @Override
    public Variable constant(mta.maude.constant.ProtocolMessageType msgType) {
        List<ProtocolMessageType> container = new ArrayList<>();
        container.add(msgType.transform());
        return new ConstantVariable<ProtocolMessageType>(container);
    }

    @Override
    public Variable constant(mta.maude.constant.ProtocolVersion version) {
        // UNKNOWN is a raw record-layer value, not a negotiated TLS version in TLS-Attacker's enum.
        if (version == mta.maude.constant.ProtocolVersion.UNKNOWN) {
            return new ConstantVariable<>(List.of(version.recordVersionBytes()));
        }
        List<ProtocolVersion> container = new ArrayList<>();
        container.add(version.transform());
        return new ConstantVariable<ProtocolVersion>(container);
    }

    @Override
    public Variable constant(mta.maude.constant.HandshakeMessageType msgType) {
        List<HandshakeMessageType> container = new ArrayList<>();
        container.add(msgType.transform());
        return new ConstantVariable<HandshakeMessageType>(container);
    }

    @Override
    public Variable constant(mta.maude.constant.CipherSuite... ciphers) {
        List<CipherSuite> container = new ArrayList<>();
        for(mta.maude.constant.CipherSuite cipher: ciphers){
            container.add(cipher.transform());
        }
        return new ConstantVariable<CipherSuite>(container);
    }

    @Override
    public Variable constant(mta.maude.constant.CompressionMethod... methods) {
        if (java.util.Arrays.asList(methods).contains(mta.maude.constant.CompressionMethod.INVALID_COMPRESSION)) {
            byte[] compressionBytes = new byte[methods.length];
            for (int index = 0; index < methods.length; index++) {
                compressionBytes[index] = methods[index].wireValue();
            }
            return new ConstantVariable<byte[]>(List.of(compressionBytes));
        }

        List<CompressionMethod> container = new ArrayList<>();
        for(mta.maude.constant.CompressionMethod method : methods){
            container.add(method.transform());
        }
        return new ConstantVariable<CompressionMethod>(container);
    }

    @Override
    public Variable constant(MessageSize size) {
        List<Integer> container = new ArrayList<>();
        switch(size){
            case VALID -> container.add(1);
            case SMALLER -> container.add(2);
            case LARGER -> container.add(3);
            case MINSIZE -> container.add(4);
            case MAXSIZE -> container.add(5);
        }
        return new ConstantVariable<Integer>(container);
    }

    @Override
    public Variable constant(mta.maude.constant.AlertLevel level) {
        List<AlertLevel> container = new ArrayList<>();
        container.add(level.transform());
        return new ConstantVariable<AlertLevel>(container);
    }

    @Override
    public Variable constant(mta.maude.constant.AlertDescription description) {
        List<AlertDescription> container = new ArrayList<>();
        container.add(description.transform());
        return new ConstantVariable<AlertDescription>(container);
    }

    @Override
    public Variable constant(mta.maude.constant.NamedGroup... groups) {
        List<NamedGroup> container = new ArrayList<>();
        for(mta.maude.constant.NamedGroup group: groups){
            container.add(group.transform());
        }
        return new ConstantVariable<NamedGroup>(container);
    }

    @Override
    public Variable constant(CurveType curveType){
        List<EllipticCurveType> container = new ArrayList<>();
        container.add(curveType.transform());
        return new ConstantVariable<>(container);
    }

    @Override
    public Variable constant(SignatureAlgorithm... signatureAlgorithms) {
        List<SignatureAndHashAlgorithm> container = new ArrayList<>();
        for(SignatureAlgorithm signatureAlgorithm : signatureAlgorithms){
            container.add(signatureAlgorithm.transform());
        }
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
    public Variable constant(mta.maude.constant.PskKeyExchangeMode... pskKeyExchangeModes) {
        List<PskKeyExchangeMode> container = new ArrayList<>();
        for(mta.maude.constant.PskKeyExchangeMode pskKeyExchangeMode: pskKeyExchangeModes){
            container.add(pskKeyExchangeMode.transform());
        }
        return new ConstantVariable<PskKeyExchangeMode>(container);
    }

    @Override
    public Variable constant(mta.maude.constant.CertificateType... certificateType) {
        List<ClientCertificateType> container = new ArrayList<>();
        for(mta.maude.constant.CertificateType type: certificateType){
            container.add(type.transform());
        }
        return new ConstantVariable<>(container);
    }

    @Override
    public Variable constant(OidFilterSpec... filters) {
        return new ConstantVariable<>(List.of(filters));
    }

    @Override
    public Variable constant(OcspResponseSpec... responses) {
        return new ConstantVariable<>(List.of(responses));
    }

    // Note: LongConstantVariable expands the last item
    // to the maximum allowed length as defined by the RFC specification.
    @Override
    public Variable longConstant(mta.maude.constant.CipherSuite... cipherSuites) {
        List<CipherSuite> container = new ArrayList<>();
        for(mta.maude.constant.CipherSuite cipherSuite: cipherSuites){
            container.add(cipherSuite.transform());
        }
        return new LongConstantVariable(container);
    }

    // Note: LongConstantVariable expands the last item
    // to the maximum allowed length as defined by the RFC specification.
    @Override
    public Variable longConstant(mta.maude.constant.ProtocolVersion... versions) {
        List<ProtocolVersion> container = new ArrayList<>();
        for(mta.maude.constant.ProtocolVersion version: versions){
            container.add(version.transform());
        }
        return new LongConstantVariable(container);
    }

    // Note: LongConstantVariable expands the last item
    // to the maximum allowed length as defined by the RFC specification.
    @Override
    public Variable longConstant(mta.maude.constant.NamedGroup... groups) {
        List<NamedGroup> container = new ArrayList<>();
        for(mta.maude.constant.NamedGroup group: groups){
            container.add(group.transform());
        }
        return new LongConstantVariable(container);
    }

    // Note: LongConstantVariable expands the last item
    // to the maximum allowed length as defined by the RFC specification.
    @Override
    public Variable longConstant(SignatureAlgorithm... signatureAlgorithms) {
        List<SignatureAndHashAlgorithm> container = new ArrayList<>();
        for(SignatureAlgorithm signatureAlgorithm : signatureAlgorithms){
            container.add(signatureAlgorithm.transform());
        }
        return new LongConstantVariable(container);
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
                        if (((MessageVariable) msg).getProtocolMessages().get(0) instanceof HandshakeMessage){
                            HandshakeMessage handshakeMessage = (HandshakeMessage) ((MessageVariable) msg).getProtocolMessages().get(0);
                            return handshakeMessage.getHandshakeMessageType();
                        }
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
        if (start < 0 || start + 1 >= input.length) {
            throw new IndexOutOfBoundsException("Requested pair exceeds byte array bounds.");
        }
        return new byte[] { input[start], input[start + 1] };
    }

    private <T extends ExtensionMessage> T getHelloExtension(ProtocolMessage message, Class<T> extensionClass) {
        if (!(message instanceof HelloMessage)) {
            return null;
        }
        return ((HelloMessage) message).getExtension(extensionClass);
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
                            return clientHelloMessage.getRandom().getValue();
                        } else if (message instanceof ServerHelloMessage){
                            ServerHelloMessage serverHelloMessage = (ServerHelloMessage) message;
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
                            return clientHelloMessage.getSessionId().getValue();
                        } else if (message instanceof ServerHelloMessage){
                            ServerHelloMessage serverHelloMessage = (ServerHelloMessage) message;
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
    public Variable getCertificateContext(Variable msg) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<byte[]> container = new ArrayList<>();
        FieldAction action = new FieldAction<byte[]>(container,
            () -> {
                if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                    ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                    if(message instanceof CertificateMessage){
                        return ((CertificateMessage) message).getRequestContext().getValue();
                    }
                    if(message instanceof CertificateRequestMessage){
                        return ((CertificateRequestMessage) message).getCertificateRequestContext().getValue();
                    }
                }
                return null;
            });
        trace.addTlsAction(action);
        return new ConstantVariable<byte[]>(container);
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
                        SupportedVersionsExtensionMessage svExtMsg =
                                getHelloExtension(message, SupportedVersionsExtensionMessage.class);
                        if(svExtMsg != null && svExtMsg.getSupportedVersions() != null && idx > 0){
                            byte[] versions = svExtMsg.getSupportedVersions().getValue();
                            if (versions != null) {
                                try {
                                    return ProtocolVersion.getProtocolVersion(extractPair(versions, idx - 1 ));
                                } catch (IndexOutOfBoundsException ex) {
                                    return null;
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
    public Variable getAuthenticationAlgorithm(Variable msg, int idx) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<SignatureAndHashAlgorithm> container = new ArrayList<>();
        FieldAction action = new FieldAction<SignatureAndHashAlgorithm>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                        SignatureAndHashAlgorithmsExtensionMessage sahExtMsg =
                                getHelloExtension(message, SignatureAndHashAlgorithmsExtensionMessage.class);
                        if(sahExtMsg != null && sahExtMsg.getSignatureAndHashAlgorithms() != null && idx > 0){
                            byte[] signatureAlgorithms = sahExtMsg.getSignatureAndHashAlgorithms().getValue();
                            if (signatureAlgorithms != null) {
                                try {
                                    return SignatureAndHashAlgorithm.getSignatureAndHashAlgorithm(extractPair(signatureAlgorithms, idx - 1 ));
                                } catch (IndexOutOfBoundsException ex) {
                                    return null;
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
    public Variable getNamedGroup(Variable msg, int idx) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<NamedGroup> container = new ArrayList<>();
        FieldAction action = new FieldAction<NamedGroup>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                        EllipticCurvesExtensionMessage ecExtMsg =
                                getHelloExtension(message, EllipticCurvesExtensionMessage.class);
                        if(ecExtMsg != null && ecExtMsg.getSupportedGroups() != null && idx > 0){
                            byte[] namedGroups = ecExtMsg.getSupportedGroups().getValue();
                            if (namedGroups != null) {
                                try {
                                    return NamedGroup.getNamedGroup(extractPair(namedGroups, idx - 1 ));
                                } catch (IndexOutOfBoundsException ex) {
                                    return null;
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
                        KeyShareExtensionMessage keyShareExtensionMessage =
                                getHelloExtension(message, KeyShareExtensionMessage.class);
                        if(keyShareExtensionMessage != null && keyShareExtensionMessage.getKeyShareList() != null
                                && idx > 0 && idx <= keyShareExtensionMessage.getKeyShareList().size()){
                            return keyShareExtensionMessage.getKeyShareList().subList(idx-1, idx);
                        }
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<List<KeyShareEntry>>(container);
    }

    @Override
    public Variable getPskExchangeMode(Variable msg, int idx) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<PskKeyExchangeMode> container = new ArrayList<>();
        FieldAction action = new FieldAction<PskKeyExchangeMode>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                        PSKKeyExchangeModesExtensionMessage pskExtMsg =
                                getHelloExtension(message, PSKKeyExchangeModesExtensionMessage.class);
                        if(pskExtMsg != null && pskExtMsg.getKeyExchangeModesListBytes() != null
                                && pskExtMsg.getKeyExchangeModesListBytes().getValue() != null && idx > 0){
                            byte[] pskExchangeModes = pskExtMsg.getKeyExchangeModesListBytes().getValue();
                            List<PskKeyExchangeMode> exchangeModes = PskKeyExchangeMode.getExchangeModes(pskExchangeModes);
                            if (exchangeModes != null && idx <= exchangeModes.size()) {
                                return exchangeModes.get(idx-1);
                            }
                        }
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<PskKeyExchangeMode>(container);
    }

    @Override
    public Variable getKeyShareNamedGroup(Variable keyShares){
        ConstantVariable<List<KeyShareEntry>> fieldVariable = (ConstantVariable<List<KeyShareEntry>>) keyShares;
        List<NamedGroup> container = new ArrayList<>();
        FieldAction action = new FieldAction<NamedGroup>(container,
                ()->{
                    if(fieldVariable != null && fieldVariable.getValue() != null && !(fieldVariable.getValue().isEmpty())){
                        List<KeyShareEntry> entryList = fieldVariable.getValue().get(0);
                        if (entryList != null && !entryList.isEmpty()){
                            return entryList.get(0).getGroupConfig();
                        }
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
                    List<List<KeyShareEntry>> fieldVariableValue =
                            fieldVariable == null ? null : (List<List<KeyShareEntry>>) fieldVariable.getValue();
                    if (fieldVariableValue != null && !fieldVariableValue.isEmpty()) {
                        List<KeyShareEntry> entryList = fieldVariableValue.get(0);
                        if (entryList != null && !entryList.isEmpty()) {
                            KeyShareEntry entry = entryList.get(0);
                            if (entry != null && entry.getPublicKey() != null) {
                                return entry.getPublicKey().getValue();
                            }
                        }
                    }
                    return null;
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
    public Variable getTicket(Variable msg) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<SessionTicket> container = new ArrayList<>();
        FieldAction action = new FieldAction<SessionTicket>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                        if(message instanceof NewSessionTicketMessage){
                            NewSessionTicketMessage sessionTicketMessage = (NewSessionTicketMessage) message;
                            return sessionTicketMessage.getTicket();
                        }
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new ConstantVariable<SessionTicket>(container);
    }

    @Override
    public Variable buildRecord(String alias, Variable content_type, Variable record_version, Variable record_len, Variable message) {
        List<Record> container = new ArrayList<>();

        BuildRecordAction action = new BuildRecordAction(alias, container);
        action.setProtocolMessageType((List<ProtocolMessageType>) content_type.getValue());
        List<?> recordVersionValue = record_version.getValue();
        if (recordVersionValue != null
                && !recordVersionValue.isEmpty()
                && recordVersionValue.get(0) instanceof byte[] rawRecordVersion) {
            action.setProtocolVersionBytes(rawRecordVersion);
        } else {
            action.setProtocolVersion((List<ProtocolVersion>) recordVersionValue);
        }
        action.setProtocolMessageLength((List<Integer>) record_len.getValue());
        action.setProtocolMessage((List<ProtocolMessage>) message.getValue());

        trace.addTlsAction(action);

        return new MessageVariable(container, (List<ProtocolMessage>) message.getValue());
    }
    @Override
    public Variable buildClientHello_old(String alias, Variable handshake_type, Variable versions, Variable ciphers_len, Variable ciphers, Variable random, Variable sessionIdLen, Variable sessionId, Variable compression_len, Variable methods){
        return buildClientHelloInternal(alias, handshake_type, versions, ciphers_len, ciphers, random,
                sessionId, sessionIdLen, true, compression_len, methods);
    }

    @Override
    public Variable buildClientHello(String alias, Variable handshake_type, Variable versions, Variable ciphers_len, Variable ciphers, Variable random, Variable sessionId, Variable sessionIdSize, Variable compression_len, Variable methods){
        return buildClientHelloInternal(alias, handshake_type, versions, ciphers_len, ciphers, random,
                sessionId, sessionIdSize, false, compression_len, methods);
    }

    private Variable buildClientHelloInternal(String alias, Variable handshake_type, Variable versions, Variable ciphers_len, Variable ciphers, Variable random, Variable sessionId, Variable sessionIdLength, boolean legacySessionIdLength, Variable compression_len, Variable methods){
        List<ProtocolMessage> container = new ArrayList<>();

        BuildClientHelloAction action = new BuildClientHelloAction(alias, container);
        action.setHandshakeType((List<HandshakeMessageType>) handshake_type.getValue());
        action.setVersion((List<ProtocolVersion>) versions.getValue());
        action.setCipherSuitesLen((List<Integer>) ciphers_len.getValue());
        action.setCipherSuites((List<CipherSuite>) ciphers.getValue());
        action.setRandom((List<byte[]>) random.getValue());
        action.setSessionId((List<byte[]>) sessionId.getValue());
        if (legacySessionIdLength) {
            action.setSessionIdLen((List<Integer>) sessionIdLength.getValue());
        } else {
            action.setSessionIdLength((List<Integer>) sessionIdLength.getValue());
        }
        action.setCompressionsLen((List<Integer>) compression_len.getValue());
        List<?> compressionMethods = methods.getValue();
        if (compressionMethods.size() == 1 && compressionMethods.get(0) instanceof byte[] rawCompressionBytes) {
            action.setCompressionBytes(rawCompressionBytes);
        } else {
            action.setCompressions((List<CompressionMethod>) compressionMethods);
        }
        trace.addTlsAction(action);

        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildServerHello(String alias, Variable handshake_type, Variable version, Variable suite, Variable random, Variable session_len, Variable sessionId, Variable compression){
        List<ProtocolMessage> container = new ArrayList<>();

        List<HandshakeMessageType> handshakeTypes = (List<HandshakeMessageType>) handshake_type.getValue();
        if (!handshakeTypes.isEmpty() && handshakeTypes.get(0) == HandshakeMessageType.CLIENT_HELLO) {
            BuildWrongSideClientHelloAction action = new BuildWrongSideClientHelloAction(alias, container);
            action.setHandshakeType(handshakeTypes);
            action.setVersion((List<ProtocolVersion>) version.getValue());
            action.setCipherSuite((List<CipherSuite>) suite.getValue());
            action.setRandom((List<byte[]>) random.getValue());
            action.setCompression((List<CompressionMethod>) compression.getValue());
            trace.addTlsAction(action);
            return new ProtocolMessageVariable(container);
        }

        BuildServerHelloAction action = new BuildServerHelloAction(alias, container);
        action.setHandshakeType(handshakeTypes);
        action.setVersion((List<ProtocolVersion>) version.getValue());
        action.setCipherSuite((List<CipherSuite>) suite.getValue());
        action.setRandom((List<byte[]>) random.getValue());
        action.setSessionId((List<byte[]>) sessionId.getValue());
        action.setSessionIdLen((List<Integer>) session_len.getValue());
        action.setCompression((List<CompressionMethod>) compression.getValue());
        trace.addTlsAction(action);

        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildECDHEServerKeyExchange(String alias, Variable handshake_type, Variable curve_type, Variable named_curve, Variable ec_private, Variable signature_key) {
        List<ProtocolMessage> container = new ArrayList<>();

        BuildECDHEServerKeyExchangeAction action = new BuildECDHEServerKeyExchangeAction(alias, container);
        action.setHandshakeType((List<HandshakeMessageType>) handshake_type.getValue());
        action.setCurveType((List<EllipticCurveType>) curve_type.getValue());
        action.setNamedCurve((List<NamedGroup>) named_curve.getValue());
        action.setECPrivateKey((List<byte[]>) ec_private.getValue());
        action.setSignatureKey((List<byte[]>) signature_key.getValue());
        trace.addTlsAction(action);

        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildServerHelloDone(String alias, Variable handshake_type) {
        List<ProtocolMessage> container = new ArrayList<>();

        BuildServerHelloDoneAction action = new BuildServerHelloDoneAction(alias, container);
        action.setHandshakeType((List<HandshakeMessageType>) handshake_type.getValue());
        trace.addTlsAction(action);

        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildECDHClientKeyExchange(String alias, Variable handshake_type, Variable ec_private) {
        List<ProtocolMessage> container = new ArrayList<>();

        BuildECDHClientKeyExchangeAction action = new BuildECDHClientKeyExchangeAction(alias, container);
        action.setHandshakeType((List<HandshakeMessageType>) handshake_type.getValue());
        action.setECPrivateKey((List<byte[]>) ec_private.getValue());

        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildCertificate(String alias, Variable handshakeType, Variable certificate_len, Variable certificate, Variable certificate_request_context_len, Variable certificate_request_context) {
        List<ProtocolMessage> container = new ArrayList<>();
        BuildCertificateAction action = new BuildCertificateAction(alias, container);
        action.setHandshakeType((List<HandshakeMessageType>) handshakeType.getValue());
        action.setCertificate((List<CertificateEntry>) certificate.getValue());
        action.setCertificateLen((List<Integer>) certificate_len.getValue());
        action.setCertificateRequestContext((List<byte[]>)certificate_request_context.getValue());
        action.setCertificateRequestContextLen((List<Integer>) certificate_request_context_len.getValue());

        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildCertificate(String alias, Variable handshakeType, Variable certificate_len, Variable certificate) {
        List<ProtocolMessage> container = new ArrayList<>();
        List<HandshakeMessageType> handshakeTypes =
                (List<HandshakeMessageType>) handshakeType.getValue();
        if (reifyServerCertificateClientHello
                && !handshakeTypes.isEmpty()
                && handshakeTypes.get(0) == HandshakeMessageType.CLIENT_HELLO) {
            LOGGER.info(
                    "Concretizing TLS 1.2 server Certificate mutation as a wrong-side ClientHello");
            BuildWrongSideClientHelloAction action =
                    new BuildWrongSideClientHelloAction(alias, container);
            action.setHandshakeType(handshakeTypes);
            trace.addTlsAction(action);
            return new ProtocolMessageVariable(container);
        }

        BuildCertificateAction action = new BuildCertificateAction(alias, container);
        action.setHandshakeType(handshakeTypes);
        action.setCertificate((List<CertificateEntry>) certificate.getValue());
        action.setCertificateLen((List<Integer>) certificate_len.getValue());

        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    /** Adds a symbolic OCSP response to a zero-based TLS 1.3 Certificate entry. */
    @Override
    public void addCertificateEntryStatusRequestExtension(
            String alias, Variable certificateMessage, int certificateEntryIndex,
            Variable ocspResponse) {
        if (certificateMessage == null) {
            throw new IllegalArgumentException("Certificate message is required");
        }
        if (ocspResponse == null || ocspResponse.getValue() == null
                || ocspResponse.getValue().size() != 1
                || !(ocspResponse.getValue().get(0) instanceof OcspResponseSpec)) {
            throw new IllegalArgumentException("Expected one OCSP response constant");
        }
        OcspResponseSpec spec = (OcspResponseSpec) ocspResponse.getValue().get(0);
        byte[] ocspResponseBytes =
                OcspResponseFixtureResolver.load(ocspResponseFixtureDirectory, spec);
        AddCertificateEntryStatusRequestExtensionAction action =
                new AddCertificateEntryStatusRequestExtensionAction(
                        alias, (List<ProtocolMessage>) certificateMessage.getValue());
        action.setCertificateEntryIndex(certificateEntryIndex);
        action.setOcspResponse(ocspResponseBytes);
        trace.addTlsAction(action);
    }

    @Override
    public Variable buildEncryptedExtension(String alias) {
        List<ProtocolMessage> container = new ArrayList<>();
        BuildEncryptedExtensionAction action = new BuildEncryptedExtensionAction(alias, container);

        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildEncryptedExtension(String alias, Variable handshake_type) {
        List<ProtocolMessage> container = new ArrayList<>();
        BuildEncryptedExtensionAction action = new BuildEncryptedExtensionAction(alias, container);
        action.setHandshakeType((List<HandshakeMessageType>) handshake_type.getValue());

        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildCertificateRequest(String alias, Variable handshake_type, Variable certificate_type, Variable certificate_type_length, Variable certificate_algo, Variable certificate_algo_length){
        List<ProtocolMessage> container = new ArrayList<>();
        BuildCertificateRequestAction action = new BuildCertificateRequestAction(alias, container);
        action.setHandshakeType((List<HandshakeMessageType>) handshake_type.getValue());
        action.setClientCertificateTypes((List<ClientCertificateType>) certificate_type.getValue());
        action.setClientCertificateTypesLen((List<Integer>) certificate_type_length.getValue());
        action.setSignatureAndHashAlgorithms((List<SignatureAndHashAlgorithm>) certificate_algo.getValue());
        action.setSignatureAndHashAlgorithmsLen((List<Integer>) certificate_algo_length.getValue());

        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildCertificateRequest(String alias, Variable handshake_type, Variable certificate_context_len, Variable certificate_context) {
        List<ProtocolMessage> container = new ArrayList<>();
        BuildCertificateRequestAction action = new BuildCertificateRequestAction(alias, container);
        action.setHandshakeType((List<HandshakeMessageType>) handshake_type.getValue());
        action.setCertificateRequestContext((List<byte[]>) certificate_context.getValue());
        action.setCertificateRequestContextLen((List<Integer>) certificate_context_len.getValue());

        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildCertificateVerify(String alias, Variable handshake_type, Variable certificate_private_key) {
        List<ProtocolMessage> container = new ArrayList<>();
        BuildCertificateVerifyAction action = new BuildCertificateVerifyAction(alias, container);
        action.setHandshakeType((List<HandshakeMessageType>) handshake_type.getValue());
        action.setCertificatePrivateKey((List<byte[]>) certificate_private_key.getValue());
        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildCertificateVerify(String alias, Variable handshake_type, Variable signatureHashAlgorithm, Variable certificate_private_key) {
        List<ProtocolMessage> container = new ArrayList<>();
        BuildCertificateVerifyAction action = new BuildCertificateVerifyAction(alias, container);
        action.setHandshakeType((List<HandshakeMessageType>) handshake_type.getValue());
        action.setSignature_and_hash_algorithm_container((List<SignatureAndHashAlgorithm>)signatureHashAlgorithm.getValue());
        action.setCertificatePrivateKey((List<byte[]>) certificate_private_key.getValue());
        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildCertificateVerify(String alias, Variable handshake_type, Variable wireSignatureHashAlgorithm, Variable signingSignatureHashAlgorithm, Variable certificate_private_key) {
        List<ProtocolMessage> container = new ArrayList<>();
        BuildCertificateVerifyAction action = new BuildCertificateVerifyAction(alias, container);
        action.setHandshakeType((List<HandshakeMessageType>) handshake_type.getValue());
        action.setSignature_and_hash_algorithm_container((List<SignatureAndHashAlgorithm>)wireSignatureHashAlgorithm.getValue());
        action.setSigningSignatureAndHashAlgorithmContainer((List<SignatureAndHashAlgorithm>)signingSignatureHashAlgorithm.getValue());
        action.setCertificatePrivateKey((List<byte[]>) certificate_private_key.getValue());
        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildChangeCipherSpec(String alias) {
        return buildChangeCipherSpec(alias, true);
    }

    @Override
    public Variable buildChangeCipherSpec(String alias, boolean ccsProtocolTypeValid) {
        List<ProtocolMessage> container = new ArrayList<>();
        BuildChangeCipherSpecAction action = new BuildChangeCipherSpecAction(alias, container);
        action.setCcsProtocolTypeValid(ccsProtocolTypeValid);

        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildChangeCipherSpec(String alias, Variable ccsProtocolTypeValid) {
        List<?> values = ccsProtocolTypeValid == null ? null : ccsProtocolTypeValid.getValue();
        if (values == null || values.size() != 1 || !(values.get(0) instanceof Boolean value)) {
            throw new IllegalArgumentException("CCS payload validity must be one boolean value");
        }
        return buildChangeCipherSpec(alias, value);
    }

    @Override
    public Variable buildApplicationData(String alias, Variable payload) {
        List<ProtocolMessage> container = new ArrayList<>();
        BuildApplicationDataAction action = new BuildApplicationDataAction(alias, container);
        action.setPayload((List<byte[]>) payload.getValue());

        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildEarlyData(String alias, Variable payload) {
        List<ProtocolMessage> container = new ArrayList<>();
        BuildEarlyDataAction action = new BuildEarlyDataAction(alias, container);
        action.setPayload((List<byte[]>) payload.getValue());

        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildFinished(String alias, Variable handshake_type) {
        List<ProtocolMessage> container = new ArrayList<>();
        BuildFinishedAction action = new BuildFinishedAction(alias, container);
        action.setHandshakeType((List<HandshakeMessageType>) handshake_type.getValue());

        trace.addTlsAction(action);
        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildFinished(String alias, Variable handshake_type, Variable verify_data) {
        List<ProtocolMessage> container = new ArrayList<>();
        BuildFinishedAction action = new BuildFinishedAction(alias, container);
        action.setHandshakeType((List<HandshakeMessageType>) handshake_type.getValue());
        action.setVerifyData((List<byte[]>) verify_data.getValue());

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
    public Variable buildNewSessionTicket(String alias, Variable ticket) {
        List<ProtocolMessage> container = new ArrayList<>();

        BuildNewSessionTicket action = new BuildNewSessionTicket(alias, container);
        action.setTicket((List<SessionTicket>)ticket.getValue());
        trace.addTlsAction(action);

        return new ProtocolMessageVariable(container);
    }

    @Override
    public void addSupportedVersionExtension(String alias, Variable extension_len, Variable handshake_message, Variable supported_versions){
        AddSupportedVersionAction action = new AddSupportedVersionAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        if (supported_versions instanceof LongConstantVariable){
            action.setLongExtensions((List<ProtocolVersion>) supported_versions.getValue());
        } else {
            action.setExtensions((List<ProtocolVersion>) supported_versions.getValue());
        }
        action.setExtensionLen((List<Integer>) extension_len.getValue());
        trace.addTlsAction(action);
    }

    @Override
    public void addSignatureAlgorithmExtension(String alias, Variable extension_len, Variable handshake_message, Variable algorithms){
        AddSignatureAndHashAlgorithmAction action = new AddSignatureAndHashAlgorithmAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        if (algorithms instanceof LongConstantVariable){
            action.setLongExtensions((List<SignatureAndHashAlgorithm>) algorithms.getValue());
        } else {
            action.setExtensions((List<SignatureAndHashAlgorithm>) algorithms.getValue());
        }
        action.setExtensionLen((List<Integer>) extension_len.getValue());

        trace.addTlsAction(action);
    }

    @Override
    public void addSignatureAlgorithmCertExtension(String alias, Variable extension_len, Variable handshake_message, Variable algorithms){
        AddSignatureAlgorithmCertsAction action = new AddSignatureAlgorithmCertsAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        if (algorithms instanceof LongConstantVariable){
            action.setLongExtensions((List<SignatureAndHashAlgorithm>) algorithms.getValue());
        } else {
            action.setExtensions((List<SignatureAndHashAlgorithm>) algorithms.getValue());
        }
        action.setExtensionLen((List<Integer>) extension_len.getValue());

        trace.addTlsAction(action);
    }

    @Override
    public void addSupportedSignatureAlgorithmExtension(String alias, Variable handshake_message, Variable supported_signature_algorithms) {
        AddSignatureAndHashAlgorithmAction action = new AddSignatureAndHashAlgorithmAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        if (supported_signature_algorithms instanceof LongConstantVariable){
            action.setLongExtensions((List<SignatureAndHashAlgorithm>) supported_signature_algorithms.getValue());
        } else {
            action.setExtensions((List<SignatureAndHashAlgorithm>) supported_signature_algorithms.getValue());
        }
        action.setExtensionLen((List<Integer>) supported_signature_algorithms.getValue());
        trace.addTlsAction(action);
    }

    @Override
    public void addNamedCurvesExtension(String alias, Variable handshake_message, Variable named_curves) {
        AddSupportedGroupAction action = new AddSupportedGroupAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        if (named_curves instanceof LongConstantVariable){
            action.setLongExtensions((List<NamedGroup>) named_curves.getValue());
        } else {
            action.setExtensions((List<NamedGroup>) named_curves.getValue());
        }
        action.setExtensionLen((List<Integer>) named_curves.getValue());
        trace.addTlsAction(action);
    }

    @Override
    public void addSupportedGroupExtension(String alias, Variable extension_len, Variable handshake_message, Variable supported_groups){
        AddSupportedGroupAction action = new AddSupportedGroupAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        if (supported_groups instanceof LongConstantVariable){
            action.setLongExtensions((List<NamedGroup>) supported_groups.getValue());
        } else {
            action.setExtensions((List<NamedGroup>) supported_groups.getValue());
        }
        action.setExtensionLen((List<Integer>) extension_len.getValue());

        trace.addTlsAction(action);
    }

    @Override
    public void addPSKExchangeModeExtension(String alias, Variable extension_len, Variable handshake_message, Variable psk_exchange_modes) {
        AddPSKExchangeModeAction action = new AddPSKExchangeModeAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        action.setExtensions((List<PskKeyExchangeMode>) psk_exchange_modes.getValue());
        action.setExtensionLen((List<Integer>) extension_len.getValue());

        trace.addTlsAction(action);
    }

    @Override
    public void addCHPreSharedKeyExtension(String alias, Variable extension_len, Variable handshake_message, Variable ticket) {
        AddCHPreSharedKeyAction action = new AddCHPreSharedKeyAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        action.setExtensions((List<SessionTicket>) ticket.getValue());
        action.setExtensionLen((List<Integer>) extension_len.getValue());
        trace.addTlsAction(action);
    }

    @Override
    public void addOidFiltersExtension(String alias, Variable extension_len, Variable handshake_message,
                                       Variable filters) {
        if (filters == null) {
            throw new IllegalArgumentException("OID filters must not be null");
        }
        List<String> oids = new ArrayList<>();
        List<byte[]> valuesDer = new ArrayList<>();
        var seenOids = EnumSet.noneOf(CertificateExtensionOid.class);
        for (Object item : filters.getValue()) {
            if (!(item instanceof OidFilterSpec filter)) {
                throw new IllegalArgumentException("Expected an OID filter term: " + item);
            }
            if (!seenOids.add(filter.oid())) {
                throw new IllegalArgumentException("Duplicate OID filter: " + filter.oid());
            }
            oids.add(filter.oid().dottedDecimal());
            valuesDer.add(OidFilterEncoder.encodeValue(filter));
        }

        AddOidFiltersAction action = new AddOidFiltersAction(
                alias, (List<ProtocolMessage>) handshake_message.getValue());
        action.setExtensions(oids);
        action.setValuesDer(valuesDer);
        if (extension_len != null) {
            action.setExtensionLen((List<Integer>) extension_len.getValue());
        }
        trace.addTlsAction(action);
    }

    @Override
    public void setCHPreSharedKeyBinder(String alias, Variable handshake_message, Variable binder) {
        SetCHPreSharedKeyBinderAction action =
                new SetCHPreSharedKeyBinderAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        action.setBinderValues((List<byte[]>) binder.getValue());
        trace.addTlsAction(action);
    }

    @Override
    public void addSHPreSharedKeyExtension(String alias, Variable extension_len, Variable handshake_message, Variable selected_identity) {
        AddSHPreSharedKeyAction action = new AddSHPreSharedKeyAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        action.setExtensions((List<Integer>) selected_identity.getValue());
        action.setExtensionLen((List<Integer>) extension_len.getValue());
        trace.addTlsAction(action);
    }

    @Override
    public Variable buildEmptyKeyShareEntryList() {
        List<KeyShareEntry> container = new ArrayList<>();
        return new ConstantVariable<KeyShareEntry>(container);
    }

    @Override
    public void addKeyShareEntry(String alias, Variable entry_list, Variable nonce, Variable named_group) {
        List<KeyShareEntry> keyShareEntryList = (List<KeyShareEntry>) entry_list.getValue();
        BuildKeyShareEntryAction keyShareEntryAction = new BuildKeyShareEntryAction(alias, keyShareEntryList);
        keyShareEntryAction.setNamedGroup((List<NamedGroup>) named_group.getValue());
        keyShareEntryAction.setPrivateKey((List<byte[]>) nonce.getValue());
        trace.addTlsAction(keyShareEntryAction);
    }

    @Override
    public void addEarlyDataExtension(String alias, Variable extension_len, Variable handshake_message) {
        AddEarlyDataAction action = new AddEarlyDataAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        action.setExtensionLen((List<Integer>) extension_len.getValue());
        trace.addTlsAction(action);
    }

    @Override
    public void addPostHandshakeExtension(String alias, Variable extension_len, Variable handshake_message) {
        AddPostHandshakeAuthAction action = new AddPostHandshakeAuthAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        action.setExtensionLen((List<Integer>) extension_len.getValue());
        trace.addTlsAction(action);
    }

    @Override
    public void addKeyShareExtension(String alias, Variable extension_len, Variable handshake_message, Variable key_share_entry_list) {
        AddKeyShareAction action = new AddKeyShareAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        action.setExtensions((List<KeyShareEntry>) key_share_entry_list.getValue());
        action.setExtensionLen((List<Integer>) extension_len.getValue());
        trace.addTlsAction(action);
    }

    @Override
    public void addHRRKeyShareExtension(String alias, Variable extension_len, Variable handshake_message, Variable named_group) {
        AddHRRKeyShareAction action = new AddHRRKeyShareAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        action.setExtensions((List<NamedGroup>) named_group.getValue());
        action.setExtensionLen((List<Integer>) extension_len.getValue());
        trace.addTlsAction(action);
    }

    @Override
    public void addPostHandshakeAuthExtension(String alias, Variable extension_len, Variable handshake_message) {
        AddPostHandshakeAuthAction action = new AddPostHandshakeAuthAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        action.setExtensionLen((List<Integer>) extension_len.getValue());
        trace.addTlsAction(action);
    }

    @Override
    public void addRenegotiationInfoExtension(String alias, Variable extension_len, Variable handshake_message) {
        AddRenegotiationInfoAction action = new AddRenegotiationInfoAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        action.setExtensionLen((List<Integer>) extension_len.getValue());
        trace.addTlsAction(action);
    }

    @Override
    public void addCKSExtension(String alias, Variable extension_len, Variable handshake_message) {
        AddCKSAction action = new AddCKSAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        action.setExtensionLen((List<Integer>) extension_len.getValue());
        trace.addTlsAction(action);
    }

    @Override
    public void addExtensionLen(String alias, Variable extension_len, Variable handshake_message) {
        AddExtensionLenAction action = new AddExtensionLenAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        action.setExtensionLen((List<Integer>) extension_len.getValue());
        trace.addTlsAction(action);
    }

    @Override
    public void addHandshakeLen(String alias, Variable handshake_len, Variable handshake_message) {
        AddHandshakeLenAction action = new AddHandshakeLenAction(alias, (List<ProtocolMessage>) handshake_message.getValue());
        action.setHandshakeLen((List<Integer>) handshake_len.getValue());
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
    public Variable generateVerifyData(String alias) {
        List<byte[]> verifyData = new ArrayList<>();
        GenerateVerifyDataAction action = new GenerateVerifyDataAction(alias, verifyData);
        trace.addTlsAction(action);

        return new ConstantVariable(verifyData);
    }

    @Override
    public void updateContext(String alias, Variable msg, boolean isSent){
        MessageVariable messageVariable = (MessageVariable) msg;
        UpdateContextAction action = new UpdateContextAction(alias, ((List<ProtocolMessage>) messageVariable.getProtocolMessages()), isSent);
        trace.addTlsAction(action);
    }

    @Override
    public void setUpPSK(String alias, Variable psk) {
        SetUpPSKAction action = new SetUpPSKAction(alias);
        action.setPSK((List<PskSet>) psk.getValue());
        trace.addTlsAction(action);
    }

}
