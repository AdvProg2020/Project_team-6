package view;

import controller.ProgramManager;
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
    public void manageProducts(PersonalInfoMenuView personalInfoMenuView){
        Stage stage = new Stage();
        VBox vBox = new VBox(10);
        Label label = new Label("Enter Discount Code");
        Label fill = new Label("please fill this field");
        fill.setVisible(false);
        fill.setTextFill(Color.RED);

        TextField discountCode = new TextField();
        discountCode.setPrefWidth(200);
        discountCode.setMaxWidth(200);
        discountCode.setMinWidth(200);

        Button view = new Button("View");

        Label codeLabel = new Label();
        Label startDateLabel = new Label();
        Label endDateLabel = new Label();
        Label percentageLabel = new Label();
        Label repetitionTimeLabel = new Label();

        TextField codeTextField = new TextField();
        TextField startDateTextField = new TextField();
        TextField endDateTextField = new TextField();
        TextField percentageTextField = new TextField();
        TextField repetitionTimeTextField = new TextField();

        codeLabel.setWrapText(true);
        Button edit = new Button("Edit");
        Button remove = new Button("Remove");
        Button back = new Button("Back");

        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(label, fill, discountCode, view, edit, remove, back, codeLabel, startDateLabel, endDateLabel, percentageLabel, repetitionTimeLabel);

        stage.setScene(new Scene(vBox, 400, 700));
        stage.setTitle("Manage Discounts");
        stage.show();
        edit.setOnAction(actionEvent -> {

            fill.setVisible(discountCode.getText().equals(""));
            fill.setVisible(!discountCode.getText().matches("\\.+"));
            if (!discountCode.getText().equals("") && discountCode.getText().matches("\\.+")) {
                if (ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode.getText()) != null) {
                    codeLabel.setText("Enter your new code:");
                    codeTextField.setPromptText("Code");
                    ShowDiscountCode.getShowDiscountCodeInstance().editDiscountCodeCode(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode.getText()), codeTextField.getText());

                    startDateLabel.setText("Enter your new Start Date:");
                    startDateTextField.setPromptText("Start Date");
                    ShowDiscountCode.getShowDiscountCodeInstance().editDiscountCodeStartDate(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode.getText()), startDateTextField.getText());

                    endDateLabel.setText("Enter your new End Date:");
                    endDateTextField.setPromptText("End Date");
                    ShowDiscountCode.getShowDiscountCodeInstance().editDiscountCodeEndDate(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode.getText()), endDateTextField.getText());


                    percentageLabel.setText("Enter your new Percentage:");
                    percentageTextField.setPromptText("Percentage");
                    ShowDiscountCode.getShowDiscountCodeInstance().editDiscountCodePercentage(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode.getText()), Integer.parseInt(percentageTextField.getText()));


                    repetitionTimeLabel.setText("Enter your new Repetition Time:");
                    repetitionTimeTextField.setPromptText("RepetitionTime");
                    ShowDiscountCode.getShowDiscountCodeInstance().editDiscountCodeRepetitionTime(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode.getText()), Integer.parseInt(repetitionTimeTextField.getText()));
                    try {
                        new Alert().showAlert("DiscountCode with this Code doesn't exist", "Ok", 0, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        view.setOnAction(actionEvent -> {
            fill.setVisible(discountCode.getText().equals(""));
            fill.setVisible(!discountCode.getText().matches("\\.+"));
            if (!discountCode.getText().equals("") && discountCode.getText().matches("\\.+")) {
                if (ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode.getText()) != null) {
                    codeLabel.setText(viewDiscountCode1(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode.getText())));
                    startDateLabel.setText(viewDiscountCode2(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode.getText())));
                    endDateLabel.setText(viewDiscountCode3(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode.getText())));
                    percentageLabel.setText(viewDiscountCode4(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode.getText())));
                    repetitionTimeLabel.setText(viewDiscountCode5(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode.getText())));
                    codeLabel.setText("");
                    startDateLabel.setText("");
                    endDateLabel.setText("");
                    percentageLabel.setText("");
                    repetitionTimeLabel.setText("");
                    discountCode.setText("");
                } else {
                    try {
                        new Alert().showAlert("DiscountCode with this code doesnt exist", "Ok", 0, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        remove.setOnAction(actionEvent -> {
            fill.setVisible(discountCode.getText().equals(""));
            fill.setVisible(!discountCode.getText().matches("\\.+"));
            if (!discountCode.getText().equals("") && discountCode.getText().matches("\\.+")) {
                if (ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode.getText()) != null) {
                    removeDiscountCode(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode.getText()));
                    codeLabel.setText("");
                    discountCode.setText("");
                    try {
                        new Alert().showAlert("DiscountCode removed successfully!", "Ok", 0, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        new Alert().showAlert("Discount Code with this code doesnt exist", "Ok", 0, null);
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
