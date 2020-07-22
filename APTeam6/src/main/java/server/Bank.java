package server;

import server.controller.Parent;

import java.io.*;
import java.net.Socket;

public class Bank implements Parent {
    private String token;
    private Socket bankSocket = null;
    private Server server = null;
    private int creditCard = -1;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;


    public Bank(Socket bankSocket) throws IOException {
        this.bankSocket = bankSocket;
        dataOutputStream = new DataOutputStream(new BufferedOutputStream(bankSocket.getOutputStream()));
        dataInputStream = new DataInputStream(new BufferedInputStream(bankSocket.getInputStream()));
    }

    public void createAccount(String data) throws IOException {
        String command = "create_account";
        command += " ";
        command += data.split("---")[0];
        command += " ";
        command += data.split("---")[1];
        command += " ";
        command += data.split("---")[2];
        command += " ";
        command += data.split("---")[3];
        command += " ";
        command += data.split("---")[4];

        dataOutputStream.writeUTF(command);
        dataOutputStream.flush();
        String response = dataInputStream.readUTF();

        if (response.startsWith("password")) {
            sendMessage("passwordMatch");
            return;
        }

        if (response.startsWith("username")) {
            sendMessage("usernameNotAvailable");
            return;
        }

        int creditCard = Integer.parseInt(response);
        this.creditCard = creditCard;
        createToken(data.split("---")[2],data.split("---")[3]);
        sendMessage("created");

    }

    public void createToken(String username, String password) throws IOException {
        String string = "get_token";
        string += " ";
        string += username;
        string += " ";
        string += password;

        dataOutputStream.writeUTF(string);
        dataOutputStream.flush();
        String response = dataInputStream.readUTF();

        if (response.equals("invalid username or password")) {
            sendMessage("invalid username or password");
            return;
        }


        token = response;

    }

    public void createReceipt(String receiptType, long money, int sourceID,
                              int destinationID, String description) throws IOException {
        String command = "create_receipt ";
        command += token;
        command += " ";
        command += receiptType;
        command += " ";
        command += String.valueOf(money);
        command += " ";
        if (receiptType.equals("deposit")) {
            command += "-1";
        } else {
            command += String.valueOf(sourceID);
        }
        command += " ";
        if (receiptType.equals("withdraw")) {
            command += "-1";
        } else {
            command += String.valueOf(destinationID);
        }
        command += " ";
        command += description;

        dataOutputStream.writeUTF(command);
        dataOutputStream.flush();

        String response = dataInputStream.readUTF();
        if (response.equals("invalid receipt type")) {
            sendMessage("invalid receipt type");
            return;
        }
        else if (response.equals("invalid money")) {
            sendMessage("invalid money");
            return ;
        }
        else if (response.equals("invalid parameters passed")) {
            sendMessage("invalid parameters passed");
            return ;
        }
        else if (response.equals("token is invalid")) {
            sendMessage("token is invalid");
            return ;
        }
        else if (response.equals("token expired")) {
            sendMessage("token expired");
            return ;
        }
        else if (response.equals("source account id is invalid")) {
            sendMessage("source account id is invalid");
            return ;
        }
        else if (response.equals("dest account id is invalid")) {
            sendMessage("dest account id is invalid");
            return ;
        }
        else if (response.equals("equal source and dest account")) {
            sendMessage("equal source and dest account");
            return ;
        }
        else if (response.equals("invalid account id")) {
            sendMessage("invalid account id");
            return ;
        }
        else if (response.equals("your input contains invalid characters")) {
            sendMessage("your input contains invalid characters");
            return ;
        }
        sendMessage(response);


    }

    public void getTransactions(String type) throws IOException {
        String command = "get_transaction ";
        command+=token;
        command+=" ";
        command+=type;

        dataOutputStream.writeUTF(command);
        dataOutputStream.flush();
        String response = dataInputStream.readUTF();

        if (response.equals("token is invalid")){
            sendMessage("token is invalid");
        }else if (response.equals("token expired")){
            sendMessage("token expired");
        }else if (response.equals("invalid receipt id")){
            sendMessage("invalid receipt id");
        }else {
            sendMessage(response);
        }
    }

    public void pay(String receiptID) throws IOException {
        String command = "pay ";
        command+=receiptID;

        dataOutputStream.writeUTF(command);
        dataOutputStream.flush();
        String response = dataInputStream.readUTF();

        if (response.equals("invalid receipt id")){
            sendMessage("invalid receipt id");
        }else if (response.equals("receipt is paid before")){
            sendMessage("receipt is paid before");
        }else if (response.equals("source account does not have enough money")){
            sendMessage("source account does not have enough money");
        }else if (response.equals("invalid account id")){
            sendMessage("invalid account id");
        }else if (response.equals("done successfully")){
            sendMessage("done successfully");
        }else{
            sendMessage("error in connection");
        }
    }

    public void getBalance() throws IOException {
        String command = "get_balance ";
        command+=token;

        dataOutputStream.writeUTF(command);
        dataOutputStream.flush();
        String response = dataInputStream.readUTF();

        if (response.equals("token is invalid")){
            sendMessage("token is invalid");
        }else if (response.equals("token expired")){
            sendMessage("token expired");
        }else{
            sendMessage(response);
        }
    }

    public void sendMessage(String message) throws IOException {
        server.sendMessage(message);
    }

    @Override
    public void start(Server server) throws IOException {
        this.server = server;
        sendMessage("start");
    }
}


