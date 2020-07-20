package server.controller.sellerPanels;

import server.Server;
import server.controller.Parent;
import client.view.old.SellerProductsMenuView;

import java.io.IOException;

public class SellerProductsMenu implements Parent {
    private Server server = null;
    @Override
    public void start(Server server) throws IOException {
        this.server = server;
        sendMessage("start");
    }

    private void sendMessage(String message) throws IOException {
        server.sendMessage("11-" + message);
    }

    public void viewProduct(String message) throws IOException {
        sendMessage("");
    }

    public void editProduct(String message) throws IOException {
        sendMessage("");
    }

    public void addProduct(String message) throws IOException {
        sendMessage("");
    }

    public void viewBuyersOfProduct(String message) throws IOException {
        sendMessage("");
    }

    public void removeProduct(String message) throws IOException {
        sendMessage("");
    }
    /*
    private static SellerProductsMenu instance;
    public static SellerProductsMenu getInstance(){
        if (instance == null)
            instance = new SellerProductsMenu();
        return instance;
    }
    ////////////////////////////////
    private SellerProductsMenuView view;

    public void start(){
        view = new SellerProductsMenuView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.equals("back")) {
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to LoginMenu by client.view");
            }
        }
    }
    */
}
