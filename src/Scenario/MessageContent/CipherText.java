package Scenario.MessageContent;

public class CipherText implements MessageContent{
    private EncryptedMessage encryptedMessage;
    public CipherText(EncryptedMessage encryptedMessage){
        this.encryptedMessage = encryptedMessage;
    }
}
