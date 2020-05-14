package controller.managerPanels;

import controller.LoginMenu;
import controller.ProgramManager;
import model.account.Account;
import view.LoginMenuView;
import view.ManageUsersView;

import javax.swing.colorchooser.AbstractColorChooserPanel;

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
            }
            else if (command.matches("change type \\S+, \\S+")) {
                ChangeType(command.split("\\s")[2],command.split("\\s")[3]);
            }
            else if(command.matches("delete user \\S+")){
                deleteUser(command.split("\\s")[3]);
            }
            else {
                throw new RuntimeException("Unknown command was passed to LoginMenu by view");
            }
        }
    }
    public Account ViewUser(String username){
        return ProgramManager.getProgramManagerInstance().getAccountByUsername(username);
    }
    public void ChangeType(String username,String role){
        ProgramManager.getProgramManagerInstance().deleteAccount(ProgramManager.getProgramManagerInstance().getAccountByUsername(username));
        LoginMenu loginMenu;
        LoginMenu.getLoginMenuInstance().register(username,role);
        view.giveOutput("the user's role was successfully changed!");
    }
    public void deleteUser(String username){
        ProgramManager.getProgramManagerInstance().deleteAccount(ProgramManager.getProgramManagerInstance().getAccountByUsername(username));
        view.giveOutput("the user was successfully removed!");
    }
}

