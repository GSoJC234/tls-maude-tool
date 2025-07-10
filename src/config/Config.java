package config;

import java.util.List;

public class Config {
    private List<Integer> scenario;
    private List<Node> nodes;

    public List<Integer> getScenario() {
        return scenario;
    }

    public void setScenario(List<Integer> scenario) {
        this.scenario = scenario;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
}
