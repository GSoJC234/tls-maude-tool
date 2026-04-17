grammar ScenarioSpec;

scenarioSpec
    : scenarioItem* scenarioPropertiesSection+ EOF
    ;

scenarioItem
    : loadStatement
    | useStatement
    | nodesSection
    | linkSection
    | constantsSection
    | statePropositionsSection
    | actionPropositionsSection
    ;

loadStatement
    : LOAD stringLiteral AS identifierValue
    ;

useStatement
    : USE stringLiteral
    ;

nodesSection
    : NODES COLON identifierValue (COMMA identifierValue)*
    ;

linkSection
    : LINKS COLON linkDeclaration (COMMA linkDeclaration)*
    ;

linkDeclaration
    :  identifierValue EQ linkedIdentifiers
    ;

linkedIdentifiers
    :  identifierValue LEFTRIGHTARROW identifierValue
    ;

constantsSection
    : CONSTANTS COLON constantDeclaration (COMMA? constantDeclaration)*
    ;

constantDeclaration
    : constantRef EQ constantSet
    ;

constantSet
    : LBRACE constantValue (COMMA constantValue)* RBRACE
    ;

constantValue
    : identifierValue
    | hexLiteral
    ;

statePropositionsSection
    : STATE_PROPOSITIONS COLON statePropositionDeclaration (COMMA? statePropositionDeclaration)*
    ;

statePropositionDeclaration
    : identifierValue EQ statePropositionExpr
    ;

statePropositionExpr
    : statePropositionTerm
    | NOT statePropositionExpr
    | LPAREN statePropositionExpr RPAREN
    | statePropositionExpr ( OR | XOR | AND) statePropositionExpr
    ;

statePropositionTerm
    : identifierValue PIPE LPAREN conditionExpr RPAREN
    ;

actionPropositionsSection
    : ACTION_PROPOSITIONS COLON actionPropositionDeclaration (COMMA? actionPropositionDeclaration)*
    ;

actionPropositionDeclaration
    : identifierValue EQ actionInvocation
    ;

actionInvocation
    : identifierValue LPAREN actionArgument (COMMA actionArgument)* RPAREN
    | identifierValue
    ;

actionArgument
    : oneOfExpr
    | actionValue
    ;

oneOfExpr
    : ONEOF LPAREN constantRef RPAREN
    ;

actionValue
    : identifierValue
    | hexLiteral
    ;

scenarioPropertiesSection
    : SCENARIO_PROPERTIES COLON scenarioPropertyDeclaration (COMMA? scenarioPropertyDeclaration)*
    ;

scenarioPropertyDeclaration
    : identifierValue LBRACK nodeBinding (COMMA nodeBinding)* RBRACK linkQualifier COLON propertyRelation
    ;

linkQualifier
    : VIA identifierValue (COMMA identifierValue)*
    ;

nodeBinding
    : identifierValue BIND identifierValue
    ;

propertyRelation
    : identifierValue
    | NOT propertyRelation
    | LPAREN propertyRelation RPAREN
    | propertyRelation (ZERO_OR_MORE | ONE_OR_MORE)
    | propertyRelation (STEP_ZERO_OR_MORE | STEP_ONE_OR_MORE | STEP_ONE | PIPE | OR) propertyRelation
    ;

propertyReferenceValue
    : constantRef
    | identifierValue
    | stringLiteral
    ;

conditionExpr
    : conditionPredicate
    | NOT conditionExpr
    | LPAREN conditionExpr RPAREN
    | conditionExpr (OR | XOR | AND) conditionExpr
    ;

conditionPredicate
    : valueAccessor EQ2 conditionOperand
    ;

valueAccessor
    : VALUE LPAREN identifierValue RPAREN
    ;

conditionOperand
    : constantRef
    | identifierValue
    | stringLiteral
    ;

stringLiteral
    : STRING
    ;

constantRef
    : CONSTANT_REF
    ;

identifierValue
    : IDENTIFIER
    ;

hexLiteral
    : HEX
    ;

LOAD
    : 'load'
    ;

USE
    : 'use'
    ;

AS
    : 'as'
    ;

NODES
    : 'Nodes'
    ;

LINKS
    : 'Links'
    ;

CONSTANTS
    : 'Constants'
    ;

STATE_PROPOSITIONS
    : 'StatePropositions'
    ;

ACTION_PROPOSITIONS
    : 'ActionPropositions'
    ;

SCENARIO_PROPERTY
    : 'ScenarioProperty'
    ;

SCENARIO_PROPERTIES
    : 'ScenarioProperties'
    ;

VALUE
    : 'value'
    ;

ONEOF
    : 'oneOf'
    ;

LEFTRIGHTARROW
    : '<->'
    ;

VIA
    : 'via'
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

STEP_ZERO_OR_MORE
    : '->*'
    ;

STEP_ONE_OR_MORE
    : '->+'
    ;

STEP_ONE
    : '->'
    ;

ZERO_OR_MORE
    : '*'
    ;

ONE_OR_MORE
    : '+'
    ;

BIND
    : '<-'
    ;

PIPE
    : '|'
    ;

EQ2
    : '=='
    ;

EQ
    : '='
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

CONSTANT_REF
    : '#' [A-Za-z_] [A-Za-z0-9_-]*
    ;

HEX
    : '0x' [0-9a-fA-F]+
    ;

IDENTIFIER
    : [A-Za-z_] [A-Za-z0-9_-]*
    ;

STRING
    : '"' (~["\\\r\n] | '\\' .)* '"'
    ;

WS
    : [ \t\r\n]+ -> skip
    ;

LINE_COMMENT
    : '//' ~[\r\n]* -> skip
    ;
