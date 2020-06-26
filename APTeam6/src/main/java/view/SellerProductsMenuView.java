package view;

import controller.ProgramManager;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.product.Comment;
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
        Label l4 = new Label("please fill this field");
        Label l5 = new Label("please fill this field");
        Label l6 = new Label("please fill this field");
        Label l7 = new Label("please fill this field");
        l1.setTextFill(Color.RED);
        l2.setTextFill(Color.RED);
        l3.setTextFill(Color.RED);
        l4.setTextFill(Color.RED);
        l5.setTextFill(Color.RED);
        l6.setTextFill(Color.RED);
        l7.setTextFill(Color.RED);
        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(false);
        l4.setVisible(false);
        l5.setVisible(false);
        l6.setVisible(false);
        l7.setVisible(false);

        Label label1 = new Label("name:");
        TextField name = new TextField();

        Label label2 = new Label("category:");
        TextField category = new TextField();

        Label label3 = new Label("subCategory:");
        TextField sub = new TextField();


        Label label4 = new Label("creation date");
        TextField label5 = new TextField();

        Label label6 = new Label("visit count");
        TextField label7 = new TextField();

        Label label8 = new Label("description");
        TextArea label9 = new TextArea();

        Label label10 = new Label("Additional Info - each attribute in one line");
        TextArea label11 = new TextArea();




        Button create = new Button("Create");
        Button back = new Button("Back");


        vBox.getChildren().addAll(label1,l1,name,label2,l2,category,label3,l3,sub,
                label4,l4,label5,label6,l5,label7,label8,l6,label9,label10,
                l7,label11,create,back);
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