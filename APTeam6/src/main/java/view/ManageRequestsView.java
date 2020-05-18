package view;

import controller.ProgramManager;
import controller.managerPanels.ManageRequests;

public class ManageRequestsView {
    public ManageRequestsView() {
        System.out.println("=== Manage Requests menu");
        System.out.println("The requests are (respectively):");
        ProgramManager.getProgramManagerInstance().showAllRequests();
    }
    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.equals("back")) {
                return command;
            }
            else if(command.matches("details \\.+")){
                return command;
            }
            else if(command.matches("(accept|decline) \\.+")){
                return command;
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }
    public void accept(int id){
        ProgramManager.getProgramManagerInstance().acceptRequests(id);
    }
}
