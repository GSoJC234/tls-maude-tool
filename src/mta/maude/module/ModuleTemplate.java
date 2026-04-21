package mta.maude.module;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ModuleTemplate {

    private final String source;
    private final Map<String, String> bindings;

    public ModuleTemplate(String source) {
        this.source = Objects.requireNonNull(source, "source must not be null");
        this.bindings = new LinkedHashMap<String, String>();
    }

    public static ModuleTemplate base() {
        return new ModuleTemplate("""
                smod GENERATED-SCENARIO is
                  protecting TEST-TLS-KEY .
                  protecting TEST-TLS-CERTIFICATE .
                  protecting RUN-SCENARIO .
                  protecting INTERNAL-FORMAL-SCENARIO-GENERATOR .
                  protecting SCENARIO-BLOCK .
                  protecting APPLY-BEHAVIOR-INSTANCE .
                  
                  --- Loaded TLS profiles
                ${profiles}

                  --- Scenario nodes
                ${nodes}

                  --- Node initial states
                ${initialStates}

                  --- Constant declarations
                ${constants}

                  --- State propositions
                ${statePropositions}

                  --- Action propositions
                ${actionPropositions}

                  --- Parameterized Behavior Instances
                ${parameterizedBehaviorInstances}

                  --- Concrete Behavior Instances
                ${concreteBehaviorInstances}

                  --- Scenario properties
                ${scenarioProperties}
                endsm
                """);
    }

    public ModuleTemplate bind(String placeholder, String value) {
        bindings.put(
                Objects.requireNonNull(placeholder, "placeholder must not be null"),
                value != null ? value : ""
        );
        return this;
    }

    public ModuleTemplate copy() {
        ModuleTemplate copy = new ModuleTemplate(source);
        copy.bindings.putAll(bindings);
        return copy;
    }

    public String render() {
        String rendered = source;
        for (Map.Entry<String, String> entry : bindings.entrySet()) {
            rendered = rendered.replace(toToken(entry.getKey()), entry.getValue());
        }
        return rendered;
    }

    private static String toToken(String placeholder) {
        return "${" + placeholder + "}";
    }
}
