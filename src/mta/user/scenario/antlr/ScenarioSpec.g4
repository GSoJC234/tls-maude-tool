grammar ScenarioSpec;

scenarioSpec
    : declaration+ EOF
    ;

declaration
    : statePropositionDeclaration
    | actionPropositionDeclaration
    | scenarioPropertyDeclaration
    ;

statePropositionDeclaration
    : STATE_PROPOSITION LBRACK identifier RBRACK COLON stateExpr
    ;

actionPropositionDeclaration
    : ACTION_PROPOSITION LBRACK identifier RBRACK COLON actionExpr
    ;

scenarioPropertyDeclaration
    : SCENARIO_PROPERTY (LBRACK identifier RBRACK)? COLON scenarioExpr
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

propositionRef
    : identifier
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
    | propositionRef
    ;

stateExpr
    : stateOr
    ;

stateOr
    : stateAnd (OR stateAnd)*
    ;

stateAnd
    : stateNot (AND stateNot)*
    ;

stateNot
    : NOT stateNot
    | stateAtom
    | LPAREN stateExpr RPAREN
    ;

actionExpr
    : actionOr
    ;

actionOr
    : actionAnd (OR actionAnd)*
    ;

actionAnd
    : actionNot (AND actionNot)*
    ;

actionNot
    : NOT actionNot
    | actionAtom
    | LPAREN actionExpr RPAREN
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
    | ANY_STEP
    ;

numberLiteral
    : NUMBER
    ;

stringLiteral
    : STRING
    ;

STATE_PROPOSITION
    : 'StateProposition'
    ;

ACTION_PROPOSITION
    : 'ActionProposition'
    ;

SCENARIO_PROPERTY
    : 'ScenarioProperty'
    ;

ANY_STEP
    : 'anyStep'
    | 'any'
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
