package mta.maude.module;

import mta.user.behavior.BehaviorDeviationSpecification;
import mta.user.behavior.BehaviorSpecLoader;
import mta.user.profile.TLSProfileLoader;
import mta.user.profile.TLSProfiles;
import mta.user.scenario.ScenarioSpec;
import mta.user.scenario.ScenarioSpecLoader;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class UserScenarioModuleBuilder {

    public static final String PROFILE_FILE = "tlsprofile.dsl";
    public static final String BEHAVIOR_FILE = "behavior.dsl";
    public static final String SCENARIO_FILE = "scenario.dsl";
    public static final String MANIFEST_FILE = "manifest.properties";
    public static final String EXTRA_MAUDE_FILE = "extra.maude";

    private final TLSProfileLoader profileLoader;
    private final BehaviorSpecLoader behaviorSpecLoader;
    private final ScenarioSpecLoader scenarioSpecLoader;

    public UserScenarioModuleBuilder() {
        this(new TLSProfileLoader(), new BehaviorSpecLoader(), new ScenarioSpecLoader());
    }

    public UserScenarioModuleBuilder(TLSProfileLoader profileLoader,
                                     BehaviorSpecLoader behaviorSpecLoader,
                                     ScenarioSpecLoader scenarioSpecLoader) {
        this.profileLoader = profileLoader;
        this.behaviorSpecLoader = behaviorSpecLoader;
        this.scenarioSpecLoader = scenarioSpecLoader;
    }

    public GeneratedTestModuleSpec fromDirectory(Path caseDirectory) {
        Properties manifest = loadManifest(caseDirectory.resolve(MANIFEST_FILE));
        GeneratedTestModuleSpec spec = fromFiles(
                caseDirectory.resolve(PROFILE_FILE),
                caseDirectory.resolve(BEHAVIOR_FILE),
                caseDirectory.resolve(SCENARIO_FILE),
                manifest
        );

        Path extraMaude = caseDirectory.resolve(EXTRA_MAUDE_FILE);
        if (Files.exists(extraMaude)) {
            try {
                spec.addExtraMaudeDefinition(Files.readString(extraMaude));
            } catch (IOException e) {
                throw new RuntimeException("Failed to read extra Maude definitions: " + extraMaude, e);
            }
        }
        return spec;
    }

    public GeneratedTestModuleSpec fromFiles(Path profilePath,
                                             Path behaviorPath,
                                             Path scenarioPath,
                                             Properties manifest) {
        TLSProfiles profiles = profileLoader.loadTLSProfiles(profilePath);
        BehaviorDeviationSpecification behavior =
                behaviorSpecLoader.loadBehaviorDeviationSpecification(behaviorPath);
        ScenarioSpec scenarioSpec = scenarioSpecLoader.loadScenarioSpec(scenarioPath);

        GeneratedTestModuleSpec spec = new GeneratedTestModuleSpec();
        spec.setTlsProfiles(profiles);
        spec.setBehaviorDeviationSpecification(behavior);
        spec.setScenarioSpec(scenarioSpec);

        setIfPresent(manifest, "moduleName", spec::setModuleName);
        setIfPresent(manifest, "baseLoadPath", spec::setBaseLoadPath);
        setIfPresent(manifest, "protocolVersion", spec::setProtocolVersion);
        setIfPresent(manifest, "initialCwaName", spec::setInitialCwaName);
        setIfPresent(manifest, "tlsConfigurationName", spec::setTlsConfigurationName);
        setIfPresent(manifest, "behaviorSpecificationName", spec::setBehaviorSpecificationName);
        setIfPresent(manifest, "scenarioPropertyName", spec::setScenarioPropertyName);
        setIfPresent(manifest, "runner", spec::setRunner);
        setIfPresent(manifest, "expectedResultSort", spec::setExpectedResultSort);
        if (manifest.getProperty("nat") != null && !manifest.getProperty("nat").isBlank()) {
            spec.setNat(Integer.parseInt(manifest.getProperty("nat").trim()));
        }

        List<String> extraModules = commaSeparated(manifest.getProperty("extraProtectingModules"));
        spec.setExtraProtectingModules(extraModules);
        return spec;
    }

    private Properties loadManifest(Path manifestPath) {
        Properties properties = new Properties();
        try (Reader reader = Files.newBufferedReader(manifestPath)) {
            properties.load(reader);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read scenario manifest: " + manifestPath, e);
        }
    }

    private interface StringSetter {
        void set(String value);
    }

    private static void setIfPresent(Properties properties, String key, StringSetter setter) {
        String value = properties.getProperty(key);
        if (value != null && !value.isBlank()) {
            setter.set(value.trim());
        }
    }

    private static List<String> commaSeparated(String value) {
        if (value == null || value.isBlank()) {
            return List.of();
        }
        return Arrays.stream(value.split(","))
                .map(String::trim)
                .filter(item -> !item.isEmpty())
                .toList();
    }
}
