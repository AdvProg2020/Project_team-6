package controller.managerPanels;

import controller.PersonalInfoMenu;
import view.userPanel.ManagerUserPanelView;

public class ManagerUserPanel {
    private static ManagerUserPanel managerUserPanelInstance;

    public static ManagerUserPanel getManagerUserPanelInstance() {
        if (managerUserPanelInstance == null)
            managerUserPanelInstance = new ManagerUserPanel();
        return managerUserPanelInstance;
    }

    //////////////////////////////////////
    ManagerUserPanelView view;

    public void start() {
        view = new ManagerUserPanelView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.equalsIgnoreCase("view personal info")) {
                PersonalInfoMenu.getPersonalInfoMenuInstance().start();
            }
            else {
                throw new RuntimeException("Unknown command was passed to ManagerUserPanel by view");
            }
        }
    }
}
