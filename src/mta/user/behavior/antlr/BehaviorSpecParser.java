// Generated from BehaviorSpec.g4 by ANTLR 4.13.2
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
		SKIP_KW=10, DELAY=11, VALUE=12, AND=13, OR=14, XOR=15, NOT=16, EQ=17, 
		COLON=18, COMMA=19, LPAREN=20, RPAREN=21, PARAM_REF=22, HEX=23, IDENTIFIER=24, 
		WS=25, LINE_COMMENT=26;
	public static final int
		RULE_behaviorSpec = 0, RULE_behaviorIdSection = 1, RULE_parametersSection = 2, 
		RULE_eventTypeSection = 3, RULE_conditionsSection = 4, RULE_conditionExpr = 5, 
		RULE_conditionPredicate = 6, RULE_valueAccessor = 7, RULE_modificationSection = 8, 
		RULE_modificationStatement = 9, RULE_addModification = 10, RULE_setModification = 11, 
		RULE_removeModification = 12, RULE_noCheckModification = 13, RULE_skipModification = 14, 
		RULE_delayModification = 15, RULE_modificationValue = 16, RULE_targetRef = 17, 
		RULE_operandValue = 18, RULE_parameterRef = 19, RULE_identifierValue = 20, 
		RULE_hexLiteral = 21;
	private static String[] makeRuleNames() {
		return new String[] {
			"behaviorSpec", "behaviorIdSection", "parametersSection", "eventTypeSection", 
			"conditionsSection", "conditionExpr", "conditionPredicate", "valueAccessor", 
			"modificationSection", "modificationStatement", "addModification", "setModification", 
			"removeModification", "noCheckModification", "skipModification", "delayModification", 
			"modificationValue", "targetRef", "operandValue", "parameterRef", "identifierValue", 
			"hexLiteral"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'BehaviorId'", "'Parameters'", "'EventType'", "'Conditions'", 
			"'Modification'", "'add'", "'set'", "'remove'", "'noCheck'", "'skip'", 
			"'delay'", "'value'", "'and'", "'or'", "'xor'", "'not'", "'=='", "':'", 
			"','", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "ADD", "SET", "REMOVE", "NOCHECK", 
			"SKIP_KW", "DELAY", "VALUE", "AND", "OR", "XOR", "NOT", "EQ", "COLON", 
			"COMMA", "LPAREN", "RPAREN", "PARAM_REF", "HEX", "IDENTIFIER", "WS", 
			"LINE_COMMENT"
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
			setState(44);
			behaviorIdSection();
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(45);
				parametersSection();
				}
			}

			setState(48);
			eventTypeSection();
			setState(49);
			conditionsSection();
			setState(50);
			modificationSection();
			setState(51);
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
			setState(53);
			match(T__0);
			setState(54);
			match(COLON);
			setState(55);
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
			setState(57);
			match(T__1);
			setState(58);
			match(COLON);
			setState(59);
			parameterRef();
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(60);
				match(COMMA);
				setState(61);
				parameterRef();
				}
				}
				setState(66);
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
			setState(67);
			match(T__2);
			setState(68);
			match(COLON);
			setState(69);
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
			setState(71);
			match(T__3);
			setState(72);
			match(COLON);
			setState(73);
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
			setState(79);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VALUE:
				{
				setState(76);
				conditionPredicate();
				}
				break;
			case NOT:
				{
				setState(77);
				match(NOT);
				setState(78);
				conditionExpr(2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(86);
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
					setState(81);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(82);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 57344L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(83);
					conditionExpr(2);
					}
					} 
				}
				setState(88);
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
			setState(89);
			valueAccessor();
			setState(90);
			match(EQ);
			setState(91);
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
			setState(93);
			match(VALUE);
			setState(94);
			match(LPAREN);
			setState(95);
			identifierValue();
			setState(96);
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
			setState(98);
			match(T__4);
			setState(99);
			match(COLON);
			setState(101); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(100);
				modificationStatement();
				}
				}
				setState(103); 
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
			setState(111);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
				enterOuterAlt(_localctx, 1);
				{
				setState(105);
				addModification();
				}
				break;
			case SET:
				enterOuterAlt(_localctx, 2);
				{
				setState(106);
				setModification();
				}
				break;
			case REMOVE:
				enterOuterAlt(_localctx, 3);
				{
				setState(107);
				removeModification();
				}
				break;
			case NOCHECK:
				enterOuterAlt(_localctx, 4);
				{
				setState(108);
				noCheckModification();
				}
				break;
			case SKIP_KW:
				enterOuterAlt(_localctx, 5);
				{
				setState(109);
				skipModification();
				}
				break;
			case DELAY:
				enterOuterAlt(_localctx, 6);
				{
				setState(110);
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
			setState(113);
			match(ADD);
			setState(114);
			match(LPAREN);
			setState(115);
			targetRef();
			setState(116);
			match(COMMA);
			setState(117);
			modificationValue();
			setState(118);
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
			setState(120);
			match(SET);
			setState(121);
			match(LPAREN);
			setState(122);
			targetRef();
			setState(123);
			match(COMMA);
			setState(124);
			modificationValue();
			setState(125);
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
			setState(127);
			match(REMOVE);
			setState(128);
			match(LPAREN);
			setState(129);
			targetRef();
			setState(130);
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
			setState(132);
			match(NOCHECK);
			setState(133);
			match(LPAREN);
			setState(134);
			targetRef();
			setState(135);
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
			setState(137);
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
			setState(139);
			match(DELAY);
			setState(140);
			match(LPAREN);
			setState(141);
			operandValue();
			setState(142);
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
		public OperandValueContext operandValue() {
			return getRuleContext(OperandValueContext.class,0);
		}
		public HexLiteralContext hexLiteral() {
			return getRuleContext(HexLiteralContext.class,0);
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
			setState(146);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PARAM_REF:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(144);
				operandValue();
				}
				break;
			case HEX:
				enterOuterAlt(_localctx, 2);
				{
				setState(145);
				hexLiteral();
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
			setState(150);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PARAM_REF:
				enterOuterAlt(_localctx, 1);
				{
				setState(148);
				parameterRef();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(149);
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
		enterRule(_localctx, 36, RULE_operandValue);
		try {
			setState(154);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PARAM_REF:
				enterOuterAlt(_localctx, 1);
				{
				setState(152);
				parameterRef();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(153);
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
		enterRule(_localctx, 38, RULE_parameterRef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
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
		enterRule(_localctx, 40, RULE_identifierValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
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
		enterRule(_localctx, 42, RULE_hexLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
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
		"\u0004\u0001\u001a\u00a3\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007"+
		"\u0015\u0001\u0000\u0001\u0000\u0003\u0000/\b\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0005\u0002?\b\u0002\n\u0002\f\u0002B\t\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005P\b"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005U\b\u0005\n\u0005"+
		"\f\u0005X\t\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b"+
		"\u0001\b\u0004\bf\b\b\u000b\b\f\bg\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0003\tp\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0003\u0010\u0093\b\u0010\u0001\u0011\u0001\u0011\u0003\u0011\u0097\b"+
		"\u0011\u0001\u0012\u0001\u0012\u0003\u0012\u009b\b\u0012\u0001\u0013\u0001"+
		"\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0000"+
		"\u0001\n\u0016\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u001a\u001c\u001e \"$&(*\u0000\u0001\u0001\u0000\r\u000f\u0099"+
		"\u0000,\u0001\u0000\u0000\u0000\u00025\u0001\u0000\u0000\u0000\u00049"+
		"\u0001\u0000\u0000\u0000\u0006C\u0001\u0000\u0000\u0000\bG\u0001\u0000"+
		"\u0000\u0000\nO\u0001\u0000\u0000\u0000\fY\u0001\u0000\u0000\u0000\u000e"+
		"]\u0001\u0000\u0000\u0000\u0010b\u0001\u0000\u0000\u0000\u0012o\u0001"+
		"\u0000\u0000\u0000\u0014q\u0001\u0000\u0000\u0000\u0016x\u0001\u0000\u0000"+
		"\u0000\u0018\u007f\u0001\u0000\u0000\u0000\u001a\u0084\u0001\u0000\u0000"+
		"\u0000\u001c\u0089\u0001\u0000\u0000\u0000\u001e\u008b\u0001\u0000\u0000"+
		"\u0000 \u0092\u0001\u0000\u0000\u0000\"\u0096\u0001\u0000\u0000\u0000"+
		"$\u009a\u0001\u0000\u0000\u0000&\u009c\u0001\u0000\u0000\u0000(\u009e"+
		"\u0001\u0000\u0000\u0000*\u00a0\u0001\u0000\u0000\u0000,.\u0003\u0002"+
		"\u0001\u0000-/\u0003\u0004\u0002\u0000.-\u0001\u0000\u0000\u0000./\u0001"+
		"\u0000\u0000\u0000/0\u0001\u0000\u0000\u000001\u0003\u0006\u0003\u0000"+
		"12\u0003\b\u0004\u000023\u0003\u0010\b\u000034\u0005\u0000\u0000\u0001"+
		"4\u0001\u0001\u0000\u0000\u000056\u0005\u0001\u0000\u000067\u0005\u0012"+
		"\u0000\u000078\u0003(\u0014\u00008\u0003\u0001\u0000\u0000\u00009:\u0005"+
		"\u0002\u0000\u0000:;\u0005\u0012\u0000\u0000;@\u0003&\u0013\u0000<=\u0005"+
		"\u0013\u0000\u0000=?\u0003&\u0013\u0000><\u0001\u0000\u0000\u0000?B\u0001"+
		"\u0000\u0000\u0000@>\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000"+
		"A\u0005\u0001\u0000\u0000\u0000B@\u0001\u0000\u0000\u0000CD\u0005\u0003"+
		"\u0000\u0000DE\u0005\u0012\u0000\u0000EF\u0003(\u0014\u0000F\u0007\u0001"+
		"\u0000\u0000\u0000GH\u0005\u0004\u0000\u0000HI\u0005\u0012\u0000\u0000"+
		"IJ\u0003\n\u0005\u0000J\t\u0001\u0000\u0000\u0000KL\u0006\u0005\uffff"+
		"\uffff\u0000LP\u0003\f\u0006\u0000MN\u0005\u0010\u0000\u0000NP\u0003\n"+
		"\u0005\u0002OK\u0001\u0000\u0000\u0000OM\u0001\u0000\u0000\u0000PV\u0001"+
		"\u0000\u0000\u0000QR\n\u0001\u0000\u0000RS\u0007\u0000\u0000\u0000SU\u0003"+
		"\n\u0005\u0002TQ\u0001\u0000\u0000\u0000UX\u0001\u0000\u0000\u0000VT\u0001"+
		"\u0000\u0000\u0000VW\u0001\u0000\u0000\u0000W\u000b\u0001\u0000\u0000"+
		"\u0000XV\u0001\u0000\u0000\u0000YZ\u0003\u000e\u0007\u0000Z[\u0005\u0011"+
		"\u0000\u0000[\\\u0003$\u0012\u0000\\\r\u0001\u0000\u0000\u0000]^\u0005"+
		"\f\u0000\u0000^_\u0005\u0014\u0000\u0000_`\u0003(\u0014\u0000`a\u0005"+
		"\u0015\u0000\u0000a\u000f\u0001\u0000\u0000\u0000bc\u0005\u0005\u0000"+
		"\u0000ce\u0005\u0012\u0000\u0000df\u0003\u0012\t\u0000ed\u0001\u0000\u0000"+
		"\u0000fg\u0001\u0000\u0000\u0000ge\u0001\u0000\u0000\u0000gh\u0001\u0000"+
		"\u0000\u0000h\u0011\u0001\u0000\u0000\u0000ip\u0003\u0014\n\u0000jp\u0003"+
		"\u0016\u000b\u0000kp\u0003\u0018\f\u0000lp\u0003\u001a\r\u0000mp\u0003"+
		"\u001c\u000e\u0000np\u0003\u001e\u000f\u0000oi\u0001\u0000\u0000\u0000"+
		"oj\u0001\u0000\u0000\u0000ok\u0001\u0000\u0000\u0000ol\u0001\u0000\u0000"+
		"\u0000om\u0001\u0000\u0000\u0000on\u0001\u0000\u0000\u0000p\u0013\u0001"+
		"\u0000\u0000\u0000qr\u0005\u0006\u0000\u0000rs\u0005\u0014\u0000\u0000"+
		"st\u0003\"\u0011\u0000tu\u0005\u0013\u0000\u0000uv\u0003 \u0010\u0000"+
		"vw\u0005\u0015\u0000\u0000w\u0015\u0001\u0000\u0000\u0000xy\u0005\u0007"+
		"\u0000\u0000yz\u0005\u0014\u0000\u0000z{\u0003\"\u0011\u0000{|\u0005\u0013"+
		"\u0000\u0000|}\u0003 \u0010\u0000}~\u0005\u0015\u0000\u0000~\u0017\u0001"+
		"\u0000\u0000\u0000\u007f\u0080\u0005\b\u0000\u0000\u0080\u0081\u0005\u0014"+
		"\u0000\u0000\u0081\u0082\u0003\"\u0011\u0000\u0082\u0083\u0005\u0015\u0000"+
		"\u0000\u0083\u0019\u0001\u0000\u0000\u0000\u0084\u0085\u0005\t\u0000\u0000"+
		"\u0085\u0086\u0005\u0014\u0000\u0000\u0086\u0087\u0003\"\u0011\u0000\u0087"+
		"\u0088\u0005\u0015\u0000\u0000\u0088\u001b\u0001\u0000\u0000\u0000\u0089"+
		"\u008a\u0005\n\u0000\u0000\u008a\u001d\u0001\u0000\u0000\u0000\u008b\u008c"+
		"\u0005\u000b\u0000\u0000\u008c\u008d\u0005\u0014\u0000\u0000\u008d\u008e"+
		"\u0003$\u0012\u0000\u008e\u008f\u0005\u0015\u0000\u0000\u008f\u001f\u0001"+
		"\u0000\u0000\u0000\u0090\u0093\u0003$\u0012\u0000\u0091\u0093\u0003*\u0015"+
		"\u0000\u0092\u0090\u0001\u0000\u0000\u0000\u0092\u0091\u0001\u0000\u0000"+
		"\u0000\u0093!\u0001\u0000\u0000\u0000\u0094\u0097\u0003&\u0013\u0000\u0095"+
		"\u0097\u0003(\u0014\u0000\u0096\u0094\u0001\u0000\u0000\u0000\u0096\u0095"+
		"\u0001\u0000\u0000\u0000\u0097#\u0001\u0000\u0000\u0000\u0098\u009b\u0003"+
		"&\u0013\u0000\u0099\u009b\u0003(\u0014\u0000\u009a\u0098\u0001\u0000\u0000"+
		"\u0000\u009a\u0099\u0001\u0000\u0000\u0000\u009b%\u0001\u0000\u0000\u0000"+
		"\u009c\u009d\u0005\u0016\u0000\u0000\u009d\'\u0001\u0000\u0000\u0000\u009e"+
		"\u009f\u0005\u0018\u0000\u0000\u009f)\u0001\u0000\u0000\u0000\u00a0\u00a1"+
		"\u0005\u0017\u0000\u0000\u00a1+\u0001\u0000\u0000\u0000\t.@OVgo\u0092"+
		"\u0096\u009a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}