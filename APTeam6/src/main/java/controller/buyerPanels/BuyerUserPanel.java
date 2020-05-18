package controller.buyerPanels;

import controller.PersonalInfoMenu;
import view.userPanel.BuyerUserPanelView;

public class BuyerUserPanel {
    private static BuyerUserPanel buyerUserPanelInstance;

    public static BuyerUserPanel getBuyerUserPanelInstance() {
        if (buyerUserPanelInstance == null)
            buyerUserPanelInstance = new BuyerUserPanel();
        return buyerUserPanelInstance;
    }

    ////////////////////////////////////////
    BuyerUserPanelView view;

    public void start() {
        view = new BuyerUserPanelView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.equalsIgnoreCase("view personal info")) {
                PersonalInfoMenu.getPersonalInfoMenuInstance().start();
            }
            else {
                throw new RuntimeException("Unknown command was passed to BuyerUserPanel by view");
            }
        }
    }
}
