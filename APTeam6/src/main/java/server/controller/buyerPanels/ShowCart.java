package server.controller.buyerPanels;

import com.gilecode.yagson.YaGson;
import server.Server;
import server.controller.Parent;
import client.view.userPanel.ShowCartView;
import server.model.product.Product;

import java.io.IOException;
import java.util.HashMap;

public class ShowCart implements Parent {
    /*
    private static ShowCart showCartInstance;
    public static ShowCart getShowCartInstance() {
        if (showCartInstance == null)
            showCartInstance = new ShowCart();
        return showCartInstance;
    }

    //////////////////////////
    private ShowCartView view;

    public void start(){
        view = new ShowCartView();
    }
     */


    private HashMap<Product, Integer> buyBasket = new HashMap<>();
    private Server server = null;


    public ShowCart(HashMap<Product, Integer> buyBasket) {
        this.buyBasket = buyBasket;
    }



    @Override
    public void start(Server server) throws IOException {
        this.server = server;
        StringBuilder message = new StringBuilder();
        for (Product product : buyBasket.keySet()) {
            message.append(product.getName());
            message.append("---");
            message.append(buyBasket.get(product).toString());
            message.append("===");
        }
        sendMessage(message.toString());
    }

    private void sendMessage(String message) throws IOException {
        server.sendMessage("05-" + message);
    }

}
