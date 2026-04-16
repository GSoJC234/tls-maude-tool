package mta.user.scenario;

import mta.user.behavior.BehaviorSpec;
import mta.user.profile.TLSProfile;
import mta.user.scenario.property.ScenarioProperty;
import mta.user.scenario.state.StateProposition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ScenarioSpec {

    private final Map<String, TLSProfile> profiles;
    private final List<BehaviorSpec> behaviorSpecs;
    private final List<String> nodeIds;
    private final Map<String, Set<String>> constants;
    private final Map<String, StateProposition> statePropositions;
    private final Map<String, ActionProposition> actionPropositions;
    private final List<ScenarioProperty> scenarioProperties;

    public ScenarioSpec() {
        profiles = new LinkedHashMap<String, TLSProfile>();
        behaviorSpecs = new ArrayList<BehaviorSpec>();
        nodeIds = new ArrayList<String>();
        constants = new LinkedHashMap<String, Set<String>>();
        statePropositions = new LinkedHashMap<String, StateProposition>();
        actionPropositions = new LinkedHashMap<String, ActionProposition>();
        scenarioProperties = new ArrayList<ScenarioProperty>();
    }

    public Map<String, TLSProfile> getProfiles() {
        return Collections.unmodifiableMap(profiles);
    }

    public void setProfiles(Map<String, TLSProfile> profiles) {
        this.profiles.clear();
        if (profiles != null) {
            this.profiles.putAll(profiles);
        }
    }

    public void addProfile(String alias, TLSProfile profile) {
        this.profiles.put(alias, profile);
    }

     public TLSProfile getProfile(String alias) {
        return profiles.get(alias);
    }

    public List<BehaviorSpec> getBehaviorSpecs() {
        return Collections.unmodifiableList(behaviorSpecs);
    }

    public List<BehaviorSpec> getBehaviorSpec() {
        return getBehaviorSpecs();
    }

    public void setBehaviorSpecs(List<BehaviorSpec> behaviorSpecs) {
        this.behaviorSpecs.clear();
        if (behaviorSpecs != null) {
            this.behaviorSpecs.addAll(behaviorSpecs);
        }
    }

    public void addBehaviorSpec(BehaviorSpec behaviorSpec) {
        if (behaviorSpec != null) {
            this.behaviorSpecs.add(behaviorSpec);
        }
    }

    public List<String> getNodeIds() {
        return Collections.unmodifiableList(nodeIds);
    }

    public void setNodeIds(List<String> nodeIds) {
        this.nodeIds.clear();
        if (nodeIds != null) {
            this.nodeIds.addAll(nodeIds);
        }
    }

    public void addNodeId(String nodeId) {
        if (nodeId != null) {
            this.nodeIds.add(nodeId);
        }
    }

    public Map<String, Set<String>> getConstants() {
        Map<String, Set<String>> view = new LinkedHashMap<String, Set<String>>();
        for (Map.Entry<String, Set<String>> entry : constants.entrySet()) {
            view.put(entry.getKey(), Collections.unmodifiableSet(entry.getValue()));
        }
        return Collections.unmodifiableMap(view);
    }

    public void setConstants(Map<String, Set<String>> constants) {
        this.constants.clear();
        if (constants == null) {
            return;
        }
        for (Map.Entry<String, Set<String>> entry : constants.entrySet()) {
            addConstant(entry.getKey(), entry.getValue());
        }
    }

    public Set<String> getConstant(String constantId) {
        Set<String> values = constants.get(constantId);
        return values != null ? Collections.unmodifiableSet(values) : null;
    }

    public void addConstant(String constantId, Set<String> values) {
        if (constantId == null) {
            return;
        }
        Set<String> copy = new LinkedHashSet<String>();
        if (values != null) {
            copy.addAll(values);
        }
        this.constants.put(constantId, copy);
    }

    public void addConstantValue(String constantId, String value) {
        if (constantId == null || value == null) {
            return;
        }
        this.constants.computeIfAbsent(constantId, ignored -> new LinkedHashSet<String>()).add(value);
    }

    public Map<String, StateProposition> getStatePropositions() {
        return Collections.unmodifiableMap(statePropositions);
    }

    public void setStatePropositions(Map<String, StateProposition> statePropositions) {
        this.statePropositions.clear();
        if (statePropositions != null) {
            this.statePropositions.putAll(statePropositions);
        }
    }

    public StateProposition getStateProposition(String propositionId) {
        return statePropositions.get(propositionId);
    }

    public void addStateProposition(String propositionId, StateProposition stateProposition) {
        if (propositionId == null || stateProposition == null) {
            return;
        }
        this.statePropositions.put(propositionId, stateProposition);
    }

    public Map<String, ActionProposition> getActionPropositions() {
        return Collections.unmodifiableMap(actionPropositions);
    }

    public void setActionPropositions(Map<String, ActionProposition> actionPropositions) {
        this.actionPropositions.clear();
        if (actionPropositions != null) {
            this.actionPropositions.putAll(actionPropositions);
        }
    }

    public ActionProposition getActionProposition(String propositionId) {
        return actionPropositions.get(propositionId);
    }

    public void addActionProposition(String propositionId, ActionProposition actionProposition) {
        if (propositionId == null || actionProposition == null) {
            return;
        }
        this.actionPropositions.put(propositionId, actionProposition);
    }

    public List<ScenarioProperty> getScenarioProperties() {
        return Collections.unmodifiableList(scenarioProperties);
    }

    public void setScenarioProperties(List<ScenarioProperty> scenarioProperties) {
        this.scenarioProperties.clear();
        if (scenarioProperties != null) {
            this.scenarioProperties.addAll(scenarioProperties);
        }
    }

    public void addScenarioProperty(ScenarioProperty scenarioProperty) {
        if (scenarioProperty != null) {
            this.scenarioProperties.add(scenarioProperty);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ScenarioSpec that)) {
            return false;
        }
        return Objects.equals(profiles, that.profiles)
                && Objects.equals(behaviorSpecs, that.behaviorSpecs)
                && Objects.equals(nodeIds, that.nodeIds)
                && Objects.equals(constants, that.constants)
                && Objects.equals(statePropositions, that.statePropositions)
                && Objects.equals(actionPropositions, that.actionPropositions)
                && Objects.equals(scenarioProperties, that.scenarioProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profiles, behaviorSpecs, nodeIds,
                constants, statePropositions, actionPropositions, scenarioProperties);
    }

    @Override
    public String toString() {
        return "ScenarioSpec{"
                + "profiles=" + profiles.keySet()
                + ", behaviorSpecs=" + behaviorSpecs
                + ", nodeIds=" + nodeIds
                + ", constants=" + constants
                + ", statePropositions=" + statePropositions
                + ", actionPropositions=" + actionPropositions
                + ", scenarioProperties=" + scenarioProperties
                + '}';
    }
}
