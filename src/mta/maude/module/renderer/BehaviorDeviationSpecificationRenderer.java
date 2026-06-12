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
    private final BehaviorValueLowerer behaviorValueLowerer;

    public BehaviorDeviationSpecificationRenderer() {
        this(new BehaviorSpecExpander(), new ActionExpressionRenderer(), new MaudeUserTermRenderer());
    }

    public BehaviorDeviationSpecificationRenderer(BehaviorSpecExpander expander,
                                                  ActionExpressionRenderer actionExpressionRenderer,
                                                  MaudeUserTermRenderer termRenderer) {
        this.expander = expander;
        this.actionExpressionRenderer = actionExpressionRenderer;
        this.behaviorValueLowerer = new BehaviorValueLowerer(termRenderer);
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
                    + behaviorValueLowerer.renderMessageField(setMessage.field())
                    + ", "
                    + behaviorValueLowerer.renderMessageValue(setMessage.field(), setMessage.value())
                    + ")";
        }
        if (modification instanceof BehaviorModificationSpec.SetFeature setFeature) {
            return "setF("
                    + behaviorValueLowerer.renderAttribute(setFeature.field())
                    + ", "
                    + behaviorValueLowerer.renderAttributeValue(setFeature.field(), setFeature.value())
                    + ")";
        }
        if (modification instanceof BehaviorModificationSpec.AddMessage addMessage) {
            return "add("
                    + behaviorValueLowerer.renderMessageField(addMessage.field())
                    + ", "
                    + behaviorValueLowerer.renderMessageValue(addMessage.field(), addMessage.value())
                    + ")";
        }
        if (modification instanceof BehaviorModificationSpec.RemoveMessage removeMessage) {
            return "remove(" + behaviorValueLowerer.renderMessageField(removeMessage.field()) + ")";
        }
        if (modification instanceof BehaviorModificationSpec.NoCheck noCheck) {
            return "noCheck(" + behaviorValueLowerer.renderNoCheckLabel(noCheck.label()) + ")";
        }
        if (modification instanceof BehaviorModificationSpec.Skip) {
            return "skip()";
        }
        if (modification instanceof BehaviorModificationSpec.Delay delay) {
            throw behaviorValueLowerer.unsupportedModification("delay",
                    "current Maude behavior semantics do not apply delay("
                            + delay.handshakeType().source()
                            + ")");
        }
        if (modification instanceof BehaviorModificationSpec.RawMaude rawMaude) {
            return rawMaude.text();
        }
        throw new IllegalArgumentException("Unsupported behavior modification: " + modification);
    }
}
