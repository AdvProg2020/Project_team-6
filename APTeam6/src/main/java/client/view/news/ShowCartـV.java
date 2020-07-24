package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.*;


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
        senderReceiver.sendMessage("14-2" + productId.getText());
        senderReceiver.getMessage();
    }
    public void decrease(ActionEvent actionEvent) {
        senderReceiver.sendMessage("14-3" +productId.getText());
        senderReceiver.getMessage();
    }
    public void showTotalPrice(ActionEvent actionEvent) {
        senderReceiver.sendMessage("14-5");
        senderReceiver.getMessage();
        int totalPrice = Integer.parseInt(senderReceiver.getMessage().substring(4));
        new Alert(Alert.AlertType.INFORMATION,"the totalPrice is" + totalPrice).showAndWait();

    }
    public void viewProduct(ActionEvent actionEvent) {
        senderReceiver.sendMessage("14-1" + productId.getText());
    }
    public void purchase(ActionEvent actionEvent) {
        senderReceiver.sendMessage("14-4");
        senderReceiver.getMessage();
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
