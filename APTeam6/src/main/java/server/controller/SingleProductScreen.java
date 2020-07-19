package server.controller;

import javafx.stage.Stage;
import server.Server;
import server.model.product.Product;
import server.model.product.Score;
import client.view.old.SingleProductScreenView;

import java.io.IOException;
import java.util.ArrayList;

public class SingleProductScreen implements Parent{
    Product product;
    public SingleProductScreen(Product product){
        this.product = product;
    }
    private Server server = null;
    @Override
    public void start(Server server) throws IOException {
        this.server = server;
        sendMessage("start");
    }

    private void sendMessage(String message) throws IOException {
        server.sendMessage("02-" + message);
    }

    public void start(Product product) throws Exception {
    }
}
