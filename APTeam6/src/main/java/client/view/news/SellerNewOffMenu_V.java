package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class SellerNewOffMenu_V extends GeneralController_V{
    public ListView<CheckBox> productsListView;
    public TextField percentageTextField;

    @Override
    public void start() {
        senderReceiver.changeMenu(11);
        reset(); //TODO: I need a method in chontroller for getting all product names
    }

    public void reset(ArrayList<String> productNames){
        productsListView.getItems().clear();
        for (String productName : productNames) {
            productsListView.getItems().add(new CheckBox(productName));
        }
    }

    public void cancelButton(ActionEvent actionEvent) {
        senderReceiver.allControllers.get(12).start();
    }

    public void createButton(ActionEvent actionEvent) {
        if (percentageTextField.getText().matches("[0-9]+")){
            //TODO (message) - selected indices, percentage
        }
    }
}
