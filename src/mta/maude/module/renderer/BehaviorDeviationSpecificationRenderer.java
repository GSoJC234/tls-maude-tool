package mta.maude.module.renderer;

import mta.user.behavior.BehaviorDeviationSpecification;
import mta.user.behavior.BehaviorModificationSpec;
import mta.user.behavior.BehaviorSpec;
import mta.user.behavior.BehaviorSpecExpander;
import mta.user.valuedomain.ValueDomainsSpec;

import java.util.List;
import java.util.StringJoiner;

public class BehaviorDeviationSpecificationRenderer {

    private final BehaviorSpecExpander expander;
    private final ActionExpressionRenderer actionExpressionRenderer;
    private final BehaviorValueToMaudeConverter behaviorValueToMaudeConverter;

    public BehaviorDeviationSpecificationRenderer() {
        this(new BehaviorSpecExpander(), new ActionExpressionRenderer(), new MaudeUserTermRenderer());
    }

    public BehaviorDeviationSpecificationRenderer(BehaviorSpecExpander expander,
                                                  ActionExpressionRenderer actionExpressionRenderer,
                                                  MaudeUserTermRenderer termRenderer) {
        this.expander = expander;
        this.actionExpressionRenderer = actionExpressionRenderer;
        this.behaviorValueToMaudeConverter = new BehaviorValueToMaudeConverter(termRenderer);
    }

    public String renderEq(String opName, BehaviorDeviationSpecification specification) {
        return renderEq(opName, specification, null);
    }

    public String renderEq(String opName,
                           BehaviorDeviationSpecification specification,
                           ValueDomainsSpec valueDomains) {
        return "  op "
                + opName
                + " : -> Set{BehaviorDVSpec} ."
                + System.lineSeparator()
                + "  eq "
                + opName
                + " = "
                + render(specification, valueDomains)
                + " .";
    }

    public String render(BehaviorDeviationSpecification specification) {
        return render(specification, null);
    }

    public String render(BehaviorDeviationSpecification specification, ValueDomainsSpec valueDomains) {
        List<BehaviorSpec> expanded = expander.expand(specification, valueDomains);
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
                    + behaviorValueToMaudeConverter.renderMessageField(setMessage.field())
                    + ", "
                    + behaviorValueToMaudeConverter.renderMessageValue(setMessage.field(), setMessage.value())
                    + ")";
        }
        if (modification instanceof BehaviorModificationSpec.SetFeature setFeature) {
            return "setF("
                    + behaviorValueToMaudeConverter.renderAttribute(setFeature.field())
                    + ", "
                    + behaviorValueToMaudeConverter.renderAttributeValue(setFeature.field(), setFeature.value())
                    + ")";
        }
        if (modification instanceof BehaviorModificationSpec.AddMessage addMessage) {
            return "add("
                    + behaviorValueToMaudeConverter.renderMessageField(addMessage.field())
                    + ", "
                    + behaviorValueToMaudeConverter.renderMessageValue(addMessage.field(), addMessage.value())
                    + ")";
        }
        if (modification instanceof BehaviorModificationSpec.RemoveMessage removeMessage) {
            return "remove(" + behaviorValueToMaudeConverter.renderMessageField(removeMessage.field()) + ")";
        }
        if (modification instanceof BehaviorModificationSpec.NoCheck noCheck) {
            return "noCheck(" + behaviorValueToMaudeConverter.renderNoCheckLabel(noCheck.label()) + ")";
        }
        if (modification instanceof BehaviorModificationSpec.Skip) {
            return "skip()";
        }
        if (modification instanceof BehaviorModificationSpec.Delay delay) {
            throw behaviorValueToMaudeConverter.unsupportedModification("delay",
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
