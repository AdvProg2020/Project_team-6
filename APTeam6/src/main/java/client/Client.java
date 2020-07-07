package client;

import client.view.news.GeneralController_V;
import client.view.old.Alert;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client extends Application {
    private Stage theStage;
    private ArrayList<FXMLLoader> allFXMLLoaders;
    private ArrayList<Scene> allScenes;
    private ArrayList<GeneralController_V> allControllers;

    public void run() throws IOException {
        /*Scanner scanner = new Scanner(System.in);
        while (true){
            String s = scanner.nextLine();
            sendMessage(s);
            s = getMessage();
            System.out.println(s);
        }*/

        theStage = new Stage();
        allFXMLLoaders = new ArrayList<>();
        allScenes = new ArrayList<>();
        allControllers = new ArrayList<>();
    }

    public String getMessage() throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(this.serverSocket.getInputStream()));
        String command = dataInputStream.readUTF();
        //TODO: decode
        return command;
    }

    public void sendMessage(String command) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(this.serverSocket.getOutputStream()));
        //TODO: encode
        dataOutputStream.writeUTF(command);
        dataOutputStream.flush();
    }

    ////////////////////////////////////////////////////////////////////////////////////

    private Socket serverSocket = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox vBox = new VBox(10);
        Label serverIpLabel = new Label("Server ip:");
        CheckBox isOnLocalhost = new CheckBox("server is on Localhost");
        TextField ipAddress = new TextField("localhost");
        Label serverPortLabel = new Label("Server port:");
        TextField serverPort = new TextField();
        Button connect = new Button("Connect");
        Button exit = new Button("Exit");

        isOnLocalhost.setSelected(true);
        //ipAddress.setEditable(false);
        ipAddress.setDisable(true);
        ipAddress.setPromptText("e.g. 192.168.1.14");
        serverPort.setPromptText("between 1 and 65535");

        connect.setOnAction(actionEvent -> {
            // TODO check data validation  ip & port
            try {
                System.out.println("connecting...");
                Socket serverSocket = new Socket(ipAddress.getText(), Integer.parseInt(serverPort.getText()));
                System.out.println("connected!");
                stage.close();
                this.serverSocket = serverSocket;
                run();
                new Alert().showAlert("connected!","ok",0,null);
            } catch (IOException e) {
                System.out.println("an error happened in connecting to server!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        isOnLocalhost.setOnAction(actionEvent -> {
            //ipAddress.setEditable(!isOnLocalhost.isSelected());
            ipAddress.setDisable(isOnLocalhost.isSelected());
            if(isOnLocalhost.isSelected()){
                ipAddress.setText("localhost");
            }else {
                ipAddress.setText("");
            }
        });

        exit.setOnAction(actionEvent -> {
            stage.close();
            System.exit(0);
        });

        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(serverIpLabel,isOnLocalhost,ipAddress,serverPortLabel,serverPort,connect,exit);
        Scene scene = new Scene(vBox,250,400);
        stage.setScene(scene);
        stage.setTitle("Connect");
        stage.show();
    }
}
