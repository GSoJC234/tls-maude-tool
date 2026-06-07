// Generated from /Users/gsojc234/git/maude-tls-attacker/src/mta/user/scenario/antlr/ScenarioSpec.g4 by ANTLR 4.13.2
package mta.user.scenario.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ScenarioSpecParser}.
 */
public interface ScenarioSpecListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#scenarioSpec}.
	 * @param ctx the parse tree
	 */
	void enterScenarioSpec(ScenarioSpecParser.ScenarioSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#scenarioSpec}.
	 * @param ctx the parse tree
	 */
	void exitScenarioSpec(ScenarioSpecParser.ScenarioSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#scenarioExpr}.
	 * @param ctx the parse tree
	 */
	void enterScenarioExpr(ScenarioSpecParser.ScenarioExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#scenarioExpr}.
	 * @param ctx the parse tree
	 */
	void exitScenarioExpr(ScenarioSpecParser.ScenarioExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#scenarioChoice}.
	 * @param ctx the parse tree
	 */
	void enterScenarioChoice(ScenarioSpecParser.ScenarioChoiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#scenarioChoice}.
	 * @param ctx the parse tree
	 */
	void exitScenarioChoice(ScenarioSpecParser.ScenarioChoiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#scenarioSequence}.
	 * @param ctx the parse tree
	 */
	void enterScenarioSequence(ScenarioSpecParser.ScenarioSequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#scenarioSequence}.
	 * @param ctx the parse tree
	 */
	void exitScenarioSequence(ScenarioSpecParser.ScenarioSequenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#scenarioRepeat}.
	 * @param ctx the parse tree
	 */
	void enterScenarioRepeat(ScenarioSpecParser.ScenarioRepeatContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#scenarioRepeat}.
	 * @param ctx the parse tree
	 */
	void exitScenarioRepeat(ScenarioSpecParser.ScenarioRepeatContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#scenarioPrimary}.
	 * @param ctx the parse tree
	 */
	void enterScenarioPrimary(ScenarioSpecParser.ScenarioPrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#scenarioPrimary}.
	 * @param ctx the parse tree
	 */
	void exitScenarioPrimary(ScenarioSpecParser.ScenarioPrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#stepExpr}.
	 * @param ctx the parse tree
	 */
	void enterStepExpr(ScenarioSpecParser.StepExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#stepExpr}.
	 * @param ctx the parse tree
	 */
	void exitStepExpr(ScenarioSpecParser.StepExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#stepOr}.
	 * @param ctx the parse tree
	 */
	void enterStepOr(ScenarioSpecParser.StepOrContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#stepOr}.
	 * @param ctx the parse tree
	 */
	void exitStepOr(ScenarioSpecParser.StepOrContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#stepAnd}.
	 * @param ctx the parse tree
	 */
	void enterStepAnd(ScenarioSpecParser.StepAndContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#stepAnd}.
	 * @param ctx the parse tree
	 */
	void exitStepAnd(ScenarioSpecParser.StepAndContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#stepNot}.
	 * @param ctx the parse tree
	 */
	void enterStepNot(ScenarioSpecParser.StepNotContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#stepNot}.
	 * @param ctx the parse tree
	 */
	void exitStepNot(ScenarioSpecParser.StepNotContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#stepAtom}.
	 * @param ctx the parse tree
	 */
	void enterStepAtom(ScenarioSpecParser.StepAtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#stepAtom}.
	 * @param ctx the parse tree
	 */
	void exitStepAtom(ScenarioSpecParser.StepAtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#stateAtom}.
	 * @param ctx the parse tree
	 */
	void enterStateAtom(ScenarioSpecParser.StateAtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#stateAtom}.
	 * @param ctx the parse tree
	 */
	void exitStateAtom(ScenarioSpecParser.StateAtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#stateObject}.
	 * @param ctx the parse tree
	 */
	void enterStateObject(ScenarioSpecParser.StateObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#stateObject}.
	 * @param ctx the parse tree
	 */
	void exitStateObject(ScenarioSpecParser.StateObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#actionAtom}.
	 * @param ctx the parse tree
	 */
	void enterActionAtom(ScenarioSpecParser.ActionAtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#actionAtom}.
	 * @param ctx the parse tree
	 */
	void exitActionAtom(ScenarioSpecParser.ActionAtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(ScenarioSpecParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(ScenarioSpecParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#dottedTerm}.
	 * @param ctx the parse tree
	 */
	void enterDottedTerm(ScenarioSpecParser.DottedTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#dottedTerm}.
	 * @param ctx the parse tree
	 */
	void exitDottedTerm(ScenarioSpecParser.DottedTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#primaryTerm}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryTerm(ScenarioSpecParser.PrimaryTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#primaryTerm}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryTerm(ScenarioSpecParser.PrimaryTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#functionTerm}.
	 * @param ctx the parse tree
	 */
	void enterFunctionTerm(ScenarioSpecParser.FunctionTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#functionTerm}.
	 * @param ctx the parse tree
	 */
	void exitFunctionTerm(ScenarioSpecParser.FunctionTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#indexedTerm}.
	 * @param ctx the parse tree
	 */
	void enterIndexedTerm(ScenarioSpecParser.IndexedTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#indexedTerm}.
	 * @param ctx the parse tree
	 */
	void exitIndexedTerm(ScenarioSpecParser.IndexedTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#listTerm}.
	 * @param ctx the parse tree
	 */
	void enterListTerm(ScenarioSpecParser.ListTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#listTerm}.
	 * @param ctx the parse tree
	 */
	void exitListTerm(ScenarioSpecParser.ListTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#braceTerm}.
	 * @param ctx the parse tree
	 */
	void enterBraceTerm(ScenarioSpecParser.BraceTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#braceTerm}.
	 * @param ctx the parse tree
	 */
	void exitBraceTerm(ScenarioSpecParser.BraceTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#termList}.
	 * @param ctx the parse tree
	 */
	void enterTermList(ScenarioSpecParser.TermListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#termList}.
	 * @param ctx the parse tree
	 */
	void exitTermList(ScenarioSpecParser.TermListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#rawMaudeCall}.
	 * @param ctx the parse tree
	 */
	void enterRawMaudeCall(ScenarioSpecParser.RawMaudeCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#rawMaudeCall}.
	 * @param ctx the parse tree
	 */
	void exitRawMaudeCall(ScenarioSpecParser.RawMaudeCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(ScenarioSpecParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(ScenarioSpecParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#numberLiteral}.
	 * @param ctx the parse tree
	 */
	void enterNumberLiteral(ScenarioSpecParser.NumberLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#numberLiteral}.
	 * @param ctx the parse tree
	 */
	void exitNumberLiteral(ScenarioSpecParser.NumberLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(ScenarioSpecParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(ScenarioSpecParser.StringLiteralContext ctx);
}