package model;

public abstract class Account {
    private String username;
    private String password;
    private String firstName = null;
    private String lastName = null;
    private String emailAddress = null;
    private String phoneNumber = null;
    private byte role;
    private long credit;

    public Account(String username) {
        this.username = username;
    }

    public boolean checkPassword(String password){
        return password == this.password;
    }
}
