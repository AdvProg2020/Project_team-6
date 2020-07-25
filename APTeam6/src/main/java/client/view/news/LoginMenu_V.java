package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

public class LoginMenu_V extends GeneralController_V{
    public TextField userNameText;
    public TextField passwordText;
    public Button loginButton;
    public Button logoutButton;
    public Button registerManagerButton;

    @Override
    public void start() {
        senderReceiver.changeMenu(4);
        senderReceiver.sendMessage("03-0");
        String receipt = senderReceiver.getMessage();
        reset();
    }

    private void reset(){
        userNameText.setText("");
        passwordText.setText("");
    }

    public void backButtonAction(ActionEvent actionEvent) {
        senderReceiver.allControllers.get(5).start();
    }

    public void logoutButtonAction(ActionEvent actionEvent) {
        senderReceiver.sendMessage("03-5");
        new Alert(Alert.AlertType.INFORMATION, senderReceiver.getMessage()).showAndWait();
    }

    public void loginButtonAction(ActionEvent actionEvent) {
        senderReceiver.sendMessage("03-4" + userNameText.getText() + "---" + passwordText.getText());
        new Alert(Alert.AlertType.INFORMATION, senderReceiver.getMessage()).showAndWait();
    }

    public void registerBuyerButtonAction(ActionEvent actionEvent) {
        String[] strings = getBuyerOrManagerInfo();
        StringBuilder builder = new StringBuilder(userNameText.getText() + "---" + passwordText.getText());
        for (String string : strings) {
            builder.append("---").append(string);
        }
        senderReceiver.sendMessage("03-1" + builder.toString());
        new Alert(Alert.AlertType.INFORMATION, senderReceiver.getMessage()).showAndWait();
    }

    public void registerManagerButtonAction(ActionEvent actionEvent) {
        String[] strings = getBuyerOrManagerInfo();
        StringBuilder builder = new StringBuilder(userNameText.getText() + "---" + passwordText.getText());
        for (String string : strings) {
            builder.append("---").append(string);
        }
        senderReceiver.sendMessage("03-3" + builder.toString());
        new Alert(Alert.AlertType.INFORMATION, senderReceiver.getMessage()).showAndWait();
    }

    public void registerSellerButtonAction(ActionEvent actionEvent) {
        String[] strings = getSellerInfo();
        StringBuilder builder = new StringBuilder(userNameText.getText() + "---" + passwordText.getText());
        for (String string : strings) {
            builder.append("---").append(string);
        }
        senderReceiver.sendMessage("03-2" + builder.toString());
        new Alert(Alert.AlertType.INFORMATION, senderReceiver.getMessage()).showAndWait();
    }

    //////////////////////////////////////

    private String[] getBuyerOrManagerInfo(){
        String[] strings = new String[4];
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Buyer info");
        inputDialog.getDialogPane().setStyle("-fx-background-color: #eeffff;");

        inputDialog.setHeaderText("Enter your first name");
        do {
            inputDialog.showAndWait();
            strings[0] = inputDialog.getResult();
        }while (!strings[0].matches("[a-zA-Z0-9]+"));

        inputDialog.setContentText("");
        inputDialog.setHeaderText("Enter your last name");
        do {
            inputDialog.showAndWait();
            strings[1] = inputDialog.getResult();
        }while (!strings[1].matches("[a-zA-Z0-9]+"));

        inputDialog.setContentText("");
        inputDialog.setHeaderText("Enter your phone Num");
        do {
            inputDialog.showAndWait();
            strings[2] = inputDialog.getResult();
        }while (!strings[2].matches("[0-9]+"));

        inputDialog.setContentText("");
        inputDialog.setHeaderText("Enter your email address");
        do {
            inputDialog.showAndWait();
            strings[3] = inputDialog.getResult();
        }while (!strings[3].matches("[a-zA-Z0-9@.]+"));

        return strings;
    }

    private String[] getSellerInfo(){
        String[] strings = new String[5];
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Seller info");
        inputDialog.getDialogPane().setStyle("-fx-background-color: #ffffee;");

        inputDialog.setHeaderText("Enter your first name");
        do {
            inputDialog.showAndWait();
            strings[0] = inputDialog.getResult();
        }while (!strings[0].matches("[a-zA-Z0-9]+"));

        inputDialog.setContentText("");
        inputDialog.setHeaderText("Enter your last name");
        do {
            inputDialog.showAndWait();
            strings[1] = inputDialog.getResult();
        }while (!strings[1].matches("[a-zA-Z0-9]+"));

        inputDialog.setContentText("");
        inputDialog.setHeaderText("Enter your phone Num");
        do {
            inputDialog.showAndWait();
            strings[2] = inputDialog.getResult();
        }while (!strings[2].matches("[0-9]+"));

        inputDialog.setContentText("");
        inputDialog.setHeaderText("Enter your email address");
        do {
            inputDialog.showAndWait();
            strings[3] = inputDialog.getResult();
        }while (!strings[3].matches("[a-zA-Z0-9@.]+"));

        inputDialog.setContentText("");
        inputDialog.setHeaderText("Enter your chompany name");
        do {
            inputDialog.showAndWait();
            strings[4] = inputDialog.getResult();
        }while (!strings[4].matches("[a-zA-Z0-9]+"));

        return strings;
    }
}
