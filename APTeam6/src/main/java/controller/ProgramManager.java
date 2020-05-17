package controller;

import com.gilecode.yagson.YaGson;
import com.google.gson.reflect.TypeToken;
import model.account.Account;
import model.account.Buyer;
import model.logs.LogsInGeneral;
import model.product.Category;
import model.product.DiscountCode;
import model.product.Product;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.time.LocalDateTime;
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
    private File discountCodesFile;

    private HashMap<String, Account> allAccounts;
    private HashMap<Integer, LogsInGeneral> allLogs;
    private HashMap<Integer, Product> allProducts;
    private HashMap<Integer, Category> allCategories;
    private HashMap<String, DiscountCode> allDiscountCodes;
    private Account currentlyLoggedInUser;

    private ArrayList<Product> buyBasket;

    private ProgramManager() {
        folder = new File(ADDRESS);
        accountsFile = new File(ADDRESS + "accounts.json");
        logsFile = new File(ADDRESS + "logs.json");
        productsFile = new File(ADDRESS + "products.json");
        categoriesFile = new File(ADDRESS + "categories.json");
        discountCodesFile = new File(ADDRESS + "discountCodes.json");

        currentlyLoggedInUser = null;

        buyBasket = new ArrayList<>();
    }

    public void loadFromFiles() {
        if (!folder.exists()){
            folder.mkdir();
            try {
                accountsFile.createNewFile();
                logsFile.createNewFile();
                productsFile.createNewFile();
                categoriesFile.createNewFile();
                discountCodesFile.createNewFile();
            }
            catch (Exception ignored){
                System.out.println("Failed to make files...");
            }
        }
        else {
            try {
                if (!accountsFile.exists())
                    accountsFile.createNewFile();
                if (!logsFile.exists())
                    logsFile.createNewFile();
                if (!productsFile.exists())
                    productsFile.createNewFile();
                if (!categoriesFile.exists())
                    categoriesFile.createNewFile();
                if (!discountCodesFile.exists())
                    discountCodesFile.createNewFile();
            }
            catch (Exception ignored){
                System.out.println("Failed to make files...");
            }

            YaGson gsonParser = new YaGson();
            try {
                allAccounts = gsonParser.fromJson(Files.readString(Paths.get(ADDRESS + "accounts.json")), new TypeToken<HashMap<String, Account>>(){}.getType());
                allLogs = gsonParser.fromJson(Files.readString(Paths.get(ADDRESS + "logs.json")), new TypeToken<HashMap<String, LogsInGeneral>>(){}.getType());
                allProducts = gsonParser.fromJson(Files.readString(Paths.get(ADDRESS + "products.json")), new TypeToken<HashMap<String, Product>>(){}.getType());
                allCategories = gsonParser.fromJson(Files.readString(Paths.get(ADDRESS + "categories.json")), new TypeToken<HashMap<String, Category>>(){}.getType());
                allDiscountCodes = gsonParser.fromJson(Files.readString(Paths.get(ADDRESS + "discountCodes.json")), new TypeToken<HashMap<String, DiscountCode>>(){}.getType());
                if (allAccounts == null)
                    allAccounts = new HashMap<>();
                if (allLogs == null)
                    allLogs = new HashMap<>();
                if (allProducts == null)
                    allProducts = new HashMap<>();
                if (allCategories == null)
                    allCategories = new HashMap<>();
                if (allDiscountCodes == null)
                    allDiscountCodes = new HashMap<>();
            }
            catch (Exception ignored){
                ignored.printStackTrace();
                System.out.println("Couldn't read from files...");
            }
        }
    }

    public void saveToFiles() {
        YaGson gsonCreator = new YaGson();
        try {
            FileWriter fileWriter = new FileWriter(accountsFile, false);
            fileWriter.write(gsonCreator.toJson(allAccounts));
            fileWriter.close();
            fileWriter = new FileWriter(logsFile, false);
            fileWriter.write(gsonCreator.toJson(allLogs));
            fileWriter.close();
            fileWriter = new FileWriter(productsFile, false);
            fileWriter.write(gsonCreator.toJson(allProducts));
            fileWriter.close();
            fileWriter = new FileWriter(categoriesFile, false);
            fileWriter.write(gsonCreator.toJson(allCategories));
            fileWriter.close();
            fileWriter = new FileWriter(discountCodesFile, false);
            fileWriter.write(gsonCreator.toJson(allDiscountCodes));
            fileWriter.close();
        }
        catch (Exception ignored){
            System.out.println("Failed to save files...");
        }
    }

    //////////////////////////////////////////////

    public Collection<LogsInGeneral> getAllLogs() {
        return allLogs.values();
    }

    public boolean isThereAccountWithUsername(String name) {
        return allAccounts.get(name) != null;
    }

    public Account getAccountByUsername(String username) {
        return allAccounts.get(username);
    }

    public void loginSuccessful(Account account) {
        currentlyLoggedInUser = account;
        if (account.getRole() == 1){
            ((Buyer)account).addProductToBuyBasket(buyBasket);
            buyBasket.clear();
        }
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

    public void removeProductById(int productId) {
        allProducts.remove(productId);
    }

    //TODO: I think it would be better if you create the code elsewhere then pass it to ProgramManager
    public void createDiscountCode(){

    }

    public void deleteAccount(String username){
        allAccounts.remove(username);
    }

    public Account getCurrentlyLoggedInUser(){
        return currentlyLoggedInUser;
    }

    public Collection<Account> getAllAccounts(){
        return allAccounts.values();
    }

    public Collection<Category> getAllCategories(){
        return allCategories.values();
    }


    public LocalDateTime parsingStringToData(String input){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
        return dateTime;
    }
    public void addDiscountCodeToArrayList(DiscountCode discountCode){
        allDiscountCodes.put(discountCode.getCode(),discountCode);
    }

    /**
     * If a buyer is logged in, this method will add the product to their buyBasket, otherwise it will be added to ProgramManager buyBasket.
     * @param product the product to be added
     */
    public void addToCurrentBuyBasket(Product product){
        if (currentlyLoggedInUser == null)
            buyBasket.add(product);
        else if (currentlyLoggedInUser.getRole() == 1)
            ((Buyer)currentlyLoggedInUser).addProductToBuyBasket(product);
    }
}

// Important note: this programManager doesn't and shouldn't have static methods. it is a singleton.