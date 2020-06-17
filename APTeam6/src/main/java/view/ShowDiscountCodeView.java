package view;

import controller.ProgramManager;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.product.DiscountCode;
import model.requests.Request;

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

    public void viewDiscountCode(DiscountCode discountCode) {
        System.out.println("the discountCode's ID is " + discountCode.getId());
        System.out.println("the discountCode's code is " + discountCode.getCode());
        System.out.println("the discountCode's startDate is " + discountCode.getStart());
        System.out.println("the discountCode's endDate is " + discountCode.getEnd());
        System.out.println("the discountCode's percentage is " + discountCode.getPercentage());
        System.out.println("the discountCode's repetitionTime is " + discountCode.getRepetitionTime());
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
}
