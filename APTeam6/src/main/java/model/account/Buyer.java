package model.account;

import model.logs.BuyLog;
import model.logs.LogsInGeneral;

import java.util.ArrayList;
import java.util.HashMap;

public class Buyer extends Account {
    private long credit;
    public static ArrayList<Integer> buyLogIds = new ArrayList<Integer>();

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