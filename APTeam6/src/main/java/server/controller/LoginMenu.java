package server.controller;

import server.Server;
import server.model.account.Account;
import server.model.account.Buyer;
import server.model.account.Manager;
import server.model.account.Seller;
import client.view.old.LoginMenuView;

import java.io.IOException;

public class LoginMenu implements Parent{


    private Server server = null;

    @Override
    public void start(Server server) throws IOException {
        this.server = server;
        sendMessage("start");
    }

    private void sendMessage(String message) throws IOException {
        server.sendMessage("03-" + message);
    }

    /*
    private static LoginMenu loginMenuInstance = null;

    LoginMenuView view;

    public void start() {
        view = new LoginMenuView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.matches("login \\S+ \\S+")) {
                String[] splitCommand = command.split("\\s");
                login(splitCommand[1], splitCommand[2]);
            }
            else if (command.matches("create account (buyer|seller|manager) \\S+")) {
                String[] splitCommand = command.split("\\s");
                if (splitCommand[2].equals("manager"))
                    System.out.println("You can't make managers here. get away");
                else
                    register(splitCommand[3], splitCommand[2]);
            }
            else if (command.equals("logout")) {
                logout();
            }
            else if (command.equals("back")) {
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to LoginMenu by client.view");
            }
        }
    }*/



    public void login(String data) throws IOException {

        //TODO check data validation

        if (!ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(data.split("---")[0])) {
            //view.giveOutput("This username does not exist");
            sendMessage("4-username_does_not_exist");
            return;
        }
        Account tempAccount = ProgramManager.getProgramManagerInstance().getAccountByUsername(data.split("---")[0]);
        if (!tempAccount.checkPassword(data.split("---")[1])) {
            //view.giveOutput("Wrong password");
            sendMessage("4-wrong_password");
            return;
        }
        server.loginSuccessful(ProgramManager.getProgramManagerInstance().getAccountByUsername(data.split("---")[0]));
        //view.giveOutput("Welcome " + username + ".");
        sendMessage("4-login_successful");
    }

    public void registerNewBuyer(String data) throws IOException {
        if(data.split("---").length==6 && !data.split("---")[0].equals("") &&
                !data.split("---")[1].equals("") && !data.split("---")[2].equals("") &&
                !data.split("---")[3].equals("") && !data.split("---")[4].equals("") &&
                !data.split("---")[5].equals("")) {

            //TODO check exist user with this user name

            new Buyer(data.split("---")[0], data.split("---")[1], data.split("---")[2],
                    data.split("---")[3], data.split("---")[4], data.split("---")[5]);

            sendMessage("1-account_created");
        }else{
            sendMessage("error in data");
        }
    }

    public void registerNewSeller(String data) throws IOException {
        if(data.split("---").length==7 && !data.split("---")[0].equals("") &&
                !data.split("---")[1].equals("") && !data.split("---")[2].equals("") &&
                !data.split("---")[3].equals("") && !data.split("---")[4].equals("") &&
                !data.split("---")[5].equals("") && !data.split("---")[6].equals("") ) {

            //TODO check exist user with this user name

            new Seller(data.split("---")[0], data.split("---")[1], data.split("---")[2],
                    data.split("---")[3], data.split("---")[4], data.split("---")[5],
                    data.split("---")[6]);

            sendMessage("2-account_created");
        }else{
            sendMessage("error in data");
        }
    }

    public void registerNewManager(String data) throws IOException {
        if(data.split("---").length==6 && !data.split("---")[0].equals("") &&
                !data.split("---")[1].equals("") && !data.split("---")[2].equals("") &&
                !data.split("---")[3].equals("") && !data.split("---")[4].equals("") &&
                !data.split("---")[5].equals("")) {

            //TODO check exist user with this user name

            new Manager(data.split("---")[0], data.split("---")[1], data.split("---")[2],
                    data.split("---")[3], data.split("---")[4], data.split("---")[5]);


            sendMessage("3-account_created");
        }else{
            sendMessage("error in data");
        }
    }


    /*
    public void register(String username, String role) {
        if (ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(username)) {
            //view.giveOutput("This username is already occupied");
            return;
        }
        String[] userData = null;
        //userData = view.getUserUsualData();

        if (role.equals("buyer")) {
            System.out.println("here now1");
            new Buyer(username, userData[4], userData[0], userData[1], userData[3], userData[2]);
        }
        else if (role.equals("seller")) {
            //String company = view.getSellerCompany();
            //new Seller(username, userData[4], userData[0], userData[1], userData[3], userData[2], company);
        }
        //view.giveOutput("Registered successfully.");
    }
     */

    public void logout() {
        server.loginSuccessful(null);
    }

}
