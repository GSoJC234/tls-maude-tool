package mta.user.profile;

import mta.maude.constant.CertificateType;
import mta.maude.constant.CipherSuite;
import mta.maude.constant.CompressionMethod;
import mta.maude.constant.NamedGroup;
import mta.maude.constant.ProtocolVersion;
import mta.maude.constant.PskKeyExchangeMode;
import mta.maude.constant.SignatureAlgorithm;
import mta.maude.constant.SupportedVersion;
import mta.user.profile.TestRole;
import mta.user.profile.antlr.TLSProfileBaseVisitor;
import mta.user.profile.antlr.TLSProfileParser;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TLSProfileBuildingVisitor extends TLSProfileBaseVisitor<TLSProfile> {

    private final TLSProfile profile = new TLSProfile();

    @Override
    public TLSProfile visitProfile(TLSProfileParser.ProfileContext ctx) {
        for (TLSProfileParser.ProfileEntryContext entry : ctx.profileEntry()) {
            visit(entry);
        }
        return profile;
    }

    @Override
    public TLSProfile visitTestRoleEntry(TLSProfileParser.TestRoleEntryContext ctx) {
        profile.setTestRole(parseEnum(TestRole.class, readScalar(ctx.scalarValue())));
        return profile;
    }

    @Override
    public TLSProfile visitTlsRoleEntry(TLSProfileParser.TlsRoleEntryContext ctx) {
        profile.setTlsRole(parseEnum(TLSRole.class, readScalar(ctx.scalarValue())));
        return profile;
    }

    @Override
    public TLSProfile visitVersionEntry(TLSProfileParser.VersionEntryContext ctx) {
        profile.setVersion(parseEnum(ProtocolVersion.class, readScalar(ctx.scalarValue())));
        return profile;
    }

    @Override
    public TLSProfile visitCipherSuitesEntry(TLSProfileParser.CipherSuitesEntryContext ctx) {
        profile.setCipherSuites(parseEnumList(ctx.valueList(), CipherSuite.class));
        return profile;
    }

    @Override
    public TLSProfile visitCompressionsEntry(TLSProfileParser.CompressionsEntryContext ctx) {
        profile.setCompressions(parseEnumList(ctx.valueList(), CompressionMethod.class));
        return profile;
    }

    @Override
    public TLSProfile visitCertificateTypesEntry(TLSProfileParser.CertificateTypesEntryContext ctx) {
        profile.setCertificateTypes(parseEnumList(ctx.valueList(), CertificateType.class));
        return profile;
    }

    @Override
    public TLSProfile visitCertificateAlgosEntry(TLSProfileParser.CertificateAlgosEntryContext ctx) {
        profile.setCertificateAlgos(parseEnumList(ctx.valueList(), SignatureAlgorithm.class));
        return profile;
    }

    @Override
    public TLSProfile visitCertificateEntry(TLSProfileParser.CertificateEntryContext ctx) {
        profile.setCertificatePath(parsePath(ctx.pathValue()));
        return profile;
    }

    @Override
    public TLSProfile visitPrivateKeyEntry(TLSProfileParser.PrivateKeyEntryContext ctx) {
        profile.setPrivateKeyPath(parsePath(ctx.pathValue()));
        return profile;
    }

    @Override
    public TLSProfile visitCaCertificateEntry(TLSProfileParser.CaCertificateEntryContext ctx) {
        profile.setCaCertificatePath(parsePath(ctx.pathValue()));
        return profile;
    }

    @Override
    public TLSProfile visitSupportedGroupsEntry(TLSProfileParser.SupportedGroupsEntryContext ctx) {
        profile.setSupportedGroups(parseEnumList(ctx.valueList(), NamedGroup.class));
        return profile;
    }

    @Override
    public TLSProfile visitSignatureAlgorithmsEntry(TLSProfileParser.SignatureAlgorithmsEntryContext ctx) {
        profile.setSignatureAlgorithms(parseEnumList(ctx.valueList(), SignatureAlgorithm.class));
        return profile;
    }

    @Override
    public TLSProfile visitKeySharesEntry(TLSProfileParser.KeySharesEntryContext ctx) {
        profile.setKeyShares(parseEnumList(ctx.valueList(), NamedGroup.class));
        return profile;
    }

    @Override
    public TLSProfile visitSupportedVersionsEntry(TLSProfileParser.SupportedVersionsEntryContext ctx) {
        profile.setSupportedVersions(parseEnumList(ctx.valueList(), SupportedVersion.class));
        return profile;
    }

    @Override
    public TLSProfile visitPskKeyExchangeModesEntry(TLSProfileParser.PskKeyExchangeModesEntryContext ctx) {
        profile.setPskKeyExchangeModes(parseEnumList(ctx.valueList(), PskKeyExchangeMode.class));
        return profile;
    }

    @Override
    public TLSProfile visitNewSessionTicketReqEntry(TLSProfileParser.NewSessionTicketReqEntryContext ctx) {
        profile.setNewSessionTicketReq(parseBoolean(ctx.booleanValue()));
        return profile;
    }

    @Override
    public TLSProfile visitNewSessionTicketWaitEntry(TLSProfileParser.NewSessionTicketWaitEntryContext ctx) {
        profile.setNewSessionTicketWait(parseBoolean(ctx.booleanValue()));
        return profile;
    }

    @Override
    public TLSProfile visitEarlyDataReqEntry(TLSProfileParser.EarlyDataReqEntryContext ctx) {
        profile.setEarlyDataReq(parseBoolean(ctx.booleanValue()));
        return profile;
    }

    @Override
    public TLSProfile visitPostClientAuthReqEntry(TLSProfileParser.PostClientAuthReqEntryContext ctx) {
        profile.setPostClientAuthReq(parseBoolean(ctx.booleanValue()));
        return profile;
    }

    @Override
    public TLSProfile visitKeyUpdateReqEntry(TLSProfileParser.KeyUpdateReqEntryContext ctx) {
        profile.setKeyUpdateReq(parseBoolean(ctx.booleanValue()));
        return profile;
    }

    @Override
    public TLSProfile visitKeyUpdateWaitEntry(TLSProfileParser.KeyUpdateWaitEntryContext ctx) {
        profile.setKeyUpdateWait(parseBoolean(ctx.booleanValue()));
        return profile;
    }

    @Override
    public TLSProfile visitCertificateRequestEntry(TLSProfileParser.CertificateRequestEntryContext ctx) {
        profile.setCertificateRequest(parseBoolean(ctx.booleanValue()));
        return profile;
    }

    @Override
    public TLSProfile visitExecutionConfigurationEntry(TLSProfileParser.ExecutionConfigurationEntryContext ctx) {
        TLSProfile.ExecutionConfiguration executionConfiguration = profile.getExecutionConfiguration();
        if (executionConfiguration == null) {
            executionConfiguration = new TLSProfile.ExecutionConfiguration();
            profile.setExecutionConfiguration(executionConfiguration);
        }

        for (TLSProfileParser.ExecutionConfigurationFieldContext field : ctx.executionConfigurationField()) {
            if (field.executionConfigurationName() != null) {
                executionConfiguration.setName(readScalar(field.executionConfigurationName().scalarValue()));
            } else if (field.executionConfigurationPath() != null) {
                executionConfiguration.setPath(parsePath(field.executionConfigurationPath().pathValue()));
            }
        }

        return profile;
    }

    private static <E extends Enum<E>> List<E> parseEnumList(TLSProfileParser.ValueListContext ctx, Class<E> enumType) {
        List<E> values = new ArrayList<>();
        for (TLSProfileParser.ScalarValueContext valueContext : ctx.scalarValue()) {
            values.add(parseEnum(enumType, readScalar(valueContext)));
        }
        return values;
    }

    private static boolean parseBoolean(TLSProfileParser.BooleanValueContext ctx) {
        String rawValue = ctx.BOOLEAN() != null ? ctx.BOOLEAN().getText() : readScalar(ctx.scalarValue());
        String normalized = rawValue.trim().toLowerCase(Locale.ROOT);
        if ("true".equals(normalized)) {
            return true;
        }
        if ("false".equals(normalized)) {
            return false;
        }
        throw new IllegalArgumentException("Unknown boolean value: " + rawValue);
    }

    private static Path parsePath(TLSProfileParser.PathValueContext ctx) {
        return Path.of(readPath(ctx));
    }

    private static String readPath(TLSProfileParser.PathValueContext ctx) {
        return ctx.STRING() != null ? stripQuotes(ctx.STRING().getText()) : ctx.VALUE().getText();
    }

    private static String readScalar(TLSProfileParser.ScalarValueContext ctx) {
        return ctx.STRING() != null ? stripQuotes(ctx.STRING().getText()) : ctx.VALUE().getText();
    }

    private static <E extends Enum<E>> E parseEnum(Class<E> enumType, String rawValue) {
        return Enum.valueOf(enumType, rawValue);
    }

    private static String stripQuotes(String text) {
        if (text.length() >= 2 && text.startsWith("\"") && text.endsWith("\"")) {
            return text.substring(1, text.length() - 1);
        }
        return text;
    }
}
