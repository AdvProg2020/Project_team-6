package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class ManageAllProducts_V extends GeneralController_V{
    public ListView<String> productsListView;

    @Override
    public void start() {
        senderReceiver.changeMenu(6);
        senderReceiver.sendMessage("");
        String receipt = senderReceiver.getMessage();
        reset(receipt.split("@"));
    }

    private void reset(String[] strings){
        productsListView.getItems().clear();
        productsListView.getItems().addAll(strings);
    }

    public void initialize(){
        productsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void backButton(ActionEvent actionEvent) {
        senderReceiver.allControllers.get(10).start();
    }

    public void removeButton(ActionEvent actionEvent) {
        if (productsListView.getSelectionModel().getSelectedIndices().size() == 1){
            senderReceiver.sendMessage("18-1" + productsListView.getSelectionModel().getSelectedIndices().get(0));
            new Alert(Alert.AlertType.NONE, senderReceiver.getMessage()).showAndWait();
        }
    }
}
