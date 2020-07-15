package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class CategoriesAndSubCategoriesMenu_V extends GeneralController_V{
    //This method is to be called before setting the scene
    public void reset(){
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public ListView listView;
    public ListView attributesListView;

    public void backBtn(ActionEvent actionEvent) {
        //TODO: (message) -
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
