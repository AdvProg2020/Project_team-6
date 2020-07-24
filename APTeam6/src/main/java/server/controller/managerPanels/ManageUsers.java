package server.controller.managerPanels;

import server.Server;
import server.controller.Parent;
import server.controller.ProgramManager;
import server.model.account.Account;
import server.model.account.Buyer;
import server.model.account.Manager;
import client.view.old.ManageUsersView;
import server.model.account.Seller;

import java.io.IOException;

public class ManageUsers implements Parent {

    private Server server = null;

    @Override
    public void start(Server server) throws IOException {
        this.server = server;
        StringBuilder string = new StringBuilder();
        for (Account account : ProgramManager.getProgramManagerInstance().getAllAccounts()) {
            string.append(account.getUsername());
            string.append("---");
            string.append(account.getRole());
            string.append("---");
            string.append(((Boolean)account.isOnline()).toString());
            string.append("===");
        }
        sendMessage(string.toString());
    }

    private void sendMessage(String message) throws IOException {
        server.sendMessage("09-" + message);
    }

    public void deleteUser(String data) throws IOException {
        if (ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(data)){
            if(ProgramManager.getProgramManagerInstance().allLoggedInUser.contains(ProgramManager.getProgramManagerInstance().getAccountByUsername(data))){
                sendMessage("firstLogout");
            }else{
                ProgramManager.getProgramManagerInstance().deleteAccount(data);
                sendMessage("deleted");
            }
        }else{
            sendMessage("usernameDoesntExist");
        }
    }

    public void changeUserStart(String data) throws IOException {
        if (ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(data)) {
            //username---password---firstName---lastName---email---phoneNumber
            //username---password---firstName---lastName---email---phoneNumber---credit
            //username---password---firstName---lastName---email---phoneNumber---credit---company
            Account account = ProgramManager.getProgramManagerInstance().getAccountByUsername(data.split("---")[0]);
            if(account.getRole()==1){
                account.setPassword(data.split("---")[1]);
                account.setFirstName(data.split("---")[2]);
                account.setLastName(data.split("---")[3]);
                account.setEmailAddress(data.split("---")[4]);
                account.setPhoneNumber(data.split("---")[5]);
            }else if(account.getRole()==2){
                account.setPassword(data.split("---")[1]);
                account.setFirstName(data.split("---")[2]);
                account.setLastName(data.split("---")[3]);
                account.setEmailAddress(data.split("---")[4]);
                account.setPhoneNumber(data.split("---")[5]);
                Seller seller = (Seller) account;
                seller.setCompanyName(data.split("---")[6]);
            }else if(account.getRole()==3){
                account.setPassword(data.split("---")[1]);
                account.setFirstName(data.split("---")[2]);
                account.setLastName(data.split("---")[3]);
                account.setEmailAddress(data.split("---")[4]);
                account.setPhoneNumber(data.split("---")[5]);
            }
        } else {
            sendMessage("usernameDoesntExist");
        }
    }

    public void changeUserChange(String data) throws IOException {
        if(data.split("---")[0].equals("buyer")){

        }
        if(data.split("---").length==7 && !data.split("---")[0].equals("") &&
                !data.split("---")[1].equals("") && !data.split("---")[2].equals("") &&
                !data.split("---")[3].equals("") && !data.split("---")[4].equals("") &&
                !data.split("---")[5].equals("") && !data.split("---")[6].equals("") ) {

            if(ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(data.split("---")[0])){
                sendMessage("this username already taken");
                return;
            }

            new Seller(data.split("---")[0], data.split("---")[1], data.split("---")[2],
                    data.split("---")[3], data.split("---")[4], data.split("---")[5],
                    data.split("---")[6]);

            sendMessage("2-account_created");
        }else{
            sendMessage("error in data");
        }
    }

    public void viewUser(String data) throws IOException {
        if (ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(data)) {
            StringBuilder message = new StringBuilder();
            //manager---username---password---firstName---lastName---email---phoneNumber
            //buyer---username---password---firstName---lastName---email---phoneNumber---credit
            //seller---username---password---firstName---lastName---email---phoneNumber---credit---company

            Account account = ProgramManager.getProgramManagerInstance().getAccountByUsername(data);
            if (account.getRole() == 1) {
                message.append("buyer");
            } else if (account.getRole() == 2) {
                message.append("seller");
            } else if (account.getRole() == 3) {
                message.append("manager");
            } else {
                message.append("support");
            }

            message.append("---");
            message.append(account.getUsername());
            message.append("---");
            message.append(account.getPassword());
            message.append("---");
            message.append(account.getFirstName());
            message.append("---");
            message.append(account.getLastName());
            message.append("---");
            message.append(account.getEmailAddress());
            message.append("---");
            message.append(account.getPhoneNumber());

            if (account.getRole() == 1) {
                message.append("---");
                Buyer buyer = (Buyer) account;
                message.append(buyer.getCredit());
            } else if (account.getRole() == 2) {
                message.append("---");
                Seller seller = (Seller) account;
                message.append(seller.getCredit());
                message.append("---");
                message.append(seller.getCompanyName());
            }

            sendMessage(message.toString());
        } else {
            sendMessage("usernameDoesntExist");
        }
    }



    /*
    private static ManageUsers manageUsersInstance = null;
    public static ManageUsers getManageUsersInstance() {
        if (manageUsersInstance == null)
            manageUsersInstance = new ManageUsers();
        return manageUsersInstance;
    }
    ////////////////////////////////////
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
                throw new RuntimeException("Unknown command was passed to ManageUsersMenu by client.view");
            }
        }
    }
     */

    /*
    private void ViewUser(String username){
        Account tempAccount = ProgramManager.getProgramManagerInstance().getAccountByUsername(username);
        System.out.println("the user's first name is :" + tempAccount.getFirstName() + "the user's last name is :" + tempAccount.getLastName());
    }

    private void deleteUser(String username){
        ProgramManager.getProgramManagerInstance().deleteAccount(username);
        //view.giveOutput("the user was successfully removed!");
    }
    private void createManagerProfile(String username, String password, String firstName, String lastName, String emailAddress, String phoneNumber){
        Manager newManager = new Manager(username,password,firstName,lastName,emailAddress,phoneNumber);
        ProgramManager.getProgramManagerInstance().addAccountToList(username,newManager);
    }

     */

}

