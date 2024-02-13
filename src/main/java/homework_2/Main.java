package homework_2;

import homework_2.client.ChatClientGUI;
import homework_2.repository.FileRepository;
import homework_2.repository.Repository;
import homework_2.server.ChatServerGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Repository<String> repository = new FileRepository("messages.log");
                ChatServerGUI serverViewSwing = new ChatServerGUI(repository);
                new ChatClientGUI(serverViewSwing.getServer());
                new ChatClientGUI(serverViewSwing.getServer());
            }
        });
    }
}