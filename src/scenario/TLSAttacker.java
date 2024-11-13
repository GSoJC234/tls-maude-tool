package scenario;


import Protocol.TLSProtocol;
import Protocol.Variable;
import de.rub.nds.tlsattacker.core.config.Config;
import de.rub.nds.tlsattacker.core.connection.AliasedConnection;
import de.rub.nds.tlsattacker.core.connection.InboundConnection;
import de.rub.nds.tlsattacker.core.connection.OutboundConnection;
import de.rub.nds.tlsattacker.core.constants.RunningModeType;
import de.rub.nds.tlsattacker.core.protocol.ProtocolMessage;
import de.rub.nds.tlsattacker.core.record.Record;
import de.rub.nds.tlsattacker.core.state.State;
import de.rub.nds.tlsattacker.core.util.ProviderUtil;
import de.rub.nds.tlsattacker.core.workflow.DefaultWorkflowExecutor;
import de.rub.nds.tlsattacker.core.workflow.WorkflowTrace;
import de.rub.nds.tlsattacker.core.workflow.action.ReceiveOneAction;
import de.rub.nds.tlsattacker.core.workflow.action.SendAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TLSAttacker extends TLSProtocol {

    public String serverAlias = null;
    public String clientAlias = null;
    private Config config = null;
    private WorkflowTrace trace = null;
    private State state = null;
    private DefaultWorkflowExecutor executor = null;
    private List<AliasedConnection> aliasedConnections = null;

    private static Logger LOGGER = LogManager.getLogger();

    public TLSAttacker(String configPath){
        ProviderUtil.addBouncyCastleProvider();
        aliasedConnections = new ArrayList<>();
        config = Config.createConfig(new File(configPath));
        config.setDefaultRunningMode(RunningModeType.MITM);
        trace = new WorkflowTrace(aliasedConnections);
    }


    @Override
    public void connect(String alias, int port, String ip) {
        OutboundConnection connection = new OutboundConnection(alias, port, ip);
        connection.setConnectionTimeout(10000);
        aliasedConnections.add(connection);
    }

    @Override
    public void accept(String alias, int port, String ip){
        InboundConnection connection = new InboundConnection(alias, port, ip);
        connection.setConnectionTimeout(10000);
        aliasedConnections.add(connection);
    }

    @Override
    public void close(String alias) {

    }

    @Override
    public Variable recv(String alias) {
        List<ProtocolMessage> protocolMessages = new ArrayList<>();
        List<Record> recordMessages = new ArrayList<>();
        trace.addTlsAction(new ReceiveOneAction(alias, recordMessages, protocolMessages));
        return new MessageVariable(recordMessages, protocolMessages);
    }

    @Override
    public void send(String alias, Variable variable) {
        MessageVariable messageVariable = (MessageVariable) variable;
        SendAction action = new SendAction(alias, messageVariable.getProtocolMessages());
        action.setConfiguredRecords(messageVariable.getRecordMessages());
        trace.addTlsAction(action);
    }

    @Override
    public void execute(){
        state = new State(config, trace);
        executor = new DefaultWorkflowExecutor(state);
        executor.executeWorkflow();
    }
}
