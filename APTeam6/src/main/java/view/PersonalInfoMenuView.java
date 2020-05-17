package view;

import controller.ProgramManager;
import model.account.Account;
import model.account.Buyer;
import model.account.Seller;

public class PersonalInfoMenuView {

    public PersonalInfoMenuView(){
        Account currentUser = ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUser();
        System.out.println("Your personal info is as follows:" +
                "\n\tUsername: " + currentUser.getUsername() +
                "\n\tFirst name: " + currentUser.getFirstName() +
                "\n\tLast name: " + currentUser.getLastName() +
                "\n\tEmail address: " + currentUser.getEmailAddress() +
                "\n\tTelephone num: " + currentUser.getPhoneNumber() +
                "\n\tRole: " + currentUser.getRole());
        if (currentUser.getRole() == 2){
            System.out.println("\tCredit: " + ((Seller) currentUser).getCredit());
        }
        else if (currentUser.getRole() == 1){
            System.out.println("\tCredit: " + ((Buyer) currentUser).getCredit());
        }
    }

    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.equals("help")) {
                showHelp();
            }
            else if (command.equals("back")) {
                return command;
            }
            else if (command.matches("edit (password|firstName|lastName|phoneNumber|email)")) {
                return command;
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }

    private void showHelp() {
        System.out.println("List of commands:\n\tedit [password/firstName/lastName/phoneNumber/email]\n\tback");
    }

    public String getNewValueForField(String field){
        System.out.println("Enter your new " + field);
        return Input.getInput();
    }
}
