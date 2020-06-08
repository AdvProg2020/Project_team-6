package view;

import controller.ProgramManager;

public class ManageCategoriesView {
    public ManageCategoriesView() {
        System.out.println("=== ManageCategoriesView menu");
    }

    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            if(command.matches("edit \\.+")){
                return command;
            }
            else if(command.matches("add \\.+")){
                return command;
            }
            else if(command.matches("remove \\.+")){
                return command;
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }
}
