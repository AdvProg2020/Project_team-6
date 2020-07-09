package client.view.userPanel;

import client.view.old.Input;

import java.util.Arrays;
import java.util.HashSet;

public class BuyerUserPanelView {
    public BuyerUserPanelView() {
        System.out.println("=== Buyer main screen");
    }

    private final HashSet<String> returningCommand = new HashSet<>(Arrays.asList("client.view personal info", "login menu", "show cart"));

    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.equals("help")) {
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
        System.out.println("List of commands:");
        for (String comm : returningCommand) {
            System.out.println("\t" + comm);
        }
    }

    public void giveOutput(String message) {
        System.out.println(message);
    }
}
