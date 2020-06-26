package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.product.Comment;
import model.product.Product;
import model.product.Score;

import java.io.FileInputStream;
import java.util.ArrayList;

public class SingleProductScreenView extends Application {
    Label label;
    Product product;

    public SingleProductScreenView(String name) {
        System.out.println("Product: " + name);
    }

    public SingleProductScreenView() {

    }
    //TODO: guess yourself

    public void printProductDetails(String name, String subCategoryName, String categoryName, String description, String creationTime, int visitCount, Double averageScore) {
        label.setText("Name: " + name + "\n\nAt " + subCategoryName + "at " + categoryName + "\n\nDescription: " + description + "\n\nCreated at: " + creationTime + "\n\nVisited " + visitCount + " times\nAverage score: " + averageScore);
    }

    //////////////////////////////////////////////////////////////////////

    public void showSingleProductScreenView(Product product) throws Exception {
        this.product = product;
        start(new Stage());
    }


    @Override
    public void start(Stage stage) throws Exception {


        VBox vBox = new VBox();
        stage.setTitle("Single Product");
        stage.getIcons().add(new Image(new FileInputStream("src/main/java/view/pictures/icon.png")));
        vBox.setAlignment(Pos.CENTER);

        Label label1 = new Label("name:");
        Label name = new Label(this.product.getName());

        Label label2 = new Label("category:");
        Label category = new Label(this.product.getCategoryName());

        Label label3 = new Label("subCategory:");
        Label sub = new Label(this.product.getSubCategoryName());

        Label label4 = new Label("creation date");
        Label label5 = new Label(this.product.getCreationDate().toString());

        Label label6 = new Label("visit count");
        Label label7 = new Label("" + this.product.getVisitCount());

        Label label8 = new Label("description");
        Label label9 = new Label(this.product.getDescription());
        label9.setWrapText(true);

        Label label10 = new Label("Additional Info");
        Label label11 = new Label(this.product.getCategoryAdditionalInfo().toString());
        label11.setWrapText(true);

        Label label12 = new Label("comment");
        label12.setWrapText(true);
        StringBuilder s = new StringBuilder();
        for (Comment comment : this.product.getComments()) {
            s.append(comment.getUsername());
            s.append(" : ");
            s.append(comment.getTitle());
            s.append("=>");
            s.append(comment.getText());
            s.append("\n");
        }
        Label label13 = new Label(s.toString());


        Button back = new Button("Back");

        Button addToBuyBasket = new Button("Add to buyBasket");

        vBox.getChildren().addAll(label1,name,label2,category,label3,sub,label4,label5,label6,label7,label8,
                label9,label10,label11,label12,label13,back,addToBuyBasket);

        Scene scene = new Scene(vBox, 300, 600);
        stage.setScene(scene);
        stage.show();

        back.setOnAction(actionEvent -> {
            //TODO
        });

        addToBuyBasket.setOnAction(actionEvent -> {
            //TODO
        });

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
