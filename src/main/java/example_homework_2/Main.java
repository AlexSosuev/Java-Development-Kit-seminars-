package example_homework_2;

import example_homework_2.client.ui.ClientGUI;
import example_homework_2.server.ui.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        new ClientGUI(serverWindow);
        new ClientGUI(serverWindow);
    }
}