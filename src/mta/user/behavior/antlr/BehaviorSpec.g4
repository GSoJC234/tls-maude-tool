grammar BehaviorSpec;

behaviorDeviationSpecification
    : BEHAVIOR_DEVIATION_SPECIFICATION COLON behaviorSpec* EOF
    ;

behaviorSpec
    : DASH? behaviorIdSection parametersSection? conditionsSection modificationsSection parameterInstancesSection?
    ;

behaviorIdSection
    : BEHAVIOR_ID COLON identifier
    ;

parametersSection
    : PARAMETERS COLON parameterDeclaration (COMMA parameterDeclaration)*
    ;

parameterDeclaration
    : parameterRef COLON typeExpression
    | parameterName
    ;

typeExpression
    : getTypeCall
    | term
    ;

getTypeCall
    : GET_TYPE LPAREN term RPAREN
    ;

conditionsSection
    : CONDITIONS COLON actionExpr
    ;

modificationsSection
    : MODIFICATIONS COLON modificationExpr
    ;

parameterInstancesSection
    : PARAMETER_INSTANCES COLON parameterInstance*
    ;

parameterInstance
    : DASH? parameterBinding (COMMA? parameterBinding)*
    ;

parameterBinding
    : (parameterName | parameterRef) (COLON | EQ) term
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
    | rawMaudeCall
    | actionAtom
    | LPAREN actionExpr RPAREN
    ;

actionAtom
    : identifier EQ term
    ;

modificationExpr
    : modificationCall (AND modificationCall)*
    ;

modificationCall
    : rawMaudeCall
    | SETM LPAREN term COMMA term RPAREN
    | SETF LPAREN term COMMA term RPAREN
    | ADD LPAREN term COMMA term RPAREN
    | REMOVE LPAREN term RPAREN
    | NOCHECK LPAREN term RPAREN
    | SKIP_KW LPAREN? RPAREN?
    | DELAY LPAREN term RPAREN
    ;

term
    : dottedTerm
    ;

dottedTerm
    : primaryTerm (DOT primaryTerm)*
    ;

primaryTerm
    : parameterRef
    | rawMaudeCall
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

parameterRef
    : PARAMETER_REF
    ;

parameterName
    : IDENTIFIER
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

BEHAVIOR_DEVIATION_SPECIFICATION
    : 'BehaviorDeviationSpecification'
    ;

BEHAVIOR_ID
    : 'BehaviorId'
    ;

PARAMETERS
    : 'Parameters'
    ;

CONDITIONS
    : 'Conditions'
    ;

MODIFICATIONS
    : 'Modifications'
    ;

PARAMETER_INSTANCES
    : 'ParameterInstances'
    ;

GET_TYPE
    : '#getType'
    ;

SETM
    : 'setM'
    ;

SETF
    : 'setF'
    ;

ADD
    : 'add'
    ;

REMOVE
    : 'remove'
    ;

NOCHECK
    : 'noCheck'
    ;

SKIP_KW
    : 'skip'
    ;

DELAY
    : 'delay'
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

DASH
    : '-'
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

PARAMETER_REF
    : '$' [A-Za-z_] [A-Za-z0-9_-]*
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
