package hw2.server;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Server server;
    private Socket clientSocket = null;

    private PrintWriter outMessage;
    private Scanner inMessage;
    private static int client_count = 0;

    public ClientHandler(Socket socket, Server server) {
        try {
            client_count++;
            this.server = server;
            this.clientSocket = socket;
            this.outMessage = new PrintWriter(socket.getOutputStream());
            this.inMessage = new Scanner(socket.getInputStream());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void sendMessage(String mes) {
        outMessage.println(mes);
        outMessage.flush();
    }

    public void closeConnetion() {
        server.removeClient(this);
        client_count--;
        server.sendToAll("" + client_count);
    }

    @Override
    public void run() {
        try {
            server.sendToAll("new client");
            while (true) {
                String clientmessage = inMessage.nextLine();
                if (clientmessage.equals("stop")) {
                    break;
                }
                System.out.println(clientmessage);
                server.sendToAll(clientmessage);
            }
            Thread.sleep(100);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}