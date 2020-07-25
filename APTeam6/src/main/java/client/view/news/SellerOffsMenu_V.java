package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.util.ArrayList;

public class SellerOffsMenu_V extends GeneralController_V{
    public Label percentageLabel;
    public Label statusLabel;
    public ListView<String> offsList;
    public ListView<String> productsList;

    @Override
    public void start() {
        senderReceiver.changeMenu(12);
        senderReceiver.sendMessage("13-0");
        String receipt = senderReceiver.getMessage();
        reset();
    }

    public void initialize(){
        offsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        productsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void reset(String percentage, String status, ArrayList<String> allOffs, ArrayList<String> allProducts){
        percentageLabel.setText(percentage);
        statusLabel.setText(status);
        offsList.getItems().clear();
        productsList.getItems().clear();
        offsList.getItems().addAll(allOffs);
        productsList.getItems().addAll(allProducts);
    }

    public void backButton(ActionEvent actionEvent) {
        senderReceiver.allControllers.get(10).start();
    }

    public void newButton(ActionEvent actionEvent) {
        senderReceiver.allControllers.get(11).start();
    }

    public void editButton(ActionEvent actionEvent) {
        if (offsList.getSelectionModel().getSelectedIndices().size() == 1){
            //TODO: (message) index
        }
    }

    public void deleteButton(ActionEvent actionEvent) {
        if (offsList.getSelectionModel().getSelectedIndices().size() == 1){
            //TODO: (message) index
        }
    }
}
