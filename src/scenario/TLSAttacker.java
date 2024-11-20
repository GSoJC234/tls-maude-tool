package scenario;

import de.rub.nds.tlsattacker.core.constants.*;
import protocol.TLSProtocol;
import protocol.Variable;
import de.rub.nds.tlsattacker.core.config.Config;
import de.rub.nds.tlsattacker.core.connection.AliasedConnection;
import de.rub.nds.tlsattacker.core.connection.InboundConnection;
import de.rub.nds.tlsattacker.core.connection.OutboundConnection;
import de.rub.nds.tlsattacker.core.constants.AlertLevel;
import de.rub.nds.tlsattacker.core.constants.CipherSuite;
import de.rub.nds.tlsattacker.core.constants.CompressionMethod;
import de.rub.nds.tlsattacker.core.constants.ProtocolVersion;
import de.rub.nds.tlsattacker.core.protocol.ProtocolMessage;
import de.rub.nds.tlsattacker.core.protocol.message.*;
import de.rub.nds.tlsattacker.core.protocol.message.cert.CertificateEntry;
import de.rub.nds.tlsattacker.core.record.Record;
import de.rub.nds.tlsattacker.core.state.State;
import de.rub.nds.tlsattacker.core.util.ProviderUtil;
import de.rub.nds.tlsattacker.core.workflow.DefaultWorkflowExecutor;
import de.rub.nds.tlsattacker.core.workflow.WorkflowTrace;
import de.rub.nds.tlsattacker.core.workflow.action.*;
import de.rub.nds.x509attacker.chooser.X509Chooser;
import de.rub.nds.x509attacker.context.X509Context;
import de.rub.nds.x509attacker.x509.model.X509Certificate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.util.io.pem.PemReader;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class TLSAttacker extends TLSProtocol {

    private Config config = null;
    private WorkflowTrace trace = null;
    private State state = null;
    private DefaultWorkflowExecutor executor = null;
    private List<AliasedConnection> aliasedConnections = null;
    private SecureRandom secureRandom;
    private boolean isClient, isServer;

    private static Logger LOGGER = LogManager.getLogger();

    public TLSAttacker(String configPath){
        ProviderUtil.addBouncyCastleProvider();
        aliasedConnections = new ArrayList<>();
        config = Config.createConfig(new File(configPath));
        trace = new WorkflowTrace(aliasedConnections);
    }


    @Override
    public void connect(String alias, int port, String ip) {
        OutboundConnection connection = new OutboundConnection(alias, port, ip);
        connection.setConnectionTimeout(10000);
        aliasedConnections.add(connection);
        isServer = true;
    }

    @Override
    public void accept(String alias, int port, String ip){
        InboundConnection connection = new InboundConnection(alias, port, ip);
        connection.setConnectionTimeout(10000);
        aliasedConnections.add(connection);
        isClient = true;
    }

    @Override
    public void close(String alias) {

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
    public void execute(){
        if(isServer && isClient){
            config.setDefaultRunningMode(RunningModeType.MITM);
        }else if(isServer){
            config.setDefaultRunningMode(RunningModeType.SERVER);
        }else if(isClient){
            config.setDefaultRunningMode(RunningModeType.CLIENT);
        }
        state = new State(config, trace);
        executor = new DefaultWorkflowExecutor(state);
        executor.executeWorkflow();
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
    public Variable constant(maude.CipherSuite cipher) {
        List<CipherSuite> container = new ArrayList<>();
        container.add(cipher.transform());
        return new ConstantVariable<CipherSuite>(container);
    }

    @Override
    public Variable constant(maude.CompressionMethod compression) {
        List<CompressionMethod> container = new ArrayList<>();
        container.add(compression.transform());
        return new ConstantVariable<CompressionMethod>(container);
    }

    @Override
    public Variable constant(maude.AlertLevel level) {
        List<AlertLevel> container = new ArrayList<>();
        container.add(level.transform());
        return new ConstantVariable<AlertLevel>(container);
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
        byte[] randomByte = new byte[32];
        try {
            this.secureRandom = SecureRandom.getInstanceStrong();
            this.secureRandom.nextBytes(randomByte);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Secure random generator algorithm is not found");
        }
        container.add(randomByte);
        return new ConstantVariable<byte[]>(container);
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
        return new FieldVariable<ProtocolMessageType>(container);
    }

    @Override
    public Variable getRecordVersion(Variable msg) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<ProtocolVersion> container = new ArrayList<>();
        FieldAction action = new FieldAction<ProtocolVersion>(container,
                () -> {
                    if(!((MessageVariable) msg).getRecordMessages().isEmpty()){
                        Record record = ((MessageVariable) msg).getRecordMessages().get(0);
                        return de.rub.nds.tlsattacker.core.constants.ProtocolVersion.getProtocolVersion(record.getProtocolVersion().getValue());
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new FieldVariable<ProtocolVersion>(container);
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
        return new FieldVariable<HandshakeMessageType>(container);
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
                            return de.rub.nds.tlsattacker.core.constants.ProtocolVersion.getProtocolVersion(((ClientHelloMessage) message).getProtocolVersion().getValue());
                        }
                        else if (message instanceof ServerHelloMessage){
                            return de.rub.nds.tlsattacker.core.constants.ProtocolVersion.getProtocolVersion(((ServerHelloMessage) message).getProtocolVersion().getValue());
                        }
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new FieldVariable<ProtocolVersion>(container);
    }

    @Override
    public Variable getCipherSuite(Variable msg) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<de.rub.nds.tlsattacker.core.constants.CipherSuite> container = new ArrayList<>();
        FieldAction action = new FieldAction<CipherSuite>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        ProtocolMessage message = ((MessageVariable) msg).getProtocolMessages().get(0);
                        if(message instanceof ClientHelloMessage){
                            ClientHelloMessage clientHelloMessage = (ClientHelloMessage) message;
                            return CipherSuite.getCipherSuite(clientHelloMessage.getCipherSuites().getValue());
                        } else if (message instanceof ServerHelloMessage){
                            ServerHelloMessage serverHelloMessage = (ServerHelloMessage) message;
                            return CipherSuite.getCipherSuite(serverHelloMessage.getSelectedCipherSuite().getValue());
                        }
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new FieldVariable<CipherSuite>(container);
    }

    @Override
    public Variable getCompressionMethod(Variable msg) {
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
        return new FieldVariable<CompressionMethod>(container);
    }

    @Override
    public Variable getAlertLevel(Variable msg) {
        MessageVariable messageVariable = (MessageVariable) msg;
        List<AlertLevel> container = new ArrayList<>();
        FieldAction action = new FieldAction<AlertLevel>(container,
                () -> {
                    if(!((MessageVariable) msg).getProtocolMessages().isEmpty()){
                        AlertMessage message = (AlertMessage) ((MessageVariable) msg).getProtocolMessages().get(0);
                        return AlertLevel.getAlertLevel(message.getLevel().getValue());
                    }
                    return null;
                });
        trace.addTlsAction(action);
        return new FieldVariable<AlertLevel>(container);
    }

    @Override
    public Variable buildMessage(Variable record, Variable protocol_message){
        return new MessageVariable(
                (List<Record>) record.getValue(),
                (List<ProtocolMessage>) protocol_message.getValue()
        );
    }

    @Override
    public Variable buildRecord(Variable content_type, Variable record_version, Variable record_length, Variable message) {
        List<Record> container = new ArrayList<>();

        BuildRecordAction action = new BuildRecordAction(container);
        action.setProtocolMessageType((List<ProtocolMessageType>) content_type.getValue());
        action.setProtocolVersion((List<ProtocolVersion>) record_version.getValue());
        action.setLength((List<Boolean>) record_length.getValue());
        action.setProtocolMessage((List<ProtocolMessage>) message.getValue());

        trace.addTlsAction(action);

        return new RecordVariable(container);
    }

    @Override
    public Variable buildServerHello(Variable handshake_type, Variable handshake_length, Variable version, Variable suite, Variable random, Variable sessionId, Variable sessionId_length, Variable compression) {
        List<ProtocolMessage> container = new ArrayList<>();

        BuildServerHelloAction action = new BuildServerHelloAction(container);
        action.setHandshakeMessageType((List<HandshakeMessageType>) handshake_type.getValue());
        action.setMessageLength((List<Boolean>) handshake_length.getValue());
        action.setVersion((List<ProtocolVersion>) version.getValue());
        action.setCipherSuite((List<CipherSuite>) suite.getValue());
        action.setRandom((List<byte[]>) random.getValue());
        action.setSessionId((List<byte[]>) sessionId.getValue());
        action.setSessionIdLength((List<Boolean>) sessionId_length.getValue());
        action.setCompression((List<CompressionMethod>) compression.getValue());

        trace.addTlsAction(action);

        return new ProtocolMessageVariable(container);
    }

    @Override
    public Variable buildCertificate(Variable handshake_type, Variable handshake_length, Variable certificate) {
        List<ProtocolMessage> container = new ArrayList<>();

        BuildCertificateAction action = new BuildCertificateAction(container);
        action.setHandshakeMessageType((List<HandshakeMessageType>) handshake_type.getValue());
        action.setMessageLength((List<Boolean>) handshake_length.getValue());
        action.setCertificate((List<CertificateEntry>) certificate.getValue());

        trace.addTlsAction(action);

        return new ProtocolMessageVariable(container);
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
}
