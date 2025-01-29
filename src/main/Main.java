package main;

import com.beust.jcommander.JCommander;
import scenario.ScenarioExecutor;
import scenario.ScenarioGenerator;
import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class Main {

    @Parameter(names = "-req", description = "Test requirement number")
    private int requirementNum = 0;

    @Parameter(names = {"-currentIP"}, description = "Current IP can be used multiple times, and may be comma-separated")
    private List<String> currentIP = new ArrayList<>();

    @Parameter(names = {"-currentPort"}, description = "Current port can be used multiple times, and may be comma-separated")
    private List<Integer> currentPort = new ArrayList<>();

    @Parameter(names = {"-serverIP"}, description = "Target server IP can be used multiple times, and may be comma-separated")
    private List<String> serverIP = new ArrayList<>();

    @Parameter(names = {"-serverPort"}, description = "Target server port can be used multiple times, and may be comma-separated")
    private List<Integer> serverPort = new ArrayList<>();

    @Parameter(names = "-visusalIP", description = "Visual tool IP")
    private String visualToolIp = "127.0.0.1";

    @Parameter(names = "-visualPort", description = "Visual tool port")
    private int visualToolPort = 8000;

    @Parameter(names = "-debug", description = "Debug mode")
    private boolean debug = false;

    public static void main(String[] args){
        Main main = new Main();
        JCommander.newBuilder().addObject(main).build().parse(args);

        ScenarioGenerator generator = new ScenarioGenerator(main.requirementNum);
        generator.run();
        String scenario = generator.getScenario();
        ScenarioExecutor executor = new ScenarioExecutor(scenario);
        executor.setCurrentIPs(main.currentIP);
        executor.setCurrentPorts(main.currentPort);
        executor.setServerIPs(main.serverIP);
        executor.setServerPorts(main.serverPort);
        executor.execute();
    }
}