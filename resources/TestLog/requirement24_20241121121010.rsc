Title: requirement24
NodeNum: 2
NodeId: client, server
Message Sequence:
 1. [ClientHello] client -> server: (contentType: 0x16), (version: 0x0303), (recordLen: 0x004d), (handshakeType: 0x01), (handshakeLen: 0x000049), (protocol: 0x0303), (cipherSuites: 0xc0ac), (cipherSuitesLen: 0x0002), (random: 0x6673d0560e13fbc9c819cc9b6519afd72ebdb5fdcab8dc967de4d3694f2b20c8), (sessionID: 0x000000000000000000000000000000000000000000000000000000000000), (compressionMethod: 0x00), (compressionMethodsLen: 0x01), (extension : (signatureAndHashAlgorithm: 0x0102))
 2. [ServerHello] server -> client: (contentType: 0x16), (version: 0x0303), (recordLen: 0x004a), (handshakeType: 0x02), (handshakeLen: 0x000046), (protocol: 0x0303), (cipherSuites: 0xc0ac), (random: 0x6673d056418f8ec55dfa917883bcdb31e48cb3640ec9a1706069422ce3e2e025), (sessionID: 0x42eabe9a6a40d217a72a7720e46d76fa08db4aae69262e36f431f86929cb53ba), (sessionIDLen: 0x20), (compression: 0x01), (extension: (signatureAndHashAlgorithm: 0x0102), (namedCurve: 0x0102))
 3. [Alert] client -> server: (contentType: 0x21), (version: 0x0303), (recordLen: 0x02), (alertLevel: 0x01), (alertDescription: 0x28)
