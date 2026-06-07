// Generated from /Users/gsojc234/git/maude-tls-attacker/src/mta/user/profile/antlr/TLSProfile.g4 by ANTLR 4.13.2
package mta.user.profile.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TLSProfileParser}.
 */
public interface TLSProfileListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#profiles}.
	 * @param ctx the parse tree
	 */
	void enterProfiles(TLSProfileParser.ProfilesContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#profiles}.
	 * @param ctx the parse tree
	 */
	void exitProfiles(TLSProfileParser.ProfilesContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#profileBlock}.
	 * @param ctx the parse tree
	 */
	void enterProfileBlock(TLSProfileParser.ProfileBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#profileBlock}.
	 * @param ctx the parse tree
	 */
	void exitProfileBlock(TLSProfileParser.ProfileBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#profileName}.
	 * @param ctx the parse tree
	 */
	void enterProfileName(TLSProfileParser.ProfileNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#profileName}.
	 * @param ctx the parse tree
	 */
	void exitProfileName(TLSProfileParser.ProfileNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#profileEntry}.
	 * @param ctx the parse tree
	 */
	void enterProfileEntry(TLSProfileParser.ProfileEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#profileEntry}.
	 * @param ctx the parse tree
	 */
	void exitProfileEntry(TLSProfileParser.ProfileEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#profileValue}.
	 * @param ctx the parse tree
	 */
	void enterProfileValue(TLSProfileParser.ProfileValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#profileValue}.
	 * @param ctx the parse tree
	 */
	void exitProfileValue(TLSProfileParser.ProfileValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(TLSProfileParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(TLSProfileParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#dottedTerm}.
	 * @param ctx the parse tree
	 */
	void enterDottedTerm(TLSProfileParser.DottedTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#dottedTerm}.
	 * @param ctx the parse tree
	 */
	void exitDottedTerm(TLSProfileParser.DottedTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#primaryTerm}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryTerm(TLSProfileParser.PrimaryTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#primaryTerm}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryTerm(TLSProfileParser.PrimaryTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#functionTerm}.
	 * @param ctx the parse tree
	 */
	void enterFunctionTerm(TLSProfileParser.FunctionTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#functionTerm}.
	 * @param ctx the parse tree
	 */
	void exitFunctionTerm(TLSProfileParser.FunctionTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#indexedTerm}.
	 * @param ctx the parse tree
	 */
	void enterIndexedTerm(TLSProfileParser.IndexedTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#indexedTerm}.
	 * @param ctx the parse tree
	 */
	void exitIndexedTerm(TLSProfileParser.IndexedTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#listTerm}.
	 * @param ctx the parse tree
	 */
	void enterListTerm(TLSProfileParser.ListTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#listTerm}.
	 * @param ctx the parse tree
	 */
	void exitListTerm(TLSProfileParser.ListTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#braceTerm}.
	 * @param ctx the parse tree
	 */
	void enterBraceTerm(TLSProfileParser.BraceTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#braceTerm}.
	 * @param ctx the parse tree
	 */
	void exitBraceTerm(TLSProfileParser.BraceTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#termList}.
	 * @param ctx the parse tree
	 */
	void enterTermList(TLSProfileParser.TermListContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#termList}.
	 * @param ctx the parse tree
	 */
	void exitTermList(TLSProfileParser.TermListContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#rawMaudeCall}.
	 * @param ctx the parse tree
	 */
	void enterRawMaudeCall(TLSProfileParser.RawMaudeCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#rawMaudeCall}.
	 * @param ctx the parse tree
	 */
	void exitRawMaudeCall(TLSProfileParser.RawMaudeCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(TLSProfileParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(TLSProfileParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#numberLiteral}.
	 * @param ctx the parse tree
	 */
	void enterNumberLiteral(TLSProfileParser.NumberLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#numberLiteral}.
	 * @param ctx the parse tree
	 */
	void exitNumberLiteral(TLSProfileParser.NumberLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link TLSProfileParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(TLSProfileParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link TLSProfileParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(TLSProfileParser.StringLiteralContext ctx);
}