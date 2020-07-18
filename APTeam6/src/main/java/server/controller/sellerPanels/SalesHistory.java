package server.controller.sellerPanels;

import server.Server;
import server.controller.Parent;
import server.controller.ProgramManager;
import server.model.account.Seller;
import server.model.logs.LogsInGeneral;
import server.model.logs.SellLog;

import java.io.IOException;
import java.util.HashMap;

public class SalesHistory implements Parent {

    private Server server = null;

    @Override
    public void start(Server server) throws IOException {
        this.server = server;
        if (server.getCurrentlyLoggedInUsers().getRole() == 2) {

            HashMap<Integer, LogsInGeneral> logsInGeneralHashMap = ProgramManager.getProgramManagerInstance().getLogsInGeneralHashMap();
            StringBuilder string = new StringBuilder();
            for (Integer sellLogId : Seller.sellLogIds) {

                if (logsInGeneralHashMap.get(sellLogId).getType() == 2) {
                    SellLog sellLog = (SellLog) logsInGeneralHashMap.get(sellLogId);

                    if (sellLog.getSellerUserName().equals(server.getCurrentlyLoggedInUsers().getUsername())) {
                        string.append(sellLog.toString());
                    }

                }

            }
            sendMessage(string.toString());

        } else {
            sendMessage("justSeller");
        }
        sendMessage("start");
    }

    private void sendMessage(String message) throws IOException {
        server.sendMessage("10-" + message);
    }

}
