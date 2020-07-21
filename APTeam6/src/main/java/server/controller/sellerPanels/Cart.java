package server.controller.sellerPanels;

import server.Server;
import server.controller.Parent;

import java.io.IOException;

public class Cart implements Parent {
    private Server server = null;
    @Override
    public void start(Server server) throws IOException {
        this.server = server;
        sendMessage("start");
    }

    private void sendMessage(String message) throws IOException {
        server.sendMessage("14-" + message);
    }
    public void showProducts(){

    }
    public void viewProduct(int productId){

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
