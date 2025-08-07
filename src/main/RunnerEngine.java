package main;

import config.ConfigManager;
import runner.RunOpenSSL;
import runner.RunTLSLibrary;
import runner.RunWolfSSL;
import scenario.ScenarioExecutor;
import scenario.ScenarioTransform;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.Map;

public class RunnerEngine {
    private final ConfigManager manager;

    public RunnerEngine(ConfigManager manager) {
        this.manager = manager;
    }

    public void run() throws InterruptedException {
        for(int i = 0; i < manager.getScenarioNumbers().size() ; i ++){
            int num1 = manager.getScenarioNumbers().get(i);
            Thread scenarioThread = new Thread(() -> runScenario(num1));

            if (manager.isClientTarget()) {
                scenarioThread.start();
                Thread.sleep(5000);
                //tlsThread.start();
            } else {
                //tlsThread.start();
                Thread.sleep(5000);
                scenarioThread.start();
            }
            scenarioThread.join();
            //tlsThread.join();
        }


    }

    private void runScenario(int scenarioNumber) {
        ScenarioTransform transform = new ScenarioTransform();
        transform.setId(manager.getTesterId());
        transform.setIp(manager.getTesterIp());
        transform.setPort(manager.getTesterPort());
        transform.setCertificatePath(manager.getTesterCertificatePath());
        transform.setPrivateKeyPath(manager.getTesterPrivateKeyPath());

        String script = transform.transform(
            manager.getScenarioContent(scenarioNumber)
        );

        ScenarioExecutor executor = new ScenarioExecutor(script);
        if(manager.getTesterOutputPath() != null) {
            String mode = manager.isClientTarget() ? "client/" : "server/";
            executor.setRedirectionFile(Paths.get(manager.getTargetOutputPath() + mode + "scenario" + scenarioNumber + "_tester").toString());
        }
        executor.execute();
    }

    private void runLibrary(int scenarioNumber) {
        RunTLSLibrary runner = getRunner(manager.getTargetRole());
        if (manager.isClientTarget()) {
            runner.setClientExecPath(manager.getTargetPath());
        } else {
            runner.setServerExecPath(manager.getTargetPath());
        }

        if(manager.getTargetOutputPath() != null) {
            String mode = manager.isClientTarget() ? "client/" : "server/";
            runner.setRedirectionFile(Paths.get(manager.getTargetOutputPath() + mode + "scenario" +scenarioNumber + "_target").toString());
        }

        Map<String, String> config = manager.getTargetConfiguration(scenarioNumber);
        if (manager.getTargetCACertificatePath() != null) {
            config.put("cacert", manager.getTargetCACertificatePath());
        }

        String args = runner.transformCommandArguments(
                manager.getTargetIp(),
                manager.getTargetPort(),
                config
        );

        if (manager.isClientTarget()) {
            runner.connect(args);
        } else {
            runner.accept(args);
        }
    }

    private RunTLSLibrary getRunner(String name) {
        return switch (name.toLowerCase()) {
            case "wolfssl" -> new RunWolfSSL();
            case "openssl" -> new RunOpenSSL();
            default -> throw new IllegalArgumentException("Unsupported library: " + name);
        };
    }
}