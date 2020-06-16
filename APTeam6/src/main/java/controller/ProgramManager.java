package controller;

import com.gilecode.yagson.YaGson;
import com.google.gson.reflect.TypeToken;
import model.account.Manager;
import model.account.Seller;
import model.product.Off;
import model.requests.OffRequest;
import model.requests.ProductRequest;
import model.requests.Request;
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
    public final String PROGRAM_NAME = "supermarket";
    public boolean existManager = false;
    private final String ADDRESS = "files\\";

    private File folder;
    private File accountsFile;
    private File logsFile;
    private File productsFile;
    private File categoriesFile;
    private File discountCodesFile;
    private File requestsFile;
    private File offsFile;

    private HashMap<String, Account> allAccounts;
    private HashMap<Integer, LogsInGeneral> allLogs;
    private HashMap<Integer, Product> allProducts;
    private HashMap<String, Category> allCategories;
    private HashMap<String, DiscountCode> allDiscountCodes;
    private ArrayList<Request> allRequests;
    private HashMap<Integer,Off> allOffs;

    private Account currentlyLoggedInUser;

    private HashMap<Product, Integer> buyBasket;


    //------------------------ Test----------
    public void createAllAccountHashMapForTest() {
        allAccounts = new HashMap<>();
    }

    public void createAllRequestHashMapForTest() {
        allRequests = new ArrayList<>();
    }

    public void createAllCategoriesHashMapForTest() {
        allCategories = new HashMap<>();
    }

    public void createAllProductHashMap() {
        allProducts = new HashMap<>();
    }
    public void createAllOffsHashMap(){allOffs = new HashMap<>();}
    //------------------------ Test----------

    private ProgramManager() {
        folder = new File(ADDRESS);
        accountsFile = new File(ADDRESS + "accounts.json");
        logsFile = new File(ADDRESS + "logs.json");
        productsFile = new File(ADDRESS + "products.json");
        categoriesFile = new File(ADDRESS + "categories.json");
        discountCodesFile = new File(ADDRESS + "discountCodes.json");
        requestsFile = new File(ADDRESS + "requests.json");
        offsFile = new File(ADDRESS + "offs.json");

        currentlyLoggedInUser = null;

        buyBasket = new HashMap<>();
    }

    public void loadFromFiles() {
        if (!folder.exists()) {
            folder.mkdir();
            try {
                accountsFile.createNewFile();
                logsFile.createNewFile();
                productsFile.createNewFile();
                categoriesFile.createNewFile();
                discountCodesFile.createNewFile();
                requestsFile.createNewFile();
                offsFile.createNewFile();
            } catch (Exception ignored) {
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
                if (!requestsFile.exists())
                    requestsFile.createNewFile();
                if (!offsFile.exists())
                    offsFile.createNewFile();
            } catch (Exception ignored) {
                System.out.println("Failed to make files...");
            }

            YaGson gsonParser = new YaGson();
            try {
                allAccounts = gsonParser.fromJson(Files.readString(Paths.get(ADDRESS + "accounts.json")), new TypeToken<HashMap<String, Account>>(){}.getType());
                allLogs = gsonParser.fromJson(Files.readString(Paths.get(ADDRESS + "logs.json")), new TypeToken<HashMap<Integer, LogsInGeneral>>(){}.getType());
                allProducts = gsonParser.fromJson(Files.readString(Paths.get(ADDRESS + "products.json")), new TypeToken<HashMap<Integer, Product>>(){}.getType());
                allCategories = gsonParser.fromJson(Files.readString(Paths.get(ADDRESS + "categories.json")), new TypeToken<HashMap<String, Category>>(){}.getType());
                allDiscountCodes = gsonParser.fromJson(Files.readString(Paths.get(ADDRESS + "discountCodes.json")), new TypeToken<HashMap<String, DiscountCode>>(){}.getType());
                allRequests = gsonParser.fromJson(Files.readString(Paths.get(ADDRESS + "requests.json")), new TypeToken<ArrayList<Request>>(){}.getType());
                allOffs = gsonParser.fromJson(Files.readString(Paths.get(ADDRESS + "offs.json")), new TypeToken<HashMap<Integer, Off>>(){}.getType());
            }
            catch (Exception ignored){
                ignored.printStackTrace();
                System.out.println("Couldn't read from files...");
            }
        }
        if (allAccounts == null) {
            allAccounts = new HashMap<>();
            existManager = false;
        }else{
            allAccounts.forEach((s, account) ->{
                if (account.getRole()==3){
                    existManager = true;
                }
            });
        }
        if (allLogs == null)
            allLogs = new HashMap<>();
        if (allProducts == null)
            allProducts = new HashMap<>();
        if (allCategories == null)
            allCategories = new HashMap<>();
        if (allDiscountCodes == null)
            allDiscountCodes = new HashMap<>();
        if (allRequests == null)
            allRequests = new ArrayList<>();
        if (allOffs == null)
            allOffs = new HashMap<>();
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
            fileWriter = new FileWriter(requestsFile, false);
            fileWriter.write(gsonCreator.toJson(allRequests));
            fileWriter.close();
            fileWriter = new FileWriter(offsFile, false);
            fileWriter.write(gsonCreator.toJson(allOffs));
            fileWriter.close();
        } catch (Exception ignored) {
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
        if (account.getRole() == 1) {
            ((Buyer) account).addProductToBuyBasket(buyBasket);
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

    public void addAccountToList(Account account) {
        allAccounts.put(account.getUsername(), account);
    }

    public void addLogToList(LogsInGeneral log) {
        allLogs.put(log.getLogId(), log);
    }

    public LogsInGeneral getLogByLogId(int id) {
        return allLogs.get(id);
    }

    public void removeProduct(int productId) {
        allProducts.remove(productId);
        Product product = allProducts.get(productId);
        allCategories.get(product.getCategoryName()).getSubCategoryByName(product.getSubCategoryName()).removeProduct(productId);
    }

    public void removeProduct(Product product) {
        allProducts.remove(product.getId());
        allCategories.get(product.getCategoryName()).getSubCategoryByName(product.getSubCategoryName()).removeProduct(product.getId());
    }

    /**
     * this method adds the product to productsList and adds it to its subCategory as well
     */
    public void addProductToList(Product product) {
        try {
            allCategories.get(product.getCategoryName()).getSubCategoryByName(product.getSubCategoryName()).addProduct(product.getId());
            allProducts.put(product.getId(), product);
        } catch (Exception e) {
            System.out.println("Fake address");
        }
    }

    public void addOffToList(Off off){
        allOffs.put(off.getOffId(),off);
    }

    public Product getProductById(int id) {
        return allProducts.get(id);
    }

    public void deleteDiscountCode(DiscountCode discountCode) {
        allDiscountCodes.remove(discountCode);
    }

    public void deleteAccount(String username) {
        allAccounts.remove(username);
    }

    public Account getCurrentlyLoggedInUser() {
        return currentlyLoggedInUser;
    }

    /**
     * this function gets current user's role
     *
     * @return 0 if no one is logged in
     */
    public byte getCurrentlyLoggedInUserRole() {
        if (currentlyLoggedInUser != null)
            return currentlyLoggedInUser.getRole();
        return 0;
    }

    public Collection<Account> getAllAccounts() {
        return allAccounts.values();
    }

    public ArrayList<Request> getAllRequests() {
        return allRequests;
    }

    public Collection<Category> getAllCategories() {
        return allCategories.values();
    }


    public LocalDateTime parsingStringToDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
        return dateTime;
    }

    public void addDiscountCodeToArrayList(DiscountCode discountCode) {
        allDiscountCodes.put(discountCode.getCode(), discountCode);
    }

    public DiscountCode getDiscountCodeByCode(String code) {
        return allDiscountCodes.get(code);
    }

    /**
     * If a buyer is logged in, this method will add the product to their buyBasket, otherwise it will be added to ProgramManager buyBasket.
     *
     * @param product the product to be added
     */
    public void addToCurrentBuyBasket(Product product, int count) {
        if (currentlyLoggedInUser == null) {
            if (buyBasket.containsKey(product)) {
                count += buyBasket.get(product);
                buyBasket.replace(product, count);
            }
            else
                buyBasket.put(product, count);
        }
        else if (currentlyLoggedInUser.getRole() == 1)
            ((Buyer) currentlyLoggedInUser).addProductToBuyBasket(product, count);
    }

    public void addRequestToList(Request request) {
        allRequests.add(request);
    }

    public void removeRequest(Request request) {
        allRequests.remove(request);
    }

    public void addCategory(Category category) {
        allCategories.put(category.getName(), category);
    }

    public void showSellerCompanyInfo() {
        ((Seller) currentlyLoggedInUser).getClass();
    }

    //TODO: MKH don't print things here
    public void showAllRequests() {
        for (int i = 0; i < allRequests.size(); i++) {
            if (allRequests.get(i) instanceof ProductRequest) {
                System.out.println(i + ". " + allRequests.get(i) + "is a ProductRequest");
            }
            else if (allRequests.get(i) instanceof OffRequest) {
                System.out.println(i + ". " + allRequests.get(i) + "is an OffRequest");
            }
        }
    }

    public void acceptRequests(int id) {
        if (allRequests.get(id) instanceof ProductRequest) {
            allRequests.get(id).accept();
        }
        else if(allRequests.get(id) instanceof OffRequest){}
    }

    public void declineRequests(int id) {
        if (allRequests.get(id) instanceof ProductRequest) {
            allRequests.get(id).decline();
        }
    }

    public void detailsOfRequest(int id) {
        if (allRequests.get(id) instanceof ProductRequest) {
            ((ProductRequest) allRequests.get(id)).showDetails();
        }
        else if (allRequests.get(id) instanceof OffRequest) {
            ((OffRequest) allRequests.get(id)).showDetails();
        }
    }

    public void addOffToHashMap(Off off){
        allOffs.put(off.getOffId(),off);
    }

    public Off getOffById(int id){
        return allOffs.get(id);
    }

    public void showOff(Off off){
        System.out.println("the Off's Id is " + off.getOffId());
        System.out.println("the Off's StartDate is " + off.getStartDate());
        System.out.println("the Off's EndDate is " + off.getEndDate());
        System.out.println("the Off's amount is " + off.getOffAmount());
    }

    public Category getCategoryByName(String name){
        return allCategories.get(name);
    }

    public void removeCategory(Category category){
        allCategories.remove(category);
    }
}

// Important note: this programManager doesn't and shouldn't have static methods. it is a singleton.