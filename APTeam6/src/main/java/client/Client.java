package client;

import client.view.news.GeneralController_V;
import client.view.old.Exit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    private String token = "";
    private boolean tokenWasTaken = false;

    public void run() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true){


            if(!tokenWasTaken){
                token = getMessage();
                tokenWasTaken = true;
            }


            String s = scanner.nextLine();
            sendMessage(s);
            s = getMessage();
            System.out.println(s);
        }
/*
        theStage = new Stage();
        allFXMLLoaders = new ArrayList<>();

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        allScenes = new ArrayList<>();
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        allControllers = new ArrayList<>();

        theStage.setTitle("tabaian shop");
        theStage.getIcons().add(new Image(new FileInputStream("src/main/java/client/view/pictures/icon.png")));

        allFXMLLoaders.add(new FXMLLoader(getClass().getResource("news\\CategoriesAndSubCategoriesMenu_V.fxml")));
        //TODO: Add all FXMLs here 😪
 */
    }

    ////////////////////////////////////////////////////////////////////////////////////

    public String getMessage() throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(this.serverSocket.getInputStream()));
        String command = dataInputStream.readUTF();
        //TODO: decode
        return command;
    }

    public void sendMessage(String command) throws IOException {
        command = token + command;
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(this.serverSocket.getOutputStream()));
        //TODO: encode
        dataOutputStream.writeUTF(command);
        dataOutputStream.flush();
    }

    ////////////////////////////////////////////////////////////////////////////////////

    private Socket serverSocket = null;

    MediaPlayer mediaPlayer;

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

        //TODO: find a good sound
        mediaPlayer = new MediaPlayer(new Media(new File("src\\sound\\SinisterGleam.mp3").toURI().toString()));
        mediaPlayer.play();

        connect.setOnAction(actionEvent -> {
            // TODO check data validation  ip & port
            try {
                System.out.println("connecting...");
                Socket serverSocket = new Socket(ipAddress.getText(), Integer.parseInt(serverPort.getText()));
                System.out.println("connected!");
                //stage.close();
                this.serverSocket = serverSocket;
                new Alert(Alert.AlertType.INFORMATION, "CONNECTED!!!").showAndWait();
                run();
            } catch (IOException e) {
                System.out.println("An error happened while connecting to server!");
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

        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            Alert alert = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION, "You sure about that? :(", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                //TODO: (message) disconnect
                System.exit(0);
            }
        });

        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(serverIpLabel,isOnLocalhost,ipAddress,serverPortLabel,serverPort,connect,exit);
        Scene scene = new Scene(vBox,250,400);
        stage.setScene(scene);
        stage.setTitle("Connect");
        stage.show();
    }
}
