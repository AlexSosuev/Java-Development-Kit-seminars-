package homework_2.server;

import homework_2.client.ChatClient;
import homework_2.exceptions.LoginException;
import homework_2.repository.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ChatServer {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss ");
    private final Map<String, ChatClient> authorizedUsers;
    private boolean isServerWorking;
    private final ChatServerView view;
    private final Repository<String> repository;

    public ChatServer(ChatServerView view, Repository<String> repository) {
        this.repository = repository;
        authorizedUsers = new HashMap<>();
        this.view = view;
        isServerWorking = false;
    }

    public boolean getServerStatus() {
        return isServerWorking;
    }

    public void setServerStatus(boolean status) {
        isServerWorking = status;
    }

    public DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    public boolean authorize(ChatClient client, String login, int passwordHash) throws LoginException {
        if (isServerWorking) {
            if (authorizedUsers.containsKey(login)) {
                throw new LoginException(login + " уже существует, попробуйте другой логин.");
            }
            authorizedUsers.put(login, client);
            view.showInfoMessage(LocalDateTime.now().format(dateTimeFormatter) + login + " connected\n");
            return true;
        }
        return false;
    }

    public void receiveMessage(String login, String message) {
        if (authorizedUsers.containsKey(login)) {
            updateMessages(LocalDateTime.now().format(dateTimeFormatter) + login + ": " + message + "\n");
        }
    }

    private void updateMessages(String message) {
        view.showInfoMessage(message);
        saveMessage(message);
        for (ChatClient client : authorizedUsers.values()) {
            client.receiveMessageFromServer(message);
        }
    }

    void disconnectUsers() {
        for (String clientName : authorizedUsers.keySet()) {
            authorizedUsers.get(clientName).disconnect();
            view.showInfoMessage(LocalDateTime.now().format(dateTimeFormatter) + clientName + " отключен от сервера.\n");
        }
        authorizedUsers.clear();
    }

    public String loadMessages() {
        return repository.load();
    }

    private void saveMessage(String userMessages) {
        repository.save(userMessages);
    }
}