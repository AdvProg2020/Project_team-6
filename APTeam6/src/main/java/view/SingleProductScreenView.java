package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.product.Score;

import java.io.FileInputStream;
import java.util.ArrayList;

public class SingleProductScreenView extends Application {
    Label label;

    public SingleProductScreenView(String name){
        System.out.println("Product: " + name);
    }
    //TODO: guess yourself

    public void printProductDetails(String name, String subCategoryName, String categoryName, String description, String creationTime, int visitCount, Double averageScore){
        label.setText("Name: " + name + "\n\nAt " + subCategoryName + "at " + categoryName + "\n\nDescription: " + description + "\n\nCreated at: " + creationTime + "\n\nVisited " + visitCount + " times\nAverage score: " + averageScore);
    }

    //////////////////////////////////////////////////////////////////////
    @Override
    public void start(Stage stage) throws Exception {

        //TODO

        VBox vBox = new VBox();
        stage.setTitle("Single Product");
        stage.getIcons().add(new Image(new FileInputStream("src/main/java/view/pictures/icon.png")));
        Scene scene = new Scene(vBox,300,600);
        vBox.setAlignment(Pos.CENTER);
        label = new Label();
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
