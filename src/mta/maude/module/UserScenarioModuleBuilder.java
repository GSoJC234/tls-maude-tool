package mta.maude.module;

import mta.user.behavior.BehaviorDeviationSpecification;
import mta.user.behavior.BehaviorSpecLoader;
import mta.user.common.UserTerm;
import mta.user.profile.TLSProfile;
import mta.user.profile.TLSProfileLoader;
import mta.user.profile.TLSProfiles;
import mta.user.scenario.ScenarioSpec;
import mta.user.scenario.ScenarioSpecLoader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.Set;

public class UserScenarioModuleBuilder {

    public static final String PROFILE_FILE = "tlsprofile.dsl";
    public static final String BEHAVIOR_FILE = "behavior.dsl";
    public static final String SCENARIO_FILE = "scenario.dsl";

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
        return fromFiles(
                caseDirectory.resolve(PROFILE_FILE),
                caseDirectory.resolve(BEHAVIOR_FILE),
                caseDirectory.resolve(SCENARIO_FILE)
        );
    }

    public GeneratedTestModuleSpec fromFiles(Path profilePath,
                                             Path behaviorPath,
                                             Path scenarioPath) {
        TLSProfiles profiles = profileLoader.loadTLSProfiles(profilePath);
        BehaviorDeviationSpecification behavior =
                behaviorSpecLoader.loadBehaviorDeviationSpecification(behaviorPath);
        ScenarioSpec scenarioSpec = scenarioSpecLoader.loadScenarioSpec(scenarioPath);

        GeneratedTestModuleSpec spec = new GeneratedTestModuleSpec();
        spec.setTlsProfiles(profiles);
        spec.setBehaviorDeviationSpecification(behavior);
        spec.setScenarioSpec(scenarioSpec);
        applyDefaults(spec, profilePath.toAbsolutePath().normalize().getParent());
        return spec;
    }

    public void applyDefaults(GeneratedTestModuleSpec spec, Path searchStart) {
        String protocolVersion = inferProtocolVersion(spec.getTlsProfiles());
        spec.setProtocolVersion(protocolVersion);
        spec.setBaseLoadPath(defaultBaseLoadPath(protocolVersion, searchStart));
        spec.setRunner("runScenario");
        spec.setNat(null);
        spec.setExpectedResultSort("List{Scen}");
    }

    public String inferProtocolVersion(TLSProfiles profiles) {
        Set<String> versions = new LinkedHashSet<String>();
        collectProtocolVersion(versions, profiles.getTester());
        collectProtocolVersion(versions, profiles.getTarget());
        if (versions.isEmpty()) {
            return "TLS-13";
        }
        if (versions.size() > 1) {
            throw new IllegalArgumentException("Conflicting TLS profile versions: " + versions);
        }
        return versions.iterator().next();
    }

    public String defaultBaseLoadPath(String protocolVersion, Path searchStart) {
        String fileName = switch (protocolVersion) {
            case "TLS-12" -> "5246-base.maude";
            case "TLS-13" -> "8446-base.maude";
            default -> throw new IllegalArgumentException("Unsupported TLS protocol version: " + protocolVersion);
        };

        Path relative = Path.of("maude", "requirements", fileName);
        Path projectRoot = findProjectRoot(searchStart, relative);
        if (projectRoot == null) {
            projectRoot = findProjectRoot(Path.of("").toAbsolutePath().normalize(), relative);
        }
        if (projectRoot != null) {
            return projectRoot.resolve(relative).toAbsolutePath().normalize().toString();
        }
        return relative.toString();
    }

    private void collectProtocolVersion(Set<String> versions, TLSProfile profile) {
        if (profile == null) {
            return;
        }
        List<UserTerm> values = profile.getRawFields().get("Version");
        if (values == null) {
            return;
        }
        for (UserTerm value : values) {
            String rendered = renderVersionTerm(value);
            if ("TLS12".equals(rendered) || rendered.contains("TLS-12")) {
                versions.add("TLS-12");
            }
            if ("TLS13".equals(rendered) || rendered.contains("TLS-13")) {
                versions.add("TLS-13");
            }
        }
    }

    private String renderVersionTerm(UserTerm term) {
        if (term instanceof UserTerm.RawMaude rawMaude) {
            return rawMaude.text();
        }
        if (term instanceof UserTerm.StringLiteral literal) {
            return literal.value();
        }
        return term.source();
    }

    private Path findProjectRoot(Path start, Path relativeBasePath) {
        if (start == null) {
            return null;
        }
        Path current = start.toAbsolutePath().normalize();
        if (Files.isRegularFile(current)) {
            current = current.getParent();
        }
        while (current != null) {
            if (Files.exists(current.resolve(relativeBasePath))) {
                return current;
            }
            current = current.getParent();
        }
        return null;
    }
}
