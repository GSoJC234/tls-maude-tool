package scenario.session;

import de.rub.nds.tlsattacker.core.connection.AliasedConnection;
import de.rub.nds.tlsattacker.core.connection.OutboundConnection;

public class TLSClientSession extends TLSSession {

    public TLSClientSession(String alias) {
        super(alias);
    }

}
