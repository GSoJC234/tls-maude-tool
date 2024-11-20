package protocol;


import maude.*;

public interface Protocol {
    void accept(String alias, int port, String ip);
    void connect(String alias, int port, String ip);
    void close(String alias);
    Variable recv(String alias);
    void send(String alias, Variable msg);
    void execute();
    void assertEqual(Variable var1, Variable var2);

    Variable constant(ProtocolMessageType msgType);
    Variable constant(ProtocolVersion version);
    Variable constant(HandshakeMessageType msgType);
    Variable constant(CipherSuite cipher);
    Variable constant(CompressionMethod compression);
    Variable constant(AlertLevel level);
    Variable constant(MessageSize size);
    Variable constant(Random random);

    Variable makeCertificate(String path);

    Variable getContentType(Variable msg);
    Variable getRecordVersion(Variable msg);
    Variable getProtocolVersion(Variable msg);
    Variable getHandshakeMessageType(Variable msg);
    Variable getCipherSuite(Variable msg);
    Variable getCompressionMethod(Variable msg);
    Variable getAlertLevel(Variable msg);

    Variable buildMessage(Variable record, Variable protocol_message);
    Variable buildRecord(Variable content_type, Variable record_version, Variable record_length, Variable message);
    Variable buildServerHello(Variable handshake_type, Variable handshake_length,
                              Variable version, Variable suite, Variable random, Variable sessionId, Variable sessionId_length, Variable method);
    Variable buildCertificate(Variable handshake_type, Variable handshake_length, Variable certificate);
    Variable changeCertificate(Variable variable, Variable before_certificate, Variable after_certificate);
}
