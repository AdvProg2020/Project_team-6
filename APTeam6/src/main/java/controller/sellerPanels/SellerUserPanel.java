package controller.sellerPanels;

import controller.UserPanel;
import view.userPanel.BuyerUserPanelView;
import view.userPanel.SellerUserPanelView;

public class SellerUserPanel extends UserPanel {
    private static SellerUserPanel sellerUserPanelInstance;
    public static SellerUserPanel getSellerUserPanelInstance(){
        if (sellerUserPanelInstance == null)
            sellerUserPanelInstance = new SellerUserPanel();
        return sellerUserPanelInstance;
    }
    //////////////////////////////////////////
    SellerUserPanelView view;

    @Override
    public void start() {
        view = new SellerUserPanelView();
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
