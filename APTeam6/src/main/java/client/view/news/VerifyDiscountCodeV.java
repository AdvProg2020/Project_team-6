package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class VerifyDiscountCodeV extends GeneralController_V {
    public Label Address;
    public Label PhoneNumber;
    public CheckBox DiscountCheck;
    public TextField AddressTextField;
    public TextField PhoneNumberTextField;
    public TextField DiscountCode;
    public Button verifyAndSubmit;

    public void DiscountCheck(ActionEvent actionEvent) {
        if(DiscountCheck.isSelected()){
            DiscountCode.setEditable(true);
        }else {
            DiscountCode.setEditable(false);
        }
    }

    public void verifyAndSubmit(ActionEvent actionEvent) {
        AddressTextField.setText("");
        PhoneNumberTextField.setText("");
        DiscountCode.setText("");
    }
    public void start() {
        senderReceiver.changeMenu(17);
        senderReceiver.sendMessage("15-1");
        String receipt = senderReceiver.getMessage();
        if(receipt.startsWith("verified")){

        }

    }
}
