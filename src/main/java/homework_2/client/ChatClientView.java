package homework_2.client;

public interface ChatClientView {
    void sendMessageToServerAndClearField();
    void updateMessages(String message);
    void disconnectAction();
}