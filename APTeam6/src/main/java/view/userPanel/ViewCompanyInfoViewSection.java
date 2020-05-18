package view.userPanel;

import controller.ProgramManager;
import view.Input;

public class ViewCompanyInfoViewSection {
    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.equals("back")) {
                return command;
            }
            else if(command.equalsIgnoreCase("view company information")){
                return command;
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }
}
