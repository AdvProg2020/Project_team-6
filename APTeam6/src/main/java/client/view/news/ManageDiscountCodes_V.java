package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.util.ArrayList;

public class ManageDiscountCodes_V extends GeneralController_V{
    public ListView<String> discountCodeListView;

    @Override
    public void start() {
        senderReceiver.changeMenu(7);
        senderReceiver.sendMessage("04-0");
        String receipt = senderReceiver.getMessage();
        //TODO: WHAT??????
        ArrayList<String> strings = new ArrayList<>();
        reset(strings);
    }

    public void initialize(){
        discountCodeListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void reset(ArrayList<String> codes){
        discountCodeListView.getItems().clear();
        discountCodeListView.getItems().addAll(codes);
    }

    public void backButton(ActionEvent actionEvent) {
        //TODO (message) -
    }

    public void editButton(ActionEvent actionEvent) {
        //TODO I won't write this now. maybe later
    }

    public void deleteButton(ActionEvent actionEvent) {
        //TODO (message) code
    }

    public void addButton(ActionEvent actionEvent) {
        //TODO (message) -
    }
}
