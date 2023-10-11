package hw2.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import hw2.Logger;

public class Server implements ServerSend {
   private ServerRecieve msgFromClient;
   
   private  ServerWindow serverWindow;
   private boolean powered = false;

   public static LinkedList<ClientHandler> clients = new LinkedList<>();

   private  ServerSocket serverSocket;
   private  Socket clientSocket;
   private static DataInputStream din;
   private static DataOutputStream dout;

   public ServerSend getInterface() {
      return this;
   }

   public Server() {
      this.serverWindow = new ServerWindow(this);
      this.msgFromClient = serverWindow.getInterface();
      this.serverSocket = null;
      this.clientSocket = null;
   }

   public void sendToAll(String message) {
      for (ClientHandler clientHandler : clients) {
         clientHandler.sendMessage(message);
      }
   }

   public void removeClient(ClientHandler clientHandler) {
      clients.remove(clientHandler);
   }

   @Override
   public void sendtoClient(String text) {
      try {
         dout.writeUTF(text);
         Logger.writelog("Server", text);
      } catch (NullPointerException e) {
         System.out.println("нет получателей");
      } catch (IOException e) {
         System.out.println(e.getMessage());
      }
   }

   @Override
   public void sendHistory() {
      try {
         dout.writeUTF(Logger.readlog("Server"));
      } catch (IOException e) {
         System.out.println(e.getMessage());
      }
   }

   public void stop() {
      try {
         if (serverSocket != null) {
            serverSocket.close();
            if (powered) {
               if(clientSocket!=null){    
                  clientSocket.close();           
               dout.close();
               din.close();
               }
            }
         }
         powered = false;
         serverWindow.isWorked=powered;
      } catch (IOException e) {
         System.out.println(e.getMessage());
      }
   }

   public void start() {          
      powered = true;
      serverWindow.isWorked=powered;
      try {
         clientSocket =serverSocket.accept();
      } catch (IOException e) {
         // TODO Auto-generated catch block
        System.out.println(e.getMessage());
      }
      try {
         if (serverSocket == null) {
            serverSocket = new ServerSocket(1201);
         }
         System.out.println("жду подключения клиента");
         clientSocket = serverSocket.accept();
         din = new DataInputStream(clientSocket.getInputStream());
         System.out.println("Client connected " + serverSocket.getLocalPort());
         msgFromClient.resieveMsg("Client connected at " + serverSocket.getLocalPort());
         dout = new DataOutputStream(clientSocket.getOutputStream());
         sendHistory();
         String msgin = "";
         while (!serverSocket.isClosed()) {
            msgin = din.readUTF();
            msgFromClient.resieveMsg(msgin); // message from client
            Logger.writelog("Server", msgin + "\n");
         }
      } catch (Exception e) {
         System.out.println(e.getLocalizedMessage());
      }
   }

}
