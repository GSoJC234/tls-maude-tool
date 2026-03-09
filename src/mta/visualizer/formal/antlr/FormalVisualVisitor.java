// Generated from /home/jaehun/git/maude-tls-attacker-tool/src/mta/visualizer/formal/antlr/FormalVisual.g4 by ANTLR 4.13.2
package mta.visualizer.formal.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FormalVisualParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FormalVisualVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FormalVisualParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(FormalVisualParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormalVisualParser#sendStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSendStmt(FormalVisualParser.SendStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormalVisualParser#actor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActor(FormalVisualParser.ActorContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormalVisualParser#content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContent(FormalVisualParser.ContentContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormalVisualParser#pair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPair(FormalVisualParser.PairContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormalVisualParser#internalPair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInternalPair(FormalVisualParser.InternalPairContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormalVisualParser#leafPair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeafPair(FormalVisualParser.LeafPairContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormalVisualParser#leafKey}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeafKey(FormalVisualParser.LeafKeyContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormalVisualParser#anyText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnyText(FormalVisualParser.AnyTextContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormalVisualParser#atomToken}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomToken(FormalVisualParser.AtomTokenContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormalVisualParser#parenGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenGroup(FormalVisualParser.ParenGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormalVisualParser#braceGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBraceGroup(FormalVisualParser.BraceGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormalVisualParser#refExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRefExpr(FormalVisualParser.RefExprContext ctx);
}