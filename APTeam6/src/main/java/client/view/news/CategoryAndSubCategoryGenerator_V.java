package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CategoryAndSubCategoryGenerator_V {
    public ListView attributeList;
    public TextField newAttributeText;
    public TextField nameText;
    public Label titleLabel;

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
            attributeList.getItems().remove(attributeList.getSelectionModel().getSelectedIndices().get(0));
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
}
