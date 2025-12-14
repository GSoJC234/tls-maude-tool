grammar RealVisual;

// ------------- Parser rules ----------------

file
  : statements+
  ;

statements
  : sendStmt | (recvStmt assertion*);

sendStmt
  : SENDINGMSG LPAREN alias RPAREN COLON layers
  ;

recvStmt
  : RECEIVEDMSG LPAREN alias RPAREN COLON layers
  ;

layers
  : layer+
  ;

layer
  : LAYERTYPE COLON (MESSAGETYPE | RECORDTYPE) message
  ;

message
  : content*
  ;

content
  : EXTENSION COLON (content+ | NULL)
  | key COLON value
  ;

key
  : HANDSHAKETYPE | HANDSHAKELEN | PROTOCOL | RANDOM | SESSIONID | SESSIONIDLEN | CIPHERSUITES | CIPHERSUITESLEN
  | COMPRESSION | COMPRESSIONLEN | EXTENSIONLEN | PSKKEYEXCHANGEMODES | PSKKEYEXCHANGEMODESLEN | KEYSHARES | KEYSHARESLEN | CURVETYPE
  | SUPPORTEDVERSIONS | SUPPORTEDVERSIONSLEN | ELLIPTICCURVE | ELLIPTICCURVELEN | SIGNATUREALGORITHM | SIGNATUREALGORITHMLEN
  | CERTIFICATEENTRY | CERTIFICATEENTRYLEN | CERTIFICATEVERIFYALGORITHM | SIGNATURE | SIGNATURELEN | VERIFYDATA | PUBLICKEYX | PUBLICKEYY | PUBLICKEY
  | ALERTLEV | ALERTDESC | CERTIFICATETYPE | CERTIFICATETYPELNE | DISTINGUISHEDNAME | DISTINGUISHEDNAMELEN | CHANGECIPHERSPECTYPE
  | CONTENTTYPE | VERSION | RECORDLEN | SERVERHELLODONE | ECPOINTFORMAT | ECPOINTFORMATLEN | CERTIFICATEREQUESTCONTEXT | CERTIFICATEREQUESTCONTEXTLEN
  ;

value
  : HEXVALUES+
  | NULL
  ;

alias
  :  IDENT DOT IDENT
  ;

bool
  : (TRUE | FALSE)
  ;

assertion
  : ASSERTION FAILS LPAREN key RPAREN COLON EXPECTED RIGHTARROW value COMMA ACTUAL RIGHTARROW value?
  ;

// ------------- Lexer rules ----------------
RECEIVEDMSG: 'Received messages';
SENDINGMSG: 'Sending messages';
LAYERTYPE: 'LayerType';
MESSAGETYPE: 'MESSAGE';
RECORDTYPE: 'RECORD';
TRUE: 'true';
FALSE: 'false';
NULL: 'null';
EXPECTED: 'expected';
FAILS: 'fails';
ASSERTION: 'Assertion';
ACTUAL: 'actual';

// Key
HANDSHAKETYPE: 'handshakeType';
HANDSHAKELEN: 'handshakeLen';
PROTOCOL: 'mta.protocol';
RANDOM: 'random';
SESSIONID: 'sessionID';
SESSIONIDLEN: 'sessionIDLen';
CIPHERSUITES: 'cipherSuites';
CIPHERSUITESLEN: 'cipherSuitesLen';
COMPRESSION: 'compression';
COMPRESSIONLEN: 'compressionLen';
EXTENSION: 'extension';
EXTENSIONLEN: 'extensionLen';
PSKKEYEXCHANGEMODES: 'psk-key-exchange-modes';
PSKKEYEXCHANGEMODESLEN: 'psk-key-exchange-modes-len';
KEYSHARES: 'key-shares';
KEYSHARESLEN: 'key-shares-len';
SUPPORTEDVERSIONS: 'supported-versions';
SUPPORTEDVERSIONSLEN: 'supported-versions-len';
ELLIPTICCURVE: 'elliptic-curves';
ELLIPTICCURVELEN: 'elliptic-curves-len';
SIGNATUREALGORITHM: 'signature-algorithms';
SIGNATUREALGORITHMLEN: 'signature-algorithms-len';
CERTIFICATEENTRY: 'certificateEntry';
CERTIFICATEENTRYLEN: 'certificateEntryLen';
CERTIFICATEVERIFYALGORITHM: 'certificate-verify-algorithm';
SIGNATURE: 'signature';
SIGNATURELEN: 'signature-len';
VERIFYDATA: 'verifyData';
ALERTLEV: 'alertLev';
ALERTDESC: 'alertDesc';
CONTENTTYPE: 'contentType';
VERSION: 'version';
RECORDLEN: 'recordLen';
PUBLICKEY: 'publicKey';
PUBLICKEYX: 'publicKeyX';
PUBLICKEYY: 'publicKeyY';
CURVETYPE: 'curveType';
CERTIFICATETYPE: 'certificateTypes';
CERTIFICATETYPELNE: 'certificateTypesLen';
DISTINGUISHEDNAME: 'distinguishedNames';
DISTINGUISHEDNAMELEN: 'distinguishedNamesLen';
CHANGECIPHERSPECTYPE: 'changeCipherSpecType';
SERVERHELLODONE: 'ServerHelloDone';
ECPOINTFORMAT: 'ecPointFormat';
ECPOINTFORMATLEN: 'ecPointFormat-len';
CERTIFICATEREQUESTCONTEXT   : 'certificate-request-context';
CERTIFICATEREQUESTCONTEXTLEN   : 'certificate-request-context-len';

LPAREN: '(';
RPAREN: ')';
COLON: ':';
DOT: '.';
COMMA: ',';
RIGHTARROW: '->';

fragment HEX_DIGITS: ('0'..'9' | 'a'..'f' | 'A'..'F')+;
HEXVALUES : HEX_DIGITS;

fragment IDSTART : [a-zA-Z_];
fragment IDCONT  : [a-zA-Z0-9_];
IDENT
  : IDSTART (IDCONT | '-')*
  ;



WS : [ \t\r\n]+ -> skip ;