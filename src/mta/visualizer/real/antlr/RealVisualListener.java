// Generated from /home/jaehun/git/maude-tls-attacker-tool/src/mta/visualizer/real/antlr/RealVisual.g4 by ANTLR 4.13.2
package mta.visualizer.real.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RealVisualParser}.
 */
public interface RealVisualListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RealVisualParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(RealVisualParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link RealVisualParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(RealVisualParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link RealVisualParser#statements}.
	 * @param ctx the parse tree
	 */
	void enterStatements(RealVisualParser.StatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link RealVisualParser#statements}.
	 * @param ctx the parse tree
	 */
	void exitStatements(RealVisualParser.StatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link RealVisualParser#sendStmt}.
	 * @param ctx the parse tree
	 */
	void enterSendStmt(RealVisualParser.SendStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link RealVisualParser#sendStmt}.
	 * @param ctx the parse tree
	 */
	void exitSendStmt(RealVisualParser.SendStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link RealVisualParser#recvStmt}.
	 * @param ctx the parse tree
	 */
	void enterRecvStmt(RealVisualParser.RecvStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link RealVisualParser#recvStmt}.
	 * @param ctx the parse tree
	 */
	void exitRecvStmt(RealVisualParser.RecvStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link RealVisualParser#layers}.
	 * @param ctx the parse tree
	 */
	void enterLayers(RealVisualParser.LayersContext ctx);
	/**
	 * Exit a parse tree produced by {@link RealVisualParser#layers}.
	 * @param ctx the parse tree
	 */
	void exitLayers(RealVisualParser.LayersContext ctx);
	/**
	 * Enter a parse tree produced by {@link RealVisualParser#layer}.
	 * @param ctx the parse tree
	 */
	void enterLayer(RealVisualParser.LayerContext ctx);
	/**
	 * Exit a parse tree produced by {@link RealVisualParser#layer}.
	 * @param ctx the parse tree
	 */
	void exitLayer(RealVisualParser.LayerContext ctx);
	/**
	 * Enter a parse tree produced by {@link RealVisualParser#message}.
	 * @param ctx the parse tree
	 */
	void enterMessage(RealVisualParser.MessageContext ctx);
	/**
	 * Exit a parse tree produced by {@link RealVisualParser#message}.
	 * @param ctx the parse tree
	 */
	void exitMessage(RealVisualParser.MessageContext ctx);
	/**
	 * Enter a parse tree produced by {@link RealVisualParser#content}.
	 * @param ctx the parse tree
	 */
	void enterContent(RealVisualParser.ContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link RealVisualParser#content}.
	 * @param ctx the parse tree
	 */
	void exitContent(RealVisualParser.ContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link RealVisualParser#key}.
	 * @param ctx the parse tree
	 */
	void enterKey(RealVisualParser.KeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link RealVisualParser#key}.
	 * @param ctx the parse tree
	 */
	void exitKey(RealVisualParser.KeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link RealVisualParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(RealVisualParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link RealVisualParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(RealVisualParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link RealVisualParser#alias}.
	 * @param ctx the parse tree
	 */
	void enterAlias(RealVisualParser.AliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link RealVisualParser#alias}.
	 * @param ctx the parse tree
	 */
	void exitAlias(RealVisualParser.AliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link RealVisualParser#bool}.
	 * @param ctx the parse tree
	 */
	void enterBool(RealVisualParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link RealVisualParser#bool}.
	 * @param ctx the parse tree
	 */
	void exitBool(RealVisualParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by {@link RealVisualParser#assertion}.
	 * @param ctx the parse tree
	 */
	void enterAssertion(RealVisualParser.AssertionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RealVisualParser#assertion}.
	 * @param ctx the parse tree
	 */
	void exitAssertion(RealVisualParser.AssertionContext ctx);
}