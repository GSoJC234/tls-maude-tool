import Maude.*;
import de.rub.nds.tlsattacker.core.protocol.ProtocolMessage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClientTest {
    private MaudeTLS md;
    @Before
    public void initialize(){
        md = new MaudeTLS("/home/jaehun/maude-tls-attacker/resources/default_config2.xml");
        md.setAlias(Alias.CLIENT);
        md.setCertificate("/home/jaehun/maude-tls-attacker/resources/ca-cert.pem");
        md.setConnection("localhost", 4433);
    }

    @Test
    public void test_handshake_success(){
        // Send ClientHello
        ProtocolMessage v0 = md.genClientHelloMessage(ProtocolVersion.TLS12,
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM, CompressionMethod.NO_COMPRESSION,
                Random.NONCE, SessionId.EMPTY);

        md.addHelloMessageExtension(v0, Maude.NamedGroup.SECP256R1);
        md.addHelloMessageExtension(v0, Maude.ECPointFormat.UNCOMPRESSED);
        md.addHelloMessageExtension(v0, Maude.SignatureAndHashAlgorithm.ECDSA_SHA256);

        md.send(v0);

        // Receive ServerHello
        ProtocolMessage v1 = md.recv();
        Assert.assertEquals(MessageType.HANDSHAKE, md.getMessageType(v1));
        Assert.assertEquals(HandshakeMessageType.SERVER_HELLO, md.getHandshakeMessageType(v1));
        Assert.assertEquals(ProtocolVersion.TLS12, md.getProtocolVersion(v1));
        Assert.assertEquals(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM, md.getSelectedCipherSuite(v1));
        Assert.assertEquals(CompressionMethod.NULL, md.getSelectedCompressionMethod(v1));
        Assert.assertTrue(md.hasRandom(v1));
        Assert.assertTrue(md.hasSessionId(v1));

        // Receive Server Certificate
        ProtocolMessage v2 = md.recv();
        Assert.assertEquals(MessageType.HANDSHAKE, md.getMessageType(v2));
        Assert.assertEquals(HandshakeMessageType.CERTIFICATE, md.getHandshakeMessageType(v2));
        //Assert.assertTrue(md.hasPeerPublicKey(v2));

        // Receive ServerKeyExchange
        ProtocolMessage v3 = md.recv();
        Assert.assertEquals(MessageType.HANDSHAKE, md.getMessageType(v3));
        Assert.assertEquals(HandshakeMessageType.SERVER_KEY_EXCHANGE, md.getHandshakeMessageType(v3));
        Assert.assertTrue(md.hasServerKey(v3));
        Assert.assertTrue(md.verifySignature(v3));

        // Receive Certificate Request
        ProtocolMessage v4 = md.recv();
        Assert.assertEquals(MessageType.HANDSHAKE, md.getMessageType(v4));
        Assert.assertEquals(HandshakeMessageType.CERTIFICATE_REQUEST, md.getHandshakeMessageType(v4));

        // Receive ServerHelloDone
        ProtocolMessage v5 = md.recv();
        Assert.assertEquals(MessageType.HANDSHAKE, md.getMessageType(v5));
        Assert.assertEquals(HandshakeMessageType.SERVER_HELLO_DONE, md.getHandshakeMessageType(v5));

        // Send ClientKeyExchange
        ProtocolMessage v6 = md.genClientKeyExchange();
        Assert.assertNotNull(v6);
        md.send(v6);

        // Send ChangeCipherSpec
        ProtocolMessage v7 = md.genChangeCipherSpec();
        Assert.assertNotNull(v7);
        md.send(v7);

        // Send Finished
        ProtocolMessage v8 = md.genFinished();
        Assert.assertNotNull(v8);
        md.send(v8);

        // Receive ChangeCipherSpec
        ProtocolMessage v9 = md.recv();
        Assert.assertEquals(MessageType.CHANGE_CIPHER_SPEC, md.getMessageType(v9));

        // Receive Finished
        ProtocolMessage v10 = md.recv();
        Assert.assertEquals(MessageType.HANDSHAKE, md.getMessageType(v10));
        Assert.assertEquals(HandshakeMessageType.FINISHED, md.getHandshakeMessageType(v10));
        Assert.assertTrue(md.verifyData(v10));
    }

    @Test
    public void test_compression_method(){
        // compression_method MUST contain and all implementations MUST support,
        // CompressionMethod.null. Thus a client and server will always be able to
        // agree on a compression method

        // Send ClientHello
        ProtocolMessage v0 = md.genClientHelloMessage(ProtocolVersion.TLS12,
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM, CompressionMethod.NO_COMPRESSION,
                Random.NONCE, SessionId.EMPTY);
        md.send(v0);

        // Receive ServerHello
        ProtocolMessage v1 = md.recv();
        Assert.assertEquals(MessageType.HANDSHAKE, md.getMessageType(v1));
        Assert.assertEquals(HandshakeMessageType.SERVER_HELLO, md.getHandshakeMessageType(v1));
        Assert.assertEquals(CompressionMethod.NULL, md.getSelectedCompressionMethod(v1));
    }

    @Test
    public void test_clienthello_without_extension(){
        // A server MUST accept ClientHello messages both with and without the extensions field
        // If the client does not send the signature_algorithms extension, the
        // server MUST do the following: If the negotiated key exchange algorithm is one of (ECDH_ECDSA,
        // ECDHE_ECDSA), behave as if the client had sent value {sha1,ecdsa}.

        // Send ClientHello
        ProtocolMessage v0 = md.genClientHelloMessage(ProtocolVersion.TLS12,
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM, CompressionMethod.NO_COMPRESSION,
                Random.NONCE, SessionId.EMPTY);
        md.send(v0);

        // Receive ServerHello
        ProtocolMessage v1 = md.recv();
        Assert.assertEquals(MessageType.HANDSHAKE, md.getMessageType(v1));
        Assert.assertEquals(HandshakeMessageType.SERVER_HELLO, md.getHandshakeMessageType(v1));
        Assert.assertEquals(CompressionMethod.NULL, md.getSelectedCompressionMethod(v1));

        // Receive ServerCertificate
        ProtocolMessage v2 = md.recv();
        Assert.assertEquals(MessageType.HANDSHAKE, md.getMessageType(v2));
        Assert.assertEquals(HandshakeMessageType.CERTIFICATE, md.getHandshakeMessageType(v2));
        //Assert.assertEquals(SignatureAndHashAlgorithm.ECDSA_SHA1, md.getSignatureAndHashAlgorithm(v2));
    }

    @Test
    public void test_duplicate_extension_type1(){
        // There MUST NOT be more than one extension of the same type.

        // Send ClientHello
        ProtocolMessage v0 = md.genClientHelloMessage(ProtocolVersion.TLS12,
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM, CompressionMethod.NO_COMPRESSION,
                Random.NONCE, SessionId.EMPTY);
        md.addHelloMessageExtension(v0, Maude.NamedGroup.SECP256R1);
        md.addHelloMessageExtension(v0, Maude.NamedGroup.SECP256R1);
        md.addHelloMessageExtension(v0, Maude.ECPointFormat.UNCOMPRESSED);
        md.addHelloMessageExtension(v0, Maude.SignatureAndHashAlgorithm.ECDSA_SHA256);
        md.send(v0);

        // Receive Alert Fatal
        ProtocolMessage v1 = md.recv();
        Assert.assertEquals(MessageType.ALERT, md.getMessageType(v1));
        Assert.assertEquals(AlertLevel.FATAL, md.getAlertLevel(v1));
    }

    @Test
    public void test_duplicate_extension_type2(){
        // There MUST NOT be more than one extension of the same type.

        // Send ClientHello
        ProtocolMessage v0 = md.genClientHelloMessage(ProtocolVersion.TLS12,
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM, CompressionMethod.NO_COMPRESSION,
                Random.NONCE, SessionId.EMPTY);
        md.addHelloMessageExtension(v0, Maude.NamedGroup.SECP256R1);
        md.addHelloMessageExtension(v0, Maude.ECPointFormat.UNCOMPRESSED);
        md.addHelloMessageExtension(v0, Maude.ECPointFormat.UNCOMPRESSED);
        md.addHelloMessageExtension(v0, Maude.SignatureAndHashAlgorithm.ECDSA_SHA256);
        md.send(v0);

        // Receive Alert Fatal
        ProtocolMessage v1 = md.recv();
        Assert.assertEquals(MessageType.ALERT, md.getMessageType(v1));
        Assert.assertEquals(AlertLevel.FATAL, md.getAlertLevel(v1));
    }

    @Test
    public void test_duplicate_extension_type3(){
        // There MUST NOT be more than one extension of the same type.

        // Send ClientHello
        ProtocolMessage v0 = md.genClientHelloMessage(ProtocolVersion.TLS12,
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM, CompressionMethod.NO_COMPRESSION,
                Random.NONCE, SessionId.EMPTY);
        md.addHelloMessageExtension(v0, Maude.NamedGroup.SECP256R1);
        md.addHelloMessageExtension(v0, Maude.ECPointFormat.UNCOMPRESSED);
        md.addHelloMessageExtension(v0, Maude.SignatureAndHashAlgorithm.ECDSA_SHA256);
        md.addHelloMessageExtension(v0, Maude.SignatureAndHashAlgorithm.ECDSA_SHA256);
        md.send(v0);

        // Receive Alert Fatal
        ProtocolMessage v1 = md.recv();
        Assert.assertEquals(MessageType.ALERT, md.getMessageType(v1));
        Assert.assertEquals(AlertLevel.FATAL, md.getAlertLevel(v1));
    }

    @Test
    public void test_consistent_signature_and_hash_algorithm1(){
        // If the client provided a "signature_algorithms" extension, then all
        // certificates provided by the server MUST be signed by a
        // hash/signature algorithm pair that appears in that extension.

        // Send ClientHello
        ProtocolMessage v0 = md.genClientHelloMessage(ProtocolVersion.TLS12,
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM, CompressionMethod.NO_COMPRESSION,
                Random.NONCE, SessionId.EMPTY);
        md.addHelloMessageExtension(v0, Maude.NamedGroup.SECP256R1);
        md.addHelloMessageExtension(v0, Maude.ECPointFormat.UNCOMPRESSED);
        md.addHelloMessageExtension(v0, Maude.SignatureAndHashAlgorithm.ECDSA_SHA256);
        md.send(v0);

        // Receive ServerHello
        ProtocolMessage v1 = md.recv();
        Assert.assertEquals(MessageType.HANDSHAKE, md.getMessageType(v1));
        Assert.assertEquals(HandshakeMessageType.SERVER_HELLO, md.getHandshakeMessageType(v1));
        Assert.assertEquals(CompressionMethod.NULL, md.getSelectedCompressionMethod(v1));

        // Receive ServerCertificate
        ProtocolMessage v2 = md.recv();
        Assert.assertEquals(MessageType.HANDSHAKE, md.getMessageType(v2));
        Assert.assertEquals(HandshakeMessageType.CERTIFICATE, md.getHandshakeMessageType(v2));
        //Assert.assertEquals(SignatureAndHashAlgorithm.ECDSA_SHA256, md.getSignatureAndHashAlgorithm(v2));
    }

    @Test
    public void test_consistent_signature_and_hash_algorithm2(){
        // If the client provided a "signature_algorithms" extension, then all
        // certificates provided by the server MUST be signed by a
        // hash/signature algorithm pair that appears in that extension.

        // Send ClientHello
        ProtocolMessage v0 = md.genClientHelloMessage(ProtocolVersion.TLS12,
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM, CompressionMethod.NO_COMPRESSION,
                Random.NONCE, SessionId.EMPTY);
        md.addHelloMessageExtension(v0, Maude.NamedGroup.SECP256R1);
        md.addHelloMessageExtension(v0, Maude.ECPointFormat.UNCOMPRESSED);
        md.addHelloMessageExtension(v0, Maude.SignatureAndHashAlgorithm.RSA_SHA256);
        md.send(v0);

        // Receive ServerHello
        ProtocolMessage v1 = md.recv();
        Assert.assertEquals(MessageType.HANDSHAKE, md.getMessageType(v1));
        Assert.assertEquals(HandshakeMessageType.SERVER_HELLO, md.getHandshakeMessageType(v1));
        Assert.assertEquals(CompressionMethod.NULL, md.getSelectedCompressionMethod(v1));

        // Receive ServerCertificate
        ProtocolMessage v2 = md.recv();
        Assert.assertEquals(MessageType.HANDSHAKE, md.getMessageType(v2));
        Assert.assertEquals(HandshakeMessageType.CERTIFICATE, md.getHandshakeMessageType(v2));
        //Assert.assertEquals(SignatureAndHashAlgorithm.RSA_SHA256, md.getSignatureAndHashAlgorithm(v2));
    }

    @Test
    public void test_early_ccs_injection1(){
        // The ChangeCipherSpec message is sent during the
        // handshake after the security parameters have been agreed upon, but
        // before the verifying Finished message is sent.

        // Send ChangeCipherSpec
        ProtocolMessage v0 = md.genChangeCipherSpec();
        md.send(v0);

        ProtocolMessage v1 = md.recv();
        Assert.assertEquals(MessageType.ALERT, md.getMessageType(v1));
        Assert.assertEquals(AlertLevel.FATAL, md.getAlertLevel(v1));
    }

    @Test
    public void test_early_ccs_injection2(){
        // The ChangeCipherSpec message is sent during the
        // handshake after the security parameters have been agreed upon, but
        // before the verifying Finished message is sent.

        // Send ClientHello
        ProtocolMessage v0 = md.genClientHelloMessage(ProtocolVersion.TLS12,
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM, CompressionMethod.NO_COMPRESSION,
                Random.NONCE, SessionId.EMPTY);

        md.addHelloMessageExtension(v0, Maude.NamedGroup.SECP256R1);
        md.addHelloMessageExtension(v0, Maude.ECPointFormat.UNCOMPRESSED);
        md.addHelloMessageExtension(v0, Maude.SignatureAndHashAlgorithm.ECDSA_SHA256);

        md.send(v0);

        // Receive ServerHello
        ProtocolMessage v1 = md.recv();
        Assert.assertEquals(MessageType.HANDSHAKE, md.getMessageType(v1));
        Assert.assertEquals(HandshakeMessageType.SERVER_HELLO, md.getHandshakeMessageType(v1));
        Assert.assertEquals(ProtocolVersion.TLS12, md.getProtocolVersion(v1));
        Assert.assertEquals(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM, md.getSelectedCipherSuite(v1));
        Assert.assertEquals(CompressionMethod.NULL, md.getSelectedCompressionMethod(v1));
        Assert.assertTrue(md.hasRandom(v1));
        Assert.assertTrue(md.hasSessionId(v1));

        // Receive Server Certificate
        ProtocolMessage v2 = md.recv();
        Assert.assertEquals(MessageType.HANDSHAKE, md.getMessageType(v2));
        Assert.assertEquals(HandshakeMessageType.CERTIFICATE, md.getHandshakeMessageType(v2));
        //Assert.assertTrue(md.hasPeerPublicKey(v2));

        // Receive ServerKeyExchange
        ProtocolMessage v3 = md.recv();
        Assert.assertEquals(MessageType.HANDSHAKE, md.getMessageType(v3));
        Assert.assertEquals(HandshakeMessageType.SERVER_KEY_EXCHANGE, md.getHandshakeMessageType(v3));
        Assert.assertTrue(md.hasServerKey(v3));
        Assert.assertTrue(md.verifySignature(v3));

        // Receive Certificate Request
        ProtocolMessage v4 = md.recv();
        Assert.assertEquals(MessageType.HANDSHAKE, md.getMessageType(v4));
        Assert.assertEquals(HandshakeMessageType.CERTIFICATE_REQUEST, md.getHandshakeMessageType(v4));

        // Receive ServerHelloDone
        ProtocolMessage v5 = md.recv();
        Assert.assertEquals(MessageType.HANDSHAKE, md.getMessageType(v5));
        Assert.assertEquals(HandshakeMessageType.SERVER_HELLO_DONE, md.getHandshakeMessageType(v5));

        // Send ChangeCipherSpec
        ProtocolMessage v6 = md.genChangeCipherSpec();
        md.send(v6);

        ProtocolMessage v7 = md.recv();
        Assert.assertEquals(MessageType.ALERT, md.getMessageType(v7));
        Assert.assertEquals(AlertLevel.FATAL, md.getAlertLevel(v7));
    }

}