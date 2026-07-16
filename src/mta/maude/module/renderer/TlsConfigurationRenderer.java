package mta.maude.module.renderer;

import mta.user.common.UserTerm;
import mta.user.profile.TLSProfile;
import mta.user.profile.TLSProfileValueNormalizer;
import mta.user.profile.TLSProfiles;
import mta.user.profile.TLSRole;
import mta.user.profile.TestRole;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

public class TlsConfigurationRenderer {
    private static final String CLIENT_TID = "N1 . CI";
    private static final String SERVER_TID = "N2 . SI";

    private static final Set<String> SERVER_OWNED_FIELDS = Set.of(
            "ClientCertificateTypes",
            "ClientCertificateAlgos",
            "CertificateRequest",
            "NewSessionTicketReq",
            "NewSessionTicketRequest",
            "EarlyDataWait",
            "PostClientAuthReq",
            "PostClientAuthRequest"
    );

    private static final Set<String> CLIENT_OWNED_FIELDS = Set.of(
            "Renegotiation",
            "NewSessionTicketWait",
            "EarlyDataReq",
            "EarlyDataRequest",
            "PostClientAuthWait"
    );

    public String renderEq(String opName, TLSProfiles profiles) {
        return "  op "
                + opName
                + " : -> TLSConfiguration ."
                + System.lineSeparator()
                + "  eq "
                + opName
                + " = "
                + render(profiles)
                + " .";
    }

    public String render(TLSProfiles profiles) {
        validateProfiles(profiles);

        List<String> items = new ArrayList<String>();
        int testerMarkers = 0;
        int targetMarkers = 0;
        for (Map.Entry<String, TLSProfile> entry : profiles.getProfiles().entrySet()) {
            String profileName = entry.getKey();
            TLSProfile profile = entry.getValue();
            String tid = profileTId(profile);
            TestRole testRole = effectiveTestRole(profileName, profile);
            if (testRole == TestRole.Tester) {
                items.add("tester(" + tid + ")");
                testerMarkers++;
            } else if (testRole == TestRole.Target) {
                items.add("target(" + tid + ")");
                targetMarkers++;
            }
            addProfileItems(items, profileName, profile, tid, profiles);
        }
        if (testerMarkers != 1 || targetMarkers != 1) {
            throw new IllegalArgumentException("TLSProfiles must define exactly one Tester and one Target profile");
        }

        if (items.isEmpty()) {
            return "none";
        }

        StringJoiner joiner = new StringJoiner(System.lineSeparator() + "     ");
        for (String item : items) {
            joiner.add(item);
        }
        return joiner.toString();
    }

    private void validateProfiles(TLSProfiles profiles) {
        if (profiles.getTester() == null) {
            throw new IllegalArgumentException("TLSProfiles must define tester profile");
        }
        if (profiles.getTarget() == null) {
            throw new IllegalArgumentException("TLSProfiles must define target profile");
        }
    }

    private void addProfileItems(List<String> items,
                                 String profileName,
                                 TLSProfile profile,
                                 String tid,
                                 TLSProfiles profiles) {
        addCertificateAndKeyItems(items, profile, tid, profileName, profiles);
        for (Map.Entry<String, List<UserTerm>> field : profile.getRawFields().entrySet()) {
            String constructor = tlsConfigurationConstructor(field.getKey());
            if (constructor == null) {
                continue;
            }
            String fieldTid = fieldTId(field.getKey(), tid);
            if (isFlagField(field.getKey())) {
                if (isEnabled(field.getValue())) {
                    items.add(constructor + "(" + fieldTid + ")");
                }
                continue;
            }
            if ("KeyUpdateReq".equals(field.getKey()) || "KeyUpdateRequest".equals(field.getKey())) {
                if (isEnabled(field.getValue())) {
                    List<UserTerm> keyUpdateType = profile.getRawFields().get("KeyUpdateReqType");
                    if (keyUpdateType == null) {
                        keyUpdateType = profile.getRawFields().get("KeyUpdateRequestType");
                    }
                    if (keyUpdateType != null && !keyUpdateType.isEmpty()) {
                        items.add("ikeyUpdateReq(" + fieldTid + ", "
                                + renderValues("KeyUpdateReqType", keyUpdateType, profileName, profiles)
                                + ")");
                    } else {
                        items.add("ikeyUpdateReq(" + fieldTid + ")");
                    }
                }
                continue;
            }
            items.add(constructor
                    + "("
                    + fieldTid
                    + ", "
                    + renderValues(field.getKey(), field.getValue(), profileName, profiles)
                    + ")");
        }
    }

    private void addCertificateAndKeyItems(List<String> items,
                                           TLSProfile profile,
                                           String tid,
                                           String profileName,
                                           TLSProfiles profiles) {
        requireCertificateField(profile.getCaCertificateType(), "CACertificateType", profileName);
        requireCertificateField(profile.getCertificateType(), "CertificateType", profileName);
        requireCertificateField(profile.getPrivateKeyType(), "PrivateKeyType", profileName);
        requireCertificateField(profile.getCertificateSignatureAlgorithm(),
                "CertificateSignatureAlgorithm", profileName);

        items.add("icertificates(" + tid + ", makeCertificate("
                + tid
                + ", "
                + profile.getCertificateType()
                + ", "
                + renderTerm(profile.getCertificateSignatureAlgorithm(), profileName, profiles)
                + ", "
                + profile.getCaCertificateType()
                + "))");
        items.add("ipublicKeys(" + tid + ", makePubKey(CA, " + profile.getCaCertificateType() + "))");
        items.add("iprivateKeys(" + tid + ", makePrvKey(" + tid + ", " + profile.getPrivateKeyType() + "))");
    }

    private void requireCertificateField(Object value, String fieldName, String profileName) {
        if (value == null) {
            throw new IllegalArgumentException("TLSProfiles " + profileName
                    + " profile must define " + fieldName);
        }
    }

    private String tlsConfigurationConstructor(String fieldName) {
        return switch (fieldName) {
            case "Version" -> "iversion";
            case "CipherSuites" -> "icipherSuites";
            case "Compressions" -> "icompressions";
            case "ClientCertificateTypes" -> "icertificateTypes";
            case "ClientCertificateAlgos" -> "icertificateAlgos";
            case "SupportedVersions" -> "isupported-versions";
            case "SignatureAlgorithms" -> "isignature-algorithms";
            case "SupportedGroups" -> "isupported-groups";
            case "PSKKeyExchangeModes", "PskKeyExchangeModes" -> "ipsk-key-exchange-modes";
            case "KeyShares" -> "ikey-shares";
            case "CertificateRequest" -> "icertificateRequest";
            case "Renegotiation" -> "irenegotiation";
            case "SecureRenegotiation" -> "isecureRenegotiation";
            case "NewSessionTicketReq", "NewSessionTicketRequest" -> "inewSessionTicketReq";
            case "NewSessionTicketWait" -> "inewSessionTicketWait";
            case "AppDataEchoBeforeNST" -> "iappDataEchoBeforeNST";
            case "Reconnect" -> "ireconnect";
            case "EarlyDataReq", "EarlyDataRequest" -> "iearlyDataReq";
            case "EarlyDataWait" -> "iearlyDataWait";
            case "PostClientAuthReq", "PostClientAuthRequest" -> "ipostClientAuthReq";
            case "PostClientAuthWait" -> "ipostClientAuthWait";
            case "KeyUpdateReq", "KeyUpdateRequest" -> "ikeyUpdateReq";
            case "KeyUpdateWait" -> "ikeyUpdateWait";
            case "CKS", "Cks" -> "icks";
            default -> null;
        };
    }

    private boolean isFlagField(String fieldName) {
        return switch (fieldName) {
            case "CertificateRequest",
                    "Renegotiation",
                    "SecureRenegotiation",
                    "NewSessionTicketReq",
                    "NewSessionTicketRequest",
                    "NewSessionTicketWait",
                    "AppDataEchoBeforeNST",
                    "Reconnect",
                    "EarlyDataReq",
                    "EarlyDataRequest",
                    "EarlyDataWait",
                    "PostClientAuthReq",
                    "PostClientAuthRequest",
                    "PostClientAuthWait",
                    "KeyUpdateWait",
                    "CKS",
                    "Cks" -> true;
            default -> false;
        };
    }

    private boolean isEnabled(List<UserTerm> values) {
        if (values == null || values.isEmpty()) {
            return true;
        }
        String value = values.get(0).source().toLowerCase(Locale.ROOT);
        return "true".equals(value);
    }

    private String renderValues(String fieldName, List<UserTerm> values, String profileName, TLSProfiles profiles) {
        if (values == null || values.isEmpty()) {
            return "nil";
        }
        values = TLSProfileValueNormalizer.toMaudeTerms(fieldName, values);
        StringJoiner joiner = new StringJoiner(" ");
        for (UserTerm value : values) {
            joiner.add(renderTerm(value, profileName, profiles));
        }
        return joiner.toString();
    }

    private String renderTerm(UserTerm term, String profileName, TLSProfiles profiles) {
        if (term instanceof UserTerm.Atom atom) {
            return switch (atom.text()) {
                case "self" -> profileTId(profiles.getProfile(profileName));
                case "tester" -> profileTId(profileByTestRole(profiles, TestRole.Tester));
                case "target" -> profileTId(profileByTestRole(profiles, TestRole.Target));
                default -> atom.text();
            };
        }
        if (term instanceof UserTerm.StringLiteral literal) {
            return literal.source();
        }
        if (term instanceof UserTerm.RawMaude rawMaude) {
            return rawMaude.text();
        }
        if (term instanceof UserTerm.Call call) {
            StringJoiner joiner = new StringJoiner(", ");
            for (UserTerm argument : call.arguments()) {
                joiner.add(renderTerm(argument, profileName, profiles));
            }
            return call.name() + "(" + joiner + ")";
        }
        if (term instanceof UserTerm.ListTerm list) {
            StringJoiner joiner = new StringJoiner(" ");
            for (UserTerm value : list.values()) {
                joiner.add(renderTerm(value, profileName, profiles));
            }
            return joiner.toString();
        }
        if (term instanceof UserTerm.BraceTerm brace) {
            StringJoiner joiner = new StringJoiner(", ", "{", "}");
            for (UserTerm value : brace.values()) {
                joiner.add(renderTerm(value, profileName, profiles));
            }
            return joiner.toString();
        }
        if (term instanceof UserTerm.Indexed indexed) {
            return indexed.name() + "[" + renderTerm(indexed.index(), profileName, profiles) + "]";
        }
        if (term instanceof UserTerm.Dotted dotted) {
            StringJoiner joiner = new StringJoiner(" . ");
            for (UserTerm part : dotted.parts()) {
                joiner.add(renderTerm(part, profileName, profiles));
            }
            return joiner.toString();
        }
        if (term instanceof UserTerm.Parameter parameter) {
            throw new IllegalArgumentException("Parameters are not supported in TLSProfiles: "
                    + parameter.source());
        }
        throw new IllegalArgumentException("Unsupported TLS profile term: " + term);
    }

    private TestRole effectiveTestRole(String profileName, TLSProfile profile) {
        if (profile.getTestRole() != null) {
            return profile.getTestRole();
        }
        if (TLSProfiles.TESTER.equals(profileName)) {
            return TestRole.Tester;
        }
        if (TLSProfiles.TARGET.equals(profileName)) {
            return TestRole.Target;
        }
        return null;
    }

    private TLSProfile profileByTestRole(TLSProfiles profiles, TestRole testRole) {
        TLSProfile matched = null;
        for (Map.Entry<String, TLSProfile> entry : profiles.getProfiles().entrySet()) {
            if (effectiveTestRole(entry.getKey(), entry.getValue()) != testRole) {
                continue;
            }
            if (matched != null) {
                throw new IllegalArgumentException("TLSProfiles must define exactly one " + testRole + " profile");
            }
            matched = entry.getValue();
        }
        if (matched == null) {
            throw new IllegalArgumentException("TLSProfiles must define a " + testRole + " profile");
        }
        return matched;
    }

    private String fieldTId(String fieldName, String defaultTid) {
        if (SERVER_OWNED_FIELDS.contains(fieldName)) {
            return SERVER_TID;
        }
        if (CLIENT_OWNED_FIELDS.contains(fieldName)) {
            return CLIENT_TID;
        }
        return defaultTid;
    }

    private String profileTId(TLSProfile profile) {
        if (profile == null) {
            throw new IllegalArgumentException("Unknown TLS profile");
        }
        TLSRole tlsRole = profile.getTlsRole();
        if (tlsRole == null) {
            throw new IllegalArgumentException("TLSProfile must define TLSRole");
        }
        return switch (tlsRole) {
            case Client -> CLIENT_TID;
            case Server -> SERVER_TID;
            case Mitm -> throw new IllegalArgumentException(
                    "TLSRole Mitm is not supported by the generated initial TLS system");
        };
    }
}
