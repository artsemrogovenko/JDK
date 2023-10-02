package hw1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
   private static ServerWindow serverwindow;

   static ServerSocket ss;
   static Socket s;
   static DataInputStream din;
   static DataOutputStream dout;

   public static void main(String[] args) {
      serverwindow = new ServerWindow();
      String msgin = "";
      try {
         ss = new ServerSocket(1201);
         s = ss.accept();
         din = new DataInputStream(s.getInputStream());
         System.out.println("Client connected " + ss.getLocalPort());
         serverwindow.getmessage("Server started at " + ss.getLocalPort());
         dout = new DataOutputStream(s.getOutputStream());
         sendHistory();
         // while (!msgin.equals("exit")) {
         while (!serverwindow.closed) {
            msgin = din.readUTF();
            serverwindow.getmessage(msgin); // message from client
            Logger.writelog("Server",msgin+"\n");
         }
      } catch (Exception e) {
      }
   }

   public static void sendtoClient(String text) {
      try {
         dout.writeUTF(text);
         Logger.writelog("Server",text);
      } catch (Exception e) {
      }
   }

   private static void sendHistory(){
      try {
         dout.writeUTF(Logger.readlog("Server"));
      } catch (IOException e) {
      } 
   }

}
