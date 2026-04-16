// Generated from /Users/gsojc234/git/maude-tls-attacker/src/mta/user/scenario/antlr/ScenarioSpec.g4 by ANTLR 4.13.2
package mta.user.scenario.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class ScenarioSpecParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LOAD=1, USE=2, AS=3, NODES=4, CONSTANTS=5, STATE_PROPOSITIONS=6, ACTION_PROPOSITIONS=7, 
		SCENARIO_PROPERTY=8, SCENARIO_PROPERTIES=9, VALUE=10, AND=11, OR=12, XOR=13, 
		NOT=14, STEP_ZERO_OR_MORE=15, STEP_ONE_OR_MORE=16, STEP_ONE=17, ZERO_OR_MORE=18, 
		ONE_OR_MORE=19, BIND=20, PIPE=21, EQ2=22, EQ=23, COLON=24, COMMA=25, LPAREN=26, 
		RPAREN=27, LBRACK=28, RBRACK=29, LBRACE=30, RBRACE=31, CONSTANT_REF=32, 
		IDENTIFIER=33, STRING=34, WS=35, LINE_COMMENT=36;
	public static final int
		RULE_scenarioSpec = 0, RULE_scenarioItem = 1, RULE_loadStatement = 2, 
		RULE_useStatement = 3, RULE_nodesSection = 4, RULE_constantsSection = 5, 
		RULE_constantDeclaration = 6, RULE_constantSet = 7, RULE_statePropositionsSection = 8, 
		RULE_statePropositionDeclaration = 9, RULE_statePropositionExpr = 10, 
		RULE_statePropositionTerm = 11, RULE_actionPropositionsSection = 12, RULE_actionPropositionDeclaration = 13, 
		RULE_actionInvocation = 14, RULE_actionArgument = 15, RULE_scenarioPropertiesSection = 16, 
		RULE_scenarioPropertyDeclaration = 17, RULE_nodeBinding = 18, RULE_propertyRelation = 19, 
		RULE_propertyReferenceValue = 20, RULE_conditionExpr = 21, RULE_conditionPredicate = 22, 
		RULE_valueAccessor = 23, RULE_conditionOperand = 24, RULE_stringLiteral = 25, 
		RULE_constantRef = 26, RULE_identifierValue = 27;
	private static String[] makeRuleNames() {
		return new String[] {
			"scenarioSpec", "scenarioItem", "loadStatement", "useStatement", "nodesSection", 
			"constantsSection", "constantDeclaration", "constantSet", "statePropositionsSection", 
			"statePropositionDeclaration", "statePropositionExpr", "statePropositionTerm", 
			"actionPropositionsSection", "actionPropositionDeclaration", "actionInvocation", 
			"actionArgument", "scenarioPropertiesSection", "scenarioPropertyDeclaration", 
			"nodeBinding", "propertyRelation", "propertyReferenceValue", "conditionExpr", 
			"conditionPredicate", "valueAccessor", "conditionOperand", "stringLiteral", 
			"constantRef", "identifierValue"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'load'", "'use'", "'as'", "'Nodes'", "'Constants'", "'StatePropositions'", 
			"'ActionPropositions'", "'ScenarioProperty'", "'ScenarioProperties'", 
			"'value'", "'and'", "'or'", "'xor'", "'not'", "'->*'", "'->+'", "'->'", 
			"'*'", "'+'", "'<-'", "'|'", "'=='", "'='", "':'", "','", "'('", "')'", 
			"'['", "']'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "LOAD", "USE", "AS", "NODES", "CONSTANTS", "STATE_PROPOSITIONS", 
			"ACTION_PROPOSITIONS", "SCENARIO_PROPERTY", "SCENARIO_PROPERTIES", "VALUE", 
			"AND", "OR", "XOR", "NOT", "STEP_ZERO_OR_MORE", "STEP_ONE_OR_MORE", "STEP_ONE", 
			"ZERO_OR_MORE", "ONE_OR_MORE", "BIND", "PIPE", "EQ2", "EQ", "COLON", 
			"COMMA", "LPAREN", "RPAREN", "LBRACK", "RBRACK", "LBRACE", "RBRACE", 
			"CONSTANT_REF", "IDENTIFIER", "STRING", "WS", "LINE_COMMENT"
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
	public String getGrammarFileName() { return "ScenarioSpec.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ScenarioSpecParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ScenarioSpecContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(ScenarioSpecParser.EOF, 0); }
		public List<ScenarioItemContext> scenarioItem() {
			return getRuleContexts(ScenarioItemContext.class);
		}
		public ScenarioItemContext scenarioItem(int i) {
			return getRuleContext(ScenarioItemContext.class,i);
		}
		public List<ScenarioPropertiesSectionContext> scenarioPropertiesSection() {
			return getRuleContexts(ScenarioPropertiesSectionContext.class);
		}
		public ScenarioPropertiesSectionContext scenarioPropertiesSection(int i) {
			return getRuleContext(ScenarioPropertiesSectionContext.class,i);
		}
		public ScenarioSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scenarioSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterScenarioSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitScenarioSpec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitScenarioSpec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScenarioSpecContext scenarioSpec() throws RecognitionException {
		ScenarioSpecContext _localctx = new ScenarioSpecContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_scenarioSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 246L) != 0)) {
				{
				{
				setState(56);
				scenarioItem();
				}
				}
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(63); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(62);
				scenarioPropertiesSection();
				}
				}
				setState(65); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SCENARIO_PROPERTY || _la==SCENARIO_PROPERTIES );
			setState(67);
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
	public static class ScenarioItemContext extends ParserRuleContext {
		public LoadStatementContext loadStatement() {
			return getRuleContext(LoadStatementContext.class,0);
		}
		public UseStatementContext useStatement() {
			return getRuleContext(UseStatementContext.class,0);
		}
		public NodesSectionContext nodesSection() {
			return getRuleContext(NodesSectionContext.class,0);
		}
		public ConstantsSectionContext constantsSection() {
			return getRuleContext(ConstantsSectionContext.class,0);
		}
		public StatePropositionsSectionContext statePropositionsSection() {
			return getRuleContext(StatePropositionsSectionContext.class,0);
		}
		public ActionPropositionsSectionContext actionPropositionsSection() {
			return getRuleContext(ActionPropositionsSectionContext.class,0);
		}
		public ScenarioItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scenarioItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterScenarioItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitScenarioItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitScenarioItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScenarioItemContext scenarioItem() throws RecognitionException {
		ScenarioItemContext _localctx = new ScenarioItemContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_scenarioItem);
		try {
			setState(75);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LOAD:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				loadStatement();
				}
				break;
			case USE:
				enterOuterAlt(_localctx, 2);
				{
				setState(70);
				useStatement();
				}
				break;
			case NODES:
				enterOuterAlt(_localctx, 3);
				{
				setState(71);
				nodesSection();
				}
				break;
			case CONSTANTS:
				enterOuterAlt(_localctx, 4);
				{
				setState(72);
				constantsSection();
				}
				break;
			case STATE_PROPOSITIONS:
				enterOuterAlt(_localctx, 5);
				{
				setState(73);
				statePropositionsSection();
				}
				break;
			case ACTION_PROPOSITIONS:
				enterOuterAlt(_localctx, 6);
				{
				setState(74);
				actionPropositionsSection();
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
	public static class LoadStatementContext extends ParserRuleContext {
		public TerminalNode LOAD() { return getToken(ScenarioSpecParser.LOAD, 0); }
		public StringLiteralContext stringLiteral() {
			return getRuleContext(StringLiteralContext.class,0);
		}
		public TerminalNode AS() { return getToken(ScenarioSpecParser.AS, 0); }
		public IdentifierValueContext identifierValue() {
			return getRuleContext(IdentifierValueContext.class,0);
		}
		public LoadStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loadStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterLoadStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitLoadStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitLoadStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoadStatementContext loadStatement() throws RecognitionException {
		LoadStatementContext _localctx = new LoadStatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_loadStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(LOAD);
			setState(78);
			stringLiteral();
			setState(79);
			match(AS);
			setState(80);
			identifierValue();
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
	public static class UseStatementContext extends ParserRuleContext {
		public TerminalNode USE() { return getToken(ScenarioSpecParser.USE, 0); }
		public StringLiteralContext stringLiteral() {
			return getRuleContext(StringLiteralContext.class,0);
		}
		public UseStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_useStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterUseStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitUseStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitUseStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UseStatementContext useStatement() throws RecognitionException {
		UseStatementContext _localctx = new UseStatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_useStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(USE);
			setState(83);
			stringLiteral();
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
	public static class NodesSectionContext extends ParserRuleContext {
		public TerminalNode NODES() { return getToken(ScenarioSpecParser.NODES, 0); }
		public TerminalNode COLON() { return getToken(ScenarioSpecParser.COLON, 0); }
		public List<IdentifierValueContext> identifierValue() {
			return getRuleContexts(IdentifierValueContext.class);
		}
		public IdentifierValueContext identifierValue(int i) {
			return getRuleContext(IdentifierValueContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ScenarioSpecParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ScenarioSpecParser.COMMA, i);
		}
		public NodesSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodesSection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterNodesSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitNodesSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitNodesSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodesSectionContext nodesSection() throws RecognitionException {
		NodesSectionContext _localctx = new NodesSectionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_nodesSection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(NODES);
			setState(86);
			match(COLON);
			setState(87);
			identifierValue();
			setState(92);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(88);
				match(COMMA);
				setState(89);
				identifierValue();
				}
				}
				setState(94);
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
	public static class ConstantsSectionContext extends ParserRuleContext {
		public TerminalNode CONSTANTS() { return getToken(ScenarioSpecParser.CONSTANTS, 0); }
		public TerminalNode COLON() { return getToken(ScenarioSpecParser.COLON, 0); }
		public List<ConstantDeclarationContext> constantDeclaration() {
			return getRuleContexts(ConstantDeclarationContext.class);
		}
		public ConstantDeclarationContext constantDeclaration(int i) {
			return getRuleContext(ConstantDeclarationContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ScenarioSpecParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ScenarioSpecParser.COMMA, i);
		}
		public ConstantsSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantsSection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterConstantsSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitConstantsSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitConstantsSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantsSectionContext constantsSection() throws RecognitionException {
		ConstantsSectionContext _localctx = new ConstantsSectionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_constantsSection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(CONSTANTS);
			setState(96);
			match(COLON);
			setState(97);
			constantDeclaration();
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA || _la==CONSTANT_REF) {
				{
				{
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(98);
					match(COMMA);
					}
				}

				setState(101);
				constantDeclaration();
				}
				}
				setState(106);
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
	public static class ConstantDeclarationContext extends ParserRuleContext {
		public ConstantRefContext constantRef() {
			return getRuleContext(ConstantRefContext.class,0);
		}
		public TerminalNode EQ() { return getToken(ScenarioSpecParser.EQ, 0); }
		public ConstantSetContext constantSet() {
			return getRuleContext(ConstantSetContext.class,0);
		}
		public ConstantDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterConstantDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitConstantDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitConstantDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantDeclarationContext constantDeclaration() throws RecognitionException {
		ConstantDeclarationContext _localctx = new ConstantDeclarationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_constantDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			constantRef();
			setState(108);
			match(EQ);
			setState(109);
			constantSet();
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
	public static class ConstantSetContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(ScenarioSpecParser.LBRACE, 0); }
		public List<IdentifierValueContext> identifierValue() {
			return getRuleContexts(IdentifierValueContext.class);
		}
		public IdentifierValueContext identifierValue(int i) {
			return getRuleContext(IdentifierValueContext.class,i);
		}
		public TerminalNode RBRACE() { return getToken(ScenarioSpecParser.RBRACE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(ScenarioSpecParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ScenarioSpecParser.COMMA, i);
		}
		public ConstantSetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantSet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterConstantSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitConstantSet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitConstantSet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantSetContext constantSet() throws RecognitionException {
		ConstantSetContext _localctx = new ConstantSetContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_constantSet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(LBRACE);
			setState(112);
			identifierValue();
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(113);
				match(COMMA);
				setState(114);
				identifierValue();
				}
				}
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(120);
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
	public static class StatePropositionsSectionContext extends ParserRuleContext {
		public TerminalNode STATE_PROPOSITIONS() { return getToken(ScenarioSpecParser.STATE_PROPOSITIONS, 0); }
		public TerminalNode COLON() { return getToken(ScenarioSpecParser.COLON, 0); }
		public List<StatePropositionDeclarationContext> statePropositionDeclaration() {
			return getRuleContexts(StatePropositionDeclarationContext.class);
		}
		public StatePropositionDeclarationContext statePropositionDeclaration(int i) {
			return getRuleContext(StatePropositionDeclarationContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ScenarioSpecParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ScenarioSpecParser.COMMA, i);
		}
		public StatePropositionsSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statePropositionsSection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterStatePropositionsSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitStatePropositionsSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitStatePropositionsSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatePropositionsSectionContext statePropositionsSection() throws RecognitionException {
		StatePropositionsSectionContext _localctx = new StatePropositionsSectionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_statePropositionsSection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(STATE_PROPOSITIONS);
			setState(123);
			match(COLON);
			setState(124);
			statePropositionDeclaration();
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA || _la==IDENTIFIER) {
				{
				{
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(125);
					match(COMMA);
					}
				}

				setState(128);
				statePropositionDeclaration();
				}
				}
				setState(133);
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
	public static class StatePropositionDeclarationContext extends ParserRuleContext {
		public IdentifierValueContext identifierValue() {
			return getRuleContext(IdentifierValueContext.class,0);
		}
		public TerminalNode EQ() { return getToken(ScenarioSpecParser.EQ, 0); }
		public StatePropositionExprContext statePropositionExpr() {
			return getRuleContext(StatePropositionExprContext.class,0);
		}
		public StatePropositionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statePropositionDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterStatePropositionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitStatePropositionDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitStatePropositionDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatePropositionDeclarationContext statePropositionDeclaration() throws RecognitionException {
		StatePropositionDeclarationContext _localctx = new StatePropositionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_statePropositionDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			identifierValue();
			setState(135);
			match(EQ);
			setState(136);
			statePropositionExpr(0);
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
	public static class StatePropositionExprContext extends ParserRuleContext {
		public StatePropositionTermContext statePropositionTerm() {
			return getRuleContext(StatePropositionTermContext.class,0);
		}
		public TerminalNode NOT() { return getToken(ScenarioSpecParser.NOT, 0); }
		public List<StatePropositionExprContext> statePropositionExpr() {
			return getRuleContexts(StatePropositionExprContext.class);
		}
		public StatePropositionExprContext statePropositionExpr(int i) {
			return getRuleContext(StatePropositionExprContext.class,i);
		}
		public TerminalNode LPAREN() { return getToken(ScenarioSpecParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ScenarioSpecParser.RPAREN, 0); }
		public TerminalNode OR() { return getToken(ScenarioSpecParser.OR, 0); }
		public TerminalNode XOR() { return getToken(ScenarioSpecParser.XOR, 0); }
		public TerminalNode AND() { return getToken(ScenarioSpecParser.AND, 0); }
		public StatePropositionExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statePropositionExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterStatePropositionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitStatePropositionExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitStatePropositionExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatePropositionExprContext statePropositionExpr() throws RecognitionException {
		return statePropositionExpr(0);
	}

	private StatePropositionExprContext statePropositionExpr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StatePropositionExprContext _localctx = new StatePropositionExprContext(_ctx, _parentState);
		StatePropositionExprContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_statePropositionExpr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				setState(139);
				statePropositionTerm();
				}
				break;
			case NOT:
				{
				setState(140);
				match(NOT);
				setState(141);
				statePropositionExpr(3);
				}
				break;
			case LPAREN:
				{
				setState(142);
				match(LPAREN);
				setState(143);
				statePropositionExpr(0);
				setState(144);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(153);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StatePropositionExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_statePropositionExpr);
					setState(148);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(149);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 14336L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(150);
					statePropositionExpr(2);
					}
					} 
				}
				setState(155);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatePropositionTermContext extends ParserRuleContext {
		public IdentifierValueContext identifierValue() {
			return getRuleContext(IdentifierValueContext.class,0);
		}
		public TerminalNode PIPE() { return getToken(ScenarioSpecParser.PIPE, 0); }
		public TerminalNode LPAREN() { return getToken(ScenarioSpecParser.LPAREN, 0); }
		public ConditionExprContext conditionExpr() {
			return getRuleContext(ConditionExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ScenarioSpecParser.RPAREN, 0); }
		public StatePropositionTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statePropositionTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterStatePropositionTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitStatePropositionTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitStatePropositionTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatePropositionTermContext statePropositionTerm() throws RecognitionException {
		StatePropositionTermContext _localctx = new StatePropositionTermContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_statePropositionTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			identifierValue();
			setState(157);
			match(PIPE);
			setState(158);
			match(LPAREN);
			setState(159);
			conditionExpr(0);
			setState(160);
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
	public static class ActionPropositionsSectionContext extends ParserRuleContext {
		public TerminalNode ACTION_PROPOSITIONS() { return getToken(ScenarioSpecParser.ACTION_PROPOSITIONS, 0); }
		public TerminalNode COLON() { return getToken(ScenarioSpecParser.COLON, 0); }
		public List<ActionPropositionDeclarationContext> actionPropositionDeclaration() {
			return getRuleContexts(ActionPropositionDeclarationContext.class);
		}
		public ActionPropositionDeclarationContext actionPropositionDeclaration(int i) {
			return getRuleContext(ActionPropositionDeclarationContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ScenarioSpecParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ScenarioSpecParser.COMMA, i);
		}
		public ActionPropositionsSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actionPropositionsSection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterActionPropositionsSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitActionPropositionsSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitActionPropositionsSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionPropositionsSectionContext actionPropositionsSection() throws RecognitionException {
		ActionPropositionsSectionContext _localctx = new ActionPropositionsSectionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_actionPropositionsSection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			match(ACTION_PROPOSITIONS);
			setState(163);
			match(COLON);
			setState(164);
			actionPropositionDeclaration();
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA || _la==IDENTIFIER) {
				{
				{
				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(165);
					match(COMMA);
					}
				}

				setState(168);
				actionPropositionDeclaration();
				}
				}
				setState(173);
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
	public static class ActionPropositionDeclarationContext extends ParserRuleContext {
		public IdentifierValueContext identifierValue() {
			return getRuleContext(IdentifierValueContext.class,0);
		}
		public TerminalNode EQ() { return getToken(ScenarioSpecParser.EQ, 0); }
		public ActionInvocationContext actionInvocation() {
			return getRuleContext(ActionInvocationContext.class,0);
		}
		public ActionPropositionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actionPropositionDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterActionPropositionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitActionPropositionDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitActionPropositionDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionPropositionDeclarationContext actionPropositionDeclaration() throws RecognitionException {
		ActionPropositionDeclarationContext _localctx = new ActionPropositionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_actionPropositionDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			identifierValue();
			setState(175);
			match(EQ);
			setState(176);
			actionInvocation();
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
	public static class ActionInvocationContext extends ParserRuleContext {
		public IdentifierValueContext identifierValue() {
			return getRuleContext(IdentifierValueContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(ScenarioSpecParser.LPAREN, 0); }
		public List<ActionArgumentContext> actionArgument() {
			return getRuleContexts(ActionArgumentContext.class);
		}
		public ActionArgumentContext actionArgument(int i) {
			return getRuleContext(ActionArgumentContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(ScenarioSpecParser.RPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(ScenarioSpecParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ScenarioSpecParser.COMMA, i);
		}
		public ActionInvocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actionInvocation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterActionInvocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitActionInvocation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitActionInvocation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionInvocationContext actionInvocation() throws RecognitionException {
		ActionInvocationContext _localctx = new ActionInvocationContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_actionInvocation);
		int _la;
		try {
			setState(191);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(178);
				identifierValue();
				setState(179);
				match(LPAREN);
				setState(180);
				actionArgument();
				setState(185);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(181);
					match(COMMA);
					setState(182);
					actionArgument();
					}
					}
					setState(187);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(188);
				match(RPAREN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(190);
				identifierValue();
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
	public static class ActionArgumentContext extends ParserRuleContext {
		public ConstantRefContext constantRef() {
			return getRuleContext(ConstantRefContext.class,0);
		}
		public IdentifierValueContext identifierValue() {
			return getRuleContext(IdentifierValueContext.class,0);
		}
		public ActionArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actionArgument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterActionArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitActionArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitActionArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionArgumentContext actionArgument() throws RecognitionException {
		ActionArgumentContext _localctx = new ActionArgumentContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_actionArgument);
		try {
			setState(195);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONSTANT_REF:
				enterOuterAlt(_localctx, 1);
				{
				setState(193);
				constantRef();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(194);
				identifierValue();
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
	public static class ScenarioPropertiesSectionContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(ScenarioSpecParser.COLON, 0); }
		public List<ScenarioPropertyDeclarationContext> scenarioPropertyDeclaration() {
			return getRuleContexts(ScenarioPropertyDeclarationContext.class);
		}
		public ScenarioPropertyDeclarationContext scenarioPropertyDeclaration(int i) {
			return getRuleContext(ScenarioPropertyDeclarationContext.class,i);
		}
		public TerminalNode SCENARIO_PROPERTY() { return getToken(ScenarioSpecParser.SCENARIO_PROPERTY, 0); }
		public TerminalNode SCENARIO_PROPERTIES() { return getToken(ScenarioSpecParser.SCENARIO_PROPERTIES, 0); }
		public List<TerminalNode> COMMA() { return getTokens(ScenarioSpecParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ScenarioSpecParser.COMMA, i);
		}
		public ScenarioPropertiesSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scenarioPropertiesSection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterScenarioPropertiesSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitScenarioPropertiesSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitScenarioPropertiesSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScenarioPropertiesSectionContext scenarioPropertiesSection() throws RecognitionException {
		ScenarioPropertiesSectionContext _localctx = new ScenarioPropertiesSectionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_scenarioPropertiesSection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			_la = _input.LA(1);
			if ( !(_la==SCENARIO_PROPERTY || _la==SCENARIO_PROPERTIES) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(198);
			match(COLON);
			setState(199);
			scenarioPropertyDeclaration();
			setState(206);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA || _la==IDENTIFIER) {
				{
				{
				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(200);
					match(COMMA);
					}
				}

				setState(203);
				scenarioPropertyDeclaration();
				}
				}
				setState(208);
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
	public static class ScenarioPropertyDeclarationContext extends ParserRuleContext {
		public IdentifierValueContext identifierValue() {
			return getRuleContext(IdentifierValueContext.class,0);
		}
		public TerminalNode LBRACK() { return getToken(ScenarioSpecParser.LBRACK, 0); }
		public List<NodeBindingContext> nodeBinding() {
			return getRuleContexts(NodeBindingContext.class);
		}
		public NodeBindingContext nodeBinding(int i) {
			return getRuleContext(NodeBindingContext.class,i);
		}
		public TerminalNode RBRACK() { return getToken(ScenarioSpecParser.RBRACK, 0); }
		public TerminalNode COLON() { return getToken(ScenarioSpecParser.COLON, 0); }
		public PropertyRelationContext propertyRelation() {
			return getRuleContext(PropertyRelationContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(ScenarioSpecParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ScenarioSpecParser.COMMA, i);
		}
		public ScenarioPropertyDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scenarioPropertyDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterScenarioPropertyDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitScenarioPropertyDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitScenarioPropertyDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScenarioPropertyDeclarationContext scenarioPropertyDeclaration() throws RecognitionException {
		ScenarioPropertyDeclarationContext _localctx = new ScenarioPropertyDeclarationContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_scenarioPropertyDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			identifierValue();
			setState(210);
			match(LBRACK);
			setState(211);
			nodeBinding();
			setState(216);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(212);
				match(COMMA);
				setState(213);
				nodeBinding();
				}
				}
				setState(218);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(219);
			match(RBRACK);
			setState(220);
			match(COLON);
			setState(221);
			propertyRelation(0);
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
	public static class NodeBindingContext extends ParserRuleContext {
		public List<IdentifierValueContext> identifierValue() {
			return getRuleContexts(IdentifierValueContext.class);
		}
		public IdentifierValueContext identifierValue(int i) {
			return getRuleContext(IdentifierValueContext.class,i);
		}
		public TerminalNode BIND() { return getToken(ScenarioSpecParser.BIND, 0); }
		public NodeBindingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodeBinding; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterNodeBinding(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitNodeBinding(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitNodeBinding(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodeBindingContext nodeBinding() throws RecognitionException {
		NodeBindingContext _localctx = new NodeBindingContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_nodeBinding);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			identifierValue();
			setState(224);
			match(BIND);
			setState(225);
			identifierValue();
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
	public static class PropertyRelationContext extends ParserRuleContext {
		public IdentifierValueContext identifierValue() {
			return getRuleContext(IdentifierValueContext.class,0);
		}
		public TerminalNode NOT() { return getToken(ScenarioSpecParser.NOT, 0); }
		public List<PropertyRelationContext> propertyRelation() {
			return getRuleContexts(PropertyRelationContext.class);
		}
		public PropertyRelationContext propertyRelation(int i) {
			return getRuleContext(PropertyRelationContext.class,i);
		}
		public TerminalNode LPAREN() { return getToken(ScenarioSpecParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ScenarioSpecParser.RPAREN, 0); }
		public TerminalNode STEP_ZERO_OR_MORE() { return getToken(ScenarioSpecParser.STEP_ZERO_OR_MORE, 0); }
		public TerminalNode STEP_ONE_OR_MORE() { return getToken(ScenarioSpecParser.STEP_ONE_OR_MORE, 0); }
		public TerminalNode STEP_ONE() { return getToken(ScenarioSpecParser.STEP_ONE, 0); }
		public TerminalNode PIPE() { return getToken(ScenarioSpecParser.PIPE, 0); }
		public TerminalNode OR() { return getToken(ScenarioSpecParser.OR, 0); }
		public TerminalNode ZERO_OR_MORE() { return getToken(ScenarioSpecParser.ZERO_OR_MORE, 0); }
		public TerminalNode ONE_OR_MORE() { return getToken(ScenarioSpecParser.ONE_OR_MORE, 0); }
		public PropertyRelationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyRelation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterPropertyRelation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitPropertyRelation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitPropertyRelation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyRelationContext propertyRelation() throws RecognitionException {
		return propertyRelation(0);
	}

	private PropertyRelationContext propertyRelation(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PropertyRelationContext _localctx = new PropertyRelationContext(_ctx, _parentState);
		PropertyRelationContext _prevctx = _localctx;
		int _startState = 38;
		enterRecursionRule(_localctx, 38, RULE_propertyRelation, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				setState(228);
				identifierValue();
				}
				break;
			case NOT:
				{
				setState(229);
				match(NOT);
				setState(230);
				propertyRelation(4);
				}
				break;
			case LPAREN:
				{
				setState(231);
				match(LPAREN);
				setState(232);
				propertyRelation(0);
				setState(233);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(244);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(242);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
					case 1:
						{
						_localctx = new PropertyRelationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_propertyRelation);
						setState(237);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(238);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2330624L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(239);
						propertyRelation(2);
						}
						break;
					case 2:
						{
						_localctx = new PropertyRelationContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_propertyRelation);
						setState(240);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(241);
						_la = _input.LA(1);
						if ( !(_la==ZERO_OR_MORE || _la==ONE_OR_MORE) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(246);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PropertyReferenceValueContext extends ParserRuleContext {
		public ConstantRefContext constantRef() {
			return getRuleContext(ConstantRefContext.class,0);
		}
		public IdentifierValueContext identifierValue() {
			return getRuleContext(IdentifierValueContext.class,0);
		}
		public StringLiteralContext stringLiteral() {
			return getRuleContext(StringLiteralContext.class,0);
		}
		public PropertyReferenceValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyReferenceValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterPropertyReferenceValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitPropertyReferenceValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitPropertyReferenceValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyReferenceValueContext propertyReferenceValue() throws RecognitionException {
		PropertyReferenceValueContext _localctx = new PropertyReferenceValueContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_propertyReferenceValue);
		try {
			setState(250);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONSTANT_REF:
				enterOuterAlt(_localctx, 1);
				{
				setState(247);
				constantRef();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(248);
				identifierValue();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(249);
				stringLiteral();
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
	public static class ConditionExprContext extends ParserRuleContext {
		public ConditionPredicateContext conditionPredicate() {
			return getRuleContext(ConditionPredicateContext.class,0);
		}
		public TerminalNode NOT() { return getToken(ScenarioSpecParser.NOT, 0); }
		public List<ConditionExprContext> conditionExpr() {
			return getRuleContexts(ConditionExprContext.class);
		}
		public ConditionExprContext conditionExpr(int i) {
			return getRuleContext(ConditionExprContext.class,i);
		}
		public TerminalNode LPAREN() { return getToken(ScenarioSpecParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ScenarioSpecParser.RPAREN, 0); }
		public TerminalNode OR() { return getToken(ScenarioSpecParser.OR, 0); }
		public TerminalNode XOR() { return getToken(ScenarioSpecParser.XOR, 0); }
		public TerminalNode AND() { return getToken(ScenarioSpecParser.AND, 0); }
		public ConditionExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterConditionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitConditionExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitConditionExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionExprContext conditionExpr() throws RecognitionException {
		return conditionExpr(0);
	}

	private ConditionExprContext conditionExpr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConditionExprContext _localctx = new ConditionExprContext(_ctx, _parentState);
		ConditionExprContext _prevctx = _localctx;
		int _startState = 42;
		enterRecursionRule(_localctx, 42, RULE_conditionExpr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VALUE:
				{
				setState(253);
				conditionPredicate();
				}
				break;
			case NOT:
				{
				setState(254);
				match(NOT);
				setState(255);
				conditionExpr(3);
				}
				break;
			case LPAREN:
				{
				setState(256);
				match(LPAREN);
				setState(257);
				conditionExpr(0);
				setState(258);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(267);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ConditionExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_conditionExpr);
					setState(262);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(263);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 14336L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(264);
					conditionExpr(2);
					}
					} 
				}
				setState(269);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConditionPredicateContext extends ParserRuleContext {
		public ValueAccessorContext valueAccessor() {
			return getRuleContext(ValueAccessorContext.class,0);
		}
		public TerminalNode EQ2() { return getToken(ScenarioSpecParser.EQ2, 0); }
		public ConditionOperandContext conditionOperand() {
			return getRuleContext(ConditionOperandContext.class,0);
		}
		public ConditionPredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionPredicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterConditionPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitConditionPredicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitConditionPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionPredicateContext conditionPredicate() throws RecognitionException {
		ConditionPredicateContext _localctx = new ConditionPredicateContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_conditionPredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			valueAccessor();
			setState(271);
			match(EQ2);
			setState(272);
			conditionOperand();
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
	public static class ValueAccessorContext extends ParserRuleContext {
		public TerminalNode VALUE() { return getToken(ScenarioSpecParser.VALUE, 0); }
		public TerminalNode LPAREN() { return getToken(ScenarioSpecParser.LPAREN, 0); }
		public IdentifierValueContext identifierValue() {
			return getRuleContext(IdentifierValueContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ScenarioSpecParser.RPAREN, 0); }
		public ValueAccessorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueAccessor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterValueAccessor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitValueAccessor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitValueAccessor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueAccessorContext valueAccessor() throws RecognitionException {
		ValueAccessorContext _localctx = new ValueAccessorContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_valueAccessor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			match(VALUE);
			setState(275);
			match(LPAREN);
			setState(276);
			identifierValue();
			setState(277);
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
	public static class ConditionOperandContext extends ParserRuleContext {
		public ConstantRefContext constantRef() {
			return getRuleContext(ConstantRefContext.class,0);
		}
		public IdentifierValueContext identifierValue() {
			return getRuleContext(IdentifierValueContext.class,0);
		}
		public StringLiteralContext stringLiteral() {
			return getRuleContext(StringLiteralContext.class,0);
		}
		public ConditionOperandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionOperand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterConditionOperand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitConditionOperand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitConditionOperand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionOperandContext conditionOperand() throws RecognitionException {
		ConditionOperandContext _localctx = new ConditionOperandContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_conditionOperand);
		try {
			setState(282);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONSTANT_REF:
				enterOuterAlt(_localctx, 1);
				{
				setState(279);
				constantRef();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(280);
				identifierValue();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(281);
				stringLiteral();
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
	public static class StringLiteralContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(ScenarioSpecParser.STRING, 0); }
		public StringLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitStringLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitStringLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringLiteralContext stringLiteral() throws RecognitionException {
		StringLiteralContext _localctx = new StringLiteralContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_stringLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ConstantRefContext extends ParserRuleContext {
		public TerminalNode CONSTANT_REF() { return getToken(ScenarioSpecParser.CONSTANT_REF, 0); }
		public ConstantRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantRef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterConstantRef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitConstantRef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitConstantRef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantRefContext constantRef() throws RecognitionException {
		ConstantRefContext _localctx = new ConstantRefContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_constantRef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			match(CONSTANT_REF);
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
	public static class IdentifierValueContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(ScenarioSpecParser.IDENTIFIER, 0); }
		public IdentifierValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifierValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).enterIdentifierValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScenarioSpecListener ) ((ScenarioSpecListener)listener).exitIdentifierValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScenarioSpecVisitor ) return ((ScenarioSpecVisitor<? extends T>)visitor).visitIdentifierValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierValueContext identifierValue() throws RecognitionException {
		IdentifierValueContext _localctx = new IdentifierValueContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_identifierValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 10:
			return statePropositionExpr_sempred((StatePropositionExprContext)_localctx, predIndex);
		case 19:
			return propertyRelation_sempred((PropertyRelationContext)_localctx, predIndex);
		case 21:
			return conditionExpr_sempred((ConditionExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean statePropositionExpr_sempred(StatePropositionExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean propertyRelation_sempred(PropertyRelationContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 1);
		case 2:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean conditionExpr_sempred(ConditionExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001$\u0123\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0001\u0000\u0005\u0000:\b\u0000\n\u0000\f\u0000=\t\u0000\u0001\u0000"+
		"\u0004\u0000@\b\u0000\u000b\u0000\f\u0000A\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003"+
		"\u0001L\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0005\u0004[\b\u0004\n\u0004\f\u0004^\t"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005d\b"+
		"\u0005\u0001\u0005\u0005\u0005g\b\u0005\n\u0005\f\u0005j\t\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0005\u0007t\b\u0007\n\u0007\f\u0007w\t\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u007f\b\b\u0001"+
		"\b\u0005\b\u0082\b\b\n\b\f\b\u0085\t\b\u0001\t\u0001\t\u0001\t\u0001\t"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003"+
		"\n\u0093\b\n\u0001\n\u0001\n\u0001\n\u0005\n\u0098\b\n\n\n\f\n\u009b\t"+
		"\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00a7\b\f\u0001\f\u0005\f\u00aa"+
		"\b\f\n\f\f\f\u00ad\t\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u00b8\b\u000e\n"+
		"\u000e\f\u000e\u00bb\t\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003"+
		"\u000e\u00c0\b\u000e\u0001\u000f\u0001\u000f\u0003\u000f\u00c4\b\u000f"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00ca\b\u0010"+
		"\u0001\u0010\u0005\u0010\u00cd\b\u0010\n\u0010\f\u0010\u00d0\t\u0010\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u00d7"+
		"\b\u0011\n\u0011\f\u0011\u00da\t\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0003\u0013\u00ec\b\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0005\u0013\u00f3\b\u0013\n\u0013\f\u0013\u00f6"+
		"\t\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u00fb\b\u0014"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0003\u0015\u0105\b\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0005\u0015\u010a\b\u0015\n\u0015\f\u0015\u010d\t\u0015\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0003"+
		"\u0018\u011b\b\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0000\u0003\u0014&*\u001c\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \""+
		"$&(*,.0246\u0000\u0004\u0001\u0000\u000b\r\u0001\u0000\b\t\u0003\u0000"+
		"\f\f\u000f\u0011\u0015\u0015\u0001\u0000\u0012\u0013\u0129\u0000;\u0001"+
		"\u0000\u0000\u0000\u0002K\u0001\u0000\u0000\u0000\u0004M\u0001\u0000\u0000"+
		"\u0000\u0006R\u0001\u0000\u0000\u0000\bU\u0001\u0000\u0000\u0000\n_\u0001"+
		"\u0000\u0000\u0000\fk\u0001\u0000\u0000\u0000\u000eo\u0001\u0000\u0000"+
		"\u0000\u0010z\u0001\u0000\u0000\u0000\u0012\u0086\u0001\u0000\u0000\u0000"+
		"\u0014\u0092\u0001\u0000\u0000\u0000\u0016\u009c\u0001\u0000\u0000\u0000"+
		"\u0018\u00a2\u0001\u0000\u0000\u0000\u001a\u00ae\u0001\u0000\u0000\u0000"+
		"\u001c\u00bf\u0001\u0000\u0000\u0000\u001e\u00c3\u0001\u0000\u0000\u0000"+
		" \u00c5\u0001\u0000\u0000\u0000\"\u00d1\u0001\u0000\u0000\u0000$\u00df"+
		"\u0001\u0000\u0000\u0000&\u00eb\u0001\u0000\u0000\u0000(\u00fa\u0001\u0000"+
		"\u0000\u0000*\u0104\u0001\u0000\u0000\u0000,\u010e\u0001\u0000\u0000\u0000"+
		".\u0112\u0001\u0000\u0000\u00000\u011a\u0001\u0000\u0000\u00002\u011c"+
		"\u0001\u0000\u0000\u00004\u011e\u0001\u0000\u0000\u00006\u0120\u0001\u0000"+
		"\u0000\u00008:\u0003\u0002\u0001\u000098\u0001\u0000\u0000\u0000:=\u0001"+
		"\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000;<\u0001\u0000\u0000\u0000"+
		"<?\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000>@\u0003 \u0010\u0000"+
		"?>\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000A?\u0001\u0000\u0000"+
		"\u0000AB\u0001\u0000\u0000\u0000BC\u0001\u0000\u0000\u0000CD\u0005\u0000"+
		"\u0000\u0001D\u0001\u0001\u0000\u0000\u0000EL\u0003\u0004\u0002\u0000"+
		"FL\u0003\u0006\u0003\u0000GL\u0003\b\u0004\u0000HL\u0003\n\u0005\u0000"+
		"IL\u0003\u0010\b\u0000JL\u0003\u0018\f\u0000KE\u0001\u0000\u0000\u0000"+
		"KF\u0001\u0000\u0000\u0000KG\u0001\u0000\u0000\u0000KH\u0001\u0000\u0000"+
		"\u0000KI\u0001\u0000\u0000\u0000KJ\u0001\u0000\u0000\u0000L\u0003\u0001"+
		"\u0000\u0000\u0000MN\u0005\u0001\u0000\u0000NO\u00032\u0019\u0000OP\u0005"+
		"\u0003\u0000\u0000PQ\u00036\u001b\u0000Q\u0005\u0001\u0000\u0000\u0000"+
		"RS\u0005\u0002\u0000\u0000ST\u00032\u0019\u0000T\u0007\u0001\u0000\u0000"+
		"\u0000UV\u0005\u0004\u0000\u0000VW\u0005\u0018\u0000\u0000W\\\u00036\u001b"+
		"\u0000XY\u0005\u0019\u0000\u0000Y[\u00036\u001b\u0000ZX\u0001\u0000\u0000"+
		"\u0000[^\u0001\u0000\u0000\u0000\\Z\u0001\u0000\u0000\u0000\\]\u0001\u0000"+
		"\u0000\u0000]\t\u0001\u0000\u0000\u0000^\\\u0001\u0000\u0000\u0000_`\u0005"+
		"\u0005\u0000\u0000`a\u0005\u0018\u0000\u0000ah\u0003\f\u0006\u0000bd\u0005"+
		"\u0019\u0000\u0000cb\u0001\u0000\u0000\u0000cd\u0001\u0000\u0000\u0000"+
		"de\u0001\u0000\u0000\u0000eg\u0003\f\u0006\u0000fc\u0001\u0000\u0000\u0000"+
		"gj\u0001\u0000\u0000\u0000hf\u0001\u0000\u0000\u0000hi\u0001\u0000\u0000"+
		"\u0000i\u000b\u0001\u0000\u0000\u0000jh\u0001\u0000\u0000\u0000kl\u0003"+
		"4\u001a\u0000lm\u0005\u0017\u0000\u0000mn\u0003\u000e\u0007\u0000n\r\u0001"+
		"\u0000\u0000\u0000op\u0005\u001e\u0000\u0000pu\u00036\u001b\u0000qr\u0005"+
		"\u0019\u0000\u0000rt\u00036\u001b\u0000sq\u0001\u0000\u0000\u0000tw\u0001"+
		"\u0000\u0000\u0000us\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000"+
		"vx\u0001\u0000\u0000\u0000wu\u0001\u0000\u0000\u0000xy\u0005\u001f\u0000"+
		"\u0000y\u000f\u0001\u0000\u0000\u0000z{\u0005\u0006\u0000\u0000{|\u0005"+
		"\u0018\u0000\u0000|\u0083\u0003\u0012\t\u0000}\u007f\u0005\u0019\u0000"+
		"\u0000~}\u0001\u0000\u0000\u0000~\u007f\u0001\u0000\u0000\u0000\u007f"+
		"\u0080\u0001\u0000\u0000\u0000\u0080\u0082\u0003\u0012\t\u0000\u0081~"+
		"\u0001\u0000\u0000\u0000\u0082\u0085\u0001\u0000\u0000\u0000\u0083\u0081"+
		"\u0001\u0000\u0000\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084\u0011"+
		"\u0001\u0000\u0000\u0000\u0085\u0083\u0001\u0000\u0000\u0000\u0086\u0087"+
		"\u00036\u001b\u0000\u0087\u0088\u0005\u0017\u0000\u0000\u0088\u0089\u0003"+
		"\u0014\n\u0000\u0089\u0013\u0001\u0000\u0000\u0000\u008a\u008b\u0006\n"+
		"\uffff\uffff\u0000\u008b\u0093\u0003\u0016\u000b\u0000\u008c\u008d\u0005"+
		"\u000e\u0000\u0000\u008d\u0093\u0003\u0014\n\u0003\u008e\u008f\u0005\u001a"+
		"\u0000\u0000\u008f\u0090\u0003\u0014\n\u0000\u0090\u0091\u0005\u001b\u0000"+
		"\u0000\u0091\u0093\u0001\u0000\u0000\u0000\u0092\u008a\u0001\u0000\u0000"+
		"\u0000\u0092\u008c\u0001\u0000\u0000\u0000\u0092\u008e\u0001\u0000\u0000"+
		"\u0000\u0093\u0099\u0001\u0000\u0000\u0000\u0094\u0095\n\u0001\u0000\u0000"+
		"\u0095\u0096\u0007\u0000\u0000\u0000\u0096\u0098\u0003\u0014\n\u0002\u0097"+
		"\u0094\u0001\u0000\u0000\u0000\u0098\u009b\u0001\u0000\u0000\u0000\u0099"+
		"\u0097\u0001\u0000\u0000\u0000\u0099\u009a\u0001\u0000\u0000\u0000\u009a"+
		"\u0015\u0001\u0000\u0000\u0000\u009b\u0099\u0001\u0000\u0000\u0000\u009c"+
		"\u009d\u00036\u001b\u0000\u009d\u009e\u0005\u0015\u0000\u0000\u009e\u009f"+
		"\u0005\u001a\u0000\u0000\u009f\u00a0\u0003*\u0015\u0000\u00a0\u00a1\u0005"+
		"\u001b\u0000\u0000\u00a1\u0017\u0001\u0000\u0000\u0000\u00a2\u00a3\u0005"+
		"\u0007\u0000\u0000\u00a3\u00a4\u0005\u0018\u0000\u0000\u00a4\u00ab\u0003"+
		"\u001a\r\u0000\u00a5\u00a7\u0005\u0019\u0000\u0000\u00a6\u00a5\u0001\u0000"+
		"\u0000\u0000\u00a6\u00a7\u0001\u0000\u0000\u0000\u00a7\u00a8\u0001\u0000"+
		"\u0000\u0000\u00a8\u00aa\u0003\u001a\r\u0000\u00a9\u00a6\u0001\u0000\u0000"+
		"\u0000\u00aa\u00ad\u0001\u0000\u0000\u0000\u00ab\u00a9\u0001\u0000\u0000"+
		"\u0000\u00ab\u00ac\u0001\u0000\u0000\u0000\u00ac\u0019\u0001\u0000\u0000"+
		"\u0000\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ae\u00af\u00036\u001b\u0000"+
		"\u00af\u00b0\u0005\u0017\u0000\u0000\u00b0\u00b1\u0003\u001c\u000e\u0000"+
		"\u00b1\u001b\u0001\u0000\u0000\u0000\u00b2\u00b3\u00036\u001b\u0000\u00b3"+
		"\u00b4\u0005\u001a\u0000\u0000\u00b4\u00b9\u0003\u001e\u000f\u0000\u00b5"+
		"\u00b6\u0005\u0019\u0000\u0000\u00b6\u00b8\u0003\u001e\u000f\u0000\u00b7"+
		"\u00b5\u0001\u0000\u0000\u0000\u00b8\u00bb\u0001\u0000\u0000\u0000\u00b9"+
		"\u00b7\u0001\u0000\u0000\u0000\u00b9\u00ba\u0001\u0000\u0000\u0000\u00ba"+
		"\u00bc\u0001\u0000\u0000\u0000\u00bb\u00b9\u0001\u0000\u0000\u0000\u00bc"+
		"\u00bd\u0005\u001b\u0000\u0000\u00bd\u00c0\u0001\u0000\u0000\u0000\u00be"+
		"\u00c0\u00036\u001b\u0000\u00bf\u00b2\u0001\u0000\u0000\u0000\u00bf\u00be"+
		"\u0001\u0000\u0000\u0000\u00c0\u001d\u0001\u0000\u0000\u0000\u00c1\u00c4"+
		"\u00034\u001a\u0000\u00c2\u00c4\u00036\u001b\u0000\u00c3\u00c1\u0001\u0000"+
		"\u0000\u0000\u00c3\u00c2\u0001\u0000\u0000\u0000\u00c4\u001f\u0001\u0000"+
		"\u0000\u0000\u00c5\u00c6\u0007\u0001\u0000\u0000\u00c6\u00c7\u0005\u0018"+
		"\u0000\u0000\u00c7\u00ce\u0003\"\u0011\u0000\u00c8\u00ca\u0005\u0019\u0000"+
		"\u0000\u00c9\u00c8\u0001\u0000\u0000\u0000\u00c9\u00ca\u0001\u0000\u0000"+
		"\u0000\u00ca\u00cb\u0001\u0000\u0000\u0000\u00cb\u00cd\u0003\"\u0011\u0000"+
		"\u00cc\u00c9\u0001\u0000\u0000\u0000\u00cd\u00d0\u0001\u0000\u0000\u0000"+
		"\u00ce\u00cc\u0001\u0000\u0000\u0000\u00ce\u00cf\u0001\u0000\u0000\u0000"+
		"\u00cf!\u0001\u0000\u0000\u0000\u00d0\u00ce\u0001\u0000\u0000\u0000\u00d1"+
		"\u00d2\u00036\u001b\u0000\u00d2\u00d3\u0005\u001c\u0000\u0000\u00d3\u00d8"+
		"\u0003$\u0012\u0000\u00d4\u00d5\u0005\u0019\u0000\u0000\u00d5\u00d7\u0003"+
		"$\u0012\u0000\u00d6\u00d4\u0001\u0000\u0000\u0000\u00d7\u00da\u0001\u0000"+
		"\u0000\u0000\u00d8\u00d6\u0001\u0000\u0000\u0000\u00d8\u00d9\u0001\u0000"+
		"\u0000\u0000\u00d9\u00db\u0001\u0000\u0000\u0000\u00da\u00d8\u0001\u0000"+
		"\u0000\u0000\u00db\u00dc\u0005\u001d\u0000\u0000\u00dc\u00dd\u0005\u0018"+
		"\u0000\u0000\u00dd\u00de\u0003&\u0013\u0000\u00de#\u0001\u0000\u0000\u0000"+
		"\u00df\u00e0\u00036\u001b\u0000\u00e0\u00e1\u0005\u0014\u0000\u0000\u00e1"+
		"\u00e2\u00036\u001b\u0000\u00e2%\u0001\u0000\u0000\u0000\u00e3\u00e4\u0006"+
		"\u0013\uffff\uffff\u0000\u00e4\u00ec\u00036\u001b\u0000\u00e5\u00e6\u0005"+
		"\u000e\u0000\u0000\u00e6\u00ec\u0003&\u0013\u0004\u00e7\u00e8\u0005\u001a"+
		"\u0000\u0000\u00e8\u00e9\u0003&\u0013\u0000\u00e9\u00ea\u0005\u001b\u0000"+
		"\u0000\u00ea\u00ec\u0001\u0000\u0000\u0000\u00eb\u00e3\u0001\u0000\u0000"+
		"\u0000\u00eb\u00e5\u0001\u0000\u0000\u0000\u00eb\u00e7\u0001\u0000\u0000"+
		"\u0000\u00ec\u00f4\u0001\u0000\u0000\u0000\u00ed\u00ee\n\u0001\u0000\u0000"+
		"\u00ee\u00ef\u0007\u0002\u0000\u0000\u00ef\u00f3\u0003&\u0013\u0002\u00f0"+
		"\u00f1\n\u0002\u0000\u0000\u00f1\u00f3\u0007\u0003\u0000\u0000\u00f2\u00ed"+
		"\u0001\u0000\u0000\u0000\u00f2\u00f0\u0001\u0000\u0000\u0000\u00f3\u00f6"+
		"\u0001\u0000\u0000\u0000\u00f4\u00f2\u0001\u0000\u0000\u0000\u00f4\u00f5"+
		"\u0001\u0000\u0000\u0000\u00f5\'\u0001\u0000\u0000\u0000\u00f6\u00f4\u0001"+
		"\u0000\u0000\u0000\u00f7\u00fb\u00034\u001a\u0000\u00f8\u00fb\u00036\u001b"+
		"\u0000\u00f9\u00fb\u00032\u0019\u0000\u00fa\u00f7\u0001\u0000\u0000\u0000"+
		"\u00fa\u00f8\u0001\u0000\u0000\u0000\u00fa\u00f9\u0001\u0000\u0000\u0000"+
		"\u00fb)\u0001\u0000\u0000\u0000\u00fc\u00fd\u0006\u0015\uffff\uffff\u0000"+
		"\u00fd\u0105\u0003,\u0016\u0000\u00fe\u00ff\u0005\u000e\u0000\u0000\u00ff"+
		"\u0105\u0003*\u0015\u0003\u0100\u0101\u0005\u001a\u0000\u0000\u0101\u0102"+
		"\u0003*\u0015\u0000\u0102\u0103\u0005\u001b\u0000\u0000\u0103\u0105\u0001"+
		"\u0000\u0000\u0000\u0104\u00fc\u0001\u0000\u0000\u0000\u0104\u00fe\u0001"+
		"\u0000\u0000\u0000\u0104\u0100\u0001\u0000\u0000\u0000\u0105\u010b\u0001"+
		"\u0000\u0000\u0000\u0106\u0107\n\u0001\u0000\u0000\u0107\u0108\u0007\u0000"+
		"\u0000\u0000\u0108\u010a\u0003*\u0015\u0002\u0109\u0106\u0001\u0000\u0000"+
		"\u0000\u010a\u010d\u0001\u0000\u0000\u0000\u010b\u0109\u0001\u0000\u0000"+
		"\u0000\u010b\u010c\u0001\u0000\u0000\u0000\u010c+\u0001\u0000\u0000\u0000"+
		"\u010d\u010b\u0001\u0000\u0000\u0000\u010e\u010f\u0003.\u0017\u0000\u010f"+
		"\u0110\u0005\u0016\u0000\u0000\u0110\u0111\u00030\u0018\u0000\u0111-\u0001"+
		"\u0000\u0000\u0000\u0112\u0113\u0005\n\u0000\u0000\u0113\u0114\u0005\u001a"+
		"\u0000\u0000\u0114\u0115\u00036\u001b\u0000\u0115\u0116\u0005\u001b\u0000"+
		"\u0000\u0116/\u0001\u0000\u0000\u0000\u0117\u011b\u00034\u001a\u0000\u0118"+
		"\u011b\u00036\u001b\u0000\u0119\u011b\u00032\u0019\u0000\u011a\u0117\u0001"+
		"\u0000\u0000\u0000\u011a\u0118\u0001\u0000\u0000\u0000\u011a\u0119\u0001"+
		"\u0000\u0000\u0000\u011b1\u0001\u0000\u0000\u0000\u011c\u011d\u0005\""+
		"\u0000\u0000\u011d3\u0001\u0000\u0000\u0000\u011e\u011f\u0005 \u0000\u0000"+
		"\u011f5\u0001\u0000\u0000\u0000\u0120\u0121\u0005!\u0000\u0000\u01217"+
		"\u0001\u0000\u0000\u0000\u001a;AK\\chu~\u0083\u0092\u0099\u00a6\u00ab"+
		"\u00b9\u00bf\u00c3\u00c9\u00ce\u00d8\u00eb\u00f2\u00f4\u00fa\u0104\u010b"+
		"\u011a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}