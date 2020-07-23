package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.util.ArrayList;

public class ManageDiscountCodes_V extends GeneralController_V{
    public ListView<String> discountCodeListView;

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
