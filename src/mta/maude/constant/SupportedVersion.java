package mta.maude.constant;

public enum SupportedVersion {
    SSL30, TLS10, TLS11, TLS12, TLS13;

    public de.rub.nds.tlsattacker.core.constants.ProtocolVersion transform(){
        return switch (this) {
            case SSL30 -> de.rub.nds.tlsattacker.core.constants.ProtocolVersion.SSL3;
            case TLS10 -> de.rub.nds.tlsattacker.core.constants.ProtocolVersion.TLS10;
            case TLS11 -> de.rub.nds.tlsattacker.core.constants.ProtocolVersion.TLS11;
            case TLS12 -> de.rub.nds.tlsattacker.core.constants.ProtocolVersion.TLS12;
            case TLS13 -> de.rub.nds.tlsattacker.core.constants.ProtocolVersion.TLS13;
            default -> throw new IllegalArgumentException("Unknown ProtocolVersion: " + this);
        };
    }

    public String maudeTerm() {
        return switch (this) {
            case SSL30 -> "SSL-30";
            case TLS10 -> "TLS-10";
            case TLS11 -> "TLS-11";
            case TLS12 -> "TLS-12";
            case TLS13 -> "TLS-13";
            default -> throw new IllegalArgumentException("Unknown ProtocolVersion: " + this);
        };
    }
}
