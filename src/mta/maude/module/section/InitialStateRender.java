package mta.maude.module.section;

import mta.maude.module.ModuleSectionRenderer;
import mta.user.profile.TLSProfile;
import mta.user.profile.TLSRole;
import mta.user.scenario.NodeLink;
import mta.user.scenario.ScenarioSpec;
import mta.user.scenario.property.NodeBinding;
import mta.user.scenario.property.ScenarioProperty;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class InitialStateRender implements ModuleSectionRenderer {

    @Override
    public String placeholder() {
        return "initialStates";
    }

    @Override
    public String render(ScenarioSpec scenarioSpec) {
        if (scenarioSpec.getNodeIds().isEmpty()) {
            return "  --- no node objects declared";
        }
        if (scenarioSpec.getScenarioProperties().isEmpty()) {
            return "  --- no scenario property available for initial state resolution";
        }

        ScenarioProperty firstScenarioProperty = scenarioSpec.getScenarioProperties().get(0);
        Map<String, String> nodeProfiles = resolveFirstNodeProfiles(firstScenarioProperty);
        StringJoiner joiner = new StringJoiner(System.lineSeparator());

        List<String> nodeIds = scenarioSpec.getNodeIds();
        for (int index = 0; index < nodeIds.size(); index++) {
            String nodeId = nodeIds.get(index);
            TLSProfile profile = readProfile(scenarioSpec, nodeProfiles, nodeId);
            String targetId = resolveTargetId(scenarioSpec, nodeId, nodeIds);

            joiner.add("  op " + nodeId + "obj : -> Object .");
            joiner.add("  eq " + nodeId + "obj = "
                    + renderInitialObject(nodeId, targetId, profile.getTlsRole()));
            if (index < nodeIds.size() - 1) {
                joiner.add("");
            }
        }

        return joiner.toString();
    }

    private Map<String, String> resolveFirstNodeProfiles(ScenarioProperty scenarioProperty) {
        Map<String, String> resolved = new LinkedHashMap<String, String>();
        for (NodeBinding nodeBinding : scenarioProperty.getNodeBindings()) {
            resolved.put(nodeBinding.getNodeId(), nodeBinding.getProfileAlias());
        }
        return resolved;
    }

    private TLSProfile readProfile(ScenarioSpec scenarioSpec, Map<String, String> nodeProfiles, String nodeId) {
        String profileAlias = nodeProfiles.get(nodeId);
        if (profileAlias == null) {
            throw new IllegalArgumentException("Node " + nodeId + " is not bound in the first scenario property");
        }

        TLSProfile profile = scenarioSpec.getProfile(profileAlias);
        if (profile == null) {
            throw new IllegalArgumentException("Unknown profile alias: " + profileAlias);
        }
        if (profile.getTlsRole() == null) {
            throw new IllegalArgumentException("TLS role is not set for profile: " + profileAlias);
        }
        if (profile.getTlsRole() == TLSRole.Mitm) {
            throw new IllegalArgumentException("MITM profile is not supported for initial object generation: " + profileAlias);
        }
        return profile;
    }

    private String resolveTargetId(ScenarioSpec scenarioSpec, String nodeId, List<String> nodeIds) {
        for (NodeLink nodeLink : scenarioSpec.getNodeLinks().values()) {
            if (nodeId.equals(nodeLink.getNodeId1())) {
                return nodeLink.getNodeId2();
            }
            if (nodeId.equals(nodeLink.getNodeId2())) {
                return nodeLink.getNodeId1();
            }
        }

        if (nodeIds.size() == 2) {
            return nodeIds.get(0).equals(nodeId) ? nodeIds.get(1) : nodeIds.get(0);
        }
        throw new IllegalArgumentException("Cannot resolve target node for " + nodeId);
    }

    private String renderInitialObject(String nodeId, String targetId, TLSRole tlsRole) {
        String className = tlsRole == TLSRole.Client ? "Client" : "Server";
        String roleSpecificSection = tlsRole == TLSRole.Client
                ? renderClientSpecificSection()
                : renderServerSpecificSection();

        return "< " + nodeId + " : " + className + " | target : " + targetId + "," + System.lineSeparator()
                + "              --- supported features" + System.lineSeparator()
                + "              cipherSuites : nil," + System.lineSeparator()
                + "              sessionId : noNonce," + System.lineSeparator()
                + "              publicKey : empty," + System.lineSeparator()
                + "              privateKey : empty," + System.lineSeparator()
                + "              certificates : nil," + System.lineSeparator()
                + "              compressions : nil," + System.lineSeparator()
                + "              extensions : empty," + System.lineSeparator()
                + "              pskInfo : nil," + System.lineSeparator()
                + System.lineSeparator()
                + "              --- selected features" + System.lineSeparator()
                + "              selectedVersion : noVersion," + System.lineSeparator()
                + "              selectedCipherSuite : noSuite," + System.lineSeparator()
                + "              selectedCompression : noComp," + System.lineSeparator()
                + "                    selectedPSK : noNonce," + System.lineSeparator()
                + "              selectedExtensions : empty," + System.lineSeparator()
                + "              serverRandom : noNonce," + System.lineSeparator()
                + "              clientRandom : noNonce," + System.lineSeparator()
                + "              sessionReadKey : noKey," + System.lineSeparator()
                + "              sessionWriteKey : noKey," + System.lineSeparator()
                + "                    peerCertificate : nil," + System.lineSeparator()
                + System.lineSeparator()
                + "              --- TLS 1.2-specific features" + System.lineSeparator()
                + "              masterSecret : noNonce," + System.lineSeparator()
                + "              keyExchangeGroup : noGroup," + System.lineSeparator()
                + "              keyExchangeValue : noNonce," + System.lineSeparator()
                + "              certificateType : nil," + System.lineSeparator()
                + "              certificateAlgo : nil," + System.lineSeparator()
                + "              certificateAuth : nil," + System.lineSeparator()
                + "              pendingReadKey : noKey," + System.lineSeparator()
                + "              pendingWriteKey : noKey," + System.lineSeparator()
                + System.lineSeparator()
                + "              --- TLS 1.3-specific features" + System.lineSeparator()
                + "              earlySecret : noNonce," + System.lineSeparator()
                + "              sharedSecret : noNonce," + System.lineSeparator()
                + "              handshakeSecret : noNonce," + System.lineSeparator()
                + "              applicationSecret : noNonce," + System.lineSeparator()
                + "              certificateRequestContext : noNonce," + System.lineSeparator()
                + "              keyUpdateReq : false," + System.lineSeparator()
                + "              keyUpdateWait : false," + System.lineSeparator()
                + System.lineSeparator()
                + "              --- runtime metadata" + System.lineSeparator()
                + "              --- etc" + System.lineSeparator()
                + "              transcript : empty," + System.lineSeparator()
                + "              nonceCtr : 0," + System.lineSeparator()
                + "              errorLog : noError," + System.lineSeparator()
                + System.lineSeparator()
                + roleSpecificSection;
    }

    private String renderClientSpecificSection() {
        return "              --- client specific" + System.lineSeparator()
                + "              clientState : VC-NONE," + System.lineSeparator()
                + "              certificateRequested : false," + System.lineSeparator()
                + "              newSessionTicketWait : false > .";
    }

    private String renderServerSpecificSection() {
        return "              --- server specific" + System.lineSeparator()
                + "              serverState : VS-NONE," + System.lineSeparator()
                + "              clientCertificateReq : false," + System.lineSeparator()
                + "              newSessionTicketReq : false," + System.lineSeparator()
                + "              earlyDataRequested : false," + System.lineSeparator()
                + "              postClientAuthRequested : false > .";
    }
}
