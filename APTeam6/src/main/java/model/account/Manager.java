package model.account;

public class Manager extends Account {
    public Manager(String username, String password, String firstName, String lastName, String emailAddress, String phoneNumber) {
        super(username, password, firstName, lastName, emailAddress, phoneNumber);
        this.role = 3;
    }
}
