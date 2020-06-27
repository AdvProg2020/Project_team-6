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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.product.Product;


public class ManageRequestsView extends Application {
    public ManageRequestsView() {
        System.out.println("=== Manage Requests menu");
        System.out.println("The requests are (respectively):");
        System.out.println(ProgramManager.getProgramManagerInstance().showAllRequests());
    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    public void manageRequestPage(PersonalInfoMenuView personalInfoMenuView) {
        Stage stage = new Stage();
        VBox vBox = new VBox();
        vBox.setStyle("-fx-background-color: #86fffa");
        Label label = new Label("Please enter ID");
        Label fill = new Label("please fill this field");
        fill.setVisible(false);
        fill.setTextFill(Color.RED);
        TextField id = new TextField();
        Button detail = new Button("Show detail");
        Label detailLabel = new Label("");
        detailLabel.setWrapText(true);
        Button accept = new Button("Accept");
        Button decline = new Button("Decline");
        Button back = new Button("Back");

        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(label, fill, id, detail, detailLabel, accept, decline, back);

        stage.setScene(new Scene(vBox, 400, 700));
        stage.setTitle("manage requests");
        stage.show();

        detail.setOnAction(actionEvent -> {
            fill.setVisible(id.getText().equals(""));
            fill.setVisible(!id.getText().matches("[0-9]+"));
            if (!id.getText().equals("") && id.getText().matches("[0-9]+")) {
                if (details(Integer.parseInt(id.getText())) != null) {
                    detailLabel.setText(details(Integer.parseInt(id.getText())));
                } else {
                    try {
                        new Alert().showAlert("Request with this id doesnt exist", "Ok", 0, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        accept.setOnAction(actionEvent -> {
            fill.setVisible(id.getText().equals(""));
            fill.setVisible(!id.getText().matches("[0-9]+"));
            if (!id.getText().equals("") && id.getText().matches("[0-9]+")) {
                if (details(Integer.parseInt(id.getText())) != null) {
                    accept(Integer.parseInt(id.getText()));
                    detailLabel.setText("");
                    id.setText("");
                    try {
                        new Alert().showAlert("Accepted!", "Ok", 0, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        new Alert().showAlert("Request with this id doesnt exist", "Ok", 0, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        decline.setOnAction(actionEvent -> {
            fill.setVisible(id.getText().equals(""));
            fill.setVisible(!id.getText().matches("[0-9]+"));
            if (!id.getText().equals("") && id.getText().matches("[0-9]+")) {
                if (details(Integer.parseInt(id.getText())) != null) {
                    accept(Integer.parseInt(id.getText()));
                    detailLabel.setText("");
                    id.setText("");
                    try {
                        new Alert().showAlert("Declined!", "Ok", 0, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        new Alert().showAlert("Request with this id doesnt exist", "Ok", 0, null);
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

    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.equals("back")) {
                return command;
            } else if (command.equals("help")) {
                showHelp();
            } else if (command.matches("details .+")) {
                return command;
            } else if (command.matches("(accept|decline) .+")) {
                return command;
            } else {
                System.out.println("Invalid command");
            }
        }
    }

    private void showHelp() {
        System.out.println("List of commands:\n\tdetails [requestId]\n\taccept [requestId]\n\tdecline [requestId]");
    }

    public void accept(int id) {
        ProgramManager.getProgramManagerInstance().acceptRequests(id);
    }

    public void decline(int id) {
        ProgramManager.getProgramManagerInstance().declineRequests(id);
    }

    public String details(int id) {
        return ProgramManager.getProgramManagerInstance().detailsOfRequest(id);
    }
}
