package mta.user.behavior;

import mta.user.behavior.antlr.BehaviorSpecBaseVisitor;
import mta.user.behavior.antlr.BehaviorSpecParser;
import mta.user.behavior.condition.BehaviorCondition;
import mta.user.behavior.condition.BehaviorConditionExpr;
import mta.user.behavior.condition.BehaviorConditionOpr;
import mta.user.behavior.condition.BehaviorConditionTerm;
import mta.user.behavior.modification.BehaviorModification;
import mta.user.behavior.modification.item.BMDelay;
import mta.user.behavior.modification.item.BMNoCheck;
import mta.user.behavior.modification.item.BMDelete;
import mta.user.behavior.modification.item.BMSet;
import mta.user.behavior.modification.item.BMSkip;
import mta.user.behavior.modification.item.BehaviorModificationItem;
import mta.user.behavior.modification.BehaviorModificationValue;

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
        for (BehaviorSpecParser.IdentifierValueContext identifierValueContext : ctx.identifierValue()) {
            parameters.add(readIdentifier(identifierValueContext));
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
                readIdentifier(ctx.fieldValueAccessor().identifierValue()),
                readIdentifier(ctx.valueAccessor().identifierValue())
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
        if (ctx.setModification() != null) {
            return visit(ctx.setModification());
        }
        if (ctx.deleteModification() != null) {
            return visit(ctx.deleteModification());
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
    public Object visitSetModification(BehaviorSpecParser.SetModificationContext ctx) {
        return new BMSet(readIdentifier(ctx.identifierValue()), readModificationValue(ctx.modificationValue()));
    }

    @Override
    public Object visitDeleteModification(BehaviorSpecParser.DeleteModificationContext ctx) {
        return new BMDelete(readIdentifier(ctx.identifierValue()));
    }

    @Override
    public Object visitNoCheckModification(BehaviorSpecParser.NoCheckModificationContext ctx) {
        return new BMNoCheck(readIdentifier(ctx.identifierValue()));
    }

    @Override
    public Object visitSkipModification(BehaviorSpecParser.SkipModificationContext ctx) {
        return new BMSkip();
    }

    @Override
    public Object visitDelayModification(BehaviorSpecParser.DelayModificationContext ctx) {
        return new BMDelay(readIdentifier(ctx.identifierValue()));
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

    private static BehaviorModificationValue readModificationValue(BehaviorSpecParser.ModificationValueContext ctx) {
        if (ctx.hexLiteral() != null) {
            return new BehaviorModificationValue(readHexLiteral(ctx.hexLiteral()));
        }
        return new BehaviorModificationValue(readIdentifier(ctx.identifierValue()));
    }

    private static String readIdentifier(BehaviorSpecParser.IdentifierValueContext ctx) {
        return ctx.IDENTIFIER().getText();
    }

    private static String readHexLiteral(BehaviorSpecParser.HexLiteralContext ctx) {
        return ctx.HEX().getText();
    }
}
