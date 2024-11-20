package scenario;

import protocol.Variable;
import de.rub.nds.tlsattacker.core.protocol.ProtocolMessage;
import de.rub.nds.tlsattacker.core.record.Record;

import java.util.List;

public class ProtocolMessageVariable implements Variable {
    private List<ProtocolMessage> recordMessages = null;

    public ProtocolMessageVariable(List<ProtocolMessage> recordMessages) {
        this.recordMessages = recordMessages;
    }

    public List<ProtocolMessage> getValue() {
        return recordMessages;
    }
}
