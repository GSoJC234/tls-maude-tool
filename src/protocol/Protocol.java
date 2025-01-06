package protocol;


import maude.*;

public interface Protocol {
    void accept(int port, String ip);
    void connect(int port, String ip);
    void close();
    Variable recv();
    void send(Variable msg);
    Variable encrypt(Variable message);
    Variable encrypt(Variable message, Variable key);
    Variable decrypt(Variable message);
    Variable decrypt(Variable message, Variable key);
    void assertEqual(Variable var1, Variable var2);

    Variable constant(ProtocolMessageType msgType);
    Variable constant(ProtocolVersion version);
    Variable constant(HandshakeMessageType msgType);
    Variable constant(CipherSuite cipher);
    Variable constant(CompressionMethod compression);
    Variable constant(AlertLevel level);
    Variable constant(MessageSize size);
    Variable constant(Random random);
    Variable constant(NamedGroup group);
    Variable constant(SignatureAndHashAlgorithm signatureAndHashAlgorithm);
    Variable constant(AlertDescription description);
    Variable constant(SupportedVersion supportedVersion);

    Variable makeCertificate(String path);

    Variable getContentType(Variable msg);
    Variable getRecordVersion(Variable msg);
    Variable getProtocolVersion(Variable msg);
    Variable getHandshakeMessageType(Variable msg);
    Variable getCipherSuite(Variable msg);
    Variable getCompressionMethod(Variable msg);
    Variable getAlertLevel(Variable msg);
    Variable getAlertDescription(Variable msg);
    Variable getSupportedVersion(Variable msg);
    Variable getSignatureAndHashAlgorithm(Variable msg);
    Variable getNamedGroup(Variable msg);
    Variable getKeyShareEntries(Variable msg);
    Variable getPublicKeyFromKeyShare(Variable msg);
    Variable getNamedGroupFromKeyShares(Variable keyShares);

    Variable buildMessage(Variable record, Variable protocol_message);
    Variable buildRecord(Variable content_type, Variable record_version, Variable record_length, Variable message);
    Variable buildServerHello(Variable handshake_type, Variable handshake_length,
                              Variable version, Variable suite, Variable random, Variable sessionId, Variable sessionId_length, Variable method, Variable... extensions);


    Variable buildCertificate(Variable handshake_type, Variable handshake_length, Variable certificate);
    Variable buildEncryptedExtension(Variable handshake_type, Variable handshake_leng, Variable... extensions);
    Variable buildCertificateRequest(Variable handshake_type, Variable handshake_length, Variable certificate_context, Variable certificate_context_len, Variable... extensions);
    Variable buildCertificateVerify(Variable handshake_type, Variable handshake_length, Variable signatureHashAlgorithm);
    Variable buildFinished(Variable handshake_type, Variable handshake_length);

    Variable buildKeyShareEntry(Variable group);
    Variable calculateMessageDigest(Variable... messages);

    Variable changeCertificate(Variable variable, Variable before_certificate, Variable after_certificate);

    void setRandomPrivateKey(String group);
    void setCertificateEcPrivateKey(String keyPath, String namedCurve);
}

