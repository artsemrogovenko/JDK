package hw2;

import hw2.client.Client;
import hw2.server.Server;

public class Main {
    public static void main(String[] args) {
        new Thread(() ->new Server().main(args)).start();
        new Thread(() ->new Client().main(args)).start();
    }
}
