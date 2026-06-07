package mta.user.profile;

import mta.user.common.UserTerm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TLSProfile {

    private TestRole testRole;
    private TLSRole tlsRole;
    private String libraryName;
    private String libraryVersion;
    private final Map<String, List<UserTerm>> rawFields;

    public TLSProfile() {
        this.rawFields = new LinkedHashMap<String, List<UserTerm>>();
    }

    public TestRole getTestRole() {
        return testRole;
    }

    public void setTestRole(TestRole testRole) {
        this.testRole = testRole;
    }

    public TLSRole getTlsRole() {
        return tlsRole;
    }

    public void setTlsRole(TLSRole tlsRole) {
        this.tlsRole = tlsRole;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getLibraryVersion() {
        return libraryVersion;
    }

    public void setLibraryVersion(String libraryVersion) {
        this.libraryVersion = libraryVersion;
    }

    public Map<String, List<UserTerm>> getRawFields() {
        Map<String, List<UserTerm>> copy = new LinkedHashMap<String, List<UserTerm>>();
        for (Map.Entry<String, List<UserTerm>> entry : rawFields.entrySet()) {
            copy.put(entry.getKey(), Collections.unmodifiableList(entry.getValue()));
        }
        return Collections.unmodifiableMap(copy);
    }

    public void putRawField(String name, List<UserTerm> values) {
        if (name == null) {
            return;
        }
        rawFields.put(name, values != null ? new ArrayList<UserTerm>(values) : List.of());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TLSProfile that)) {
            return false;
        }
        return testRole == that.testRole
                && tlsRole == that.tlsRole
                && Objects.equals(libraryName, that.libraryName)
                && Objects.equals(libraryVersion, that.libraryVersion)
                && Objects.equals(rawFields, that.rawFields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testRole, tlsRole, libraryName, libraryVersion, rawFields);
    }

    @Override
    public String toString() {
        return "TLSProfile{"
                + "testRole=" + testRole
                + ", tlsRole=" + tlsRole
                + ", libraryName='" + libraryName + '\''
                + ", libraryVersion='" + libraryVersion + '\''
                + ", rawFields=" + rawFields
                + '}';
    }
}
