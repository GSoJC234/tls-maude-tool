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
	 * Enter a parse tree produced by {@link ScenarioSpecParser#scenarioItem}.
	 * @param ctx the parse tree
	 */
	void enterScenarioItem(ScenarioSpecParser.ScenarioItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#scenarioItem}.
	 * @param ctx the parse tree
	 */
	void exitScenarioItem(ScenarioSpecParser.ScenarioItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#loadStatement}.
	 * @param ctx the parse tree
	 */
	void enterLoadStatement(ScenarioSpecParser.LoadStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#loadStatement}.
	 * @param ctx the parse tree
	 */
	void exitLoadStatement(ScenarioSpecParser.LoadStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#useStatement}.
	 * @param ctx the parse tree
	 */
	void enterUseStatement(ScenarioSpecParser.UseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#useStatement}.
	 * @param ctx the parse tree
	 */
	void exitUseStatement(ScenarioSpecParser.UseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#nodesSection}.
	 * @param ctx the parse tree
	 */
	void enterNodesSection(ScenarioSpecParser.NodesSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#nodesSection}.
	 * @param ctx the parse tree
	 */
	void exitNodesSection(ScenarioSpecParser.NodesSectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#constantsSection}.
	 * @param ctx the parse tree
	 */
	void enterConstantsSection(ScenarioSpecParser.ConstantsSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#constantsSection}.
	 * @param ctx the parse tree
	 */
	void exitConstantsSection(ScenarioSpecParser.ConstantsSectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#constantDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstantDeclaration(ScenarioSpecParser.ConstantDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#constantDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstantDeclaration(ScenarioSpecParser.ConstantDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#constantSet}.
	 * @param ctx the parse tree
	 */
	void enterConstantSet(ScenarioSpecParser.ConstantSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#constantSet}.
	 * @param ctx the parse tree
	 */
	void exitConstantSet(ScenarioSpecParser.ConstantSetContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#statePropositionsSection}.
	 * @param ctx the parse tree
	 */
	void enterStatePropositionsSection(ScenarioSpecParser.StatePropositionsSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#statePropositionsSection}.
	 * @param ctx the parse tree
	 */
	void exitStatePropositionsSection(ScenarioSpecParser.StatePropositionsSectionContext ctx);
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
	 * Enter a parse tree produced by {@link ScenarioSpecParser#statePropositionExpr}.
	 * @param ctx the parse tree
	 */
	void enterStatePropositionExpr(ScenarioSpecParser.StatePropositionExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#statePropositionExpr}.
	 * @param ctx the parse tree
	 */
	void exitStatePropositionExpr(ScenarioSpecParser.StatePropositionExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#statePropositionTerm}.
	 * @param ctx the parse tree
	 */
	void enterStatePropositionTerm(ScenarioSpecParser.StatePropositionTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#statePropositionTerm}.
	 * @param ctx the parse tree
	 */
	void exitStatePropositionTerm(ScenarioSpecParser.StatePropositionTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#actionPropositionsSection}.
	 * @param ctx the parse tree
	 */
	void enterActionPropositionsSection(ScenarioSpecParser.ActionPropositionsSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#actionPropositionsSection}.
	 * @param ctx the parse tree
	 */
	void exitActionPropositionsSection(ScenarioSpecParser.ActionPropositionsSectionContext ctx);
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
	 * Enter a parse tree produced by {@link ScenarioSpecParser#actionInvocation}.
	 * @param ctx the parse tree
	 */
	void enterActionInvocation(ScenarioSpecParser.ActionInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#actionInvocation}.
	 * @param ctx the parse tree
	 */
	void exitActionInvocation(ScenarioSpecParser.ActionInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#actionArgument}.
	 * @param ctx the parse tree
	 */
	void enterActionArgument(ScenarioSpecParser.ActionArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#actionArgument}.
	 * @param ctx the parse tree
	 */
	void exitActionArgument(ScenarioSpecParser.ActionArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#scenarioPropertiesSection}.
	 * @param ctx the parse tree
	 */
	void enterScenarioPropertiesSection(ScenarioSpecParser.ScenarioPropertiesSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#scenarioPropertiesSection}.
	 * @param ctx the parse tree
	 */
	void exitScenarioPropertiesSection(ScenarioSpecParser.ScenarioPropertiesSectionContext ctx);
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
	 * Enter a parse tree produced by {@link ScenarioSpecParser#nodeBinding}.
	 * @param ctx the parse tree
	 */
	void enterNodeBinding(ScenarioSpecParser.NodeBindingContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#nodeBinding}.
	 * @param ctx the parse tree
	 */
	void exitNodeBinding(ScenarioSpecParser.NodeBindingContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#propertyRelation}.
	 * @param ctx the parse tree
	 */
	void enterPropertyRelation(ScenarioSpecParser.PropertyRelationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#propertyRelation}.
	 * @param ctx the parse tree
	 */
	void exitPropertyRelation(ScenarioSpecParser.PropertyRelationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#propertyReferenceValue}.
	 * @param ctx the parse tree
	 */
	void enterPropertyReferenceValue(ScenarioSpecParser.PropertyReferenceValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#propertyReferenceValue}.
	 * @param ctx the parse tree
	 */
	void exitPropertyReferenceValue(ScenarioSpecParser.PropertyReferenceValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#conditionExpr}.
	 * @param ctx the parse tree
	 */
	void enterConditionExpr(ScenarioSpecParser.ConditionExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#conditionExpr}.
	 * @param ctx the parse tree
	 */
	void exitConditionExpr(ScenarioSpecParser.ConditionExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#conditionPredicate}.
	 * @param ctx the parse tree
	 */
	void enterConditionPredicate(ScenarioSpecParser.ConditionPredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#conditionPredicate}.
	 * @param ctx the parse tree
	 */
	void exitConditionPredicate(ScenarioSpecParser.ConditionPredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#valueAccessor}.
	 * @param ctx the parse tree
	 */
	void enterValueAccessor(ScenarioSpecParser.ValueAccessorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#valueAccessor}.
	 * @param ctx the parse tree
	 */
	void exitValueAccessor(ScenarioSpecParser.ValueAccessorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#conditionOperand}.
	 * @param ctx the parse tree
	 */
	void enterConditionOperand(ScenarioSpecParser.ConditionOperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#conditionOperand}.
	 * @param ctx the parse tree
	 */
	void exitConditionOperand(ScenarioSpecParser.ConditionOperandContext ctx);
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
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#constantRef}.
	 * @param ctx the parse tree
	 */
	void enterConstantRef(ScenarioSpecParser.ConstantRefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#constantRef}.
	 * @param ctx the parse tree
	 */
	void exitConstantRef(ScenarioSpecParser.ConstantRefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScenarioSpecParser#identifierValue}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierValue(ScenarioSpecParser.IdentifierValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScenarioSpecParser#identifierValue}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierValue(ScenarioSpecParser.IdentifierValueContext ctx);
}