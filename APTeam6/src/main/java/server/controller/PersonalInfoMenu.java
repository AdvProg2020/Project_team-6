package server.controller;

import server.Server;
import server.model.account.Account;
import server.model.account.Buyer;
import server.model.account.Seller;
import client.view.old.PersonalInfoMenuView;

import java.io.IOException;

public class PersonalInfoMenu implements Parent {
    /*
    private static PersonalInfoMenu personalInfoMenuInstance = null;
    public static PersonalInfoMenu getPersonalInfoMenuInstance() {
        if (personalInfoMenuInstance == null)
            personalInfoMenuInstance = new PersonalInfoMenu();
        return personalInfoMenuInstance;
    }

    ///////////////////////////////////////////////////
    PersonalInfoMenuView view;
    private Account currentUser = ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUser();

    public void start(){
        view = new PersonalInfoMenuView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.equals("back")) {
                return;
            }
            else if (command.matches("edit (password|firstName|lastName|phoneNumber|email)")) {
                if (command.equalsIgnoreCase("edit password"))
                    currentUser.setPassword(view.getNewValueForField("password"));
                if (command.equalsIgnoreCase("edit firstName"))
                    currentUser.setFirstName(view.getNewValueForField("firstName"));
                if (command.equalsIgnoreCase("edit lastName"))
                    currentUser.setLastName(view.getNewValueForField("lastName"));
                if (command.equalsIgnoreCase("edit phoneNumber"))
                    currentUser.setPhoneNumber(view.getNewValueForField("phoneNumber"));
                if (command.equalsIgnoreCase("edit email"))
                    currentUser.setEmailAddress(view.getNewValueForField("email"));
            }
            else if (command.matches("edit credit") && (currentUser.getRole() == 1 || currentUser.getRole() == 2)){
                if (currentUser.getRole() == 1)
                    ((Buyer)currentUser).modifyCreditBy(Long.parseLong(view.getNewValueForField("credit modifier")));
                if (currentUser.getRole() == 2)
                    ((Seller)currentUser).modifyCreditBy(Long.parseLong(view.getNewValueForField("credit modifier")));
            }
            else {
                throw new RuntimeException("Unknown command was passed to LoginMenu by client.view");
            }
        }
    }
     */

    private Server server = null;

    @Override
    public void start(Server server) throws IOException {
        this.server = server;
        String message = "start";
        //manager---username---password---firstName---lastName---email---phoneNumber
        //buyer---username---password---firstName---lastName---email---phoneNumber---credit
        //seller---username---password---firstName---lastName---email---phoneNumber---credit---company
        if (server.isAnyoneLoggedIn()) {
            Account account = server.getCurrentlyLoggedInUsers();
            if (account.getRole() == 1) {
                message += "buyer";
            } else if (account.getRole() == 2) {
                message += "seller";
            } else if (account.getRole() == 3) {
                message += "manager";
            } else {
                message += "support";
            }
            message += "---" + account.getUsername() + "---" + account.getPassword() + "---" + account.getFirstName() +
                    "---" + account.getLastName() + "---" + account.getEmailAddress() + "---" + account.getPhoneNumber();
            if (account.getRole() == 1) {
                message += "---";
                Buyer buyer = (Buyer) account;
                message += buyer.getCredit();
            } else if (account.getRole() == 2) {
                message += "---";
                Seller seller = (Seller) account;
                message += seller.getCredit();
                message += "---";
                message += seller.getCompanyName();
            }
        } else {
            message = "loginFirst";
        }
        sendMessage(message);
    }

    public void changeInformation(String data){
        Account account = server.getCurrentlyLoggedInUsers();

        if (data.startsWith("0"))
            account.setFirstName(data.substring(1));
        else if (data.startsWith("1"))
            account.setLastName(data.substring(1));
        else if (data.startsWith("2"))
            account.setEmailAddress(data.substring(1));
        else if (data.startsWith("3"))
            account.setPhoneNumber(data.substring(1));
        else if (data.startsWith("4"))
            account.setPassword(data.substring(1));
        /*
        //password---firstName---lastName---email---phoneNumber
        //password---firstName---lastName---email---phoneNumber
        //password---firstName---lastName---email---phoneNumber---company

        Account account = server.getCurrentlyLoggedInUsers();
        if(account.getRole()==1){
            account.setPassword(data.split("---")[0]);
            account.setFirstName(data.split("---")[1]);
            account.setLastName(data.split("---")[2]);
            account.setEmailAddress(data.split("---")[3]);
            account.setPhoneNumber(data.split("---")[4]);
        }else if(account.getRole()==2){
            account.setPassword(data.split("---")[0]);
            account.setFirstName(data.split("---")[1]);
            account.setLastName(data.split("---")[2]);
            account.setEmailAddress(data.split("---")[3]);
            account.setPhoneNumber(data.split("---")[4]);
            Seller seller = (Seller) account;
            seller.setCompanyName(data.split("---")[5]);
        }else if(account.getRole()==3){
            account.setPassword(data.split("---")[0]);
            account.setFirstName(data.split("---")[1]);
            account.setLastName(data.split("---")[2]);
            account.setEmailAddress(data.split("---")[3]);
            account.setPhoneNumber(data.split("---")[4]);
        }*/
    }

    private void sendMessage(String message) throws IOException {
        server.sendMessage("01-" + message);
    }

}
