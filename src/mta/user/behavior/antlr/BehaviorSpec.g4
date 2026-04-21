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
    : 'Parameters' COLON identifierValue (COMMA identifierValue)*
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
    : fieldValueAccessor EQ valueAccessor
    | valueAccessor EQ fieldValueAccessor
    ;

fieldValueAccessor
    : FIELD_VALUE LPAREN identifierValue RPAREN
    ;

valueAccessor
    : VALUE LPAREN identifierValue RPAREN
    ;

modificationSection
    : 'Modification' COLON modificationStatement+
    ;

modificationStatement
    : setModification
    | deleteModification
    | noCheckModification
    | skipModification
    | delayModification
    ;

setModification
    : SET LPAREN identifierValue COMMA modificationValue RPAREN
    ;

deleteModification
    : DELETE LPAREN identifierValue RPAREN
    ;

noCheckModification
    : NOCHECK LPAREN identifierValue RPAREN
    ;

skipModification
    : SKIP_KW
    ;

delayModification
    : DELAY LPAREN identifierValue RPAREN
    ;

modificationValue
    : identifierValue
    | hexLiteral
    ;

identifierValue
    : IDENTIFIER
    ;

hexLiteral
    : HEX
    ;

SET
    : 'set'
    ;

DELETE
    : 'delete'
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

VALUE
    : 'value'
    ;

FIELD_VALUE
    : 'fieldValue'
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
    | '='
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
