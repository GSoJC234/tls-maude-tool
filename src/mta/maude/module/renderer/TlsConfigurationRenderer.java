package mta.maude.module.renderer;

import mta.user.common.UserTerm;
import mta.user.profile.TLSProfile;
import mta.user.profile.TLSProfiles;
import mta.user.profile.TLSRole;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringJoiner;

public class TlsConfigurationRenderer {

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
        for (Map.Entry<String, TLSProfile> entry : profiles.getProfiles().entrySet()) {
            String profileName = entry.getKey();
            TLSProfile profile = entry.getValue();
            String tid = profileTId(profileName, profile);
            if (TLSProfiles.TESTER.equals(profileName)) {
                items.add("tester(" + tid + ")");
            } else if (TLSProfiles.TARGET.equals(profileName)) {
                items.add("target(" + tid + ")");
            }
            addProfileItems(items, profileName, profile, tid, profiles);
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
        for (Map.Entry<String, List<UserTerm>> field : profile.getRawFields().entrySet()) {
            String constructor = tlsConfigurationConstructor(field.getKey());
            if (constructor == null) {
                continue;
            }
            if (isFlagField(field.getKey())) {
                if (isEnabled(field.getValue())) {
                    items.add(constructor + "(" + tid + ")");
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
                        items.add("ikeyUpdateReq(" + tid + ", "
                                + renderValues(keyUpdateType, profileName, profiles)
                                + ")");
                    } else {
                        items.add("ikeyUpdateReq(" + tid + ")");
                    }
                }
                continue;
            }
            items.add(constructor
                    + "("
                    + tid
                    + ", "
                    + renderValues(field.getValue(), profileName, profiles)
                    + ")");
        }
    }

    private String tlsConfigurationConstructor(String fieldName) {
        return switch (fieldName) {
            case "Version" -> "iversion";
            case "CipherSuites" -> "icipherSuites";
            case "Compressions" -> "icompressions";
            case "Certificates" -> "icertificates";
            case "CertificateTypes" -> "icertificateTypes";
            case "CertificateAlgorithms", "CertificateAlgos" -> "icertificateAlgos";
            case "PublicKeys" -> "ipublicKeys";
            case "PrivateKeys" -> "iprivateKeys";
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

    private String renderValues(List<UserTerm> values, String profileName, TLSProfiles profiles) {
        if (values == null || values.isEmpty()) {
            return "nil";
        }
        StringJoiner joiner = new StringJoiner(" ");
        for (UserTerm value : values) {
            joiner.add(renderTerm(value, profileName, profiles));
        }
        return joiner.toString();
    }

    private String renderTerm(UserTerm term, String profileName, TLSProfiles profiles) {
        if (term instanceof UserTerm.Atom atom) {
            return switch (atom.text()) {
                case "self" -> profileTId(profileName, profiles.getProfile(profileName));
                case "tester" -> profileTId(TLSProfiles.TESTER, profiles.getTester());
                case "target" -> profileTId(TLSProfiles.TARGET, profiles.getTarget());
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

    private String profileTId(String profileName, TLSProfile profile) {
        if (profile == null) {
            throw new IllegalArgumentException("Unknown profile: " + profileName);
        }
        String node = TLSProfiles.TESTER.equals(profileName) ? "N1" : "N2";
        String component = profile.getTlsRole() == TLSRole.Server ? "SI" : "CI";
        return node + " . " + component;
    }
}
