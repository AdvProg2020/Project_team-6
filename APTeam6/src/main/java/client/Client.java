package client;

import client.view.Alert;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class Client extends Application {
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
                run(serverSocket);
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


    public void run(Socket serverSocket){
        System.out.println("runnnnnn");
    }
}
