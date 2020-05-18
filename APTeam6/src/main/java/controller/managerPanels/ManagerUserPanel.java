package controller.managerPanels;

import controller.LoginMenu;
import controller.PersonalInfoMenu;
import controller.ProgramManager;
import view.userPanel.ManagerUserPanelView;

public class ManagerUserPanel {
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
            if (command.equalsIgnoreCase("view personal info")) {
                PersonalInfoMenu.getPersonalInfoMenuInstance().start();
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
            else {
                throw new RuntimeException("Unknown command was passed to ManagerUserPanel by view");
            }
            /////////////
            if (ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUserRole() != 3){
                return;
            }
        }
    }
}
