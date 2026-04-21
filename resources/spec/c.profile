TestRole: Tester
TLSRole: Client
Version: TLS13
CipherSuites: TLS_AES_128_CCM_SHA256, TLS_AES_128_CCM_8_SHA256
Compressions: NO_COMPRESSION
CertificatePath: "./certs/client-cert.pem"
PrivateKeyPath: "./keys/client-key.pem"
CACertificatePath: "./certs/ca-cert.pem"
SupportedGroups: SECP256R1, SECP521R1
SignatureAlgorithms: ECDSA_SHA256, ECDSA_SHA512
KeyShares: SECP256R1, SECP521R1
SupportedVersions: TLS13