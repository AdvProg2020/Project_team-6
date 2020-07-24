package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.util.ArrayList;
import java.util.Arrays;

public class ManageUsers_V extends GeneralController_V{
    public ListView<String> usersListView;

    @Override
    public void start() {
        senderReceiver.changeMenu(9);
        senderReceiver.sendMessage("09-0");
        String receipt = senderReceiver.getMessage();
        //TODO: kidding me?
        //reset(new ArrayList<String>(Arrays.asList(receipt.)));
    }

    private void reset(ArrayList<String> users){
        usersListView.getItems().clear();
        usersListView.getItems().addAll(users);
    }

    public void initialize(){
        usersListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void deleteButton(ActionEvent actionEvent) {
        if (usersListView.getSelectionModel().getSelectedIndices().size() == 1){
            //TODO: (message) index
        }
    }

    public void detailsButton(ActionEvent actionEvent) {
        if (usersListView.getSelectionModel().getSelectedIndices().size() == 1){
            //TODO: (message) index
        }
    }

    public void backButton(ActionEvent actionEvent) {
        senderReceiver.allControllers.get(10).start();
    }
}
