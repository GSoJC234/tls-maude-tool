// Generated from ScenarioSpec.g4 by ANTLR 4.13.2
package mta.user.scenario.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ScenarioSpecParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ScenarioSpecVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#scenarioSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScenarioSpec(ScenarioSpecParser.ScenarioSpecContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(ScenarioSpecParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#statePropositionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatePropositionDeclaration(ScenarioSpecParser.StatePropositionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#actionPropositionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionPropositionDeclaration(ScenarioSpecParser.ActionPropositionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#scenarioPropertyDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScenarioPropertyDeclaration(ScenarioSpecParser.ScenarioPropertyDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#scenarioExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScenarioExpr(ScenarioSpecParser.ScenarioExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#scenarioChoice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScenarioChoice(ScenarioSpecParser.ScenarioChoiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#scenarioSequence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScenarioSequence(ScenarioSpecParser.ScenarioSequenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#scenarioRepeat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScenarioRepeat(ScenarioSpecParser.ScenarioRepeatContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#scenarioPrimary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScenarioPrimary(ScenarioSpecParser.ScenarioPrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#propositionRef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropositionRef(ScenarioSpecParser.PropositionRefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#stepExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStepExpr(ScenarioSpecParser.StepExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#stepOr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStepOr(ScenarioSpecParser.StepOrContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#stepAnd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStepAnd(ScenarioSpecParser.StepAndContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#stepNot}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStepNot(ScenarioSpecParser.StepNotContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#stepAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStepAtom(ScenarioSpecParser.StepAtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#stateExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStateExpr(ScenarioSpecParser.StateExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#stateOr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStateOr(ScenarioSpecParser.StateOrContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#stateAnd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStateAnd(ScenarioSpecParser.StateAndContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#stateNot}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStateNot(ScenarioSpecParser.StateNotContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#actionExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionExpr(ScenarioSpecParser.ActionExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#actionOr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionOr(ScenarioSpecParser.ActionOrContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#actionAnd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionAnd(ScenarioSpecParser.ActionAndContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#actionNot}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionNot(ScenarioSpecParser.ActionNotContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#stateAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStateAtom(ScenarioSpecParser.StateAtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#stateObject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStateObject(ScenarioSpecParser.StateObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#actionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionAtom(ScenarioSpecParser.ActionAtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(ScenarioSpecParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#dottedTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDottedTerm(ScenarioSpecParser.DottedTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#primaryTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryTerm(ScenarioSpecParser.PrimaryTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#functionTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionTerm(ScenarioSpecParser.FunctionTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#indexedTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexedTerm(ScenarioSpecParser.IndexedTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#listTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListTerm(ScenarioSpecParser.ListTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#braceTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBraceTerm(ScenarioSpecParser.BraceTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#termList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermList(ScenarioSpecParser.TermListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#rawMaudeCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRawMaudeCall(ScenarioSpecParser.RawMaudeCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(ScenarioSpecParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#numberLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberLiteral(ScenarioSpecParser.NumberLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#stringLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(ScenarioSpecParser.StringLiteralContext ctx);
}