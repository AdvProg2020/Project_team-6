package server;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import server.controller.*;
import server.controller.buyerPanels.BuyHistory;
import server.controller.buyerPanels.ShowCart;
import server.controller.managerPanels.*;
import server.model.account.Account;
import server.model.account.Buyer;
import server.model.product.DiscountCode;
import server.model.product.Product;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Server implements Runnable {

    private Socket clientSocket;
    private Parent thisParent = new MainScreen();
    private Parent preParent = null;
    private Log log = null;
    private Account currentlyLoggedInUsers = null;
    private HashMap<Product, Integer> buyBasket = new HashMap<>();
    private boolean tokenSent = false;


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

        while (true) {
            String command = "";
            if(!tokenSent){
                try {
                    sendMessage(createToken());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                tokenSent = true;
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



            /*
            command start with:
            00-0: start main screen or register manager(managerPanel/registerManager or mainScreen)
                      return 02-start for register manager or 00-start for main screen

            01-0: start PersonalInfoMenu (personalMenuInfo)
                -1: change information in personalInfoMenu

            02-1: (managerPanel/registerManager): get and verify data for register

            03-0: start login menu(LoginMenu)
                -1: get and verify data for new buyer
                -2: get and verify data for new seller
                -3: get and verify data for new manager
                -4: get data and check password for login
                -5: logout

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




            */

            if (command.startsWith("00-0")) {
                if (ProgramManager.getProgramManagerInstance().existManager) {
                    MainScreen mainScreen = new MainScreen();
                    try {
                        mainScreen.start(this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    preParent = thisParent;
                    thisParent = mainScreen;
                } else {
                    RegisterManager registerManager = new RegisterManager();
                    try {
                        registerManager.start(this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    preParent = thisParent;
                    thisParent = registerManager;
                }
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









            System.out.println(command);
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
        //TODO decode
        this.log.addLog(command, 1);
        return command;
    }

    public void sendMessage(String command) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(this.clientSocket.getOutputStream()));
        this.log.addLog(command, 0);
        //TODO encode
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
