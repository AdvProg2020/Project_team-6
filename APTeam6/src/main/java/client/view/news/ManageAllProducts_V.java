package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class ManageAllProducts_V {
    public ListView<String> productsListView;

    public void initialize(){
        productsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void backButton(ActionEvent actionEvent) {
        //TODO: (message) -
    }

    public void removeButton(ActionEvent actionEvent) {
        //TODO: (message) index
    }
}
