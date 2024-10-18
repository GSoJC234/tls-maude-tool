package scenario;

import Protocol.Variable;
import de.rub.nds.tlsattacker.core.protocol.ProtocolMessage;

import java.util.List;

public class MessageVariable implements Variable {

    private List<ProtocolMessage> message;
    public MessageVariable(List<ProtocolMessage> message) {
        this.message = message;
    }

    public List<ProtocolMessage> get() {
        return message;
    }
}
