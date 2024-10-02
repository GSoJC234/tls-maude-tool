import Maude.*;
import de.rub.nds.tlsattacker.core.protocol.ProtocolMessage;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ServerTest {

    private MaudeTLS md;
    @Before
    public void initialize(){
        md = new MaudeTLS("/home/jaehun/git/My-TLS-Attacker/resources/default_config2.xml");
        md.setCertificateKeyPair("/home/jaehun/git/My-TLS-Attacker/resources/ca-cert.pem");
        md.setCertificateKeyPair("/home/jaehun/git/My-TLS-Attacker/resources/server-cert.pem",
                "/home/jaehun/git/My-TLS-Attacker/resources/server-key.pem");
        md.initialize("localhost", 4433, "server");
    }

    @Test
    public void test_handshake_sucess(){
        // Receive ClientHello
        ProtocolMessage v0 = md.recv();
        Assert.assertEquals(MessageType.HANDSHAKE, md.getMessageType(v0));
        Assert.assertEquals(HandshakeMessageType.CLIENT_HELLO, md.getHandshakeMessageType(v0));
        Assert.assertEquals(TLSVersion.TLS12, md.getProtocolVersion(v0));
        Assert.assertEquals(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM, md.getCipherSuite(v0));
        Assert.assertEquals(CompressionMethod.NULL, md.getCompressionMethods(v0).get(0));
        Assert.assertTrue(md.hasRandom(v0));
        if(md.getSessionIdLength(v0) > 0){
            Assert.assertTrue(md.hasSessionId(v0));
        }

        // Send ServerHello
        ProtocolMessage v1 = md.genServerHelloMessage(TLSVersion.TLS12, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM,
                CompressionMethod.NO_COMPRESSION, Random.NONCE, SessionId.NONCE);
        md.tempFunc(v1, Hex.decode("6673d056"), Hex.decode("418f8ec55dfa917883bcdb31e48cb3640ec9a1706069422ce3e2e025"),
                Hex.decode("42eabe9a6a40d217a72a7720e46d76fa08db4aae69262e36f4318f6929cb53ba"));
        Assert.assertNotNull(v1);
        md.send(v1);

        // Send Server Certificate
        ProtocolMessage v2 = md.genCertificate();
        Assert.assertNotNull(v2);
        md.send(v2);

        // Send ServerKeyExchange
        ProtocolMessage v4 = md.genServerKeyExchange(CurveType.NAMED_CURVE, NamedGroup.SECP256R1);
        Assert.assertNotNull(v4);
        md.send(v4);

        // Send Certificate Request
        ProtocolMessage v3 = md.genCertificateRequest(CertificateType.ECDSA_SIGN, SignatureAlgorithm.ECDSA, HashAlgorithm.SHA256);
        Assert.assertNotNull(v3);
        md.send(v3);

        // Send ServerHelloDone
        ProtocolMessage v5 = md.genServerHelloDone();
        Assert.assertNotNull(v5);
        md.send(v5);

        // Receive Client Certificate
        ProtocolMessage v6 = md.recv();
        Assert.assertEquals(MessageType.HANDSHAKE, md.getMessageType(v6));
        Assert.assertEquals(HandshakeMessageType.CERTIFICATE, md.getHandshakeMessageType(v6));
        Assert.assertEquals(CertificateKeyType.ECDSA, md.getCertificateKeyType(v6));
        Assert.assertEquals(SignatureAndHashAlgorithm.ECDSA_SHA256, md.getSignatureAndHashAlgorithm(v6));
        Assert.assertEquals(NamedGroup.SECP256R1, md.getPublicKeyGroup(v6));
        Assert.assertEquals(NamedGroup.SECP256R1, md.getSignatureGroup(v6));

        // Receive ClientKeyExchange
        ProtocolMessage v7 = md.recv();
        Assert.assertEquals(MessageType.HANDSHAKE, md.getMessageType(v7));
        Assert.assertEquals(HandshakeMessageType.CLIENT_KEY_EXCHANGE, md.getHandshakeMessageType(v7));
        Assert.assertTrue(md.hasECDHClientPublicKey(v7));

        // Receive Certificate Verify
        ProtocolMessage v8 = md.recv();
        Assert.assertEquals(MessageType.HANDSHAKE, md.getMessageType(v8));
        Assert.assertEquals(HandshakeMessageType.CERTIFICATE_VERIFY, md.getHandshakeMessageType(v8));
        Assert.assertTrue(md.hasCertificateVerifySignature(v8));
        Assert.assertEquals(SignatureAndHashAlgorithm.ECDSA_SHA256, md.getSignatureAndHashAlgorithm(v8));

        // Receive ChangeCipherSpec
        ProtocolMessage v9 = md.recv();
        Assert.assertEquals(MessageType.CHANGE_CIPHER_SPEC, md.getMessageType(v9));

        // Receive Finished
        ProtocolMessage v10 = md.genFinished();
        Assert.assertEquals(MessageType.HANDSHAKE, md.getMessageType(v10));
        Assert.assertEquals(HandshakeMessageType.FINISHED, md.getHandshakeMessageType(v10));

        // Send ChangeCipherSpec
        ProtocolMessage v11 = md.genChangeCipherSpec();
        Assert.assertNotNull(v11);
        md.send(v11);

        // Receive Finished
        ProtocolMessage v12 = md.genFinished();
        Assert.assertNotNull(v12);
        md.send(v12);
    }

    @Test
    public void test_compression_method(){
        // compression_method MUST contain and all implementations MUST support,
        // CompressionMethod.null. Thus a client and server will always be able to
        // agree on a compression method

        // Receive ClientHello
        ProtocolMessage v0 = md.recv();
        Assert.assertEquals(MessageType.HANDSHAKE, md.getMessageType(v0));
        Assert.assertEquals(HandshakeMessageType.CLIENT_HELLO, md.getHandshakeMessageType(v0));
        Assert.assertTrue(0 < md.getCompressionMethodLength(v0));
        Assert.assertTrue(md.getCompressionMethods(v0).contains(CompressionMethod.NULL));
    }

}
