// Generated from /home/jaehun/git/mta.maude-tls-attacker/src/mta.visualizer/real/antlr/RealVisual.g4 by ANTLR 4.13.2
package mta.visualizer.real.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RealVisualParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RealVisualVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RealVisualParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(RealVisualParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link RealVisualParser#statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatements(RealVisualParser.StatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RealVisualParser#sendStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSendStmt(RealVisualParser.SendStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link RealVisualParser#recvStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecvStmt(RealVisualParser.RecvStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link RealVisualParser#layers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLayers(RealVisualParser.LayersContext ctx);
	/**
	 * Visit a parse tree produced by {@link RealVisualParser#layer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLayer(RealVisualParser.LayerContext ctx);
	/**
	 * Visit a parse tree produced by {@link RealVisualParser#message}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMessage(RealVisualParser.MessageContext ctx);
	/**
	 * Visit a parse tree produced by {@link RealVisualParser#content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContent(RealVisualParser.ContentContext ctx);
	/**
	 * Visit a parse tree produced by {@link RealVisualParser#key}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKey(RealVisualParser.KeyContext ctx);
	/**
	 * Visit a parse tree produced by {@link RealVisualParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(RealVisualParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link RealVisualParser#alias}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlias(RealVisualParser.AliasContext ctx);
	/**
	 * Visit a parse tree produced by {@link RealVisualParser#bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(RealVisualParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by {@link RealVisualParser#assertion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssertion(RealVisualParser.AssertionContext ctx);
}