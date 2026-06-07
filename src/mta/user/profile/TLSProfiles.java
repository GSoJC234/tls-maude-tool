package mta.user.profile;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class TLSProfiles {

    public static final String TESTER = "tester";
    public static final String TARGET = "target";

    private final Map<String, TLSProfile> profiles = new LinkedHashMap<String, TLSProfile>();

    public Map<String, TLSProfile> getProfiles() {
        return Collections.unmodifiableMap(profiles);
    }

    public TLSProfile getProfile(String name) {
        return profiles.get(name);
    }

    public TLSProfile getTester() {
        return profiles.get(TESTER);
    }

    public TLSProfile getTarget() {
        return profiles.get(TARGET);
    }

    public void putProfile(String name, TLSProfile profile) {
        if (name == null || profile == null) {
            return;
        }
        profiles.put(name, profile);
    }
}
