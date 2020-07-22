package server;

import server.controller.Parent;
import server.model.account.Account;

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

        if (response.equals("invalid username or password")){
            sendMessage("invalid username or password");
            return;
        }

        sendMessage("ok");
        token = response;

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
