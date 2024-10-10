package Parser;

import Scenario.*;
import Scenario.MessageContent.*;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class DefaultScenarioVisitor extends ScenarioBaseVisitor<ScenarioElement> {

    @Override public Scenario visitScenario(ScenarioParser.ScenarioContext ctx) {
        Scenario scenario = new Scenario();
        scenario.setTitle(ctx.title().IDENTIFIER().getText());
        scenario.setNodeNum(Integer.parseInt(ctx.node_num().NUM().getText()));
        scenario.setNodeId(visitNode_entry(ctx.node_entry()));

        for(ScenarioParser.MessageContext msgContext : ctx.message()){
            scenario.addMessage(visitMessage(msgContext));
        }
        return scenario;
    }
    @Override public NodeEntry visitNode_entry(ScenarioParser.Node_entryContext ctx) {
        NodeEntry nodeEntry = new NodeEntry();
        for(ScenarioParser.Node_idContext idContext : ctx.node_id()){
            nodeEntry.addEntry(idContext.IDENTIFIER().getText());
        }
        return nodeEntry;
    }
    @Override public Message visitMessage(ScenarioParser.MessageContext ctx) {
        Message message = new Message();
        message.setNumber(Integer.parseInt(ctx.NUM().getText()));
        message.setTitle(ctx.message_title().IDENTIFIER().getText());
        message.setSender(ctx.sender().getText());
        message.setReceiver(ctx.receiver().getText());

        for(ScenarioParser.Message_contentContext contentContext : ctx.message_content()){
            message.addContent(visitMessage_content(contentContext));
        }
        return message;
    }
    @Override public MessageContent visitMessage_content(ScenarioParser.Message_contentContext ctx) {
        return (MessageContent) visitChildren(ctx);
    }
    @Override public ContentType visitContent_type(ScenarioParser.Content_typeContext ctx) {
        switch(ctx.CONTENT_TYPE().getText()){
            case "handshake": return ContentType.HANDSHAKE;
            case "change-cipher-spec": return ContentType.CHANGE_CIPHER_SPEC;
            case "alert": return ContentType.ALERT;
            case "application-data": return ContentType.APPLICATION_DATA;
            default: throw new IllegalArgumentException("Unknown ContentType: " + ctx.CONTENT_TYPE().getText());
        }
    }
    @Override public ProtocolVersion visitVersion(ScenarioParser.VersionContext ctx) {
        return getProtocolVersion(ctx.TLS_VERSION().getText());
    }
    @Override public MsgSize visitRecord_len(ScenarioParser.Record_lenContext ctx) {
        return getMessageSize(ctx.MSG_SIZE().getText());
    }
    @Override public HandshakeType visitHandshake_type(ScenarioParser.Handshake_typeContext ctx) {
        switch(ctx.HANDSHAKE_TYPE().getText()){
            case "client-hello": return HandshakeType.CLIENT_HELLO;
            case "server-hello": return HandshakeType.SERVER_HELLO;
            case "server-certificate": return HandshakeType.CERTIFICATE;
            case "client-certificate": return HandshakeType.CERTIFICATE;
            case "server-key-exchange": return HandshakeType.SERVER_KEY_EXCHANGE;
            case "client-key-exchange": return HandshakeType.CLIENT_KEY_EXCHANGE;
            case "certificate-request": return HandshakeType.CERTIFICATE_REQUEST;
            case "server-hello-done": return HandshakeType.SERVER_HELLO_DONE;
            case "certificate-verify": return HandshakeType.CERTIFICATE_VERIFY;
            case "client-finished": return HandshakeType.FINISHED;
            case "server-finished": return HandshakeType.FINISHED;
            default: throw new IllegalArgumentException("Unknown HandshakeType: " + ctx.HANDSHAKE_TYPE().getText());
        }
    }
    @Override public MsgSize visitHandshake_len(ScenarioParser.Handshake_lenContext ctx) {
        return getMessageSize(ctx.MSG_SIZE().getText());
    }
    @Override public ProtocolVersion visitProtocol_version(ScenarioParser.Protocol_versionContext ctx) {
        return getProtocolVersion(ctx.TLS_VERSION().getText());
    }
    @Override public CipherSuites visitCiphersuite(ScenarioParser.CiphersuiteContext ctx) {
        CipherSuites cipherSuites = new CipherSuites();
        for(TerminalNode node : ctx.CIPHER_SUITE()){
            switch(node.getText()){
                case "TLS-ECDHE-ECDSA-WITH-AES-128-CCM": cipherSuites.addCipherSuite(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM); break;
                case "TLS-ECDHE-ECDSA-WITH-AES-256-CCM": cipherSuites.addCipherSuite(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM); break;
                case "TLS-DHE-RSA-with-AES-128-CBC-SHA256": cipherSuites.addCipherSuite(CipherSuite.TLS_DHE_RSA_with_AES_128_CBC_SHA256); break;
                default: throw new IllegalArgumentException("Unknown CipherSuite: "+ node.getText());
            }
        }
        return cipherSuites;
    }
    @Override public MsgSize visitCiphersuite_len(ScenarioParser.Ciphersuite_lenContext ctx) {
        return getMessageSize(ctx.MSG_SIZE().getText());
    }
    @Override public Random visitRandom(ScenarioParser.RandomContext ctx) {
        return new Random(visitNonce(ctx.nonce()));
    }
    @Override public Nonce visitNonce(ScenarioParser.NonceContext ctx) {
        if(ctx.NUM() != null && ctx.IDENTIFIER() != null){
            return new Nonce(Integer.parseInt(ctx.NUM().getText()), ctx.IDENTIFIER().getText());
        } else {
            return new Nonce();
        }

    }
    @Override public SessionId visitSession_id(ScenarioParser.Session_idContext ctx) {
        return new SessionId(visitNonce(ctx.nonce()));
    }
    @Override public MsgSize visitSession_id_len(ScenarioParser.Session_id_lenContext ctx) {
        return getMessageSize(ctx.MSG_SIZE().getText());
    }
    @Override public CompressionMethods visitCompression(ScenarioParser.CompressionContext ctx) {
        CompressionMethods compressionMethods = new CompressionMethods();
        for(TerminalNode node : ctx.COMPRESSION_METHOD()){
            compressionMethods.addCompressionMethod(getCompressionMethod(node.getText()));
        }
        return compressionMethods;
    }
    @Override public MsgSize visitCompression_len(ScenarioParser.Compression_lenContext ctx) {
        return getMessageSize(ctx.MSG_SIZE().getText());
    }
    @Override public Extension visitExtension(ScenarioParser.ExtensionContext ctx) {
        Extension extension = new Extension();
        for(ScenarioParser.Message_contentContext content : ctx.message_content()){
            extension.addMessageContent(visitMessage_content(content));
        }
        return extension;
    }
    @Override public SignatureAndHashAlgorithms visitSignature_and_hash_algorithm(ScenarioParser.Signature_and_hash_algorithmContext ctx) {
        List<SignatureAlgorithm> signatureAlgorithm = new ArrayList<>();
        List<HashAlgorithm> hashAlgorithm = new ArrayList<>();

        for(TerminalNode node : ctx.SIGNATURE_ALGO()){
            signatureAlgorithm.add(getSignatureAlgorithm(node.getText()));
        }
        for(TerminalNode node : ctx.HASH_ALGO()){
            hashAlgorithm.add(getHashAlgorithm(node.getText()));
        }
        SignatureAndHashAlgorithms signatureAndHashAlgorithms = new SignatureAndHashAlgorithms();
        for(int i = 0; i < signatureAlgorithm.size(); i++){
            signatureAndHashAlgorithms.add(new SignatureAndHashAlgorithm(signatureAlgorithm.get(i), hashAlgorithm.get(i)));
        }
        return signatureAndHashAlgorithms;
    }
    @Override public NamedCurves visitNamed_curve(ScenarioParser.Named_curveContext ctx) {
        NamedCurves namedCurves = new NamedCurves();
        for(TerminalNode node : ctx.NAMED_CURVE()){
            namedCurves.addNamedCurve(getNamedCurve(node.getText()));
        }
        return namedCurves;
    }
    @Override public Certificates visitCertificate(ScenarioParser.CertificateContext ctx) {
        Certificates certificates = new Certificates();
        for(ScenarioParser.Certificate_contentContext contentContext : ctx.certificate_content()){
            certificates.addCertificate(visitCertificate_content(contentContext));
        }
        return certificates;
    }
    @Override public Certificate visitCertificate_content(ScenarioParser.Certificate_contentContext ctx) {
        Certificate certificate = new Certificate();
        certificate.setSignatureAndHashAlgorithm(new SignatureAndHashAlgorithm(getSignatureAlgorithm(ctx.SIGNATURE_ALGO(0).getText()), getHashAlgorithm(ctx.HASH_ALGO().getText())));
        certificate.setCertificateAuthor(ctx.IDENTIFIER(0).getText());
        certificate.setKey(visitKey(ctx.key()));
        certificate.setSignatureAlgorithm(getSignatureAlgorithm(ctx.SIGNATURE_ALGO(1).getText()));
        certificate.setEncryptedMessage(visitEncrypted_message(ctx.encrypted_message()));
        return certificate;
    }
    @Override public MsgSize visitCertificate_len(ScenarioParser.Certificate_lenContext ctx) {
        return getMessageSize(ctx.MSG_SIZE().getText());
    }
    @Override public EncryptedMessage visitEncrypted_message(ScenarioParser.Encrypted_messageContext ctx) {
        EncryptedMessage encryptedMessage = new EncryptedMessage();
        encryptedMessage.setKey(visitKey(ctx.key()));
        for(ScenarioParser.Message_contentContext content : ctx.message_content()){
            encryptedMessage.addMessage(visitMessage_content(content));
        }
        return encryptedMessage;
    }
    @Override public Key visitKey(ScenarioParser.KeyContext ctx) {
        return (Key) visitChildren(ctx);
    }
    @Override public PublicKey visitPublic_key(ScenarioParser.Public_keyContext ctx) {
        PublicKey publicKey = new PublicKey();
        if(ctx.KEY_EXCHANGE_ALGORITHM() != null){
            publicKey.setAlgorithm(getKeyExchangeAlgorithm(ctx.KEY_EXCHANGE_ALGORITHM().getText()));
        }
        if(ctx.SIGNATURE_ALGO() != null){
            publicKey.setSignatureAlgorithm(getSignatureAlgorithm(ctx.SIGNATURE_ALGO().getText()));
        }
        publicKey.setNonce(visitNonce(ctx.nonce()));
        return publicKey;
    }
    @Override public PrivateKey visitPrivate_key(ScenarioParser.Private_keyContext ctx) {
        PrivateKey privateKey = new PrivateKey();
        if(ctx.KEY_EXCHANGE_ALGORITHM() != null){
            privateKey.setAlgorithm(getKeyExchangeAlgorithm(ctx.KEY_EXCHANGE_ALGORITHM().getText()));
        }
        if(ctx.SIGNATURE_ALGO() != null){
            privateKey.setSignatureAlgorithm(getSignatureAlgorithm(ctx.SIGNATURE_ALGO().getText()));
        }
        privateKey.setNonce(visitNonce(ctx.nonce()));
        return privateKey;
    }
    @Override public SymmetricKey visitSymmetric_key(ScenarioParser.Symmetric_keyContext ctx) {
        SymmetricKey symmetricKey = new SymmetricKey();
        symmetricKey.setEncryptionAlgorithm(getEncryptionAlgorithm(ctx.ENCRYPTION_ALGORITHM().getText()));
        symmetricKey.setMasterSecret(visitMaster_secret(ctx.master_secret()));
        symmetricKey.setClientRandom(visitNonce(ctx.nonce(0)));
        symmetricKey.setServerRandom(visitNonce(ctx.nonce(1)));
        return symmetricKey;
    }
    @Override public MacKey visitMac_key(ScenarioParser.Mac_keyContext ctx) {
        MacKey macKey = new MacKey();
        macKey.setAlgorithm(getHashAlgorithm(ctx.HASH_ALGO().getText()));
        macKey.setMasterSecret(visitMaster_secret(ctx.master_secret()));
        macKey.setClientRandom(visitNonce(ctx.nonce(0)));
        macKey.setServerRandom(visitNonce(ctx.nonce(1)));
        return macKey;
    }
    @Override public Signature visitSignature(ScenarioParser.SignatureContext ctx) {
        return new Signature(visitEncrypted_message(ctx.encrypted_message()));
    }
    @Override public CertifiateTypes visitCertificate_type(ScenarioParser.Certificate_typeContext ctx) {
        CertifiateTypes certificates = new CertifiateTypes();
        for(TerminalNode node : ctx.CERTIFICATE_TYPE()){
            certificates.addCertificateType(getCertificateType(node.getText()));
        }
        return certificates;
    }
    @Override public MsgSize visitCertificate_type_len(ScenarioParser.Certificate_type_lenContext ctx) {
        return getMessageSize(ctx.MSG_SIZE().getText());
    }
    @Override public CertificateAlgos visitCertificate_algo(ScenarioParser.Certificate_algoContext ctx) {
        List<SignatureAlgorithm> signatureAlgorithm = new ArrayList<>();
        List<HashAlgorithm> hashAlgorithm = new ArrayList<>();

        for(TerminalNode node : ctx.SIGNATURE_ALGO()){
            signatureAlgorithm.add(getSignatureAlgorithm(node.getText()));
        }
        for(TerminalNode node : ctx.HASH_ALGO()){
            hashAlgorithm.add(getHashAlgorithm(node.getText()));
        }
        CertificateAlgos certificateAlgos = new CertificateAlgos();
        for(int i = 0; i < signatureAlgorithm.size(); i++){
            certificateAlgos.addSignatureAlgorithm(new SignatureAndHashAlgorithm(signatureAlgorithm.get(i), hashAlgorithm.get(i)));
        }
        return certificateAlgos;
    }
    @Override public MsgSize visitCertificate_algo_len(ScenarioParser.Certificate_algo_lenContext ctx) {
        return getMessageSize(ctx.MSG_SIZE().getText());
    }
    @Override public CertificateAuths visitCertificate_auth(ScenarioParser.Certificate_authContext ctx) {
        CertificateAuths certificateAuths = new CertificateAuths();
        for(TerminalNode node : ctx.IDENTIFIER()){
            certificateAuths.addAuth(node.getText());
        }
        return certificateAuths;
    }
    @Override public MsgSize visitCertificate_auth_len(ScenarioParser.Certificate_auth_lenContext ctx) {
        return getMessageSize(ctx.MSG_SIZE().getText());
    }
    @Override public KeyParam visitKey_param(ScenarioParser.Key_paramContext ctx) {
        return (KeyParam) visitChildren(ctx);
    }
    @Override public DheParam visitDheParam(ScenarioParser.DheParamContext ctx) {
        DheParam dheParam = new DheParam();
        if(ctx.nonce(0) != null && ctx.nonce(1) != null){
            dheParam.setServerNonce1(visitNonce(ctx.nonce(0)));
            dheParam.setServerNonce2(visitNonce(ctx.nonce(1)));
            dheParam.setServerKey(visitKey(ctx.key()));
        }else{
            dheParam.setClientKey(visitKey(ctx.key()));
        }
        return dheParam;
    }
    @Override public EcdheParam visitEcdheParam(ScenarioParser.EcdheParamContext ctx) {
        EcdheParam ecdheParam = new EcdheParam();
        if(ctx.NAMED_CURVE() != null){
            ecdheParam.setNamedCurve(getNamedCurve(ctx.NAMED_CURVE().getText()));
            ecdheParam.setServerKey(visitKey(ctx.key()));
        } else {
            ecdheParam.setClientKey(visitKey(ctx.key()));
        }
        return ecdheParam;
    }
    @Override public RsaParam visitRsaParam(ScenarioParser.RsaParamContext ctx) {
        RsaParam rsaParam = new RsaParam();
        rsaParam.setNonce(visitNonce(ctx.nonce()));
        return rsaParam;
    }
    @Override public Hash visitHash_content(ScenarioParser.Hash_contentContext ctx) {
        return (Hash) visitChildren(ctx);
    }
    @Override public Hash visitHash(ScenarioParser.HashContext ctx) {
        Hash hash = new Hash();
        hash.setHashAlgorithm(getHashAlgorithm(ctx.HASH_ALGO().getText()));
        for(ScenarioParser.Message_contentContext contentContext : ctx.message_content()){
            hash.addMessageContent(visitMessage_content(contentContext));
        }
        return hash;
    }
    @Override public ChangeCipherSpec visitChange_cipher_spec(ScenarioParser.Change_cipher_specContext ctx) {
        return new ChangeCipherSpec();
    }
    @Override public VerifyData visitVerify_data(ScenarioParser.Verify_dataContext ctx) {
        return new VerifyData(visitMaster_secret(ctx.master_secret()), visitHash(ctx.hash()));
    }
    @Override public MasterSecret visitMaster_secret(ScenarioParser.Master_secretContext ctx) {
        return new MasterSecret(visitPre_master_secret(ctx.pre_master_secret()), visitNonce(ctx.nonce(0)), visitNonce(ctx.nonce(1)));
    }
    @Override public PreMasterSecret visitPre_master_secret(ScenarioParser.Pre_master_secretContext ctx) {
        PreMasterSecret preMasterSecret = new PreMasterSecret();
        if(ctx.nonce()!=null){
            preMasterSecret.setNonce(visitNonce(ctx.nonce()));
        }else{
            preMasterSecret.setAlgorithm(getKeyExchangeAlgorithm(ctx.KEY_EXCHANGE_ALGORITHM().getText()));
            preMasterSecret.setKey1(visitKey(ctx.key(0)));
            preMasterSecret.setKey2(visitKey(ctx.key(1)));
        }
        return preMasterSecret;
    }
    @Override public AeadExplicitNonce visitAead_explicit_nonce(ScenarioParser.Aead_explicit_nonceContext ctx) {
        return new AeadExplicitNonce(visitNonce(ctx.nonce()));
    }
    @Override public AeadData visitAead_data(ScenarioParser.Aead_dataContext ctx) {
        return new AeadData(Integer.parseInt(ctx.NUM().getText()), getCompressionMethod(ctx.COMPRESSION_METHOD().getText()));
    }
    @Override public AeadNonce visitAead_nonce(ScenarioParser.Aead_nonceContext ctx) {
        return new AeadNonce(visitNonce(ctx.nonce()), visitIv(ctx.iv()));
    }
    @Override public CipherText visitCipher_text(ScenarioParser.Cipher_textContext ctx) {
        return new CipherText(visitEncrypted_message(ctx.encrypted_message()));
    }
    @Override public InitializationVector visitIv(ScenarioParser.IvContext ctx) {
        return new InitializationVector(visitMaster_secret(ctx.master_secret()), visitNonce(ctx.nonce(0)), visitNonce(ctx.nonce(1)));
    }

    private MsgSize getMessageSize(String size){
        switch(size){
            case "valid": return MsgSize.VALID;
            case "invalid": return MsgSize.INVALID;
            default: throw new IllegalArgumentException("Unknown Message Size: " + size);
        }
    }

    private ProtocolVersion getProtocolVersion(String version){
        switch(version){
            case "TLS-10": return ProtocolVersion.SSLV2;
            case "TLS-11": return ProtocolVersion.TLS11;
            case "TLS-12": return ProtocolVersion.TLS12;
            case "TLS-13": return ProtocolVersion.TLS13;
            default: throw new IllegalArgumentException("Unknown TLS version: " + version);
        }
    }

    private KeyExchangeAlgorithm getKeyExchangeAlgorithm(String algorithm){
        switch(algorithm){
            case "dh": return KeyExchangeAlgorithm.DH;
            case "dhe": return KeyExchangeAlgorithm.DHE;
            case "ecdh": return KeyExchangeAlgorithm.ECDH;
            case "ecdhe": return KeyExchangeAlgorithm.ECDH;
            default: throw new IllegalArgumentException("Unknown Key Exchange Algorithm: " + algorithm);
        }
    }

    private SignatureAlgorithm getSignatureAlgorithm(String algorithm){
        switch(algorithm){
            case "anonAuth": return SignatureAlgorithm.ANON;
            case "rsaAuth": return SignatureAlgorithm.RSA;
            case "ecdsaAuth": return SignatureAlgorithm.ECDSA;
            default: throw new IllegalArgumentException("Unknown SignatureAlgorithm: "+ algorithm);
        }
    }

    private HashAlgorithm getHashAlgorithm(String algorithm){
        switch(algorithm){
            case "sha1": return HashAlgorithm.SHA1;
            case "sha224": return HashAlgorithm.SHA224;
            case "sha256": return HashAlgorithm.SHA256;
            case "sha384": return HashAlgorithm.SHA384;
            case "sha512": return HashAlgorithm.SHA512;
            default: throw new IllegalArgumentException("Unknown HashAlgorithm: "+ algorithm);
        }
    }

    private CertificateType getCertificateType(String algorithm){
        switch(algorithm){
            case "ecdsa-sign": return CertificateType.ECDSA_SIGN;
            case "rsa-sign": return CertificateType.RSA_SIGN;
            default: throw new IllegalArgumentException("Unknown Certificate Type: " + algorithm);
        }
    }

    private NamedCurve getNamedCurve(String namedCurve){
        switch(namedCurve){
            case "secp256r1": return NamedCurve.SECP256R1;
            case "secp256k1": return NamedCurve.SECP256K1;
            case "secp384r1": return NamedCurve.SECP384R1;
            case "secp384k1": return NamedCurve.SECP384K1;
            default: throw new IllegalArgumentException("Unknown NamedCurve: "+ namedCurve);
        }
    }

    private CompressionMethod getCompressionMethod(String method){
        switch(method){
            case "zlib-compression": return CompressionMethod.LZS;
            case "no-compression": return CompressionMethod.NO_COMPRESSION;
            default: throw new IllegalArgumentException("Unknown CompressionMethod: " + method);
        }
    }

    private EncryptionAlgorithm getEncryptionAlgorithm(String algorithm){
        switch(algorithm){
            case "aes128": return EncryptionAlgorithm.AES128;
            case "aes256": return EncryptionAlgorithm.AES256;
            case "3des": return EncryptionAlgorithm.DES3;
            default: throw new IllegalArgumentException("Unknown EncryptionAlgorithm: " + algorithm);
        }
    }
}
