package protocol;


import maude.*;

public interface Protocol {
    void accept(String alias, String ip, int port);
    void connect(String alias, String ip, int port);
    void close();
    Variable recv();
    Variable recv(String alias);
    void send(Variable msg);
    void send(String alias, Variable msg);
    Variable encrypt(Variable message);
    Variable encrypt(Variable message, Variable key);
    Variable decrypt(Variable message);
    Variable decrypt(Variable message, Variable key);
    void assertEqual(Variable var1, Variable var2);

    Variable constant(ProtocolMessageType msgType);
    Variable constant(ProtocolVersion version);
    Variable constant(HandshakeMessageType msgType);
    Variable constant(CipherSuite... ciphers);
    Variable constant(maude.CompressionMethod... methods);
    Variable constant(AlertLevel level);
    Variable constant(MessageSize size);
    Variable constant(Random random);
    Variable constant(NamedGroup group);
    Variable constant(SignatureAndHashAlgorithm signatureAndHashAlgorithm);
    Variable constant(AlertDescription description);
    Variable constant(SupportedVersion... supportedVersions);

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
    Variable buildRecord(Variable content_type, Variable record_version, Variable message);
    Variable buildServerHello(Variable version, Variable suite, Variable random, Variable sessionId, Variable compression);
    Variable buildClientHello(Variable versions, Variable ciphers, Variable random, Variable sessionId, Variable methods);

    Variable buildCertificate(Variable certificate);
    Variable buildEmptyCertificate();
    Variable buildEncryptedExtension();
    Variable buildCertificateRequest(Variable certificate_context);
    Variable buildCertificateVerify(Variable signatureHashAlgorithm);
    Variable buildFinished();

    Variable buildKeyShareEntry(Variable group);
    Variable buildKeyShareEntry(Variable group, Variable privateKey);
    Variable calculateMessageDigest(Variable... messages);

    Variable changeCertificate(Variable variable, Variable before_certificate, Variable after_certificate);
    void addSupportedVersionExtension(Variable handshake_message, Variable supported_versions);
    void addSignatureAndHashAlgorithmExtension(Variable handshake_message, Variable algorithms);
    void addSupportedGroupExtension(Variable handshake_message, Variable supported_groups);
    void addKeyShareExtension(Variable handshake_message, Variable key_shares);

    void setRandomPrivateKey(String group);
    void setCertificateEcPrivateKey(String keyPath, String namedCurve);

    void updateDigest(Variable msg);


}

