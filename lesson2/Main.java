package lesson2;

import lesson2.client.ClientGUI;
import lesson2.server.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        new ClientGUI(serverWindow);
        new ClientGUI(serverWindow);
    }
}
