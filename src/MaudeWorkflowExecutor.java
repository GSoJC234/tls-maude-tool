import de.rub.nds.protocol.exception.WorkflowExecutionException;
import de.rub.nds.tlsattacker.core.state.Context;
import de.rub.nds.tlsattacker.core.state.State;
import de.rub.nds.tlsattacker.core.workflow.WorkflowExecutor;
import de.rub.nds.tlsattacker.core.workflow.WorkflowTrace;
import de.rub.nds.tlsattacker.core.workflow.action.TlsAction;
import de.rub.nds.tlsattacker.core.workflow.action.executor.WorkflowExecutorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class MaudeWorkflowExecutor extends WorkflowExecutor {

    private boolean isWorkflowExecuted = false;
    private static final Logger LOGGER = LogManager.getLogger();

    public MaudeWorkflowExecutor(State state) {
        super(WorkflowExecutorType.DEFAULT, state);
    }

    @Override
    public void executeWorkflow() throws WorkflowExecutionException {

        if(config.isWorkflowExecutorShouldOpen()){
            try{
                initAllLayer();
            } catch (IOException e) {
                throw new WorkflowExecutionException("Workflow not executed, could not initialize transport handler: ", e);
            }
        }
        state.getWorkflowTrace().reset();
        state.setStartTimestamp(System.currentTimeMillis());
        isWorkflowExecuted = true;
    }

    public void executeAction(TlsAction action) throws WorkflowExecutionException {
        if(!isWorkflowExecuted) {
            throw new WorkflowExecutionException("Workflow not executed: ");
        }
        this.executeAction(action, state);
    }

    public void finishWorkflow(){
        isWorkflowExecuted = false;

        if (config.isFinishWithCloseNotify()) {
            for (Context context : state.getAllContexts()) {
                sendCloseNotify(context.getTlsContext());
            }
        }
        setFinalSocketState();
        closeConnection();
    }
}
