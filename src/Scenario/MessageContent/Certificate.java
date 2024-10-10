package Scenario.MessageContent;

public class Certificate implements MessageContent{
    private SignatureAndHashAlgorithm signatureAndHashAlgorithm;
    private String certificateAuthor;
    private Key key;
    private SignatureAlgorithm signatureAlgorithm;
    private String owner;
    private EncryptedMessage encryptedMessage;

    public void setSignatureAndHashAlgorithm(SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        this.signatureAndHashAlgorithm = signatureAndHashAlgorithm;
    }
    public void setCertificateAuthor(String certificateAuthor) {
        this.certificateAuthor = certificateAuthor;
    }
    public void setKey(Key key) {
        this.key = key;
    }
    public void setSignatureAlgorithm(SignatureAlgorithm signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public void setEncryptedMessage(EncryptedMessage encryptedMessage) {
        this.encryptedMessage = encryptedMessage;
    }
}
