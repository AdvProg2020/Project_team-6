package server.model.account;

public abstract class Account {
    protected String username;
    protected String password;
    protected String firstName = null;
    protected String lastName = null;
    protected String emailAddress = null;
    protected String phoneNumber = null;
    protected byte role;
    protected boolean isOnline;

    public Account(String username, String password, String firstName, String lastName, String emailAddress, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
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

    /**
     * returns account's role as a number
     * @return 1 - if Buyer <br/>
     *         2 - if Seller <br/>
     *         3 - if Manager
     */
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

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public boolean isOnline() {
        return isOnline;
    }
}
