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
        protocol.accept("CI");
        protocol.connect("SI");

        // ClientHello
        Variable v0 = protocol.recv("CI");
        protocol.send("SI", v0);
        Variable v1 = protocol.recv("SI");
        protocol.send("CI", v1);
        Variable v2 = protocol.recv("SI");
        protocol.send("CI", v2);
        Variable v3 = protocol.recv("SI");
        protocol.send("CI", v3);
        Variable v4 = protocol.recv("SI");
        protocol.send("CI", v4);
        Variable v5 = protocol.recv("SI");
        protocol.send("CI", v5);
        Variable v6 = protocol.recv("CI");
        protocol.send("SI", v6);
        Variable v7 = protocol.recv("CI");
        protocol.send("SI", v7);
        Variable v8 = protocol.recv("CI");
        protocol.send("SI", v8);
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
}
