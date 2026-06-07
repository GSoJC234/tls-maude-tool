package mta.maude.module.renderer;

import mta.user.common.UserTerm;

import java.util.StringJoiner;

public class MaudeUserTermRenderer {

    public String renderMessageField(UserTerm term) {
        String field = renderBareIdentifier(term, "message field");
        return field.startsWith("#") ? field : "#" + field;
    }

    public String renderAttribute(UserTerm term) {
        String attribute = renderBareIdentifier(term, "attribute");
        return attribute.startsWith("@") ? attribute : "@" + attribute;
    }

    public String renderRuleLabel(UserTerm term) {
        String label = renderTerm(term);
        return label.startsWith("'") ? label : "'" + label;
    }

    public String renderMessageValue(UserTerm term) {
        return "mv[" + renderTerm(term) + "]";
    }

    public String renderAttributeValue(UserTerm term) {
        return "av[" + renderTerm(term) + "]";
    }

    public String renderTerm(UserTerm term) {
        if (term instanceof UserTerm.Atom atom) {
            return atom.text();
        }
        if (term instanceof UserTerm.StringLiteral literal) {
            return literal.source();
        }
        if (term instanceof UserTerm.RawMaude rawMaude) {
            return rawMaude.text();
        }
        if (term instanceof UserTerm.Parameter parameter) {
            throw new IllegalArgumentException("Unexpanded parameter reached Maude renderer: "
                    + parameter.source());
        }
        if (term instanceof UserTerm.Call call) {
            StringJoiner joiner = new StringJoiner(", ");
            for (UserTerm argument : call.arguments()) {
                joiner.add(renderTerm(argument));
            }
            return call.name() + "(" + joiner + ")";
        }
        if (term instanceof UserTerm.ListTerm list) {
            StringJoiner joiner = new StringJoiner(" ");
            for (UserTerm value : list.values()) {
                joiner.add(renderTerm(value));
            }
            return joiner.toString();
        }
        if (term instanceof UserTerm.BraceTerm brace) {
            StringJoiner joiner = new StringJoiner(", ", "{", "}");
            for (UserTerm value : brace.values()) {
                joiner.add(renderTerm(value));
            }
            return joiner.toString();
        }
        if (term instanceof UserTerm.Indexed indexed) {
            return indexed.name() + "[" + renderTerm(indexed.index()) + "]";
        }
        if (term instanceof UserTerm.Dotted dotted) {
            StringJoiner joiner = new StringJoiner(" . ");
            for (UserTerm part : dotted.parts()) {
                joiner.add(renderTerm(part));
            }
            return joiner.toString();
        }
        throw new IllegalArgumentException("Unsupported user term: " + term);
    }

    private String renderBareIdentifier(UserTerm term, String context) {
        if (term instanceof UserTerm.Atom atom) {
            return atom.text();
        }
        throw new IllegalArgumentException("Expected " + context + " identifier, got: " + term.source());
    }
}
