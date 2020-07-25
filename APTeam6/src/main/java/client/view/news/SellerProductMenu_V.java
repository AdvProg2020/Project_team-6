package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Arrays;

public class SellerProductMenu_V extends GeneralController_V{
    public TextField nameTextField;
    public TextArea descriptionTextArea;
    public ListView<TextField> categoryInfoListView;
    public ListView<TextField> subCategoryInfoListView;

    @Override
    public void start() {
        senderReceiver.changeMenu(13);
        senderReceiver.sendMessage("12-b");
        String receipt1 = senderReceiver.getMessage();
        ArrayList<String> catAdditional = new ArrayList<>(Arrays.asList(receipt1.split("@")[0].split("---")));
        ArrayList<String> subAdditional = new ArrayList<>(Arrays.asList(receipt1.split("@")[1].split("---")));
        reset(catAdditional, subAdditional);
    }

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
        senderReceiver.allControllers.get(0).start();
    }

    public void createButton(ActionEvent actionEvent) {
        if (nameTextField.getText().matches("[a-zA-Z0-9]+")){
            senderReceiver.sendMessage("11-0");
            String receipt1 = senderReceiver.getMessage();
            senderReceiver.sendMessage("11-3");
            String receipt2 = senderReceiver.getMessage();
            //TODO (message) name, description, list of infos. maybe get category
        }
        else
            new Alert(Alert.AlertType.ERROR, "Bad inputs!", new ButtonType("Ok, sorry")).showAndWait();
    }
}
