package view;

import controller.ProgramManager;

public class ManageUsersView {
    public ManageUsersView(){
        System.out.println("=== Manage Users Menu");
    }
    public String getInputCommand(){
        String command;
        while (true){
            command = Input.getInput();
            if(command.matches("view \\S+")){
                return command;
            }
            else if(command.matches("change type \\S+, \\S+")){
                return command;
            }
            else if(command.matches("delete user \\S+")){
                return command;
            }

            else {
                System.out.println("Invalid command");
            }

        }

    }
    public void giveOutput(String message){
        System.out.println(message);
    }
}
