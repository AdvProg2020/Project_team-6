package server;

import server.controller.*;
import server.controller.buyerPanels.BuyHistory;
import server.controller.buyerPanels.ShowCart;
import server.controller.managerPanels.*;
import server.controller.sellerPanels.*;
import server.model.account.Account;
import server.model.account.Buyer;
import server.model.product.Product;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;

public class Server implements Runnable {

    private Socket clientSocket;
    private Parent thisParent = new MainScreen();
    private Parent preParent = null;
    private Log log = null;
    private Account currentlyLoggedInUsers = null;
    private HashMap<Product, Integer> buyBasket = new HashMap<>();
    private boolean tokenSent = false;
    private String token = "";
    private Bank bank = null;

    public HashMap<Product, Integer> getBuyBasket() {
        return buyBasket;
    }

    Server(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void loginSuccessful(Account currentlyLoggedInUsers) {
        if (currentlyLoggedInUsers.getRole() == 1) {
            ((Buyer) currentlyLoggedInUsers).addProductToBuyBasket(buyBasket);
            buyBasket.clear();
        }
        this.currentlyLoggedInUsers = currentlyLoggedInUsers;
        ProgramManager.getProgramManagerInstance().allLoggedInUser.add(currentlyLoggedInUsers);
        try {
            bank = new Bank(MainServer.bankSocket);
            bank.start(this);
        } catch (IOException e) {
            System.err.println("error occurred");
        }
    }

    public Account getCurrentlyLoggedInUsers() {
        return currentlyLoggedInUsers;
    }

    public boolean isAnyoneLoggedIn() {
        return currentlyLoggedInUsers != null;
    }

    public void logoutSuccessful(){
        ProgramManager.getProgramManagerInstance().allLoggedInUser.remove(currentlyLoggedInUsers);
        currentlyLoggedInUsers = null;
    }

    public void addToCurrentBuyBasket(Product product, int count) {
        if (currentlyLoggedInUsers == null) {
            if (buyBasket.containsKey(product)) {
                count += buyBasket.get(product);
                buyBasket.replace(product, count);
            } else {
                buyBasket.put(product, count);
            }
        } else if (currentlyLoggedInUsers.getRole() == 1) {
            ((Buyer) currentlyLoggedInUsers).addProductToBuyBasket(product, count);
        }
    }

    @Override
    public void run() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("\'log--\'yyyy-MM-dd--HH+mm+ss");
        LocalDateTime now = LocalDateTime.now();
        Log log = new Log(dtf.format(now));
        this.log = log;

        long[] time = new long[20];
        Arrays.fill(time, 0);
        time[19] = System.currentTimeMillis();

        while (true) {
            String command = "";
            if(!tokenSent){
                try {
                    token = createToken();
                    sendMessage(token);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            try {
                command = getMessage();
            } catch (IOException e) {
                this.log.addLog("disconnected!", 2);
                this.log.addLog(e.getMessage(), 2);
                ProgramManager.getProgramManagerInstance().saveToFiles();
                MainServer.runningServer--;
                break;
            }

            try {
                replayAttacks(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(!command.startsWith(token)){
                try {
                    sendMessage("invalid token");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                continue;
            }else{
                command = command.substring(10);
            }




            /*

            **command start with:**

            00-0: start main screen(mainScreen)


            01-0: start PersonalInfoMenu (personalMenuInfo)
                -1: change information in personalInfoMenu

            02-0: start register manager or supporter
                -1: (managerPanel/registerManager): get and verify data for register manager
                -2: (managerPanel/registerManager): get and verify data for register supporter

            03-0: start login menu(LoginMenu)
                (return start when exist manager and return createManager when doesnt exist manager)
                -1: get and verify data for new buyer
                -2: get and verify data for new seller
                -3: get and verify data for new manager
                -4: get data and check password for login
                -5: logout
                -6: get and verify data for new supporter

            04-0: start showDiscountCode
                -1: view discount code(return data by code)
                -2: change data by code
                -3: remove discount code

            05-0: start ShowCart

            06-0: start manageRequest
                -1: accept request
                -2: decline request
                -3: detail request

            07-0: start buyHistory log

            08-0: start create discount code
                -1: create discount code(get data and create)

            09-0: start manage users
                -1: view user(with username)
                -2: delete user(with username)
                -3: change user(with username) start(only send information)
                -4: change user(with username) get and verify data and change

            10-0: start View Sales History(for seller)

            11-0: start manage product(seller)
                -1: view product(view product by id)
                -2: view buyers of product(by id)
                -3: edit product(by id)
                -4: add product(get and verify data)
                -5: remove product(by id)

            12-0: start Categories and SubCategories Menu(seller)
                -1: open Category
                -2: add Category
                -3: edit Category
                -4: remove Category
                -5: open SubCategory
                -6: add SubCategory
                -7: edit SubCategory
                -8: remove SubCategory
                -9: open product
                -a: add to buy basket
                -b: send current category and subCategory information

            13-0: start view offs
                -1: view off by id
                -2: edit off by id
                -3: add off(get and verify data)
                -4: send all product name's for current seller

            14-0: Start View Cart
                -1: viewProduct
                -2: increase
                -3: decrease
                -4: purchase
                -5: showTotalPrice

            15-0: start verifyDiscountCode
                -1: verify

            16-0: start receiveBuyerInfo

            17-0: start bank
                -1: create account
                -2: create receipt
                -3: get transaction
                -4: pay
                -5: get balance

            18-0: start manage all product (manager)
                -1: remove product

            19-0: start EWallet
                -1:takeCredit
                -2addToSellLog
                -3:addToBuyLog


            */

            if (command.startsWith("00-0")) {
                //if (ProgramManager.getProgramManagerInstance().existManager) {
                    MainScreen mainScreen = new MainScreen();
                    try {
                        mainScreen.start(this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    preParent = thisParent;
                    thisParent = mainScreen;
                /*} else {
                    RegisterManager registerManager = new RegisterManager();
                    try {
                        registerManager.start(this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    preParent = thisParent;
                    thisParent = registerManager;
                }

                 */
            }
            else if (command.startsWith("01-0")) {
                PersonalInfoMenu personalInfoMenu = new PersonalInfoMenu();
                try {
                    personalInfoMenu.start(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                preParent = thisParent;
                thisParent = personalInfoMenu;
            }
            else if (command.startsWith("01-1")) {
                if(thisParent instanceof PersonalInfoMenu) {
                    PersonalInfoMenu personalInfoMenu = (PersonalInfoMenu) thisParent;
                    personalInfoMenu.changeInformation(command.substring(4));
                }else{
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("02-0")) {
                RegisterManager registerManager = new RegisterManager();
                try {
                    registerManager.start(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                preParent = thisParent;
                thisParent = registerManager;
            }
            else if (command.startsWith("02-1")) {
                if (thisParent instanceof RegisterManager) {
                    RegisterManager registerManager = (RegisterManager) thisParent;
                    try {
                        registerManager.registerNewManager(command.substring(4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("02-2")) {
                if (thisParent instanceof RegisterManager) {
                    RegisterManager registerManager = (RegisterManager) thisParent;
                    try {
                        registerManager.registerNewSupporter(command.substring(4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("03-0")) {
                LoginMenu loginMenu = new LoginMenu();
                try {
                    loginMenu.start(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                preParent = thisParent;
                thisParent = loginMenu;
            }
            else if (command.startsWith("03-1")) {
                if(thisParent instanceof LoginMenu){
                    LoginMenu loginMenu = (LoginMenu) thisParent;
                    try {
                        loginMenu.registerNewBuyer(command.substring(4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("03-2")) {
                if(thisParent instanceof LoginMenu){
                    LoginMenu loginMenu = (LoginMenu) thisParent;
                    try {
                        loginMenu.registerNewSeller(command.substring(4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("03-3")) {
                if(thisParent instanceof LoginMenu){
                    LoginMenu loginMenu = (LoginMenu) thisParent;
                    try {
                        loginMenu.registerNewManager(command.substring(4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("03-4")) {
                if(thisParent instanceof LoginMenu){
                    LoginMenu loginMenu = (LoginMenu) thisParent;
                    try {
                        loginMenu.login(command.substring(4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("03-5")) {
                if(thisParent instanceof LoginMenu){
                    if(currentlyLoggedInUsers!=null){
                        logoutSuccessful();
                        try {
                            sendMessage("logout");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else {
                        try {
                            sendMessage("firstLogin");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("03-6")) {
                if(thisParent instanceof LoginMenu){
                    LoginMenu loginMenu = (LoginMenu) thisParent;
                    try {
                        loginMenu.registerNewSupporter(command.substring(4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("04-0")) {
                ShowDiscountCode showDiscountCode = new ShowDiscountCode();
                try {
                    showDiscountCode.start(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                preParent = thisParent;
                thisParent = showDiscountCode;
            }
            else if (command.startsWith("04-1")) {
                if(thisParent instanceof ShowDiscountCode){
                    ShowDiscountCode showDiscountCode = (ShowDiscountCode) thisParent;
                    try {
                        showDiscountCode.showDiscountCode(command.substring(4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("04-2")) {
                if(thisParent instanceof ShowDiscountCode){
                    ShowDiscountCode showDiscountCode = (ShowDiscountCode) thisParent;
                    try {
                        showDiscountCode.changeDataByCode(command.substring(4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("04-3")) {
                if(thisParent instanceof ShowDiscountCode){
                    ShowDiscountCode showDiscountCode = (ShowDiscountCode) thisParent;
                    try {
                        showDiscountCode.removeDiscountCode(command.substring(4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("05-0")) {
                ShowCart showCart = new ShowCart(buyBasket);
                try {
                    showCart.start(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                preParent = thisParent;
                thisParent = showCart;
            }
            else if (command.startsWith("06-0")) {
                ManageRequests manageRequests = new ManageRequests();
                try {
                    manageRequests.start(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                preParent = thisParent;
                thisParent = manageRequests;
            }
            else if (command.startsWith("06-1")) {
                if(thisParent instanceof ManageRequests){
                    ManageRequests manageRequests = (ManageRequests) thisParent;
                    try {
                        manageRequests.acceptRequest(command.substring(4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("06-2")) {
                if(thisParent instanceof ManageRequests){
                    ManageRequests manageRequests = (ManageRequests) thisParent;
                    try {
                        manageRequests.declineRequest(command.substring(4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("06-3")) {
                if(thisParent instanceof ManageRequests){
                    ManageRequests manageRequests = (ManageRequests) thisParent;
                    try {
                        manageRequests.detailRequest(command.substring(4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("07-0")) {
                BuyHistory buyHistory = new BuyHistory();
                try {
                    buyHistory.start(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                preParent = thisParent;
                thisParent = buyHistory;
            }
            else if (command.startsWith("08-0")) {
                CreateDiscountCode createDiscountCode = new CreateDiscountCode();
                try {
                    createDiscountCode.start(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                preParent = thisParent;
                thisParent = createDiscountCode;
            }
            else if (command.startsWith("08-1")) {
                if(thisParent instanceof CreateDiscountCode){
                    CreateDiscountCode createDiscountCode = (CreateDiscountCode) thisParent;
                    try {
                        createDiscountCode.createDiscountCodeByData(command.substring(4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("09-0")) {
                ManageUsers manageUsers = new ManageUsers();
                try {
                    manageUsers.start(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                preParent = thisParent;
                thisParent = manageUsers;
            }
            else if (command.startsWith("09-1")) {
                if(thisParent instanceof ManageUsers){
                    ManageUsers manageUsers = (ManageUsers) thisParent;
                    try {
                        manageUsers.viewUser(command.substring(4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("09-2")) {
                if(thisParent instanceof ManageUsers){
                    ManageUsers manageUsers = (ManageUsers) thisParent;
                    try {
                        manageUsers.deleteUser(command.substring(4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("09-3")) {
                if(thisParent instanceof ManageUsers){
                    ManageUsers manageUsers = (ManageUsers) thisParent;
                    try {
                        manageUsers.changeUserStart(command.substring(4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("09-4")) {
                if(thisParent instanceof ManageUsers){
                    ManageUsers manageUsers = (ManageUsers) thisParent;
                    try {
                        manageUsers.changeUserChange(command.substring(4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("10-0")) {
                SalesHistory salesHistory = new SalesHistory();
                try {
                    salesHistory.start(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                preParent = thisParent;
                thisParent = salesHistory;
            }
            else if (command.startsWith("11-0")) {
                SellerProductsMenu sellerProductsMenu = new SellerProductsMenu();
                try {
                    sellerProductsMenu.start(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                preParent = thisParent;
                thisParent = sellerProductsMenu;
            }
            else if (command.startsWith("11-1")) {
                if(thisParent instanceof SellerProductsMenu){
                    SellerProductsMenu sellerProductsMenu = (SellerProductsMenu) thisParent;
                    try {
                        sellerProductsMenu.viewProduct(command.substring(4));
                    } catch (IOException e) {
                        System.err.println("error occurred");
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        System.err.println("error occurred");
                    }
                }
            }
            else if (command.startsWith("11-2")) {
                if(thisParent instanceof SellerProductsMenu){
                    SellerProductsMenu sellerProductsMenu = (SellerProductsMenu) thisParent;
                    try {
                        sellerProductsMenu.viewBuyersOfProduct(command.substring(4));
                    } catch (IOException e) {
                        System.err.println("error occurred");
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        System.err.println("error occurred");
                    }
                }
            }
            else if (command.startsWith("11-3")) {
                if(thisParent instanceof SellerProductsMenu){
                    SellerProductsMenu sellerProductsMenu = (SellerProductsMenu) thisParent;
                    try {
                        sellerProductsMenu.editProduct(command.substring(4));
                    } catch (IOException e) {
                        System.err.println("error occurred");
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        System.err.println("error occurred");
                    }
                }
            }
            else if (command.startsWith("11-4")) {
                if(thisParent instanceof SellerProductsMenu){
                    SellerProductsMenu sellerProductsMenu = (SellerProductsMenu) thisParent;
                    try {
                        sellerProductsMenu.addProduct(command.substring(4));
                    } catch (IOException e) {
                        System.err.println("error occurred");
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        System.err.println("error occurred");
                    }
                }
            }
            else if (command.startsWith("11-5")) {
                if(thisParent instanceof SellerProductsMenu){
                    SellerProductsMenu sellerProductsMenu = (SellerProductsMenu) thisParent;
                    try {
                        sellerProductsMenu.removeProduct(command.substring(4));
                    } catch (IOException e) {
                        System.err.println("error occurred");
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        System.err.println("error occurred");
                    }
                }
            }
            else if (command.startsWith("12-0")) {
                CategoriesAndSubCategoriesMenu categoriesAndSubCategoriesMenu= new CategoriesAndSubCategoriesMenu();
                try {
                    categoriesAndSubCategoriesMenu.start(this);
                } catch (IOException e) {
                    System.err.println("error occurred");
                }
                preParent = thisParent;
                thisParent = categoriesAndSubCategoriesMenu;
            }
            else if (command.startsWith("12-1")) {
                if(thisParent instanceof CategoriesAndSubCategoriesMenu){
                    CategoriesAndSubCategoriesMenu categoriesAndSubCategoriesMenu = (CategoriesAndSubCategoriesMenu) thisParent;
                    categoriesAndSubCategoriesMenu.openCategory(Integer.parseInt(command.substring(4)));
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        System.err.println("error occurred");
                    }
                }

            }
            else if (command.startsWith("12-2")) {
                if(thisParent instanceof CategoriesAndSubCategoriesMenu){
                    CategoriesAndSubCategoriesMenu categoriesAndSubCategoriesMenu = (CategoriesAndSubCategoriesMenu) thisParent;
                    try {
                        categoriesAndSubCategoriesMenu.addCategory(command.substring(4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            else if (command.startsWith("12-3")) {
                if(thisParent instanceof CategoriesAndSubCategoriesMenu){
                    CategoriesAndSubCategoriesMenu categoriesAndSubCategoriesMenu = (CategoriesAndSubCategoriesMenu) thisParent;
                    categoriesAndSubCategoriesMenu.editCategory(Integer.parseInt(command.split("---")[0].substring(4)),command.split("---")[1]);
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            else if (command.startsWith("12-4")) {
                if(thisParent instanceof CategoriesAndSubCategoriesMenu){
                    CategoriesAndSubCategoriesMenu categoriesAndSubCategoriesMenu = (CategoriesAndSubCategoriesMenu) thisParent;
                    categoriesAndSubCategoriesMenu.removeCategory(Integer.parseInt(command.substring(4)));
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            else if (command.startsWith("12-5")) {
                if(thisParent instanceof CategoriesAndSubCategoriesMenu){
                    CategoriesAndSubCategoriesMenu categoriesAndSubCategoriesMenu = (CategoriesAndSubCategoriesMenu) thisParent;
                    categoriesAndSubCategoriesMenu.openSubCategory(Integer.parseInt(command.substring(4)));
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            else if (command.startsWith("12-6")) {
                if(thisParent instanceof CategoriesAndSubCategoriesMenu){
                    CategoriesAndSubCategoriesMenu categoriesAndSubCategoriesMenu = (CategoriesAndSubCategoriesMenu) thisParent;
                    categoriesAndSubCategoriesMenu.addSubCategory(command.substring(4));
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("12-7")) {
                if(thisParent instanceof CategoriesAndSubCategoriesMenu){
                    CategoriesAndSubCategoriesMenu categoriesAndSubCategoriesMenu = (CategoriesAndSubCategoriesMenu) thisParent;
                    categoriesAndSubCategoriesMenu.editSubCategory(Integer.parseInt(command.split("---")[0].substring(4)),command.split("---")[1]);
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("12-8")) {
                if(thisParent instanceof CategoriesAndSubCategoriesMenu){
                    CategoriesAndSubCategoriesMenu categoriesAndSubCategoriesMenu = (CategoriesAndSubCategoriesMenu) thisParent;
                    categoriesAndSubCategoriesMenu.removeSubCategory(Integer.parseInt(command.substring(4)));
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("12-9")) {
                if(thisParent instanceof CategoriesAndSubCategoriesMenu){
                    CategoriesAndSubCategoriesMenu categoriesAndSubCategoriesMenu = (CategoriesAndSubCategoriesMenu) thisParent;
                    categoriesAndSubCategoriesMenu.openProduct(Integer.parseInt(command.substring(4)));
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("12-a")) {
                if(thisParent instanceof CategoriesAndSubCategoriesMenu){
                    CategoriesAndSubCategoriesMenu categoriesAndSubCategoriesMenu = (CategoriesAndSubCategoriesMenu) thisParent;
                    categoriesAndSubCategoriesMenu.addToBuyBasket(Integer.parseInt(command.split("---")[0].substring(4)),Integer.parseInt(command.split("---")[1]));
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("12-b")) {
                if(thisParent instanceof CategoriesAndSubCategoriesMenu){
                    CategoriesAndSubCategoriesMenu categoriesAndSubCategoriesMenu = (CategoriesAndSubCategoriesMenu) thisParent;
                    try {
                        categoriesAndSubCategoriesMenu.getAdditionalInformationCategorySubCategory();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("13-0")) {
                OffManagementSeller offManagementSeller = new OffManagementSeller();
                try {
                    offManagementSeller.start(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                preParent = thisParent;
                thisParent = offManagementSeller;
            }
            else if (command.startsWith("13-1")) {
                if(thisParent instanceof OffManagementSeller){
                    OffManagementSeller offManagementSeller = (OffManagementSeller) thisParent;
                    try {
                        offManagementSeller.viewOff(command.substring(4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("13-2")) {
                if(thisParent instanceof OffManagementSeller){
                    OffManagementSeller offManagementSeller = (OffManagementSeller) thisParent;
                    try {
                        offManagementSeller.editOff(command.substring(4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("13-3")) {
                if(thisParent instanceof OffManagementSeller){
                    OffManagementSeller offManagementSeller = (OffManagementSeller) thisParent;
                    try {
                        offManagementSeller.addOff(command.substring(4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("13-4")) {
                if(thisParent instanceof OffManagementSeller){
                    OffManagementSeller offManagementSeller = (OffManagementSeller) thisParent;
                    offManagementSeller.showProductIds();
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (command.startsWith("14-0")) {
                Cart cart = new Cart();
                try {
                    cart.start(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                preParent = thisParent;
                thisParent = cart;

            }
            else if (command.startsWith("14-1")) {
                if(thisParent instanceof Cart){
                    Cart cart = (Cart) thisParent;
                    cart.viewProduct(Integer.parseInt(command.substring(4)));
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            else if (command.startsWith("14-2")) {
                if(thisParent instanceof Cart){
                    Cart cart = (Cart) thisParent;
                    cart.increase(Integer.parseInt(command.substring(4)));
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            else if (command.startsWith("14-3")) {
                if(thisParent instanceof Cart){
                    Cart cart = (Cart) thisParent;
                    cart.decrease(Integer.parseInt(command.substring(4)));
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            else if (command.startsWith("14-4")) {
                if(thisParent instanceof Cart){
                    Cart cart = (Cart) thisParent;
                    cart.purchase();
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            else if (command.startsWith("14-5")) {
                if(thisParent instanceof Cart){
                    Cart cart = (Cart) thisParent;
                    cart.showTotalPrice();
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            else if (command.startsWith("15-0")) {
                VerifyDiscountCode verifyDiscountCode = new VerifyDiscountCode();
                try {
                    verifyDiscountCode.start(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                preParent = thisParent;
                thisParent = verifyDiscountCode;
            }
            else if (command.startsWith("15-1")) {
                if(thisParent instanceof VerifyDiscountCode){
                    VerifyDiscountCode verifyDiscountCode = (VerifyDiscountCode) thisParent;
                    verifyDiscountCode.verify(command.substring(4));
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        System.err.println("error occurred");
                    }
                }
            }
            else if (command.startsWith("16-0")) {
                ReceiveBuyerInfo receiveBuyerInfo = new ReceiveBuyerInfo();
                try {
                    receiveBuyerInfo.start(this);
                } catch (IOException e) {
                    System.err.println("error occurred");
                }
                preParent = thisParent;
                thisParent = receiveBuyerInfo;
            }
            else if (command.startsWith("17-0")) {
                Bank bank = null;
                try {
                    bank = new Bank(MainServer.bankSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    bank.start(this);
                } catch (IOException e) {
                    System.err.println("error occurred");
                }
                preParent = thisParent;
                thisParent = bank;
            }
            else if (command.startsWith("17-1")) {
                if(thisParent instanceof Bank){
                    Bank bank = (Bank) thisParent;
                    try {
                        bank.createAccount(command.substring(4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        System.err.println("error occurred");
                    }
                }
            }
            else if (command.startsWith("17-2")) {
                if(thisParent instanceof Bank){
                    Bank bank = (Bank) thisParent;
                    try {
                        bank.createReceipt(command.substring(4).split("---")[0],
                                Long.parseLong(command.substring(4).split("---")[1]),
                                Integer.parseInt(command.substring(4).split("---")[2]),
                                Integer.parseInt(command.substring(4).split("---")[3]),
                                command.substring(4).split("---")[4]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        System.err.println("error occurred");
                    }
                }
            }
            else if (command.startsWith("17-3")) {
                if(thisParent instanceof Bank){
                    Bank bank = (Bank) thisParent;
                    try {
                        bank.getTransactions(command.substring(4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        System.err.println("error occurred");
                    }
                }
            }
            else if (command.startsWith("17-4")) {
                if(thisParent instanceof Bank){
                    Bank bank = (Bank) thisParent;
                    try {
                        bank.pay(command.substring(4));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        System.err.println("error occurred");
                    }
                }
            }
            else if (command.startsWith("17-5")) {
                if(thisParent instanceof Bank){
                    Bank bank = (Bank) thisParent;
                    try {
                        bank.getBalance();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        System.err.println("error occurred");
                    }
                }
            }
            /*else if(command.startsWith("18-0")){
                if(thisParent instanceof Bank){
                    Bank bank = (Bank) thisParent;
                    try {
                        bank.getBalance();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        System.err.println("error occurred");
                    }
                }
            }
             */
            else if (command.startsWith("18-0")) {
                ManageAllProducts manageAllProducts = new ManageAllProducts();
                try {
                    manageAllProducts.start(this);
                } catch (IOException e) {
                    System.err.println("error occurred");
                }
                preParent = thisParent;
                thisParent = manageAllProducts;
            }
            else if (command.startsWith("18-1")) {
                if(thisParent instanceof ManageAllProducts){
                    ManageAllProducts manageAllProducts = (ManageAllProducts) thisParent;
                    manageAllProducts.remove(Integer.parseInt(command));
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        System.err.println("error occurred");
                    }
                }
            }
            else if (command.startsWith("19-0")) {
                EWallet eWallet = new EWallet();
                try {
                    eWallet.start(this);
                } catch (IOException e) {
                    System.err.println("error occurred");
                }
                preParent = thisParent;
                thisParent = eWallet;
            }
            else if (command.startsWith("19-1")) {
                if(thisParent instanceof EWallet){
                    EWallet eWallet = (EWallet) thisParent;
                    eWallet.takeCredit(command.substring(4));
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        System.err.println("error occurred");
                    }
                }
            }
            else if (command.startsWith("19-2")) {
                    if(thisParent instanceof EWallet){
                        EWallet eWallet = (EWallet) thisParent;
                        eWallet.addToSellLog(command.substring(4));
                    } else {
                        try {
                            sendMessage("NotAllowed");
                        } catch (IOException e) {
                            System.err.println("error occurred");
                        }
                    }
            }

            else if (command.startsWith("19-3")) {
                if(thisParent instanceof EWallet){
                    EWallet eWallet = (EWallet) thisParent;
                    eWallet.addToBuyLog(command.substring(4));
                } else {
                    try {
                        sendMessage("NotAllowed");
                    } catch (IOException e) {
                        System.err.println("error occurred");
                    }
                }
            }












            try {
                sendMessage(command);
            } catch (IOException e) {
                this.log.addLog("disconnected!", 2);
                this.log.addLog(e.getMessage(), 2);
                ProgramManager.getProgramManagerInstance().saveToFiles();
                MainServer.runningServer--;
                break;

            }

        }
    }


    private static void replayAttacks(long[] time) throws InterruptedException {
        for (int i = 0; i < 19; i++) {
            time[i] = time[i+1];
        }

        time[19] = System.currentTimeMillis();

        if(time[0]!=0){
            if(time[19]-time[0]<10000){
                System.out.println("server suspended for 10 second because too many request");
                Thread.sleep(10000);
            }
        }

    }

    /*
    public Scene createLogsPage(){
        Stage stage = new Stage();
        VBox vBox = new VBox();
        Label logLabel = new Label("Connect :");
        logLabel.setWrapText(true);
        Scene scene = new Scene(vBox,200,650);
        stage.setScene(scene);
        stage.show();
        System.out.println("--..--");
        return scene;
    }*/

    public String getMessage() throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(this.clientSocket.getInputStream()));
        String command = dataInputStream.readUTF();

        if(command.startsWith(token)){
            command = command.substring(10);
        }else{
            sendMessage("tokenExpired");
            return null;
        }

        String secretKey;
        secretKey = AES.getSecretKeyByToken(token);
        command = AES.decrypt(command,secretKey);

        this.log.addLog(command, 1);
        return command;
    }

    public void sendMessage(String command) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(this.clientSocket.getOutputStream()));
        this.log.addLog(command, 0);

        if(tokenSent){
            String secretKey;
            secretKey = AES.getSecretKeyByToken(token);
            command = AES.encrypt(command,secretKey);
        }else{
            tokenSent = true;
        }

        dataOutputStream.writeUTF(command);
        dataOutputStream.flush();
    }

    private String createToken(){
        String string = "asdfghjklqwertyuiopzxcvbnmASDFGHJKLZXCVBNMQWERTYUIOP9875643210";
        String token = "";

        for (int i = 0; i < 15; i++) {
            double a = Math.random();
            a = a * 62;
            int b = (int) Math.floor(a);
            token += string.substring(b,b+1);
        }

        //System.out.println(token);
        return token;

    }
}
