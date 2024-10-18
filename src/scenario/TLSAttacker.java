package scenario;


import Protocol.TLSProtocol;
import Protocol.Variable;
import de.rub.nds.modifiablevariable.util.Modifiable;
import de.rub.nds.tlsattacker.core.config.Config;
import de.rub.nds.tlsattacker.core.connection.InboundConnection;
import de.rub.nds.tlsattacker.core.connection.OutboundConnection;
import de.rub.nds.tlsattacker.core.constants.RunningModeType;
import de.rub.nds.tlsattacker.core.protocol.ProtocolMessage;
import de.rub.nds.tlsattacker.core.protocol.message.ClientHelloMessage;
import de.rub.nds.tlsattacker.core.protocol.message.ServerHelloMessage;
import de.rub.nds.tlsattacker.core.state.State;
import de.rub.nds.tlsattacker.core.util.ProviderUtil;
import de.rub.nds.tlsattacker.core.workflow.DefaultWorkflowExecutor;
import de.rub.nds.tlsattacker.core.workflow.WorkflowTrace;
import de.rub.nds.tlsattacker.core.workflow.action.ReceiveAction;
import de.rub.nds.tlsattacker.core.workflow.action.ReceiveOneAction;
import de.rub.nds.tlsattacker.core.workflow.action.SendAction;
import org.xbill.DNS.Message;

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
    private List<ProtocolMessage> receivedMessage = null;


    public TLSAttacker(String configPath){
        ProviderUtil.addBouncyCastleProvider();
        config = Config.createConfig(new File(configPath));
        config.setDefaultRunningMode(RunningModeType.MITM);
        trace = new WorkflowTrace();
        receivedMessage = new ArrayList<>();
    }

    @Override
    public void connect(String alias) {
        OutboundConnection connection = new OutboundConnection(alias, 4433, "localhost");
        connection.setConnectionTimeout(10000);
        config.setDefaultClientConnection(connection);
    }

    @Override
    public void accept(String alias){
        InboundConnection connection = new InboundConnection(alias, 4444, "localhost");
        connection.setConnectionTimeout(10000);
        config.setDefaultServerConnection(connection);
    }

    @Override
    public void close(String alias) {

    }

    @Override
    public Variable recv(String alias) {
        List<ProtocolMessage> messages = new ArrayList<>();
        trace.addTlsAction(new ReceiveOneAction(alias, messages));
        return new MessageVariable(messages);
    }

    @Override
    public void send(String alias, Variable variable) {
        MessageVariable messageVariable = (MessageVariable) variable;
        trace.addTlsAction(new SendAction(alias, messageVariable.get()));
    }

    @Override
    public void execute(){
        state = new State(config, trace);
        executor = new DefaultWorkflowExecutor(state);
        executor.executeWorkflow();
    }

    private ProtocolMessage unsetPreparation(ProtocolMessage message){
        message.setShouldPrepareDefault(false);
        return message;
    }
}
