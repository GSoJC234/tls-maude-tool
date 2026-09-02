package mta.maude.module;

import mta.maude.module.renderer.BehaviorDeviationSpecificationRenderer;
import mta.maude.module.renderer.ScenarioPropertyRenderer;
import mta.maude.module.renderer.TlsConfigurationRenderer;

import java.util.StringJoiner;

public class GeneratedTestModuleRenderer {

    private final TlsConfigurationRenderer tlsConfigurationRenderer;
    private final BehaviorDeviationSpecificationRenderer behaviorRenderer;
    private final ScenarioPropertyRenderer scenarioPropertyRenderer;

    public GeneratedTestModuleRenderer() {
        this(new TlsConfigurationRenderer(),
                new BehaviorDeviationSpecificationRenderer(),
                new ScenarioPropertyRenderer());
    }

    public GeneratedTestModuleRenderer(TlsConfigurationRenderer tlsConfigurationRenderer,
                                       BehaviorDeviationSpecificationRenderer behaviorRenderer,
                                       ScenarioPropertyRenderer scenarioPropertyRenderer) {
        this.tlsConfigurationRenderer = tlsConfigurationRenderer;
        this.behaviorRenderer = behaviorRenderer;
        this.scenarioPropertyRenderer = scenarioPropertyRenderer;
    }

    public String render(GeneratedTestModuleSpec spec) {
        validate(spec);

        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        if (spec.getBaseLoadPath() != null && !spec.getBaseLoadPath().isBlank()) {
            joiner.add("load " + spec.getBaseLoadPath());
            joiner.add("");
        }
        joiner.add("smod " + spec.getModuleName() + " is");
        joiner.add("  protecting RUN-SCENARIO .");
        joiner.add("  protecting TLS-INITIAL-SYSTEM .");
        joiner.add("  protecting TLS-ALL-VALUES .");
        joiner.add("  protecting SET{BehaviorDVSpec} .");
        for (String extraModule : spec.getExtraProtectingModules()) {
            joiner.add("  protecting " + extraModule + " .");
        }
        joiner.add("");
        joiner.add(tlsConfigurationRenderer.renderEq(spec.getTlsConfigurationName(), spec.getTlsProfiles()));
        joiner.add("");
        joiner.add("  op " + spec.getInitialCwaName() + " : -> ConfWithAction .");
        joiner.add("  eq " + spec.getInitialCwaName() + " = {system | nil : nil} .");
        joiner.add("");
        for (String definition : spec.getExtraMaudeDefinitions()) {
            joiner.add(indentDefinition(definition));
            joiner.add("");
        }
        joiner.add(behaviorRenderer.renderEq(
                spec.getBehaviorSpecificationName(),
                spec.getBehaviorDeviationSpecification(),
                spec.getValueDomainsSpec()
        ));
        joiner.add("");
        joiner.add(scenarioPropertyRenderer.renderEq(spec.getScenarioPropertyName(), spec.getScenarioSpec()));
        joiner.add("endsm");
        return joiner.toString();
    }

    public String renderReductionCommand(GeneratedTestModuleSpec spec) {
        validate(spec);
        return spec.toRunManifest().toReductionCommand();
    }

    private void validate(GeneratedTestModuleSpec spec) {
        if (spec.getTlsProfiles() == null) {
            throw new IllegalArgumentException("Generated module spec has no TLSProfiles");
        }
        if (spec.getBehaviorDeviationSpecification() == null) {
            throw new IllegalArgumentException("Generated module spec has no behavior deviation specification");
        }
        if (spec.getScenarioSpec() == null) {
            throw new IllegalArgumentException("Generated module spec has no scenario property");
        }
        if (!"runScenario".equals(spec.getRunner()) && !"runScenarioCWA".equals(spec.getRunner())) {
            throw new IllegalArgumentException("Unsupported runner: " + spec.getRunner());
        }
    }

    private String indentDefinition(String definition) {
        String[] lines = definition.split("\\R", -1);
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        for (String line : lines) {
            if (line.isBlank()) {
                joiner.add("");
            } else if (line.startsWith("  ")) {
                joiner.add(line);
            } else {
                joiner.add("  " + line);
            }
        }
        return joiner.toString();
    }
}
