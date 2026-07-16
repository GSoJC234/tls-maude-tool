package mta.scenario;

import mta.user.common.UserTerm;
import mta.user.profile.TLSProfile;
import mta.user.profile.TLSProfileLoader;
import mta.user.profile.TLSProfiles;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class TargetExecutionMetadataLoader {

    public TargetExecutionMetadata load(Path profilePath) {
        if (profilePath == null) {
            return TargetExecutionMetadata.empty();
        }

        TLSProfiles profiles = new TLSProfileLoader().loadTLSProfiles(profilePath);
        TLSProfile target = profiles.getTarget();
        if (target == null) {
            throw new IllegalArgumentException("TLS profile has no target block: " + profilePath);
        }

        return new TargetExecutionMetadata(
                clean(target.getLibraryName()),
                clean(target.getLibraryVersion()),
                clean(target.getLibraryPath()),
                target.getTlsRole() == null ? "" : target.getTlsRole().name(),
                field(target, "ExecutionMode"),
                field(target, "RuntimePlatform"),
                field(target, "DockerImage"),
                field(target, "BuildProfile"),
                field(target, "BinaryPath"));
    }

    private static String field(TLSProfile profile, String fieldName) {
        Map<String, List<UserTerm>> rawFields = profile.getRawFields();
        List<UserTerm> values = rawFields.get(fieldName);
        if (values == null || values.isEmpty()) {
            return "";
        }
        if (values.size() != 1) {
            throw new IllegalArgumentException("TLSProfiles target field " + fieldName
                    + " expects exactly one value");
        }
        return clean(value(values.get(0)));
    }

    private static String value(UserTerm term) {
        if (term instanceof UserTerm.StringLiteral literal) {
            return literal.value();
        }
        if (term instanceof UserTerm.Atom atom) {
            return atom.text();
        }
        return term.source();
    }

    private static String clean(String value) {
        return value == null ? "" : value.trim();
    }
}
