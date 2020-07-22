package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class ManageRequests_V extends GeneralController_V{
    public ListView<String> requestListView;
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
