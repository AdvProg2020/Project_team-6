package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.util.ArrayList;
import java.util.Arrays;

public class CategoriesAndSubCategoriesMenu_V extends GeneralController_V{
    @Override
    public void start(){
        senderReceiver.changeMenu(0);
        senderReceiver.sendMessage("12-0");
        String receipt = senderReceiver.getMessage();
        ArrayList<String> allCategories = new ArrayList<>(Arrays.asList(receipt.substring(3).split("---")));
        reset(allCategories);
    }

    public void reset(ArrayList<String> allCategories){
        listView.getItems().clear();
        listView.getItems().addAll(allCategories);
    }

    public ListView<String> listView;
    public ListView<String> attributesListView;

    public void initialize(){
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void backBtn(ActionEvent actionEvent) {
        senderReceiver.
    }

    public void addBtn(ActionEvent actionEvent) {
        //TODO: (message) -
    }

    public void removeBtn(ActionEvent actionEvent) {
        if (listView.getSelectionModel().getSelectedIndices().size() == 1){
            //TODO: (message) index
        }
    }

    public void editBtn(ActionEvent actionEvent) {
        if (listView.getSelectionModel().getSelectedIndices().size() == 1){
            //TODO: (message) index
        }
    }

    public void openBtn(ActionEvent actionEvent) {
        if (listView.getSelectionModel().getSelectedIndices().size() == 1){
            //TODO: (message) index
        }
    }
}
