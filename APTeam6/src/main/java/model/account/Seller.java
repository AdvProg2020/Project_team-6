package model.account;

import controller.ProgramManager;
import model.requests.Request;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Seller extends Account implements Comparable<Seller>, Request {
    private long credit;
    public static ArrayList<Integer> sellLogIds = new ArrayList<Integer>();
    private String companyName;

    public Seller(String username, String password, String firstName, String lastName, String emailAddress, String phoneNumber, String company) {
        super(username, password, firstName, lastName, emailAddress, phoneNumber);
        this.companyName = company;
        this.role = 2;
        credit = 0;
        ProgramManager.getProgramManagerInstance().addRequestToList(this);
    }

    public void modifyCreditBy(long amount){
        credit += amount;
    }

    public long getCredit() {
        return credit;
    }

    private static int field = 1;
    private static ArrayList<Seller> sellerArrayList;

    public int compareTo(Seller seller) {
        switch (field) {
            case 1:
                return -(seller.firstName.compareTo(this.firstName));
            case 2:
                return -(seller.lastName.compareTo(this.lastName));
            case 3:
                return -(seller.phoneNumber.compareTo(this.phoneNumber));
            case 4:
                return -(seller.emailAddress.compareTo(this.emailAddress));
            case 5:
                return -(seller.username.compareTo(this.username));
            default:
                return 0;
        }
    }

    public static ArrayList<Seller> sortSellers(int fieldSort) {
        sellerArrayList = new ArrayList<>();
        /*
        How to use this method :
        fieldSort = 1 for firstName sort
        fieldSort = 2 for lastName sort
        fieldSort = 3 for phoneNumber sort
        fieldSort = 4 for emailAddress sort
        fieldSort = 5 for username sort
         */
        field = fieldSort;
        Collection<Account> values = ProgramManager.getProgramManagerInstance().getAllAccounts();
        ArrayList<Account> listOfValues = new ArrayList<>(values);
        for (int i = 0; i < listOfValues.size(); i++) {
            if (listOfValues.get(i).role==2) {
                sellerArrayList.add((Seller) listOfValues.get(i));
            }
        }
        Collections.sort(sellerArrayList);
        return sellerArrayList;
    }

    @Override
    public void accept() {
        ProgramManager.getProgramManagerInstance().addAccountToList(this);
        ProgramManager.getProgramManagerInstance().removeRequest(this);
    }

    @Override
    public void decline() {
        ProgramManager.getProgramManagerInstance().removeRequest(this);
    }

    public String getCompanyName() {
        return companyName;
    }

    public ArrayList<Integer> getSellLogIds() {
        return sellLogIds;
    }
}
