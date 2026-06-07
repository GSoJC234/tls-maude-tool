grammar TLSProfile;

profiles
    : TLS_PROFILES COLON profileBlock+ EOF
    ;

profileBlock
    : profileName COLON profileEntry*
    ;

profileName
    : TESTER
    | TARGET
    ;

profileEntry
    : identifier COLON profileValue
    ;

profileValue
    : listTerm
    | term
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

TLS_PROFILES
    : 'TLSProfiles'
    ;

TESTER
    : 'tester'
    ;

TARGET
    : 'target'
    ;

MAUDE
    : 'maude'
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
