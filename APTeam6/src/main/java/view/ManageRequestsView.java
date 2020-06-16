package view;

import controller.ProgramManager;
import controller.managerPanels.ManageRequests;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.product.Product;


public class ManageRequestsView extends Application {
    public ManageRequestsView() {
        System.out.println("=== Manage Requests menu");
        System.out.println("The requests are (respectively):");
        ProgramManager.getProgramManagerInstance().showAllRequests();
    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    public void manageRequestPage(PersonalInfoMenuView personalInfoMenuView){
        Stage stage = new Stage();
        VBox vBox = new VBox();

        Label label = new Label("Please enter ID");
        TextField id = new TextField();
        Button detail = new Button("Show detail");
        Label detailLabel = new Label("");
        Button accept = new Button("Accept");
        Button decline = new Button("Decline");
        Button back = new Button("Back");

        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(label,id,detail,detailLabel,accept,decline,back);

        stage.setScene(new Scene(vBox,400,700));
        stage.setTitle("manage requests");
        stage.show();

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
            else if(command.matches("details \\.+")){
                return command;
            }
            else if(command.matches("(accept|decline) \\.+")){
                return command;
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }

    private void showHelp() {
        System.out.println("List of commands:\n\tdetails [requestId]\n\taccept [requestId]\n\tdecline [requestId]");
    }

    public void accept(int id){
        ProgramManager.getProgramManagerInstance().acceptRequests(id);
    }
    public void decline(int id){
        ProgramManager.getProgramManagerInstance().declineRequests(id);
    }
    public void details(int id){
        ProgramManager.getProgramManagerInstance().detailsOfRequest(id);
    }
}
