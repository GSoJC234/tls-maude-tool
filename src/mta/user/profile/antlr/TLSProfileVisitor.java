// Generated from /Users/gsojc234/git/maude-tls-attacker/src/mta/user/profile/antlr/TLSProfile.g4 by ANTLR 4.13.2
package mta.user.profile.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TLSProfileParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TLSProfileVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#profiles}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProfiles(TLSProfileParser.ProfilesContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#profileBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProfileBlock(TLSProfileParser.ProfileBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#profileName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProfileName(TLSProfileParser.ProfileNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#profileEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProfileEntry(TLSProfileParser.ProfileEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#profileValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProfileValue(TLSProfileParser.ProfileValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(TLSProfileParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#dottedTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDottedTerm(TLSProfileParser.DottedTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#primaryTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryTerm(TLSProfileParser.PrimaryTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#functionTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionTerm(TLSProfileParser.FunctionTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#indexedTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexedTerm(TLSProfileParser.IndexedTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#listTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListTerm(TLSProfileParser.ListTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#braceTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBraceTerm(TLSProfileParser.BraceTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#termList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermList(TLSProfileParser.TermListContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#rawMaudeCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRawMaudeCall(TLSProfileParser.RawMaudeCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(TLSProfileParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#numberLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberLiteral(TLSProfileParser.NumberLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link TLSProfileParser#stringLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(TLSProfileParser.StringLiteralContext ctx);
}