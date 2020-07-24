package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CategoryAndSubCategoryGenerator_V extends GeneralController_V{
    public ListView<String> attributeList;
    public TextField newAttributeText;
    public TextField nameText;
    public Label titleLabel;

    private boolean isSubCategory;

    @Override
    public void start(){
        senderReceiver.changeMenu(1);
        reset();
    }

    private void reset(){
        attributeList.getItems().clear();
        newAttributeText.setText("");
        nameText.setText("");
    }

    public void setSubCategory(boolean subCategory) {
        isSubCategory = subCategory;
    }

    public void finishButton(ActionEvent actionEvent) {
        if (nameText.getText().matches("[a-zA-Z0-9]+")) {
            //TODO: (Message) all attributes, name
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please use alphabet and numbers only");
            alert.showAndWait();
        }
    }

    public void removeButton(ActionEvent actionEvent) {
        if (attributeList.getSelectionModel().getSelectedIndices().size() == 1){
            attributeList.getItems().remove(attributeList.getItems().get(attributeList.getSelectionModel().getSelectedIndices().get(0)));
        }
    }

    public void addButton(ActionEvent actionEvent) {
        if (!attributeList.getItems().contains(newAttributeText.getText())){
            if (newAttributeText.getText().matches("[a-zA-Z0-9]+")) {
                attributeList.getItems().add(newAttributeText.getText());
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please use alphabet and numbers only");
                alert.showAndWait();
            }
        }
    }

    public void cancelButton(ActionEvent actionEvent) {
        senderReceiver.allControllers.get(0).start();
    }
}
