package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.util.ArrayList;

public class ManageRequests_V extends GeneralController_V{
    public ListView<String> requestListView;

    @Override
    public void start() {
        senderReceiver.changeMenu(8);
        senderReceiver.sendMessage("06-0");
        String receipt = senderReceiver.getMessage();
        //TODO: Please ToT
        ArrayList<String> requests = new ArrayList<>();
        reset(requests);
    }

    private void reset(ArrayList<String> requests){
        requestListView.getItems().clear();
        requestListView.getItems().addAll(requests);
    }

    public void initialize(){
        requestListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void acceptButton(ActionEvent actionEvent) {
        if (requestListView.getSelectionModel().getSelectedIndices().size() == 1){
            //TODO: (message) index
        }
    }

    public void declineButton(ActionEvent actionEvent) {
        if (requestListView.getSelectionModel().getSelectedIndices().size() == 1){
            //TODO: (message) index
        }
    }

    public void detailsButton(ActionEvent actionEvent) {
        if (requestListView.getSelectionModel().getSelectedIndices().size() == 1){
            //TODO: (message) index
        }
    }

    public void backButton(ActionEvent actionEvent) {
        //TODO: (message) index
    }
}
