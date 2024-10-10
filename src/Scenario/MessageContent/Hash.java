package Scenario.MessageContent;

import java.util.ArrayList;
import java.util.List;

public class Hash implements MessageContent{
    private HashAlgorithm hashAlgorithm;
    private List<MessageContent> messageContentList;

    public Hash(){
        messageContentList = new ArrayList<MessageContent>();
    }

    public void setHashAlgorithm(HashAlgorithm hashAlgorithm){
        this.hashAlgorithm = hashAlgorithm;
    }
    public void addMessageContent(MessageContent messageContent){
        messageContentList.add(messageContent);
    }
}
