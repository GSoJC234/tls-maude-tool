package Protocol;

public interface Protocol {
    void accept(String alias, int port, String ip);
    void connect(String alias, int port, String ip);
    void close(String alias);
    Variable recv(String alias);
    void send(String alias, Variable msg);
    void execute();
}
