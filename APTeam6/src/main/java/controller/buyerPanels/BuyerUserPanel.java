package controller.buyerPanels;

import controller.UserPanel;
import view.userPanel.BuyerUserPanelView;

public class BuyerUserPanel extends UserPanel {
    private static BuyerUserPanel buyerUserPanelInstance;
    public static BuyerUserPanel getBuyerUserPanelInstance(){
        if (buyerUserPanelInstance == null)
            buyerUserPanelInstance = new BuyerUserPanel();
        return buyerUserPanelInstance;
    }
    ////////////////////////////////////////
    BuyerUserPanelView view;

    @Override
    public void start() {
        view = new BuyerUserPanelView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.equalsIgnoreCase("")){

            }
            else {
                throw new RuntimeException("Unknown command was passed to BuyerUserPanel by view");
            }
        }
    }
}
