package hw1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {

   private static ClientWindow clientWindow;

   static Socket s;
   static DataInputStream din;
   static DataOutputStream dout;

   public static void main(String[] args) {
      clientWindow = new ClientWindow();
      try {
         s = new Socket(clientWindow.getServerIP(), clientWindow.getServerPort());
         din = new DataInputStream(s.getInputStream());
         dout = new DataOutputStream(s.getOutputStream());
         String msgin = "";
         while (!clientWindow.closed) {
            // while (!msgin.equals("exit")) {
            msgin = din.readUTF();
            clientWindow.resieveMsg(msgin);
         }
      } catch (Exception e) {
         System.out.println(e.getMessage());
      }
   }

   public static void sendtoServer(String s) {
      try {
         dout.writeUTF(s.trim());
      } catch (Exception e) {
         System.out.println(e.getMessage());
      }
   }



}
