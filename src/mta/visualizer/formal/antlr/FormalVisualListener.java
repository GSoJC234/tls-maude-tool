// Generated from /home/jaehun/git/maude-tls-attacker-tool/src/mta/visualizer/formal/antlr/FormalVisual.g4 by ANTLR 4.13.2
package mta.visualizer.formal.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FormalVisualParser}.
 */
public interface FormalVisualListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FormalVisualParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(FormalVisualParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormalVisualParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(FormalVisualParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormalVisualParser#sendStmt}.
	 * @param ctx the parse tree
	 */
	void enterSendStmt(FormalVisualParser.SendStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormalVisualParser#sendStmt}.
	 * @param ctx the parse tree
	 */
	void exitSendStmt(FormalVisualParser.SendStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormalVisualParser#actor}.
	 * @param ctx the parse tree
	 */
	void enterActor(FormalVisualParser.ActorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormalVisualParser#actor}.
	 * @param ctx the parse tree
	 */
	void exitActor(FormalVisualParser.ActorContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormalVisualParser#content}.
	 * @param ctx the parse tree
	 */
	void enterContent(FormalVisualParser.ContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormalVisualParser#content}.
	 * @param ctx the parse tree
	 */
	void exitContent(FormalVisualParser.ContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormalVisualParser#pair}.
	 * @param ctx the parse tree
	 */
	void enterPair(FormalVisualParser.PairContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormalVisualParser#pair}.
	 * @param ctx the parse tree
	 */
	void exitPair(FormalVisualParser.PairContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormalVisualParser#internalPair}.
	 * @param ctx the parse tree
	 */
	void enterInternalPair(FormalVisualParser.InternalPairContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormalVisualParser#internalPair}.
	 * @param ctx the parse tree
	 */
	void exitInternalPair(FormalVisualParser.InternalPairContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormalVisualParser#leafPair}.
	 * @param ctx the parse tree
	 */
	void enterLeafPair(FormalVisualParser.LeafPairContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormalVisualParser#leafPair}.
	 * @param ctx the parse tree
	 */
	void exitLeafPair(FormalVisualParser.LeafPairContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormalVisualParser#leafKey}.
	 * @param ctx the parse tree
	 */
	void enterLeafKey(FormalVisualParser.LeafKeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormalVisualParser#leafKey}.
	 * @param ctx the parse tree
	 */
	void exitLeafKey(FormalVisualParser.LeafKeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormalVisualParser#anyText}.
	 * @param ctx the parse tree
	 */
	void enterAnyText(FormalVisualParser.AnyTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormalVisualParser#anyText}.
	 * @param ctx the parse tree
	 */
	void exitAnyText(FormalVisualParser.AnyTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormalVisualParser#atomToken}.
	 * @param ctx the parse tree
	 */
	void enterAtomToken(FormalVisualParser.AtomTokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormalVisualParser#atomToken}.
	 * @param ctx the parse tree
	 */
	void exitAtomToken(FormalVisualParser.AtomTokenContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormalVisualParser#parenGroup}.
	 * @param ctx the parse tree
	 */
	void enterParenGroup(FormalVisualParser.ParenGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormalVisualParser#parenGroup}.
	 * @param ctx the parse tree
	 */
	void exitParenGroup(FormalVisualParser.ParenGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormalVisualParser#braceGroup}.
	 * @param ctx the parse tree
	 */
	void enterBraceGroup(FormalVisualParser.BraceGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormalVisualParser#braceGroup}.
	 * @param ctx the parse tree
	 */
	void exitBraceGroup(FormalVisualParser.BraceGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormalVisualParser#refExpr}.
	 * @param ctx the parse tree
	 */
	void enterRefExpr(FormalVisualParser.RefExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormalVisualParser#refExpr}.
	 * @param ctx the parse tree
	 */
	void exitRefExpr(FormalVisualParser.RefExprContext ctx);
}