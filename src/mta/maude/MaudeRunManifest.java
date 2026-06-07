package mta.maude;

import java.util.Objects;
import java.util.StringJoiner;

public class MaudeRunManifest {

    private String runner = "runScenario";
    private String protocolVersion = "TLS-13";
    private String initialCwa = "initialCWA";
    private String tlsConfiguration = "initConf";
    private String behaviorSpec = "behaviorDeviationSpecification";
    private String scenarioProperty = "scenarioProperty";
    private Integer nat;
    private String expectedResultSort = "List{Scen}";

    public String getRunner() {
        return runner;
    }

    public void setRunner(String runner) {
        this.runner = Objects.requireNonNull(runner, "runner must not be null");
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = Objects.requireNonNull(protocolVersion, "protocolVersion must not be null");
    }

    public String getInitialCwa() {
        return initialCwa;
    }

    public void setInitialCwa(String initialCwa) {
        this.initialCwa = Objects.requireNonNull(initialCwa, "initialCwa must not be null");
    }

    public String getTlsConfiguration() {
        return tlsConfiguration;
    }

    public void setTlsConfiguration(String tlsConfiguration) {
        this.tlsConfiguration = Objects.requireNonNull(tlsConfiguration, "tlsConfiguration must not be null");
    }

    public String getBehaviorSpec() {
        return behaviorSpec;
    }

    public void setBehaviorSpec(String behaviorSpec) {
        this.behaviorSpec = Objects.requireNonNull(behaviorSpec, "behaviorSpec must not be null");
    }

    public String getScenarioProperty() {
        return scenarioProperty;
    }

    public void setScenarioProperty(String scenarioProperty) {
        this.scenarioProperty = Objects.requireNonNull(scenarioProperty, "scenarioProperty must not be null");
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

    public String toReductionCommand() {
        if (!"runScenario".equals(runner) && !"runScenarioCWA".equals(runner)) {
            throw new IllegalArgumentException("Unsupported Maude runner: " + runner);
        }

        StringJoiner arguments = new StringJoiner(", ");
        arguments.add(protocolVersion);
        arguments.add(initialCwa);
        arguments.add(tlsConfiguration);
        arguments.add(behaviorSpec);
        arguments.add(scenarioProperty);
        if (nat != null) {
            arguments.add(String.valueOf(nat));
        }
        return "red " + runner + "(" + arguments + ") .";
    }
}
