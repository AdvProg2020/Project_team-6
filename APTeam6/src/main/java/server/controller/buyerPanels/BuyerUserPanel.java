package server.controller.buyerPanels;

import server.controller.LoginMenu;
import server.controller.Parent;
import server.controller.ProgramManager;
import client.view.userPanel.BuyerUserPanelView;

public class BuyerUserPanel implements Parent {
    private static BuyerUserPanel buyerUserPanelInstance;

    public static BuyerUserPanel getBuyerUserPanelInstance() {
        if (buyerUserPanelInstance == null)
            buyerUserPanelInstance = new BuyerUserPanel();
        return buyerUserPanelInstance;
    }

    ////////////////////////////////////////
    private BuyerUserPanelView view;

    public void start() {
        view = new BuyerUserPanelView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.equalsIgnoreCase("client.view personal info")) {
                //PersonalInfoMenu.getPersonalInfoMenuInstance().start(new Stage());
            }
            else if (command.equals("login menu")){
                LoginMenu.getLoginMenuInstance().start();
            }
            else if (command.equals("client.view cart")){
                ShowCart.getShowCartInstance().start();
            }
            else {
                throw new RuntimeException("Unknown command was passed to BuyerUserPanel by client.view");
            }
            /////////////
            if (ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUserRole() != 1){
                return;
            }
        }
    }
}
