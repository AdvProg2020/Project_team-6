package controller;

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
                // TODO: 5/6/2020  
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
        ProgramManager.getProgramManagerInstance().getAccountByUsername(username).setRole(role);
    }
}
