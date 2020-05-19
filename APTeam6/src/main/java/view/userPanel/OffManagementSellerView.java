package view.userPanel;

import controller.ProgramManager;
import view.Input;

public class OffManagementSellerView {
    public OffManagementSellerView() {
        System.out.println("=== OffManagementSellerView menu");
    }

    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.equals("back")) {
                return command;
            }
            else if (command.matches("view \\.+")) {
                return command;
            }
            else if(command.matches("edit \\.+")){
                return command;
            }
            else if(command.equalsIgnoreCase("add off")){
                return command;
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }
}
