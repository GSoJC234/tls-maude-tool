// Generated from src/mta/user/behavior/antlr/BehaviorSpec.g4 by ANTLR 4.13.2
package mta.user.behavior.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BehaviorSpecParser}.
 */
public interface BehaviorSpecListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#behaviorDeviationSpecification}.
	 * @param ctx the parse tree
	 */
	void enterBehaviorDeviationSpecification(BehaviorSpecParser.BehaviorDeviationSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#behaviorDeviationSpecification}.
	 * @param ctx the parse tree
	 */
	void exitBehaviorDeviationSpecification(BehaviorSpecParser.BehaviorDeviationSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#behaviorSpec}.
	 * @param ctx the parse tree
	 */
	void enterBehaviorSpec(BehaviorSpecParser.BehaviorSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#behaviorSpec}.
	 * @param ctx the parse tree
	 */
	void exitBehaviorSpec(BehaviorSpecParser.BehaviorSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#behaviorIdSection}.
	 * @param ctx the parse tree
	 */
	void enterBehaviorIdSection(BehaviorSpecParser.BehaviorIdSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#behaviorIdSection}.
	 * @param ctx the parse tree
	 */
	void exitBehaviorIdSection(BehaviorSpecParser.BehaviorIdSectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#parametersSection}.
	 * @param ctx the parse tree
	 */
	void enterParametersSection(BehaviorSpecParser.ParametersSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#parametersSection}.
	 * @param ctx the parse tree
	 */
	void exitParametersSection(BehaviorSpecParser.ParametersSectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterParameterDeclaration(BehaviorSpecParser.ParameterDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitParameterDeclaration(BehaviorSpecParser.ParameterDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#typeExpression}.
	 * @param ctx the parse tree
	 */
	void enterTypeExpression(BehaviorSpecParser.TypeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#typeExpression}.
	 * @param ctx the parse tree
	 */
	void exitTypeExpression(BehaviorSpecParser.TypeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#getTypeCall}.
	 * @param ctx the parse tree
	 */
	void enterGetTypeCall(BehaviorSpecParser.GetTypeCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#getTypeCall}.
	 * @param ctx the parse tree
	 */
	void exitGetTypeCall(BehaviorSpecParser.GetTypeCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#conditionsSection}.
	 * @param ctx the parse tree
	 */
	void enterConditionsSection(BehaviorSpecParser.ConditionsSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#conditionsSection}.
	 * @param ctx the parse tree
	 */
	void exitConditionsSection(BehaviorSpecParser.ConditionsSectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#modificationsSection}.
	 * @param ctx the parse tree
	 */
	void enterModificationsSection(BehaviorSpecParser.ModificationsSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#modificationsSection}.
	 * @param ctx the parse tree
	 */
	void exitModificationsSection(BehaviorSpecParser.ModificationsSectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#parameterInstancesSection}.
	 * @param ctx the parse tree
	 */
	void enterParameterInstancesSection(BehaviorSpecParser.ParameterInstancesSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#parameterInstancesSection}.
	 * @param ctx the parse tree
	 */
	void exitParameterInstancesSection(BehaviorSpecParser.ParameterInstancesSectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#parameterInstance}.
	 * @param ctx the parse tree
	 */
	void enterParameterInstance(BehaviorSpecParser.ParameterInstanceContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#parameterInstance}.
	 * @param ctx the parse tree
	 */
	void exitParameterInstance(BehaviorSpecParser.ParameterInstanceContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#parameterBinding}.
	 * @param ctx the parse tree
	 */
	void enterParameterBinding(BehaviorSpecParser.ParameterBindingContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#parameterBinding}.
	 * @param ctx the parse tree
	 */
	void exitParameterBinding(BehaviorSpecParser.ParameterBindingContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#actionExpr}.
	 * @param ctx the parse tree
	 */
	void enterActionExpr(BehaviorSpecParser.ActionExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#actionExpr}.
	 * @param ctx the parse tree
	 */
	void exitActionExpr(BehaviorSpecParser.ActionExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#actionOr}.
	 * @param ctx the parse tree
	 */
	void enterActionOr(BehaviorSpecParser.ActionOrContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#actionOr}.
	 * @param ctx the parse tree
	 */
	void exitActionOr(BehaviorSpecParser.ActionOrContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#actionAnd}.
	 * @param ctx the parse tree
	 */
	void enterActionAnd(BehaviorSpecParser.ActionAndContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#actionAnd}.
	 * @param ctx the parse tree
	 */
	void exitActionAnd(BehaviorSpecParser.ActionAndContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#actionNot}.
	 * @param ctx the parse tree
	 */
	void enterActionNot(BehaviorSpecParser.ActionNotContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#actionNot}.
	 * @param ctx the parse tree
	 */
	void exitActionNot(BehaviorSpecParser.ActionNotContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#actionAtom}.
	 * @param ctx the parse tree
	 */
	void enterActionAtom(BehaviorSpecParser.ActionAtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#actionAtom}.
	 * @param ctx the parse tree
	 */
	void exitActionAtom(BehaviorSpecParser.ActionAtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#modificationExpr}.
	 * @param ctx the parse tree
	 */
	void enterModificationExpr(BehaviorSpecParser.ModificationExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#modificationExpr}.
	 * @param ctx the parse tree
	 */
	void exitModificationExpr(BehaviorSpecParser.ModificationExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#modificationCall}.
	 * @param ctx the parse tree
	 */
	void enterModificationCall(BehaviorSpecParser.ModificationCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#modificationCall}.
	 * @param ctx the parse tree
	 */
	void exitModificationCall(BehaviorSpecParser.ModificationCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(BehaviorSpecParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(BehaviorSpecParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#dottedTerm}.
	 * @param ctx the parse tree
	 */
	void enterDottedTerm(BehaviorSpecParser.DottedTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#dottedTerm}.
	 * @param ctx the parse tree
	 */
	void exitDottedTerm(BehaviorSpecParser.DottedTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#primaryTerm}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryTerm(BehaviorSpecParser.PrimaryTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#primaryTerm}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryTerm(BehaviorSpecParser.PrimaryTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#functionTerm}.
	 * @param ctx the parse tree
	 */
	void enterFunctionTerm(BehaviorSpecParser.FunctionTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#functionTerm}.
	 * @param ctx the parse tree
	 */
	void exitFunctionTerm(BehaviorSpecParser.FunctionTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#indexedTerm}.
	 * @param ctx the parse tree
	 */
	void enterIndexedTerm(BehaviorSpecParser.IndexedTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#indexedTerm}.
	 * @param ctx the parse tree
	 */
	void exitIndexedTerm(BehaviorSpecParser.IndexedTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#listTerm}.
	 * @param ctx the parse tree
	 */
	void enterListTerm(BehaviorSpecParser.ListTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#listTerm}.
	 * @param ctx the parse tree
	 */
	void exitListTerm(BehaviorSpecParser.ListTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#braceTerm}.
	 * @param ctx the parse tree
	 */
	void enterBraceTerm(BehaviorSpecParser.BraceTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#braceTerm}.
	 * @param ctx the parse tree
	 */
	void exitBraceTerm(BehaviorSpecParser.BraceTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#termList}.
	 * @param ctx the parse tree
	 */
	void enterTermList(BehaviorSpecParser.TermListContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#termList}.
	 * @param ctx the parse tree
	 */
	void exitTermList(BehaviorSpecParser.TermListContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#rawMaudeCall}.
	 * @param ctx the parse tree
	 */
	void enterRawMaudeCall(BehaviorSpecParser.RawMaudeCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#rawMaudeCall}.
	 * @param ctx the parse tree
	 */
	void exitRawMaudeCall(BehaviorSpecParser.RawMaudeCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#parameterRef}.
	 * @param ctx the parse tree
	 */
	void enterParameterRef(BehaviorSpecParser.ParameterRefContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#parameterRef}.
	 * @param ctx the parse tree
	 */
	void exitParameterRef(BehaviorSpecParser.ParameterRefContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#parameterName}.
	 * @param ctx the parse tree
	 */
	void enterParameterName(BehaviorSpecParser.ParameterNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#parameterName}.
	 * @param ctx the parse tree
	 */
	void exitParameterName(BehaviorSpecParser.ParameterNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(BehaviorSpecParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(BehaviorSpecParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#numberLiteral}.
	 * @param ctx the parse tree
	 */
	void enterNumberLiteral(BehaviorSpecParser.NumberLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#numberLiteral}.
	 * @param ctx the parse tree
	 */
	void exitNumberLiteral(BehaviorSpecParser.NumberLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(BehaviorSpecParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(BehaviorSpecParser.StringLiteralContext ctx);
}