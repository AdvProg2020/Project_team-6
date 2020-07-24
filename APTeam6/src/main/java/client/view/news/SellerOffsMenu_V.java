package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class SellerOffsMenu_V extends GeneralController_V{
    public Label percentageLabel;
    public Label statusLabel;
    public ListView<String> offsList;
    public ListView<String> productsList;

    private String[] offs;

    @Override
    public void start() {
        senderReceiver.changeMenu(12);
        senderReceiver.sendMessage("13-0");
        String receipt = senderReceiver.getMessage();
        offs = receipt.split("---");
        ArrayList<String> strings = new ArrayList<>();
        for (String off : offs) strings.add(off.split("===")[0]);
        reset(strings);
    }

    public void initialize(){
        offsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        productsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        offs = new String[0];
        statusLabel.setVisible(false);
    }

    public void reset(ArrayList<String> allOffs){
        offsList.getItems().clear();
        productsList.getItems().clear();
        offsList.getItems().addAll(allOffs);
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

    public void clikedList(MouseEvent mouseEvent) {
        if (offsList.getSelectionModel().getSelectedIndices().size() == 1){
            int index = offsList.getSelectionModel().getSelectedIndices().get(0);
            String[] strings = offs[index].split("===");
            productsList.getItems().addAll(strings[2].split("@"));
            percentageLabel.setText(strings[2]);
        }
    }
}
