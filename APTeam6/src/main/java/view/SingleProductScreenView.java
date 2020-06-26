package view;

import controller.PersonalInfoMenu;
import controller.ProgramManager;
import controller.SingleProductScreen;
import controller.managerPanels.ShowDiscountCode;
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
import model.product.Score;

import java.io.FileInputStream;
import java.util.ArrayList;

public class SingleProductScreenView extends Application {
    Label label = new Label();
    public SingleProductScreenView(String name){
        System.out.println("Product: " + name);
    }
    public SingleProductScreenView(){}
    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.matches("add to buy basket .+")) {
                return command;
            } else if (command.matches("Compare \\w+ \\w+")) {
                return command;
            } else if (command.equals("back")) {
                return command;
            } else {
                System.err.println("Invalid Command");
            }
        }
    }
    //TODO: guess yourself

    public void printProductDetails(String name, String subCategoryName, String categoryName, String description, String creationTime, int visitCount, Double averageScore){
        label.setText("Name: " + name + "\n\nAt " + subCategoryName + "at " + categoryName + "\n\nDescription: " + description + "\n\nCreated at: " + creationTime + "\n\nVisited " + visitCount + " times\nAverage score: " + averageScore);
    }
    public void singleProductGraphics(PersonalInfoMenuView personalInfoMenuView){
        Stage stage = new Stage();
        VBox vBox = new VBox(10);
        Button addToBuyBasket = new Button("add to buy basket");
        Button back = new Button("Back");
        Label productId = new Label("Enter the Product ID: ");
        Label fill = new Label("please fill this field");
        fill.setVisible(false);
        fill.setTextFill(Color.RED);
        TextField productIdTextField = new TextField();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(label, fill, productId, productIdTextField,addToBuyBasket, back);

        stage.setScene(new Scene(vBox, 400, 700));
        stage.setTitle("Single Product Screen Menu");
        stage.show();
        addToBuyBasket.setOnAction(actionEvent -> {
            fill.setVisible(productId.getText().equals(""));
            fill.setVisible(!productId.getText().matches(".+"));
            if (!productIdTextField.getText().equals("") && productIdTextField.getText().matches(".+")) {
                if (ProgramManager.getProgramManagerInstance().getProductById(Integer.parseInt(productIdTextField.getText())) != null) {
                    ProgramManager.getProgramManagerInstance().addToCurrentBuyBasket(ProgramManager.getProgramManagerInstance().getProductById(Integer.parseInt(productIdTextField.getText())),1);
                    try {
                        new Alert().showAlert("Product With That Id Doesn't exist!", "Ok", 0, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            try {
                new Exit().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        back.setOnAction(actionEvent -> {
            stage.close();
            try {
                personalInfoMenuView.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    //////////////////////////////////////////////////////////////////////
    @Override
    public void start(Stage stage) throws Exception {
    }
}
