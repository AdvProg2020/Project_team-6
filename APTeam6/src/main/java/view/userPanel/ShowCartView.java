package view.userPanel;

import controller.ProgramManager;
import model.account.Buyer;
import view.Input;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ShowCartView {
    /*public ShowCartView() {
        System.out.println("=== Cart screen (Buy basket -_-)");
        HashMap<Integer, Integer> productHashMap = ((Buyer)ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUser()).getBuyBasket();
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : productHashMap.entrySet()) {
            i++;
            System.out.println(i + ". " + ProgramManager.getProgramManagerInstance().getProductById(entry.getKey()).getName() + ": " + entry.getValue());
        }
    }*/

    private final HashSet<String> returningCommand = new HashSet<>(Arrays.asList("login menu", ""));
    //TODO: i should regex

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
        for (String command : returningCommand) {
            System.out.println("\t" + command);
        }
    }
}
