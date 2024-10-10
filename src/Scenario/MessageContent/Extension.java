package Scenario.MessageContent;

import java.util.ArrayList;
import java.util.List;

public class Extension implements MessageContent {
    private List<MessageContent> MessageContentList;
    public Extension() {
        MessageContentList = new ArrayList<MessageContent>();
    }
    public void addMessageContent(MessageContent MessageContent) {
        MessageContentList.add(MessageContent);
    }

}
