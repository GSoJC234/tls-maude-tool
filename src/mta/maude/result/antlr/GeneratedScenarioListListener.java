// Generated from src/mta/maude/result/antlr/GeneratedScenarioList.g4 by ANTLR 4.13.2

package mta.maude.result.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GeneratedScenarioListParser}.
 */
public interface GeneratedScenarioListListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GeneratedScenarioListParser#resultTerm}.
	 * @param ctx the parse tree
	 */
	void enterResultTerm(GeneratedScenarioListParser.ResultTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedScenarioListParser#resultTerm}.
	 * @param ctx the parse tree
	 */
	void exitResultTerm(GeneratedScenarioListParser.ResultTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedScenarioListParser#emptyList}.
	 * @param ctx the parse tree
	 */
	void enterEmptyList(GeneratedScenarioListParser.EmptyListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedScenarioListParser#emptyList}.
	 * @param ctx the parse tree
	 */
	void exitEmptyList(GeneratedScenarioListParser.EmptyListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedScenarioListParser#castEmptyList}.
	 * @param ctx the parse tree
	 */
	void enterCastEmptyList(GeneratedScenarioListParser.CastEmptyListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedScenarioListParser#castEmptyList}.
	 * @param ctx the parse tree
	 */
	void exitCastEmptyList(GeneratedScenarioListParser.CastEmptyListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedScenarioListParser#bracketTerm}.
	 * @param ctx the parse tree
	 */
	void enterBracketTerm(GeneratedScenarioListParser.BracketTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedScenarioListParser#bracketTerm}.
	 * @param ctx the parse tree
	 */
	void exitBracketTerm(GeneratedScenarioListParser.BracketTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedScenarioListParser#genericTerm}.
	 * @param ctx the parse tree
	 */
	void enterGenericTerm(GeneratedScenarioListParser.GenericTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedScenarioListParser#genericTerm}.
	 * @param ctx the parse tree
	 */
	void exitGenericTerm(GeneratedScenarioListParser.GenericTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedScenarioListParser#parenTerm}.
	 * @param ctx the parse tree
	 */
	void enterParenTerm(GeneratedScenarioListParser.ParenTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedScenarioListParser#parenTerm}.
	 * @param ctx the parse tree
	 */
	void exitParenTerm(GeneratedScenarioListParser.ParenTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedScenarioListParser#termPart}.
	 * @param ctx the parse tree
	 */
	void enterTermPart(GeneratedScenarioListParser.TermPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedScenarioListParser#termPart}.
	 * @param ctx the parse tree
	 */
	void exitTermPart(GeneratedScenarioListParser.TermPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link GeneratedScenarioListParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(GeneratedScenarioListParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link GeneratedScenarioListParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(GeneratedScenarioListParser.AtomContext ctx);
}