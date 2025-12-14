package mta.maude.constant;

public enum PskKeyExchangeMode {
    PSK_KE, PSK_DHE_KE;

    public static String title(){
        return "PskKeyExchangeMode";
    }

    public de.rub.nds.tlsattacker.core.constants.PskKeyExchangeMode transform(){
        switch(this){
            case PSK_KE: return de.rub.nds.tlsattacker.core.constants.PskKeyExchangeMode.PSK_KE;
            case PSK_DHE_KE: return de.rub.nds.tlsattacker.core.constants.PskKeyExchangeMode.PSK_DHE_KE;
            default: throw new IllegalArgumentException("Unknown PskKeyExchangeModes : " + this);
        }
    }
}
