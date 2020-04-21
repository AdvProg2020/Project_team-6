package model.account;

import model.logs.SellLog;

import java.util.ArrayList;

public class Seller extends Account {
    private long credit;
    private ArrayList<Integer> sellLogIds = new ArrayList<Integer>();
    private String companyName;

    public Seller(String username, String password, String firstName, String lastName, String emailAddress, String phoneNumber, String company) {
        super(username, password, firstName, lastName, emailAddress, phoneNumber);
        this.companyName = company;
        this.role = 2;
        credit = 0;
    }

    public void modifyCreditBy(long amount){
        credit += amount;
    }
}
