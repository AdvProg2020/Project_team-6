package model.account;

import controller.ProgramManager;

public abstract class Account {
    protected String username;
    protected String password;
    protected String firstName = null;
    protected String lastName = null;
    protected String emailAddress = null;
    protected String phoneNumber = null;
    protected byte role;

    public Account(String username, String password, String firstName, String lastName, String emailAddress, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        ProgramManager.getProgramManagerInstance().addAccountToList(username, this);
    }

    public Account(){}

    public boolean checkPassword(String password){
        return password.equals(this.password);
    }
}
