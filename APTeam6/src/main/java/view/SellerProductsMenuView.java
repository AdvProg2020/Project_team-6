package view;

import controller.ProgramManager;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.product.Product;

import java.io.FileInputStream;

public class SellerProductsMenuView extends Application {
    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            // please complete graphical menu


        }
    }

    @Override
    public void start(Stage stage) throws Exception {



        VBox vBox = new VBox(10);
        stage.setTitle("Products");
        stage.getIcons().add(new Image(new FileInputStream("src/main/java/view/pictures/icon.png")));
        vBox.setAlignment(Pos.CENTER);

        Label l1 = new Label("please fill this field");
        Label l2 = new Label("please fill this field");
        Label l3 = new Label("please fill this field");
        l1.setTextFill(Color.RED);
        l2.setTextFill(Color.RED);
        l3.setTextFill(Color.RED);
        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(false);

        Label label1 = new Label("name:");
        TextField name = new TextField();

        Label label2 = new Label("category:");
        TextField category = new TextField();

        Label label3 = new Label("subCategory:");
        TextField sub = new TextField();


        Button create = new Button("Create");
        Button back = new Button("Back");


        vBox.getChildren().addAll(label1,l1,name,label2,l2,category,label3,l3,sub,create,back);
        Scene scene = new Scene(vBox,300,600);
        stage.setScene(scene);
        stage.show();

        create.setOnAction(actionEvent -> {

            l1.setVisible(name.getText().equals(""));
            l2.setVisible(category.getText().equals(""));
            l3.setVisible(sub.getText().equals(""));

            if(!(name.getText().equals("")||category.getText().equals("")||sub.getText().equals(""))){
                new Product(name.getText(),category.getText(),sub.getText());
                try {
                    new Alert().showAlert("product created","ok",0,null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                stage.close();
                try {
                    new SellerProductsMenuView().start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        back.setOnAction(actionEvent -> {
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