package model.account;

import controller.ProgramManager;
import model.logs.SellLog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Seller extends Account implements Comparable<Seller> {
    private long credit;
    public static ArrayList<Integer> sellLogIds = new ArrayList<Integer>();
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

    private static int field = 1;
    private static ArrayList<Seller> sellerArrayList = new ArrayList<>();

    public int compareTo(Seller seller) {
        if (field == 1) {
            return -(seller.firstName.compareTo(this.firstName));
        } else if (field == 2) {
            return -(seller.lastName.compareTo(this.lastName));
        } else if (field == 3) {
            return -(seller.phoneNumber.compareTo(this.phoneNumber));
        } else if (field == 4) {
            return -(seller.emailAddress.compareTo(this.emailAddress));
        } else if (field == 5) {
            return -(seller.username.compareTo(this.username));
        } else {
            return 0;
        }
    }

    public ArrayList<Seller> sortSellers(int fieldSort) {
        /*
        How to use this method :
        fieldSort = 1 for firstName sort
        fieldSort = 2 for lastName sort
        fieldSort = 3 for phoneNumber sort
        fieldSort = 4 for emailAddress sort
        fieldSort = 5 for username sort
         */
        field = fieldSort;
        Collection<Account> values = ProgramManager.getProgramManagerInstance().getAllAccounts().values();
        ArrayList<Account> listOfValues = new ArrayList<>(values);
        for (int i = 0; i < listOfValues.size(); i++) {
            if (listOfValues.get(i).role==2) {
                sellerArrayList.add((Seller) listOfValues.get(i));
            }
        }
        Collections.sort(sellerArrayList);
        return sellerArrayList;
    }
}
