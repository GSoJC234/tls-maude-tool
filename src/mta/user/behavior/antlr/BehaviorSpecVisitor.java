// Generated from src/mta/user/behavior/antlr/BehaviorSpec.g4 by ANTLR 4.13.2
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
	 * Visit a parse tree produced by {@link BehaviorSpecParser#behaviorDeviationSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBehaviorDeviationSpecification(BehaviorSpecParser.BehaviorDeviationSpecificationContext ctx);
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
	 * Visit a parse tree produced by {@link BehaviorSpecParser#conditionsSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionsSection(BehaviorSpecParser.ConditionsSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#modificationsSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModificationsSection(BehaviorSpecParser.ModificationsSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#parameterInstancesSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterInstancesSection(BehaviorSpecParser.ParameterInstancesSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#parameterInstance}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterInstance(BehaviorSpecParser.ParameterInstanceContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#parameterBinding}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterBinding(BehaviorSpecParser.ParameterBindingContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#actionExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionExpr(BehaviorSpecParser.ActionExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#actionOr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionOr(BehaviorSpecParser.ActionOrContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#actionAnd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionAnd(BehaviorSpecParser.ActionAndContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#actionNot}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionNot(BehaviorSpecParser.ActionNotContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#actionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionAtom(BehaviorSpecParser.ActionAtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#modificationExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModificationExpr(BehaviorSpecParser.ModificationExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#modificationCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModificationCall(BehaviorSpecParser.ModificationCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(BehaviorSpecParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#dottedTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDottedTerm(BehaviorSpecParser.DottedTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#primaryTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryTerm(BehaviorSpecParser.PrimaryTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#functionTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionTerm(BehaviorSpecParser.FunctionTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#indexedTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexedTerm(BehaviorSpecParser.IndexedTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#listTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListTerm(BehaviorSpecParser.ListTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#braceTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBraceTerm(BehaviorSpecParser.BraceTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#termList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermList(BehaviorSpecParser.TermListContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#rawMaudeCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRawMaudeCall(BehaviorSpecParser.RawMaudeCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#parameterRef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterRef(BehaviorSpecParser.ParameterRefContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#parameterName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterName(BehaviorSpecParser.ParameterNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(BehaviorSpecParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#numberLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberLiteral(BehaviorSpecParser.NumberLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link BehaviorSpecParser#stringLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(BehaviorSpecParser.StringLiteralContext ctx);
}