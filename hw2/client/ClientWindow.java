package hw2.client;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import hw2.Logger;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;

public class ClientWindow implements ClientRecieve{
    /*
     * Создать окно клиента чата. Окно должно содержать JtextField
     * для ввода логина, пароля, IP-адреса сервера, порта подключения
     * к серверу, область ввода сообщений, JTextArea область просмотра
     * сообщений чата и JButton подключения к серверу и отправки сообщения
     * в чат. Желательно сразу сгруппировать компоненты, относящиеся
     * к серверу сгруппировать на JPanel сверху экрана, а компоненты,
     * относящиеся к отправке сообщения – на JPanel снизу
     */
    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;

    public boolean closed = false;
    public boolean loginIsPressed = false;
    public boolean connected = false;

    private static String[] randomnames = { "Alfred", "Bill", "Brandon", "Calvin", "Dean", "Dustin", "Ethan", "Harold",
            "Henry", "Irving", "Jason", "Jenssen", "Josh", "Martin", "Nick", "Norm", "Orin", "Pat", "Perry",
            "Ron", "Shawn", "Tim", "Will ", "Wyatt  " };

    JList<String> users = new JList<String>();

    private static JFrame frame = new JFrame("Chat client");
    private static JButton login = new JButton("Login");
    private static JButton send = new JButton("Send");

    private static JTextField serverIP = new JTextField("127.0.0.1");
    private static JTextField serverPort = new JTextField("1201");
    private static JTextField nickname = new JTextField(randomnames[new Random().nextInt(randomnames.length)]);
    private static JTextField passwd = new JTextField("123456");

    private static JTextField sendText = new JTextField();
    private JTextArea chatHistory = new JTextArea(4, 1);

    private static JPanel topPanel = new JPanel(new GridBagLayout());
    private static JPanel centerPanel = new JPanel(new BorderLayout());
    private static JPanel bottomPanel = new JPanel(new BorderLayout());

    GridBagConstraints c = new GridBagConstraints();

    JScrollPane slog = new JScrollPane(chatHistory,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    public int getServerPort() {
        return Integer.parseInt(serverPort.getText());
    }

    public String getServerIP() {
        return serverIP.getText();
    }

    public String getNick() {
        return nickname.getText();
    }

    public String getPass() {
        return passwd.getText();
    }

    private void fillPanel() {
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        // c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        topPanel.add(serverIP, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        // c.gridwidth = 3;
        c.gridx = 1;
        c.gridy = 0;
        topPanel.add(serverPort, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        // c.gridwidth = 3;
        c.gridx = 2;
        c.gridy = 0;
        topPanel.add(login, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        // c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        topPanel.add(nickname, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        // c.gridwidth = 3;
        c.gridx = 1;
        c.gridy = 1;
        topPanel.add(passwd, c);
        chatHistory.setEditable(false);
        bottomPanel.add(sendText, BorderLayout.CENTER);
        bottomPanel.add(send, BorderLayout.EAST);
        centerPanel.add(slog, BorderLayout.CENTER);
        centerPanel.add(users, BorderLayout.EAST);
    }
    
    public ClientRecieve getInterface() {
        return this;
    }

    private Client client;
    private ClientSend msgToServer;
    
    public ClientWindow(Client newClient) {
        this.client = newClient;
        this.msgToServer=newClient.getInterface();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        // frame.setResizable(false);
        fillPanel();
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {               
                System.out.println("presed");
                loginIsPressed = true;
                connect();             
            }
        });

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!sendText.getText().isEmpty()) {
                    sendString();
                }
            }
        });

        sendText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    sendString();
                }
                if (e.getKeyCode() == 27) {
                    sendText.setText(null);
                }
            };
        });         
        
       
    }

    private void sendString() {
        if (!sendText.getText().equals("") && connected) {
            String out = Logger.getTime() + nickname.getText() + " : " + sendText.getText() + "\n";
            chatHistory.setCaretPosition(chatHistory.getDocument().getLength());
            msgToServer.sendtoServer(out);
            chatHistory.append(out);
            sendText.setText(null);
            Logger.writelog(getNick(),out);
        }
    }

    @Override
    public void resieveMsg(String str) {
        chatHistory.append(str);
        Logger.writelog(getNick(),str);
        chatHistory.setCaretPosition(chatHistory.getDocument().getLength());
    }

    @Override
    public void resieveUsers(LinkedList<String> data) {
        if(connected){
            String[] names = new String[data.size()];
            for (int i = 0; i < data.size(); i++) {
                names[i] = data.get(i);
            }
            users.setListData(names);
        }
       
    }
private void connect(){
    client.connect(getServerIP(), getServerPort(), getNick(), getPass()); 
}
}
