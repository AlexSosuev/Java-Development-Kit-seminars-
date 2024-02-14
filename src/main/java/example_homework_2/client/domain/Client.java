package example_homework_2.client.domain;

import example_homework_2.client.ui.ClientView;
import example_homework_2.server.domain.Server;

public class Client {
    private boolean connected;
    private String name;
    private ClientView clientView;
    private Server server;

    public Client(ClientView clientView, Server server) {
        this.clientView = clientView;
        this.server = server;
    }

    public boolean connectToServer(String name) {
        this.name = name;
        if (server.connectUser(this)){
            showOnWindow("Вы успешно подключились!\n");
            connected = true;
            String log = server.getHistory();
            if (log != null){
                showOnWindow(log);
            }
            return true;
        } else {
            showOnWindow("Подключение не удалось");
            return false;
        }
    }

    public void answerFromServer(String text) {
        showOnWindow(text);
    }

    public void disconnectFromServer() {
        if (connected) {
            connected = false;
            clientView.disconnectFromServer();
            server.disconnectUser(this);
            showOnWindow("Вы были отключены от сервера!");
        }
    }

    public void message(String text) {
        if (connected) {
            if (!text.isEmpty()) {
                server.message(name + ": " + text);
            }
        } else {
            showOnWindow("Нет подключения к серверу");
        }
    }

    public String getName() {
        return name;
    }

    private void showOnWindow(String text) {
        clientView.showMessage(text + "\n");
    }
}
