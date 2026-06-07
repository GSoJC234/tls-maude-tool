package mta.user.profile;

import mta.maude.constant.*;
import mta.user.common.UserTerm;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TLSProfile {

    private TestRole testRole;
    private TLSRole tlsRole;
    private ProtocolVersion version;
    private final List<CipherSuite> cipherSuites;
    private final List<CompressionMethod> compressions;
    private final List<CertificateType> certificateTypes;
    private final List<SignatureAlgorithm> certificateAlgos;
    private Path certificatePath;
    private Path privateKeyPath;
    private Path caCertificatePath;
    private final List<NamedGroup> supportedGroups;
    private final List<SignatureAlgorithm> signatureAlgorithms;
    private final List<NamedGroup> keyShares;
    private final List<SupportedVersion> supportedVersions;
    private final List<PskKeyExchangeMode> pskKeyExchangeModes;
    private boolean newSessionTicketReq;
    private boolean newSessionTicketWait;
    private boolean earlyDataReq;
    private boolean postClientAuthReq;
    private boolean keyUpdateReq;
    private boolean keyUpdateWait;
    private boolean certificateRequest;
    private ExecutionConfiguration executionConfiguration;
    private String libraryName;
    private String libraryVersion;
    private final Map<String, List<UserTerm>> rawFields;

    public TLSProfile() {
        this.certificateTypes = new ArrayList<CertificateType>();
        this.certificateAlgos = new ArrayList<SignatureAlgorithm>();
        this.pskKeyExchangeModes = new ArrayList<PskKeyExchangeMode>();
        this.cipherSuites = new ArrayList<CipherSuite>();
        this.compressions = new ArrayList<CompressionMethod>();
        this.supportedGroups = new ArrayList<NamedGroup>();
        this.signatureAlgorithms = new ArrayList<SignatureAlgorithm>();
        this.keyShares = new ArrayList<NamedGroup>();
        this.supportedVersions = new ArrayList<SupportedVersion>();
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

    public ProtocolVersion getVersion() {
        return version;
    }

    public void setVersion(ProtocolVersion version) {
        this.version = version;
    }

    public List<CipherSuite> getCipherSuites() {
        return Collections.unmodifiableList(cipherSuites);
    }

    public void setCipherSuites(List<CipherSuite> cipherSuites) {
        this.cipherSuites.clear();
        if (cipherSuites != null) {
            this.cipherSuites.addAll(cipherSuites);
        }
    }

    public void addCipherSuite(CipherSuite cipherSuite) {
        if (cipherSuite != null) {
            this.cipherSuites.add(cipherSuite);
        }
    }

    public List<CompressionMethod> getCompressions() {
        return Collections.unmodifiableList(compressions);
    }

    public void setCompressions(List<CompressionMethod> compressions) {
        this.compressions.clear();
        if (compressions != null) {
            this.compressions.addAll(compressions);
        }
    }

    public void addCompression(CompressionMethod compression) {
        if (compression != null) {
            this.compressions.add(compression);
        }
    }

    public List<CertificateType> getCertificateTypes() {
        return Collections.unmodifiableList(certificateTypes);
    }

    public void setCertificateTypes(List<CertificateType> certificateTypes) {
        this.certificateTypes.clear();
        if (certificateTypes != null) {
            this.certificateTypes.addAll(certificateTypes);
        }
    }

    public void addCertificateType(CertificateType certificateType) {
        if (certificateType != null) {
            this.certificateTypes.add(certificateType);
        }
    }

    public List<SignatureAlgorithm> getCertificateAlgos() {
        return Collections.unmodifiableList(certificateAlgos);
    }

    public void setCertificateAlgos(List<SignatureAlgorithm> certificateAlgos) {
        this.certificateAlgos.clear();
        if (certificateAlgos != null) {
            this.certificateAlgos.addAll(certificateAlgos);
        }
    }

    public void addCertificateAlgo(SignatureAlgorithm certificateAlgo) {
        if (certificateAlgo != null) {
            this.certificateAlgos.add(certificateAlgo);
        }
    }

    public Path getCertificatePath() {
        return certificatePath;
    }

    public void setCertificatePath(Path certificatePath) {
        this.certificatePath = certificatePath;
    }

    public Path getPrivateKeyPath() {
        return privateKeyPath;
    }

    public void setPrivateKeyPath(Path privateKeyPath) {
        this.privateKeyPath = privateKeyPath;
    }

    public Path getCaCertificatePath() {
        return caCertificatePath;
    }

    public void setCaCertificatePath(Path caCertificatePath) {
        this.caCertificatePath = caCertificatePath;
    }

    public List<NamedGroup> getSupportedGroups() {
        return Collections.unmodifiableList(supportedGroups);
    }

    public void setSupportedGroups(List<NamedGroup> supportedGroups) {
        this.supportedGroups.clear();
        if (supportedGroups != null) {
            this.supportedGroups.addAll(supportedGroups);
        }
    }

    public void addSupportedGroup(NamedGroup supportedGroup) {
        if (supportedGroup != null) {
            this.supportedGroups.add(supportedGroup);
        }
    }

    public List<SignatureAlgorithm> getSignatureAlgorithms() {
        return Collections.unmodifiableList(signatureAlgorithms);
    }

    public void setSignatureAlgorithms(List<SignatureAlgorithm> signatureAlgorithms) {
        this.signatureAlgorithms.clear();
        if (signatureAlgorithms != null) {
            this.signatureAlgorithms.addAll(signatureAlgorithms);
        }
    }

    public void addSignatureAlgorithm(SignatureAlgorithm signatureAlgorithm) {
        if (signatureAlgorithm != null) {
            this.signatureAlgorithms.add(signatureAlgorithm);
        }
    }

    public List<NamedGroup> getKeyShares() {
        return Collections.unmodifiableList(keyShares);
    }

    public void setKeyShares(List<NamedGroup> keyShares) {
        this.keyShares.clear();
        if (keyShares != null) {
            this.keyShares.addAll(keyShares);
        }
    }

    public void addKeyShare(NamedGroup keyShare) {
        if (keyShare != null) {
            this.keyShares.add(keyShare);
        }
    }

    public List<SupportedVersion> getSupportedVersions() {
        return Collections.unmodifiableList(supportedVersions);
    }

    public void setSupportedVersions(List<SupportedVersion> supportedVersions) {
        this.supportedVersions.clear();
        if (supportedVersions != null) {
            this.supportedVersions.addAll(supportedVersions);
        }
    }

    public void addSupportedVersion(SupportedVersion supportedVersion) {
        if (supportedVersion != null) {
            this.supportedVersions.add(supportedVersion);
        }
    }

    public List<PskKeyExchangeMode> getPskKeyExchangeModes() {
        return Collections.unmodifiableList(pskKeyExchangeModes);
    }

    public void setPskKeyExchangeModes(List<PskKeyExchangeMode> pskKeyExchangeModes) {
        this.pskKeyExchangeModes.clear();
        if (pskKeyExchangeModes != null) {
            this.pskKeyExchangeModes.addAll(pskKeyExchangeModes);
        }
    }

    public void addPskKeyExchangeMode(PskKeyExchangeMode pskKeyExchangeMode) {
        if (pskKeyExchangeMode != null) {
            this.pskKeyExchangeModes.add(pskKeyExchangeMode);
        }
    }

    public boolean isNewSessionTicketReq() {
        return newSessionTicketReq;
    }

    public void setNewSessionTicketReq(boolean newSessionTicketReq) {
        this.newSessionTicketReq = newSessionTicketReq;
    }

    public boolean isNewSessionTicketWait() {
        return newSessionTicketWait;
    }

    public void setNewSessionTicketWait(boolean newSessionTicketWait) {
        this.newSessionTicketWait = newSessionTicketWait;
    }

    public boolean isEarlyDataReq() {
        return earlyDataReq;
    }

    public void setEarlyDataReq(boolean earlyDataReq) {
        this.earlyDataReq = earlyDataReq;
    }

    public boolean isPostClientAuthReq() {
        return postClientAuthReq;
    }

    public void setPostClientAuthReq(boolean postClientAuthReq) {
        this.postClientAuthReq = postClientAuthReq;
    }

    public boolean isKeyUpdateReq() {
        return keyUpdateReq;
    }

    public void setKeyUpdateReq(boolean keyUpdateReq) {
        this.keyUpdateReq = keyUpdateReq;
    }

    public boolean isKeyUpdateWait() {
        return keyUpdateWait;
    }

    public void setKeyUpdateWait(boolean keyUpdateWait) {
        this.keyUpdateWait = keyUpdateWait;
    }

    public Boolean getCertificateRequest() {
        return certificateRequest;
    }

    public void setCertificateRequest(Boolean certificateRequest) {
        this.certificateRequest = certificateRequest;
    }

    public ExecutionConfiguration getExecutionConfiguration() {
        return executionConfiguration;
    }

    public void setExecutionConfiguration(ExecutionConfiguration executionConfiguration) {
        this.executionConfiguration = executionConfiguration;
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
        return Objects.equals(testRole, that.testRole)
                && Objects.equals(tlsRole, that.tlsRole)
                && Objects.equals(version, that.version)
                && Objects.equals(cipherSuites, that.cipherSuites)
                && Objects.equals(compressions, that.compressions)
                && Objects.equals(certificateTypes, that.certificateTypes)
                && Objects.equals(certificateAlgos, that.certificateAlgos)
                && Objects.equals(certificatePath, that.certificatePath)
                && Objects.equals(privateKeyPath, that.privateKeyPath)
                && Objects.equals(caCertificatePath, that.caCertificatePath)
                && Objects.equals(supportedGroups, that.supportedGroups)
                && Objects.equals(signatureAlgorithms, that.signatureAlgorithms)
                && Objects.equals(keyShares, that.keyShares)
                && Objects.equals(supportedVersions, that.supportedVersions)
                && Objects.equals(pskKeyExchangeModes, that.pskKeyExchangeModes)
                && newSessionTicketReq == that.newSessionTicketReq
                && newSessionTicketWait == that.newSessionTicketWait
                && earlyDataReq == that.earlyDataReq
                && postClientAuthReq == that.postClientAuthReq
                && keyUpdateReq == that.keyUpdateReq
                && keyUpdateWait == that.keyUpdateWait
                && certificateRequest == that.certificateRequest
                && Objects.equals(executionConfiguration, that.executionConfiguration)
                && Objects.equals(libraryName, that.libraryName)
                && Objects.equals(libraryVersion, that.libraryVersion)
                && Objects.equals(rawFields, that.rawFields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testRole, tlsRole, version, cipherSuites, compressions,
                certificateTypes, certificateAlgos, certificatePath, privateKeyPath,
                caCertificatePath, supportedGroups, signatureAlgorithms, keyShares,
                supportedVersions, pskKeyExchangeModes, newSessionTicketReq,
                newSessionTicketWait, earlyDataReq, postClientAuthReq,
                keyUpdateReq, keyUpdateWait, certificateRequest,
                executionConfiguration, libraryName, libraryVersion, rawFields);
    }

    @Override
    public String toString() {
        return "TLSProfile{"
                + "testRole='" + testRole + '\''
                + ", tlsRole='" + tlsRole + '\''
                + ", version='" + version + '\''
                + ", cipherSuites=" + cipherSuites
                + ", compressions=" + compressions
                + ", certificatePath='" + certificatePath + '\''
                + ", privateKeyPath='" + privateKeyPath + '\''
                + ", caCertificatePath='" + caCertificatePath + '\''
                + ", supportedGroups=" + supportedGroups
                + ", signatureAlgorithms=" + signatureAlgorithms
                + ", keyShares=" + keyShares
                + ", supportedVersions=" + supportedVersions
                + ", newSessionTicketReq=" + newSessionTicketReq
                + ", newSessionTicketWait=" + newSessionTicketWait
                + ", earlyDataReq=" + earlyDataReq
                + ", postClientAuthReq=" + postClientAuthReq
                + ", keyUpdateReq=" + keyUpdateReq
                + ", keyUpdateWait=" + keyUpdateWait
                + ", certificateRequest=" + certificateRequest
                + ", executionConfiguration=" + executionConfiguration
                + ", libraryName='" + libraryName + '\''
                + ", libraryVersion='" + libraryVersion + '\''
                + ", rawFields=" + rawFields
                + '}';
    }

    public static class ExecutionConfiguration {

        private String name;
        private Path path;

        public ExecutionConfiguration() {
        }

        public ExecutionConfiguration(String name, Path path) {
            this.name = name;
            this.path = path;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Path getPath() {
            return path;
        }

        public void setPath(Path path) {
            this.path = path;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof ExecutionConfiguration that)) {
                return false;
            }
            return Objects.equals(name, that.name)
                    && Objects.equals(path, that.path);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, path);
        }

        @Override
        public String toString() {
            return "ExecutionConfiguration{"
                    + "name='" + name + '\''
                    + ", path='" + path + '\''
                    + '}';
        }
    }

}
