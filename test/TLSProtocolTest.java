
import maude.*;
import org.junit.After;
import protocol.Protocol;
import protocol.Variable;
import scenario.TLSAttacker;
import protocol.TLSProtocol;
import org.junit.Before;
import org.junit.Test;
import scenario.session.TLSClientSession;
import scenario.session.TLSServerSession;

public class TLSProtocolTest {

    public TLSAttacker protocol;
    public TLSClientSession client_session;
    public TLSServerSession server_session;

    public TLSClientSession client_session1;
    public TLSServerSession server_session1;
    public TLSClientSession client_session2;
    public TLSServerSession server_session2;


    @Before
    public void setTLSAttacker(){
        protocol = new TLSAttacker("/home/jaehun/git/temp/maude-tls-attacker/resources/default_config3.xml");

        client_session = new TLSClientSession("CI");
        client_session.setExecutor(protocol);
        server_session = new TLSServerSession("SI");
        server_session.setExecutor(protocol);


        client_session1 = new TLSClientSession("CI1");
        client_session1.setExecutor(protocol);

        server_session1 = new TLSServerSession("SI1");
        server_session1.setExecutor(protocol);


        client_session2 = new TLSClientSession("CI2");
        client_session2.setExecutor(protocol);

        server_session2 = new TLSServerSession("SI2");
        server_session2.setExecutor(protocol);
    }

    @After
    public void execute(){
        protocol.execute();
    }

    @Test
    public void test1(){
        server_session.accept(4444, "localhost");
        client_session.connect(4433, "localhost");

        // ClientHello
        Variable v0 = server_session.recv();
        client_session.send(v0);
        Variable v1 = client_session.recv();
        server_session.send(v1);
        Variable v2 = client_session.recv();
        server_session.send(v2);
        Variable v3 = client_session.recv();
        server_session.send(v3);
        Variable v5 = client_session.recv();
        server_session.send(v5);
        Variable v7 = server_session.recv();
        client_session.send(v7);
        Variable v9 = server_session.recv();
        client_session.send(v9);
        Variable v10 = server_session.recv();
        client_session.send(v10);
        Variable v11 = client_session.recv();
        server_session.send(v11);
        Variable v12 = client_session.recv();
        server_session.send(v12);

        server_session.close();
        client_session.close();
    }

    @Test
    public void test2(){
        server_session1.accept(4444, "localhost");
        server_session2.accept(4445, "localhost");
        client_session1.connect(4433, "localhost");
        client_session2.connect(4434, "localhost");

        // ClientHello
        Variable v0 = server_session1.recv();
        Variable v1 = server_session2.recv();
        client_session1.send(v0);
        client_session2.send(v1);

        // ServerHello
        Variable v2 = client_session1.recv();
        Variable v3 = client_session2.recv();
        server_session1.send(v2);
        server_session2.send(v3);

        // Certificate
        Variable v4 = client_session1.recv();
        Variable v5 = client_session2.recv();
        server_session1.send(v4);
        server_session2.send(v5);

        // ServerKeyExchange
        Variable v6 = client_session1.recv();
        Variable v7 = client_session2.recv();
        server_session1.send(v6);
        server_session2.send(v7);

        // ServerHelloDone
        Variable v8 = client_session1.recv();
        Variable v9 = client_session2.recv();
        server_session1.send(v8);
        server_session2.send(v9);

        // ClientKeyExchange
        Variable v10 = server_session1.recv();
        Variable v11 = server_session2.recv();
        client_session1.send(v10);
        client_session2.send(v11);

        // ChangeCipherSpec
        Variable v12 = server_session1.recv();
        Variable v13 = server_session2.recv();
        client_session1.send(v12);
        client_session2.send(v13);

        // ClientFinished
        Variable v14 = server_session1.recv();
        Variable v15 = server_session2.recv();
        client_session1.send(v14);
        client_session2.send(v15);

        // ChangeCipherSpec
        Variable v16 = client_session1.recv();
        Variable v17 = client_session2.recv();
        server_session1.send(v16);
        server_session2.send(v17);

        // ServerFinished
        Variable v18 = client_session1.recv();
        Variable v19 = client_session2.recv();
        server_session1.send(v18);
        server_session2.send(v19);

        server_session1.close();
        client_session1.close();
        server_session2.close();
        client_session2.close();
    }

    @Test
    public void test3(){
        server_session.accept(4446, "localhost");

        Variable v0 = server_session.makeCertificate("/home/jaehun/git/maude-tls-attacker/resources/server-cert.pem"); // Server Certificate
        Variable v1 = server_session.makeCertificate("/home/jaehun/git/maude-tls-attacker/resources/client-ecc-cert.pem"); // Attacker Certificate (for testing, change invalid certificate)

        Variable v2 = server_session.recv();
        server_session.assertEqual(server_session.constant(ProtocolMessageType.HANDSHAKE), server_session.getContentType(v2));
        server_session.assertEqual(server_session.constant(ProtocolVersion.TLS12), server_session.getRecordVersion(v2));
        server_session.assertEqual(server_session.constant(HandshakeMessageType.CLIENT_HELLO), server_session.getHandshakeMessageType(v2));
        server_session.assertEqual(server_session.constant(ProtocolVersion.TLS12), server_session.getProtocolVersion(v2));
        server_session.assertEqual(server_session.constant(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM), server_session.getCipherSuite(v2));
        server_session.assertEqual(server_session.constant(CompressionMethod.NULL), server_session.getCompressionMethod(v2));

        Variable v3 = server_session.buildServerHello(server_session.constant(HandshakeMessageType.SERVER_HELLO),
                                                      server_session.constant(MessageSize.VALID),
                                                      server_session.constant(ProtocolVersion.TLS12),
                                                      server_session.constant(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM),
                                                      server_session.constant(Random.NONCE),
                                                      server_session.constant(Random.NONCE),
                                                      server_session.constant(MessageSize.VALID),
                                                      server_session.constant(CompressionMethod.NULL));
        Variable v4 = server_session.buildRecord(server_session.constant(ProtocolMessageType.HANDSHAKE), server_session.constant(ProtocolVersion.TLS12), server_session.constant(MessageSize.VALID), v3);
        Variable v5 = server_session.buildMessage(v4, v3);

        Variable v6 = server_session.buildCertificate(server_session.constant(HandshakeMessageType.CERTIFICATE), server_session.constant(MessageSize.VALID), v0);
        Variable v7 = server_session.buildRecord(server_session.constant(ProtocolMessageType.HANDSHAKE), server_session.constant(ProtocolVersion.TLS12), server_session.constant(MessageSize.VALID), v6);
        Variable v8 = server_session.buildMessage(v7, v6);

        Variable v9 = server_session.changeCertificate(v8, v0, v1);

        server_session.send(v5);
        server_session.send(v9);

        Variable v10 = server_session.recv();
        server_session.assertEqual(server_session.constant(ProtocolMessageType.ALERT), server_session.getContentType(v10));
        server_session.assertEqual(server_session.constant(ProtocolVersion.TLS12), server_session.getRecordVersion(v10));
        server_session.assertEqual(server_session.constant(AlertLevel.FATAL), server_session.getAlertLevel(v10));
    }

    @Test
    public void CVE_2020_24613(){
        server_session.accept(4446, "localhost");
        server_session.setRandomPrivateKey("SECP256R1");
        server_session.setCertificateEcPrivateKey("/home/jaehun/git/temp/maude-tls-attacker/resources/server-key.pem", "SECP256R1");
        Variable v0 = server_session.makeCertificate("/home/jaehun/git/temp/maude-tls-attacker/resources/server-cert.pem"); // Server Certificate

        Variable v1 = server_session.recv();
        server_session.assertEqual(server_session.constant(ProtocolMessageType.HANDSHAKE), server_session.getContentType(v1));
        server_session.assertEqual(server_session.constant(ProtocolVersion.TLS12), server_session.getRecordVersion(v1));
        server_session.assertEqual(server_session.constant(HandshakeMessageType.CLIENT_HELLO), server_session.getHandshakeMessageType(v1));
        server_session.assertEqual(server_session.constant(ProtocolVersion.TLS12), server_session.getProtocolVersion(v1));
        server_session.assertEqual(server_session.constant(CompressionMethod.NULL), server_session.getCompressionMethod(v1));
        server_session.assertEqual(server_session.constant(CipherSuite.TLS_AES_128_CCM_SHA256), server_session.getCipherSuite(v1));
        server_session.assertEqual(server_session.constant(ProtocolVersion.TLS13), server_session.getSupportedVersion(v1));
        server_session.assertEqual(server_session.constant(SignatureAndHashAlgorithm.ECDSA_SHA256), server_session.getSignatureAndHashAlgorithm(v1));
        server_session.assertEqual(server_session.constant(NamedGroup.SECP256R1), server_session.getNamedGroupFromKeyShares(server_session.getKeyShareEntries(v1)));
        server_session.assertEqual(server_session.constant(NamedGroup.SECP256R1), server_session.getNamedGroup(v1));

        Variable v5 = server_session.buildKeyShareEntry(server_session.constant(NamedGroup.SECP256R1));
        Variable v6 = server_session.buildServerHello(server_session.constant(HandshakeMessageType.SERVER_HELLO),
                                                      server_session.constant(MessageSize.VALID),
                                                      server_session.constant(ProtocolVersion.TLS12),
                                                      server_session.constant(CipherSuite.TLS_AES_128_CCM_SHA256),
                                                      server_session.constant(Random.NONCE),
                                                      server_session.constant(Random.EMPTY),
                                                      server_session.constant(MessageSize.VALID),
                                                      server_session.constant(CompressionMethod.NULL)
                                                      );
        Variable v6_ext = server_session.buildExtension(v6, server_session.constant(SupportedVersion.TLS13), v5);
        server_session.updateDigest(v6_ext);
        Variable v7 = server_session.buildRecord(server_session.constant(ProtocolMessageType.HANDSHAKE), server_session.constant(ProtocolVersion.TLS12), server_session.constant(MessageSize.VALID), v6_ext);
        Variable v8 = server_session.buildMessage(v7, v6_ext);
        server_session.send(v8);

        Variable v12 = server_session.buildEncryptedExtension(server_session.constant(HandshakeMessageType.ENCRYPTED_EXTENSION),
                                                              server_session.constant(MessageSize.VALID));
        Variable v12_ext = server_session.buildExtension(v12, server_session.constant(NamedGroup.SECP256R1));
        server_session.updateDigest(v12_ext);
        Variable v13 = server_session.buildRecord(server_session.constant(ProtocolMessageType.HANDSHAKE), server_session.constant(ProtocolVersion.TLS12), server_session.constant(MessageSize.VALID), v12_ext);
        Variable v14 = server_session.buildMessage(v13, v12_ext);
        Variable v15 = server_session.encrypt(v14);
        server_session.send(v15);

        Variable v28 = server_session.buildFinished(server_session.constant(HandshakeMessageType.FINISHED),
                                                    server_session.constant(MessageSize.VALID));
        server_session.updateDigest(v28);
        Variable v29 = server_session.buildRecord(server_session.constant(ProtocolMessageType.HANDSHAKE), server_session.constant(ProtocolVersion.TLS12), server_session.constant(MessageSize.VALID), v28);
        Variable v30 = server_session.buildMessage(v29, v28);
        Variable v31 = server_session.encrypt(v30);
        server_session.send(v31);

        Variable v32 = server_session.recv();
        server_session.assertEqual(server_session.constant(ProtocolMessageType.ALERT), server_session.getContentType(v32));
        server_session.assertEqual(server_session.constant(ProtocolVersion.TLS12), server_session.getRecordVersion(v32));
        server_session.assertEqual(server_session.constant(AlertLevel.FATAL), server_session.getAlertLevel(v32));
        server_session.assertEqual(server_session.constant(AlertDescription.UNEXPECTED_MESSAGE), server_session.getAlertDescription(v32));
    }
}
