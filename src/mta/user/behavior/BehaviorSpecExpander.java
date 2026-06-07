package mta.user.behavior;

import mta.user.common.UserTerm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BehaviorSpecExpander {

    public List<BehaviorSpec> expand(BehaviorDeviationSpecification specification) {
        List<BehaviorSpec> expanded = new ArrayList<BehaviorSpec>();
        for (BehaviorSpec behaviorSpec : specification.getBehaviorSpecs()) {
            expanded.addAll(expand(behaviorSpec));
        }
        return expanded;
    }

    public List<BehaviorSpec> expand(BehaviorSpec behaviorSpec) {
        validateDeclaredParameters(behaviorSpec);

        if (behaviorSpec.getParameters().isEmpty()) {
            return List.of(behaviorSpec);
        }
        if (behaviorSpec.getParameterInstances().isEmpty()) {
            throw new IllegalArgumentException("Parameterized behavior has no ParameterInstances: "
                    + behaviorSpec.getBehaviorId());
        }

        List<BehaviorSpec> expanded = new ArrayList<BehaviorSpec>();
        for (BehaviorParameterInstance instance : behaviorSpec.getParameterInstances()) {
            validateInstanceBindings(behaviorSpec, instance);
            for (Map<String, UserTerm> binding : expandOneOfBindings(instance.getBindings())) {
                expanded.add(substitute(behaviorSpec, binding));
            }
        }
        return expanded;
    }

    private void validateDeclaredParameters(BehaviorSpec behaviorSpec) {
        Set<String> declared = new LinkedHashSet<String>(behaviorSpec.getParameters());
        if (declared.size() != behaviorSpec.getParameters().size()) {
            throw new IllegalArgumentException("Duplicate parameter in behavior: "
                    + behaviorSpec.getBehaviorId());
        }

        Set<String> used = new LinkedHashSet<String>();
        if (behaviorSpec.getActionCondition() != null) {
            used.addAll(behaviorSpec.getActionCondition().parameterRefs());
        }
        for (BehaviorModificationSpec modification : behaviorSpec.getModificationSpecs()) {
            used.addAll(modification.parameterRefs());
        }
        if (!declared.containsAll(used)) {
            Set<String> undeclared = new LinkedHashSet<String>(used);
            undeclared.removeAll(declared);
            throw new IllegalArgumentException("Undeclared behavior parameters in "
                    + behaviorSpec.getBehaviorId()
                    + ": "
                    + undeclared);
        }
    }

    private void validateInstanceBindings(BehaviorSpec behaviorSpec, BehaviorParameterInstance instance) {
        Set<String> declared = new LinkedHashSet<String>(behaviorSpec.getParameters());
        Set<String> bound = instance.getBindings().keySet();
        if (!bound.equals(declared)) {
            Set<String> missing = new LinkedHashSet<String>(declared);
            missing.removeAll(bound);
            Set<String> extra = new LinkedHashSet<String>(bound);
            extra.removeAll(declared);
            throw new IllegalArgumentException("Invalid ParameterInstances binding for "
                    + behaviorSpec.getBehaviorId()
                    + ": missing="
                    + missing
                    + ", extra="
                    + extra);
        }
    }

    private List<Map<String, UserTerm>> expandOneOfBindings(Map<String, UserTerm> bindings) {
        List<Map<String, UserTerm>> expanded = new ArrayList<Map<String, UserTerm>>();
        expanded.add(new LinkedHashMap<String, UserTerm>());

        for (Map.Entry<String, UserTerm> binding : bindings.entrySet()) {
            List<UserTerm> choices = expandOneOf(binding.getValue());
            List<Map<String, UserTerm>> next = new ArrayList<Map<String, UserTerm>>();
            for (Map<String, UserTerm> current : expanded) {
                for (UserTerm choice : choices) {
                    Map<String, UserTerm> copy = new LinkedHashMap<String, UserTerm>(current);
                    copy.put(binding.getKey(), choice);
                    next.add(copy);
                }
            }
            expanded = next;
        }

        return expanded;
    }

    private List<UserTerm> expandOneOf(UserTerm value) {
        if (value instanceof UserTerm.Call call && "oneOf".equals(call.name())) {
            if (call.arguments().isEmpty()) {
                throw new IllegalArgumentException("oneOf requires at least one value");
            }
            return call.arguments();
        }
        return List.of(value);
    }

    private BehaviorSpec substitute(BehaviorSpec template, Map<String, UserTerm> bindings) {
        BehaviorSpec concrete = new BehaviorSpec();
        concrete.setBehaviorId(template.getBehaviorId());
        concrete.setActionCondition(template.getActionCondition().substitute(bindings));
        List<BehaviorModificationSpec> modifications = new ArrayList<BehaviorModificationSpec>();
        for (BehaviorModificationSpec modification : template.getModificationSpecs()) {
            modifications.add(modification.substitute(bindings));
        }
        concrete.setModificationSpecs(modifications);
        return concrete;
    }
}
