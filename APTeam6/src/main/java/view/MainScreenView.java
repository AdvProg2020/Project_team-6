package view;

import controller.ProgramManager;

public class MainScreenView {

    private void showHelp(){
        System.out.println("List of commands:\n\tlogin menu\n\to");
    }

    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.equalsIgnoreCase("exit")){
                return command;
            }
            if (command.equalsIgnoreCase("")){

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
