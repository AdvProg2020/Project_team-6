package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class ManageAllProductsView extends Application {
    public ManageAllProductsView(){
        System.out.println("=== ManageAllProducts menu");
    }
    public String getInputCommand(){
        String command;
        while (true) {
            command = Input.getInput();
            if(command.matches("remove \\.+")){
                return command;
            }
            else if(command.equals("help")){
                showHelp();
            }
            else if(command.equals("back")){
                return command;
            }
            else {
                giveOutput("Invalid Command!");
            }
        }
    }

    private void showHelp() {
        System.out.println("List of commands:\n\tremove [productId]\n\t");
    }

    public void giveOutput(String message){
        System.out.println(message);
    }

    @Override
    public void start(Stage stage) throws Exception {

        //TODO

        VBox vBox = new VBox();
        stage.setTitle("Manage products");
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
