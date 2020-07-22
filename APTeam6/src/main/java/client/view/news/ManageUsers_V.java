package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class ManageUsers_V extends GeneralController_V{
    public ListView<String> usersListView;

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
        //TODO: (message) index
    }
}
