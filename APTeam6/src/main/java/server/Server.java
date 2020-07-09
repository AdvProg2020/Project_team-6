package server;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import server.controller.MainScreen;
import server.controller.Parent;
import server.controller.ProgramManager;

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
                this.log.addLog("disconnected!",2);
                this.log.addLog(e.getMessage(),2);
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
                this.log.addLog("disconnected!",2);
                this.log.addLog(e.getMessage(),2);
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
        this.log.addLog(command,1);
        //TODO decode
        return command;
    }

    public void sendMessage(String command) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(this.clientSocket.getOutputStream()));
        this.log.addLog(command,0);
        //TODO encode
        dataOutputStream.writeUTF(command);
        dataOutputStream.flush();
    }
}
