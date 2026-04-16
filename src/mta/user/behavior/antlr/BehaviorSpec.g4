grammar BehaviorSpec;

behaviorSpec
    : behaviorIdSection
      parametersSection?
      eventTypeSection
      conditionsSection
      modificationSection
      EOF
    ;

behaviorIdSection
    : 'BehaviorId' COLON identifierValue
    ;

parametersSection
    : 'Parameters' COLON parameterRef (COMMA parameterRef)*
    ;

eventTypeSection
    : 'EventType' COLON identifierValue
    ;

conditionsSection
    : 'Conditions' COLON conditionExpr
    ;

conditionExpr
    : conditionPredicate
    | NOT conditionExpr 
    | conditionExpr (OR | XOR | AND) conditionExpr
    ;

conditionPredicate
    : valueAccessor EQ operandValue
    ;

valueAccessor
    : VALUE LPAREN identifierValue RPAREN
    ;

modificationSection
    : 'Modification' COLON modificationStatement+
    ;

modificationStatement
    : addModification
    | setModification
    | removeModification
    | noCheckModification
    | skipModification
    | delayModification
    ;

addModification
    : ADD LPAREN targetRef COMMA modificationValue RPAREN
    ;

setModification
    : SET LPAREN targetRef COMMA modificationValue RPAREN
    ;

removeModification
    : REMOVE LPAREN targetRef RPAREN
    ;

noCheckModification
    : NOCHECK LPAREN targetRef RPAREN
    ;

skipModification
    : SKIP_KW
    ;

delayModification
    : DELAY LPAREN operandValue RPAREN
    ;

modificationValue
    : functionCall
    | operandValue
    ;

targetRef
    : parameterRef
    | identifierValue
    ;

functionCall
    : ONEOF LPAREN parameterRef RPAREN
    | BYTES LPAREN hexLiteral RPAREN
    ;

argumentList
    : argumentValue (COMMA argumentValue)*
    ;

argumentValue
    : functionCall
    | operandValue
    ;

operandValue
    : parameterRef
    | identifierValue
    ;

parameterRef
    : PARAM_REF
    ;

identifierValue
    : IDENTIFIER
    ;

hexLiteral
    : HEX
    ;

ADD
    : 'add'
    ;

SET
    : 'set'
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

ONEOF
    : 'oneOf'
    ;

BYTES
    : 'bytes'
    ;

VALUE
    : 'value'
    ;

AND
    : 'and'
    ;

OR
    : 'or'
    ;

XOR
    : 'xor'
    ;

NOT
    : 'not'
    ;

EQ
    : '=='
    ;

COLON
    : ':'
    ;

COMMA
    : ','
    ;

LPAREN
    : '('
    ;

RPAREN
    : ')'
    ;

PARAM_REF
    : '$' [A-Za-z_] [A-Za-z0-9_-]*
    ;

HEX
    : '0x' [0-9a-fA-F]+
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
