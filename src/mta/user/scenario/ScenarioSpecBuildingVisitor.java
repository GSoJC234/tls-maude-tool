package mta.user.scenario;

import mta.user.common.ActionExpression;
import mta.user.common.ScenarioExpression;
import mta.user.common.StateExpression;
import mta.user.common.StepExpression;
import mta.user.common.UserTerm;
import mta.user.scenario.antlr.ScenarioSpecBaseVisitor;
import mta.user.scenario.antlr.ScenarioSpecParser;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ScenarioSpecBuildingVisitor extends ScenarioSpecBaseVisitor<Object> {

    @SuppressWarnings("unused")
    private final Path baseDirectory;

    public ScenarioSpecBuildingVisitor() {
        this(Path.of(".").toAbsolutePath().normalize());
    }

    public ScenarioSpecBuildingVisitor(Path baseDirectory) {
        this.baseDirectory = baseDirectory != null ? baseDirectory : Path.of(".").toAbsolutePath().normalize();
    }

    @Override
    public Object visitScenarioSpec(ScenarioSpecParser.ScenarioSpecContext ctx) {
        ScenarioSpec scenarioSpec = new ScenarioSpec();
        scenarioSpec.setCurrentScenarioProperty((ScenarioExpression) visit(ctx.scenarioExpr()));
        return scenarioSpec;
    }

    @Override
    public Object visitScenarioExpr(ScenarioSpecParser.ScenarioExprContext ctx) {
        return visit(ctx.scenarioChoice());
    }

    @Override
    public Object visitScenarioChoice(ScenarioSpecParser.ScenarioChoiceContext ctx) {
        ScenarioExpression expression = (ScenarioExpression) visit(ctx.scenarioSequence(0));
        for (int index = 1; index < ctx.scenarioSequence().size(); index++) {
            expression = new ScenarioExpression.Choice(
                    expression,
                    (ScenarioExpression) visit(ctx.scenarioSequence(index))
            );
        }
        return expression;
    }

    @Override
    public Object visitScenarioSequence(ScenarioSpecParser.ScenarioSequenceContext ctx) {
        ScenarioExpression expression = (ScenarioExpression) visit(ctx.scenarioRepeat(0));
        for (int index = 1; index < ctx.scenarioRepeat().size(); index++) {
            expression = new ScenarioExpression.Sequence(
                    expression,
                    (ScenarioExpression) visit(ctx.scenarioRepeat(index))
            );
        }
        return expression;
    }

    @Override
    public Object visitScenarioRepeat(ScenarioSpecParser.ScenarioRepeatContext ctx) {
        ScenarioExpression expression = (ScenarioExpression) visit(ctx.scenarioPrimary());
        for (int index = 0; index < ctx.STAR().size(); index++) {
            expression = new ScenarioExpression.Star(expression);
        }
        return expression;
    }

    @Override
    public Object visitScenarioPrimary(ScenarioSpecParser.ScenarioPrimaryContext ctx) {
        if (ctx.ANY_STEP() != null) {
            return new ScenarioExpression.Step(new StepExpression.AnyStep());
        }
        if (ctx.rawMaudeCall() != null) {
            return new ScenarioExpression.RawMaude((String) visit(ctx.rawMaudeCall()));
        }
        if (ctx.scenarioExpr() != null) {
            return visit(ctx.scenarioExpr());
        }
        return new ScenarioExpression.Step((StepExpression) visit(ctx.stepExpr()));
    }

    @Override
    public Object visitStepExpr(ScenarioSpecParser.StepExprContext ctx) {
        return visit(ctx.stepOr());
    }

    @Override
    public Object visitStepOr(ScenarioSpecParser.StepOrContext ctx) {
        StepExpression expression = (StepExpression) visit(ctx.stepAnd(0));
        for (int index = 1; index < ctx.stepAnd().size(); index++) {
            expression = new StepExpression.Binary(
                    StepExpression.Operator.OR,
                    expression,
                    (StepExpression) visit(ctx.stepAnd(index))
            );
        }
        return expression;
    }

    @Override
    public Object visitStepAnd(ScenarioSpecParser.StepAndContext ctx) {
        StepExpression expression = (StepExpression) visit(ctx.stepNot(0));
        for (int index = 1; index < ctx.stepNot().size(); index++) {
            expression = new StepExpression.Binary(
                    StepExpression.Operator.AND,
                    expression,
                    (StepExpression) visit(ctx.stepNot(index))
            );
        }
        return expression;
    }

    @Override
    public Object visitStepNot(ScenarioSpecParser.StepNotContext ctx) {
        if (ctx.NOT() != null) {
            return new StepExpression.Not((StepExpression) visit(ctx.stepNot()));
        }
        if (ctx.stepAtom() != null) {
            return visit(ctx.stepAtom());
        }
        return visit(ctx.stepExpr());
    }

    @Override
    public Object visitStepAtom(ScenarioSpecParser.StepAtomContext ctx) {
        if (ctx.stateAtom() != null) {
            return new StepExpression.State((StateExpression) visit(ctx.stateAtom()));
        }
        return new StepExpression.Action((ActionExpression) visit(ctx.actionAtom()));
    }

    @Override
    public Object visitStateAtom(ScenarioSpecParser.StateAtomContext ctx) {
        return new StateExpression.Atom(
                (UserTerm) visit(ctx.stateObject()),
                readIdentifier(ctx.identifier()),
                (UserTerm) visit(ctx.term())
        );
    }

    @Override
    public Object visitStateObject(ScenarioSpecParser.StateObjectContext ctx) {
        List<ScenarioSpecParser.IdentifierContext> identifiers = ctx.identifier();
        if (identifiers.size() == 1) {
            return new UserTerm.Atom(readIdentifier(identifiers.get(0)));
        }

        List<UserTerm> parts = new ArrayList<UserTerm>();
        for (ScenarioSpecParser.IdentifierContext identifierContext : identifiers) {
            parts.add(new UserTerm.Atom(readIdentifier(identifierContext)));
        }
        return new UserTerm.Dotted(parts);
    }

    @Override
    public Object visitActionAtom(ScenarioSpecParser.ActionAtomContext ctx) {
        return new ActionExpression.Atom(readIdentifier(ctx.identifier()), (UserTerm) visit(ctx.term()));
    }

    @Override
    public Object visitTerm(ScenarioSpecParser.TermContext ctx) {
        return visit(ctx.dottedTerm());
    }

    @Override
    public Object visitDottedTerm(ScenarioSpecParser.DottedTermContext ctx) {
        List<ScenarioSpecParser.PrimaryTermContext> parts = ctx.primaryTerm();
        if (parts.size() == 1) {
            return visit(parts.get(0));
        }

        List<UserTerm> renderedParts = new ArrayList<UserTerm>();
        for (ScenarioSpecParser.PrimaryTermContext part : parts) {
            renderedParts.add((UserTerm) visit(part));
        }
        return new UserTerm.Dotted(renderedParts);
    }

    @Override
    public Object visitPrimaryTerm(ScenarioSpecParser.PrimaryTermContext ctx) {
        if (ctx.functionTerm() != null) {
            return visit(ctx.functionTerm());
        }
        if (ctx.rawMaudeCall() != null) {
            return new UserTerm.RawMaude((String) visit(ctx.rawMaudeCall()));
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
    public Object visitRawMaudeCall(ScenarioSpecParser.RawMaudeCallContext ctx) {
        return stripQuotes(ctx.stringLiteral().STRING().getText());
    }

    @Override
    public Object visitFunctionTerm(ScenarioSpecParser.FunctionTermContext ctx) {
        return new UserTerm.Call(readIdentifier(ctx.identifier()), readTermList(ctx.termList()));
    }

    @Override
    public Object visitIndexedTerm(ScenarioSpecParser.IndexedTermContext ctx) {
        return new UserTerm.Indexed(readIdentifier(ctx.identifier()), (UserTerm) visit(ctx.term()));
    }

    @Override
    public Object visitListTerm(ScenarioSpecParser.ListTermContext ctx) {
        return new UserTerm.ListTerm(readTermList(ctx.termList()));
    }

    @Override
    public Object visitBraceTerm(ScenarioSpecParser.BraceTermContext ctx) {
        return new UserTerm.BraceTerm(readTermList(ctx.termList()));
    }

    private List<UserTerm> readTermList(ScenarioSpecParser.TermListContext ctx) {
        List<UserTerm> terms = new ArrayList<UserTerm>();
        if (ctx == null) {
            return terms;
        }
        for (ScenarioSpecParser.TermContext termContext : ctx.term()) {
            terms.add((UserTerm) visit(termContext));
        }
        return terms;
    }

    private static String readIdentifier(ScenarioSpecParser.IdentifierContext ctx) {
        return ctx.IDENTIFIER().getText();
    }

    private static String stripQuotes(String text) {
        if (text.length() >= 2 && text.startsWith("\"") && text.endsWith("\"")) {
            return text.substring(1, text.length() - 1);
        }
        return text;
    }
}
