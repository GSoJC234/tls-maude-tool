package mta.maude.module;

import mta.maude.MaudeRunManifest;
import mta.user.behavior.BehaviorDeviationSpecification;
import mta.user.profile.TLSProfiles;
import mta.user.scenario.ScenarioSpec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GeneratedTestModuleSpec {

    private String moduleName = "GENERATED-SCENARIO";
    private String baseLoadPath;
    private String protocolVersion = "TLS-13";
    private String initialCwaName = "initialCWA";
    private String tlsConfigurationName = "initConf";
    private String behaviorSpecificationName = "behaviorDeviationSpecification";
    private String scenarioPropertyName = "scenarioProperty";
    private String runner = "runScenario";
    private Integer nat;
    private String expectedResultSort = "List{Scen}";
    private final List<String> extraProtectingModules = new ArrayList<String>();
    private final List<String> extraMaudeDefinitions = new ArrayList<String>();
    private TLSProfiles tlsProfiles;
    private BehaviorDeviationSpecification behaviorDeviationSpecification;
    private ScenarioSpec scenarioSpec;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = Objects.requireNonNull(moduleName, "moduleName must not be null");
    }

    public String getBaseLoadPath() {
        return baseLoadPath;
    }

    public void setBaseLoadPath(String baseLoadPath) {
        this.baseLoadPath = baseLoadPath;
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = Objects.requireNonNull(protocolVersion, "protocolVersion must not be null");
    }

    public String getInitialCwaName() {
        return initialCwaName;
    }

    public void setInitialCwaName(String initialCwaName) {
        this.initialCwaName = Objects.requireNonNull(initialCwaName, "initialCwaName must not be null");
    }

    public String getTlsConfigurationName() {
        return tlsConfigurationName;
    }

    public void setTlsConfigurationName(String tlsConfigurationName) {
        this.tlsConfigurationName = Objects.requireNonNull(tlsConfigurationName, "tlsConfigurationName must not be null");
    }

    public String getBehaviorSpecificationName() {
        return behaviorSpecificationName;
    }

    public void setBehaviorSpecificationName(String behaviorSpecificationName) {
        this.behaviorSpecificationName =
                Objects.requireNonNull(behaviorSpecificationName, "behaviorSpecificationName must not be null");
    }

    public String getScenarioPropertyName() {
        return scenarioPropertyName;
    }

    public void setScenarioPropertyName(String scenarioPropertyName) {
        this.scenarioPropertyName =
                Objects.requireNonNull(scenarioPropertyName, "scenarioPropertyName must not be null");
    }

    public String getRunner() {
        return runner;
    }

    public void setRunner(String runner) {
        this.runner = Objects.requireNonNull(runner, "runner must not be null");
    }

    public Integer getNat() {
        return nat;
    }

    public void setNat(Integer nat) {
        this.nat = nat;
    }

    public String getExpectedResultSort() {
        return expectedResultSort;
    }

    public void setExpectedResultSort(String expectedResultSort) {
        this.expectedResultSort = Objects.requireNonNull(expectedResultSort, "expectedResultSort must not be null");
    }

    public List<String> getExtraProtectingModules() {
        return Collections.unmodifiableList(extraProtectingModules);
    }

    public void setExtraProtectingModules(List<String> extraProtectingModules) {
        this.extraProtectingModules.clear();
        if (extraProtectingModules != null) {
            this.extraProtectingModules.addAll(extraProtectingModules);
        }
    }

    public void addExtraProtectingModule(String moduleName) {
        if (moduleName != null && !moduleName.isBlank()) {
            extraProtectingModules.add(moduleName);
        }
    }

    public List<String> getExtraMaudeDefinitions() {
        return Collections.unmodifiableList(extraMaudeDefinitions);
    }

    public void setExtraMaudeDefinitions(List<String> extraMaudeDefinitions) {
        this.extraMaudeDefinitions.clear();
        if (extraMaudeDefinitions != null) {
            this.extraMaudeDefinitions.addAll(extraMaudeDefinitions);
        }
    }

    public void addExtraMaudeDefinition(String definition) {
        if (definition != null && !definition.isBlank()) {
            extraMaudeDefinitions.add(definition);
        }
    }

    public TLSProfiles getTlsProfiles() {
        return tlsProfiles;
    }

    public void setTlsProfiles(TLSProfiles tlsProfiles) {
        this.tlsProfiles = tlsProfiles;
    }

    public BehaviorDeviationSpecification getBehaviorDeviationSpecification() {
        return behaviorDeviationSpecification;
    }

    public void setBehaviorDeviationSpecification(BehaviorDeviationSpecification behaviorDeviationSpecification) {
        this.behaviorDeviationSpecification = behaviorDeviationSpecification;
    }

    public ScenarioSpec getScenarioSpec() {
        return scenarioSpec;
    }

    public void setScenarioSpec(ScenarioSpec scenarioSpec) {
        this.scenarioSpec = scenarioSpec;
    }

    public MaudeRunManifest toRunManifest() {
        MaudeRunManifest manifest = new MaudeRunManifest();
        manifest.setRunner(runner);
        manifest.setProtocolVersion(protocolVersion);
        manifest.setInitialCwa(initialCwaName);
        manifest.setTlsConfiguration(tlsConfigurationName);
        manifest.setBehaviorSpec(behaviorSpecificationName);
        manifest.setScenarioProperty(scenarioPropertyName);
        manifest.setNat(nat);
        manifest.setExpectedResultSort(expectedResultSort);
        return manifest;
    }
}
