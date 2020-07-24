package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class ViewProductInCart_V extends GeneralController_V {
    public TextField nameTextField;
    public TextField categoryNameTextField;
    public TextField subCategoryNameTextField;
    public TextField descriptionTextField;
    public TextField priceTextField;


    @Override
    public void start() {
        senderReceiver.changeMenu(16);
        senderReceiver.sendMessage("14-1");
        String receipt = senderReceiver.getMessage().substring(4);
        String[] dataSplit = receipt.split("---");
        nameTextField.setText(dataSplit[0]);
        categoryNameTextField.setText(dataSplit[1]);
        subCategoryNameTextField.setText(dataSplit[2]);
        descriptionTextField.setText(dataSplit[3]);
        priceTextField.setText(dataSplit[4]);
    }
}
