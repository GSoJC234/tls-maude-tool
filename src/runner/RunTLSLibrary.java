package runner;

import docker.DockerRun;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class RunTLSLibrary {

    private boolean useDocker = false;
    private String clientExecPath;
    private String serverExecPath;
    private int exitCode = 0;
    private String redirectionPath = null;

    public void setRedirectionFile(String redirectionPath) {
        this.redirectionPath = redirectionPath;
    }

    public int connect(String arguments) {
        return runWithDockerOrExec(arguments, true);
    }

    public int accept(String arguments) {
        return runWithDockerOrExec(arguments, false);
    }

    private int runWithDockerOrExec(String arguments, boolean isClient) {
        if (useDocker) {
            return (int) new DockerRun().run(TLSLibraryName(), arguments, isClient);
        } else {
            String execPath = isClient ? clientExecPath : serverExecPath;
            if (execPath == null) {
                throw new UnsupportedOperationException("Executable path not set for " + (isClient ? "client" : "server"));
            }

            List<String> command = new ArrayList<>();
            command.add(execPath);
            command.addAll(Arrays.asList(arguments.trim().split("\\s+")));

            ProcessBuilder pb = new ProcessBuilder(command);
            if(redirectionPath != null) {
                pb.redirectOutput(new File(redirectionPath));
                pb.redirectError(new File(redirectionPath));
            } else {
                pb.redirectErrorStream(true);
            }
            try {
                Process process = pb.start();

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                }

                exitCode = process.waitFor();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Execution failed: " + e.getMessage(), e);
            }

            return exitCode;
        }
    }

    public void setClientExecPath(String path) {
        this.clientExecPath = path;
    }

    public void setServerExecPath(String path) {
        this.serverExecPath = path;
    }

    public abstract String TLSLibraryName();
    protected abstract String transformValue(String value);

    public String transformCommandArguments(String ip, int port, Map<String, String> config) {
        StringBuilder sb = new StringBuilder();

        sb.append("-ip ").append(ip).append(" -port ").append(port).append(" ");

        config.forEach((key, value) -> {
            String transformed = transformValue(value);
            sb.append("-").append(key).append(" ").append(transformed).append(" ");
        });

        return sb.toString().trim();
    }}