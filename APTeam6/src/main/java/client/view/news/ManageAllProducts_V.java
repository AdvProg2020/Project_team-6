package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class ManageAllProducts_V extends GeneralController_V{
    public ListView<String> productsListView;

    @Override
    public void start() {
        senderReceiver.changeMenu(6);
        senderReceiver.sendMessage("");
        //TODO: WHAT??????
        String receipt = senderReceiver.getMessage();
        reset();
    }

    private void reset(){

    }

    public void initialize(){
        productsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void backButton(ActionEvent actionEvent) {
        //TODO: (message) -
    }

    public void removeButton(ActionEvent actionEvent) {
        if (productsListView.getSelectionModel().getSelectedIndices().size() == 1){
            //TODO: (message) index
        }
    }
}
