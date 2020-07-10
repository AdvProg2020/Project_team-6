package server.controller.sellerPanels;

import server.Server;
import server.controller.LoginMenu;
import server.controller.Parent;
import server.controller.ProgramManager;
import client.view.userPanel.SellerUserPanelView;

import java.io.IOException;

public class SellerUserPanel implements Parent {
    /*
    private static SellerUserPanel sellerUserPanelInstance;
    public static SellerUserPanel getSellerUserPanelInstance(){
        if (sellerUserPanelInstance == null)
            sellerUserPanelInstance = new SellerUserPanel();
        return sellerUserPanelInstance;
    }
    //////////////////////////////////////////
    private SellerUserPanelView view;

    public void start() {
        view = new SellerUserPanelView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.equals("client.view personal info")) {
                //PersonalInfoMenu.getPersonalInfoMenuInstance().start(new Stage());
            }
            else if (command.equals("login menu")){
                LoginMenu.getLoginMenuInstance().start();
            }
            else if (command.equals("client.view company information")){
                view.viewCompany();
            }
            else if (command.equals("client.view sales history")){
                view.viewSellLogs();
            }
            else if (command.equals("manage products")){
                SellerProductsMenu.getInstance().start();
            }
            else if (command.equals("client.view offs")){
                OffManagementSeller.getInstance().start();
            }
            else if (command.equals("client.view balance")){
                view.viewBalance();
            }
            else {
                throw new RuntimeException("Unknown command was passed to SellerUserPanel by client.view");
            }
            /////////////
            if (ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUserRole() != 2){
                return;
            }
        }
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
