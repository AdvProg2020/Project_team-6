package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class ManageRequests_V extends GeneralController_V{
    public ListView<String> requestListView;
    public TextField requestId;
    String[] requests;

    @Override
    public void start() {
        senderReceiver.changeMenu(8);
        senderReceiver.sendMessage("06-0");
        String receipt = senderReceiver.getMessage();
        requests = receipt.split("---");
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
            senderReceiver.sendMessage("06-1"  +requestId.getText());
        }
    }

    public void declineButton(ActionEvent actionEvent) {
        if (requestListView.getSelectionModel().getSelectedIndices().size() == 1){
            if (requestListView.getSelectionModel().getSelectedIndices().size() == 1){
                senderReceiver.sendMessage("06-2"  +requestId.getText());
            }
        }
    }

    public void detailsButton(ActionEvent actionEvent) {
        if (requestListView.getSelectionModel().getSelectedIndices().size() == 1){
            //TODO: (message) index
        }
    }

    public void backButton(ActionEvent actionEvent) {
        senderReceiver.allControllers.get(10).start();
    }
}
