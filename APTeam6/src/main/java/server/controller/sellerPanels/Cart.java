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
        sendMessage("start");
    }

    private void sendMessage(String message) throws IOException {
        server.sendMessage("14-" + message);
    }
    public void showProducts(){
        HashMap<Product,Integer> buyBasket = server.getBuyBasket();
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
    public void viewProduct(int productId){
        Product tempProduct = ProgramManager.getProgramManagerInstance().getProductById(productId);
        //Name---CategoryName---subCategoryName---Price---Description
        String message = tempProduct.getName() + "---" + tempProduct.getCategoryName() + "---" + tempProduct.getSubCategoryName() + "---" + tempProduct.getPrice() + "---" + tempProduct.getDescription();
        try {
            sendMessage(message);
        } catch (IOException e) {
            System.err.println("error occurred");
        }
    }
    public void increase(int productId){

    }
    public void decrease(int productId){

    }
    public void purchase(){

    }
    public void showTotalPrice(){

    }


}
