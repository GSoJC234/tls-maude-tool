package mta.maude.module.section;

import mta.maude.constant.*;
import mta.maude.module.ModuleSectionRenderer;
import mta.user.profile.TLSProfile;
import mta.user.scenario.ScenarioSpec;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Function;

public class ProfilesSectionRenderer implements ModuleSectionRenderer {

    private static final String ITEM_INDENT = "                ";

    @Override
    public String placeholder() {
        return "profiles";
    }

    @Override
    public String render(ScenarioSpec scenarioSpec) {
        if (scenarioSpec.getProfiles().isEmpty()) {
            return "  --- no profiles loaded";
        }

        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        for (Map.Entry<String, TLSProfile> entry : scenarioSpec.getProfiles().entrySet()) {
            joiner.add("  op " + entry.getKey() + " : -> TLSConfiguration [ctor] .");
            addTLSProfiles(entry.getKey(), entry.getValue(), joiner);
            joiner.add("");
        }
        return joiner.toString();
    }

    private void addTLSProfiles(String profileId, TLSProfile profile, StringJoiner joiner) {
        List<String> configurationItems = new ArrayList<String>();

        addItem(configurationItems, "iversion", profile.getVersion(), ProtocolVersion::maudeTerm);
        addItem(configurationItems, "icipherSuites", profile.getCipherSuites(), CipherSuite::maudeTerm);
        addItem(configurationItems, "icertificateTypes", profile.getCertificateTypes(), CertificateType::maudeTerm);
        addItem(configurationItems, "icertificateAlgos", profile.getCertificateAlgos(), SignatureAlgorithm::maudeTerm);
        addItem(configurationItems, "icompressions", profile.getCompressions(), CompressionMethod::maudeTerm);
        addItem(configurationItems, "isupported-versions", profile.getSupportedVersions(), SupportedVersion::maudeTerm);
        addItem(configurationItems, "isignature-algorithms", profile.getSignatureAlgorithms(), SignatureAlgorithm::maudeTerm);
        addItem(configurationItems, "isupported-groups", profile.getSupportedGroups(), NamedGroup::maudeTerm);
        addItem(configurationItems, "ipsk-key-exchange-modes", profile.getPskKeyExchangeModes(), PskKeyExchangeMode::maudeTerm);
        addItem(configurationItems, "ikey-shares", profile.getKeyShares(), NamedGroup::maudeTerm);

        addFlag(configurationItems, "icertificateRequest", profile.getCertificateRequest());
        addFlag(configurationItems, "inewSessionTicketReq", profile.isNewSessionTicketReq());
        addFlag(configurationItems, "inewSessionTicketWait", profile.isNewSessionTicketWait());
        addFlag(configurationItems, "iearlyDataReq", profile.isEarlyDataReq());
        addFlag(configurationItems, "ipostClientAuthReq", profile.isPostClientAuthReq());
        addFlag(configurationItems, "ikeyUpdateReq", profile.isKeyUpdateReq());
        addFlag(configurationItems, "ikeyUpdateWait", profile.isKeyUpdateWait());

        if (configurationItems.isEmpty()) {
            joiner.add("  eq " + profileId + " = none .");
            return;
        }

        joiner.add("  eq " + profileId + " = ");
        for (int index = 0; index < configurationItems.size(); index++) {
            String suffix = index == configurationItems.size() - 1 ? " ." : "";
            joiner.add(ITEM_INDENT + configurationItems.get(index) + suffix);
        }
    }

    private static <T> void addItem(List<String> configurationItems,
                                    String functionName,
                                    T value,
                                    Function<T, String> valueRenderer) {
        if (value == null) {
            return;
        }
        configurationItems.add(functionName + "(" + valueRenderer.apply(value) + ")");
    }

    private static <T> void addItem(List<String> configurationItems,
                                    String functionName,
                                    List<T> values,
                                    Function<T, String> valueRenderer) {
        if (values == null || values.isEmpty()) {
            return;
        }
        configurationItems.add(functionName + "(" + renderTerms(values, valueRenderer) + ")");
    }

    private static void addFlag(List<String> configurationItems, String functionName, Boolean enabled) {
        if (Boolean.TRUE.equals(enabled)) {
            configurationItems.add(functionName);
        }
    }

    private static <T> String renderTerms(List<T> values, Function<T, String> valueRenderer) {
        if (values.size() == 1) {
            return valueRenderer.apply(values.get(0));
        }

        StringJoiner joiner = new StringJoiner(" ", "(", ")");
        for (T value : values) {
            joiner.add(valueRenderer.apply(value));
        }
        return joiner.toString();
    }
}
