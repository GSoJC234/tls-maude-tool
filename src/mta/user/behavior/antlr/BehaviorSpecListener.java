// Generated from src/mta/user/behavior/antlr/BehaviorSpec.g4 by ANTLR 4.13.2
package mta.user.behavior.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BehaviorSpecParser}.
 */
public interface BehaviorSpecListener extends ParseTreeListener {
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
	 * Enter a parse tree produced by {@link BehaviorSpecParser#eventTypeSection}.
	 * @param ctx the parse tree
	 */
	void enterEventTypeSection(BehaviorSpecParser.EventTypeSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#eventTypeSection}.
	 * @param ctx the parse tree
	 */
	void exitEventTypeSection(BehaviorSpecParser.EventTypeSectionContext ctx);
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
	 * Enter a parse tree produced by {@link BehaviorSpecParser#conditionExpr}.
	 * @param ctx the parse tree
	 */
	void enterConditionExpr(BehaviorSpecParser.ConditionExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#conditionExpr}.
	 * @param ctx the parse tree
	 */
	void exitConditionExpr(BehaviorSpecParser.ConditionExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#conditionPredicate}.
	 * @param ctx the parse tree
	 */
	void enterConditionPredicate(BehaviorSpecParser.ConditionPredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#conditionPredicate}.
	 * @param ctx the parse tree
	 */
	void exitConditionPredicate(BehaviorSpecParser.ConditionPredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#valueAccessor}.
	 * @param ctx the parse tree
	 */
	void enterValueAccessor(BehaviorSpecParser.ValueAccessorContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#valueAccessor}.
	 * @param ctx the parse tree
	 */
	void exitValueAccessor(BehaviorSpecParser.ValueAccessorContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#modificationSection}.
	 * @param ctx the parse tree
	 */
	void enterModificationSection(BehaviorSpecParser.ModificationSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#modificationSection}.
	 * @param ctx the parse tree
	 */
	void exitModificationSection(BehaviorSpecParser.ModificationSectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#modificationStatement}.
	 * @param ctx the parse tree
	 */
	void enterModificationStatement(BehaviorSpecParser.ModificationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#modificationStatement}.
	 * @param ctx the parse tree
	 */
	void exitModificationStatement(BehaviorSpecParser.ModificationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#addModification}.
	 * @param ctx the parse tree
	 */
	void enterAddModification(BehaviorSpecParser.AddModificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#addModification}.
	 * @param ctx the parse tree
	 */
	void exitAddModification(BehaviorSpecParser.AddModificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#setModification}.
	 * @param ctx the parse tree
	 */
	void enterSetModification(BehaviorSpecParser.SetModificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#setModification}.
	 * @param ctx the parse tree
	 */
	void exitSetModification(BehaviorSpecParser.SetModificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#removeModification}.
	 * @param ctx the parse tree
	 */
	void enterRemoveModification(BehaviorSpecParser.RemoveModificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#removeModification}.
	 * @param ctx the parse tree
	 */
	void exitRemoveModification(BehaviorSpecParser.RemoveModificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#noCheckModification}.
	 * @param ctx the parse tree
	 */
	void enterNoCheckModification(BehaviorSpecParser.NoCheckModificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#noCheckModification}.
	 * @param ctx the parse tree
	 */
	void exitNoCheckModification(BehaviorSpecParser.NoCheckModificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#skipModification}.
	 * @param ctx the parse tree
	 */
	void enterSkipModification(BehaviorSpecParser.SkipModificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#skipModification}.
	 * @param ctx the parse tree
	 */
	void exitSkipModification(BehaviorSpecParser.SkipModificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#delayModification}.
	 * @param ctx the parse tree
	 */
	void enterDelayModification(BehaviorSpecParser.DelayModificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#delayModification}.
	 * @param ctx the parse tree
	 */
	void exitDelayModification(BehaviorSpecParser.DelayModificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#modificationValue}.
	 * @param ctx the parse tree
	 */
	void enterModificationValue(BehaviorSpecParser.ModificationValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#modificationValue}.
	 * @param ctx the parse tree
	 */
	void exitModificationValue(BehaviorSpecParser.ModificationValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#targetRef}.
	 * @param ctx the parse tree
	 */
	void enterTargetRef(BehaviorSpecParser.TargetRefContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#targetRef}.
	 * @param ctx the parse tree
	 */
	void exitTargetRef(BehaviorSpecParser.TargetRefContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(BehaviorSpecParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(BehaviorSpecParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void enterArgumentList(BehaviorSpecParser.ArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void exitArgumentList(BehaviorSpecParser.ArgumentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#argumentValue}.
	 * @param ctx the parse tree
	 */
	void enterArgumentValue(BehaviorSpecParser.ArgumentValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#argumentValue}.
	 * @param ctx the parse tree
	 */
	void exitArgumentValue(BehaviorSpecParser.ArgumentValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#operandValue}.
	 * @param ctx the parse tree
	 */
	void enterOperandValue(BehaviorSpecParser.OperandValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#operandValue}.
	 * @param ctx the parse tree
	 */
	void exitOperandValue(BehaviorSpecParser.OperandValueContext ctx);
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
	 * Enter a parse tree produced by {@link BehaviorSpecParser#identifierValue}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierValue(BehaviorSpecParser.IdentifierValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#identifierValue}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierValue(BehaviorSpecParser.IdentifierValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link BehaviorSpecParser#hexLiteral}.
	 * @param ctx the parse tree
	 */
	void enterHexLiteral(BehaviorSpecParser.HexLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link BehaviorSpecParser#hexLiteral}.
	 * @param ctx the parse tree
	 */
	void exitHexLiteral(BehaviorSpecParser.HexLiteralContext ctx);
}