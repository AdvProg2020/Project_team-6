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
import model.product.DiscountCode;
import model.requests.Request;

import javax.swing.*;
import java.io.FileInputStream;
import java.util.ArrayList;

public class ShowDiscountCodeView extends Application {

    public void giveOutput(String message) {
        System.out.println(message);
    }

    public ShowDiscountCodeView() {
        System.out.println("=== Discount codes view menu\n\tCurrent codes:");
        ArrayList<Request> requests = ProgramManager.getProgramManagerInstance().getAllRequests();
        for (Request request : requests) {
            System.out.println("\t\t" + request);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

        //TODO

        VBox vBox = new VBox();
        stage.setTitle("Discount code");
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

    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.equals("back")) {
                return command;
            }
            else if(command.equals("help")){
                showHelp();
            }
            else if (command.matches("view discount code \\.+")) {
                return command;
            }
            else if (command.matches("edit discount code \\.+")) {
                return command;
            }
            else if (command.matches("remove discount code \\.+")) {
                return command;
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }

    private void showHelp() {
        System.out.println("List of commands:\n\tview discount code [discountCode code]\n\t edit discount code [discountCode code] [changingValue]\n\t remove discount code [discountCode code]\n\t");
    }

    public String viewDiscountCode1(DiscountCode discountCode) {
        return "the discountCode's ID is " + discountCode.getId();
    }
    public String viewDiscountCode2(DiscountCode discountCode) {
        return "the discountCode's code is " + discountCode.getCode();
    }
    public String viewDiscountCode3(DiscountCode discountCode) {
        return "the discountCode's startDate is " + discountCode.getStart();
    }
    public String viewDiscountCode4(DiscountCode discountCode) {
        return "the discountCode's endDate is " + discountCode.getEnd();
    }
    public String viewDiscountCode5(DiscountCode discountCode) {
        return "the discountCode's percentage is " + discountCode.getPercentage();
    }
    public String viewDiscountCode6(DiscountCode discountCode) {
        return "the discountCode's repetitionTime is " + discountCode.getRepetitionTime();
    }

    public void editDiscountCodeCode(DiscountCode discountCode, String code) {
        discountCode.setCode(code);
    }

    public void editDiscountCodeStartDate(DiscountCode discountCode, String startDate) {
        discountCode.setStart(ProgramManager.getProgramManagerInstance().parsingStringToDate(startDate));
    }

    public void editDiscountCodeEndDate(DiscountCode discountCode, String endDate) {
        discountCode.setEnd(ProgramManager.getProgramManagerInstance().parsingStringToDate(endDate));
    }

    public void editDiscountCodePercentage(DiscountCode discountCode, int percentage) {
        discountCode.setPercentage(percentage);
    }

    public void editDiscountCodeRepetitionTime(DiscountCode discountCode, int repetitionTime) {
        discountCode.setRepetitionTime(repetitionTime);
    }

    public void removeDiscountCode(DiscountCode discountCode) {
        ProgramManager.getProgramManagerInstance().deleteDiscountCode(discountCode);
    }

    public void manageRequestPage(PersonalInfoMenuView personalInfoMenuView) {
            Stage stage = new Stage();
            VBox vBox = new VBox();
            Label label = new Label("Enter Discount Code");
            Label fill = new Label("please fill this field");
            fill.setVisible(false);
            fill.setTextFill(Color.RED);
            TextField discountCode = new TextField();
            Button view = new Button("View");
            Label detailLabel = new Label("");
            Label detailLabel2 = new Label("");
            Label detailLabel3 = new Label("");
            Label detailLabel4 = new Label("");
            Label detailLabel5 = new Label("");
            Label detailLabel6 = new Label("");
            detailLabel.setWrapText(true);
            Button edit = new Button("Edit");
            Button remove = new Button("Remove");
            Button back = new Button("Back");
            vBox.setAlignment(Pos.CENTER);
            vBox.getChildren().addAll(label,fill,discountCode,view,detailLabel,view,remove,back,detailLabel2,detailLabel3,detailLabel4,detailLabel5,detailLabel6);

            stage.setScene(new Scene(vBox,400,700));
            stage.setTitle("Manage Discounts");
            stage.show();
            /*
            edit.setOnAction(actionEvent -> {
                fill.setVisible(discountCode.getText().equals(""));
                fill.setVisible(!discountCode.getText().matches("\\.+"));
                if(!discountCode.getText().equals("") && discountCode.getText().matches("\\.+")){
                    if(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode.getText())!=null){
                        detailLabel.setText(//TODO);
                    }else{
                        try {
                            new Alert().showAlert("Request with this id doesnt exist","Ok",0,null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });*/

            view.setOnAction(actionEvent -> {
                fill.setVisible(discountCode.getText().equals(""));
                fill.setVisible(!discountCode.getText().matches("\\.+"));
                if(!discountCode.getText().equals("") && discountCode.getText().matches("\\.+")){
                    if(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode.getText()) != null){
                        detailLabel.setText(viewDiscountCode1(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode.getText())));
                        detailLabel2.setText(viewDiscountCode2(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode.getText())));
                        detailLabel3.setText(viewDiscountCode3(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode.getText())));
                        detailLabel4.setText(viewDiscountCode4(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode.getText())));
                        detailLabel5.setText(viewDiscountCode5(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode.getText())));
                        detailLabel6.setText(viewDiscountCode6(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode.getText())));
                        detailLabel.setText("");
                        detailLabel2.setText("");
                        detailLabel3.setText("");
                        detailLabel4.setText("");
                        detailLabel5.setText("");
                        detailLabel6.setText("");
                        discountCode.setText("");
                    }else{
                        try {
                            new Alert().showAlert("DiscountCode with this code doesnt exist","Ok",0,null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });


            remove.setOnAction(actionEvent -> {
                fill.setVisible(discountCode.getText().equals(""));
                fill.setVisible(!discountCode.getText().matches("\\.+"));
                if(!discountCode.getText().equals("") && discountCode.getText().matches("\\.+")){
                    if(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode.getText()) != null){
                        removeDiscountCode(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode.getText()));
                        detailLabel.setText("");
                        discountCode.setText("");
                        try {
                            new Alert().showAlert("DiscountCode removed successfully!","Ok",0,null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        try {
                            new Alert().showAlert("Discount Code with this code doesnt exist","Ok",0,null);
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
