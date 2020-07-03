package server.controller;

import server.model.account.Account;
import server.model.account.Buyer;
import server.model.account.Seller;
import client.view.PersonalInfoMenuView;

public class PersonalInfoMenu implements Parent{
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
}
