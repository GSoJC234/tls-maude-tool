// Generated from /Users/gsojc234/git/maude-tls-attacker/src/mta/user/profile/antlr/TLSProfile.g4 by ANTLR 4.13.2
package mta.user.profile.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class TLSProfileParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TLS_PROFILES=1, TESTER=2, TARGET=3, MAUDE=4, COLON=5, COMMA=6, DOT=7, 
		LPAREN=8, RPAREN=9, LBRACK=10, RBRACK=11, LBRACE=12, RBRACE=13, NUMBER=14, 
		STRING=15, IDENTIFIER=16, WS=17, LINE_COMMENT=18;
	public static final int
		RULE_profiles = 0, RULE_profileBlock = 1, RULE_profileName = 2, RULE_profileEntry = 3, 
		RULE_profileValue = 4, RULE_term = 5, RULE_dottedTerm = 6, RULE_primaryTerm = 7, 
		RULE_functionTerm = 8, RULE_indexedTerm = 9, RULE_listTerm = 10, RULE_braceTerm = 11, 
		RULE_termList = 12, RULE_rawMaudeCall = 13, RULE_identifier = 14, RULE_numberLiteral = 15, 
		RULE_stringLiteral = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"profiles", "profileBlock", "profileName", "profileEntry", "profileValue", 
			"term", "dottedTerm", "primaryTerm", "functionTerm", "indexedTerm", "listTerm", 
			"braceTerm", "termList", "rawMaudeCall", "identifier", "numberLiteral", 
			"stringLiteral"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'TLSProfiles'", "'tester'", "'target'", "'maude'", "':'", "','", 
			"'.'", "'('", "')'", "'['", "']'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "TLS_PROFILES", "TESTER", "TARGET", "MAUDE", "COLON", "COMMA", 
			"DOT", "LPAREN", "RPAREN", "LBRACK", "RBRACK", "LBRACE", "RBRACE", "NUMBER", 
			"STRING", "IDENTIFIER", "WS", "LINE_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "TLSProfile.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TLSProfileParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProfilesContext extends ParserRuleContext {
		public TerminalNode TLS_PROFILES() { return getToken(TLSProfileParser.TLS_PROFILES, 0); }
		public TerminalNode COLON() { return getToken(TLSProfileParser.COLON, 0); }
		public TerminalNode EOF() { return getToken(TLSProfileParser.EOF, 0); }
		public List<ProfileBlockContext> profileBlock() {
			return getRuleContexts(ProfileBlockContext.class);
		}
		public ProfileBlockContext profileBlock(int i) {
			return getRuleContext(ProfileBlockContext.class,i);
		}
		public ProfilesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_profiles; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).enterProfiles(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).exitProfiles(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TLSProfileVisitor ) return ((TLSProfileVisitor<? extends T>)visitor).visitProfiles(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProfilesContext profiles() throws RecognitionException {
		ProfilesContext _localctx = new ProfilesContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_profiles);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(TLS_PROFILES);
			setState(35);
			match(COLON);
			setState(37); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(36);
				profileBlock();
				}
				}
				setState(39); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==TESTER || _la==TARGET );
			setState(41);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProfileBlockContext extends ParserRuleContext {
		public ProfileNameContext profileName() {
			return getRuleContext(ProfileNameContext.class,0);
		}
		public TerminalNode COLON() { return getToken(TLSProfileParser.COLON, 0); }
		public List<ProfileEntryContext> profileEntry() {
			return getRuleContexts(ProfileEntryContext.class);
		}
		public ProfileEntryContext profileEntry(int i) {
			return getRuleContext(ProfileEntryContext.class,i);
		}
		public ProfileBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_profileBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).enterProfileBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).exitProfileBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TLSProfileVisitor ) return ((TLSProfileVisitor<? extends T>)visitor).visitProfileBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProfileBlockContext profileBlock() throws RecognitionException {
		ProfileBlockContext _localctx = new ProfileBlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_profileBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			profileName();
			setState(44);
			match(COLON);
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENTIFIER) {
				{
				{
				setState(45);
				profileEntry();
				}
				}
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProfileNameContext extends ParserRuleContext {
		public TerminalNode TESTER() { return getToken(TLSProfileParser.TESTER, 0); }
		public TerminalNode TARGET() { return getToken(TLSProfileParser.TARGET, 0); }
		public ProfileNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_profileName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).enterProfileName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).exitProfileName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TLSProfileVisitor ) return ((TLSProfileVisitor<? extends T>)visitor).visitProfileName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProfileNameContext profileName() throws RecognitionException {
		ProfileNameContext _localctx = new ProfileNameContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_profileName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			_la = _input.LA(1);
			if ( !(_la==TESTER || _la==TARGET) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProfileEntryContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode COLON() { return getToken(TLSProfileParser.COLON, 0); }
		public ProfileValueContext profileValue() {
			return getRuleContext(ProfileValueContext.class,0);
		}
		public ProfileEntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_profileEntry; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).enterProfileEntry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).exitProfileEntry(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TLSProfileVisitor ) return ((TLSProfileVisitor<? extends T>)visitor).visitProfileEntry(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProfileEntryContext profileEntry() throws RecognitionException {
		ProfileEntryContext _localctx = new ProfileEntryContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_profileEntry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			identifier();
			setState(54);
			match(COLON);
			setState(55);
			profileValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProfileValueContext extends ParserRuleContext {
		public ListTermContext listTerm() {
			return getRuleContext(ListTermContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public ProfileValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_profileValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).enterProfileValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).exitProfileValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TLSProfileVisitor ) return ((TLSProfileVisitor<? extends T>)visitor).visitProfileValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProfileValueContext profileValue() throws RecognitionException {
		ProfileValueContext _localctx = new ProfileValueContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_profileValue);
		try {
			setState(59);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(57);
				listTerm();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
				term();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermContext extends ParserRuleContext {
		public DottedTermContext dottedTerm() {
			return getRuleContext(DottedTermContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TLSProfileVisitor ) return ((TLSProfileVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_term);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			dottedTerm();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DottedTermContext extends ParserRuleContext {
		public List<PrimaryTermContext> primaryTerm() {
			return getRuleContexts(PrimaryTermContext.class);
		}
		public PrimaryTermContext primaryTerm(int i) {
			return getRuleContext(PrimaryTermContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(TLSProfileParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(TLSProfileParser.DOT, i);
		}
		public DottedTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dottedTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).enterDottedTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).exitDottedTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TLSProfileVisitor ) return ((TLSProfileVisitor<? extends T>)visitor).visitDottedTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DottedTermContext dottedTerm() throws RecognitionException {
		DottedTermContext _localctx = new DottedTermContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_dottedTerm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			primaryTerm();
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(64);
				match(DOT);
				setState(65);
				primaryTerm();
				}
				}
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryTermContext extends ParserRuleContext {
		public RawMaudeCallContext rawMaudeCall() {
			return getRuleContext(RawMaudeCallContext.class,0);
		}
		public FunctionTermContext functionTerm() {
			return getRuleContext(FunctionTermContext.class,0);
		}
		public IndexedTermContext indexedTerm() {
			return getRuleContext(IndexedTermContext.class,0);
		}
		public ListTermContext listTerm() {
			return getRuleContext(ListTermContext.class,0);
		}
		public BraceTermContext braceTerm() {
			return getRuleContext(BraceTermContext.class,0);
		}
		public StringLiteralContext stringLiteral() {
			return getRuleContext(StringLiteralContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public NumberLiteralContext numberLiteral() {
			return getRuleContext(NumberLiteralContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(TLSProfileParser.LPAREN, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(TLSProfileParser.RPAREN, 0); }
		public PrimaryTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).enterPrimaryTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).exitPrimaryTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TLSProfileVisitor ) return ((TLSProfileVisitor<? extends T>)visitor).visitPrimaryTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryTermContext primaryTerm() throws RecognitionException {
		PrimaryTermContext _localctx = new PrimaryTermContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_primaryTerm);
		try {
			setState(83);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(71);
				rawMaudeCall();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
				functionTerm();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(73);
				indexedTerm();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(74);
				listTerm();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(75);
				braceTerm();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(76);
				stringLiteral();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(77);
				identifier();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(78);
				numberLiteral();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(79);
				match(LPAREN);
				setState(80);
				term();
				setState(81);
				match(RPAREN);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionTermContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(TLSProfileParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(TLSProfileParser.RPAREN, 0); }
		public TermListContext termList() {
			return getRuleContext(TermListContext.class,0);
		}
		public FunctionTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).enterFunctionTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).exitFunctionTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TLSProfileVisitor ) return ((TLSProfileVisitor<? extends T>)visitor).visitFunctionTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionTermContext functionTerm() throws RecognitionException {
		FunctionTermContext _localctx = new FunctionTermContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_functionTerm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			identifier();
			setState(86);
			match(LPAREN);
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 120080L) != 0)) {
				{
				setState(87);
				termList();
				}
			}

			setState(90);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IndexedTermContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode LBRACK() { return getToken(TLSProfileParser.LBRACK, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(TLSProfileParser.RBRACK, 0); }
		public IndexedTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexedTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).enterIndexedTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).exitIndexedTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TLSProfileVisitor ) return ((TLSProfileVisitor<? extends T>)visitor).visitIndexedTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexedTermContext indexedTerm() throws RecognitionException {
		IndexedTermContext _localctx = new IndexedTermContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_indexedTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			identifier();
			setState(93);
			match(LBRACK);
			setState(94);
			term();
			setState(95);
			match(RBRACK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListTermContext extends ParserRuleContext {
		public TerminalNode LBRACK() { return getToken(TLSProfileParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(TLSProfileParser.RBRACK, 0); }
		public TermListContext termList() {
			return getRuleContext(TermListContext.class,0);
		}
		public ListTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).enterListTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).exitListTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TLSProfileVisitor ) return ((TLSProfileVisitor<? extends T>)visitor).visitListTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListTermContext listTerm() throws RecognitionException {
		ListTermContext _localctx = new ListTermContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_listTerm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			match(LBRACK);
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 120080L) != 0)) {
				{
				setState(98);
				termList();
				}
			}

			setState(101);
			match(RBRACK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BraceTermContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(TLSProfileParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(TLSProfileParser.RBRACE, 0); }
		public TermListContext termList() {
			return getRuleContext(TermListContext.class,0);
		}
		public BraceTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_braceTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).enterBraceTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).exitBraceTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TLSProfileVisitor ) return ((TLSProfileVisitor<? extends T>)visitor).visitBraceTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BraceTermContext braceTerm() throws RecognitionException {
		BraceTermContext _localctx = new BraceTermContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_braceTerm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			match(LBRACE);
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 120080L) != 0)) {
				{
				setState(104);
				termList();
				}
			}

			setState(107);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermListContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(TLSProfileParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(TLSProfileParser.COMMA, i);
		}
		public TermListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).enterTermList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).exitTermList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TLSProfileVisitor ) return ((TLSProfileVisitor<? extends T>)visitor).visitTermList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermListContext termList() throws RecognitionException {
		TermListContext _localctx = new TermListContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_termList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			term();
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(110);
				match(COMMA);
				setState(111);
				term();
				}
				}
				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RawMaudeCallContext extends ParserRuleContext {
		public TerminalNode MAUDE() { return getToken(TLSProfileParser.MAUDE, 0); }
		public TerminalNode LPAREN() { return getToken(TLSProfileParser.LPAREN, 0); }
		public StringLiteralContext stringLiteral() {
			return getRuleContext(StringLiteralContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(TLSProfileParser.RPAREN, 0); }
		public RawMaudeCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rawMaudeCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).enterRawMaudeCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).exitRawMaudeCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TLSProfileVisitor ) return ((TLSProfileVisitor<? extends T>)visitor).visitRawMaudeCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RawMaudeCallContext rawMaudeCall() throws RecognitionException {
		RawMaudeCallContext _localctx = new RawMaudeCallContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_rawMaudeCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(MAUDE);
			setState(118);
			match(LPAREN);
			setState(119);
			stringLiteral();
			setState(120);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(TLSProfileParser.IDENTIFIER, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TLSProfileVisitor ) return ((TLSProfileVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumberLiteralContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(TLSProfileParser.NUMBER, 0); }
		public NumberLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numberLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).enterNumberLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).exitNumberLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TLSProfileVisitor ) return ((TLSProfileVisitor<? extends T>)visitor).visitNumberLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberLiteralContext numberLiteral() throws RecognitionException {
		NumberLiteralContext _localctx = new NumberLiteralContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_numberLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StringLiteralContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(TLSProfileParser.STRING, 0); }
		public StringLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).enterStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TLSProfileListener ) ((TLSProfileListener)listener).exitStringLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TLSProfileVisitor ) return ((TLSProfileVisitor<? extends T>)visitor).visitStringLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringLiteralContext stringLiteral() throws RecognitionException {
		StringLiteralContext _localctx = new StringLiteralContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_stringLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0012\u0081\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0001\u0000\u0001\u0000\u0001\u0000\u0004"+
		"\u0000&\b\u0000\u000b\u0000\f\u0000\'\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0005\u0001/\b\u0001\n\u0001\f\u00012\t\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0003\u0004<\b\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006C\b\u0006\n\u0006\f\u0006"+
		"F\t\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0003\u0007T\b\u0007\u0001\b\u0001\b\u0001\b\u0003\bY\b\b"+
		"\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0003\nd\b\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0003\u000bj\b"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0005\fq\b\f\n"+
		"\f\f\ft\t\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001"+
		"\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0000"+
		"\u0000\u0011\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u001a\u001c\u001e \u0000\u0001\u0001\u0000\u0002\u0003\u007f\u0000"+
		"\"\u0001\u0000\u0000\u0000\u0002+\u0001\u0000\u0000\u0000\u00043\u0001"+
		"\u0000\u0000\u0000\u00065\u0001\u0000\u0000\u0000\b;\u0001\u0000\u0000"+
		"\u0000\n=\u0001\u0000\u0000\u0000\f?\u0001\u0000\u0000\u0000\u000eS\u0001"+
		"\u0000\u0000\u0000\u0010U\u0001\u0000\u0000\u0000\u0012\\\u0001\u0000"+
		"\u0000\u0000\u0014a\u0001\u0000\u0000\u0000\u0016g\u0001\u0000\u0000\u0000"+
		"\u0018m\u0001\u0000\u0000\u0000\u001au\u0001\u0000\u0000\u0000\u001cz"+
		"\u0001\u0000\u0000\u0000\u001e|\u0001\u0000\u0000\u0000 ~\u0001\u0000"+
		"\u0000\u0000\"#\u0005\u0001\u0000\u0000#%\u0005\u0005\u0000\u0000$&\u0003"+
		"\u0002\u0001\u0000%$\u0001\u0000\u0000\u0000&\'\u0001\u0000\u0000\u0000"+
		"\'%\u0001\u0000\u0000\u0000\'(\u0001\u0000\u0000\u0000()\u0001\u0000\u0000"+
		"\u0000)*\u0005\u0000\u0000\u0001*\u0001\u0001\u0000\u0000\u0000+,\u0003"+
		"\u0004\u0002\u0000,0\u0005\u0005\u0000\u0000-/\u0003\u0006\u0003\u0000"+
		".-\u0001\u0000\u0000\u0000/2\u0001\u0000\u0000\u00000.\u0001\u0000\u0000"+
		"\u000001\u0001\u0000\u0000\u00001\u0003\u0001\u0000\u0000\u000020\u0001"+
		"\u0000\u0000\u000034\u0007\u0000\u0000\u00004\u0005\u0001\u0000\u0000"+
		"\u000056\u0003\u001c\u000e\u000067\u0005\u0005\u0000\u000078\u0003\b\u0004"+
		"\u00008\u0007\u0001\u0000\u0000\u00009<\u0003\u0014\n\u0000:<\u0003\n"+
		"\u0005\u0000;9\u0001\u0000\u0000\u0000;:\u0001\u0000\u0000\u0000<\t\u0001"+
		"\u0000\u0000\u0000=>\u0003\f\u0006\u0000>\u000b\u0001\u0000\u0000\u0000"+
		"?D\u0003\u000e\u0007\u0000@A\u0005\u0007\u0000\u0000AC\u0003\u000e\u0007"+
		"\u0000B@\u0001\u0000\u0000\u0000CF\u0001\u0000\u0000\u0000DB\u0001\u0000"+
		"\u0000\u0000DE\u0001\u0000\u0000\u0000E\r\u0001\u0000\u0000\u0000FD\u0001"+
		"\u0000\u0000\u0000GT\u0003\u001a\r\u0000HT\u0003\u0010\b\u0000IT\u0003"+
		"\u0012\t\u0000JT\u0003\u0014\n\u0000KT\u0003\u0016\u000b\u0000LT\u0003"+
		" \u0010\u0000MT\u0003\u001c\u000e\u0000NT\u0003\u001e\u000f\u0000OP\u0005"+
		"\b\u0000\u0000PQ\u0003\n\u0005\u0000QR\u0005\t\u0000\u0000RT\u0001\u0000"+
		"\u0000\u0000SG\u0001\u0000\u0000\u0000SH\u0001\u0000\u0000\u0000SI\u0001"+
		"\u0000\u0000\u0000SJ\u0001\u0000\u0000\u0000SK\u0001\u0000\u0000\u0000"+
		"SL\u0001\u0000\u0000\u0000SM\u0001\u0000\u0000\u0000SN\u0001\u0000\u0000"+
		"\u0000SO\u0001\u0000\u0000\u0000T\u000f\u0001\u0000\u0000\u0000UV\u0003"+
		"\u001c\u000e\u0000VX\u0005\b\u0000\u0000WY\u0003\u0018\f\u0000XW\u0001"+
		"\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000YZ\u0001\u0000\u0000\u0000"+
		"Z[\u0005\t\u0000\u0000[\u0011\u0001\u0000\u0000\u0000\\]\u0003\u001c\u000e"+
		"\u0000]^\u0005\n\u0000\u0000^_\u0003\n\u0005\u0000_`\u0005\u000b\u0000"+
		"\u0000`\u0013\u0001\u0000\u0000\u0000ac\u0005\n\u0000\u0000bd\u0003\u0018"+
		"\f\u0000cb\u0001\u0000\u0000\u0000cd\u0001\u0000\u0000\u0000de\u0001\u0000"+
		"\u0000\u0000ef\u0005\u000b\u0000\u0000f\u0015\u0001\u0000\u0000\u0000"+
		"gi\u0005\f\u0000\u0000hj\u0003\u0018\f\u0000ih\u0001\u0000\u0000\u0000"+
		"ij\u0001\u0000\u0000\u0000jk\u0001\u0000\u0000\u0000kl\u0005\r\u0000\u0000"+
		"l\u0017\u0001\u0000\u0000\u0000mr\u0003\n\u0005\u0000no\u0005\u0006\u0000"+
		"\u0000oq\u0003\n\u0005\u0000pn\u0001\u0000\u0000\u0000qt\u0001\u0000\u0000"+
		"\u0000rp\u0001\u0000\u0000\u0000rs\u0001\u0000\u0000\u0000s\u0019\u0001"+
		"\u0000\u0000\u0000tr\u0001\u0000\u0000\u0000uv\u0005\u0004\u0000\u0000"+
		"vw\u0005\b\u0000\u0000wx\u0003 \u0010\u0000xy\u0005\t\u0000\u0000y\u001b"+
		"\u0001\u0000\u0000\u0000z{\u0005\u0010\u0000\u0000{\u001d\u0001\u0000"+
		"\u0000\u0000|}\u0005\u000e\u0000\u0000}\u001f\u0001\u0000\u0000\u0000"+
		"~\u007f\u0005\u000f\u0000\u0000\u007f!\u0001\u0000\u0000\u0000\t\'0;D"+
		"SXcir";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}