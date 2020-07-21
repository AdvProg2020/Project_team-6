package server.controller;

//package server.controller;
//
//import client.view.old.CategoriesAndSubCategoriesMenuView;
//import javafx.stage.Stage;
//import server.Server;
//import server.model.account.Account;
//import server.model.logs.LogsInGeneral;
//import server.model.product.*;
//import client.view.old.SingleProductScreenView;
//import server.model.requests.OffRequest;
//import server.model.requests.ProductRequest;
//import server.model.requests.Request;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//
public class SingleProductScreen {
////    Product product;
////    public SingleProductScreen(Product product){
////        this.product = product;
////    }
////    private Server server = null;
////    @Override
////    public void start(Server server) throws IOException {
////        this.server = server;
////        sendMessage("start");
////    }
////
////    private void sendMessage(String message) throws IOException {
////        server.sendMessage("02-" + message);
////    }
////
////    public void start(Product product) throws Exception {
////    }
////private Server server = null;
////
////    @Override
////    public void start(Server server) throws IOException {
////        this.server = server;
////        String message = "";
////        for (int i = 0; i < allCategoriesArrayList.size(); i++) {
////            message = message + "---" + i + ". " + allCategoriesArrayList.get(i);
////        }
////        sendMessage(message);
////    }
////
////    private void sendMessage(String message) throws IOException {
////        server.sendMessage("12-" + message);
////    }
////
////    ///////////////////////////////////////////
////    private CategoriesAndSubCategoriesMenuView view;
////    private Category currentCategory = null;
////    private SubCategory currentSubCategory = null;
////
////    private ArrayList<Category> allCategoriesArrayList;
////    private ArrayList<SubCategory> allSubCategoriesArrayList;
////    private ArrayList<Product> allProductsArrayList;
////
////    private void updateCategoriesArrayList() {
////        allCategoriesArrayList = new ArrayList<>(ProgramManager.getProgramManagerInstance().getAllCategories());
//
////        categorySort(allCategoriesArrayList);
////    }
////
////    private void updateSubCategoriesArrayList() {
////        allSubCategoriesArrayList = new ArrayList<>(currentCategory.getAllSubCategories());
//
////        subCategorySort(allSubCategoriesArrayList);
////    }
////
////    private void updateProductsArrayList() {
////        ArrayList<Integer> productIds = currentSubCategory.getAllProductIds();
////        allProductsArrayList = new ArrayList<>();
////        ProgramManager programManager = ProgramManager.getProgramManagerInstance();
////        for (Integer id : productIds) {
////            allProductsArrayList.add(programManager.getProductById(id));
////        }
//
////    }
////
////
////    ///////////////////////////////////
////    public void editCategory(int index, String newName) {
////        if (server.getCurrentlyLoggedInUsers().getRole() == 3) {
////            Category category = allCategoriesArrayList.get(index);
////            category.setName(newName);
////            updateCategoriesArrayList();
////            try {
////                sendMessage("edited");
////            } catch (IOException e) {
////                System.err.println("error occurred");
////            }
////        }
////    }
////
////    public void editSubCategory(int index, String newName) {
////        if (server.getCurrentlyLoggedInUsers().getRole() == 3) {
////            SubCategory subCategory = allSubCategoriesArrayList.get(index);
////            subCategory.setName(newName);
////            updateSubCategoriesArrayList();
////            try {
////                sendMessage("edited");
////            } catch (IOException e) {
////                System.err.println("error occurred");
////            }
////        }
////    }
////
////    public void addCategory(String Data) throws IOException {
////        if (server.getCurrentlyLoggedInUsers().getRole() == 3) {
////            if (!ProgramManager.getProgramManagerInstance().getAllCategories().contains(ProgramManager.getProgramManagerInstance().getCategoryByName(Data.split("---")[0]))) {
////                ArrayList<String> additionalAttributes = new ArrayList<>();
////                String[] dataSplit = Data.split("---");
////                for (int i = 1; i < dataSplit.length; i++) {
////                    additionalAttributes.add(dataSplit[i]);
////                }
////                allCategoriesArrayList.add(new Category(dataSplit[0], additionalAttributes));
////                updateCategoriesArrayList();
////                sendMessage("Added");
////            } else
////                sendMessage("duplicateCategory");
////        }
////    }
////
////    public void addSubCategory(String Data) {
////        if (server.getCurrentlyLoggedInUsers().getRole() == 3) {
////            if (!currentCategory.getAllSubCategories().contains(currentCategory.getSubCategoryByName(Data.split("---")[0]))) {
////                ArrayList<String> additionalAttributes = new ArrayList<>();
////                String[] dataSplit = Data.split("---");
////                for (int i = 1; i < dataSplit.length; i++) {
////                    additionalAttributes.add(dataSplit[i]);
////                }
////                allSubCategoriesArrayList.add(new SubCategory(dataSplit[0], additionalAttributes));
////                updateSubCategoriesArrayList();
////                try {
////                    sendMessage("Added");
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            } else {
////                try {
////                    sendMessage("duplicateSubCategory");
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }
////        }
////    }
////
////    public void removeCategory(int index) {
////        if (server.getCurrentlyLoggedInUsers().getRole() == 3) {
////            allCategoriesArrayList.remove(index);
////            updateCategoriesArrayList();
////            try {
////                sendMessage("removed");
////            } catch (IOException e) {
////                System.err.println("error occurred");
////            }
////        }
////    }
////
////    public void removeSubCategory(int index) {
////        if (server.getCurrentlyLoggedInUsers().getRole() == 3) {
////            allSubCategoriesArrayList.remove(index);
////            updateSubCategoriesArrayList();
////            try {
////                sendMessage("removed");
////            } catch (IOException e) {
////                System.err.println("error occurred");
////            }
////        }
////    }
////
////    public void openCategory(int index) {
////        Category tempCategory = allCategoriesArrayList.get(index);
////        currentCategory = tempCategory;
////        try {
////            String categoriesMessage = "";
////            for (SubCategory allSubCategory : currentCategory.getAllSubCategories()) {
////                categoriesMessage = categoriesMessage + allSubCategory.getName() + "---";
////            }
////            for (String additionalAttribute : currentCategory.getAdditionalAttributes()) {
////                categoriesMessage = categoriesMessage + additionalAttribute + "---";
////            }
////            sendMessage(categoriesMessage);
////        } catch (IOException e) {
////            System.err.println("error occurred");
////        }
////    }
////    public void openSubCategory(int index) {
////        SubCategory tempSubCategory = allSubCategoriesArrayList.get(index);
////        currentSubCategory = tempSubCategory;
////        try {
////            String subCategoriesMessage = "";
////            for (Integer allProductId : currentSubCategory.getAllProductIds()) {
////                subCategoriesMessage = subCategoriesMessage + ProgramManager.getProgramManagerInstance().getProductById(allProductId).getName() + "---";
////            }
////            for (String additionalAttribute : currentSubCategory.getAdditionalAttributes()) {
////                subCategoriesMessage = subCategoriesMessage + additionalAttribute + "---";
////            }
////            sendMessage(subCategoriesMessage);
////        } catch (IOException e) {
////            System.err.println("error occurred");
////        }
////    }
////    public void openProduct(int index){
////        Product tempProduct = allProductsArrayList.get(index);
////        try {
////            //name---categoryName---SubCategoryName---description---price
////            sendMessage(tempProduct.getName() + "---" + tempProduct.getCategoryName() + "---" + tempProduct.getSubCategoryName() + "---" + tempProduct.getDescription() + "---" + tempProduct.getPrice());
////        } catch (IOException e) {
////            System.err.println("error occurred");
////        }
////    }
////    public void addToBuyBasket(int index,int count){
////        Product tempProduct = allProductsArrayList.get(index);
////        try {
////            server.addToCurrentBuyBasket(tempProduct,count);
////            sendMessage("added to buy basket");
////        } catch (IOException e) {
////            System.err.println("error occurred");
////        }
////
////    }
////
////    public void categorySort(ArrayList<Category> allCategoriesArrayList) {
////
////    }
////    public void subCategorySort(ArrayList<SubCategory> allSubCategoriesArrayList) {
////        //TOD
////    }
//    /*
//    /*
//    private static ShowDiscountCode showDiscountCode = null;
//    public static ShowDiscountCode getShowDiscountCodeInstance() {
//        if (showDiscountCode == null)
//            showDiscountCode= new ShowDiscountCode();
//        return showDiscountCode;
//    }
//    //////////////////////////
//    private ShowDiscountCodeView view;
//
//    public void start(){
//        view = new ShowDiscountCodeView();
//        String command = null;
//        while (true) {
//            command = view.getInputCommand();
//            if (command.matches("view discount code \\.+")) {
//                viewDiscountCode(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[3]));
//            }
//            else if (command.matches("edit discount code \\.+")) {
//                if(command.split("\\s")[3].equalsIgnoreCase("code")) {
//                    editDiscountCodeCode(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[4]),command.split("\\s")[5]);
//                }
//                else if(command.split("\\s")[3].equalsIgnoreCase("startDate")){
//                    editDiscountCodeStartDate(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[4]),command.split("\\s")[5]);
//                }
//                else if(command.split("\\s")[3].equalsIgnoreCase("endDate")){
//                    editDiscountCodeEndDate(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[4]),command.split("\\s")[5]);
//                }
//                else if(command.split("\\s")[3].equalsIgnoreCase("percentage")){
//                    editDiscountCodePercentage(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[4]),Integer.parseInt(command.split("\\s")[5]));
//                }
//                else{
//                    editDiscountCodeRepetitionTime(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[4]),Integer.parseInt(command.split("\\s")[5]));
//                }
//            }
//            else if (command.equals("remove discount code \\.+")){
//                removeDiscountCode(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[3]));
//            }
//            else if (command.equals("back")){
//                return;
//            }
//            else {
//                throw new RuntimeException("Unknown command was passed to DiscountCode by client.view");
//            }
//        }
//    }
//
//
//    public void viewDiscountCode(DiscountCode discountCode){
//        view.viewDiscountCode1(discountCode);
//        view.viewDiscountCode2(discountCode);
//        view.viewDiscountCode3(discountCode);
//        view.viewDiscountCode4(discountCode);
//        view.viewDiscountCode5(discountCode);
//        view.viewDiscountCode6(discountCode);
//    }
//    public void editDiscountCodeCode(DiscountCode discountCode, String code){
//        view.editDiscountCodeCode(discountCode,code);
//    }
//    public void removeDiscountCode(DiscountCode discountCode){
//        view.removeDiscountCode(discountCode);
//    }
//    public void editDiscountCodeStartDate(DiscountCode discountCode,String startDate){
//        view.editDiscountCodeStartDate(discountCode,startDate);
//    }
//    public void editDiscountCodeEndDate(DiscountCode discountCode,String endDate){
//        view.editDiscountCodeEndDate(discountCode,endDate);
//    }
//    public void editDiscountCodePercentage(DiscountCode discountCode,int percentage){
//        view.editDiscountCodePercentage(discountCode,percentage);
//    }
//    public void editDiscountCodeRepetitionTime(DiscountCode discountCode, int repetitionTime){
//        view.editDiscountCodeRepetitionTime(discountCode,repetitionTime);
//    }
//
//
//    private Server server = null;
//
//    @Override
//    public void start(Server server) throws IOException {
//        this.server = server;
//        StringBuilder string = new StringBuilder();
//        HashMap<String, DiscountCode> allDiscountCodes = ProgramManager.getProgramManagerInstance().getAllDiscountCodes();
//        for (String discountCode : allDiscountCodes.keySet()) {
//            string.append(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode).toString());
//        }
//        sendMessage(string.toString());
//    }
//
//    private void sendMessage(String message) throws IOException {
//        server.sendMessage("04-" + message);
//    }
//
//    public void showDiscountCode(String data) throws IOException {
//        HashMap<String,DiscountCode> discountCodeHashMap = ProgramManager.getProgramManagerInstance().getAllDiscountCodes();
//        if(discountCodeHashMap.containsKey(data)) {
//            DiscountCode tempDiscountCode = ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(data);
//            sendMessage("id :" + tempDiscountCode.getId() + "\nStart Data :" + tempDiscountCode.getStart() + " \nEnd Date :" + tempDiscountCode.getEnd() + "\nRepetition Time :" + tempDiscountCode.getRepetitionTime() + "\nPercentage :" + tempDiscountCode.getPercentage());
//        }else{
//            sendMessage("incorrectCode");
//        }
//    }
//
//    public void removeDiscountCode(String data) throws IOException {
//        HashMap<String,DiscountCode> discountCodeHashMap = ProgramManager.getProgramManagerInstance().getAllDiscountCodes();
//        if(discountCodeHashMap.containsKey(data)) {
//            ProgramManager.getProgramManagerInstance().deleteDiscountCode(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(data));
//        }else{
//            sendMessage("incorrectCode");
//        }
//    }
//
//    public void changeDataByCode(String data) throws IOException {
//        HashMap<String,DiscountCode> discountCodeHashMap = ProgramManager.getProgramManagerInstance().getAllDiscountCodes();
//        if(discountCodeHashMap.containsKey(data)) {
//            DiscountCode tempDiscountCode = ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(data.split("---")[0]);
//            //
//            //code---startDate---endDate---percentage---repetitionTime
//            tempDiscountCode.setStart(ProgramManager.getProgramManagerInstance().parsingStringToDate(data.split("---")[1]));
//            tempDiscountCode.setEnd(ProgramManager.getProgramManagerInstance().parsingStringToDate(data.split("---")[2]));
//            tempDiscountCode.setPercentage(Integer.parseInt(data.split("---")[3]));
//            tempDiscountCode.setRepetitionTime(Integer.parseInt(data.split("---")[4]));
//        }else{
//            sendMessage("incorrectCode");
//        }
//
//    }
//
//
//private Server server = null;
//    @Override
//    public void start(Server server) throws IOException {
//        this.server = server;
//        sendMessage("start");
//    }
//
//    private void sendMessage(String message) throws IOException {
//        server.sendMessage("11-" + message);
//    }
//
//    public void viewProduct(String message) throws IOException {
//        Product tempProduct = ProgramManager.getProgramManagerInstance().getProductById(Integer.parseInt(message));
//        //Name---Description---Price---Category---SubCategory---Comments
//        sendMessage(tempProduct.getName() + "---" + tempProduct.getDescription() + "---" + tempProduct.getPrice() + "---" + tempProduct.getCategoryName() + "---" + tempProduct.getSubCategoryName() + "---" + tempProduct.getComments());
//    }
//
//    public void editProduct(String message) throws IOException {
//        HashMap<Integer,Product> allProducts = ProgramManager.getProgramManagerInstance().getAllProducts();
//        if(allProducts.containsKey(Integer.parseInt(message.split("---")[0]))) {
//            Product tempProduct = ProgramManager.getProgramManagerInstance().getProductById(Integer.parseInt(message.split("---")[0]));
//            //
//            //name---categoryName---subCategoryName---price
//            tempProduct.setName(message.split("---")[1]);
//            tempProduct.setCategoryName(message.split("---")[2]);
//            tempProduct.setSubCategoryName(message.split("---")[3]);
//            tempProduct.setPrice(Long.parseLong(message.split("---")[4]));
//        }else{
//            sendMessage("incorrectId");
//        }
//        sendMessage("");
//    }
//
//    public void addProduct(String message) throws IOException {
//        //name---categoryName---subCategoryName---Date---price
//        String[] dataSplit = message.split("---");
//        new Product(dataSplit[0],dataSplit[1],dataSplit[2],dataSplit[3],Long.parseLong(dataSplit[4]));
//        sendMessage("created");
//    }
//
//    public void viewBuyersOfProduct(String message) throws IOException {
//        for (Integer integer : ProgramManager.getProgramManagerInstance().getLogsInGeneralHashMap().keySet()) {
//
//        }
//
//        sendMessage("");
//
//    }
//
//    public void removeProduct(String message) throws IOException {
//        ProgramManager.getProgramManagerInstance().removeProduct(Integer.parseInt(message));
//        sendMessage("removed");
//    }
//    /*
//    private static SellerProductsMenu instance;
//    public static SellerProductsMenu getInstance(){
//        if (instance == null)
//            instance = new SellerProductsMenu();
//        return instance;
//    }
//    ////////////////////////////////
//    private SellerProductsMenuView view;
//
//    public void start(){
//        view = new SellerProductsMenuView();
//        String command = null;
//        while (true) {
//            command = view.getInputCommand();
//            if (command.equals("back")) {
//                return;
//            }
//            else {
//                throw new RuntimeException("Unknown command was passed to LoginMenu by client.view");
//            }
//        }
//    }
//    private static ProgramManager programManagerInstance = null;
//
//    public static ProgramManager getProgramManagerInstance() {
//        if (programManagerInstance == null)
//            programManagerInstance = new ProgramManager();
//        return programManagerInstance;
//    }
//
//
//    //////////////////////////////////////////////
//    public final String PROGRAM_NAME = "supermarket";
//    public boolean existManager = false;
//    private final String ADDRESS = "files\\";
//
//    private File folder;
//    private File accountsFile;
//    private File logsFile;
//    private File productsFile;
//    private File categoriesFile;
//    private File discountCodesFile;
//    private File requestsFile;
//    private File offsFile;
//
//    private HashMap<String, Account> allAccounts;
//    private HashMap<Integer, LogsInGeneral> allLogs;
//    private HashMap<Integer, Product> allProducts;
//    private HashMap<String, Category> allCategories;
//    private HashMap<String, DiscountCode> allDiscountCodes;
//    private ArrayList<Request> allRequests;
//    private HashMap<Integer, Off> allOffs;
//    public ArrayList<Account> allLoggedInUser = null;
//
//    //private Account currentlyLoggedInUsers;
//
//    private HashMap<Product, Integer> buyBasket;
//
//
//    //------------------------ Test----------
//    public void createAllAccountHashMapForTest() {
//        allAccounts = new HashMap<>();
//    }
//
//    public void createAllRequestHashMapForTest() {
//        allRequests = new ArrayList<>();
//    }
//
//    public void createAllCategoriesHashMapForTest() {
//        allCategories = new HashMap<>();
//    }
//
//    public void createAllProductHashMap() {
//        allProducts = new HashMap<>();
//    }
//
//    public void createAllOffsHashMap() {
//        allOffs = new HashMap<>();
//    }
//    //------------------------ Test----------
//
//    private ProgramManager() {
//        folder = new File(ADDRESS);
//        accountsFile = new File(ADDRESS + "accounts.json");
//        logsFile = new File(ADDRESS + "logs.json");
//        productsFile = new File(ADDRESS + "products.json");
//        categoriesFile = new File(ADDRESS + "categories.json");
//        discountCodesFile = new File(ADDRESS + "discountCodes.json");
//        requestsFile = new File(ADDRESS + "requests.json");
//        offsFile = new File(ADDRESS + "offs.json");
//
//        //currentlyLoggedInUsers = null;
//
//        buyBasket = new HashMap<>();
//    }
//
//    public void loadFromFiles() {
//        if (!folder.exists()) {
//            folder.mkdir();
//            try {
//                accountsFile.createNewFile();
//                logsFile.createNewFile();
//                productsFile.createNewFile();
//                categoriesFile.createNewFile();
//                discountCodesFile.createNewFile();
//                requestsFile.createNewFile();
//                offsFile.createNewFile();
//            } catch (Exception ignored) {
//                System.out.println("Failed to make files...");
//            }
//        } else {
//            try {
//                if (!accountsFile.exists())
//                    accountsFile.createNewFile();
//                if (!logsFile.exists())
//                    logsFile.createNewFile();
//                if (!productsFile.exists())
//                    productsFile.createNewFile();
//                if (!categoriesFile.exists())
//                    categoriesFile.createNewFile();
//                if (!discountCodesFile.exists())
//                    discountCodesFile.createNewFile();
//                if (!requestsFile.exists())
//                    requestsFile.createNewFile();
//                if (!offsFile.exists())
//                    offsFile.createNewFile();
//            } catch (Exception ignored) {
//                System.out.println("Failed to make files...");
//            }
//
//            YaGson gsonParser = new YaGson();
//            try {
//                allAccounts = gsonParser.fromJson(Files.readString(Paths.get(ADDRESS + "accounts.json")), new TypeToken<HashMap<String, Account>>() {
//                }.getType());
//                allLogs = gsonParser.fromJson(Files.readString(Paths.get(ADDRESS + "logs.json")), new TypeToken<HashMap<Integer, LogsInGeneral>>() {
//                }.getType());
//                allProducts = gsonParser.fromJson(Files.readString(Paths.get(ADDRESS + "products.json")), new TypeToken<HashMap<Integer, Product>>() {
//                }.getType());
//                allCategories = gsonParser.fromJson(Files.readString(Paths.get(ADDRESS + "categories.json")), new TypeToken<HashMap<String, Category>>() {
//                }.getType());
//                allDiscountCodes = gsonParser.fromJson(Files.readString(Paths.get(ADDRESS + "discountCodes.json")), new TypeToken<HashMap<String, DiscountCode>>() {
//                }.getType());
//                allRequests = gsonParser.fromJson(Files.readString(Paths.get(ADDRESS + "requests.json")), new TypeToken<ArrayList<Request>>() {
//                }.getType());
//                allOffs = gsonParser.fromJson(Files.readString(Paths.get(ADDRESS + "offs.json")), new TypeToken<HashMap<Integer, Off>>() {
//                }.getType());
//            } catch (Exception ignored) {
//                ignored.printStackTrace();
//                System.out.println("Couldn't read from files...");
//            }
//        }
//        if (allAccounts == null) {
//            allAccounts = new HashMap<>();
//            existManager = false;
//        } else {
//            allAccounts.forEach((s, account) -> {
//                if (account.getRole() == 3) {
//                    existManager = true;
//                }
//            });
//        }
//        if (allLogs == null)
//            allLogs = new HashMap<>();
//        if (allProducts == null)
//            allProducts = new HashMap<>();
//        if (allCategories == null)
//            allCategories = new HashMap<>();
//        if (allDiscountCodes == null)
//            allDiscountCodes = new HashMap<>();
//        if (allRequests == null)
//            allRequests = new ArrayList<>();
//        if (allOffs == null)
//            allOffs = new HashMap<>();
//    }
//
//    public void saveToFiles() {
//        YaGson gsonCreator = new YaGson();
//        try {
//            FileWriter fileWriter = new FileWriter(accountsFile, false);
//            fileWriter.write(gsonCreator.toJson(allAccounts));
//            fileWriter.close();
//            fileWriter = new FileWriter(logsFile, false);
//            fileWriter.write(gsonCreator.toJson(allLogs));
//            fileWriter.close();
//            fileWriter = new FileWriter(productsFile, false);
//            fileWriter.write(gsonCreator.toJson(allProducts));
//            fileWriter.close();
//            fileWriter = new FileWriter(categoriesFile, false);
//            fileWriter.write(gsonCreator.toJson(allCategories));
//            fileWriter.close();
//            fileWriter = new FileWriter(discountCodesFile, false);
//            fileWriter.write(gsonCreator.toJson(allDiscountCodes));
//            fileWriter.close();
//            fileWriter = new FileWriter(requestsFile, false);
//            fileWriter.write(gsonCreator.toJson(allRequests));
//            fileWriter.close();
//            fileWriter = new FileWriter(offsFile, false);
//            fileWriter.write(gsonCreator.toJson(allOffs));
//            fileWriter.close();
//        } catch (Exception ignored) {
//            System.out.println("Failed to save files...");
//        }
//    }
//
//    //////////////////////////////////////////////
//
//    public Collection<LogsInGeneral> getAllLogs() {
//        return allLogs.values();
//    }
//
//    public HashMap<Integer,LogsInGeneral> getLogsInGeneralHashMap(){
//        return allLogs;
//    }
//
//    public boolean isThereAccountWithUsername(String name) {
//        return allAccounts.get(name) != null;
//    }
//
//    public Account getAccountByUsername(String username) {
//        return allAccounts.get(username);
//    }
//
//    /*public void loginSuccessful(Account account) {
//        currentlyLoggedInUsers = account;
//        if (account.getRole() == 1) {
//            ((Buyer) account).addProductToBuyBasket(buyBasket);
//            buyBasket.clear();
//        }
//    }*/
//
//    /*public boolean isAnyoneLoggedIn() {
//        return currentlyLoggedInUsers != null;
//    }
//
//    public void logoutSuccessful() {
//        currentlyLoggedInUsers = null;
//    }
//
//     */
//
//    public void addAccountToList(String username, Account account) {
//        allAccounts.put(username, account);
//    }
//
//    public void addAccountToList(Account account) {
//        allAccounts.put(account.getUsername(), account);
//    }
//
//    public void addLogToList(LogsInGeneral log) {
//        allLogs.put(log.getLogId(), log);
//    }
//
//    public LogsInGeneral getLogByLogId(int id) {
//        return allLogs.get(id);
//    }
//
//    public void removeProduct(int productId) {
//        allProducts.remove(productId);
//        Product product = allProducts.get(productId);
//        allCategories.get(product.getCategoryName()).getSubCategoryByName(product.getSubCategoryName()).removeProduct(productId);
//    }
//
//    public void removeProduct(Product product) {
//        allProducts.remove(product.getId());
//        allCategories.get(product.getCategoryName()).getSubCategoryByName(product.getSubCategoryName()).removeProduct(product.getId());
//    }
//
//    /**
//     * this method adds the product to productsList and adds it to its subCategory as well
//     */
//    public void addProductToList(Product product) {
//        try {
//            allCategories.get(product.getCategoryName()).getSubCategoryByName(product.getSubCategoryName()).addProduct(product.getId());
//            allProducts.put(product.getId(), product);
//        } catch (Exception e) {
//            System.out.println("Fake address");
//        }
//    }
//
//    public void addOffToList(Off off) {
//        allOffs.put(off.getOffId(), off);
//    }
//
//    public Product getProductById(int id) {
//        return allProducts.get(id);
//    }
//
//    public void deleteDiscountCode(DiscountCode discountCode) {
//        allDiscountCodes.remove(discountCode);
//    }
//
//    public void deleteAccount(String username) {
//        allAccounts.remove(username);
//    }
//
//    /*public Account getCurrentlyLoggedInUsers() {
//        return currentlyLoggedInUsers;
//    }
//
//     */
//
//
//     * this function gets current user's role
//     *
//     * @return 0 if no one is logged in
//
//    /*public byte getCurrentlyLoggedInUserRole() {
//        if (currentlyLoggedInUsers != null)
//            return currentlyLoggedInUsers.getRole();
//        return 0;
//    }
//
//
//
//    public Collection<Account> getAllAccounts() {
//        return allAccounts.values();
//    }
//
//    public ArrayList<Request> getAllRequests() {
//        return allRequests;
//    }
//
//    public Collection<Category> getAllCategories() {
//        return allCategories.values();
//    }
//
//
//
//    public LocalDateTime parsingStringToDate(String input) {
//        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
//        LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
//        return dateTime;
//    }
//
//    public void addDiscountCodeToArrayList(DiscountCode discountCode) {
//        allDiscountCodes.put(discountCode.getCode(), discountCode);
//    }
//
//    public DiscountCode getDiscountCodeByCode(String code) {
//        return allDiscountCodes.get(code);
//    }
//
//    public HashMap<String, DiscountCode> getAllDiscountCodes() {
//        return allDiscountCodes;
//    }
//    public HashMap<Integer,Product> getAllProducts() {
//        return allProducts;
//    }
//
//    /**
//     * If a buyer is logged in, this method will add the product to their buyBasket, otherwise it will be added to ProgramManager buyBasket.
//     *
//     *  product the product to be added
//     */
//    /*public void addToCurrentBuyBasket(Product product, int count) {
//        if (currentlyLoggedInUsers == null) {
//            if (buyBasket.containsKey(product)) {
//                count += buyBasket.get(product);
//                buyBasket.replace(product, count);
//            } else
//                buyBasket.put(product, count);
//        } else if (currentlyLoggedInUsers.getRole() == 1)
//            ((Buyer) currentlyLoggedInUsers).addProductToBuyBasket(product, count);
//    }
//
//     */
//
//    public void addRequestToList(Request request) {
//        allRequests.add(request);
//    }
//
//    public void removeRequest(Request request) {
//        allRequests.remove(request);
//    }
//
//
//    // MKH don't print things here
//    public String showAllRequests() {
//        StringBuilder result = new StringBuilder();
//        result.append("Requests : \n");
//
//        for (int i = 0; i < allRequests.size(); i++) {
//            if (allRequests.get(i) instanceof ProductRequest) {
//                //System.out.println(i + ". " + allRequests.get(i) + "is a ProductRequest");
//                result.append("\n").append(i).append(". ").append(allRequests.get(i).toString()).append("is a ProductRequest");
//            } else if (allRequests.get(i) instanceof OffRequest) {
//                //System.out.println(i + ". " + allRequests.get(i) + "is an OffRequest");
//                result.append("\n").append(i).append(". ").append(allRequests.get(i).toString()).append("is an OffRequest");
//            }
//        }
//
//        return result.toString();
//    }
//
//    public void acceptRequests(int id) {
//        if (allRequests.get(id) instanceof ProductRequest) {
//            allRequests.get(id).accept();
//        } else if (allRequests.get(id) instanceof OffRequest) {
//            allRequests.get(id).accept();
//        }
//    }
//
//    public void declineRequests(int id) {
//        if (allRequests.get(id) instanceof ProductRequest) {
//            allRequests.get(id).decline();
//        } else if (allRequests.get(id) instanceof OffRequest) {
//            allRequests.get(id).decline();
//        }
//    }
//
//    public String detailsOfRequest(int id) {
//        if (allRequests.get(id) instanceof ProductRequest) {
//            return ((ProductRequest) allRequests.get(id)).showDetails(1);
//        } else if (allRequests.get(id) instanceof OffRequest) {
//            return ((OffRequest) allRequests.get(id)).showDetails(1);
//        }
//        return null;
//    }
//
//    public void addOffToHashMap(Off off) {
//        allOffs.put(off.getOffId(), off);
//    }
//
//    public Off getOffById(int id) {
//        return allOffs.get(id);
//    }
//
//    public void showOff(Off off) {
//        System.out.println("the Off's Id is " + off.getOffId());
//        System.out.println("the Off's StartDate is " + off.getStartDate());
//        System.out.println("the Off's EndDate is " + off.getEndDate());
//        System.out.println("the Off's amount is " + off.getOffAmount());
//    }
//
//    public String showOff(Off off, int a) {
//        String s = "";
//        s += "the Off's Id is " + off.getOffId() + "\n";
//        s += "the Off's StartDate is " + off.getStartDate() + "\n";
//        s += "the Off's EndDate is " + off.getEndDate() + "\n";
//        s += "the Off's amount is " + off.getOffAmount() + "\n";
//        return s;
//    }
//
//    public Category getCategoryByName(String name) {
//        return allCategories.get(name);
//    }
//
//    public void removeCategory(Category category) {
//        allCategories.remove(category);
//    }
//
//
//
}
