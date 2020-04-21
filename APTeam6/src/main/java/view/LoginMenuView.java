package view;

import controller.LoginMenu;
import controller.ProgramManager;

public class LoginMenuView {
    public LoginMenuView(){
        System.out.println("=== Login/register menu");
    }

    public String getInputCommand(){
        String command;
        while (true){
            command = Input.getInput();
            if (command.equals("help")){
                showHelp();
                continue;
            }
            if (command.equals("back")){
                return command;
            }
            if (ProgramManager.getProgramManagerInstance().isAnyoneLoggedIn()){
                if (command.equals("logout")) {
                    return command;
                }
            }
            else {
                if (command.matches("login \\S+ \\S+") || command.matches("create account (buyer|seller) \\S+")) {
                    return command;
                }
            }
            System.out.println("Invalid command");
        }
    }

    private void showHelp(){
        System.out.println("List of commands:\n\tlogin [username] [password]\n\tcreate account [role] [username]");
    }

    public void giveOutput(String message){
        System.out.println(message);
    }

    public String[] getUserUsualData(){
        String[] data = new String[5];
        System.out.println("Enter your first name:");
        data[0] = Input.getInput();
        System.out.println("Enter your last name:");
        data[1] = Input.getInput();
        while (true) {
            System.out.println("Enter your phone number:");
            data[2] = Input.getInput();
            if (data[2].matches("[0-9]+")) {
                break;
            }
            System.out.println("Wrong phone number");
        }
        System.out.println("Enter your email address:");
        data[3] = Input.getInput();
        System.out.println("Enter your password:");
        data[4] = Input.getInput();
        return data;
    }

    public String getSellerCompany(){
        System.out.println("Enter your company name:");
        return Input.getInput();
    }
}
