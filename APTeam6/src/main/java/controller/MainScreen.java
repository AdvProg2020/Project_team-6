package controller;

import controller.buyerPanels.BuyerUserPanel;
import controller.managerPanels.ManagerUserPanel;
import controller.sellerPanels.SellerUserPanel;
import view.LoginMenuView;
import view.MainScreenView;

public class MainScreen {
    private static MainScreen mainScreenInstance = null;

    public static MainScreen getMainScreenInstance() {
        if (mainScreenInstance == null)
            mainScreenInstance = new MainScreen();
        return mainScreenInstance;
    }

    public void start() {
        MainScreenView view = new MainScreenView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.equalsIgnoreCase("login menu")) {
                LoginMenu.getLoginMenuInstance().start();
            }
            else if (command.equalsIgnoreCase("products")) {
                CategoriesAndSubCategoriesMenu.getInstance().startAsBuyer();
            }
            else if (command.equalsIgnoreCase("exit")){
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to MainScreen by view");
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
            }
        }
    }
}