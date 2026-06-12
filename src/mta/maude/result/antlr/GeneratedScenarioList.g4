grammar GeneratedScenarioList;

@header {
package mta.maude.result.antlr;
}

resultTerm
    : emptyList EOF
    | castEmptyList EOF
    | bracketTerm EOF
    | genericTerm EOF
    ;

emptyList
    : LBRACK RBRACK
    ;

castEmptyList
    : LPAREN LBRACK RBRACK RPAREN DOT LIST LBRACE SCEN RBRACE
    ;

bracketTerm
    : LBRACK termPart* RBRACK
    ;

genericTerm
    : termPart+
    ;

parenTerm
    : LPAREN termPart* RPAREN
    ;

termPart
    : bracketTerm
    | parenTerm
    | atom
    ;

atom
    : LIST
    | SCEN
    | DOT
    | LBRACE
    | RBRACE
    | RAW
    ;

LIST: 'List';
SCEN: 'Scen';
LBRACK: '[';
RBRACK: ']';
LPAREN: '(';
RPAREN: ')';
LBRACE: '{';
RBRACE: '}';
DOT: '.';
RAW: ~[()[\]{}.]+;
