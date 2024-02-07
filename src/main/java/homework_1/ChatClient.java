package homework_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatClient extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    private final ChatServer chatServer;
    private boolean connected;
    private String name;
    JTextArea log;
    JTextField ipUser, port, login, message;
    JPasswordField password;
    JButton btnLogin, btnSend;
    JPanel headerPanel;

    public ChatClient(ChatServer chatServer){
        this.chatServer = chatServer;
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");
        setLocation(chatServer.getX() - 500, chatServer.getY());
        createPanel();
        setVisible(true);
    }

    public void answer(String text){
        appendLog(text);
    }

    private void connectToServer() {
        if (chatServer.connectUser(this)){
            appendLog("Вы успешно подключились!\n");
            headerPanel.setVisible(false);
            connected = true;
            name = login.getText();
            String log = chatServer.getLog();
            if (log != null){
                appendLog(log);
            }
        } else {
            appendLog("Подключение не удалось");
        }
    }

    public void disconnectFromServer() {
        if (connected) {
            headerPanel.setVisible(true);
            connected = false;
            chatServer.disconnectUser(this);
            appendLog("Вы были отключены от сервера!");
        }
    }

    public void message(){
        if (connected){
            String text = message.getText();
            if (!text.equals("")){
                chatServer.message(name + ": " + text);
                message.setText("");
            }
        } else {
            appendLog("Нет подключения к серверу");
        }
    }

    private void appendLog(String text){
        log.append(text + "\n");
    }

    private void createPanel() {
        add(createConnectPanel(), BorderLayout.NORTH);
        add(createLog());
        add(createFooter(), BorderLayout.SOUTH);
    }

    private Component createConnectPanel(){
        headerPanel = new JPanel(new GridLayout(2, 3));
        ipUser = new JTextField("127.0.0.1");
        port = new JTextField("8080");
        login = new JTextField("Petya");
        password = new JPasswordField("123456");
        btnLogin = new JButton("login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToServer();
            }
        });

        headerPanel.add(ipUser);
        headerPanel.add(port);
        headerPanel.add(new JPanel());
        headerPanel.add(login);
        headerPanel.add(password);
        headerPanel.add(btnLogin);
        return headerPanel;
    }

    private Component createLog(){
        log = new JTextArea();
        log.setEditable(false);
        return new JScrollPane(log);
    }

    private Component createFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        message = new JTextField();
        message.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n'){
                    message();
                }
            }
        });
        btnSend = new JButton("send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message();
            }
        });
        panel.add(message);
        panel.add(btnSend, BorderLayout.EAST);
        return panel;
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING){
            disconnectFromServer();
        }
        super.processWindowEvent(e);
    }
}