
import Maude.*;
import Protocol.Variable;
import scenario.TLSAttacker;
import Protocol.TLSProtocol;
import org.junit.Before;
import org.junit.Test;

public class TLSProtocolTest {

    public TLSProtocol protocol;

    @Before
    public void setTLSAttacker(){
        protocol = new TLSAttacker("/home/jaehun/git/maude-tls-attacker/resources/default_config3.xml");
    }

    @Test
    public void test1(){
        protocol.accept("CI", 4444, "localhost");
        protocol.connect("SI", 4433, "localhost");

        // ClientHello
        Variable v0 = protocol.recv("CI");
        protocol.send("SI", v0);
        Variable v1 = protocol.recv("SI");
        protocol.send("CI", v1);
        Variable v2 = protocol.recv("SI");
        protocol.send("CI", v2);
        Variable v3 = protocol.recv("SI");
        protocol.send("CI", v3);
        Variable v5 = protocol.recv("SI");
        protocol.send("CI", v5);
        Variable v7 = protocol.recv("CI");
        protocol.send("SI", v7);
        Variable v9 = protocol.recv("CI");
        protocol.send("SI", v9);
        Variable v10 = protocol.recv("CI");
        protocol.send("SI", v10);
        Variable v11 = protocol.recv("SI");
        protocol.send("CI", v11);
        Variable v12 = protocol.recv("SI");
        protocol.send("CI", v12);
        protocol.close("CI");
        protocol.close("SI");

        protocol.execute();
    }

    @Test
    public void test2(){
        protocol.accept("CI1", 4444, "localhost");
        protocol.accept("CI2", 4445, "localhost");
        protocol.connect("SI1", 4433, "localhost");
        protocol.connect("SI2", 4434, "localhost");

        // ClientHello
        Variable v0 = protocol.recv("CI1");
        Variable v1 = protocol.recv("CI2");
        protocol.send("SI1", v0);
        protocol.send("SI2", v1);

        // ServerHello
        Variable v2 = protocol.recv("SI1");
        Variable v3 = protocol.recv("SI2");
        protocol.send("CI1", v2);
        protocol.send("CI2", v3);

        // Certificate
        Variable v4 = protocol.recv("SI1");
        Variable v5 = protocol.recv("SI2");
        protocol.send("CI1", v4);
        protocol.send("CI2", v5);

        // ServerKeyExchange
        Variable v6 = protocol.recv("SI1");
        Variable v7 = protocol.recv("SI2");
        protocol.send("CI1", v6);
        protocol.send("CI2", v7);

        // ServerHelloDone
        Variable v8 = protocol.recv("SI1");
        Variable v9 = protocol.recv("SI2");
        protocol.send("CI1", v8);
        protocol.send("CI2", v9);

        // ClientKeyExchange
        Variable v10 = protocol.recv("CI1");
        Variable v11 = protocol.recv("CI2");
        protocol.send("SI1", v10);
        protocol.send("SI2", v11);

        // ChangeCipherSpec
        Variable v12 = protocol.recv("CI1");
        Variable v13 = protocol.recv("CI2");
        protocol.send("SI1", v12);
        protocol.send("SI2", v13);

        // ClientFinished
        Variable v14 = protocol.recv("CI1");
        Variable v15 = protocol.recv("CI2");
        protocol.send("CI1", v14);
        protocol.send("CI2", v15);

        // ChangeCipherSpec
        Variable v16 = protocol.recv("SI1");
        Variable v17 = protocol.recv("SI2");
        protocol.send("CI1", v16);
        protocol.send("CI2", v17);

        // ServerFinished
        Variable v18 = protocol.recv("SI1");
        Variable v19 = protocol.recv("SI2");
        protocol.send("CI1", v18);
        protocol.send("CI2", v19);

        protocol.close("CI1");
        protocol.close("SI1");
        protocol.close("CI2");
        protocol.close("SI2");

        protocol.execute();
    }

    @Test
    public void test3(){
        protocol.connect("CI", 4444, "localhost");
        Variable v0 = protocol.makeCertificate("/home/jaehun/git/maude-tls-attacker/resources/server-cert.pem"); // Server Certificate
        Variable v1 = protocol.makeCertificate("/home/jaehun/git/maude-tls-attacker/resources/client-ecc-cert.pem"); // Attacker Certificate

        Variable v2 = protocol.recv("CI");
        protocol.assertEqual(protocol.constant(ProtocolMessageType.HANDSHAKE), protocol.getContentType(v2));
        protocol.assertEqual(protocol.constant(ProtocolVersion.TLS12), protocol.getRecordVersion(v2));
        protocol.assertEqual(protocol.constant(HandshakeMessageType.CLIENT_HELLO), protocol.getHandshakeMessageType(v2));
        protocol.assertEqual(protocol.constant(ProtocolVersion.TLS12), protocol.getProtocolVersion(v2));
        protocol.assertEqual(protocol.constant(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM), protocol.getCipherSuite(v2));
        protocol.assertEqual(protocol.constant(CompressionMethod.NULL), protocol.getCompressionMethod(v2));

        Variable v3 = protocol.buildServerHello(protocol.constant(HandshakeMessageType.SERVER_HELLO),
                                                protocol.constant(MessageSize.VALID), protocol.constant(ProtocolVersion.TLS12), protocol.constant(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM), protocol.constant(Random.NONCE),
                                                protocol.constant(Random.NONCE), protocol.constant(MessageSize.VALID), protocol.constant(CompressionMethod.NULL));
        Variable v4 = protocol.buildRecord(protocol.constant(ProtocolMessageType.HANDSHAKE), protocol.constant(ProtocolVersion.TLS12), protocol.constant(MessageSize.VALID), v3);
        Variable v5 = protocol.buildMessage(v4, v3);

        Variable v6 = protocol.buildCertificate(protocol.constant(HandshakeMessageType.CERTIFICATE), protocol.constant(MessageSize.VALID), v0);
        Variable v7 = protocol.buildRecord(protocol.constant(ProtocolMessageType.HANDSHAKE), protocol.constant(ProtocolVersion.TLS12), protocol.constant(MessageSize.VALID), v6);
        Variable v8 = protocol.buildMessage(v7, v6);

        Variable v9 = protocol.changeCertificate(v8, v1);

        protocol.send("CI", v5);
        protocol.send("CI", v9);

        Variable v10 = protocol.recv("CI");
        protocol.assertEqual(protocol.constant(ProtocolMessageType.ALERT), protocol.getContentType(v10));
        protocol.assertEqual(protocol.constant(ProtocolVersion.TLS12), protocol.getRecordVersion(v10));
        protocol.assertEqual(protocol.constant(AlertLevel.FATAL), protocol.getAlertLevel(v10));

        protocol.execute();
    }
}
