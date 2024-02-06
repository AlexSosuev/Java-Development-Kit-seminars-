package homework_1;

public class Main {
    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        new ChatClient(server);
        new ChatClient(server);
    }
}
