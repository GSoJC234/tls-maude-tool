// Generated from /Users/gsojc234/git/maude-tls-attacker/src/mta/user/scenario/antlr/ScenarioSpec.g4 by ANTLR 4.13.2
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
	 * Visit a parse tree produced by {@link ScenarioSpecParser#scenarioItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScenarioItem(ScenarioSpecParser.ScenarioItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#loadStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadStatement(ScenarioSpecParser.LoadStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#useStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUseStatement(ScenarioSpecParser.UseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#nodesSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNodesSection(ScenarioSpecParser.NodesSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#linkSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLinkSection(ScenarioSpecParser.LinkSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#linkDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLinkDeclaration(ScenarioSpecParser.LinkDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#linkedIdentifiers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLinkedIdentifiers(ScenarioSpecParser.LinkedIdentifiersContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#constantsSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantsSection(ScenarioSpecParser.ConstantsSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#constantDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDeclaration(ScenarioSpecParser.ConstantDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#constantSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantSet(ScenarioSpecParser.ConstantSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#constantValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantValue(ScenarioSpecParser.ConstantValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#statePropositionsSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatePropositionsSection(ScenarioSpecParser.StatePropositionsSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#statePropositionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatePropositionDeclaration(ScenarioSpecParser.StatePropositionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#statePropositionExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatePropositionExpr(ScenarioSpecParser.StatePropositionExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#statePropositionTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatePropositionTerm(ScenarioSpecParser.StatePropositionTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#actionPropositionsSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionPropositionsSection(ScenarioSpecParser.ActionPropositionsSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#actionPropositionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionPropositionDeclaration(ScenarioSpecParser.ActionPropositionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#actionInvocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionInvocation(ScenarioSpecParser.ActionInvocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#actionArgument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionArgument(ScenarioSpecParser.ActionArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#oneOfExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOneOfExpr(ScenarioSpecParser.OneOfExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#actionValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionValue(ScenarioSpecParser.ActionValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#scenarioPropertiesSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScenarioPropertiesSection(ScenarioSpecParser.ScenarioPropertiesSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#scenarioPropertyDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScenarioPropertyDeclaration(ScenarioSpecParser.ScenarioPropertyDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#linkQualifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLinkQualifier(ScenarioSpecParser.LinkQualifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#nodeBinding}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNodeBinding(ScenarioSpecParser.NodeBindingContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#propertyRelation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyRelation(ScenarioSpecParser.PropertyRelationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#propertyReferenceValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyReferenceValue(ScenarioSpecParser.PropertyReferenceValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#conditionExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionExpr(ScenarioSpecParser.ConditionExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#conditionPredicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionPredicate(ScenarioSpecParser.ConditionPredicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#valueAccessor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueAccessor(ScenarioSpecParser.ValueAccessorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#conditionOperand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionOperand(ScenarioSpecParser.ConditionOperandContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#stringLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(ScenarioSpecParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#constantRef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantRef(ScenarioSpecParser.ConstantRefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#identifierValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierValue(ScenarioSpecParser.IdentifierValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScenarioSpecParser#hexLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHexLiteral(ScenarioSpecParser.HexLiteralContext ctx);
}