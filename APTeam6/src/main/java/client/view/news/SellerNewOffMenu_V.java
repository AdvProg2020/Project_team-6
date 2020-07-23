package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class SellerNewOffMenu_V {
    public ListView<CheckBox> productsListView;
    public TextField percentageTextField;

    public void reset(ArrayList<String> productNames){
        productsListView.getItems().clear();
        for (String productName : productNames) {
            productsListView.getItems().add(new CheckBox(productName));
        }
    }

    public void cancelButton(ActionEvent actionEvent) {
        //TODO (NO message)
    }

    public void createButton(ActionEvent actionEvent) {
        if (percentageTextField.getText().matches("[0-9]+")){
            //TODO (message) - selected indices, percentage
        }
    }
}
