package server.controller.sellerPanels;

import server.Server;
import server.controller.Parent;
import server.controller.ProgramManager;
import server.model.account.Account;
import server.model.account.Buyer;
import server.model.logs.BuyLog;
import server.model.logs.SellLog;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class EWallet implements Parent {
    private Server server = null;

    @Override
    public void start(Server server) throws IOException {
        this.server = server;
        sendMessage("start");
    }
    private void sendMessage(String message) throws IOException {
        server.sendMessage("19-" + message);
    }
    public void takeCredit(String Data){
        String[] dataSplit = Data.split("---");
        Account tempAccount = ProgramManager.getProgramManagerInstance().getAccountByUsername(dataSplit[0]);
        if(tempAccount.getRole() == 1){
            ((Buyer)(tempAccount)).setCredit(((Buyer)(tempAccount)).getCredit() - Long.parseLong(dataSplit[1]));
        }
    }
    public void addToSellLog(String Data){
        //executedDiscount---logId---receivedAmount===ArrayList<Integer>productIds
        String[] dataSplit = Data.split("---");
        ArrayList<Integer> productIds = new ArrayList<>();
        String[] productIdSplit = Data.split("===");
        for (String s : productIdSplit) {
            productIds.add(Integer.parseInt(s));
        }

        new SellLog(Integer.parseInt(dataSplit[0]),Integer.parseInt(dataSplit[1]),Integer.parseInt(dataSplit[2]),productIds);
    }
    public void addToBuyLog(String Data){
        //executedDiscount---logId---paidAmount---buyerUserName===ArrayList<Integer>productIds
        String[] dataSplit = Data.split("---");
        String[] productIdSplit = Data.split("===");
        ArrayList<Integer> productIds = new ArrayList<>();
        for (String s : productIdSplit) {
            productIds.add(Integer.parseInt(s));
        }
        new BuyLog(Integer.parseInt(dataSplit[0]),Integer.parseInt(dataSplit[1]),Integer.parseInt(dataSplit[2]),dataSplit[3],productIds);
    }
}
