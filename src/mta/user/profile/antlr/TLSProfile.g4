grammar TLSProfile;

// TLS profile files are line-oriented "Key: Value" documents.
// Each entry maps directly to a field in mta.user.profile.TLSProfile.
//
// Supported top-level keys:
// - TestRole
// - TLSRole
// - Version
// - CipherSuites
// - Compressions
// - CertificateTypes
// - CertificateAlgos
// - Certificate / CertificatePath
// - PrivateKey / PrivateKeyPath
// - CACertificate / CaCertificatePath
// - SupportedGroups
// - SignatureAlgorithms
// - KeyShares
// - SupportedVersions
// - PskKeyExchangeModes / PSKKeyExchangeModes
// - NewSessionTicketReq
// - NewSessionTicketWait
// - EarlyDataReq
// - PostClientAuthReq
// - KeyUpdateReq
// - KeyUpdateWait
// - CertificateRequest
// - ExecutionConfiguration
//
// Example:
// TestRole: Target
// TLSRole: Client
// Version: TLS-12
// CipherSuites: TLS-ECDHE-ECDSA-WITH-AES-128-GCM-SHA256
// ExecutionConfiguration:
//   Name: gnuTLS
//   Path: "./gnuTLS-v3.8.12/build/src/gnutls-cli"

profile
    : profileEntry+ EOF
    ;

profileEntry
    : testRoleEntry
    | tlsRoleEntry
    | versionEntry
    | cipherSuitesEntry
    | compressionsEntry
    | certificateTypesEntry
    | certificateAlgosEntry
    | certificateEntry
    | privateKeyEntry
    | caCertificateEntry
    | supportedGroupsEntry
    | signatureAlgorithmsEntry
    | keySharesEntry
    | supportedVersionsEntry
    | pskKeyExchangeModesEntry
    | newSessionTicketReqEntry
    | newSessionTicketWaitEntry
    | earlyDataReqEntry
    | postClientAuthReqEntry
    | keyUpdateReqEntry
    | keyUpdateWaitEntry
    | certificateRequestEntry
    | executionConfigurationEntry
    ;

testRoleEntry
    : 'TestRole' COLON scalarValue
    ;

tlsRoleEntry
    : 'TLSRole' COLON scalarValue
    ;

versionEntry
    : 'Version' COLON scalarValue
    ;

cipherSuitesEntry
    : 'CipherSuites' COLON valueList
    ;

compressionsEntry
    : 'Compressions' COLON valueList
    ;

certificateTypesEntry
    : 'CertificateTypes' COLON valueList
    ;

certificateAlgosEntry
    : 'CertificateAlgorithms' COLON valueList
    ;

certificateEntry
    : 'CertificatePath' COLON pathValue
    ;

privateKeyEntry
    : 'PrivateKeyPath' COLON pathValue
    ;

caCertificateEntry
    : 'CACertificatePath' COLON pathValue
    ;

supportedGroupsEntry
    : 'SupportedGroups' COLON valueList
    ;

signatureAlgorithmsEntry
    : 'SignatureAlgorithms' COLON valueList
    ;

keySharesEntry
    : 'KeyShares' COLON valueList
    ;

supportedVersionsEntry
    : 'SupportedVersions' COLON valueList
    ;

pskKeyExchangeModesEntry
    : 'PSKKeyExchangeModes' COLON valueList
    ;

newSessionTicketReqEntry
    : 'NewSessionTicketRequest' COLON booleanValue
    ;

newSessionTicketWaitEntry
    : 'NewSessionTicketWait' COLON booleanValue
    ;

earlyDataReqEntry
    : 'EarlyDataRequest' COLON booleanValue
    ;

postClientAuthReqEntry
    : 'PostClientAuthRequest' COLON booleanValue
    ;

keyUpdateReqEntry
    : 'KeyUpdateRequest' COLON booleanValue
    ;

keyUpdateWaitEntry
    : 'KeyUpdateWait' COLON booleanValue
    ;

certificateRequestEntry
    : 'CertificateRequest' COLON booleanValue
    ;

executionConfigurationEntry
    : 'ExecutionConfiguration' COLON executionConfigurationField*
    ;

executionConfigurationField
    : executionConfigurationName
    | executionConfigurationPath
    ;

executionConfigurationName
    : 'Name' COLON scalarValue
    ;

executionConfigurationPath
    : 'Path' COLON pathValue
    ;

valueList
    : scalarValue (COMMA scalarValue)*
    ;

booleanValue
    : BOOLEAN
    | scalarValue
    ;

pathValue
    : STRING
    | VALUE
    ;

scalarValue
    : STRING
    | VALUE
    ;

BOOLEAN
    : 'true'
    | 'false'
    | 'True'
    | 'False'
    | 'TRUE'
    | 'FALSE'
    ;

STRING
    : '"' (ESC | ~["\\\r\n])* '"'
    ;

VALUE
    : [A-Za-z0-9_./-]+ (':' [A-Za-z0-9_./-]+)*
    ;

COLON
    : ':'
    ;

COMMA
    : ','
    ;

WS
    : [ \t\n\r]+ -> skip
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
