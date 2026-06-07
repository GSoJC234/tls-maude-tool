grammar ScenarioSpec;

scenarioSpec
    : SCENARIO_PROPERTY COLON scenarioExpr EOF
    ;

scenarioExpr
    : scenarioChoice
    ;

scenarioChoice
    : scenarioSequence (PIPE scenarioSequence)*
    ;

scenarioSequence
    : scenarioRepeat (SEMI scenarioRepeat)*
    ;

scenarioRepeat
    : scenarioPrimary STAR*
    ;

scenarioPrimary
    : ANY_STEP
    | rawMaudeCall
    | LPAREN scenarioExpr RPAREN
    | stepExpr
    ;

stepExpr
    : stepOr
    ;

stepOr
    : stepAnd (OR stepAnd)*
    ;

stepAnd
    : stepNot (AND stepNot)*
    ;

stepNot
    : NOT stepNot
    | stepAtom
    | LPAREN stepExpr RPAREN
    ;

stepAtom
    : stateAtom
    | actionAtom
    ;

stateAtom
    : stateObject DOT identifier EQ term
    ;

stateObject
    : identifier (DOT identifier)*
    ;

actionAtom
    : identifier EQ term
    ;

term
    : dottedTerm
    ;

dottedTerm
    : primaryTerm (DOT primaryTerm)*
    ;

primaryTerm
    : rawMaudeCall
    | functionTerm
    | indexedTerm
    | listTerm
    | braceTerm
    | stringLiteral
    | identifier
    | numberLiteral
    | LPAREN term RPAREN
    ;

functionTerm
    : identifier LPAREN termList? RPAREN
    ;

indexedTerm
    : identifier LBRACK term RBRACK
    ;

listTerm
    : LBRACK termList? RBRACK
    ;

braceTerm
    : LBRACE termList? RBRACE
    ;

termList
    : term (COMMA term)*
    ;

rawMaudeCall
    : MAUDE LPAREN stringLiteral RPAREN
    ;

identifier
    : IDENTIFIER
    ;

numberLiteral
    : NUMBER
    ;

stringLiteral
    : STRING
    ;

SCENARIO_PROPERTY
    : 'ScenarioProperty'
    ;

ANY_STEP
    : 'anyStep'
    ;

MAUDE
    : 'maude'
    ;

AND
    : 'and'
    ;

OR
    : 'or'
    ;

NOT
    : 'not'
    ;

EQ
    : '='
    | '=='
    ;

COLON
    : ':'
    ;

COMMA
    : ','
    ;

DOT
    : '.'
    ;

SEMI
    : ';'
    ;

PIPE
    : '|'
    ;

STAR
    : '*'
    ;

LPAREN
    : '('
    ;

RPAREN
    : ')'
    ;

LBRACK
    : '['
    ;

RBRACK
    : ']'
    ;

LBRACE
    : '{'
    ;

RBRACE
    : '}'
    ;

NUMBER
    : [0-9]+
    ;

STRING
    : '"' (ESC | ~["\\\r\n])* '"'
    ;

IDENTIFIER
    : [A-Za-z_] [A-Za-z0-9_-]*
    ;

WS
    : [ \t\r\n]+ -> skip
    ;

LINE_COMMENT
    : '//' ~[\r\n]* -> skip
    ;

fragment ESC
    : '\\' ["\\/bfnrt]
    | '\\' 'u' HEX HEX HEX HEX
    ;

fragment HEX
    : [0-9a-fA-F]
    ;
