package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.account.Account;
import model.account.Buyer;
import model.logs.LogsInGeneral;
import model.product.Category;
import model.product.Product;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    private final String ADDRESS = "files\\";

    private File folder;
    private File accountsFile;
    private File logsFile;
    private File productsFile;
    private File categoriesFile;

    private HashMap<String, Account> allAccounts;
    private HashMap<Integer, LogsInGeneral> allLogs;
    private HashMap<Integer, Product> allProducts;
    private HashMap<Integer, Category> allCategories;

    private Account currentlyLoggedInUser;

    private ProgramManager() {
        folder = new File(ADDRESS);
        accountsFile = new File(ADDRESS + "accounts.json");
        logsFile = new File(ADDRESS + "logs.json");
        productsFile = new File(ADDRESS + "products.json");
        categoriesFile = new File(ADDRESS + "categories.json");

        allAccounts = new HashMap<String, Account>();
        allLogs = new HashMap<Integer, LogsInGeneral>();
        allProducts = new HashMap<Integer, Product>();
        currentlyLoggedInUser = null;
        // TODO: Add arrayLists here
    }

    public void loadFromFiles() {
        if (!folder.exists()){
            folder.mkdir();
            try {
                accountsFile.createNewFile();
                logsFile.createNewFile();
                productsFile.createNewFile();
                categoriesFile.createNewFile();
            }
            catch (Exception ignored){
                System.out.println("Failed to make files...");
            }
        }
        else {
            /*Gson gsonParser = new Gson();
            try {
                allAccounts = gsonParser.fromJson(Files.readString(Paths.get(ADDRESS + "accounts.json")), new TypeToken<HashMap<String, Account>>(){}.getType());
                allLogs = gsonParser.fromJson(Files.readString(Paths.get(ADDRESS + "logs.json")), new TypeToken<HashMap<String, LogsInGeneral>>(){}.getType());
                allProducts = gsonParser.fromJson(Files.readString(Paths.get(ADDRESS + "products.json")), new TypeToken<HashMap<String, Product>>(){}.getType());
                allCategories = gsonParser.fromJson(Files.readString(Paths.get(ADDRESS + "categories.json")), new TypeToken<HashMap<String, Category>>(){}.getType());
            }
            catch (Exception ignored){
                ignored.printStackTrace();
                System.out.println("Couldn't read from files...");
            }*/
        }
        // TODO: We really should do something about this...
    }

    public void saveToFiles() {
        Gson gsonCreator = new Gson();
        try {
            FileWriter accountsFileWriter = new FileWriter(accountsFile, false);
            accountsFileWriter.write(gsonCreator.toJson(allAccounts));
            accountsFileWriter.close();
        }
        catch (Exception ignored){
            System.out.println("Failed to save files...");
        }
        // TODO: We really should do something about this...
    }

    //////////////////////////////////////////////

    public boolean isThereAccountWithUsername(String name) {
        return allAccounts.get(name) != null;
    }

    public Account getAccountByUsername(String username) {
        return allAccounts.get(username);
    }

    public void loginSuccessful(Account account) {
        currentlyLoggedInUser = account;
    }

    public boolean isAnyoneLoggedIn() {
        return currentlyLoggedInUser != null;
    }

    public void logoutSuccessful() {
        currentlyLoggedInUser = null;
    }

    public void addAccountToList(String username, Account account) {
        allAccounts.put(username, account);
    }

    public void addLogToList(LogsInGeneral log) {
        allLogs.put(log.getLogId(), log);
    }

    public LogsInGeneral getLogByLogId(int id) {
        return allLogs.get(id);
    }
}

// Important note: this programManager doesn't and shouldn't have static methods. it is a singleton.