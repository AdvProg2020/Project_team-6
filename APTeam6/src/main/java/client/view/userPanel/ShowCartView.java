package client.view.userPanel;

import server.controller.ProgramManager;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import server.model.account.Buyer;
import client.view.Exit;
import client.view.Input;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class ShowCartView extends Application {
    /*public ShowCartView() {
        System.out.println("=== Cart screen (Buy basket -_-)");
        HashMap<Integer, Integer> productHashMap = ((Buyer)ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUser()).getBuyBasket();
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : productHashMap.entrySet()) {
            i++;
            System.out.println(i + ". " + ProgramManager.getProgramManagerInstance().getProductById(entry.getKey()).getName() + ": " + entry.getValue());
        }
    }*/

    private final HashSet<String> returningCommand = new HashSet<>(Arrays.asList("login menu", ""));
    //TODO: i should regex

    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.equals("help")) {
                showHelp();
            }
            else if (returningCommand.contains(command)){
                return command;
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }

    private void showHelp() {
        System.out.println("List of commands:");
        for (String command : returningCommand) {
            System.out.println("\t" + command);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

        //TODO

        VBox vBox = new VBox();
        stage.setTitle("Show cart");
        stage.getIcons().add(new Image(new FileInputStream("src/main/java/client/view/pictures/icon.png")));
        Scene scene = new Scene(vBox,300,600);
        vBox.setAlignment(Pos.CENTER);
        stage.setScene(scene);
        stage.show();

        HashMap<Integer, Integer> buyBasket = ((Buyer)ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUser()).getBuyBasket();
        ListView<String> productList = new ListView<>();
        vBox.getChildren().add(productList);
        for (Integer id : buyBasket.keySet()) {
            StringBuilder row = new StringBuilder(ProgramManager.getProgramManagerInstance().getProductById(id).getName());
            while (row.length() < 16)
                row.append(" ");
            row.setCharAt(13, (char) (48 + buyBasket.get(id) / 100));
            row.setCharAt(14, (char) (48 + buyBasket.get(id) % 100 / 10));
            row.setCharAt(15, (char) (48 + buyBasket.get(id) % 10));
            productList.getItems().add(new String(row));
        }
        Button buyButton = new Button("BUY!!!");
        vBox.getChildren().add(buyButton);


        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            try {
                new Exit().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}
