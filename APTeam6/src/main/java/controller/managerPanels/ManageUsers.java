package controller.managerPanels;

import controller.LoginMenu;
import controller.ProgramManager;
import model.account.Account;
import view.ManageUsersView;

public class ManageUsers {
    private static ManageUsers manageUsersInstance = null;
    public static ManageUsers getManageUsersInstance() {
        if (manageUsersInstance == null)
            manageUsersInstance = new ManageUsers();
        return manageUsersInstance;
    }
    
    ManageUsersView view;
    public void start(){
        view = new ManageUsersView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if(command.matches("view \\S+")) {
                ViewUser(command.split("\\s")[1]);
                //TODO: What is this? It doesn't do anything
            }
            /*else if (command.matches("change type \\S+, \\S+")) {
                ChangeType(command.split("\\s")[2],command.split("\\s")[3]);
            }*/
            else if(command.matches("delete user \\S+")){
                deleteUser(command.split("\\s")[3]);
            }
            else if (command.equals("back")){
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to LoginMenu by view");
            }
        }
    }
    private Account ViewUser(String username){
        return ProgramManager.getProgramManagerInstance().getAccountByUsername(username);
    }
    /*private void ChangeType(String username, String role){
        ProgramManager.getProgramManagerInstance().deleteAccount(ProgramManager.getProgramManagerInstance().getAccountByUsername(username));
        LoginMenu loginMenu;
        LoginMenu.getLoginMenuInstance().register(username,role);
        view.giveOutput("the user's role was successfully changed!");
    }*/
    private void deleteUser(String username){
        ProgramManager.getProgramManagerInstance().deleteAccount(username);
        view.giveOutput("the user was successfully removed!");
    }
}
