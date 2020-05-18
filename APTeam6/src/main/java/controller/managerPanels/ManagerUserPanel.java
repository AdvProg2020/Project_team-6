package controller.managerPanels;

import controller.ProgramManager;
import controller.UserPanel;
import controller.buyerPanels.BuyerUserPanel;
import view.userPanel.BuyerUserPanelView;
import view.userPanel.ManagerUserPanelView;

public class ManagerUserPanel extends UserPanel {
    private static ManagerUserPanel managerUserPanelInstance;
    public static ManagerUserPanel getManagerUserPanelInstance(){
        if (managerUserPanelInstance == null)
            managerUserPanelInstance = new ManagerUserPanel();
        return managerUserPanelInstance;
    }
    //////////////////////////////////////
    ManagerUserPanelView view;

    @Override
    public void start() {
        view = new ManagerUserPanelView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.equalsIgnoreCase("")){

            }
            else {
                throw new RuntimeException("Unknown command was passed to LoginMenu by view");
            }
        }
    }
}
