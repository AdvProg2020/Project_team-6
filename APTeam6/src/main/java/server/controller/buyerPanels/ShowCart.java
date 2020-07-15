package server.controller.buyerPanels;

import server.Server;
import server.controller.Parent;
import client.view.userPanel.ShowCartView;

import java.io.IOException;

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

    private Server server = null;

    @Override
    public void start(Server server) throws IOException {
        this.server = server;
        sendMessage("start");
    }

    private void sendMessage(String message) throws IOException {
        server.sendMessage("02-" + message);
    }

}
