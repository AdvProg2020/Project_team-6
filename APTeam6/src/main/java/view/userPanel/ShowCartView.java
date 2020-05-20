package view.userPanel;

import controller.ProgramManager;
import model.account.Buyer;
import view.Input;

import java.util.Arrays;
import java.util.HashSet;

public class ShowCartView {
    public ShowCartView() {
        System.out.println("=== Cart screen (Buy basket -_-)");
        ((Buyer)ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUser()).getBuyBasketProductIds()
    }

    private final HashSet<String> returningCommand = new HashSet<>(Arrays.asList("login menu", ""));

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
}
