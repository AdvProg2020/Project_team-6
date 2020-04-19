package model.account;

public class Seller extends Account {
    private long credit;

    public Seller(String username, String password, String firstName, String lastName, String emailAddress, String phoneNumber) {
        super(username, password, firstName, lastName, emailAddress, phoneNumber);
        this.role = 2;
        credit = 0;
    }
}
