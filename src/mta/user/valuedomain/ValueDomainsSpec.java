package mta.user.valuedomain;

import mta.user.common.UserTerm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ValueDomainsSpec {

    private final Map<String, List<UserTerm>> domains = new LinkedHashMap<String, List<UserTerm>>();
    private final Map<String, Map<String, String>> typeMappings =
            new LinkedHashMap<String, Map<String, String>>();
    private final Map<String, List<Map<String, UserTerm>>> instances =
            new LinkedHashMap<String, List<Map<String, UserTerm>>>();

    public boolean isEmpty() {
        return domains.isEmpty() && typeMappings.isEmpty() && instances.isEmpty();
    }

    public void putDomain(String name, List<UserTerm> values) {
        domains.put(name, List.copyOf(values));
    }

    public List<UserTerm> domain(String name) {
        List<UserTerm> values = domains.get(name);
        if (values == null) {
            return null;
        }
        return Collections.unmodifiableList(values);
    }

    public Map<String, List<UserTerm>> domains() {
        return Collections.unmodifiableMap(domains);
    }

    public void putTypeMapping(String functionName, String key, String domainName) {
        typeMappings.computeIfAbsent(functionName, ignored -> new LinkedHashMap<String, String>())
                .put(key, domainName);
    }

    public String mappedDomain(String functionName, String key) {
        Map<String, String> mapping = typeMappings.get(functionName);
        if (mapping == null) {
            return null;
        }
        return mapping.get(key);
    }

    public void addInstance(String behaviorId, Map<String, UserTerm> bindings) {
        instances.computeIfAbsent(behaviorId, ignored -> new ArrayList<Map<String, UserTerm>>())
                .add(new LinkedHashMap<String, UserTerm>(bindings));
    }

    public List<Map<String, UserTerm>> instancesFor(String behaviorId) {
        List<Map<String, UserTerm>> values = instances.get(behaviorId);
        if (values == null) {
            return List.of();
        }
        return Collections.unmodifiableList(values);
    }
}
