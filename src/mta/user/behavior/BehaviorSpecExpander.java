package mta.user.behavior;

import mta.user.common.UserTerm;
import mta.user.valuedomain.ValueDomainsSpec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BehaviorSpecExpander {

    public List<BehaviorSpec> expand(BehaviorDeviationSpecification specification) {
        return expand(specification, null);
    }

    public List<BehaviorSpec> expand(BehaviorDeviationSpecification specification,
                                     ValueDomainsSpec valueDomains) {
        List<BehaviorSpec> expanded = new ArrayList<BehaviorSpec>();
        for (BehaviorSpec behaviorSpec : specification.getBehaviorSpecs()) {
            expanded.addAll(expand(behaviorSpec, valueDomains));
        }
        return expanded;
    }

    public List<BehaviorSpec> expand(BehaviorSpec behaviorSpec) {
        return expand(behaviorSpec, null);
    }

    public List<BehaviorSpec> expand(BehaviorSpec behaviorSpec, ValueDomainsSpec valueDomains) {
        validateDeclaredParameters(behaviorSpec);

        if (behaviorSpec.getParameters().isEmpty()) {
            return List.of(behaviorSpec);
        }
        if (!behaviorSpec.getParameterInstances().isEmpty()) {
            return expandLegacyParameterInstances(behaviorSpec);
        }
        if (valueDomains != null && !valueDomains.instancesFor(behaviorSpec.getBehaviorId()).isEmpty()) {
            return expandValueDomainInstances(behaviorSpec, valueDomains);
        }
        if (valueDomains != null && hasTypedParameters(behaviorSpec)) {
            return expandTypedDomains(behaviorSpec, valueDomains);
        }
        if (behaviorSpec.getParameterInstances().isEmpty()) {
            throw new IllegalArgumentException("Parameterized behavior has no ParameterInstances: "
                    + behaviorSpec.getBehaviorId());
        }
        return List.of(behaviorSpec);
    }

    private List<BehaviorSpec> expandLegacyParameterInstances(BehaviorSpec behaviorSpec) {
        List<BehaviorSpec> expanded = new ArrayList<BehaviorSpec>();
        for (BehaviorParameterInstance instance : behaviorSpec.getParameterInstances()) {
            validateInstanceBindings(behaviorSpec, instance);
            for (Map<String, UserTerm> binding : expandOneOfBindings(instance.getBindings())) {
                expanded.add(substitute(behaviorSpec, binding));
            }
        }
        return expanded;
    }

    private List<BehaviorSpec> expandValueDomainInstances(BehaviorSpec behaviorSpec,
                                                          ValueDomainsSpec valueDomains) {
        List<BehaviorSpec> expanded = new ArrayList<BehaviorSpec>();
        Set<String> declared = new LinkedHashSet<String>(behaviorSpec.getParameters());
        for (Map<String, UserTerm> binding : valueDomains.instancesFor(behaviorSpec.getBehaviorId())) {
            validateBindings(behaviorSpec, declared, binding.keySet(), "ValueDomains Instances");
            expanded.add(substitute(behaviorSpec, binding));
        }
        return expanded;
    }

    private List<BehaviorSpec> expandTypedDomains(BehaviorSpec behaviorSpec,
                                                  ValueDomainsSpec valueDomains) {
        List<Map<String, UserTerm>> bindings = new ArrayList<Map<String, UserTerm>>();
        bindings.add(new LinkedHashMap<String, UserTerm>());
        for (BehaviorParameter parameter : behaviorSpec.getParameterDeclarations()) {
            if (!parameter.hasType()) {
                throw new IllegalArgumentException("Behavior parameter has no type declaration in "
                        + behaviorSpec.getBehaviorId()
                        + ": "
                        + parameter.name());
            }
            List<Map<String, UserTerm>> next = new ArrayList<Map<String, UserTerm>>();
            for (Map<String, UserTerm> current : bindings) {
                List<UserTerm> values = resolveDomainValues(
                        behaviorSpec.getBehaviorId(),
                        parameter,
                        current,
                        valueDomains
                );
                for (UserTerm value : values) {
                    Map<String, UserTerm> copy = new LinkedHashMap<String, UserTerm>(current);
                    copy.put(parameter.name(), value);
                    next.add(copy);
                }
            }
            bindings = next;
        }

        List<BehaviorSpec> expanded = new ArrayList<BehaviorSpec>();
        for (Map<String, UserTerm> binding : bindings) {
            expanded.add(substitute(behaviorSpec, binding));
        }
        return expanded;
    }

    private boolean hasTypedParameters(BehaviorSpec behaviorSpec) {
        for (BehaviorParameter parameter : behaviorSpec.getParameterDeclarations()) {
            if (parameter.hasType()) {
                return true;
            }
        }
        return false;
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
        validateBindings(behaviorSpec, declared, bound, "ParameterInstances");
    }

    private void validateBindings(BehaviorSpec behaviorSpec,
                                  Set<String> declared,
                                  Set<String> bound,
                                  String sourceName) {
        if (!bound.equals(declared)) {
            Set<String> missing = new LinkedHashSet<String>(declared);
            missing.removeAll(bound);
            Set<String> extra = new LinkedHashSet<String>(bound);
            extra.removeAll(declared);
            throw new IllegalArgumentException("Invalid "
                    + sourceName
                    + " binding for "
                    + behaviorSpec.getBehaviorId()
                    + ": missing="
                    + missing
                    + ", extra="
                    + extra);
        }
    }

    private List<UserTerm> resolveDomainValues(String behaviorId,
                                               BehaviorParameter parameter,
                                               Map<String, UserTerm> currentBindings,
                                               ValueDomainsSpec valueDomains) {
        String domainName = resolveDomainName(behaviorId, parameter, currentBindings, valueDomains);
        List<UserTerm> values = valueDomains.domain(domainName);
        if (values == null) {
            throw new IllegalArgumentException("Unknown value domain in "
                    + behaviorId
                    + " for $"
                    + parameter.name()
                    + ": "
                    + domainName);
        }
        if (values.isEmpty()) {
            throw new IllegalArgumentException("Empty value domain in "
                    + behaviorId
                    + " for $"
                    + parameter.name()
                    + ": "
                    + domainName);
        }
        return values;
    }

    private String resolveDomainName(String behaviorId,
                                     BehaviorParameter parameter,
                                     Map<String, UserTerm> currentBindings,
                                     ValueDomainsSpec valueDomains) {
        UserTerm type = parameter.typeExpression();
        if (type instanceof UserTerm.Atom atom) {
            return atom.text();
        }
        if (type instanceof UserTerm.Call call && "#getType".equals(call.name())) {
            if (call.arguments().size() != 1 || !(call.arguments().get(0) instanceof UserTerm.Parameter ref)) {
                throw new IllegalArgumentException("#getType requires one parameter reference in "
                        + behaviorId
                        + " for $"
                        + parameter.name());
            }
            UserTerm bound = currentBindings.get(ref.name());
            if (bound == null) {
                throw new IllegalArgumentException("#getType references unbound parameter in "
                        + behaviorId
                        + " for $"
                        + parameter.name()
                        + ": $"
                        + ref.name());
            }
            String mapped = valueDomains.mappedDomain("getType", bound.source());
            if (mapped == null) {
                throw new IllegalArgumentException("No #getType mapping in "
                        + behaviorId
                        + " for "
                        + bound.source());
            }
            return mapped;
        }
        throw new IllegalArgumentException("Unsupported value-domain type expression in "
                + behaviorId
                + " for $"
                + parameter.name()
                + ": "
                + type.source());
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
