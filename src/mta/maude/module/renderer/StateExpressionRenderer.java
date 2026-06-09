package mta.maude.module.renderer;

import mta.user.common.StateExpression;
import mta.user.common.UserTerm;

import java.util.Locale;

public class StateExpressionRenderer {

    private final MaudeUserTermRenderer termRenderer;
    private final BehaviorValueLowerer behaviorValueLowerer;

    public StateExpressionRenderer() {
        this(new MaudeUserTermRenderer());
    }

    public StateExpressionRenderer(MaudeUserTermRenderer termRenderer) {
        this.termRenderer = termRenderer;
        this.behaviorValueLowerer = new BehaviorValueLowerer(termRenderer);
    }

    public String render(StateExpression expression) {
        if (expression instanceof StateExpression.Atom atom) {
            Endpoint endpoint = endpoint(atom.object());
            String attribute = normalizeAttribute(endpoint, atom.attribute());
            return endpoint.maudeTid()
                    + " . "
                    + behaviorValueLowerer.renderAttribute(new UserTerm.Atom(attribute))
                    + " = "
                    + renderAttributeValue(endpoint, attribute, atom.value());
        }
        if (expression instanceof StateExpression.Not not) {
            return "not " + renderOperand(not.expression());
        }
        if (expression instanceof StateExpression.Binary binary) {
            return renderOperand(binary.left())
                    + " "
                    + binary.operator().maudeOperator()
                    + " "
                    + renderOperand(binary.right());
        }
        throw new IllegalArgumentException("Unsupported state expression: " + expression);
    }

    private String renderOperand(StateExpression expression) {
        if (expression instanceof StateExpression.Atom) {
            return render(expression);
        }
        return "(" + render(expression) + ")";
    }

    private Endpoint endpoint(UserTerm object) {
        if (object instanceof UserTerm.Atom atom) {
            return switch (atom.text()) {
                case "client", "tester" -> new Endpoint("client", "N1 . CI", "C");
                case "server", "target" -> new Endpoint("server", "N2 . SI", "S");
                default -> new Endpoint(atom.text(), atom.text(), "");
            };
        }
        return new Endpoint(object.source(), termRenderer.renderTerm(object), "");
    }

    private String normalizeAttribute(Endpoint endpoint, String attribute) {
        String key = attribute.toLowerCase(Locale.ROOT).replace('-', '_');
        if ("alert".equals(key) || "alert_description".equals(key)) {
            return "errorLog";
        }
        if ("state".equals(key)) {
            return switch (endpoint.role()) {
                case "C" -> "clientState";
                case "S" -> "serverState";
                default -> throw new IllegalArgumentException("ScenarioProperty state attribute '"
                        + attribute
                        + "' requires client/tester or server/target endpoint");
            };
        }
        String canonical = behaviorValueLowerer.normalizeAttributeName(attribute);
        String role = roleForStateAttribute(canonical);
        if (!role.isEmpty() && !endpoint.role().isEmpty() && !role.equals(endpoint.role())) {
            throw new IllegalArgumentException("ScenarioProperty state attribute '"
                    + attribute
                    + "' does not match endpoint '"
                    + endpoint.source()
                    + "'");
        }
        return canonical;
    }

    private String renderAttributeValue(Endpoint endpoint, String attribute, UserTerm value) {
        if ("clientState".equals(attribute) || "serverState".equals(attribute)) {
            return "av[" + renderStateValue(endpoint, attribute, value) + "]";
        }
        return behaviorValueLowerer.renderAttributeValue(new UserTerm.Atom(attribute), value);
    }

    private String renderStateValue(Endpoint endpoint, String attribute, UserTerm value) {
        String expectedRole = roleForStateAttribute(attribute);
        if (!expectedRole.isEmpty() && !endpoint.role().isEmpty() && !expectedRole.equals(endpoint.role())) {
            throw new IllegalArgumentException("ScenarioProperty state attribute '"
                    + attribute
                    + "' does not match endpoint '"
                    + endpoint.source()
                    + "'");
        }
        if (value instanceof UserTerm.RawMaude rawMaude) {
            return rawMaude.text();
        }
        if (value instanceof UserTerm.Atom atom) {
            return normalizeStateConstant(endpoint, attribute, atom.text());
        }
        if (value instanceof UserTerm.Call call) {
            String state = call.name().toLowerCase(Locale.ROOT);
            if (!"closed".equals(state) && !"error".equals(state)) {
                throw new IllegalArgumentException("ScenarioProperty state value '"
                        + call.source()
                        + "' must be closed(TLS12), closed(TLS13), error(TLS12), or error(TLS13)");
            }
            if (call.arguments().size() != 1 || !(call.arguments().get(0) instanceof UserTerm.Atom versionAtom)) {
                throw new IllegalArgumentException("ScenarioProperty state value '"
                        + call.source()
                        + "' expects one TLS version argument");
            }
            String role = expectedRole.isEmpty() ? endpoint.role() : expectedRole;
            return protocolStatePrefix(versionAtom.text())
                    + role
                    + "-"
                    + ("closed".equals(state) ? "CLOSED" : "ERROR");
        }
        throw new IllegalArgumentException("Unsupported ScenarioProperty state value: " + value.source());
    }

    private String normalizeStateConstant(Endpoint endpoint, String attribute, String value) {
        String normalized = value.toUpperCase(Locale.ROOT).replace('_', '-');
        if (!normalized.matches("V[23][CS]-(CLOSED|ERROR)")) {
            throw new IllegalArgumentException("ScenarioProperty state value '"
                    + value
                    + "' must be closed(TLS12), closed(TLS13), error(TLS12), error(TLS13), or a V2/V3 state constant");
        }
        String actualRole = normalized.substring(2, 3);
        String expectedRole = roleForStateAttribute(attribute);
        if (expectedRole.isEmpty()) {
            expectedRole = endpoint.role();
        }
        if (!expectedRole.isEmpty() && !expectedRole.equals(actualRole)) {
            throw new IllegalArgumentException("ScenarioProperty state value '"
                    + value
                    + "' does not match endpoint '"
                    + endpoint.source()
                    + "'");
        }
        return normalized;
    }

    private String roleForStateAttribute(String attribute) {
        return switch (attribute) {
            case "clientState" -> "C";
            case "serverState" -> "S";
            default -> "";
        };
    }

    private String protocolStatePrefix(String version) {
        String key = version.toUpperCase(Locale.ROOT).replace("-", "");
        return switch (key) {
            case "TLS12" -> "V2";
            case "TLS13" -> "V3";
            default -> throw new IllegalArgumentException("ScenarioProperty state version '"
                    + version
                    + "' must be TLS12 or TLS13");
        };
    }

    private record Endpoint(String source, String maudeTid, String role) {
    }
}
