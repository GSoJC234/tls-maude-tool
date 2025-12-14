package mta.visualizer.info;

public class Target {

    private String ip = null;
    private int port = 0;
    private String command = null;
    private int timeDelay = 0;
    private String remoteId = null;
    private String remotePassword = null;

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setTimeDelay(int timeDelay) {
        this.timeDelay = timeDelay;
    }

    public void setRemoteId(String remoteId) {
        this.remoteId = remoteId;
    }

    public void setRemotePassword(String remotePassword) {
        this.remotePassword = remotePassword;
    }

    public String getIp() { return ip; }
    
    public int getPort() { return port; }

    public String getCommand() { return command; }

    public boolean isValid() {
        if (ip == null) {
            System.out.println("ip is not set");
            return false;
        }
        if (port <= 0) {
            System.out.println("port is not set");
            return false;
        }
        if (command == null) {
            System.out.println("command is not set");
            return false;
        }
        if (timeDelay < 0) {
            System.out.println("timeDelay is not set");
            return false;
        }
        if (remoteId == null) {
            System.out.println("remoteId is not set");
            return false;
        }
        if (remotePassword == null) {
            System.out.println("remotePassword is not set");
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ip: ").append(ip);
        sb.append("\nport: ").append(port);
        sb.append("\ncommand: ").append(command);
        sb.append("\ntimeDelay: ").append(timeDelay);
        sb.append("\nremoteId: ").append(remoteId);
        sb.append("\nremotePassword: ").append(remotePassword);
        return sb.toString();
    }
}
