package server.controller.sellerPanels;

import server.Server;
import server.controller.Parent;
import server.controller.ProgramManager;
import server.model.product.Product;

import java.io.IOException;
import java.util.HashMap;

public class Cart implements Parent {
    private Server server = null;
    @Override
    public void start(Server server) throws IOException {
        HashMap<Product,Integer> buyBasket = server.getBuyBasket();
        this.server = server;
        String message = "";
        for (Product product : buyBasket.keySet()) {
            message = message + product.getName() + "---";
        }
        try {
            sendMessage(message);
        } catch (IOException e) {
            System.err.println("error occurred");
        }
    }

    private void sendMessage(String message) throws IOException {
        server.sendMessage("14-" + message);
    }
    public void viewProduct(int productId){
        HashMap<Product,Integer> buyBasket = server.getBuyBasket();
        if(buyBasket.containsKey(productId)) {
            Product tempProduct = ProgramManager.getProgramManagerInstance().getProductById(productId);
            //Name---CategoryName---subCategoryName---Price---Description
            String message = tempProduct.getName() + "---" + tempProduct.getCategoryName() + "---" + tempProduct.getSubCategoryName() + "---" + tempProduct.getPrice() + "---" + tempProduct.getDescription();
            try {
                sendMessage(message);
            } catch (IOException e) {
                System.err.println("error occurred");
            }
        }
    }
    public void increase(int productId){
        HashMap<Product,Integer> buyBasket = server.getBuyBasket();
        Product tempProduct = ProgramManager.getProgramManagerInstance().getProductById(productId);
        int number = buyBasket.get(tempProduct) + 1;
        buyBasket.remove(tempProduct,buyBasket.get(tempProduct));
        buyBasket.put(tempProduct,number);
        try {
            sendMessage("increased");
        } catch (IOException e) {
            System.err.println("error occurred");
        }
    }
    public void decrease(int productId){
        HashMap<Product,Integer> buyBasket = server.getBuyBasket();
        Product tempProduct = ProgramManager.getProgramManagerInstance().getProductById(productId);
        int number = buyBasket.get(tempProduct) - 1;
        buyBasket.remove(tempProduct,buyBasket.get(tempProduct));
        buyBasket.put(tempProduct,number);
        if(number <= 0){
            buyBasket.remove(productId);
        }
        try {
            sendMessage("decreased");
        } catch (IOException e) {
            System.err.println("error occurred");
        }
    }
    public void purchase(){
        try {
            sendMessage("purchase");
            new ReceiveBuyerInfo().start(server);
        } catch (IOException e) {
            System.err.println("error occurred");
        }
    }
    public void showTotalPrice(){
        HashMap<Product,Integer> buyBasket = server.getBuyBasket();
        int totalPrice = 0;
        for (Product product : buyBasket.keySet()) {
            totalPrice += (int) (product.getPrice() * buyBasket.get(product));
        }
        try {
            sendMessage(String.valueOf(totalPrice));
        } catch (IOException e) {
            System.err.println("error occurred");
        }
    }
}
