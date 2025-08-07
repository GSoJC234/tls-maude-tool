package scenario;

import de.rub.nds.protocol.constants.SignatureAlgorithm;
import de.rub.nds.tlsattacker.core.constants.*;
import de.rub.nds.tlsattacker.core.crypto.KeyShareCalculator;
import de.rub.nds.tlsattacker.core.crypto.MessageDigestCollector;
import de.rub.nds.tlsattacker.core.protocol.message.extension.*;
import de.rub.nds.tlsattacker.core.protocol.message.extension.keyshare.KeyShareEntry;
import de.rub.nds.tlsattacker.core.record.cipher.cryptohelper.KeySet;
import maude.SupportedVersion;
import org.checkerframework.checker.units.qual.K;
import protocol.TLSProtocol;
import protocol.Variable;
import de.rub.nds.tlsattacker.core.config.Config;
import de.rub.nds.tlsattacker.core.connection.AliasedConnection;
import de.rub.nds.tlsattacker.core.connection.InboundConnection;
import de.rub.nds.tlsattacker.core.connection.OutboundConnection;
import de.rub.nds.tlsattacker.core.constants.AlertLevel;
import de.rub.nds.tlsattacker.core.constants.CipherSuite;
import de.rub.nds.tlsattacker.core.constants.CompressionMethod;
import de.rub.nds.tlsattacker.core.constants.ProtocolVersion;
import de.rub.nds.tlsattacker.core.protocol.ProtocolMessage;
import de.rub.nds.tlsattacker.core.protocol.message.*;
import de.rub.nds.tlsattacker.core.protocol.message.cert.CertificateEntry;
import de.rub.nds.tlsattacker.core.record.Record;
import de.rub.nds.tlsattacker.core.state.State;
import de.rub.nds.tlsattacker.core.util.ProviderUtil;
import de.rub.nds.tlsattacker.core.workflow.DefaultWorkflowExecutor;
import de.rub.nds.tlsattacker.core.workflow.WorkflowTrace;
import de.rub.nds.tlsattacker.core.workflow.action.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.util.io.pem.PemReader;
import scenario.session.TLSClientSession;
import scenario.session.TLSServerSession;
import scenario.session.TLSSession;

import java.io.*;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class TLSAttacker {

    private Config config = null;
    private WorkflowTrace trace = null;
    private State state = null;
    private DefaultWorkflowExecutor executor = null;
    private List<AliasedConnection> aliasedConnections = null;
    private List<TLSSession> sessions = null;
    boolean isClient, isServer = false;


    public TLSAttacker(String configPath){
        ProviderUtil.addBouncyCastleProvider();
        sessions = new ArrayList<>();
        aliasedConnections = new ArrayList<>();
        config = Config.createConfig(new File(configPath));
        trace = new WorkflowTrace(aliasedConnections);
    }

    public Config getConfig(){return config; }

    public WorkflowTrace getWorkFlowTrace(){
        return trace;
    }

    public void addAliasedConnection(AliasedConnection connection){
        aliasedConnections.add(connection);
    }

    public void execute(){
        if(isServer && isClient){
            config.setDefaultRunningMode(RunningModeType.MITM);
        }else if(isServer){
            config.setDefaultRunningMode(RunningModeType.SERVER);
        }else if(isClient){
            config.setDefaultRunningMode(RunningModeType.CLIENT);
        }
        config.getDefaultClientConnection().setTimeout(30000);
        config.getDefaultServerConnection().setTimeout(30000);

        state = new State(config, trace);
        executor = new DefaultWorkflowExecutor(state);
        executor.executeWorkflow();
    }
}
