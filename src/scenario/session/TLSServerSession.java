package scenario.session;

import de.rub.nds.tlsattacker.core.connection.InboundConnection;

public class TLSServerSession extends TLSSession{

    public TLSServerSession(String alias) {
        super(alias);
    }

    @Override
    public void accept(int port, String ip) {
        connection = new InboundConnection(alias, port, ip);
        connection.setConnectionTimeout(10000);
        attacker.addAliasedConnection(connection);
    }

    @Override
    public void connect(int port, String ip) {
        throw new RuntimeException("Server session can not call connect() method");
    }
}
