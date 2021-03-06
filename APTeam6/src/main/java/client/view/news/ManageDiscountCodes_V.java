package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import server.model.product.DiscountCode;

import java.util.ArrayList;

public class ManageDiscountCodes_V extends GeneralController_V{
    public ListView<String> discountCodeListView;
    public TextField Code;
    String[] discountCodes;

    @Override
    public void start() {
        senderReceiver.changeMenu(7);
        senderReceiver.sendMessage("04-0");
        String receipt = senderReceiver.getMessage();
        ArrayList<String> strings = new ArrayList<>();
        discountCodes = receipt.split("---");
        for (String discountCode : discountCodes) strings.add(discountCode.split("===")[0]);
        reset(strings);
    }

    public void initialize(){
        discountCodeListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void reset(ArrayList<String> codes){
        discountCodeListView.getItems().clear();
        discountCodeListView.getItems().addAll(codes);
    }

    public void backButton(ActionEvent actionEvent) {
        senderReceiver.allControllers.get(10).start();
    }

    public void editButton(ActionEvent actionEvent) {
        //TODO I won't write this now. maybe later

    }

    public void deleteButton(ActionEvent actionEvent) {
        senderReceiver.sendMessage("04-3" + Code.getText());
    }

    public void addButton(ActionEvent actionEvent) {
        senderReceiver.allControllers.get(2).start();
    }
}
