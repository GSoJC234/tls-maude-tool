load "./c.profile" as C1
load "./c.profile" as C2
load "./s.profile" as S
use "./beh.behavior"

Nodes:
  n1, n2

Links:
  link1 = n1 <-> n2

StatePropositions:
  IS = n1 | ( value(clientState) == V3C-INIT) and
       n2 | ( value(serverState) == V3S-READY),
  FS = n1 | ( value(clientState) == V3C-DONE) and
       n2 | ( value(serverState) == V3C-DONE),
  ES = n1 | ( value(clientState) == V3C-ERROR) and
       n2 | ( value(serverState) == V3S-ERROR)

ActionPropositions:
  AP = wrongHandshakeType(client-hello, server-hello)

ScenarioProperties:
  connect[n1 <- C1, n2 <- S] via link1:
       IS ->* FS

  connect[n1 <- C2, n2 <- S] via link1:
       IS ->* AP ->* ES