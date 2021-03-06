package client.view.userPanel;

import server.controller.ProgramManager;
import server.model.account.Seller;
import server.model.logs.SellLog;
import client.view.old.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class SellerUserPanelView {
    public SellerUserPanelView() {
        System.out.println("=== Seller main screen");
    }

    private final HashSet<String> returningCommand = new HashSet<>(Arrays.asList("client.view personal info", "login menu", "client.view company information", "client.view sales history", "manage products", "client.view offs"));

    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.equals("help")) {
                showHelp();
            }
            else if (returningCommand.contains(command)){
                return command;
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }

    private void showHelp() {
        System.out.println("List of commands:");
        for (String comm : returningCommand) {
            System.out.println("\t" + comm);
        }
    }

    public void giveOutput(String message) {
        System.out.println(message);
    }

    public void viewCompany(){
        /*
        Seller seller = (Seller) ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUsers();
        System.out.println("Company name: " + seller.getCompanyName());

         */
    }

    public void viewSellLogs(){
        /*Seller seller = (Seller) ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUsers();
        ArrayList<Integer> logIds = seller.getSellLogIds();
        ProgramManager programManager = ProgramManager.getProgramManagerInstance();
        System.out.println("List of all logs:");
        for (Integer logId : logIds) {
            SellLog sellLog = (SellLog) programManager.getLogByLogId(logId);
            ArrayList<Integer> productIds = sellLog.getSoldProductIds();
            System.out.print("\tlog" + logId + ": ");
            for (int i = 0; i < productIds.size(); i++) {
                Integer productId = productIds.get(i);
                if (i != 0)
                    System.out.print(", ");
                System.out.print(programManager.getProductById(productId).getName());
            }
            System.out.println();
        }

         */
    }

    public void viewBalance(){
        /*
        int totalMoney = 0;
        ProgramManager programManager = ProgramManager.getProgramManagerInstance();
        ArrayList<Integer> logIds = ((Seller)programManager.getCurrentlyLoggedInUsers()).getSellLogIds();
        for (Integer logId : logIds) {
            int price = ((SellLog)programManager.getLogByLogId(logId)).getReceivedAmount();
            totalMoney += price;
            System.out.print("\tlog" + logId + ": " + price);
        }
        System.out.print("Total money: " + totalMoney);

         */
    }
}
