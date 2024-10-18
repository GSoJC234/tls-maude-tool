package Protocol;

public interface Protocol {
    void accept(String alias);
    void connect(String alias);
    void close(String alias);
    Variable recv(String alias);
    void send(String alias, Variable msg);
    void execute();
}
