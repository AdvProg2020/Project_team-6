package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;

public class ManageUsers_V extends GeneralController_V {
    public ListView<Label> usersListView;

    @Override
    public void start() {
        senderReceiver.changeMenu(9);
        senderReceiver.sendMessage("09-0");
        String receipt = senderReceiver.getMessage();
        ArrayList<String> strings = new ArrayList<>(Arrays.asList(receipt.split("===")));

        //reset(new ArrayList<String>(Arrays.asList(receipt.)));
    }

    private void reset(ArrayList<String> strings) {
        usersListView.getItems().clear();
        for (String user : strings) {
            Label label = new Label(user.split("---")[0]);
            if (user.split("---")[2].equals("true"))
                label.setTextFill(Color.GREEN);
            if (user.split("---")[2].equals("false"))
                label.setTextFill(Color.RED);
            usersListView.getItems().add(label);
        }
    }

    public void initialize() {
        usersListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void deleteButton(ActionEvent actionEvent) {
        if (usersListView.getSelectionModel().getSelectedIndices().size() == 1) {
            int index = usersListView.getSelectionModel().getSelectedIndices().get(0);
            senderReceiver.sendMessage("09-2" + usersListView.getItems().get(index).getText());
            new Alert(Alert.AlertType.NONE ,senderReceiver.getMessage()).showAndWait();
        }
    }

    public void detailsButton(ActionEvent actionEvent) {
        if (usersListView.getSelectionModel().getSelectedIndices().size() == 1) {
            int index = usersListView.getSelectionModel().getSelectedIndices().get(0);
            senderReceiver.sendMessage("09-1" + usersListView.getItems().get(index).getText());
            new Alert(Alert.AlertType.INFORMATION ,senderReceiver.getMessage()).showAndWait();
        }
    }

    public void backButton(ActionEvent actionEvent) {
        senderReceiver.allControllers.get(10).start();
    }
}
