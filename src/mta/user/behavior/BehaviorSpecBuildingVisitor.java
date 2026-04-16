package mta.user.behavior;

import mta.user.behavior.antlr.BehaviorSpecBaseVisitor;
import mta.user.behavior.antlr.BehaviorSpecParser;
import mta.user.behavior.condition.BehaviorCondition;
import mta.user.behavior.condition.BehaviorConditionExpr;
import mta.user.behavior.condition.BehaviorConditionOpr;
import mta.user.behavior.condition.BehaviorConditionTerm;
import mta.user.behavior.modification.BehaviorModification;
import mta.user.behavior.modification.item.BMAdd;
import mta.user.behavior.modification.item.BMDelay;
import mta.user.behavior.modification.item.BMNoCheck;
import mta.user.behavior.modification.item.BMRemove;
import mta.user.behavior.modification.item.BMSet;
import mta.user.behavior.modification.item.BMSkip;
import mta.user.behavior.modification.item.BehaviorModificationItem;
import mta.user.behavior.modification.item.value.BMFBytes;
import mta.user.behavior.modification.item.value.BMFOneOf;
import mta.user.behavior.modification.item.value.BehaviorModificationOperand;
import mta.user.behavior.modification.item.value.BehaviorModificationValue;

import java.util.ArrayList;
import java.util.List;

public class BehaviorSpecBuildingVisitor extends BehaviorSpecBaseVisitor<Object> {

    @Override
    public Object visitBehaviorSpec(BehaviorSpecParser.BehaviorSpecContext ctx) {
        BehaviorSpec behaviorSpec = new BehaviorSpec();
        behaviorSpec.setBehaviorId((String) visit(ctx.behaviorIdSection()));

        if (ctx.parametersSection() != null) {
            @SuppressWarnings("unchecked")
            List<String> parameters = (List<String>) visit(ctx.parametersSection());
            behaviorSpec.setParameters(parameters);
        }

        behaviorSpec.setEventType((EventType) visit(ctx.eventTypeSection()));
        behaviorSpec.setConditions((BehaviorCondition) visit(ctx.conditionsSection()));
        behaviorSpec.setModifications((BehaviorModification) visit(ctx.modificationSection()));
        return behaviorSpec;
    }

    @Override
    public Object visitBehaviorIdSection(BehaviorSpecParser.BehaviorIdSectionContext ctx) {
        return readIdentifier(ctx.identifierValue());
    }

    @Override
    public Object visitParametersSection(BehaviorSpecParser.ParametersSectionContext ctx) {
        List<String> parameters = new ArrayList<String>();
        for (BehaviorSpecParser.ParameterRefContext parameterRefContext : ctx.parameterRef()) {
            parameters.add(readParameter(parameterRefContext));
        }
        return parameters;
    }

    @Override
    public Object visitEventTypeSection(BehaviorSpecParser.EventTypeSectionContext ctx) {
        return EventType.fromValue(readIdentifier(ctx.identifierValue()));
    }

    @Override
    public Object visitConditionsSection(BehaviorSpecParser.ConditionsSectionContext ctx) {
        BehaviorCondition condition = new BehaviorCondition();
        condition.setExpression((BehaviorConditionExpr) visit(ctx.conditionExpr()));
        return condition;
    }

    @Override
    public Object visitConditionExpr(BehaviorSpecParser.ConditionExprContext ctx) {
        if (ctx.conditionPredicate() != null) {
            return visit(ctx.conditionPredicate());
        }
        if (ctx.NOT() != null) {
            BehaviorConditionExpr expression = new BehaviorConditionExpr();
            expression.setOperator(BehaviorConditionOpr.not);
            expression.setLeftExpression((BehaviorConditionExpr) visit(ctx.conditionExpr(0)));
            return expression;
        }

        BehaviorConditionExpr leftExpression = (BehaviorConditionExpr) visit(ctx.conditionExpr(0));
        BehaviorConditionExpr rightExpression = (BehaviorConditionExpr) visit(ctx.conditionExpr(1));

        if (ctx.OR() != null) {
            return combine(leftExpression, rightExpression, BehaviorConditionOpr.or);
        }
        if (ctx.XOR() != null) {
            return combine(leftExpression, rightExpression, BehaviorConditionOpr.xor);
        }
        return combine(leftExpression, rightExpression, BehaviorConditionOpr.and);
    }

    @Override
    public Object visitConditionPredicate(BehaviorSpecParser.ConditionPredicateContext ctx) {
        return new BehaviorConditionTerm(
                readIdentifier(ctx.valueAccessor().identifierValue()),
                readOperand(ctx.operandValue())
        );
    }

    @Override
    public Object visitModificationSection(BehaviorSpecParser.ModificationSectionContext ctx) {
        BehaviorModification behaviorModification = new BehaviorModification();
        for (BehaviorSpecParser.ModificationStatementContext statementContext : ctx.modificationStatement()) {
            behaviorModification.add((BehaviorModificationItem) visit(statementContext));
        }
        return behaviorModification;
    }

    @Override
    public Object visitModificationStatement(BehaviorSpecParser.ModificationStatementContext ctx) {
        if (ctx.addModification() != null) {
            return visit(ctx.addModification());
        }
        if (ctx.setModification() != null) {
            return visit(ctx.setModification());
        }
        if (ctx.removeModification() != null) {
            return visit(ctx.removeModification());
        }
        if (ctx.noCheckModification() != null) {
            return visit(ctx.noCheckModification());
        }
        if (ctx.skipModification() != null) {
            return visit(ctx.skipModification());
        }
        return visit(ctx.delayModification());
    }

    @Override
    public Object visitAddModification(BehaviorSpecParser.AddModificationContext ctx) {
        return new BMAdd(readTarget(ctx.targetRef()), readModificationOperand(ctx.modificationValue()));
    }

    @Override
    public Object visitSetModification(BehaviorSpecParser.SetModificationContext ctx) {
        return new BMSet(readTarget(ctx.targetRef()), readModificationOperand(ctx.modificationValue()));
    }

    @Override
    public Object visitRemoveModification(BehaviorSpecParser.RemoveModificationContext ctx) {
        return new BMRemove(readTarget(ctx.targetRef()));
    }

    @Override
    public Object visitNoCheckModification(BehaviorSpecParser.NoCheckModificationContext ctx) {
        return new BMNoCheck(readTarget(ctx.targetRef()));
    }

    @Override
    public Object visitSkipModification(BehaviorSpecParser.SkipModificationContext ctx) {
        return new BMSkip();
    }

    @Override
    public Object visitDelayModification(BehaviorSpecParser.DelayModificationContext ctx) {
        return new BMDelay(readOperand(ctx.operandValue()));
    }

    private static BehaviorConditionExpr combine(BehaviorConditionExpr leftExpression,
                                                 BehaviorConditionExpr rightExpression,
                                                 BehaviorConditionOpr operator) {
        BehaviorConditionExpr expression = new BehaviorConditionExpr();
        expression.setLeftExpression(leftExpression);
        expression.setRightExpression(rightExpression);
        expression.setOperator(operator);
        return expression;
    }

    private static BehaviorModificationOperand readModificationOperand(BehaviorSpecParser.ModificationValueContext ctx) {
        if (ctx.functionCall() != null) {
            return readFunctionCallOperand(ctx.functionCall());
        }
        return new BehaviorModificationValue(readOperand(ctx.operandValue()));
    }

    private static BehaviorModificationOperand readFunctionCallOperand(BehaviorSpecParser.FunctionCallContext ctx) {
        if (ctx.ONEOF() != null) {
            return new BMFOneOf(readParameter(ctx.parameterRef()));
        }
        return new BMFBytes(ctx.hexLiteral().HEX().getText());
    }

    private static String readTarget(BehaviorSpecParser.TargetRefContext ctx) {
        if (ctx.parameterRef() != null) {
            return readParameter(ctx.parameterRef());
        }
        return readIdentifier(ctx.identifierValue());
    }

    private static String readOperand(BehaviorSpecParser.OperandValueContext ctx) {
        if (ctx.parameterRef() != null) {
            return readParameter(ctx.parameterRef());
        }
        return readIdentifier(ctx.identifierValue());
    }

    private static String readParameter(BehaviorSpecParser.ParameterRefContext ctx) {
        return ctx.PARAM_REF().getText();
    }

    private static String readIdentifier(BehaviorSpecParser.IdentifierValueContext ctx) {
        return ctx.IDENTIFIER().getText();
    }
}
