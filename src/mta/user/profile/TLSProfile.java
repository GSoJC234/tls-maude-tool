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
    private String libraryPath;
    private String caCertificateType;
    private String certificateType;
    private String privateKeyType;
    private UserTerm certificateSignatureAlgorithm;
    private UserTerm caCertificateSignatureAlgorithm;
    private String caCertificatePath;
    private String certificatePath;
    private String privateKeyPath;
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

    public String getLibraryPath() {
        return libraryPath;
    }

    public void setLibraryPath(String libraryPath) {
        this.libraryPath = libraryPath;
    }

    public String getCaCertificateType() {
        return caCertificateType;
    }

    public void setCaCertificateType(String caCertificateType) {
        this.caCertificateType = caCertificateType;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getPrivateKeyType() {
        return privateKeyType;
    }

    public void setPrivateKeyType(String privateKeyType) {
        this.privateKeyType = privateKeyType;
    }

    public UserTerm getCertificateSignatureAlgorithm() {
        return certificateSignatureAlgorithm;
    }

    public void setCertificateSignatureAlgorithm(UserTerm certificateSignatureAlgorithm) {
        this.certificateSignatureAlgorithm = certificateSignatureAlgorithm;
    }

    public UserTerm getCaCertificateSignatureAlgorithm() {
        return caCertificateSignatureAlgorithm;
    }

    public void setCaCertificateSignatureAlgorithm(UserTerm caCertificateSignatureAlgorithm) {
        this.caCertificateSignatureAlgorithm = caCertificateSignatureAlgorithm;
    }

    public String getCaCertificatePath() {
        return caCertificatePath;
    }

    public void setCaCertificatePath(String caCertificatePath) {
        this.caCertificatePath = caCertificatePath;
    }

    public String getCertificatePath() {
        return certificatePath;
    }

    public void setCertificatePath(String certificatePath) {
        this.certificatePath = certificatePath;
    }

    public String getPrivateKeyPath() {
        return privateKeyPath;
    }

    public void setPrivateKeyPath(String privateKeyPath) {
        this.privateKeyPath = privateKeyPath;
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
                && Objects.equals(libraryPath, that.libraryPath)
                && Objects.equals(caCertificateType, that.caCertificateType)
                && Objects.equals(certificateType, that.certificateType)
                && Objects.equals(privateKeyType, that.privateKeyType)
                && Objects.equals(certificateSignatureAlgorithm, that.certificateSignatureAlgorithm)
                && Objects.equals(caCertificateSignatureAlgorithm, that.caCertificateSignatureAlgorithm)
                && Objects.equals(caCertificatePath, that.caCertificatePath)
                && Objects.equals(certificatePath, that.certificatePath)
                && Objects.equals(privateKeyPath, that.privateKeyPath)
                && Objects.equals(rawFields, that.rawFields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testRole, tlsRole, libraryName, libraryVersion, libraryPath,
                caCertificateType, certificateType, privateKeyType,
                certificateSignatureAlgorithm, caCertificateSignatureAlgorithm,
                caCertificatePath, certificatePath, privateKeyPath, rawFields);
    }

    @Override
    public String toString() {
        return "TLSProfile{"
                + "testRole=" + testRole
                + ", tlsRole=" + tlsRole
                + ", libraryName='" + libraryName + '\''
                + ", libraryVersion='" + libraryVersion + '\''
                + ", libraryPath='" + libraryPath + '\''
                + ", caCertificateType='" + caCertificateType + '\''
                + ", certificateType='" + certificateType + '\''
                + ", privateKeyType='" + privateKeyType + '\''
                + ", certificateSignatureAlgorithm=" + certificateSignatureAlgorithm
                + ", caCertificateSignatureAlgorithm=" + caCertificateSignatureAlgorithm
                + ", caCertificatePath='" + caCertificatePath + '\''
                + ", certificatePath='" + certificatePath + '\''
                + ", privateKeyPath='" + privateKeyPath + '\''
                + ", rawFields=" + rawFields
                + '}';
    }
}
