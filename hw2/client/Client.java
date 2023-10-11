package hw2.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.LinkedList;

public class Client implements ClientSend {

   private static ClientRecieve msgFromServer;
   private static ClientSend clientSend;

   private ClientWindow clientWindow;

   static Socket socket;
   static DataInputStream din;
   static DataOutputStream dout;

   private String _ip, _name;
   int _port;

   private static LinkedList<String> data1;

   public ClientSend getInterface() {
      return this;
   }

   public Client() {
      clientWindow = new ClientWindow(this);
      msgFromServer = clientWindow.getInterface();
      data1 = new LinkedList<>();
   }

   public void connect(String serverIP, int serverPort, String nickname, String passwd) {
      this._name = nickname;
      this._port = serverPort;
      try {
         // data1.add(nickname);
         // clientRead.resieveUsers(data1);
         socket = new Socket(serverIP, clientWindow.getServerPort());
         din = new DataInputStream(socket.getInputStream());
         dout = new DataOutputStream(socket.getOutputStream());
         String msgin = "";
         while (socket.isConnected()) {
            msgin = din.readUTF();
            msgFromServer.resieveMsg(msgin);
         }
      } catch (Exception a) {
         System.out.println("сервер недоступен");
      }
   }

   @Override
   public void sendtoServer(String msg) {
      try {
         dout.writeUTF(msg.trim());
      } catch (Exception e) {
         System.out.println(e.getMessage());
      }
   }

}
