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

    public boolean checkPassword(String password){
        return password.equals(this.password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public byte getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
