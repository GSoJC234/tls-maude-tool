package mta.maude.module.renderer;

import mta.user.behavior.BehaviorDeviationSpecification;
import mta.user.behavior.BehaviorModificationSpec;
import mta.user.behavior.BehaviorSpec;
import mta.user.behavior.BehaviorSpecExpander;

import java.util.List;
import java.util.StringJoiner;

public class BehaviorDeviationSpecificationRenderer {

    private final BehaviorSpecExpander expander;
    private final ActionExpressionRenderer actionExpressionRenderer;
    private final MaudeUserTermRenderer termRenderer;

    public BehaviorDeviationSpecificationRenderer() {
        this(new BehaviorSpecExpander(), new ActionExpressionRenderer(), new MaudeUserTermRenderer());
    }

    public BehaviorDeviationSpecificationRenderer(BehaviorSpecExpander expander,
                                                  ActionExpressionRenderer actionExpressionRenderer,
                                                  MaudeUserTermRenderer termRenderer) {
        this.expander = expander;
        this.actionExpressionRenderer = actionExpressionRenderer;
        this.termRenderer = termRenderer;
    }

    public String renderEq(String opName, BehaviorDeviationSpecification specification) {
        return "  op "
                + opName
                + " : -> Set{BehaviorDVSpec} ."
                + System.lineSeparator()
                + "  eq "
                + opName
                + " = "
                + render(specification)
                + " .";
    }

    public String render(BehaviorDeviationSpecification specification) {
        List<BehaviorSpec> expanded = expander.expand(specification);
        if (expanded.isEmpty()) {
            return "empty";
        }

        StringJoiner joiner = new StringJoiner("," + System.lineSeparator() + "     ");
        for (BehaviorSpec behaviorSpec : expanded) {
            joiner.add(renderBehaviorSpec(behaviorSpec));
        }
        return joiner.toString();
    }

    private String renderBehaviorSpec(BehaviorSpec behaviorSpec) {
        if (behaviorSpec.getActionCondition() == null) {
            throw new IllegalArgumentException("Behavior has no Conditions: "
                    + behaviorSpec.getBehaviorId());
        }
        if (behaviorSpec.getModificationSpecs().isEmpty()) {
            throw new IllegalArgumentException("Behavior has no Modifications: "
                    + behaviorSpec.getBehaviorId());
        }

        return "{'"
                + behaviorSpec.getBehaviorId()
                + ", "
                + actionExpressionRenderer.render(behaviorSpec.getActionCondition())
                + ", "
                + renderModifications(behaviorSpec.getModificationSpecs())
                + "}";
    }

    private String renderModifications(List<BehaviorModificationSpec> modifications) {
        if (modifications.size() == 1) {
            return renderModification(modifications.get(0));
        }

        StringJoiner joiner = new StringJoiner(", ", "(", ")");
        for (BehaviorModificationSpec modification : modifications) {
            joiner.add(renderModification(modification));
        }
        return joiner.toString();
    }

    private String renderModification(BehaviorModificationSpec modification) {
        if (modification instanceof BehaviorModificationSpec.SetMessage setMessage) {
            return "setM("
                    + termRenderer.renderMessageField(setMessage.field())
                    + ", "
                    + termRenderer.renderMessageValue(setMessage.value())
                    + ")";
        }
        if (modification instanceof BehaviorModificationSpec.SetFeature setFeature) {
            return "setF("
                    + termRenderer.renderAttribute(setFeature.field())
                    + ", "
                    + termRenderer.renderAttributeValue(setFeature.value())
                    + ")";
        }
        if (modification instanceof BehaviorModificationSpec.AddMessage addMessage) {
            return "add("
                    + termRenderer.renderMessageField(addMessage.field())
                    + ", "
                    + termRenderer.renderMessageValue(addMessage.value())
                    + ")";
        }
        if (modification instanceof BehaviorModificationSpec.RemoveMessage removeMessage) {
            return "remove(" + termRenderer.renderMessageField(removeMessage.field()) + ")";
        }
        if (modification instanceof BehaviorModificationSpec.NoCheck noCheck) {
            return "noCheck(" + termRenderer.renderTerm(noCheck.label()) + ")";
        }
        if (modification instanceof BehaviorModificationSpec.Skip) {
            return "skip()";
        }
        if (modification instanceof BehaviorModificationSpec.Delay delay) {
            return "delay(" + termRenderer.renderTerm(delay.handshakeType()) + ")";
        }
        if (modification instanceof BehaviorModificationSpec.RawMaude rawMaude) {
            return rawMaude.text();
        }
        throw new IllegalArgumentException("Unsupported behavior modification: " + modification);
    }
}
