package server.controller;

import server.Server;
import server.controller.buyerPanels.BuyerUserPanel;
import server.controller.managerPanels.ManagerUserPanel;
import server.controller.sellerPanels.SellerUserPanel;
import client.view.old.MainScreenView;

import java.io.IOException;


public class MainScreen implements Parent {

    private Server server = null;

    public void start(Server server) throws IOException {

        this.server = server;

        sendMessage("start");

        /*
        MainScreenView view = new MainScreenView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.equalsIgnoreCase("login menu")) {
                LoginMenu.getLoginMenuInstance().start();
            }
            else if (command.equalsIgnoreCase("products")) {
                //CategoriesAndSubCategoriesMenu.getInstance().start();
            }
            else if (command.equalsIgnoreCase("exit")){
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to MainScreen by client.view");
            }
            ////////////////////
            if (ProgramManager.getProgramManagerInstance().isAnyoneLoggedIn()){
                byte role = ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUser().getRole();
                if (role == 1)
                    BuyerUserPanel.getBuyerUserPanelInstance().start();
                else if (role == 2)
                    SellerUserPanel.getSellerUserPanelInstance().start();
                else if (role == 3)
                    ManagerUserPanel.getManagerUserPanelInstance().start();
            }*/
    }

    public void sendMessage(String message) throws IOException {
        this.server.sendMessage("00-" + message);
    }


}



