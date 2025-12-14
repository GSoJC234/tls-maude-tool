package mta.scenario.variable;

import mta.protocol.Variable;
import de.rub.nds.tlsattacker.core.protocol.ProtocolMessage;
import de.rub.nds.tlsattacker.core.record.Record;

import java.util.List;

public class MessageVariable implements Variable {

    private List<ProtocolMessage> protocolMessages;
    private List<Record> recordMessages;

    public MessageVariable(List<Record> recordMessages, List<ProtocolMessage> message) {
        this.protocolMessages = message;
        this.recordMessages = recordMessages;
    }

    public List<ProtocolMessage> getProtocolMessages() {
        return protocolMessages;
    }
    public List<Record> getRecordMessages() {
        return recordMessages;
    }

    @Override
    public List<?> getValue() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
