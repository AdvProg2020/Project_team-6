package server.controller.sellerPanels;

import server.Server;
import server.controller.Parent;
import server.controller.ProgramManager;
import server.model.account.Account;
import server.model.product.DiscountCode;
import java.io.IOException;
import java.util.HashMap;

public class ReceiveBuyerInfo implements Parent {
    private Server server = null;
    @Override
    public void start(Server server) throws IOException {
        Account tempAccount = server.getCurrentlyLoggedInUsers();
        sendMessage("start");
    }
    private void sendMessage(String message) throws IOException {
        server.sendMessage("14-" + message);
    }
}
