package client.view.userPanel;

import client.view.old.Input;

import java.util.Arrays;
import java.util.HashSet;

public class ManagerUserPanelView {
    public ManagerUserPanelView() {
        System.out.println("=== Manager main screen");
    }

    private final HashSet<String> returningCommand = new HashSet<>(Arrays.asList("client.view personal info", "login menu", "manage users", "manage all products", "create discount code", "client.view discount codes", "manage requests", "manage categories"));

    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.equalsIgnoreCase("help")) {
                showHelp();
            }
            else if (returningCommand.contains(command)){
                return command;
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }

    private void showHelp() {
        System.out.println("List of commands:" +
                "\n\tclient.view personal info" +
                "\n\tlogin menu" +
                "\n\tmanage users" +
                "\n\tmanage all products" +
                "\n\tcreate discount code" +
                "\n\tclient.view discount codes" +
                "\n\tmanage requests" +
                "\n\tmanage categories");
    }

    public void giveOutput(String message) {
        System.out.println(message);
    }
}
