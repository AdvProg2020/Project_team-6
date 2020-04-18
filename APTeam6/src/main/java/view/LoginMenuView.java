package view;

import controller.LoginMenu;
import controller.ProgramManager;

public class LoginMenuView {
    public LoginMenuView(){
        String command;
        while (!(command = Input.getInput()).equals("back")){
            if (command.equals("help")){
                showHelp();
            }
        }
    }

    public boolean checkInputForLoginCommands(String command){
        if (command.matches("login \\S+ \\S+")){
            String[] splitCommand = command.split("\\s");
            LoginMenu.getLoginMenuInstance().login(splitCommand[0], splitCommand[1]);
            return true;
        }
        else if (command.matches("register \\S+ (buyer|seller)")){
            String[] splitCommand = command.split("\\s");
            LoginMenu.getLoginMenuInstance().register(splitCommand[0], splitCommand[1]);
            return true;
        }
        else
            return false;
    }

    private void showHelp(){
        System.out.println("List of commands:\n\tlogin [username] [password]\n\tregister [username] [role]");
    }
}
