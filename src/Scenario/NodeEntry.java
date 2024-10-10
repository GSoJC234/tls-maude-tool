package Scenario;

import org.checkerframework.checker.units.qual.N;

import java.util.ArrayList;
import java.util.List;

public class NodeEntry implements ScenarioElement {
    private List<String> entries;
    public NodeEntry() {
        entries = new ArrayList<String>();
    }
    public void addEntry(String nodeId){
        entries.add(nodeId);
    }
}
