package server.model.account;

import server.controller.ProgramManager;

public class CustomerSupport extends Account {
    public CustomerSupport(String username, String password, String firstName, String lastName, String emailAddress, String phoneNumber) {
        super(username, password, firstName, lastName, emailAddress, phoneNumber);
        ProgramManager.getProgramManagerInstance().addAccountToList(this);
    }

}
