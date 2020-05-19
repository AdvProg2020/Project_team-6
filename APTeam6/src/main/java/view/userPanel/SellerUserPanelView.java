package view.userPanel;

import controller.ProgramManager;
import model.account.Seller;
import model.logs.SellLog;
import view.Input;

import java.util.ArrayList;

public class SellerUserPanelView {
    public SellerUserPanelView() {
        System.out.println("=== Seller main screen");
    }

    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.equals("help")) {
                showHelp();
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }

    private void showHelp() {
        System.out.println("List of commands:\n\t");
    }

    public void giveOutput(String message) {
        System.out.println(message);
    }

    public void viewCompany(){
        Seller seller = (Seller) ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUser();
        System.out.println("Company name: " + seller.getCompanyName());
    }

    public void viewSellLogs(){
        Seller seller = (Seller) ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUser();
        ArrayList<Integer> logIds = seller.getSellLogIds();
        ProgramManager programManager = ProgramManager.getProgramManagerInstance();
        for (Integer logId : logIds) {
            SellLog sellLog = (SellLog) programManager.getLogByLogId(logId);
            ArrayList<Integer> productIds = sellLog.getSoldProductIds();
            System.out.print("log" + logId + ": ");
            for (int i = 0; i < productIds.size(); i++) {
                Integer productId = productIds.get(i);
                if (i != 0)
                    System.out.print(", ");
                System.out.print(programManager.getProductById(productId).getName());
            }
            System.out.println();
        }
    }
}
