package homework;

import homework.client.ChatClient;
import homework.server.ChatServer;

public class Main {
    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        new ChatClient(server);
        new ChatClient(server);
    }
}