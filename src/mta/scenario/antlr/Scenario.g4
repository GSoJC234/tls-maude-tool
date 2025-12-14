grammar Scenario;

program: statement* EOF;

statement
    : assignment SEMI
    | functionCall SEMI
    | '(' (assignment | functionCall) ')' SEMI
    ;

assignment
    : variable ':=' expr
    ;

functionCall
    : function_name '(' argumentList? ')'
    ;

argumentList
    : argument (',' argument)*
    ;

argument:
    maude_constant_list | TID | functionCall | variable | value | nonce | other_constant
    ;

variable:
    'v' '[' NAT ']'
    ;

value:
    NAT | 'true' | 'false'
    ;

nonce:
    realNonce='nonce' '(' TID ',' NAT ')'
    | noNonce='noNonce'
    | hrrNonce='hrrNonce'
    ;

expr: functionCall;

function_name:
    'checkConnection' | 'connect' | 'accept'
    | 'assertEqual'
    | 'getContentType' | 'getRecordVersion' | 'getHandshakeMessageType' | 'getProtocolVersion' | 'getCipherSuite'
    | 'getCompressionMethod' | 'getSupportedVersion' | 'getAuthenticationAlgorithm'
    | 'getKeyShareEntry' | 'getKeyShareNamedGroup' | 'getNamedGroup' | 'getAlertDescription' | 'getCertificateContext'
    | 'getCertificate' | 'getPublicKeyFromCertificate' | 'getRandom' | 'getSessionId' | 'getHandshakeBody' | 'getAlertLevel' | 'getPskExchangeMode' | 'getTicket'
    | 'getRSAPreMasterSecret' | 'calculateMasterSecret' | 'buildEmptyKeyShareEntryList' | 'addKeyShareEntry' | 'addKeyShareExtension' | 'addHRRKeyShareExtension'
    | 'addSupportedVersionExtension' | 'addSignatureAlgorithmExtension' | 'addSupportedGroupExtension' | 'addPSKExchangeModeExtension' | 'addCHPreSharedKeyExtension' | 'addSHPreSharedKeyExtension'
    | 'addExtensionLen' | 'addHandshakeLen' | 'addSupportedSignatureAlgorithmExtension' | 'addNamedCurvesExtension'
    | 'updateContext' | 'send' | 'recv' | 'buildClientHello' | 'buildServerHello' | 'buildEncryptedExtension' | 'buildECDHEServerKeyExchange' | 'buildECDHClientKeyExchange'
    | 'buildCertificate' | 'buildCertificateVerify' | 'buildCertificateRequest' | 'buildChangeCipherSpec' | 'buildFinished' | 'buildAlert' | 'buildNewSessionTicket' | 'buildServerHelloDone'
    | 'buildRecord' | 'genCertificatePrivateKey' | 'getCertificate' | 'changeCertificate' | 'reEncryptRSAClientKeyExchange'
    | 'buildInvalidPaddingRSAClientKeyExchange' | 'changeVerifyData' | 'encrypt' | 'decrypt' | 'generateRandom' | 'close'
    | 'generateTicket' | 'generatePSK' | 'setUpPSK' | 'generateEmptyCertificate'
    ;

maude_constant_list:
    'c' '[' (maude_constant+ long_constant?) ']'
    ;

maude_constant:
      alert_constant | protocol_type_constant | protocol_version_constant | handshake_type_constant | ciphersuite_constant | certificate_type_constant
    | compression_constant | signature_and_hash_algorithm_constant | named_group_constant | psk_key_exchange_mode | msg_size_constant | curve_type_constant
    ;

alert_constant:
    alert_level | alert_description
    ;

alert_level:
    'fatal' | 'warn'
    ;

alert_description:
    'close-notify' | 'unexpected-message' | 'bad-record-mac' | 'record-overflow' | 'decompression-failure'
    | 'handshake-failure' | 'no-certificate' | 'bad-certificate' | 'unsupported-certificate' | 'certificate-revoked'
    | 'certificate-expired' | 'certificate-unknown' | 'illegal-parameter' | 'unknown-ca' | 'access-denied'
    | 'decode-error' | 'decrypt-error' | 'mta.protocol-version' | 'insufficient-security' | 'internal-error'
    | 'inappropriate-fallback' | 'user-canceled' | 'no-renegotiation' | 'unsupported-extension' | 'missing-extension'
    ;

protocol_type_constant:
    'handshake' | 'alert' | 'change-cipher-spec' | 'application-data'
    ;

protocol_version_constant:
    'TLS-11' | 'TLS-12' | 'TLS-13'
    ;

handshake_type_constant:
    'client-hello-v2' | 'server-hello-v2' | 'server-certificate-v2' | 'client-certificate-v2' | 'server-key-exchange-v2'
    | 'certificate-request-v2' | 'server-hello-done-v2' | 'client-key-exchange-v2'
    | 'client-certificate-verify-v2' | 'server-finished-v2' | 'client-finished-v2'
    | 'client-hello-v3' | 'server-hello-v3' | 'server-certificate-v3' | 'client-certificate-v3' | 'server-key-exchange-v3'
    | 'certificate-request-v3' | 'server-hello-done-v3' | 'client-key-exchange-v3' | 'server-certificate-verify-v3'
    | 'client-certificate-verify-v3' | 'server-finished-v3' | 'client-finished-v3' | 'hello-retry-request-v3'
    | 'encrypted-extension-v3' | 'new-session-ticket-v3'
    ;

ciphersuite_constant:
    'TLS-DHE-RSA-WITH-3DES-EDE-CBC-SHA' | 'TLS-DHE-RSA-WITH-AES-256-CBC-SHA' | 'TLS-DHE-RSA-WITH-AES-128-CBC-SHA'
    | 'TLS-DH-anon-WITH-AES-128-CBC-SHA' | 'TLS-RSA-WITH-AES-256-CBC-SHA' | 'TLS-RSA-WITH-AES-128-CBC-SHA'
    | 'TLS-RSA-WITH-NULL-MD5' | 'TLS-RSA-WITH-NULL-SHA' | 'TLS-PSK-WITH-AES-256-CBC-SHA' | 'TLS-PSK-WITH-AES-128-CBC-SHA256'
    | 'TLS-PSK-WITH-AES-256-CBC-SHA384' | 'TLS-PSK-WITH-AES-128-CBC-SHA' | 'TLS-PSK-WITH-NULL-SHA256' | 'TLS-PSK-WITH-NULL-SHA384'
    | 'TLS-PSK-WITH-NULL-SHA' | 'TLS-ECDHE-RSA-WITH-AES-256-CBC-SHA' | 'TLS-ECDHE-RSA-WITH-AES-128-CBC-SHA' | 'TLS-ECDHE-ECDSA-WITH-AES-256-CBC-SHA'
    | 'TLS-ECDHE-ECDSA-WITH-AES-128-CBC-SHA' | 'TLS-ECDHE-RSA-WITH-RC4-128-SHA' | 'TLS-ECDHE-ECDSA-WITH-RC4-128-SHA'
    | 'TLS-ECDHE-RSA-WITH-3DES-EDE-CBC-SHA' | 'TLS-ECDHE-ECDSA-WITH-3DES-EDE-CBC-SHA' | 'TLS-ECDHE-RSA-WITH-AES-128-CBC-SHA256'
    | 'TLS-ECDHE-ECDSA-WITH-AES-128-CBC-SHA256' | 'TLS-ECDHE-RSA-WITH-AES-256-CBC-SHA384' | 'TLS-ECDHE-ECDSA-WITH-AES-256-CBC-SHA384'
    | 'TLS-ECDHE-ECDSA-WITH-NULL-SHA' | 'TLS-ECDHE-PSK-WITH-NULL-SHA256' | 'TLS-ECDHE-PSK-WITH-AES-128-CBC-SHA256' | 'TLS-ECDH-RSA-WITH-AES-256-CBC-SHA'
    | 'TLS-ECDH-RSA-WITH-AES-128-CBC-SHA' | 'TLS-ECDH-ECDSA-WITH-AES-256-CBC-SHA' | 'TLS-ECDH-ECDSA-WITH-AES-128-CBC-SHA'
    | 'TLS-ECDH-RSA-WITH-RC4-128-SHA' | 'TLS-ECDH-ECDSA-WITH-RC4-128-SHA' | 'TLS-ECDH-RSA-WITH-3DES-EDE-CBC-SHA'
    | 'TLS-ECDH-ECDSA-WITH-3DES-EDE-CBC-SHA' | 'TLS-ECDH-RSA-WITH-AES-128-CBC-SHA256' | 'TLS-ECDH-ECDSA-WITH-AES-128-CBC-SHA256'
    | 'TLS-ECDH-RSA-WITH-AES-256-CBC-SHA384' | 'TLS-ECDH-ECDSA-WITH-AES-256-CBC-SHA384' | 'TLS-DHE-RSA-WITH-AES-256-CBC-SHA256'
    | 'TLS-DHE-RSA-WITH-AES-128-CBC-SHA256' | 'TLS-RSA-WITH-AES-256-CBC-SHA256' | 'TLS-RSA-WITH-AES-128-CBC-SHA256'
    | 'TLS-RSA-WITH-NULL-SHA256' | 'TLS-DHE-PSK-WITH-AES-128-CBC-SHA256' | 'TLS-DHE-PSK-WITH-NULL-SHA256' | 'TLS-DHE-PSK-WITH-AES-256-CBC-SHA384'
    | 'TLS-DHE-PSK-WITH-NULL-SHA384' | 'TLS-RSA-WITH-AES-128-GCM-SHA256' | 'TLS-RSA-WITH-AES-256-GCM-SHA384' | 'TLS-DHE-RSA-WITH-AES-128-GCM-SHA256'
    | 'TLS-DHE-RSA-WITH-AES-256-GCM-SHA384' | 'TLS-DH-anon-WITH-AES-256-GCM-SHA384' | 'TLS-PSK-WITH-AES-128-GCM-SHA256'
    | 'TLS-PSK-WITH-AES-256-GCM-SHA384' | 'TLS-DHE-PSK-WITH-AES-128-GCM-SHA256' | 'TLS-DHE-PSK-WITH-AES-256-GCM-SHA384'
    | 'TLS-ECDHE-ECDSA-WITH-AES-128-GCM-SHA256' | 'TLS-ECDHE-ECDSA-WITH-AES-256-GCM-SHA384' | 'TLS-ECDH-ECDSA-WITH-AES-128-GCM-SHA256'
    | 'TLS-ECDH-ECDSA-WITH-AES-256-GCM-SHA384' | 'TLS-ECDHE-RSA-WITH-AES-128-GCM-SHA256' | 'TLS-ECDHE-RSA-WITH-AES-256-GCM-SHA384'
    | 'TLS-ECDH-RSA-WITH-AES-128-GCM-SHA256' | 'TLS-ECDH-RSA-WITH-AES-256-GCM-SHA384' | 'TLS-RSA-WITH-AES-128-CCM-8' | 'TLS-RSA-WITH-AES-256-CCM-8'
    | 'TLS-ECDHE-ECDSA-WITH-AES-128-CCM' | 'TLS-ECDHE-ECDSA-WITH-AES-128-CCM-8' | 'TLS-ECDHE-ECDSA-WITH-AES-256-CCM-8' | 'TLS-PSK-WITH-AES-128-CCM'
    | 'TLS-PSK-WITH-AES-256-CCM' | 'TLS-PSK-WITH-AES-128-CCM-8' | 'TLS-PSK-WITH-AES-256-CCM-8' | 'TLS-DHE-PSK-WITH-AES-128-CCM' | 'TLS-DHE-PSK-WITH-AES-256-CCM'
    | 'TLS-AES-128-CCM-SHA256' | 'TLS-AES-128-CCM-8-SHA256' | 'TLS-AES-128-GCM-SHA256' | 'TLS-AES-256-GCM-SHA384'
    ;

compression_constant:
    'no-compression' | 'zlib-compression'
    ;

signature_and_hash_algorithm_constant:
    LBRACE signature_constant COMMA hash_constant RBRACE;

signature_constant:
    'anon' | 'rsa-pkcs' | 'rsa-pss-rsae' | 'rsa-pss-pss' | 'ecdsa' | 'dsa'
    ;

hash_constant:
    'sha' | 'sha224' | 'sha256' | 'sha384' | 'sha512' | 'md5'
    ;

named_group_constant:
    'secp160k1' | 'secp160r1' | 'secp160r2' | 'secp192k1' | 'secp192r1' | 'secp224r1' | 'secp224k1' | 'secp256r1' | 'secp256k1'
    | 'secp384r1' | 'secp521r1' | 'ffdhe2048' | 'ffdhe3072' | 'ffdhe4096' | 'ffdhe6144' | 'ffdhe8192'
    ;

psk_key_exchange_mode:
    'psk-ke' | 'psk-dhe-ke'
    ;

msg_size_constant:
    'valid' | 'smaller' | 'larger' | 'maxSize' | 'minSize'
    ;

curve_type_constant:
    'namedcurve'
    ;

certificate_type_constant:
    'rsa-sign' | 'dss-sign' | 'rsa-fixed-dh' | 'dss-fixed-dh' | 'ecdsa-sign' | 'rsa-fixed-ecdh' | 'ecdsa-fixed-ecdh'
    ;

other_constant:
    'prvkey-path' | 'cert-path'
    ;

long_constant:
    LONG (CS | PV | SA | NG) LPAREN (named_group_constant | signature_and_hash_algorithm_constant | ciphersuite_constant | protocol_version_constant) RPAREN
    ;

// LEXER RULES
LONG: 'long';
CS: 'CS';
PV: 'PV';
SA: 'SA';
NG: 'NG';

TID: [A-Za-z0-9_-]+ ( [ \t\r\n]* '.' [ \t\r\n]* [A-Za-z0-9_-]+ )+ ;
NAT: [0-9]+;
SEMI: ';';
LPAREN: '(';
RPAREN: ')';
LBRACE: '{';
RBRACE: '}';
COMMA: ',';
WS: [ \t\r\n]+ -> skip;
