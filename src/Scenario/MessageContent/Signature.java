package Scenario.MessageContent;

import java.util.ArrayList;
import java.util.List;

public class Signature implements MessageContent{
    private EncryptedMessage encryptedMessage;
    public Signature(EncryptedMessage encryptedMessage) {
        this.encryptedMessage = encryptedMessage;
    }
}
