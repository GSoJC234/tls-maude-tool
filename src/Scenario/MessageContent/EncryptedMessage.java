package Scenario.MessageContent;

import java.util.ArrayList;
import java.util.List;

public class EncryptedMessage implements MessageContent{
    private Key key;
    private List<MessageContent> messageContentList;

    public EncryptedMessage(){
        this.messageContentList = new ArrayList<MessageContent>();
    }

    public void setKey(Key key){
        this.key = key;
    }
    public void addMessage(MessageContent messageContent){
        this.messageContentList.add(messageContent);
    }
}
