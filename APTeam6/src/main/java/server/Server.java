package server;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import server.controller.*;
import server.controller.managerPanels.RegisterManager;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server implements Runnable {

    private Socket clientSocket;
    private Parent thisParent = new MainScreen();
    private Parent preParent = null;
    private Log log = null;


    Server(Socket clientSocket) {
        this.clientSocket = clientSocket;
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

            01: start PersonalInfoMenu (personalMenuInfo)
                -1:

            02-0: start register new manager (managerPanel/registerManager)
                -1: get and verify data

            03-0: start login menu(LoginMenu)
                -1: get and verify data for buyer
                -2: get and verify data for seller

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
