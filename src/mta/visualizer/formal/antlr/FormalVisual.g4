grammar FormalVisual;

// ---------------------- Parser rules ----------------------

file
  : sendStmt+ EOF
  ;

sendStmt
  : SEND LPAREN actor COMMA actor COMMA content RPAREN
  ;

actor
  : refExpr
  | IDENT
  ;

content
  : pair+                          // content는 pair들의 나열
  ;

pair
  : internalPair
  | leafPair
  ;

// ---- internal keys ----
internalPair
  : EXTENSION LPAREN (pair+ | anyText) RPAREN // certificate(nil) 허용(빈값 허용시 ? 유지)
  ;

// ---- leaf keys ----
leafPair
  : leafKey (LPAREN anyText? RPAREN)?                 // leaf는 값 내부 파싱 안 함(빈값 허용시 ? 유지)
  ;

leafKey
  : CONTENTTYPE | VERSION | RECORDLEN | HANDSHAKETYPE | HANDSHAKELEN
  | PROTOCOL | CIPHERSUITES | CIPHERSUITESLEN | RANDOM
  | SESSIONID | SESSIONIDLEN | COMPRESSION | COMPRESSIONLEN
  | SUPPORTED_VERSIONS | SUPPORTED_VERSIONS_LEN
  | SIGNATURE_ALGORITHMS | SIGNATURE_ALGORITHMS_LEN
  | KEY_SHARES | KEY_SHARES_LEN
  | SUPPORTED_GROUPS | SUPPORTED_GROUPS_LEN
  | EXTENSIONLEN | CERTIFICATELEN
  | SIGNATURE | SIGNATURE_LEN
  | CERTIFICATE_VERIFY_ALGORITHM | VERIFYDATA
  | ALERTDESC | ALERTLEV | CERTIFICATE | SERVERECDHPARAM | CERTIFICATETYPE | CERTIFICATETYPELEN
  | CERTIFICATEALGO | CERTIFICATEALGOLEN | CERTIFICATEAUTH | CERTIFICATEAUTHLEN | CLIENTECDHPARAM | CHANGECIPHERSPEC | CERTIFICATEREQUESTCONTEXT | CERTIFICATEREQUESTCONTEXTLEN
  ;

// ---------------------- Raw value (balanced) ----------------------
// anyText는 RPAREN/괄호를 만나기 전까지의 "토큰"들을 소비.
// 잡아먹는 TEXT_CHUNK 없이, 이미 정의된 토큰들만 소비하므로 기본 파싱에 영향 없음.
anyText
  : ( atomToken | parenGroup | braceGroup )*
  ;

atomToken
  : IDENT
  | STRING
  | INT
  | PLACEHOLDER
  | COMMA
  | DOT
  // 키워드 토큰들도 값으로 등장 가능하므로 모두 허용
  | SEND
  | EXTENSION | CERTIFICATE
  | CONTENTTYPE | VERSION | RECORDLEN | HANDSHAKETYPE | HANDSHAKELEN
  | PROTOCOL | CIPHERSUITES | CIPHERSUITESLEN | RANDOM
  | SESSIONID | SESSIONIDLEN | COMPRESSION | COMPRESSIONLEN
  | SUPPORTED_VERSIONS | SUPPORTED_VERSIONS_LEN
  | SIGNATURE_ALGORITHMS | SIGNATURE_ALGORITHMS_LEN
  | KEY_SHARES | KEY_SHARES_LEN
  | SUPPORTED_GROUPS | SUPPORTED_GROUPS_LEN
  | EXTENSIONLEN | CERTIFICATELEN
  | SIGNATURE | SIGNATURE_LEN
  | CERTIFICATE_VERIFY_ALGORITHM | VERIFYDATA
  | ALERTDESC | ALERTLEV | SERVERECDHPARAM | CERTIFICATETYPE | CERTIFICATETYPELEN
  | CERTIFICATEALGO | CERTIFICATEALGOLEN | CERTIFICATEAUTH | CERTIFICATEAUTHLEN | CLIENTECDHPARAM | CHANGECIPHERSPEC | CERTIFICATEREQUESTCONTEXT | CERTIFICATEREQUESTCONTEXTLEN
  ;

parenGroup
  : LPAREN anyText? RPAREN
  ;

braceGroup
  : LBRACE anyText? RBRACE
  ;

refExpr
  : IDENT DOT IDENT
  ;

// ---------------------- Lexer rules ----------------------

SEND        : 'send';

// internal keys
EXTENSION   : 'extension';
CERTIFICATE : 'certificate';

// leaf keys
CONTENTTYPE                 : 'contentType';
VERSION                     : 'version';
RECORDLEN                   : 'recordLen';
HANDSHAKETYPE               : 'handshakeType';
HANDSHAKELEN                : 'handshakeLen';
PROTOCOL                    : 'protocol';
CIPHERSUITES                : 'cipherSuites';
CIPHERSUITESLEN             : 'cipherSuitesLen';
RANDOM                      : 'random';
SESSIONID                   : 'sessionID';
SESSIONIDLEN                : 'sessionIDLen';
COMPRESSION                 : 'compression';
COMPRESSIONLEN              : 'compressionLen';
SUPPORTED_VERSIONS          : 'supported-versions';
SUPPORTED_VERSIONS_LEN      : 'supported-versions-len';
SIGNATURE_ALGORITHMS        : 'signature-algorithms';
SIGNATURE_ALGORITHMS_LEN    : 'signature-algorithms-len';
KEY_SHARES                  : 'key-shares';
KEY_SHARES_LEN              : 'key-shares-len';
SUPPORTED_GROUPS            : 'supported-groups';
SUPPORTED_GROUPS_LEN        : 'supported-groups-len';
EXTENSIONLEN                : 'extensionLen';
CERTIFICATELEN              : 'certificateLen';
SIGNATURE                   : 'signature';
SIGNATURE_LEN               : 'signature-len';
CERTIFICATE_VERIFY_ALGORITHM: 'certificate-verify-algorithm';
VERIFYDATA                  : 'verifyData';
ALERTDESC                   : 'alertDesc';
ALERTLEV                    : 'alertLev';
SERVERECDHPARAM             : 'serverECDHParam';
CERTIFICATETYPE             : 'certificateType';
CERTIFICATETYPELEN          : 'certificateTypeLen';
CERTIFICATEALGO             : 'certificateAlgo';
CERTIFICATEALGOLEN          : 'certificateAlgoLen';
CERTIFICATEAUTH             : 'certificateAuth';
CERTIFICATEAUTHLEN          : 'certificateAuthLen';
CLIENTECDHPARAM             : 'clientECDHParam';
CHANGECIPHERSPEC            : 'changeCipherSpec';
CERTIFICATEREQUESTCONTEXT   : 'certificate-request-context';
CERTIFICATEREQUESTCONTEXTLEN   : 'certificate-request-context-len';



// 구분 기호
LPAREN  : '(';
RPAREN  : ')';
LBRACE  : '{';
RBRACE  : '}';
COMMA   : ',';
DOT     : '.';

// 리터럴
STRING  : '"' ( '\\"' | '\\\\' | ~["\\] )* '"' ;
INT     : [0-9]+ ;

// @@TID@@ 같은 플레이스홀더
PLACEHOLDER
  : '@@' (~[@\r\n\t\f ])+ '@@'
  ;

// 식별자(하이픈 허용: TLS-13, client-hello-v3 등)
fragment IDSTART : [a-zA-Z_];
fragment IDCONT  : [a-zA-Z0-9_];
IDENT
  : IDSTART (IDCONT | '-')*
  ;

// 공백/개행/탭 무시
WS : [ \t\r\n]+ -> skip ;
