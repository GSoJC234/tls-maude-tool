package mta.user.behavior;

import mta.user.common.ActionExpression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BehaviorSpec {

    private String behaviorId;
    private final List<String> parameters;
    private final List<BehaviorParameter> parameterDeclarations;
    private ActionExpression actionCondition;
    private final List<BehaviorModificationSpec> modificationSpecs;
    private final List<BehaviorParameterInstance> parameterInstances;

    public BehaviorSpec() {
        this.parameters = new ArrayList<String>();
        this.parameterDeclarations = new ArrayList<BehaviorParameter>();
        this.modificationSpecs = new ArrayList<BehaviorModificationSpec>();
        this.parameterInstances = new ArrayList<BehaviorParameterInstance>();
    }

    public String getBehaviorId() {
        return behaviorId;
    }

    public void setBehaviorId(String behaviorId) {
        this.behaviorId = behaviorId;
    }

    public List<String> getParameters() {
        return Collections.unmodifiableList(parameters);
    }

    public void setParameters(List<String> parameters) {
        this.parameters.clear();
        this.parameterDeclarations.clear();
        if (parameters != null) {
            this.parameters.addAll(parameters);
            for (String parameter : parameters) {
                this.parameterDeclarations.add(new BehaviorParameter(parameter, null));
            }
        }
    }

    public void addParameter(String parameter) {
        if (parameter != null) {
            this.parameters.add(parameter);
            this.parameterDeclarations.add(new BehaviorParameter(parameter, null));
        }
    }

    public List<BehaviorParameter> getParameterDeclarations() {
        return Collections.unmodifiableList(parameterDeclarations);
    }

    public void setParameterDeclarations(List<BehaviorParameter> declarations) {
        this.parameters.clear();
        this.parameterDeclarations.clear();
        if (declarations != null) {
            for (BehaviorParameter declaration : declarations) {
                if (declaration != null) {
                    this.parameters.add(declaration.name());
                    this.parameterDeclarations.add(declaration);
                }
            }
        }
    }

    public ActionExpression getActionCondition() {
        return actionCondition;
    }

    public void setActionCondition(ActionExpression actionCondition) {
        this.actionCondition = actionCondition;
    }

    public List<BehaviorModificationSpec> getModificationSpecs() {
        return Collections.unmodifiableList(modificationSpecs);
    }

    public void setModificationSpecs(List<BehaviorModificationSpec> modificationSpecs) {
        this.modificationSpecs.clear();
        if (modificationSpecs != null) {
            this.modificationSpecs.addAll(modificationSpecs);
        }
    }

    public void addModificationSpec(BehaviorModificationSpec modificationSpec) {
        if (modificationSpec != null) {
            modificationSpecs.add(modificationSpec);
        }
    }

    public List<BehaviorParameterInstance> getParameterInstances() {
        return Collections.unmodifiableList(parameterInstances);
    }

    public void setParameterInstances(List<BehaviorParameterInstance> parameterInstances) {
        this.parameterInstances.clear();
        if (parameterInstances != null) {
            this.parameterInstances.addAll(parameterInstances);
        }
    }

    public void addParameterInstance(BehaviorParameterInstance parameterInstance) {
        if (parameterInstance != null) {
            parameterInstances.add(parameterInstance);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BehaviorSpec that)) {
            return false;
        }
        return Objects.equals(behaviorId, that.behaviorId)
                && Objects.equals(parameters, that.parameters)
                && Objects.equals(parameterDeclarations, that.parameterDeclarations)
                && Objects.equals(actionCondition, that.actionCondition)
                && Objects.equals(modificationSpecs, that.modificationSpecs)
                && Objects.equals(parameterInstances, that.parameterInstances);
    }

    @Override
    public int hashCode() {
        return Objects.hash(behaviorId, parameters, parameterDeclarations, actionCondition, modificationSpecs,
                parameterInstances);
    }

    @Override
    public String toString() {
        return "BehaviorSpec{"
                + "behaviorId='" + behaviorId + '\''
                + ", parameters=" + parameters
                + ", parameterDeclarations=" + parameterDeclarations
                + ", actionCondition=" + actionCondition
                + ", modificationSpecs=" + modificationSpecs
                + ", parameterInstances=" + parameterInstances
                + '}';
    }
}
