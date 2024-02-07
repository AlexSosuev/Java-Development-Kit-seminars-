package homework_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChatServer extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    public static final String LOG_PATH = "src/main/java/homework_1/log.txt";
    List<ChatClient> chatClientList;
    JButton btnStart, btnStop;
    JTextArea log;
    boolean work;

    public ChatServer(){
        chatClientList = new ArrayList<>();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);
        createPanel();
        setVisible(true);
    }

    public boolean connectUser(ChatClient chatClient){
        if (!work){
            return false;
        }
        chatClientList.add(chatClient);
        return true;
    }

    public String getLog() {
        return readLog();
    }

    public void disconnectUser(ChatClient chatClient){
        chatClientList.remove(chatClient);
        if (chatClient != null){
            chatClient.disconnectFromServer();
        }
    }

    public void message(String text){
        if (!work){
            return;
        }
        text += "";
        log.append(text + "\n");
        answerAll(text);
        saveInLog(text);
    }

    private void answerAll(String text){
        chatClientList.forEach(chatClient -> chatClient.answer(text));
    }

    private void saveInLog(String text){
        try (FileWriter writer = new FileWriter(LOG_PATH, true)){
            writer.write(text + "\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private String readLog() {
        StringBuilder stringBuilder = new StringBuilder();
        File logFile = new File(LOG_PATH);
        if (logFile.exists() && logFile.length() > 0) {
            try (BufferedReader reader = new BufferedReader(new FileReader(logFile))){
                reader.lines().forEach(line -> stringBuilder.append(line).append("\n"));
                if (stringBuilder.length() > 0) {
                    stringBuilder.setLength(stringBuilder.length() - 1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (work){
                    log.append("Сервер уже был запущен!\n");
                } else {
                    work = true;
                    log.append("Сервер запущен!\n");
                }
            }
        });
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!work){
                    log.append("Сервер уже был остановлен!\n");
                } else {
                    work = false;
                    while (!chatClientList.isEmpty()){
                        disconnectUser(chatClientList.get(chatClientList.size()-1));
                    }
                    log.append("Сервер остановлен!\n");
                }
            }
        });
        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }
}