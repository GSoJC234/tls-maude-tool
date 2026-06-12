// Generated from src/mta/maude/result/antlr/GeneratedScenarioList.g4 by ANTLR 4.13.2

package mta.maude.result.antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class GeneratedScenarioListParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LIST=1, SCEN=2, LBRACK=3, RBRACK=4, LPAREN=5, RPAREN=6, LBRACE=7, RBRACE=8, 
		DOT=9, RAW=10;
	public static final int
		RULE_resultTerm = 0, RULE_emptyList = 1, RULE_castEmptyList = 2, RULE_bracketTerm = 3, 
		RULE_genericTerm = 4, RULE_parenTerm = 5, RULE_termPart = 6, RULE_atom = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"resultTerm", "emptyList", "castEmptyList", "bracketTerm", "genericTerm", 
			"parenTerm", "termPart", "atom"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'List'", "'Scen'", "'['", "']'", "'('", "')'", "'{'", "'}'", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "LIST", "SCEN", "LBRACK", "RBRACK", "LPAREN", "RPAREN", "LBRACE", 
			"RBRACE", "DOT", "RAW"
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
	public String getGrammarFileName() { return "GeneratedScenarioList.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GeneratedScenarioListParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ResultTermContext extends ParserRuleContext {
		public EmptyListContext emptyList() {
			return getRuleContext(EmptyListContext.class,0);
		}
		public TerminalNode EOF() { return getToken(GeneratedScenarioListParser.EOF, 0); }
		public CastEmptyListContext castEmptyList() {
			return getRuleContext(CastEmptyListContext.class,0);
		}
		public BracketTermContext bracketTerm() {
			return getRuleContext(BracketTermContext.class,0);
		}
		public GenericTermContext genericTerm() {
			return getRuleContext(GenericTermContext.class,0);
		}
		public ResultTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resultTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GeneratedScenarioListListener ) ((GeneratedScenarioListListener)listener).enterResultTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GeneratedScenarioListListener ) ((GeneratedScenarioListListener)listener).exitResultTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GeneratedScenarioListVisitor ) return ((GeneratedScenarioListVisitor<? extends T>)visitor).visitResultTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ResultTermContext resultTerm() throws RecognitionException {
		ResultTermContext _localctx = new ResultTermContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_resultTerm);
		try {
			setState(28);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(16);
				emptyList();
				setState(17);
				match(EOF);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(19);
				castEmptyList();
				setState(20);
				match(EOF);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(22);
				bracketTerm();
				setState(23);
				match(EOF);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(25);
				genericTerm();
				setState(26);
				match(EOF);
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
	public static class EmptyListContext extends ParserRuleContext {
		public TerminalNode LBRACK() { return getToken(GeneratedScenarioListParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(GeneratedScenarioListParser.RBRACK, 0); }
		public EmptyListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_emptyList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GeneratedScenarioListListener ) ((GeneratedScenarioListListener)listener).enterEmptyList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GeneratedScenarioListListener ) ((GeneratedScenarioListListener)listener).exitEmptyList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GeneratedScenarioListVisitor ) return ((GeneratedScenarioListVisitor<? extends T>)visitor).visitEmptyList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EmptyListContext emptyList() throws RecognitionException {
		EmptyListContext _localctx = new EmptyListContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_emptyList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(LBRACK);
			setState(31);
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
	public static class CastEmptyListContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(GeneratedScenarioListParser.LPAREN, 0); }
		public TerminalNode LBRACK() { return getToken(GeneratedScenarioListParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(GeneratedScenarioListParser.RBRACK, 0); }
		public TerminalNode RPAREN() { return getToken(GeneratedScenarioListParser.RPAREN, 0); }
		public TerminalNode DOT() { return getToken(GeneratedScenarioListParser.DOT, 0); }
		public TerminalNode LIST() { return getToken(GeneratedScenarioListParser.LIST, 0); }
		public TerminalNode LBRACE() { return getToken(GeneratedScenarioListParser.LBRACE, 0); }
		public TerminalNode SCEN() { return getToken(GeneratedScenarioListParser.SCEN, 0); }
		public TerminalNode RBRACE() { return getToken(GeneratedScenarioListParser.RBRACE, 0); }
		public CastEmptyListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_castEmptyList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GeneratedScenarioListListener ) ((GeneratedScenarioListListener)listener).enterCastEmptyList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GeneratedScenarioListListener ) ((GeneratedScenarioListListener)listener).exitCastEmptyList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GeneratedScenarioListVisitor ) return ((GeneratedScenarioListVisitor<? extends T>)visitor).visitCastEmptyList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CastEmptyListContext castEmptyList() throws RecognitionException {
		CastEmptyListContext _localctx = new CastEmptyListContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_castEmptyList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			match(LPAREN);
			setState(34);
			match(LBRACK);
			setState(35);
			match(RBRACK);
			setState(36);
			match(RPAREN);
			setState(37);
			match(DOT);
			setState(38);
			match(LIST);
			setState(39);
			match(LBRACE);
			setState(40);
			match(SCEN);
			setState(41);
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
	public static class BracketTermContext extends ParserRuleContext {
		public TerminalNode LBRACK() { return getToken(GeneratedScenarioListParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(GeneratedScenarioListParser.RBRACK, 0); }
		public List<TermPartContext> termPart() {
			return getRuleContexts(TermPartContext.class);
		}
		public TermPartContext termPart(int i) {
			return getRuleContext(TermPartContext.class,i);
		}
		public BracketTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bracketTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GeneratedScenarioListListener ) ((GeneratedScenarioListListener)listener).enterBracketTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GeneratedScenarioListListener ) ((GeneratedScenarioListListener)listener).exitBracketTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GeneratedScenarioListVisitor ) return ((GeneratedScenarioListVisitor<? extends T>)visitor).visitBracketTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BracketTermContext bracketTerm() throws RecognitionException {
		BracketTermContext _localctx = new BracketTermContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_bracketTerm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			match(LBRACK);
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1966L) != 0)) {
				{
				{
				setState(44);
				termPart();
				}
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(50);
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
	public static class GenericTermContext extends ParserRuleContext {
		public List<TermPartContext> termPart() {
			return getRuleContexts(TermPartContext.class);
		}
		public TermPartContext termPart(int i) {
			return getRuleContext(TermPartContext.class,i);
		}
		public GenericTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GeneratedScenarioListListener ) ((GeneratedScenarioListListener)listener).enterGenericTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GeneratedScenarioListListener ) ((GeneratedScenarioListListener)listener).exitGenericTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GeneratedScenarioListVisitor ) return ((GeneratedScenarioListVisitor<? extends T>)visitor).visitGenericTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GenericTermContext genericTerm() throws RecognitionException {
		GenericTermContext _localctx = new GenericTermContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_genericTerm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(52);
				termPart();
				}
				}
				setState(55); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 1966L) != 0) );
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
	public static class ParenTermContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(GeneratedScenarioListParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(GeneratedScenarioListParser.RPAREN, 0); }
		public List<TermPartContext> termPart() {
			return getRuleContexts(TermPartContext.class);
		}
		public TermPartContext termPart(int i) {
			return getRuleContext(TermPartContext.class,i);
		}
		public ParenTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parenTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GeneratedScenarioListListener ) ((GeneratedScenarioListListener)listener).enterParenTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GeneratedScenarioListListener ) ((GeneratedScenarioListListener)listener).exitParenTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GeneratedScenarioListVisitor ) return ((GeneratedScenarioListVisitor<? extends T>)visitor).visitParenTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParenTermContext parenTerm() throws RecognitionException {
		ParenTermContext _localctx = new ParenTermContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_parenTerm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(LPAREN);
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1966L) != 0)) {
				{
				{
				setState(58);
				termPart();
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(64);
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
	public static class TermPartContext extends ParserRuleContext {
		public BracketTermContext bracketTerm() {
			return getRuleContext(BracketTermContext.class,0);
		}
		public ParenTermContext parenTerm() {
			return getRuleContext(ParenTermContext.class,0);
		}
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public TermPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termPart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GeneratedScenarioListListener ) ((GeneratedScenarioListListener)listener).enterTermPart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GeneratedScenarioListListener ) ((GeneratedScenarioListListener)listener).exitTermPart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GeneratedScenarioListVisitor ) return ((GeneratedScenarioListVisitor<? extends T>)visitor).visitTermPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermPartContext termPart() throws RecognitionException {
		TermPartContext _localctx = new TermPartContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_termPart);
		try {
			setState(69);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACK:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				bracketTerm();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				parenTerm();
				}
				break;
			case LIST:
			case SCEN:
			case LBRACE:
			case RBRACE:
			case DOT:
			case RAW:
				enterOuterAlt(_localctx, 3);
				{
				setState(68);
				atom();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	public static class AtomContext extends ParserRuleContext {
		public TerminalNode LIST() { return getToken(GeneratedScenarioListParser.LIST, 0); }
		public TerminalNode SCEN() { return getToken(GeneratedScenarioListParser.SCEN, 0); }
		public TerminalNode DOT() { return getToken(GeneratedScenarioListParser.DOT, 0); }
		public TerminalNode LBRACE() { return getToken(GeneratedScenarioListParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(GeneratedScenarioListParser.RBRACE, 0); }
		public TerminalNode RAW() { return getToken(GeneratedScenarioListParser.RAW, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GeneratedScenarioListListener ) ((GeneratedScenarioListListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GeneratedScenarioListListener ) ((GeneratedScenarioListListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GeneratedScenarioListVisitor ) return ((GeneratedScenarioListVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_atom);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1926L) != 0)) ) {
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

	public static final String _serializedATN =
		"\u0004\u0001\nJ\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003"+
		"\u0000\u001d\b\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0005\u0003.\b"+
		"\u0003\n\u0003\f\u00031\t\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0004"+
		"\u00046\b\u0004\u000b\u0004\f\u00047\u0001\u0005\u0001\u0005\u0005\u0005"+
		"<\b\u0005\n\u0005\f\u0005?\t\u0005\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0003\u0006F\b\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0000\u0000\b\u0000\u0002\u0004\u0006\b\n\f\u000e\u0000\u0001"+
		"\u0002\u0000\u0001\u0002\u0007\nI\u0000\u001c\u0001\u0000\u0000\u0000"+
		"\u0002\u001e\u0001\u0000\u0000\u0000\u0004!\u0001\u0000\u0000\u0000\u0006"+
		"+\u0001\u0000\u0000\u0000\b5\u0001\u0000\u0000\u0000\n9\u0001\u0000\u0000"+
		"\u0000\fE\u0001\u0000\u0000\u0000\u000eG\u0001\u0000\u0000\u0000\u0010"+
		"\u0011\u0003\u0002\u0001\u0000\u0011\u0012\u0005\u0000\u0000\u0001\u0012"+
		"\u001d\u0001\u0000\u0000\u0000\u0013\u0014\u0003\u0004\u0002\u0000\u0014"+
		"\u0015\u0005\u0000\u0000\u0001\u0015\u001d\u0001\u0000\u0000\u0000\u0016"+
		"\u0017\u0003\u0006\u0003\u0000\u0017\u0018\u0005\u0000\u0000\u0001\u0018"+
		"\u001d\u0001\u0000\u0000\u0000\u0019\u001a\u0003\b\u0004\u0000\u001a\u001b"+
		"\u0005\u0000\u0000\u0001\u001b\u001d\u0001\u0000\u0000\u0000\u001c\u0010"+
		"\u0001\u0000\u0000\u0000\u001c\u0013\u0001\u0000\u0000\u0000\u001c\u0016"+
		"\u0001\u0000\u0000\u0000\u001c\u0019\u0001\u0000\u0000\u0000\u001d\u0001"+
		"\u0001\u0000\u0000\u0000\u001e\u001f\u0005\u0003\u0000\u0000\u001f \u0005"+
		"\u0004\u0000\u0000 \u0003\u0001\u0000\u0000\u0000!\"\u0005\u0005\u0000"+
		"\u0000\"#\u0005\u0003\u0000\u0000#$\u0005\u0004\u0000\u0000$%\u0005\u0006"+
		"\u0000\u0000%&\u0005\t\u0000\u0000&\'\u0005\u0001\u0000\u0000\'(\u0005"+
		"\u0007\u0000\u0000()\u0005\u0002\u0000\u0000)*\u0005\b\u0000\u0000*\u0005"+
		"\u0001\u0000\u0000\u0000+/\u0005\u0003\u0000\u0000,.\u0003\f\u0006\u0000"+
		"-,\u0001\u0000\u0000\u0000.1\u0001\u0000\u0000\u0000/-\u0001\u0000\u0000"+
		"\u0000/0\u0001\u0000\u0000\u000002\u0001\u0000\u0000\u00001/\u0001\u0000"+
		"\u0000\u000023\u0005\u0004\u0000\u00003\u0007\u0001\u0000\u0000\u0000"+
		"46\u0003\f\u0006\u000054\u0001\u0000\u0000\u000067\u0001\u0000\u0000\u0000"+
		"75\u0001\u0000\u0000\u000078\u0001\u0000\u0000\u00008\t\u0001\u0000\u0000"+
		"\u00009=\u0005\u0005\u0000\u0000:<\u0003\f\u0006\u0000;:\u0001\u0000\u0000"+
		"\u0000<?\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000=>\u0001\u0000"+
		"\u0000\u0000>@\u0001\u0000\u0000\u0000?=\u0001\u0000\u0000\u0000@A\u0005"+
		"\u0006\u0000\u0000A\u000b\u0001\u0000\u0000\u0000BF\u0003\u0006\u0003"+
		"\u0000CF\u0003\n\u0005\u0000DF\u0003\u000e\u0007\u0000EB\u0001\u0000\u0000"+
		"\u0000EC\u0001\u0000\u0000\u0000ED\u0001\u0000\u0000\u0000F\r\u0001\u0000"+
		"\u0000\u0000GH\u0007\u0000\u0000\u0000H\u000f\u0001\u0000\u0000\u0000"+
		"\u0005\u001c/7=E";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}