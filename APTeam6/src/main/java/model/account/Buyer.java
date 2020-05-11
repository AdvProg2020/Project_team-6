package model.account;

import controller.ProgramManager;
import model.logs.BuyLog;
import model.logs.LogsInGeneral;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Buyer extends Account implements Comparable<Buyer> {
    private long credit;
    public static ArrayList<Integer> buyLogIds = new ArrayList<Integer>();

    public Buyer(String username, String password, String firstName, String lastName, String emailAddress, String phoneNumber) {
        super(username, password, firstName, lastName, emailAddress, phoneNumber);
        this.role = 1;
        credit = 0;
    }

    public boolean doesHaveEnoughMoney(long price) {
        return credit >= price;
    }

    public void modifyCreditBy(long amount) {
        credit += amount;
    }

    public int compareTo(Buyer buyer) {
        if (field == 1) {
            return -(buyer.firstName.compareTo(this.firstName));
        } else if (field == 2) {
            return -(buyer.lastName.compareTo(this.lastName));
        } else if (field == 3) {
            return -(buyer.phoneNumber.compareTo(this.phoneNumber));
        } else if (field == 4) {
            return -(buyer.emailAddress.compareTo(this.emailAddress));
        } else if (field == 5) {
            return -(buyer.username.compareTo(this.username));
        } else {
            return 0;
        }
    }

    private static int field = 1;
    private static ArrayList<Buyer> buyerArrayList = new ArrayList<>();

    public ArrayList<Buyer> sortBuyers(int fieldSort) {
        field = fieldSort;
        for (int i = 0; i < ProgramManager.getProgramManagerInstance().getAllAccounts().size(); i++) {
            if (ProgramManager.getProgramManagerInstance().getAllAccounts().get(i).role==1) {
                buyerArrayList.add((Buyer) ProgramManager.getProgramManagerInstance().getAllAccounts().get(i));
            }
        }
        Collections.sort(buyerArrayList);
        return buyerArrayList;
    }
}