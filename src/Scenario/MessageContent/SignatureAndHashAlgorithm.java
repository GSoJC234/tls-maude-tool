package Scenario.MessageContent;

public class SignatureAndHashAlgorithm {
    private SignatureAlgorithm signatureAlgorithm;
    private HashAlgorithm hashAlgorithm;

    public SignatureAndHashAlgorithm(SignatureAlgorithm signatureAlgorithm, HashAlgorithm hashAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
        this.hashAlgorithm = hashAlgorithm;
    }
}
