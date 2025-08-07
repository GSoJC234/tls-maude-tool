package protocol;


import maude.*;

public interface Protocol {
    void accept(String alias, String ip, int port);
    void connect(String alias, String ip, int port);
    void close();
    Variable recv(String alias);
    void send(String alias, Variable msg);
    void checkConnection(String alias);
    Variable encrypt(String alias, Variable message);
    Variable encrypt(Variable message, Variable key);
    Variable decrypt(String alias, Variable message);
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

    Variable genCertificate(String path);
    Variable genCertificatePrivateKey(String privateKeyPath);

    Variable getContentType(Variable msg);
    Variable getRecordVersion(Variable msg);
    Variable getProtocolVersion(Variable msg);
    Variable getHandshakeMessageType(Variable msg);
    Variable getCipherSuite(Variable msg, int idx);
    Variable getRandom(Variable msg);
    Variable getSessionId(Variable msg);
    Variable getCompressionMethod(Variable msg, int idx);
    Variable getAlertLevel(Variable msg);
    Variable getAlertDescription(Variable msg);
    Variable getSupportedVersion(Variable msg, int idx);
    Variable getSignatureAndHashAlgorithm(Variable msg, int idx);
    Variable getSignatureAlgorithm(Variable msg, int idx);
    Variable getNamedGroup(Variable msg, int idx);
    Variable getKeyShareEntry(Variable msg, int idx);
    Variable getPublicKeyFromKeyShare(Variable msg);
    Variable getKeyShareNamedGroup(Variable keyShares);
    Variable getHandshakeBody(Variable msg);
    Variable getCertificate(Variable msg);
    Variable getPublicKeyFromCertificate(Variable msg);
    Variable getRSAPreMasterSecret(String alias, Variable msg, Variable privateKey);
    Variable calculateMasterSecret(String clientAlias, String serverAlias, Variable preMasterSecret, Variable clientRandom, Variable serverRandom);

    Variable buildRecord(String alias, Variable content_type, Variable record_version, Variable message);
    Variable buildClientHello(String alias, Variable versions, Variable ciphers, Variable random, Variable sessionId, Variable methods);
    Variable buildServerHello(String alias, Variable version, Variable suite, Variable random, Variable sessionId, Variable compression);
    Variable buildCertificate(String alias, Variable certificate);
    Variable buildCertificate(String alias);
    Variable buildEncryptedExtension(String alias);
    Variable buildCertificateRequest(String alias, Variable certificate_context);
    Variable buildCertificateVerify(String alias, Variable signatureHashAlgorithm, Variable certificate_private_key);
    Variable buildChangeCipher(String alias);
    Variable buildFinished(String alias);
    Variable buildAlert(String alias, Variable level, Variable description);

    void addSupportedVersionExtension(String alias, Variable handshake_message, Variable supported_versions);
    void addSignatureAndHashAlgorithmExtension(String alias, Variable handshake_message, Variable algorithms);
    void addSupportedGroupExtension(String alias, Variable handshake_message, Variable supported_groups);
    void addKeyShareExtension(String alias, Variable handshake_message, Variable named_group, Variable nonce);

    Variable buildKeyShareEntry(Variable group);
    Variable buildKeyShareEntry(Variable group, Variable privateKey);
    Variable calculateMessageDigest(Variable... messages);
    Variable buildInvalidPaddingRSAClientKeyExchange(Variable publicKey, Variable serverRandom, Variable clientRandom, Variable nonce, String alias);

    Variable changeCertificate(Variable variable, Variable after_certificate, String alias);
    Variable changeVerifyData(Variable message, Variable masterSecret, String alias);
    Variable reEncryptRSAClientKeyExchange(Variable msg, Variable decrypt_key, Variable encrypt_key, String alias);


    void setRandomPrivateKey(String group);
    void setCertificateEcPrivateKey(String keyPath, String namedCurve);

    void updateContext(String alias, Variable msg, boolean isSent);


}

