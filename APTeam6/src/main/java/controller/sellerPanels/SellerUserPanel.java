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
            if (command.equals("view personal info")) {
                PersonalInfoMenu.getPersonalInfoMenuInstance().start();
            }
            else if (command.equals("login menu")){
                LoginMenu.getLoginMenuInstance().start();
            }
            else if (command.equals("view company information")){
                view.viewCompany();
            }
            else if (command.equals("view sales history")){
                view.viewSellLogs();
            }
            else if (command.equals("manage products")){
                SellerProductsMenu.getInstance().start();
            }
            else if (command.equals("view offs")){
                OffManagementSeller.getInstance().start();
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
