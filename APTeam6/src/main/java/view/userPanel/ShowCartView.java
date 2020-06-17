package view.userPanel;

import controller.ProgramManager;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.account.Buyer;
import view.Exit;
import view.Input;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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
        stage.getIcons().add(new Image(new FileInputStream("src/main/java/view/pictures/icon.png")));
        Scene scene = new Scene(vBox,300,600);
        vBox.setAlignment(Pos.CENTER);
        stage.setScene(scene);
        stage.show();


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
