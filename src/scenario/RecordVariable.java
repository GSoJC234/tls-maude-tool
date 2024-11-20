package scenario;

import protocol.Variable;
import de.rub.nds.tlsattacker.core.record.Record;

import java.util.List;

public class RecordVariable implements Variable{
    private List<Record> recordMessages = null;

    public RecordVariable(List<Record> recordMessages) {
        this.recordMessages = recordMessages;
    }

    public List<Record> getValue() {
        return recordMessages;
    }

}
