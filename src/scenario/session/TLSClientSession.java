package scenario.session;

import de.rub.nds.tlsattacker.core.connection.AliasedConnection;
import de.rub.nds.tlsattacker.core.connection.OutboundConnection;

public class TLSClientSession extends TLSSession {

    public TLSClientSession(String alias) {
        super(alias);
    }

    @Override
    public void accept(int port, String ip) {
        throw new RuntimeException("Client session can not call accept() method");
    }

    @Override
    public void connect(int port, String ip) {
        connection = new OutboundConnection(alias, port, ip);
        connection.setConnectionTimeout(10000);
        attacker.addAliasedConnection(connection);
    }
}
