package Scenario;

import java.util.ArrayList;
import java.util.List;

public class Scenario implements ScenarioElement {

    private String title;
    private NodeEntry nodeEntry;
    private int nodeNum;
    private List<Message> messageSequences;

    public Scenario(){
        messageSequences = new ArrayList<Message>();
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setNodeId(NodeEntry nodeEntry) {
        this.nodeEntry = nodeEntry;
    }
    public void setNodeNum(int nodeNum) {
        this.nodeNum = nodeNum;
    }
    public void addMessage(Message message) {
        messageSequences.add(message);
    }
}
