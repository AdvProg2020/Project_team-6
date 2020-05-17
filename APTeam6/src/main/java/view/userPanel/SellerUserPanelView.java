package view.userPanel;

import view.Input;

public class SellerUserPanelView {
    public SellerUserPanelView() {
        System.out.println("=== Seller main screen");
    }

    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.equals("help")) {
                showHelp();
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }

    private void showHelp() {
        System.out.println("List of commands:\n\t");
    }

    public void giveOutput(String message) {
        System.out.println(message);
    }
}
