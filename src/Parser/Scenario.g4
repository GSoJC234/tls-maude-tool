grammar Scenario;

scenario:
title
node_num
node_entry
'MessageSequence' COL LP message* RP
;

title: 'Title' COL IDENTIFIER
;
node_num: 'NodeNum' COL NUM
;
node_entry: 'NodeId' COL LP node_id (',' node_id)* RP
;
node_id: IDENTIFIER
;
message : LP? NUM '.' '[' message_title ']' sender '->' receiver COL message_content* RP?
;
sender: IDENTIFIER
;
receiver: IDENTIFIER
;
message_title : IDENTIFIER
;
message_content : (content_type | version | record_len | handshake_type | handshake_len | protocol_version | ciphersuite | ciphersuite_len |
                  random | session_id | session_id_len | compression | compression_len | extension | signature_and_hash_algorithm |
                  named_curve | certificate | certificate_len | encrypted_message | signature | certificate_type | certificate_type_len |
                  certificate_algo | certificate_algo_len | certificate_auth | certificate_auth_len | key_param | hash_content | change_cipher_spec | verify_data |
                  aead_explicit_nonce | aead_data | aead_nonce | iv | cipher_text)
;
content_type : 'contentType' LP CONTENT_TYPE RP
;
version: 'version' LP TLS_VERSION RP
;
record_len : 'recordLen' LP MSG_SIZE RP
;
handshake_type : 'handshakeType' LP HANDSHAKE_TYPE RP
;
handshake_len : 'handshakeLen' LP MSG_SIZE RP
;
protocol_version : 'protocol' LP TLS_VERSION RP
;
ciphersuite : 'cipherSuites' LP CIPHER_SUITE (',' CIPHER_SUITE)* RP
;
ciphersuite_len: 'cipherSuitesLen' LP MSG_SIZE RP
;
random : 'random' LP nonce RP
;
nonce: 'nonce' LP IDENTIFIER ',' NUM RP | 'noNonce'
;
session_id : 'sessionID' LP nonce RP
;
session_id_len : 'sessionIDLen' LP MSG_SIZE RP
;
compression: 'compression' LP COMPRESSION_METHOD (',' COMPRESSION_METHOD)* RP
;
compression_len: 'compressionLen' LP MSG_SIZE RP
;
extension: 'extension' LP message_content+ RP
;
signature_and_hash_algorithm: 'signatureAndHashAlgorithm' LP '{' SIGNATURE_ALGO ',' HASH_ALGO '}' (',' '{' SIGNATURE_ALGO ',' HASH_ALGO '}')* RP
;
named_curve: 'namedCurve' LP NAMED_CURVE (',' NAMED_CURVE)* RP
;
certificate: 'certificate' LP certificate_content (',' certificate_content)* RP
;
certificate_content: '{' '{' SIGNATURE_ALGO ',' HASH_ALGO '}' ',' IDENTIFIER ',' key ',' SIGNATURE_ALGO ',' IDENTIFIER ',' encrypted_message '}'
;
certificate_len: 'certificateLen' LP MSG_SIZE RP
;
encrypted_message: 'encrypt' LP key ',' message_content+ RP
;
key: public_key | private_key | symmetric_key
;
public_key: 'pubKey' LP (KEY_EXCHANGE_ALGORITHM | SIGNATURE_ALGO) ',' nonce ')'
;
private_key: 'prvKey' LP (KEY_EXCHANGE_ALGORITHM | SIGNATURE_ALGO) ',' nonce ')'
;
symmetric_key: 'symKey' LP (ENCRYPTION_ALGORITHM) ',' master_secret ',' nonce ',' nonce ')'
;
mac_key: 'macKey' LP (HASH_ALGO | HASH_ALGO ',' master_secret ',' nonce ',' nonce) RP
;
signature: 'signature' LP encrypted_message RP
;
certificate_type: 'certificateType' LP CERTIFICATE_TYPE (',' CERTIFICATE_TYPE)* RP
;
certificate_type_len: 'certificateTypeLen' LP MSG_SIZE RP
;
certificate_algo: 'certificateAlgo' LP '{' SIGNATURE_ALGO ',' HASH_ALGO '}' (',' '{' SIGNATURE_ALGO ',' HASH_ALGO '}')* RP
;
certificate_algo_len: 'certificateAlgoLen' LP MSG_SIZE RP
;
certificate_auth: 'certificateAuth' LP IDENTIFIER (',' IDENTIFIER)* RP
;
certificate_auth_len: 'certificateAuthLen' LP MSG_SIZE RP
;
key_param: 'keyParam' LP (dheParam | ecdheParam | rsaParam) RP
;
dheParam: 'dheParam' LP ( nonce ',' nonce ',' key | key) RP
;
ecdheParam: 'ecdheParam' LP (NAMED_CURVE ',' 'namedcurve' ',' key | key) RP
;
rsaParam: 'rsaParam' LP nonce RP
;
hash_content: 'hashContent' LP hash RP
;
hash: 'hash' LP HASH_ALGO ',' message_content+ RP
;
change_cipher_spec: 'changeCipherSpec'
;
verify_data: 'verifyData' LP master_secret ','  hash RP
;
master_secret: 'ms' LP pre_master_secret ',' nonce ',' nonce RP
;
pre_master_secret: 'pms' LP KEY_EXCHANGE_ALGORITHM ',' key ',' key RP |
                   'pms' LP nonce RP
;
aead_explicit_nonce: 'aeadExplicitNonce' LP nonce RP
;
aead_data: 'aeadData' LP NUM ',' COMPRESSION_METHOD RP
;
aead_nonce: 'aeadNonce' LP nonce ',' iv RP
;
cipher_text: 'cipherText' LP encrypted_message RP
;
iv: 'iv' LP master_secret ',' nonce ',' nonce RP
;


CONTENT_TYPE: ('handshake' | 'change-cipher-spec' | 'alert' | 'application-data')
;
MSG_SIZE: ('valid' | 'invalid')
;
HANDSHAKE_TYPE: ('client-hello' | 'server-hello' | 'server-certificate' | 'client-certificate' | 'server-key-exchange' |
                 'certificate-request' | 'server-hello-done' | 'client-key-exchange' |
                 'certificate-verify' | 'client-finished' | 'server-finished' | 'hello-request')
;
TLS_VERSION: ('TLS-10' | 'TLS-11' | 'TLS-12' | 'TLS-13')
;
CIPHER_SUITE: ('TLS-ECDHE-ECDSA-WITH-AES-128-CCM' | 'TLS-ECDHE-ECDSA-WITH-AES-256-CCM' | 'TLS-DHE-RSA-with-AES-128-CBC-SHA256')
;
COMPRESSION_METHOD: ('zlib-compression' | 'no-compression')
;
SIGNATURE_ALGO: ('anonAuth' | 'rsaAuth' | 'dssAuth' | 'ecdsaAuth' | 'pskAuth' | 'dsaAuth')
;
NAMED_CURVE: ('secp256r1' | 'secp256k1' | 'secp384r1' | 'secp384k1')
;
HASH_ALGO: ('sha1' | 'sha224' | 'sha256' | 'sha384' | 'sha512')
;
KEY_EXCHANGE_ALGORITHM: ('dh' | 'dhe' | 'ecdh' | 'ecdhe' | 'rsa')
;
ENCRYPTION_ALGORITHM: ('aes128' | 'aes256' | '3des' )
;
CERTIFICATE_TYPE: ('ecdsa-sign' | 'rsa-sign')
;
LP: '('
;
RP: ')'
;
COL: ':'
;
NUM : [0-9]+
;
IDENTIFIER: ID ('-' ID)*
;
ID : [a-zA-Z0-9]+
;

CARRAGE_RETURN: '\r' -> skip;
NEWLINE : '\n' -> skip;
WHITESPACE : ' ' -> skip;
