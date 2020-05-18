package model.account;

import controller.ProgramManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Manager extends Account implements Comparable<Manager>{
    public Manager(String username, String password, String firstName, String lastName, String emailAddress, String phoneNumber) {
        super(username, password, firstName, lastName, emailAddress, phoneNumber);
        this.role = 3;
        ProgramManager.getProgramManagerInstance().addAccountToList(username, this);
    }

    public int compareTo(Manager manager) {
        if (field == 1) {
            return -(manager.firstName.compareTo(this.firstName));
        } else if (field == 2) {
            return -(manager.lastName.compareTo(this.lastName));
        } else if (field == 3) {
            return -(manager.phoneNumber.compareTo(this.phoneNumber));
        } else if (field == 4) {
            return -(manager.emailAddress.compareTo(this.emailAddress));
        } else if (field == 5) {
            return -(manager.username.compareTo(this.username));
        } else {
            return 0;
        }
    }

    private static int field = 1;
    private static ArrayList<Manager> managerArrayList = new ArrayList<>();

    public static ArrayList<Manager> sortManagers(int fieldSort) {
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
            if (listOfValues.get(i).role==3) {
                managerArrayList.add((Manager) listOfValues.get(i));
            }
        }
        Collections.sort(managerArrayList);
        return managerArrayList;
    }
}
