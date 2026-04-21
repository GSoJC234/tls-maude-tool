load "./c.profile" as C
load "./s.profile" as S

Nodes:
  n1, n2

Links:
  link1 = n1 <-> n2

StatePropositions:
  IS = n1 | ( value(clientState) == V3C-INIT) and
       n2 | ( value(serverState) == V3S-READY),
  FS = n1 | ( value(clientState) == V3C-DONE) and
       n2 | ( value(serverState) == V3S-DONE)

ScenarioProperties:
  connect[n1 <- C, n2 <- S] via link1:
	  IS ->
	  sendClientHelloV3 ->
	  receiveClientHelloV3 ->
	  sendServerHelloV3 ->
	  receiveServerHelloV3 ->
	  sendEncryptedExtensionV3 ->
	  receiveEncryptedExtensionV3 ->
	  sendCertificateRequestV3 ->
	  receiveCertificateRequestV3 ->
	  sendServerCertificateV3 ->
	  receiveServerCertificateV3 ->
	  sendServerCertificateVerify3 ->
	  receiveServerCertificateVerifyV3 ->
	  sendServerFinishedV3 ->
	  receiveServerFinishedV3 ->
	  sendClientCertificateV3 ->
	  receiveClientCertificateV3 ->
	  sendClientCertificateVerifyV3 ->
	  receiveClientCertificateVerifyV3 ->
	  sendClientFinishedV3 ->
	  receiveClientFinishedV3 ->
	  FS