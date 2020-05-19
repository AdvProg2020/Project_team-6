package controller.sellerPanels;

import controller.LoginMenu;
import controller.PersonalInfoMenu;
import controller.ProgramManager;
import view.userPanel.SellerUserPanelView;

public class SellerUserPanel {
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
            if (command.equalsIgnoreCase("view personal info")) {
                PersonalInfoMenu.getPersonalInfoMenuInstance().start();
            }
            else if (command.equalsIgnoreCase("login menu")){
                LoginMenu.getLoginMenuInstance().start();
            }
            else if (command.equalsIgnoreCase("view company information")){
                view.viewCompany();
            }
            else if (command.equalsIgnoreCase("view sales history")){
                view.viewSellLogs();
            }
            else {
                throw new RuntimeException("Unknown command was passed to SellerUserPanel by view");
            }
            /////////////
            if (ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUserRole() != 2){
                return;
            }
        }
    }
}
