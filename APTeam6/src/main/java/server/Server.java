package server;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import server.controller.*;
import server.controller.buyerPanels.BuyHistory;
import server.controller.buyerPanels.ShowCart;
import server.controller.managerPanels.RegisterManager;
import server.controller.managerPanels.ShowDiscountCode;
import server.model.account.Account;
import server.model.account.Buyer;
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


    Server(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void loginSuccessful(Account currentlyLoggedInUsers) {
        if (currentlyLoggedInUsers.getRole() == 1) {
            ((Buyer) currentlyLoggedInUsers).addProductToBuyBasket(buyBasket);
            buyBasket.clear();
        }
        this.currentlyLoggedInUsers = currentlyLoggedInUsers;
    }

    public Account getCurrentlyLoggedInUsers() {
        return currentlyLoggedInUsers;
    }

    public boolean isAnyoneLoggedIn() {
        return currentlyLoggedInUsers != null;
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
                -1: start user panel(personalInfoMenu)
                -2: start product(categoriesAndSubCategoriesMenu)
                -3: start offs(offs)
                -4: start login menu(loginMenu)
                -5: get and verify new manager data

            01-0: start PersonalInfoMenu (personalMenuInfo)
                -1: change information in personalInfoMenu

            02-0: start register new manager (managerPanel/registerManager)
                -1: get and verify data

            03-0: start login menu(LoginMenu)
                -1: get and verify data for new buyer
                -2: get and verify data for new seller
                -3: get and verify data for new manager
                -4: get data and check password for login

            04-0: start showDiscountCode
                -1: show the DiscountCode

            05-0: start ShowCart



            07-0: start buyHistory log


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
            } else if (command.startsWith("01-0")) {
                PersonalInfoMenu personalInfoMenu = new PersonalInfoMenu();
                try {
                    personalInfoMenu.start(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                preParent = thisParent;
                thisParent = personalInfoMenu;
            } else if (command.startsWith("01-1")) {
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
            } else if (command.startsWith("02-1")) {
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
            } else if (command.startsWith("03-0")) {
                LoginMenu loginMenu = new LoginMenu();
                try {
                    loginMenu.start(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                preParent = thisParent;
                thisParent = loginMenu;
            }else if(command.startsWith("03-1")){
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
            }else if(command.startsWith("03-2")){
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
            }else if(command.startsWith("03-3")){
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
            }else if(command.startsWith("04-0")){
                ShowDiscountCode showDiscountCode = new ShowDiscountCode();
                try {
                    showDiscountCode.start(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                preParent = thisParent;
                thisParent = showDiscountCode;
            }else if(command.startsWith("04-1")){
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
            }else if(command.startsWith("05-0")){
                ShowCart showCart = new ShowCart(buyBasket);
                try {
                    showCart.start(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                preParent = thisParent;
                thisParent = showCart;
            }else if(command.startsWith("07-0")){
                BuyHistory buyHistory = new BuyHistory();
                try {
                    buyHistory.start(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                preParent = thisParent;
                thisParent = buyHistory;
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
}
