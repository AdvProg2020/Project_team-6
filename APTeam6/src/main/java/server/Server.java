package server;

import server.controller.ProgramManager;

import java.io.*;
import java.net.Socket;

public class Server implements Runnable {

    private Socket clientSocket;

    Server(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        System.out.println("its first run");
        while (true) {
            String command = "";
            try {
                command = getMessage();
            } catch (IOException e) {
                System.out.println("disconnected!");
                ProgramManager.getProgramManagerInstance().saveToFiles();
                MainServer.runningServer--;
                break;
            }


            /*
            command start with:
            00: main screen
                -1: user panel
                -2: product
                -3: offs
                -4: login menu

            01: PersonalInfoMenu
                -1:

             */

            if(command.startsWith("00")){

            }


            System.out.println(command);

            try {
                sendMessage(command);
            } catch (IOException e) {
                System.out.println("disconnected!");
                ProgramManager.getProgramManagerInstance().saveToFiles();
                MainServer.runningServer--;
                break;

            }
        }
    }

    public String getMessage() throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(this.clientSocket.getInputStream()));
        String command = dataInputStream.readUTF();
        //TODO decode
        return command;
    }

    public void sendMessage(String command) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(this.clientSocket.getOutputStream()));
        //TODO encode
        dataOutputStream.writeUTF(command);
        dataOutputStream.flush();
    }
}
