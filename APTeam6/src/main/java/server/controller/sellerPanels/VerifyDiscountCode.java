package server.controller.sellerPanels;

import server.Server;
import server.controller.Parent;
import server.controller.ProgramManager;
import server.model.product.DiscountCode;

import java.io.IOException;
import java.util.HashMap;

public class VerifyDiscountCode implements Parent {
    private Server server = null;
    @Override
    public void start(Server server) throws IOException {
        HashMap<String, DiscountCode> allDiscountCodes = ProgramManager.getProgramManagerInstance().getAllDiscountCodes();
        sendMessage("start");
    }
    private void sendMessage(String message) throws IOException {
        server.sendMessage("15-" + message);
    }
    public void verify(String data){
        DiscountCode tempDiscountCode = ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(data);
        if(tempDiscountCode != null){
            try {
                //verified---percentage
                sendMessage("verified" + "---" + tempDiscountCode.getPercentage());
            } catch (IOException e) {
                System.err.println("error occurred");
            }
        }
    }
}
