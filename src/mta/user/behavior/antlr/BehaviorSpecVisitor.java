// Generated from BehaviorSpec.g4 by ANTLR 4.13.2
package mta.user.behavior.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BehaviorSpecParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BehaviorSpecVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#behaviorSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBehaviorSpec(BehaviorSpecParser.BehaviorSpecContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#behaviorIdSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBehaviorIdSection(BehaviorSpecParser.BehaviorIdSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#parametersSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametersSection(BehaviorSpecParser.ParametersSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#eventTypeSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEventTypeSection(BehaviorSpecParser.EventTypeSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#conditionsSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionsSection(BehaviorSpecParser.ConditionsSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#conditionExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionExpr(BehaviorSpecParser.ConditionExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#conditionPredicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionPredicate(BehaviorSpecParser.ConditionPredicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#valueAccessor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueAccessor(BehaviorSpecParser.ValueAccessorContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#modificationSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModificationSection(BehaviorSpecParser.ModificationSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#modificationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModificationStatement(BehaviorSpecParser.ModificationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#addModification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddModification(BehaviorSpecParser.AddModificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#setModification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetModification(BehaviorSpecParser.SetModificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#removeModification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemoveModification(BehaviorSpecParser.RemoveModificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#noCheckModification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoCheckModification(BehaviorSpecParser.NoCheckModificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#skipModification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkipModification(BehaviorSpecParser.SkipModificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#delayModification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelayModification(BehaviorSpecParser.DelayModificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#modificationValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModificationValue(BehaviorSpecParser.ModificationValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#targetRef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTargetRef(BehaviorSpecParser.TargetRefContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#operandValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperandValue(BehaviorSpecParser.OperandValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#parameterRef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterRef(BehaviorSpecParser.ParameterRefContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#identifierValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierValue(BehaviorSpecParser.IdentifierValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#hexLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHexLiteral(BehaviorSpecParser.HexLiteralContext ctx);
}