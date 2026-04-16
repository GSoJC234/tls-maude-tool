// Generated from src/mta/user/behavior/antlr/BehaviorSpec.g4 by ANTLR 4.13.2
package mta.user.behavior.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class BehaviorSpecParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, ADD=6, SET=7, REMOVE=8, NOCHECK=9, 
		SKIP_KW=10, DELAY=11, ONEOF=12, BYTES=13, VALUE=14, AND=15, OR=16, XOR=17, 
		NOT=18, EQ=19, COLON=20, COMMA=21, LPAREN=22, RPAREN=23, PARAM_REF=24, 
		HEX=25, IDENTIFIER=26, WS=27, LINE_COMMENT=28;
	public static final int
		RULE_behaviorSpec = 0, RULE_behaviorIdSection = 1, RULE_parametersSection = 2, 
		RULE_eventTypeSection = 3, RULE_conditionsSection = 4, RULE_conditionExpr = 5, 
		RULE_conditionPredicate = 6, RULE_valueAccessor = 7, RULE_modificationSection = 8, 
		RULE_modificationStatement = 9, RULE_addModification = 10, RULE_setModification = 11, 
		RULE_removeModification = 12, RULE_noCheckModification = 13, RULE_skipModification = 14, 
		RULE_delayModification = 15, RULE_modificationValue = 16, RULE_targetRef = 17, 
		RULE_functionCall = 18, RULE_argumentList = 19, RULE_argumentValue = 20, 
		RULE_operandValue = 21, RULE_parameterRef = 22, RULE_identifierValue = 23, 
		RULE_hexLiteral = 24;
	private static String[] makeRuleNames() {
		return new String[] {
			"behaviorSpec", "behaviorIdSection", "parametersSection", "eventTypeSection", 
			"conditionsSection", "conditionExpr", "conditionPredicate", "valueAccessor", 
			"modificationSection", "modificationStatement", "addModification", "setModification", 
			"removeModification", "noCheckModification", "skipModification", "delayModification", 
			"modificationValue", "targetRef", "functionCall", "argumentList", "argumentValue", 
			"operandValue", "parameterRef", "identifierValue", "hexLiteral"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'BehaviorId'", "'Parameters'", "'EventType'", "'Conditions'", 
			"'Modification'", "'add'", "'set'", "'remove'", "'noCheck'", "'skip'", 
			"'delay'", "'oneOf'", "'bytes'", "'value'", "'and'", "'or'", "'xor'", 
			"'not'", "'=='", "':'", "','", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "ADD", "SET", "REMOVE", "NOCHECK", 
			"SKIP_KW", "DELAY", "ONEOF", "BYTES", "VALUE", "AND", "OR", "XOR", "NOT", 
			"EQ", "COLON", "COMMA", "LPAREN", "RPAREN", "PARAM_REF", "HEX", "IDENTIFIER", 
			"WS", "LINE_COMMENT"
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
	public String getGrammarFileName() { return "BehaviorSpec.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public BehaviorSpecParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BehaviorSpecContext extends ParserRuleContext {
		public BehaviorIdSectionContext behaviorIdSection() {
			return getRuleContext(BehaviorIdSectionContext.class,0);
		}
		public EventTypeSectionContext eventTypeSection() {
			return getRuleContext(EventTypeSectionContext.class,0);
		}
		public ConditionsSectionContext conditionsSection() {
			return getRuleContext(ConditionsSectionContext.class,0);
		}
		public ModificationSectionContext modificationSection() {
			return getRuleContext(ModificationSectionContext.class,0);
		}
		public TerminalNode EOF() { return getToken(BehaviorSpecParser.EOF, 0); }
		public ParametersSectionContext parametersSection() {
			return getRuleContext(ParametersSectionContext.class,0);
		}
		public BehaviorSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_behaviorSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).enterBehaviorSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).exitBehaviorSpec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BehaviorSpecVisitor ) return ((BehaviorSpecVisitor<? extends T>)visitor).visitBehaviorSpec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BehaviorSpecContext behaviorSpec() throws RecognitionException {
		BehaviorSpecContext _localctx = new BehaviorSpecContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_behaviorSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			behaviorIdSection();
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(51);
				parametersSection();
				}
			}

			setState(54);
			eventTypeSection();
			setState(55);
			conditionsSection();
			setState(56);
			modificationSection();
			setState(57);
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
	public static class BehaviorIdSectionContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(BehaviorSpecParser.COLON, 0); }
		public IdentifierValueContext identifierValue() {
			return getRuleContext(IdentifierValueContext.class,0);
		}
		public BehaviorIdSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_behaviorIdSection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).enterBehaviorIdSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).exitBehaviorIdSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BehaviorSpecVisitor ) return ((BehaviorSpecVisitor<? extends T>)visitor).visitBehaviorIdSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BehaviorIdSectionContext behaviorIdSection() throws RecognitionException {
		BehaviorIdSectionContext _localctx = new BehaviorIdSectionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_behaviorIdSection);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			match(T__0);
			setState(60);
			match(COLON);
			setState(61);
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
	public static class ParametersSectionContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(BehaviorSpecParser.COLON, 0); }
		public List<ParameterRefContext> parameterRef() {
			return getRuleContexts(ParameterRefContext.class);
		}
		public ParameterRefContext parameterRef(int i) {
			return getRuleContext(ParameterRefContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BehaviorSpecParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BehaviorSpecParser.COMMA, i);
		}
		public ParametersSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parametersSection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).enterParametersSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).exitParametersSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BehaviorSpecVisitor ) return ((BehaviorSpecVisitor<? extends T>)visitor).visitParametersSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParametersSectionContext parametersSection() throws RecognitionException {
		ParametersSectionContext _localctx = new ParametersSectionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_parametersSection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(T__1);
			setState(64);
			match(COLON);
			setState(65);
			parameterRef();
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(66);
				match(COMMA);
				setState(67);
				parameterRef();
				}
				}
				setState(72);
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
	public static class EventTypeSectionContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(BehaviorSpecParser.COLON, 0); }
		public IdentifierValueContext identifierValue() {
			return getRuleContext(IdentifierValueContext.class,0);
		}
		public EventTypeSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eventTypeSection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).enterEventTypeSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).exitEventTypeSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BehaviorSpecVisitor ) return ((BehaviorSpecVisitor<? extends T>)visitor).visitEventTypeSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EventTypeSectionContext eventTypeSection() throws RecognitionException {
		EventTypeSectionContext _localctx = new EventTypeSectionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_eventTypeSection);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			match(T__2);
			setState(74);
			match(COLON);
			setState(75);
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
	public static class ConditionsSectionContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(BehaviorSpecParser.COLON, 0); }
		public ConditionExprContext conditionExpr() {
			return getRuleContext(ConditionExprContext.class,0);
		}
		public ConditionsSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionsSection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).enterConditionsSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).exitConditionsSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BehaviorSpecVisitor ) return ((BehaviorSpecVisitor<? extends T>)visitor).visitConditionsSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionsSectionContext conditionsSection() throws RecognitionException {
		ConditionsSectionContext _localctx = new ConditionsSectionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_conditionsSection);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(T__3);
			setState(78);
			match(COLON);
			setState(79);
			conditionExpr(0);
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
		public TerminalNode NOT() { return getToken(BehaviorSpecParser.NOT, 0); }
		public List<ConditionExprContext> conditionExpr() {
			return getRuleContexts(ConditionExprContext.class);
		}
		public ConditionExprContext conditionExpr(int i) {
			return getRuleContext(ConditionExprContext.class,i);
		}
		public TerminalNode OR() { return getToken(BehaviorSpecParser.OR, 0); }
		public TerminalNode XOR() { return getToken(BehaviorSpecParser.XOR, 0); }
		public TerminalNode AND() { return getToken(BehaviorSpecParser.AND, 0); }
		public ConditionExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).enterConditionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).exitConditionExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BehaviorSpecVisitor ) return ((BehaviorSpecVisitor<? extends T>)visitor).visitConditionExpr(this);
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
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_conditionExpr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VALUE:
				{
				setState(82);
				conditionPredicate();
				}
				break;
			case NOT:
				{
				setState(83);
				match(NOT);
				setState(84);
				conditionExpr(2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(92);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ConditionExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_conditionExpr);
					setState(87);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(88);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 229376L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(89);
					conditionExpr(2);
					}
					} 
				}
				setState(94);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
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
		public TerminalNode EQ() { return getToken(BehaviorSpecParser.EQ, 0); }
		public OperandValueContext operandValue() {
			return getRuleContext(OperandValueContext.class,0);
		}
		public ConditionPredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionPredicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).enterConditionPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).exitConditionPredicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BehaviorSpecVisitor ) return ((BehaviorSpecVisitor<? extends T>)visitor).visitConditionPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionPredicateContext conditionPredicate() throws RecognitionException {
		ConditionPredicateContext _localctx = new ConditionPredicateContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_conditionPredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			valueAccessor();
			setState(96);
			match(EQ);
			setState(97);
			operandValue();
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
		public TerminalNode VALUE() { return getToken(BehaviorSpecParser.VALUE, 0); }
		public TerminalNode LPAREN() { return getToken(BehaviorSpecParser.LPAREN, 0); }
		public IdentifierValueContext identifierValue() {
			return getRuleContext(IdentifierValueContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(BehaviorSpecParser.RPAREN, 0); }
		public ValueAccessorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueAccessor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).enterValueAccessor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).exitValueAccessor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BehaviorSpecVisitor ) return ((BehaviorSpecVisitor<? extends T>)visitor).visitValueAccessor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueAccessorContext valueAccessor() throws RecognitionException {
		ValueAccessorContext _localctx = new ValueAccessorContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_valueAccessor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(VALUE);
			setState(100);
			match(LPAREN);
			setState(101);
			identifierValue();
			setState(102);
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
	public static class ModificationSectionContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(BehaviorSpecParser.COLON, 0); }
		public List<ModificationStatementContext> modificationStatement() {
			return getRuleContexts(ModificationStatementContext.class);
		}
		public ModificationStatementContext modificationStatement(int i) {
			return getRuleContext(ModificationStatementContext.class,i);
		}
		public ModificationSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modificationSection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).enterModificationSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).exitModificationSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BehaviorSpecVisitor ) return ((BehaviorSpecVisitor<? extends T>)visitor).visitModificationSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModificationSectionContext modificationSection() throws RecognitionException {
		ModificationSectionContext _localctx = new ModificationSectionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_modificationSection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(T__4);
			setState(105);
			match(COLON);
			setState(107); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(106);
				modificationStatement();
				}
				}
				setState(109); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 4032L) != 0) );
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
	public static class ModificationStatementContext extends ParserRuleContext {
		public AddModificationContext addModification() {
			return getRuleContext(AddModificationContext.class,0);
		}
		public SetModificationContext setModification() {
			return getRuleContext(SetModificationContext.class,0);
		}
		public RemoveModificationContext removeModification() {
			return getRuleContext(RemoveModificationContext.class,0);
		}
		public NoCheckModificationContext noCheckModification() {
			return getRuleContext(NoCheckModificationContext.class,0);
		}
		public SkipModificationContext skipModification() {
			return getRuleContext(SkipModificationContext.class,0);
		}
		public DelayModificationContext delayModification() {
			return getRuleContext(DelayModificationContext.class,0);
		}
		public ModificationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modificationStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).enterModificationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).exitModificationStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BehaviorSpecVisitor ) return ((BehaviorSpecVisitor<? extends T>)visitor).visitModificationStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModificationStatementContext modificationStatement() throws RecognitionException {
		ModificationStatementContext _localctx = new ModificationStatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_modificationStatement);
		try {
			setState(117);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
				enterOuterAlt(_localctx, 1);
				{
				setState(111);
				addModification();
				}
				break;
			case SET:
				enterOuterAlt(_localctx, 2);
				{
				setState(112);
				setModification();
				}
				break;
			case REMOVE:
				enterOuterAlt(_localctx, 3);
				{
				setState(113);
				removeModification();
				}
				break;
			case NOCHECK:
				enterOuterAlt(_localctx, 4);
				{
				setState(114);
				noCheckModification();
				}
				break;
			case SKIP_KW:
				enterOuterAlt(_localctx, 5);
				{
				setState(115);
				skipModification();
				}
				break;
			case DELAY:
				enterOuterAlt(_localctx, 6);
				{
				setState(116);
				delayModification();
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
	public static class AddModificationContext extends ParserRuleContext {
		public TerminalNode ADD() { return getToken(BehaviorSpecParser.ADD, 0); }
		public TerminalNode LPAREN() { return getToken(BehaviorSpecParser.LPAREN, 0); }
		public TargetRefContext targetRef() {
			return getRuleContext(TargetRefContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(BehaviorSpecParser.COMMA, 0); }
		public ModificationValueContext modificationValue() {
			return getRuleContext(ModificationValueContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(BehaviorSpecParser.RPAREN, 0); }
		public AddModificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addModification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).enterAddModification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).exitAddModification(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BehaviorSpecVisitor ) return ((BehaviorSpecVisitor<? extends T>)visitor).visitAddModification(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddModificationContext addModification() throws RecognitionException {
		AddModificationContext _localctx = new AddModificationContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_addModification);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(ADD);
			setState(120);
			match(LPAREN);
			setState(121);
			targetRef();
			setState(122);
			match(COMMA);
			setState(123);
			modificationValue();
			setState(124);
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
	public static class SetModificationContext extends ParserRuleContext {
		public TerminalNode SET() { return getToken(BehaviorSpecParser.SET, 0); }
		public TerminalNode LPAREN() { return getToken(BehaviorSpecParser.LPAREN, 0); }
		public TargetRefContext targetRef() {
			return getRuleContext(TargetRefContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(BehaviorSpecParser.COMMA, 0); }
		public ModificationValueContext modificationValue() {
			return getRuleContext(ModificationValueContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(BehaviorSpecParser.RPAREN, 0); }
		public SetModificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setModification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).enterSetModification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).exitSetModification(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BehaviorSpecVisitor ) return ((BehaviorSpecVisitor<? extends T>)visitor).visitSetModification(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetModificationContext setModification() throws RecognitionException {
		SetModificationContext _localctx = new SetModificationContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_setModification);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(SET);
			setState(127);
			match(LPAREN);
			setState(128);
			targetRef();
			setState(129);
			match(COMMA);
			setState(130);
			modificationValue();
			setState(131);
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
	public static class RemoveModificationContext extends ParserRuleContext {
		public TerminalNode REMOVE() { return getToken(BehaviorSpecParser.REMOVE, 0); }
		public TerminalNode LPAREN() { return getToken(BehaviorSpecParser.LPAREN, 0); }
		public TargetRefContext targetRef() {
			return getRuleContext(TargetRefContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(BehaviorSpecParser.RPAREN, 0); }
		public RemoveModificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_removeModification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).enterRemoveModification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).exitRemoveModification(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BehaviorSpecVisitor ) return ((BehaviorSpecVisitor<? extends T>)visitor).visitRemoveModification(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RemoveModificationContext removeModification() throws RecognitionException {
		RemoveModificationContext _localctx = new RemoveModificationContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_removeModification);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			match(REMOVE);
			setState(134);
			match(LPAREN);
			setState(135);
			targetRef();
			setState(136);
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
	public static class NoCheckModificationContext extends ParserRuleContext {
		public TerminalNode NOCHECK() { return getToken(BehaviorSpecParser.NOCHECK, 0); }
		public TerminalNode LPAREN() { return getToken(BehaviorSpecParser.LPAREN, 0); }
		public TargetRefContext targetRef() {
			return getRuleContext(TargetRefContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(BehaviorSpecParser.RPAREN, 0); }
		public NoCheckModificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_noCheckModification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).enterNoCheckModification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).exitNoCheckModification(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BehaviorSpecVisitor ) return ((BehaviorSpecVisitor<? extends T>)visitor).visitNoCheckModification(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NoCheckModificationContext noCheckModification() throws RecognitionException {
		NoCheckModificationContext _localctx = new NoCheckModificationContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_noCheckModification);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(NOCHECK);
			setState(139);
			match(LPAREN);
			setState(140);
			targetRef();
			setState(141);
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
	public static class SkipModificationContext extends ParserRuleContext {
		public TerminalNode SKIP_KW() { return getToken(BehaviorSpecParser.SKIP_KW, 0); }
		public SkipModificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_skipModification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).enterSkipModification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).exitSkipModification(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BehaviorSpecVisitor ) return ((BehaviorSpecVisitor<? extends T>)visitor).visitSkipModification(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SkipModificationContext skipModification() throws RecognitionException {
		SkipModificationContext _localctx = new SkipModificationContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_skipModification);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(SKIP_KW);
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
	public static class DelayModificationContext extends ParserRuleContext {
		public TerminalNode DELAY() { return getToken(BehaviorSpecParser.DELAY, 0); }
		public TerminalNode LPAREN() { return getToken(BehaviorSpecParser.LPAREN, 0); }
		public OperandValueContext operandValue() {
			return getRuleContext(OperandValueContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(BehaviorSpecParser.RPAREN, 0); }
		public DelayModificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delayModification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).enterDelayModification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).exitDelayModification(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BehaviorSpecVisitor ) return ((BehaviorSpecVisitor<? extends T>)visitor).visitDelayModification(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DelayModificationContext delayModification() throws RecognitionException {
		DelayModificationContext _localctx = new DelayModificationContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_delayModification);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(DELAY);
			setState(146);
			match(LPAREN);
			setState(147);
			operandValue();
			setState(148);
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
	public static class ModificationValueContext extends ParserRuleContext {
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public OperandValueContext operandValue() {
			return getRuleContext(OperandValueContext.class,0);
		}
		public ModificationValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modificationValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).enterModificationValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).exitModificationValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BehaviorSpecVisitor ) return ((BehaviorSpecVisitor<? extends T>)visitor).visitModificationValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModificationValueContext modificationValue() throws RecognitionException {
		ModificationValueContext _localctx = new ModificationValueContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_modificationValue);
		try {
			setState(152);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ONEOF:
			case BYTES:
				enterOuterAlt(_localctx, 1);
				{
				setState(150);
				functionCall();
				}
				break;
			case PARAM_REF:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(151);
				operandValue();
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
	public static class TargetRefContext extends ParserRuleContext {
		public ParameterRefContext parameterRef() {
			return getRuleContext(ParameterRefContext.class,0);
		}
		public IdentifierValueContext identifierValue() {
			return getRuleContext(IdentifierValueContext.class,0);
		}
		public TargetRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_targetRef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).enterTargetRef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).exitTargetRef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BehaviorSpecVisitor ) return ((BehaviorSpecVisitor<? extends T>)visitor).visitTargetRef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TargetRefContext targetRef() throws RecognitionException {
		TargetRefContext _localctx = new TargetRefContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_targetRef);
		try {
			setState(156);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PARAM_REF:
				enterOuterAlt(_localctx, 1);
				{
				setState(154);
				parameterRef();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(155);
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
	public static class FunctionCallContext extends ParserRuleContext {
		public TerminalNode ONEOF() { return getToken(BehaviorSpecParser.ONEOF, 0); }
		public TerminalNode LPAREN() { return getToken(BehaviorSpecParser.LPAREN, 0); }
		public ParameterRefContext parameterRef() {
			return getRuleContext(ParameterRefContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(BehaviorSpecParser.RPAREN, 0); }
		public TerminalNode BYTES() { return getToken(BehaviorSpecParser.BYTES, 0); }
		public HexLiteralContext hexLiteral() {
			return getRuleContext(HexLiteralContext.class,0);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).exitFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BehaviorSpecVisitor ) return ((BehaviorSpecVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_functionCall);
		try {
			setState(168);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ONEOF:
				enterOuterAlt(_localctx, 1);
				{
				setState(158);
				match(ONEOF);
				setState(159);
				match(LPAREN);
				setState(160);
				parameterRef();
				setState(161);
				match(RPAREN);
				}
				break;
			case BYTES:
				enterOuterAlt(_localctx, 2);
				{
				setState(163);
				match(BYTES);
				setState(164);
				match(LPAREN);
				setState(165);
				hexLiteral();
				setState(166);
				match(RPAREN);
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
	public static class ArgumentListContext extends ParserRuleContext {
		public List<ArgumentValueContext> argumentValue() {
			return getRuleContexts(ArgumentValueContext.class);
		}
		public ArgumentValueContext argumentValue(int i) {
			return getRuleContext(ArgumentValueContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BehaviorSpecParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BehaviorSpecParser.COMMA, i);
		}
		public ArgumentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).enterArgumentList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).exitArgumentList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BehaviorSpecVisitor ) return ((BehaviorSpecVisitor<? extends T>)visitor).visitArgumentList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentListContext argumentList() throws RecognitionException {
		ArgumentListContext _localctx = new ArgumentListContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_argumentList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			argumentValue();
			setState(175);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(171);
				match(COMMA);
				setState(172);
				argumentValue();
				}
				}
				setState(177);
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
	public static class ArgumentValueContext extends ParserRuleContext {
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public OperandValueContext operandValue() {
			return getRuleContext(OperandValueContext.class,0);
		}
		public ArgumentValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).enterArgumentValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).exitArgumentValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BehaviorSpecVisitor ) return ((BehaviorSpecVisitor<? extends T>)visitor).visitArgumentValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentValueContext argumentValue() throws RecognitionException {
		ArgumentValueContext _localctx = new ArgumentValueContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_argumentValue);
		try {
			setState(180);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ONEOF:
			case BYTES:
				enterOuterAlt(_localctx, 1);
				{
				setState(178);
				functionCall();
				}
				break;
			case PARAM_REF:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(179);
				operandValue();
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
	public static class OperandValueContext extends ParserRuleContext {
		public ParameterRefContext parameterRef() {
			return getRuleContext(ParameterRefContext.class,0);
		}
		public IdentifierValueContext identifierValue() {
			return getRuleContext(IdentifierValueContext.class,0);
		}
		public OperandValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operandValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).enterOperandValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).exitOperandValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BehaviorSpecVisitor ) return ((BehaviorSpecVisitor<? extends T>)visitor).visitOperandValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperandValueContext operandValue() throws RecognitionException {
		OperandValueContext _localctx = new OperandValueContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_operandValue);
		try {
			setState(184);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PARAM_REF:
				enterOuterAlt(_localctx, 1);
				{
				setState(182);
				parameterRef();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(183);
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
	public static class ParameterRefContext extends ParserRuleContext {
		public TerminalNode PARAM_REF() { return getToken(BehaviorSpecParser.PARAM_REF, 0); }
		public ParameterRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterRef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).enterParameterRef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).exitParameterRef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BehaviorSpecVisitor ) return ((BehaviorSpecVisitor<? extends T>)visitor).visitParameterRef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterRefContext parameterRef() throws RecognitionException {
		ParameterRefContext _localctx = new ParameterRefContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_parameterRef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			match(PARAM_REF);
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
		public TerminalNode IDENTIFIER() { return getToken(BehaviorSpecParser.IDENTIFIER, 0); }
		public IdentifierValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifierValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).enterIdentifierValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).exitIdentifierValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BehaviorSpecVisitor ) return ((BehaviorSpecVisitor<? extends T>)visitor).visitIdentifierValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierValueContext identifierValue() throws RecognitionException {
		IdentifierValueContext _localctx = new IdentifierValueContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_identifierValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
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
	public static class HexLiteralContext extends ParserRuleContext {
		public TerminalNode HEX() { return getToken(BehaviorSpecParser.HEX, 0); }
		public HexLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hexLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).enterHexLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BehaviorSpecListener ) ((BehaviorSpecListener)listener).exitHexLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BehaviorSpecVisitor ) return ((BehaviorSpecVisitor<? extends T>)visitor).visitHexLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HexLiteralContext hexLiteral() throws RecognitionException {
		HexLiteralContext _localctx = new HexLiteralContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_hexLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			match(HEX);
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
		case 5:
			return conditionExpr_sempred((ConditionExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean conditionExpr_sempred(ConditionExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u001c\u00c1\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007"+
		"\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007"+
		"\u0018\u0001\u0000\u0001\u0000\u0003\u00005\b\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0005\u0002E\b\u0002\n\u0002\f\u0002H\t\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005V\b"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005[\b\u0005\n\u0005"+
		"\f\u0005^\t\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b"+
		"\u0001\b\u0004\bl\b\b\u000b\b\f\bm\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0003\tv\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0003\u0010\u0099\b\u0010\u0001\u0011\u0001\u0011\u0003\u0011\u009d\b"+
		"\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u00a9"+
		"\b\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0005\u0013\u00ae\b\u0013"+
		"\n\u0013\f\u0013\u00b1\t\u0013\u0001\u0014\u0001\u0014\u0003\u0014\u00b5"+
		"\b\u0014\u0001\u0015\u0001\u0015\u0003\u0015\u00b9\b\u0015\u0001\u0016"+
		"\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0000\u0001\n\u0019\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014"+
		"\u0016\u0018\u001a\u001c\u001e \"$&(*,.0\u0000\u0001\u0001\u0000\u000f"+
		"\u0011\u00b7\u00002\u0001\u0000\u0000\u0000\u0002;\u0001\u0000\u0000\u0000"+
		"\u0004?\u0001\u0000\u0000\u0000\u0006I\u0001\u0000\u0000\u0000\bM\u0001"+
		"\u0000\u0000\u0000\nU\u0001\u0000\u0000\u0000\f_\u0001\u0000\u0000\u0000"+
		"\u000ec\u0001\u0000\u0000\u0000\u0010h\u0001\u0000\u0000\u0000\u0012u"+
		"\u0001\u0000\u0000\u0000\u0014w\u0001\u0000\u0000\u0000\u0016~\u0001\u0000"+
		"\u0000\u0000\u0018\u0085\u0001\u0000\u0000\u0000\u001a\u008a\u0001\u0000"+
		"\u0000\u0000\u001c\u008f\u0001\u0000\u0000\u0000\u001e\u0091\u0001\u0000"+
		"\u0000\u0000 \u0098\u0001\u0000\u0000\u0000\"\u009c\u0001\u0000\u0000"+
		"\u0000$\u00a8\u0001\u0000\u0000\u0000&\u00aa\u0001\u0000\u0000\u0000("+
		"\u00b4\u0001\u0000\u0000\u0000*\u00b8\u0001\u0000\u0000\u0000,\u00ba\u0001"+
		"\u0000\u0000\u0000.\u00bc\u0001\u0000\u0000\u00000\u00be\u0001\u0000\u0000"+
		"\u000024\u0003\u0002\u0001\u000035\u0003\u0004\u0002\u000043\u0001\u0000"+
		"\u0000\u000045\u0001\u0000\u0000\u000056\u0001\u0000\u0000\u000067\u0003"+
		"\u0006\u0003\u000078\u0003\b\u0004\u000089\u0003\u0010\b\u00009:\u0005"+
		"\u0000\u0000\u0001:\u0001\u0001\u0000\u0000\u0000;<\u0005\u0001\u0000"+
		"\u0000<=\u0005\u0014\u0000\u0000=>\u0003.\u0017\u0000>\u0003\u0001\u0000"+
		"\u0000\u0000?@\u0005\u0002\u0000\u0000@A\u0005\u0014\u0000\u0000AF\u0003"+
		",\u0016\u0000BC\u0005\u0015\u0000\u0000CE\u0003,\u0016\u0000DB\u0001\u0000"+
		"\u0000\u0000EH\u0001\u0000\u0000\u0000FD\u0001\u0000\u0000\u0000FG\u0001"+
		"\u0000\u0000\u0000G\u0005\u0001\u0000\u0000\u0000HF\u0001\u0000\u0000"+
		"\u0000IJ\u0005\u0003\u0000\u0000JK\u0005\u0014\u0000\u0000KL\u0003.\u0017"+
		"\u0000L\u0007\u0001\u0000\u0000\u0000MN\u0005\u0004\u0000\u0000NO\u0005"+
		"\u0014\u0000\u0000OP\u0003\n\u0005\u0000P\t\u0001\u0000\u0000\u0000QR"+
		"\u0006\u0005\uffff\uffff\u0000RV\u0003\f\u0006\u0000ST\u0005\u0012\u0000"+
		"\u0000TV\u0003\n\u0005\u0002UQ\u0001\u0000\u0000\u0000US\u0001\u0000\u0000"+
		"\u0000V\\\u0001\u0000\u0000\u0000WX\n\u0001\u0000\u0000XY\u0007\u0000"+
		"\u0000\u0000Y[\u0003\n\u0005\u0002ZW\u0001\u0000\u0000\u0000[^\u0001\u0000"+
		"\u0000\u0000\\Z\u0001\u0000\u0000\u0000\\]\u0001\u0000\u0000\u0000]\u000b"+
		"\u0001\u0000\u0000\u0000^\\\u0001\u0000\u0000\u0000_`\u0003\u000e\u0007"+
		"\u0000`a\u0005\u0013\u0000\u0000ab\u0003*\u0015\u0000b\r\u0001\u0000\u0000"+
		"\u0000cd\u0005\u000e\u0000\u0000de\u0005\u0016\u0000\u0000ef\u0003.\u0017"+
		"\u0000fg\u0005\u0017\u0000\u0000g\u000f\u0001\u0000\u0000\u0000hi\u0005"+
		"\u0005\u0000\u0000ik\u0005\u0014\u0000\u0000jl\u0003\u0012\t\u0000kj\u0001"+
		"\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000mk\u0001\u0000\u0000\u0000"+
		"mn\u0001\u0000\u0000\u0000n\u0011\u0001\u0000\u0000\u0000ov\u0003\u0014"+
		"\n\u0000pv\u0003\u0016\u000b\u0000qv\u0003\u0018\f\u0000rv\u0003\u001a"+
		"\r\u0000sv\u0003\u001c\u000e\u0000tv\u0003\u001e\u000f\u0000uo\u0001\u0000"+
		"\u0000\u0000up\u0001\u0000\u0000\u0000uq\u0001\u0000\u0000\u0000ur\u0001"+
		"\u0000\u0000\u0000us\u0001\u0000\u0000\u0000ut\u0001\u0000\u0000\u0000"+
		"v\u0013\u0001\u0000\u0000\u0000wx\u0005\u0006\u0000\u0000xy\u0005\u0016"+
		"\u0000\u0000yz\u0003\"\u0011\u0000z{\u0005\u0015\u0000\u0000{|\u0003 "+
		"\u0010\u0000|}\u0005\u0017\u0000\u0000}\u0015\u0001\u0000\u0000\u0000"+
		"~\u007f\u0005\u0007\u0000\u0000\u007f\u0080\u0005\u0016\u0000\u0000\u0080"+
		"\u0081\u0003\"\u0011\u0000\u0081\u0082\u0005\u0015\u0000\u0000\u0082\u0083"+
		"\u0003 \u0010\u0000\u0083\u0084\u0005\u0017\u0000\u0000\u0084\u0017\u0001"+
		"\u0000\u0000\u0000\u0085\u0086\u0005\b\u0000\u0000\u0086\u0087\u0005\u0016"+
		"\u0000\u0000\u0087\u0088\u0003\"\u0011\u0000\u0088\u0089\u0005\u0017\u0000"+
		"\u0000\u0089\u0019\u0001\u0000\u0000\u0000\u008a\u008b\u0005\t\u0000\u0000"+
		"\u008b\u008c\u0005\u0016\u0000\u0000\u008c\u008d\u0003\"\u0011\u0000\u008d"+
		"\u008e\u0005\u0017\u0000\u0000\u008e\u001b\u0001\u0000\u0000\u0000\u008f"+
		"\u0090\u0005\n\u0000\u0000\u0090\u001d\u0001\u0000\u0000\u0000\u0091\u0092"+
		"\u0005\u000b\u0000\u0000\u0092\u0093\u0005\u0016\u0000\u0000\u0093\u0094"+
		"\u0003*\u0015\u0000\u0094\u0095\u0005\u0017\u0000\u0000\u0095\u001f\u0001"+
		"\u0000\u0000\u0000\u0096\u0099\u0003$\u0012\u0000\u0097\u0099\u0003*\u0015"+
		"\u0000\u0098\u0096\u0001\u0000\u0000\u0000\u0098\u0097\u0001\u0000\u0000"+
		"\u0000\u0099!\u0001\u0000\u0000\u0000\u009a\u009d\u0003,\u0016\u0000\u009b"+
		"\u009d\u0003.\u0017\u0000\u009c\u009a\u0001\u0000\u0000\u0000\u009c\u009b"+
		"\u0001\u0000\u0000\u0000\u009d#\u0001\u0000\u0000\u0000\u009e\u009f\u0005"+
		"\f\u0000\u0000\u009f\u00a0\u0005\u0016\u0000\u0000\u00a0\u00a1\u0003,"+
		"\u0016\u0000\u00a1\u00a2\u0005\u0017\u0000\u0000\u00a2\u00a9\u0001\u0000"+
		"\u0000\u0000\u00a3\u00a4\u0005\r\u0000\u0000\u00a4\u00a5\u0005\u0016\u0000"+
		"\u0000\u00a5\u00a6\u00030\u0018\u0000\u00a6\u00a7\u0005\u0017\u0000\u0000"+
		"\u00a7\u00a9\u0001\u0000\u0000\u0000\u00a8\u009e\u0001\u0000\u0000\u0000"+
		"\u00a8\u00a3\u0001\u0000\u0000\u0000\u00a9%\u0001\u0000\u0000\u0000\u00aa"+
		"\u00af\u0003(\u0014\u0000\u00ab\u00ac\u0005\u0015\u0000\u0000\u00ac\u00ae"+
		"\u0003(\u0014\u0000\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ae\u00b1\u0001"+
		"\u0000\u0000\u0000\u00af\u00ad\u0001\u0000\u0000\u0000\u00af\u00b0\u0001"+
		"\u0000\u0000\u0000\u00b0\'\u0001\u0000\u0000\u0000\u00b1\u00af\u0001\u0000"+
		"\u0000\u0000\u00b2\u00b5\u0003$\u0012\u0000\u00b3\u00b5\u0003*\u0015\u0000"+
		"\u00b4\u00b2\u0001\u0000\u0000\u0000\u00b4\u00b3\u0001\u0000\u0000\u0000"+
		"\u00b5)\u0001\u0000\u0000\u0000\u00b6\u00b9\u0003,\u0016\u0000\u00b7\u00b9"+
		"\u0003.\u0017\u0000\u00b8\u00b6\u0001\u0000\u0000\u0000\u00b8\u00b7\u0001"+
		"\u0000\u0000\u0000\u00b9+\u0001\u0000\u0000\u0000\u00ba\u00bb\u0005\u0018"+
		"\u0000\u0000\u00bb-\u0001\u0000\u0000\u0000\u00bc\u00bd\u0005\u001a\u0000"+
		"\u0000\u00bd/\u0001\u0000\u0000\u0000\u00be\u00bf\u0005\u0019\u0000\u0000"+
		"\u00bf1\u0001\u0000\u0000\u0000\f4FU\\mu\u0098\u009c\u00a8\u00af\u00b4"+
		"\u00b8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}