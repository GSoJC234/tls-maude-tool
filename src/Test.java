import Parser.DefaultScenarioVisitor;
import Parser.ScenarioLexer;
import Parser.ScenarioParser;
import Scenario.Scenario;
import de.rub.nds.modifiablevariable.util.Modifiable;
import de.rub.nds.tlsattacker.core.config.Config;
import de.rub.nds.tlsattacker.core.connection.InboundConnection;
import de.rub.nds.tlsattacker.core.constants.CipherSuite;
import de.rub.nds.tlsattacker.core.constants.RunningModeType;
import de.rub.nds.tlsattacker.core.protocol.message.ClientHelloMessage;
import de.rub.nds.tlsattacker.core.protocol.message.ServerHelloMessage;
import de.rub.nds.tlsattacker.core.protocol.preparator.ServerHelloPreparator;
import de.rub.nds.tlsattacker.core.state.State;
import de.rub.nds.tlsattacker.core.util.ProviderUtil;
import de.rub.nds.tlsattacker.core.workflow.DefaultWorkflowExecutor;
import de.rub.nds.tlsattacker.core.workflow.WorkflowExecutorFactory;
import de.rub.nds.tlsattacker.core.workflow.WorkflowTrace;
import de.rub.nds.tlsattacker.core.workflow.WorkflowTraceMutator;
import de.rub.nds.tlsattacker.core.workflow.action.ReceiveAction;
import de.rub.nds.tlsattacker.core.workflow.action.SendAction;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args){

        Scenario scenario = null;
        try {
            CharStream charStream = CharStreams.fromFileName("/home/jaehun/maude-tls-attacker/test/test.slog");
            ScenarioLexer lexer = new ScenarioLexer(charStream);
            ScenarioParser parser = new ScenarioParser(new CommonTokenStream(lexer));

            DefaultScenarioVisitor visitor = new DefaultScenarioVisitor();
            scenario = visitor.visitScenario(parser.scenario());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        ProviderUtil.addBouncyCastleProvider();

        Config config = Config.createConfig(new File("/home/jaehun/maude-tls-attacker/resources/default_config.xml"));
        config.setDefaultRunningMode(RunningModeType.SERVER);
        config.setDefaultServerConnection(new InboundConnection("server", 4433, "localhost"));

        WorkflowTrace trace = new WorkflowTrace();
        State state = new State(config, trace);
        MaudeWorkflowExecutor executor = new MaudeWorkflowExecutor(state);
        executor.executeWorkflow();

        DefaultWorkflowExecutor executor2 = new DefaultWorkflowExecutor(state);

        ClientHelloMessage clientHelloMessage = new ClientHelloMessage();
        ReceiveAction action1 = new ReceiveAction("server", clientHelloMessage);
        executor.executeAction(action1);

        ServerHelloMessage serverHelloMessage = new ServerHelloMessage();
        serverHelloMessage.setSelectedCipherSuite(Modifiable.explicit(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM.getByteValue()));
        SendAction action2 = new SendAction("server", serverHelloMessage);
        executor.executeAction(action2);

        executor.finishWorkflow();


    }
}
