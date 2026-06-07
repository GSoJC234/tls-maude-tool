package mta.user.behavior;

import mta.user.behavior.antlr.BehaviorSpecBaseVisitor;
import mta.user.behavior.antlr.BehaviorSpecParser;
import mta.user.common.ActionExpression;
import mta.user.common.UserTerm;

import java.util.ArrayList;
import java.util.List;

public class BehaviorSpecBuildingVisitor extends BehaviorSpecBaseVisitor<Object> {

    @Override
    public Object visitBehaviorDeviationSpecification(
            BehaviorSpecParser.BehaviorDeviationSpecificationContext ctx) {
        BehaviorDeviationSpecification specification = new BehaviorDeviationSpecification();
        for (BehaviorSpecParser.BehaviorSpecContext behaviorContext : ctx.behaviorSpec()) {
            specification.addBehaviorSpec((BehaviorSpec) visit(behaviorContext));
        }
        return specification;
    }

    @Override
    public Object visitBehaviorSpec(BehaviorSpecParser.BehaviorSpecContext ctx) {
        BehaviorSpec behaviorSpec = new BehaviorSpec();
        behaviorSpec.setBehaviorId((String) visit(ctx.behaviorIdSection()));

        if (ctx.parametersSection() != null) {
            @SuppressWarnings("unchecked")
            List<String> parameters = (List<String>) visit(ctx.parametersSection());
            behaviorSpec.setParameters(parameters);
        }

        behaviorSpec.setActionCondition((ActionExpression) visit(ctx.conditionsSection()));

        @SuppressWarnings("unchecked")
        List<BehaviorModificationSpec> modifications =
                (List<BehaviorModificationSpec>) visit(ctx.modificationsSection());
        behaviorSpec.setModificationSpecs(modifications);

        if (ctx.parameterInstancesSection() != null) {
            @SuppressWarnings("unchecked")
            List<BehaviorParameterInstance> instances =
                    (List<BehaviorParameterInstance>) visit(ctx.parameterInstancesSection());
            behaviorSpec.setParameterInstances(instances);
        }

        return behaviorSpec;
    }

    @Override
    public Object visitBehaviorIdSection(BehaviorSpecParser.BehaviorIdSectionContext ctx) {
        return readIdentifier(ctx.identifier());
    }

    @Override
    public Object visitParametersSection(BehaviorSpecParser.ParametersSectionContext ctx) {
        List<String> parameters = new ArrayList<String>();
        for (BehaviorSpecParser.ParameterNameContext parameterContext : ctx.parameterName()) {
            parameters.add(readParameterName(parameterContext));
        }
        return parameters;
    }

    @Override
    public Object visitConditionsSection(BehaviorSpecParser.ConditionsSectionContext ctx) {
        return visit(ctx.actionExpr());
    }

    @Override
    public Object visitActionExpr(BehaviorSpecParser.ActionExprContext ctx) {
        return visit(ctx.actionOr());
    }

    @Override
    public Object visitActionOr(BehaviorSpecParser.ActionOrContext ctx) {
        ActionExpression expression = (ActionExpression) visit(ctx.actionAnd(0));
        for (int index = 1; index < ctx.actionAnd().size(); index++) {
            expression = new ActionExpression.Binary(
                    ActionExpression.Operator.OR,
                    expression,
                    (ActionExpression) visit(ctx.actionAnd(index))
            );
        }
        return expression;
    }

    @Override
    public Object visitActionAnd(BehaviorSpecParser.ActionAndContext ctx) {
        ActionExpression expression = (ActionExpression) visit(ctx.actionNot(0));
        for (int index = 1; index < ctx.actionNot().size(); index++) {
            expression = new ActionExpression.Binary(
                    ActionExpression.Operator.AND,
                    expression,
                    (ActionExpression) visit(ctx.actionNot(index))
            );
        }
        return expression;
    }

    @Override
    public Object visitActionNot(BehaviorSpecParser.ActionNotContext ctx) {
        if (ctx.NOT() != null) {
            return new ActionExpression.Not((ActionExpression) visit(ctx.actionNot()));
        }
        if (ctx.rawMaudeCall() != null) {
            return new ActionExpression.RawMaude((String) visit(ctx.rawMaudeCall()));
        }
        if (ctx.actionAtom() != null) {
            return visit(ctx.actionAtom());
        }
        return visit(ctx.actionExpr());
    }

    @Override
    public Object visitActionAtom(BehaviorSpecParser.ActionAtomContext ctx) {
        return new ActionExpression.Atom(readIdentifier(ctx.identifier()), (UserTerm) visit(ctx.term()));
    }

    @Override
    public Object visitModificationsSection(BehaviorSpecParser.ModificationsSectionContext ctx) {
        return visit(ctx.modificationExpr());
    }

    @Override
    public Object visitModificationExpr(BehaviorSpecParser.ModificationExprContext ctx) {
        List<BehaviorModificationSpec> modifications = new ArrayList<BehaviorModificationSpec>();
        for (BehaviorSpecParser.ModificationCallContext callContext : ctx.modificationCall()) {
            modifications.add((BehaviorModificationSpec) visit(callContext));
        }
        return modifications;
    }

    @Override
    public Object visitModificationCall(BehaviorSpecParser.ModificationCallContext ctx) {
        List<BehaviorSpecParser.TermContext> terms = ctx.term();
        if (ctx.rawMaudeCall() != null) {
            return new BehaviorModificationSpec.RawMaude((String) visit(ctx.rawMaudeCall()));
        }
        if (ctx.SETM() != null) {
            return new BehaviorModificationSpec.SetMessage(
                    (UserTerm) visit(terms.get(0)),
                    (UserTerm) visit(terms.get(1))
            );
        }
        if (ctx.SETF() != null) {
            return new BehaviorModificationSpec.SetFeature(
                    (UserTerm) visit(terms.get(0)),
                    (UserTerm) visit(terms.get(1))
            );
        }
        if (ctx.ADD() != null) {
            return new BehaviorModificationSpec.AddMessage(
                    (UserTerm) visit(terms.get(0)),
                    (UserTerm) visit(terms.get(1))
            );
        }
        if (ctx.REMOVE() != null) {
            return new BehaviorModificationSpec.RemoveMessage((UserTerm) visit(terms.get(0)));
        }
        if (ctx.NOCHECK() != null) {
            return new BehaviorModificationSpec.NoCheck((UserTerm) visit(terms.get(0)));
        }
        if (ctx.DELAY() != null) {
            return new BehaviorModificationSpec.Delay((UserTerm) visit(terms.get(0)));
        }
        return new BehaviorModificationSpec.Skip();
    }

    @Override
    public Object visitRawMaudeCall(BehaviorSpecParser.RawMaudeCallContext ctx) {
        return stripQuotes(ctx.stringLiteral().STRING().getText());
    }

    @Override
    public Object visitParameterInstancesSection(BehaviorSpecParser.ParameterInstancesSectionContext ctx) {
        List<BehaviorParameterInstance> instances = new ArrayList<BehaviorParameterInstance>();
        for (BehaviorSpecParser.ParameterInstanceContext instanceContext : ctx.parameterInstance()) {
            instances.add((BehaviorParameterInstance) visit(instanceContext));
        }
        return instances;
    }

    @Override
    public Object visitParameterInstance(BehaviorSpecParser.ParameterInstanceContext ctx) {
        BehaviorParameterInstance instance = new BehaviorParameterInstance();
        for (BehaviorSpecParser.ParameterBindingContext bindingContext : ctx.parameterBinding()) {
            instance.addBinding(
                    readParameterName(bindingContext.parameterName()),
                    (UserTerm) visit(bindingContext.term())
            );
        }
        return instance;
    }

    @Override
    public Object visitTerm(BehaviorSpecParser.TermContext ctx) {
        return visit(ctx.dottedTerm());
    }

    @Override
    public Object visitDottedTerm(BehaviorSpecParser.DottedTermContext ctx) {
        List<BehaviorSpecParser.PrimaryTermContext> parts = ctx.primaryTerm();
        if (parts.size() == 1) {
            return visit(parts.get(0));
        }

        List<UserTerm> renderedParts = new ArrayList<UserTerm>();
        for (BehaviorSpecParser.PrimaryTermContext part : parts) {
            renderedParts.add((UserTerm) visit(part));
        }
        return new UserTerm.Dotted(renderedParts);
    }

    @Override
    public Object visitPrimaryTerm(BehaviorSpecParser.PrimaryTermContext ctx) {
        if (ctx.parameterRef() != null) {
            return visit(ctx.parameterRef());
        }
        if (ctx.rawMaudeCall() != null) {
            return new UserTerm.RawMaude((String) visit(ctx.rawMaudeCall()));
        }
        if (ctx.functionTerm() != null) {
            return visit(ctx.functionTerm());
        }
        if (ctx.indexedTerm() != null) {
            return visit(ctx.indexedTerm());
        }
        if (ctx.listTerm() != null) {
            return visit(ctx.listTerm());
        }
        if (ctx.braceTerm() != null) {
            return visit(ctx.braceTerm());
        }
        if (ctx.stringLiteral() != null) {
            return new UserTerm.StringLiteral(stripQuotes(ctx.stringLiteral().STRING().getText()));
        }
        if (ctx.identifier() != null) {
            return new UserTerm.Atom(readIdentifier(ctx.identifier()));
        }
        if (ctx.numberLiteral() != null) {
            return new UserTerm.Atom(ctx.numberLiteral().NUMBER().getText());
        }
        return visit(ctx.term());
    }

    @Override
    public Object visitFunctionTerm(BehaviorSpecParser.FunctionTermContext ctx) {
        return new UserTerm.Call(readIdentifier(ctx.identifier()), readTermList(ctx.termList()));
    }

    @Override
    public Object visitIndexedTerm(BehaviorSpecParser.IndexedTermContext ctx) {
        return new UserTerm.Indexed(readIdentifier(ctx.identifier()), (UserTerm) visit(ctx.term()));
    }

    @Override
    public Object visitListTerm(BehaviorSpecParser.ListTermContext ctx) {
        return new UserTerm.ListTerm(readTermList(ctx.termList()));
    }

    @Override
    public Object visitBraceTerm(BehaviorSpecParser.BraceTermContext ctx) {
        return new UserTerm.BraceTerm(readTermList(ctx.termList()));
    }

    @Override
    public Object visitParameterRef(BehaviorSpecParser.ParameterRefContext ctx) {
        return new UserTerm.Parameter(ctx.PARAMETER_REF().getText().substring(1));
    }

    private List<UserTerm> readTermList(BehaviorSpecParser.TermListContext ctx) {
        List<UserTerm> terms = new ArrayList<UserTerm>();
        if (ctx == null) {
            return terms;
        }
        for (BehaviorSpecParser.TermContext termContext : ctx.term()) {
            terms.add((UserTerm) visit(termContext));
        }
        return terms;
    }

    private static String readIdentifier(BehaviorSpecParser.IdentifierContext ctx) {
        return ctx.IDENTIFIER().getText();
    }

    private static String readParameterName(BehaviorSpecParser.ParameterNameContext ctx) {
        return ctx.IDENTIFIER().getText();
    }

    private static String stripQuotes(String text) {
        if (text.length() >= 2 && text.startsWith("\"") && text.endsWith("\"")) {
            return text.substring(1, text.length() - 1);
        }
        return text;
    }
}
