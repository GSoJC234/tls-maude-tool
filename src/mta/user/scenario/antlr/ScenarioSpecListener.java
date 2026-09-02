// Generated from ScenarioSpec.g4 by ANTLR 4.13.2
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
	 * Enter a parse tree produced by {@link ScenarioSpecParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(ScenarioSpecParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(ScenarioSpecParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#statePropositionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStatePropositionDeclaration(ScenarioSpecParser.StatePropositionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#statePropositionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStatePropositionDeclaration(ScenarioSpecParser.StatePropositionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#actionPropositionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterActionPropositionDeclaration(ScenarioSpecParser.ActionPropositionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#actionPropositionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitActionPropositionDeclaration(ScenarioSpecParser.ActionPropositionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#scenarioPropertyDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterScenarioPropertyDeclaration(ScenarioSpecParser.ScenarioPropertyDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#scenarioPropertyDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitScenarioPropertyDeclaration(ScenarioSpecParser.ScenarioPropertyDeclarationContext ctx);
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
	 * Enter a parse tree produced by {@link ScenarioSpecParser#propositionRef}.
	 * @param ctx the parse tree
	 */
	void enterPropositionRef(ScenarioSpecParser.PropositionRefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#propositionRef}.
	 * @param ctx the parse tree
	 */
	void exitPropositionRef(ScenarioSpecParser.PropositionRefContext ctx);
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
	 * Enter a parse tree produced by {@link ScenarioSpecParser#stateExpr}.
	 * @param ctx the parse tree
	 */
	void enterStateExpr(ScenarioSpecParser.StateExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#stateExpr}.
	 * @param ctx the parse tree
	 */
	void exitStateExpr(ScenarioSpecParser.StateExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#stateOr}.
	 * @param ctx the parse tree
	 */
	void enterStateOr(ScenarioSpecParser.StateOrContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#stateOr}.
	 * @param ctx the parse tree
	 */
	void exitStateOr(ScenarioSpecParser.StateOrContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#stateAnd}.
	 * @param ctx the parse tree
	 */
	void enterStateAnd(ScenarioSpecParser.StateAndContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#stateAnd}.
	 * @param ctx the parse tree
	 */
	void exitStateAnd(ScenarioSpecParser.StateAndContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#stateNot}.
	 * @param ctx the parse tree
	 */
	void enterStateNot(ScenarioSpecParser.StateNotContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#stateNot}.
	 * @param ctx the parse tree
	 */
	void exitStateNot(ScenarioSpecParser.StateNotContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#actionExpr}.
	 * @param ctx the parse tree
	 */
	void enterActionExpr(ScenarioSpecParser.ActionExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#actionExpr}.
	 * @param ctx the parse tree
	 */
	void exitActionExpr(ScenarioSpecParser.ActionExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#actionOr}.
	 * @param ctx the parse tree
	 */
	void enterActionOr(ScenarioSpecParser.ActionOrContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#actionOr}.
	 * @param ctx the parse tree
	 */
	void exitActionOr(ScenarioSpecParser.ActionOrContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#actionAnd}.
	 * @param ctx the parse tree
	 */
	void enterActionAnd(ScenarioSpecParser.ActionAndContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#actionAnd}.
	 * @param ctx the parse tree
	 */
	void exitActionAnd(ScenarioSpecParser.ActionAndContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#actionNot}.
	 * @param ctx the parse tree
	 */
	void enterActionNot(ScenarioSpecParser.ActionNotContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#actionNot}.
	 * @param ctx the parse tree
	 */
	void exitActionNot(ScenarioSpecParser.ActionNotContext ctx);
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