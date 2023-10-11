package hw2.client;

public interface ClientRecieve {
    void resieveMsg(String str);
    void checkConnection(String message, boolean b);
    void nameListUpdate(String[] data);
}
