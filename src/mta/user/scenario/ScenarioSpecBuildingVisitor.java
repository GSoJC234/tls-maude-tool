package mta.user.scenario;

import mta.user.behavior.BehaviorSpec;
import mta.user.behavior.BehaviorSpecLoader;
import mta.user.behavior.condition.BehaviorCondition;
import mta.user.behavior.condition.BehaviorConditionExpr;
import mta.user.behavior.condition.BehaviorConditionOpr;
import mta.user.behavior.condition.BehaviorConditionTerm;
import mta.user.profile.TLSProfile;
import mta.user.profile.TLSProfileLoader;
import mta.user.scenario.antlr.ScenarioSpecBaseVisitor;
import mta.user.scenario.antlr.ScenarioSpecParser;
import mta.user.scenario.property.ConnectionStep;
import mta.user.scenario.property.NodeBinding;
import mta.user.scenario.property.PropertyRelation;
import mta.user.scenario.property.PropertyTerm;
import mta.user.scenario.property.ScenarioProperty;
import mta.user.scenario.property.operation.Not;
import mta.user.scenario.property.operation.OneOrMoreRepetition;
import mta.user.scenario.property.operation.OneOrMoreStep;
import mta.user.scenario.property.operation.OneStep;
import mta.user.scenario.property.operation.Or;
import mta.user.scenario.property.operation.PropertyRelationOperation;
import mta.user.scenario.property.operation.ZeroOrMoreRepetition;
import mta.user.scenario.property.operation.ZeroOrMoreStep;
import mta.user.scenario.state.SPAnd;
import mta.user.scenario.state.SPNot;
import mta.user.scenario.state.SPOr;
import mta.user.scenario.state.SPXor;
import mta.user.scenario.state.StateProposition;
import mta.user.scenario.state.StatePropositionExpression;
import mta.user.scenario.state.StatePropositionOperator;
import mta.user.scenario.state.StatePropositionTerm;

import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class ScenarioSpecBuildingVisitor extends ScenarioSpecBaseVisitor<Object> {

    private final Path baseDirectory;
    private final TLSProfileLoader tlsProfileLoader;
    private final BehaviorSpecLoader behaviorSpecLoader;
    private ScenarioSpec scenarioSpec;

    public ScenarioSpecBuildingVisitor() {
        this(Path.of(".").toAbsolutePath().normalize());
    }

    public ScenarioSpecBuildingVisitor(Path baseDirectory) {
        this(baseDirectory, new TLSProfileLoader(), new BehaviorSpecLoader());
    }

    public ScenarioSpecBuildingVisitor(Path baseDirectory,
                                       TLSProfileLoader tlsProfileLoader,
                                       BehaviorSpecLoader behaviorSpecLoader) {
        this.baseDirectory = baseDirectory != null ? baseDirectory : Path.of(".").toAbsolutePath().normalize();
        this.tlsProfileLoader = tlsProfileLoader;
        this.behaviorSpecLoader = behaviorSpecLoader;
    }

    @Override
    public Object visitScenarioSpec(ScenarioSpecParser.ScenarioSpecContext ctx) {
        scenarioSpec = new ScenarioSpec();

        for (ScenarioSpecParser.ScenarioItemContext itemContext : ctx.scenarioItem()) {
            visit(itemContext);
        }

        // The grammar places scenario properties after declarations, so
        // relation terms such as IS / FS / AP can be resolved in a final pass.
        for (ScenarioSpecParser.ScenarioPropertiesSectionContext sectionContext : ctx.scenarioPropertiesSection()) {
            visit(sectionContext);
        }

        return scenarioSpec;
    }

    @Override
    public Object visitLoadStatement(ScenarioSpecParser.LoadStatementContext ctx) {
        Path profilePath = resolvePath(ctx.stringLiteral());
        TLSProfile profile = tlsProfileLoader.loadTLSProfile(profilePath);
        scenarioSpec.addProfile(readIdentifier(ctx.identifierValue()), profile);
        return null;
    }

    @Override
    public Object visitUseStatement(ScenarioSpecParser.UseStatementContext ctx) {
        Path behaviorSpecPath = resolvePath(ctx.stringLiteral());
        BehaviorSpec behaviorSpec = behaviorSpecLoader.loadBehaviorSpec(behaviorSpecPath);
        scenarioSpec.addBehaviorSpec(behaviorSpec);
        return null;
    }

    @Override
    public Object visitNodesSection(ScenarioSpecParser.NodesSectionContext ctx) {
        for (ScenarioSpecParser.IdentifierValueContext identifierContext : ctx.identifierValue()) {
            scenarioSpec.addNodeId(readIdentifier(identifierContext));
        }
        return null;
    }

    @Override
    public Object visitConstantsSection(ScenarioSpecParser.ConstantsSectionContext ctx) {
        for (ScenarioSpecParser.ConstantDeclarationContext declarationContext : ctx.constantDeclaration()) {
            @SuppressWarnings("unchecked")
            Map.Entry<String, Set<String>> constantEntry =
                    (Map.Entry<String, Set<String>>) visit(declarationContext);
            scenarioSpec.addConstant(constantEntry.getKey(), constantEntry.getValue());
        }
        return null;
    }

    @Override
    public Object visitConstantDeclaration(ScenarioSpecParser.ConstantDeclarationContext ctx) {
        return new AbstractMap.SimpleEntry<String, Set<String>>(
                readConstant(ctx.constantRef()),
                readConstantSet(ctx.constantSet())
        );
    }

    @Override
    public Object visitStatePropositionsSection(ScenarioSpecParser.StatePropositionsSectionContext ctx) {
        for (ScenarioSpecParser.StatePropositionDeclarationContext declarationContext : ctx.statePropositionDeclaration()) {
            @SuppressWarnings("unchecked")
            Map.Entry<String, StateProposition> propositionEntry =
                    (Map.Entry<String, StateProposition>) visit(declarationContext);
            scenarioSpec.addStateProposition(propositionEntry.getKey(), propositionEntry.getValue());
        }
        return null;
    }

    @Override
    public Object visitStatePropositionDeclaration(ScenarioSpecParser.StatePropositionDeclarationContext ctx) {
        return new AbstractMap.SimpleEntry<String, StateProposition>(
                readIdentifier(ctx.identifierValue()),
                (StateProposition) visit(ctx.statePropositionExpr())
        );
    }

    @Override
    public Object visitStatePropositionExpr(ScenarioSpecParser.StatePropositionExprContext ctx) {
        if (ctx.statePropositionTerm() != null) {
            return visit(ctx.statePropositionTerm());
        }
        if (ctx.NOT() != null) {
            StatePropositionExpression expression = new StatePropositionExpression();
            expression.setOperator(new SPNot());
            expression.setLeftExpression((StatePropositionExpression) visit(ctx.statePropositionExpr(0)));
            return expression;
        }
        if (ctx.LPAREN() != null) {
            return visit(ctx.statePropositionExpr(0));
        }

        StatePropositionExpression leftExpression = (StatePropositionExpression) visit(ctx.statePropositionExpr(0));
        StatePropositionExpression rightExpression = (StatePropositionExpression) visit(ctx.statePropositionExpr(1));
        return combineStateExpression(leftExpression, rightExpression, readStateOperator(ctx));
    }

    @Override
    public Object visitStatePropositionTerm(ScenarioSpecParser.StatePropositionTermContext ctx) {
        return new StatePropositionTerm(
                readIdentifier(ctx.identifierValue()),
                new BehaviorCondition((BehaviorConditionExpr) visit(ctx.conditionExpr()))
        );
    }

    @Override
    public Object visitActionPropositionsSection(ScenarioSpecParser.ActionPropositionsSectionContext ctx) {
        for (ScenarioSpecParser.ActionPropositionDeclarationContext declarationContext : ctx.actionPropositionDeclaration()) {
            @SuppressWarnings("unchecked")
            Map.Entry<String, ActionProposition> propositionEntry =
                    (Map.Entry<String, ActionProposition>) visit(declarationContext);
            scenarioSpec.addActionProposition(propositionEntry.getKey(), propositionEntry.getValue());
        }
        return null;
    }

    @Override
    public Object visitActionPropositionDeclaration(ScenarioSpecParser.ActionPropositionDeclarationContext ctx) {
        return new AbstractMap.SimpleEntry<String, ActionProposition>(
                readIdentifier(ctx.identifierValue()),
                (ActionProposition) visit(ctx.actionInvocation())
        );
    }

    @Override
    public Object visitActionInvocation(ScenarioSpecParser.ActionInvocationContext ctx) {
        ActionProposition actionProposition = new ActionProposition();
        actionProposition.setActionId(readIdentifier(ctx.identifierValue()));
        for (ScenarioSpecParser.ActionArgumentContext argumentContext : ctx.actionArgument()) {
            actionProposition.addArgument((String) visit(argumentContext));
        }
        return actionProposition;
    }

    @Override
    public Object visitActionArgument(ScenarioSpecParser.ActionArgumentContext ctx) {
        if (ctx.constantRef() != null) {
            return readConstant(ctx.constantRef());
        }
        return readIdentifier(ctx.identifierValue());
    }

    @Override
    public Object visitScenarioPropertiesSection(ScenarioSpecParser.ScenarioPropertiesSectionContext ctx) {
        for (ScenarioSpecParser.ScenarioPropertyDeclarationContext declarationContext : ctx.scenarioPropertyDeclaration()) {
            scenarioSpec.addScenarioProperty((ScenarioProperty) visit(declarationContext));
        }
        return null;
    }

    @Override
    public Object visitScenarioPropertyDeclaration(ScenarioSpecParser.ScenarioPropertyDeclarationContext ctx) {
        ScenarioProperty scenarioProperty = new ScenarioProperty();
        scenarioProperty.setConnectorId(readIdentifier(ctx.identifierValue()));

        for (ScenarioSpecParser.NodeBindingContext bindingContext : ctx.nodeBinding()) {
            scenarioProperty.addNodeBinding((NodeBinding) visit(bindingContext));
        }

        ConnectionStep propertyRoot = new ConnectionStep();
        propertyRoot.setPropertyRelation((PropertyRelation) visit(ctx.propertyRelation()));
        scenarioProperty.addStep(propertyRoot);
        return scenarioProperty;
    }

    @Override
    public Object visitNodeBinding(ScenarioSpecParser.NodeBindingContext ctx) {
        return new NodeBinding(
                readIdentifier(ctx.identifierValue(0)),
                readIdentifier(ctx.identifierValue(1))
        );
    }

    @Override
    public Object visitPropertyRelation(ScenarioSpecParser.PropertyRelationContext ctx) {
        if (ctx.identifierValue() != null) {
            return readPropertyTerm(ctx.identifierValue());
        }
        if (ctx.NOT() != null) {
            return combinePropertyRelation(
                    (PropertyRelation) visit(ctx.propertyRelation(0)),
                    null,
                    new Not()
            );
        }
        if (ctx.LPAREN() != null) {
            return visit(ctx.propertyRelation(0));
        }
        if (ctx.ZERO_OR_MORE() != null || ctx.ONE_OR_MORE() != null) {
            PropertyRelationOperation operation = ctx.ZERO_OR_MORE() != null
                    ? new ZeroOrMoreRepetition()
                    : new OneOrMoreRepetition();
            return combinePropertyRelation((PropertyRelation) visit(ctx.propertyRelation(0)), null, operation);
        }

        PropertyRelation leftRelation = (PropertyRelation) visit(ctx.propertyRelation(0));
        PropertyRelation rightRelation = (PropertyRelation) visit(ctx.propertyRelation(1));
        return combinePropertyRelation(leftRelation, rightRelation, readPropertyOperation(ctx));
    }

    @Override
    public Object visitConditionExpr(ScenarioSpecParser.ConditionExprContext ctx) {
        if (ctx.conditionPredicate() != null) {
            return visit(ctx.conditionPredicate());
        }
        if (ctx.NOT() != null) {
            BehaviorConditionExpr expression = new BehaviorConditionExpr();
            expression.setOperator(BehaviorConditionOpr.not);
            expression.setLeftExpression((BehaviorConditionExpr) visit(ctx.conditionExpr(0)));
            return expression;
        }
        if (ctx.LPAREN() != null) {
            return visit(ctx.conditionExpr(0));
        }

        BehaviorConditionExpr leftExpression = (BehaviorConditionExpr) visit(ctx.conditionExpr(0));
        BehaviorConditionExpr rightExpression = (BehaviorConditionExpr) visit(ctx.conditionExpr(1));

        if (ctx.OR() != null) {
            return combineConditionExpression(leftExpression, rightExpression, BehaviorConditionOpr.or);
        }
        if (ctx.XOR() != null) {
            return combineConditionExpression(leftExpression, rightExpression, BehaviorConditionOpr.xor);
        }
        return combineConditionExpression(leftExpression, rightExpression, BehaviorConditionOpr.and);
    }

    @Override
    public Object visitConditionPredicate(ScenarioSpecParser.ConditionPredicateContext ctx) {
        return new BehaviorConditionTerm(
                readIdentifier(ctx.valueAccessor().identifierValue()),
                readConditionOperand(ctx.conditionOperand())
        );
    }

    private static BehaviorConditionExpr combineConditionExpression(BehaviorConditionExpr leftExpression,
                                                                    BehaviorConditionExpr rightExpression,
                                                                    BehaviorConditionOpr operator) {
        BehaviorConditionExpr expression = new BehaviorConditionExpr();
        expression.setLeftExpression(leftExpression);
        expression.setRightExpression(rightExpression);
        expression.setOperator(operator);
        return expression;
    }

    private static StatePropositionExpression combineStateExpression(StatePropositionExpression leftExpression,
                                                                    StatePropositionExpression rightExpression,
                                                                    StatePropositionOperator operator) {
        StatePropositionExpression expression = new StatePropositionExpression();
        expression.setLeftExpression(leftExpression);
        expression.setRightExpression(rightExpression);
        expression.setOperator(operator);
        return expression;
    }

    private static PropertyRelation combinePropertyRelation(PropertyRelation leftRelation,
                                                            PropertyRelation rightRelation,
                                                            PropertyRelationOperation operator) {
        PropertyRelation relation = new PropertyRelation();
        relation.setLeftRelation(leftRelation);
        relation.setRightRelation(rightRelation);
        relation.setOperation(operator);
        return relation;
    }

    private PropertyRelationOperation readPropertyOperation(ScenarioSpecParser.PropertyRelationContext ctx) {
        if (ctx.STEP_ZERO_OR_MORE() != null) {
            return new ZeroOrMoreStep();
        }
        if (ctx.STEP_ONE_OR_MORE() != null) {
            return new OneOrMoreStep();
        }
        if (ctx.STEP_ONE() != null) {
            return new OneStep();
        }
        return new Or();
    }

    private StatePropositionOperator readStateOperator(ScenarioSpecParser.StatePropositionExprContext ctx) {
        if (ctx.OR() != null) {
            return new SPOr();
        }
        if (ctx.XOR() != null) {
            return new SPXor();
        }
        return new SPAnd();
    }

    private PropertyTerm readPropertyTerm(ScenarioSpecParser.IdentifierValueContext ctx) {
        String referenceId = readIdentifier(ctx);
        boolean isState = scenarioSpec.getStatePropositions().containsKey(referenceId);
        boolean isAction = scenarioSpec.getActionPropositions().containsKey(referenceId);

        if (isState && isAction) {
            throw new IllegalArgumentException("Ambiguous property reference: " + referenceId);
        }
        if (isState) {
            return PropertyTerm.stateProposition(referenceId);
        }
        if (isAction) {
            return PropertyTerm.actionProposition(referenceId);
        }
        throw new IllegalArgumentException("Unknown property reference: " + referenceId);
    }

    private Path resolvePath(ScenarioSpecParser.StringLiteralContext ctx) {
        Path path = Path.of(stripQuotes(ctx.STRING().getText()));
        if (path.isAbsolute()) {
            return path.normalize();
        }
        return baseDirectory.resolve(path).normalize();
    }

    private static Set<String> readConstantSet(ScenarioSpecParser.ConstantSetContext ctx) {
        Set<String> constants = new LinkedHashSet<String>();
        for (ScenarioSpecParser.IdentifierValueContext valueContext : ctx.identifierValue()) {
                constants.add(readIdentifier(valueContext));
        }
        return constants;
    }

    private static String readConditionOperand(ScenarioSpecParser.ConditionOperandContext ctx) {
        if (ctx.constantRef() != null) {
            return readConstant(ctx.constantRef());
        }
        if (ctx.identifierValue() != null) {
            return readIdentifier(ctx.identifierValue());
        }
        return stripQuotes(ctx.stringLiteral().STRING().getText());
    }

    private static String readConstant(ScenarioSpecParser.ConstantRefContext ctx) {
        return ctx.CONSTANT_REF().getText();
    }

    private static String readIdentifier(ScenarioSpecParser.IdentifierValueContext ctx) {
        return ctx.IDENTIFIER().getText();
    }

    private static String stripQuotes(String raw) {
        if (raw == null || raw.length() < 2) {
            return raw;
        }
        if (raw.charAt(0) == '"' && raw.charAt(raw.length() - 1) == '"') {
            return raw.substring(1, raw.length() - 1);
        }
        return raw;
    }
}
