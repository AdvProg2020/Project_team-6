package server.controller.managerPanels;

import server.controller.*;
import client.view.userPanel.ManagerUserPanelView;

public class ManagerUserPanel implements Parent {
    private static ManagerUserPanel managerUserPanelInstance;

    public static ManagerUserPanel getManagerUserPanelInstance() {
        if (managerUserPanelInstance == null)
            managerUserPanelInstance = new ManagerUserPanel();
        return managerUserPanelInstance;
    }

    //////////////////////////////////////
    private ManagerUserPanelView view;

    public void start() {
        view = new ManagerUserPanelView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.equalsIgnoreCase("client.view personal info")) {
                //PersonalInfoMenu.getPersonalInfoMenuInstance().start(new Stage());
            }
            else if (command.equalsIgnoreCase("login menu")){
                LoginMenu.getLoginMenuInstance().start();
            }
            else if (command.equalsIgnoreCase("manage users")){
                ManageUsers.getManageUsersInstance().start();
            }
            else if (command.equalsIgnoreCase("manage all products")){
                ManageAllProducts.getLoginMenuInstance().start();
            }
            else if (command.equalsIgnoreCase("create discount code")){
                CreateDiscountCode.getInstance().start();
            }
            else if (command.equalsIgnoreCase("client.view discount codes")){
                ShowDiscountCode.getShowDiscountCodeInstance().start();
            }
            else if (command.equalsIgnoreCase("manage requests")){
                ManageRequests.getManageRequestsInstance().start();
            }
            else if (command.equalsIgnoreCase("manage categories")){
                //CategoriesAndSubCategoriesMenu.getInstance().start();
            }
            else {
                throw new RuntimeException("Unknown command was passed to ManagerUserPanel by client.view");
            }
            /////////////
            if (ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUserRole() != 3){
                return;
            }
        }
    }
}
