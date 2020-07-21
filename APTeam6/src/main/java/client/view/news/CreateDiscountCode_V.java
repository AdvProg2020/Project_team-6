package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CreateDiscountCode_V extends GeneralController_V{
    public TextField codeText;
    public TextField daysBeforeStartText;
    public TextField daysBeforeEndText;
    public TextField percentageText;
    public TextField repetitionText;
    public ListView<CheckBox> buyersListView;

    public void createButton(ActionEvent actionEvent) {
        if (codeText.getText().equals("") || daysBeforeStartText.getText().equals("") || daysBeforeEndText.getText().equals("") || percentageText.getText().equals("") || repetitionText.getText().equals("")){
            new Alert(Alert.AlertType.ERROR, "No empty fields!").showAndWait();
        }
        else if (!codeText.getText().matches("[0-9]+") || !daysBeforeStartText.getText().matches("[0-9]+") || !daysBeforeEndText.getText().matches("[0-9]+") || !percentageText.getText().matches("[0-9]+") || !repetitionText.getText().matches("[0-9]+")){
            new Alert(Alert.AlertType.ERROR, "Wrong input!").showAndWait();
        }
        else {
            //TODO: (Message) everything
        }
    }

    public void cancelButton(ActionEvent actionEvent) {
        //TODO: (Message) back
    }
}
