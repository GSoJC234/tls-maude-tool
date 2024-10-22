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
}
