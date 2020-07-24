package server.controller.buyerPanels;

import server.Server;
import server.controller.Parent;
import server.controller.ProgramManager;
import server.model.logs.BuyLog;
import server.model.logs.LogsInGeneral;
import server.model.product.Product;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

public class BuyHistory implements Parent {

    private Server server = null;

    @Override
    public void start(Server server) throws IOException {
        this.server = server;
        StringBuilder string = new StringBuilder();
        HashMap<Integer, LogsInGeneral> logsInGeneralHashMap = ProgramManager.getProgramManagerInstance().getLogsInGeneralHashMap();

        for (Integer integer : logsInGeneralHashMap.keySet()) {
            if (logsInGeneralHashMap.get(integer).getType()==1){

                BuyLog buyLog = (BuyLog) logsInGeneralHashMap.get(integer);
                if (buyLog.getBuyerUserName().equals(server.getCurrentlyLoggedInUsers().getUsername())){

                    //logId@paidAmount@boughtProduct@date@executedDiscount#...
                    //boughtProduct: product1---price1===product2---price2===p3...

                    string.append(buyLog.getLogId());
                    string.append("@");
                    string.append(buyLog.getPaidAmount());
                    string.append("@");

                    for (Integer boughtProduct : buyLog.getBoughtProducts()) {
                        string.append(ProgramManager.getProgramManagerInstance().getProductById(boughtProduct).getName());
                        string.append("---");
                        string.append(ProgramManager.getProgramManagerInstance().getProductById(boughtProduct).getPrice());
                        string.append("===");
                    }

                    string.append("@");
                    string.append(buyLog.getDate().toString());
                    string.append("@");
                    string.append(buyLog.getExecutedDiscount());
                    string.append("#");

                }
            }
        }
        sendMessage(string.toString());
    }

    private void sendMessage(String message) throws IOException {
        server.sendMessage("07-" + message);
    }
}
