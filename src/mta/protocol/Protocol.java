package mta.protocol;


import mta.maude.constant.*;

public interface Protocol {
    void accept(String alias, String ip, int port);
    void connect(String alias, String ip, int port);
    void close(String alias);
    Variable recv(String alias);
    void send(String alias, Variable msg);
    void echoApplicationData(String alias);
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
    Variable constant(CompressionMethod... methods);
    Variable constant(AlertLevel level);
    Variable constant(CurveType curveType);
    Variable constant(MessageSize size);
    Variable constant(NamedGroup... groups);
    Variable constant(SignatureAlgorithm... signatureAlgorithms);
    Variable constant(AlertDescription description);
    Variable constant(SupportedVersion... supportedVersions);
    Variable constant(PskKeyExchangeMode... pskKeyExchangeModes);
    Variable constant(CertificateType... certificateType);

    Variable longConstant(CipherSuite... cipherSuites);
    Variable longConstant(ProtocolVersion... versions);
    Variable longConstant(NamedGroup... groups);
    Variable longConstant(SignatureAlgorithm... signatureAlgorithms);

    Variable getCertificate(String path);
    Variable genCertificatePrivateKey(String privateKeyPath);
    Variable generateRandom(Random random);
    Variable generateTicket(Variable random);
    Variable generatePSK(String alias, Variable ticket);
    Variable generateEmptyCertificate();

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
    Variable getAuthenticationAlgorithm(Variable msg, int idx);
    Variable getNamedGroup(Variable msg, int idx);
    Variable getKeyShareEntry(Variable msg, int idx);
    Variable getPskExchangeMode(Variable msg, int idx);
    Variable getPublicKeyFromKeyShare(Variable msg);
    Variable getKeyShareNamedGroup(Variable keyShares);
    Variable getHandshakeBody(Variable msg);
    Variable getCertificate(Variable msg);
    Variable getPublicKeyFromCertificate(Variable msg);
    Variable getRSAPreMasterSecret(String alias, Variable msg, Variable privateKey);
    Variable getTicket(Variable msg);
    Variable getCertificateContext(Variable msg);


    Variable calculateMasterSecret(String clientAlias, String serverAlias, Variable preMasterSecret, Variable clientRandom, Variable serverRandom);


    Variable buildRecord(String alias, Variable content_type, Variable record_version, Variable record_len, Variable message);
    Variable buildClientHello(String alias, Variable handshake_type, Variable versions, Variable ciphers_len, Variable ciphers, Variable random, Variable session_len, Variable sessionId, Variable compression_len, Variable methods);
    Variable buildServerHello(String alias, Variable handshake_type, Variable version, Variable suite, Variable random, Variable session_len, Variable sessionId, Variable compression);
    Variable buildECDHEServerKeyExchange(String alias, Variable handshake_type, Variable curve_type, Variable named_curve, Variable ec_private, Variable signature_key);
    Variable buildServerHelloDone(String alias, Variable handshake_type);
    Variable buildECDHClientKeyExchange(String alias, Variable handshake_type, Variable ec_private);


    Variable buildCertificate(String alias, Variable handshake_type, Variable certificate_len, Variable certificate);
    Variable buildCertificate(String alias, Variable handshake_type, Variable certificate_len, Variable certificate, Variable certificate_request_context_len, Variable certificate_request_context);
    Variable buildEncryptedExtension(String alias);
    Variable buildEncryptedExtension(String alias, Variable handshake_type);
    Variable buildCertificateRequest(String alias, Variable handshake_type, Variable certificate_type, Variable certificate_type_length, Variable certificate_algo, Variable certificate_algo_length);
    Variable buildCertificateRequest(String alias, Variable handshake_type, Variable certificate_context_len, Variable certificate_context);
    Variable buildCertificateVerify(String alias, Variable handshake_type, Variable certificate_private_key);
    Variable buildCertificateVerify(String alias, Variable handshake_type, Variable signatureHashAlgorithm, Variable certificate_private_key);
    Variable buildCertificateVerify(String alias, Variable handshake_type, Variable wireSignatureHashAlgorithm, Variable signingSignatureHashAlgorithm, Variable certificate_private_key);
    Variable buildChangeCipherSpec(String alias);
    Variable buildApplicationData(String alias, Variable payload);
    Variable buildFinished(String alias, Variable handshake_type);
    Variable buildFinished(String alias, Variable handshake_type, Variable verify_data);
    Variable buildAlert(String alias, Variable level, Variable description);
    Variable buildNewSessionTicket(String alias, Variable ticket);

    void addSupportedSignatureAlgorithmExtension(String alias, Variable handshake_message, Variable supported_signature_algorithms);
    void addNamedCurvesExtension(String alias, Variable handshake_message, Variable named_curves);
    void addSupportedVersionExtension(String alias, Variable extension_len, Variable handshake_message, Variable supported_versions);
    void addSignatureAlgorithmExtension(String alias, Variable extension_len, Variable handshake_message, Variable algorithms);
    void addSignatureAlgorithmCertExtension(String alias, Variable extension_len, Variable handshake_message, Variable algorithms);
    void addSupportedGroupExtension(String alias, Variable extension_len, Variable handshake_message, Variable supported_groups);
    void addPSKExchangeModeExtension(String alias, Variable extension_len, Variable handshake_message, Variable psk_exchange_modes);
    void addCHPreSharedKeyExtension(String alias, Variable extension_len, Variable handshake_message, Variable ticket);
    void setCHPreSharedKeyBinder(String alias, Variable handshake_message, Variable binder);
    void addSHPreSharedKeyExtension(String alias, Variable extension_len, Variable handshake_message, Variable ticket);
    void addPostHandshakeAuthExtension(String alias, Variable extension_len, Variable handshake_message);
    void addRenegotiationInfoExtension(String alias, Variable extension_len, Variable handshake_message);
    void addCKSExtension(String alias, Variable extension_len, Variable handshake_message);


    Variable buildEmptyKeyShareEntryList();
    void addKeyShareEntry(String alias, Variable entry_list, Variable nonce, Variable named_group);
    void addEarlyDataExtension(String alias, Variable extension_len, Variable handshake_message);
    void addPostHandshakeExtension(String alias, Variable extension_len, Variable handshake_message);
    void addKeyShareExtension(String alias, Variable extension_len, Variable handshake_message, Variable key_share_entry_list);
    void addHRRKeyShareExtension(String alias, Variable extension_len, Variable handshake_message, Variable named_group);
    void addExtensionLen(String alias, Variable extension_len, Variable handshake_message);
    void addHandshakeLen(String alias, Variable handshake_len, Variable handshake_message);


    Variable calculateMessageDigest(Variable... messages);
    Variable buildInvalidPaddingRSAClientKeyExchange(Variable publicKey, Variable serverRandom, Variable clientRandom, Variable nonce, String alias);

    Variable changeCertificate(Variable variable, Variable after_certificate, String alias);
    Variable changeVerifyData(Variable message, Variable masterSecret, String alias);
    Variable reEncryptRSAClientKeyExchange(Variable msg, Variable decrypt_key, Variable encrypt_key, String alias);
    Variable generateVerifyData(String alias);

    void setRandomPrivateKey(String group);
    void setCertificateEcPrivateKey(String keyPath, String namedCurve);

    void updateContext(String alias, Variable msg, boolean isSent);
    void setUpPSK(String alias, Variable psk);

}
