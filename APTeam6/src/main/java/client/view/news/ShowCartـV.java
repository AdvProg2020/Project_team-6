package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import server.model.product.Product;

import java.util.ArrayList;

public class ShowCartـV extends GeneralController_V{
    public Button decrease;
    public Button increase;
    public Button viewProduct;
    public Button showTotalPrice;
    public Button purchase;
    public TextField productId;
    public ListView<String> productList;
    private String[] products;
    public void increase(ActionEvent actionEvent) {
        //TODO
        senderReceiver.sendMessage(productId.getText());
    }
    public void decrease(ActionEvent actionEvent) {
        //TODO
        senderReceiver.sendMessage(productId.getText());
    }
    public void showTotalPrice(ActionEvent actionEvent) {
        //TODO
    }
    public void viewProduct(ActionEvent actionEvent) {
        senderReceiver.sendMessage(productId.getText());
    }
    public void purchase(ActionEvent actionEvent) {
        //TODO
    }
    public void start() {
        senderReceiver.changeMenu(15);
        senderReceiver.sendMessage("14-0");
        String receipt = senderReceiver.getMessage();
        products = receipt.split("---");
        ArrayList<String> strings = new ArrayList<>();
        for (String product : products) strings.add(product.split("===")[0]);
        reset(strings);
    }

    private void reset(ArrayList<String> strings) {
        productList.getItems().clear();
        productList.getItems().clear();
        productList.getItems().addAll(strings);

    }

    public void initialize(){
        productList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
    
}
