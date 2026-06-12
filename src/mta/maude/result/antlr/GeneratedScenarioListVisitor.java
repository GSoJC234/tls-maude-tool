// Generated from src/mta/maude/result/antlr/GeneratedScenarioList.g4 by ANTLR 4.13.2

package mta.maude.result.antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GeneratedScenarioListParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GeneratedScenarioListVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GeneratedScenarioListParser#resultTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResultTerm(GeneratedScenarioListParser.ResultTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link GeneratedScenarioListParser#emptyList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyList(GeneratedScenarioListParser.EmptyListContext ctx);
	/**
	 * Visit a parse tree produced by {@link GeneratedScenarioListParser#castEmptyList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCastEmptyList(GeneratedScenarioListParser.CastEmptyListContext ctx);
	/**
	 * Visit a parse tree produced by {@link GeneratedScenarioListParser#bracketTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracketTerm(GeneratedScenarioListParser.BracketTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link GeneratedScenarioListParser#genericTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenericTerm(GeneratedScenarioListParser.GenericTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link GeneratedScenarioListParser#parenTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenTerm(GeneratedScenarioListParser.ParenTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link GeneratedScenarioListParser#termPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermPart(GeneratedScenarioListParser.TermPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link GeneratedScenarioListParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(GeneratedScenarioListParser.AtomContext ctx);
}