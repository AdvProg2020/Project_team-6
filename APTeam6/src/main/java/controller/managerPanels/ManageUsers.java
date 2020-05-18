package controller.managerPanels;

import controller.LoginMenu;
import controller.ProgramManager;
import model.account.Account;
import model.account.Manager;
import view.ManageUsersView;

public class ManageUsers {
    private static ManageUsers manageUsersInstance = null;
    public static ManageUsers getManageUsersInstance() {
        if (manageUsersInstance == null)
            manageUsersInstance = new ManageUsers();
        return manageUsersInstance;
    }

    private ManageUsersView view;
    public void start(){
        view = new ManageUsersView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if(command.matches("view \\S+")) {
                ViewUser(command.split("\\s")[1]);
            }
            else if(command.matches("delete user \\S+")){
                deleteUser(command.split("\\s")[3]);
            }
            else if(command.equalsIgnoreCase("create manager profile")){
                String[] commandSplit = command.split("\\s");
                createManagerProfile(commandSplit[3],commandSplit[4],commandSplit[5],commandSplit[6],commandSplit[7],commandSplit[8]);
            }
            else if (command.equals("back")){
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to ManageUsersMenu by view");
            }
        }
    }
    private void ViewUser(String username){
        Account tempAccount = ProgramManager.getProgramManagerInstance().getAccountByUsername(username);
        System.out.println("the user's first name is :" + tempAccount.getFirstName() + "the user's last name is :" + tempAccount.getLastName());
    }

    private void deleteUser(String username){
        ProgramManager.getProgramManagerInstance().deleteAccount(username);
        view.giveOutput("the user was successfully removed!");
    }
    private void createManagerProfile(String username, String password, String firstName, String lastName, String emailAddress, String phoneNumber){
        Manager newManager = new Manager(username,password,firstName,lastName,emailAddress,phoneNumber);
        ProgramManager.getProgramManagerInstance().addAccountToList(username,newManager);
    }
}

