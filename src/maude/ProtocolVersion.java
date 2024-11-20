package maude;

public enum ProtocolVersion {
    SSLV2, TLS11, TLS12, TLS13;

    public de.rub.nds.tlsattacker.core.constants.ProtocolVersion transform(){
        switch(this){
            case SSLV2: return de.rub.nds.tlsattacker.core.constants.ProtocolVersion.SSL2;
            case TLS11: return de.rub.nds.tlsattacker.core.constants.ProtocolVersion.TLS11;
            case TLS12: return de.rub.nds.tlsattacker.core.constants.ProtocolVersion.TLS12;
            case TLS13: return de.rub.nds.tlsattacker.core.constants.ProtocolVersion.TLS13;
            default: throw new IllegalArgumentException("Unknown ProtocolVersion: " + this);
        }
    }
}
