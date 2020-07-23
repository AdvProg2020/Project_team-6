package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.ArrayList;

public class SellerProductMenu_V extends GeneralController_V{
    public TextField nameTextField;
    public TextArea descriptionTextArea;
    public ListView<TextField> categoryInfoListView;
    public ListView<TextField> subCategoryInfoListView;

    public void reset(ArrayList<String> categoryInfo, ArrayList<String> subCategoryInfo){
        categoryInfoListView.getItems().clear();
        subCategoryInfoListView.getItems().clear();
        for (String info : categoryInfo) {
            TextField field = new TextField();
            field.setPromptText(info);
            categoryInfoListView.getItems().add(field);
        }
        for (String info : subCategoryInfo) {
            TextField field = new TextField();
            field.setPromptText(info);
            subCategoryInfoListView.getItems().add(field);
        }
    }

    public void backButton(ActionEvent actionEvent) {
        //TODO (message) -
    }

    public void createButton(ActionEvent actionEvent) {
        if (nameTextField.getText().matches("[a-zA-Z0-9]+")){
            //TODO (message) name, description, list of infos
        }
        else
            new Alert(Alert.AlertType.ERROR, "Bad inputs!", new ButtonType("Ok, sorry")).showAndWait();
    }
}
