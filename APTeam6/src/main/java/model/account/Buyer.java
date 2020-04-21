package model.account;

import model.logs.BuyLog;

import java.util.ArrayList;

public class Buyer extends Account {
    private long credit;
    private ArrayList<BuyLog> buyLogs = new ArrayList<BuyLog>();

    public Buyer(String username, String password, String firstName, String lastName, String emailAddress, String phoneNumber) {
        super(username, password, firstName, lastName, emailAddress, phoneNumber);
        this.role = 1;
        credit = 0;
    }

    public boolean doesHaveEnoughMoney(long price){
        return credit >= price;
    }

    public void modifyCreditBy(long amount){
        credit += amount;
    }
}