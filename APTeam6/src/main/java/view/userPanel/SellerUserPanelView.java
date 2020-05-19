package view.userPanel;

import controller.ProgramManager;
import model.account.Seller;
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
        ArrayList<Integer> ids = seller.getSellLogIds();

    }
}
