package hw1;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.*;

public class ClientWindow {
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

    private final String[] data1 = { "Юки", "Дуглас", "Оникс", "Симба", "Норман" };
    JList<String> users = new JList<String>();

    private static JFrame frame = new JFrame("Chat Client");
    private static JButton login = new JButton("Login");
    private static JButton send = new JButton("Send");

    private static JTextField serverIP = new JTextField("127.0.0.1");
    private static JTextField serverPort = new JTextField("1201");
    private static JTextField nickname = new JTextField("gister");
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
        users.setListData(data1);
    }

    public ClientWindow() {
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

        sendText.addKeyListener(new KeyListener() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    sendString();
                }
                if (e.getKeyCode() == 27) {
                    sendText.setText(null);
                }
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
            }

            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
            }

        });

    }

    private void sendString() {
        if (!sendText.getText().equals("")) {
            String out = Logger.getTime() + nickname.getText() + " : " + sendText.getText() + "\n";
            chatHistory.setCaretPosition(chatHistory.getDocument().getLength());
            Client.sendtoServer(out);
            chatHistory.append(out);
            sendText.setText(null);
            Logger.writelog(getNick(),out);
        }
    }

    public void resieveMsg(String r) {
        chatHistory.append(r);
        Logger.writelog(getNick(),r);
        chatHistory.setCaretPosition(chatHistory.getDocument().getLength());
    }

}
