package mta.user.profile;

import mta.user.common.UserTerm;
import mta.user.profile.antlr.TLSProfileBaseVisitor;
import mta.user.profile.antlr.TLSProfileParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TLSProfileBuildingVisitor extends TLSProfileBaseVisitor<Object> {

    private static final Set<String> TARGET_LIBRARY_FIELDS = Set.of(
            "LibraryName",
            "LibraryVersion",
            "LibraryPath");
    private static final Set<String> REQUIRED_CERTIFICATE_FIELDS = Set.of(
            "CACertificateType",
            "CertificateType",
            "PrivateKeyType",
            "CertificateSignatureAlgorithm");

    @Override
    public Object visitProfiles(TLSProfileParser.ProfilesContext ctx) {
        TLSProfiles profiles = new TLSProfiles();
        for (TLSProfileParser.ProfileBlockContext profileBlockContext : ctx.profileBlock()) {
            ProfileEntry entry = (ProfileEntry) visit(profileBlockContext);
            profiles.putProfile(entry.name(), entry.profile());
        }
        return profiles;
    }

    @Override
    public Object visitProfileBlock(TLSProfileParser.ProfileBlockContext ctx) {
        String profileName = readProfileName(ctx.profileName());
        TLSProfile profile = new TLSProfile();
        profile.setTestRole(TLSProfiles.TESTER.equals(profileName) ? TestRole.Tester : TestRole.Target);

        for (TLSProfileParser.ProfileEntryContext entryContext : ctx.profileEntry()) {
            applyProfileEntry(profile, entryContext);
        }
        validateProfileBlock(profileName, profile);
        return new ProfileEntry(profileName, profile);
    }

    private void applyProfileEntry(TLSProfile profile, TLSProfileParser.ProfileEntryContext ctx) {
        String fieldName = readIdentifier(ctx.identifier());
        @SuppressWarnings("unchecked")
        List<UserTerm> values = (List<UserTerm>) visit(ctx.profileValue());
        TLSProfileValueNormalizer.validate(fieldName, values);
        profile.putRawField(fieldName, values);

        String scalar = values.isEmpty() ? "" : values.get(0).source();
        List<UserTerm> normalizedValues = TLSProfileValueNormalizer.toMaudeTerms(fieldName, values);
        String normalizedScalar = normalizedValues.isEmpty() ? "" : normalizedValues.get(0).source();
        switch (fieldName) {
            case "TestRole" -> profile.setTestRole(TestRole.fromValue(scalar));
            case "TLSRole" -> profile.setTlsRole(TLSRole.fromValue(scalar));
            case "LibraryName" -> profile.setLibraryName(stripQuotes(scalar));
            case "LibraryVersion" -> profile.setLibraryVersion(stripQuotes(scalar));
            case "LibraryPath" -> profile.setLibraryPath(stripQuotes(scalar));
            case "CACertificateType" -> profile.setCaCertificateType(normalizedScalar);
            case "CertificateType" -> profile.setCertificateType(normalizedScalar);
            case "PrivateKeyType" -> profile.setPrivateKeyType(normalizedScalar);
            case "CertificateSignatureAlgorithm" ->
                    profile.setCertificateSignatureAlgorithm(firstNormalized(fieldName, normalizedValues));
            case "CACertificateSignatureAlgorithm" ->
                    profile.setCaCertificateSignatureAlgorithm(firstNormalized(fieldName, normalizedValues));
            case "CACertificatePath" -> profile.setCaCertificatePath(stripQuotes(scalar));
            case "CertificatePath" -> profile.setCertificatePath(stripQuotes(scalar));
            case "PrivateKeyPath" -> profile.setPrivateKeyPath(stripQuotes(scalar));
            default -> {
            }
        }
    }

    private static void validateProfileBlock(String profileName, TLSProfile profile) {
        Map<String, List<UserTerm>> rawFields = profile.getRawFields();
        if (TLSProfiles.TESTER.equals(profileName)) {
            for (String fieldName : TARGET_LIBRARY_FIELDS) {
                if (rawFields.containsKey(fieldName)) {
                    throw new IllegalArgumentException("TLSProfiles tester profile must not define "
                            + fieldName + "; library metadata belongs to target");
                }
            }
        }
        if (TLSProfiles.TARGET.equals(profileName) && !rawFields.containsKey("LibraryPath")) {
            throw new IllegalArgumentException("TLSProfiles target profile must define LibraryPath");
        }
        for (String fieldName : REQUIRED_CERTIFICATE_FIELDS) {
            if (!rawFields.containsKey(fieldName)) {
                throw new IllegalArgumentException("TLSProfiles " + profileName
                        + " profile must define " + fieldName);
            }
        }
        if (profile.getCertificateType() != null
                && profile.getPrivateKeyType() != null
                && !profile.getCertificateType().equals(profile.getPrivateKeyType())) {
            throw new IllegalArgumentException("TLSProfiles " + profileName
                    + " profile CertificateType must match PrivateKeyType");
        }
    }

    @Override
    public Object visitProfileValue(TLSProfileParser.ProfileValueContext ctx) {
        if (ctx.listTerm() != null) {
            UserTerm.ListTerm list = (UserTerm.ListTerm) visit(ctx.listTerm());
            return new ArrayList<UserTerm>(list.values());
        }
        List<UserTerm> values = new ArrayList<UserTerm>();
        values.add((UserTerm) visit(ctx.term()));
        return values;
    }

    @Override
    public Object visitTerm(TLSProfileParser.TermContext ctx) {
        return visit(ctx.dottedTerm());
    }

    @Override
    public Object visitDottedTerm(TLSProfileParser.DottedTermContext ctx) {
        List<TLSProfileParser.PrimaryTermContext> parts = ctx.primaryTerm();
        if (parts.size() == 1) {
            return visit(parts.get(0));
        }

        List<UserTerm> renderedParts = new ArrayList<UserTerm>();
        for (TLSProfileParser.PrimaryTermContext part : parts) {
            renderedParts.add((UserTerm) visit(part));
        }
        return new UserTerm.Dotted(renderedParts);
    }

    @Override
    public Object visitPrimaryTerm(TLSProfileParser.PrimaryTermContext ctx) {
        if (ctx.functionTerm() != null) {
            return visit(ctx.functionTerm());
        }
        if (ctx.rawMaudeCall() != null) {
            return new UserTerm.RawMaude((String) visit(ctx.rawMaudeCall()));
        }
        if (ctx.indexedTerm() != null) {
            return visit(ctx.indexedTerm());
        }
        if (ctx.listTerm() != null) {
            return visit(ctx.listTerm());
        }
        if (ctx.braceTerm() != null) {
            return visit(ctx.braceTerm());
        }
        if (ctx.stringLiteral() != null) {
            return new UserTerm.StringLiteral(stripQuotes(ctx.stringLiteral().STRING().getText()));
        }
        if (ctx.identifier() != null) {
            return new UserTerm.Atom(readIdentifier(ctx.identifier()));
        }
        if (ctx.numberLiteral() != null) {
            return new UserTerm.Atom(ctx.numberLiteral().NUMBER().getText());
        }
        return visit(ctx.term());
    }

    @Override
    public Object visitRawMaudeCall(TLSProfileParser.RawMaudeCallContext ctx) {
        return stripQuotes(ctx.stringLiteral().STRING().getText());
    }

    @Override
    public Object visitFunctionTerm(TLSProfileParser.FunctionTermContext ctx) {
        return new UserTerm.Call(readIdentifier(ctx.identifier()), readTermList(ctx.termList()));
    }

    @Override
    public Object visitIndexedTerm(TLSProfileParser.IndexedTermContext ctx) {
        return new UserTerm.Indexed(readIdentifier(ctx.identifier()), (UserTerm) visit(ctx.term()));
    }

    @Override
    public Object visitListTerm(TLSProfileParser.ListTermContext ctx) {
        return new UserTerm.ListTerm(readTermList(ctx.termList()));
    }

    @Override
    public Object visitBraceTerm(TLSProfileParser.BraceTermContext ctx) {
        return new UserTerm.BraceTerm(readTermList(ctx.termList()));
    }

    private List<UserTerm> readTermList(TLSProfileParser.TermListContext ctx) {
        List<UserTerm> terms = new ArrayList<UserTerm>();
        if (ctx == null) {
            return terms;
        }
        for (TLSProfileParser.TermContext termContext : ctx.term()) {
            terms.add((UserTerm) visit(termContext));
        }
        return terms;
    }

    private static String readProfileName(TLSProfileParser.ProfileNameContext ctx) {
        if (ctx.TESTER() != null) {
            return TLSProfiles.TESTER;
        }
        return TLSProfiles.TARGET;
    }

    private static String readIdentifier(TLSProfileParser.IdentifierContext ctx) {
        return ctx.IDENTIFIER().getText();
    }

    private static String stripQuotes(String text) {
        if (text.length() >= 2 && text.startsWith("\"") && text.endsWith("\"")) {
            return text.substring(1, text.length() - 1);
        }
        return text;
    }

    private static UserTerm firstNormalized(String fieldName, List<UserTerm> values) {
        if (values == null || values.size() != 1) {
            throw new IllegalArgumentException("TLSProfiles field " + fieldName
                    + " expects exactly one normalized value");
        }
        return values.get(0);
    }

    private record ProfileEntry(String name, TLSProfile profile) {
    }
}
