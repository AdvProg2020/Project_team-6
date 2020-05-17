package controller.sellerPanels;

import controller.PersonalInfoMenu;
import view.userPanel.SellerUserPanelView;

public class SellerUserPanel {
    private static SellerUserPanel sellerUserPanelInstance;
    public static SellerUserPanel getSellerUserPanelInstance(){
        if (sellerUserPanelInstance == null)
            sellerUserPanelInstance = new SellerUserPanel();
        return sellerUserPanelInstance;
    }
    //////////////////////////////////////////
    SellerUserPanelView view;

    public void start() {
        view = new SellerUserPanelView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.equalsIgnoreCase("view personal info")) {
                PersonalInfoMenu.getPersonalInfoMenuInstance().start();
            }
            else {
                throw new RuntimeException("Unknown command was passed to SellerUserPanel by view");
            }
        }
    }
}
