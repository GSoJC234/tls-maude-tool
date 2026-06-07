// Generated from /Users/gsojc234/git/maude-tls-attacker/src/mta/user/profile/antlr/TLSProfile.g4 by ANTLR 4.13.2
package mta.user.profile.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class TLSProfileLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TLS_PROFILES=1, TESTER=2, TARGET=3, MAUDE=4, COLON=5, COMMA=6, DOT=7, 
		LPAREN=8, RPAREN=9, LBRACK=10, RBRACK=11, LBRACE=12, RBRACE=13, NUMBER=14, 
		STRING=15, IDENTIFIER=16, WS=17, LINE_COMMENT=18;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"TLS_PROFILES", "TESTER", "TARGET", "MAUDE", "COLON", "COMMA", "DOT", 
			"LPAREN", "RPAREN", "LBRACK", "RBRACK", "LBRACE", "RBRACE", "NUMBER", 
			"STRING", "IDENTIFIER", "WS", "LINE_COMMENT", "ESC", "HEX"
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


	public TLSProfileLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "TLSProfile.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0012\u0090\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0004\r]\b\r\u000b"+
		"\r\f\r^\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000ed\b\u000e\n\u000e"+
		"\f\u000eg\t\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0005"+
		"\u000fm\b\u000f\n\u000f\f\u000fp\t\u000f\u0001\u0010\u0004\u0010s\b\u0010"+
		"\u000b\u0010\f\u0010t\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0005\u0011}\b\u0011\n\u0011\f\u0011\u0080\t"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003"+
		"\u0012\u008d\b\u0012\u0001\u0013\u0001\u0013\u0000\u0000\u0014\u0001\u0001"+
		"\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f"+
		"\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f"+
		"\u001f\u0010!\u0011#\u0012%\u0000\'\u0000\u0001\u0000\b\u0001\u000009"+
		"\u0004\u0000\n\n\r\r\"\"\\\\\u0003\u0000AZ__az\u0005\u0000--09AZ__az\u0003"+
		"\u0000\t\n\r\r  \u0002\u0000\n\n\r\r\b\u0000\"\"//\\\\bbffnnrrtt\u0003"+
		"\u000009AFaf\u0094\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001"+
		"\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001"+
		"\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000"+
		"\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000"+
		"\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000"+
		"\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000"+
		"\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000"+
		"\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000"+
		"\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0001"+
		")\u0001\u0000\u0000\u0000\u00035\u0001\u0000\u0000\u0000\u0005<\u0001"+
		"\u0000\u0000\u0000\u0007C\u0001\u0000\u0000\u0000\tI\u0001\u0000\u0000"+
		"\u0000\u000bK\u0001\u0000\u0000\u0000\rM\u0001\u0000\u0000\u0000\u000f"+
		"O\u0001\u0000\u0000\u0000\u0011Q\u0001\u0000\u0000\u0000\u0013S\u0001"+
		"\u0000\u0000\u0000\u0015U\u0001\u0000\u0000\u0000\u0017W\u0001\u0000\u0000"+
		"\u0000\u0019Y\u0001\u0000\u0000\u0000\u001b\\\u0001\u0000\u0000\u0000"+
		"\u001d`\u0001\u0000\u0000\u0000\u001fj\u0001\u0000\u0000\u0000!r\u0001"+
		"\u0000\u0000\u0000#x\u0001\u0000\u0000\u0000%\u008c\u0001\u0000\u0000"+
		"\u0000\'\u008e\u0001\u0000\u0000\u0000)*\u0005T\u0000\u0000*+\u0005L\u0000"+
		"\u0000+,\u0005S\u0000\u0000,-\u0005P\u0000\u0000-.\u0005r\u0000\u0000"+
		"./\u0005o\u0000\u0000/0\u0005f\u0000\u000001\u0005i\u0000\u000012\u0005"+
		"l\u0000\u000023\u0005e\u0000\u000034\u0005s\u0000\u00004\u0002\u0001\u0000"+
		"\u0000\u000056\u0005t\u0000\u000067\u0005e\u0000\u000078\u0005s\u0000"+
		"\u000089\u0005t\u0000\u00009:\u0005e\u0000\u0000:;\u0005r\u0000\u0000"+
		";\u0004\u0001\u0000\u0000\u0000<=\u0005t\u0000\u0000=>\u0005a\u0000\u0000"+
		">?\u0005r\u0000\u0000?@\u0005g\u0000\u0000@A\u0005e\u0000\u0000AB\u0005"+
		"t\u0000\u0000B\u0006\u0001\u0000\u0000\u0000CD\u0005m\u0000\u0000DE\u0005"+
		"a\u0000\u0000EF\u0005u\u0000\u0000FG\u0005d\u0000\u0000GH\u0005e\u0000"+
		"\u0000H\b\u0001\u0000\u0000\u0000IJ\u0005:\u0000\u0000J\n\u0001\u0000"+
		"\u0000\u0000KL\u0005,\u0000\u0000L\f\u0001\u0000\u0000\u0000MN\u0005."+
		"\u0000\u0000N\u000e\u0001\u0000\u0000\u0000OP\u0005(\u0000\u0000P\u0010"+
		"\u0001\u0000\u0000\u0000QR\u0005)\u0000\u0000R\u0012\u0001\u0000\u0000"+
		"\u0000ST\u0005[\u0000\u0000T\u0014\u0001\u0000\u0000\u0000UV\u0005]\u0000"+
		"\u0000V\u0016\u0001\u0000\u0000\u0000WX\u0005{\u0000\u0000X\u0018\u0001"+
		"\u0000\u0000\u0000YZ\u0005}\u0000\u0000Z\u001a\u0001\u0000\u0000\u0000"+
		"[]\u0007\u0000\u0000\u0000\\[\u0001\u0000\u0000\u0000]^\u0001\u0000\u0000"+
		"\u0000^\\\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000\u0000_\u001c\u0001"+
		"\u0000\u0000\u0000`e\u0005\"\u0000\u0000ad\u0003%\u0012\u0000bd\b\u0001"+
		"\u0000\u0000ca\u0001\u0000\u0000\u0000cb\u0001\u0000\u0000\u0000dg\u0001"+
		"\u0000\u0000\u0000ec\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000"+
		"fh\u0001\u0000\u0000\u0000ge\u0001\u0000\u0000\u0000hi\u0005\"\u0000\u0000"+
		"i\u001e\u0001\u0000\u0000\u0000jn\u0007\u0002\u0000\u0000km\u0007\u0003"+
		"\u0000\u0000lk\u0001\u0000\u0000\u0000mp\u0001\u0000\u0000\u0000nl\u0001"+
		"\u0000\u0000\u0000no\u0001\u0000\u0000\u0000o \u0001\u0000\u0000\u0000"+
		"pn\u0001\u0000\u0000\u0000qs\u0007\u0004\u0000\u0000rq\u0001\u0000\u0000"+
		"\u0000st\u0001\u0000\u0000\u0000tr\u0001\u0000\u0000\u0000tu\u0001\u0000"+
		"\u0000\u0000uv\u0001\u0000\u0000\u0000vw\u0006\u0010\u0000\u0000w\"\u0001"+
		"\u0000\u0000\u0000xy\u0005/\u0000\u0000yz\u0005/\u0000\u0000z~\u0001\u0000"+
		"\u0000\u0000{}\b\u0005\u0000\u0000|{\u0001\u0000\u0000\u0000}\u0080\u0001"+
		"\u0000\u0000\u0000~|\u0001\u0000\u0000\u0000~\u007f\u0001\u0000\u0000"+
		"\u0000\u007f\u0081\u0001\u0000\u0000\u0000\u0080~\u0001\u0000\u0000\u0000"+
		"\u0081\u0082\u0006\u0011\u0000\u0000\u0082$\u0001\u0000\u0000\u0000\u0083"+
		"\u0084\u0005\\\u0000\u0000\u0084\u008d\u0007\u0006\u0000\u0000\u0085\u0086"+
		"\u0005\\\u0000\u0000\u0086\u0087\u0005u\u0000\u0000\u0087\u0088\u0003"+
		"\'\u0013\u0000\u0088\u0089\u0003\'\u0013\u0000\u0089\u008a\u0003\'\u0013"+
		"\u0000\u008a\u008b\u0003\'\u0013\u0000\u008b\u008d\u0001\u0000\u0000\u0000"+
		"\u008c\u0083\u0001\u0000\u0000\u0000\u008c\u0085\u0001\u0000\u0000\u0000"+
		"\u008d&\u0001\u0000\u0000\u0000\u008e\u008f\u0007\u0007\u0000\u0000\u008f"+
		"(\u0001\u0000\u0000\u0000\b\u0000^cent~\u008c\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}