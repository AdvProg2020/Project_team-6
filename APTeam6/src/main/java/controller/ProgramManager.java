package controller;

import model.Account;

import java.util.ArrayList;
import java.util.HashMap;

public class ProgramManager {
    private static ProgramManager programManagerInstance = null;

    public static ProgramManager getProgramManagerInstance() {
        if (programManagerInstance == null)
            programManagerInstance = new ProgramManager();
        return programManagerInstance;
    }

    //////////////////////////////////////////////
    private HashMap<String, Account> allAccounts;
    Account currentlyLoggedInUser;

    private ProgramManager(){
        allAccounts = new HashMap<String, Account>();
        currentlyLoggedInUser = null;
        // TODO: Add arrayLists here
        loadFromFiles();
    }

    private void loadFromFiles(){
        // TODO: We really should do something about this...
    }

    //////////////////////////////////////////////

    public boolean isThereAccountWithUsername(String name){
        return allAccounts.get(name) != null;
    }

    public Account getAccountByUsername(String username){
        return allAccounts.get(username);
    }

    public boolean loginSuccessful(Account account){
        currentlyLoggedInUser = account;
    }
}
