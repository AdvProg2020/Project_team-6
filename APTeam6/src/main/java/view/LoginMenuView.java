package view;

import controller.LoginMenu;
import controller.ProgramManager;

public class LoginMenuView {
    public LoginMenuView(){
        System.out.println("=== Login/register menu");
        String command;
        while (!(command = Input.getInput()).equals("back")){
            if (command.equals("help")){
                showHelp();
            }
            if (!checkInputForLoginCommands(command))
                System.out.println("Invalid command");
        }
    }

    /**
     * Gets a string, and checks if it is a login related command. This function is used inside and outside the login menu.
     * @param command the command to be checked
     * @return true if the command was a login related command
     */
    public static boolean checkInputForLoginCommands(String command){
        if (ProgramManager.getProgramManagerInstance().isAnyoneLoggedIn()){
            if (command.equals("logout")) {
                LoginMenu.getLoginMenuInstance().logout();
            }
        }
        else {
            if (command.matches("login \\S+ \\S+")) {
                String[] splitCommand = command.split("\\s");
                LoginMenu.getLoginMenuInstance().login(splitCommand[0], splitCommand[1]);
                return true;
            }
            else if (command.matches("register \\S+ (buyer|seller)")) {
                String[] splitCommand = command.split("\\s");
                LoginMenu.getLoginMenuInstance().register(splitCommand[0], splitCommand[1]);
                return true;
            }
        }
        return false;
    }

    private void showHelp(){
        System.out.println("List of commands:\n\tlogin [username] [password]\n\tregister [username] [role]");
    }
}
