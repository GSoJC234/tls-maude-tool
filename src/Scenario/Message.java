package Scenario;

import Scenario.MessageContent.MessageContent;

import java.util.ArrayList;
import java.util.List;

public class Message  implements ScenarioElement {
    private int number;
    private String title;
    private String Sender;
    private String receiver;
    private List<MessageContent> contents;

    public Message(){
        contents = new ArrayList<MessageContent>();
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setSender(String Sender) {
        this.Sender = Sender;
    }
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    public void addContent(MessageContent content) {
        contents.add(content);
    }
}
