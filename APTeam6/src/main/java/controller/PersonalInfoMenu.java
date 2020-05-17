package controller;

import model.account.Account;
import view.LoginMenuView;
import view.PersonalInfoMenuView;

public class PersonalInfoMenu {
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
            else {
                throw new RuntimeException("Unknown command was passed to LoginMenu by view");
            }
        }
    }
    //TODO: write here
}
