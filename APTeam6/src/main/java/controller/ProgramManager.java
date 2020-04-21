package controller;

import model.account.Account;

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
    }

    public void loadFromFiles(){
        // TODO: We really should do something about this...
    }

    public void saveToFiles(){
        // TODO: We really should do something about this...
    }

    //////////////////////////////////////////////

    public boolean isThereAccountWithUsername(String name){
        return allAccounts.get(name) != null;
    }

    public Account getAccountByUsername(String username){
        return allAccounts.get(username);
    }

    public void loginSuccessful(Account account){
        currentlyLoggedInUser = account;
    }

    public boolean isAnyoneLoggedIn(){
        return currentlyLoggedInUser != null;
    }

    public void logoutSuccessful(){
        currentlyLoggedInUser = null;
    }

    public void addAccountToList(String username, Account account){
        allAccounts.put(username, account);
    }
}
