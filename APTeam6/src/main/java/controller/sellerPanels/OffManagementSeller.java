package controller.sellerPanels;

import controller.ProgramManager;
import model.product.Off;
import view.userPanel.OffManagementSellerView;

public class OffManagementSeller {
    private static OffManagementSeller instance;
    public static OffManagementSeller getInstance(){
        if (instance == null)
            instance = new OffManagementSeller();
        return instance;
    }
    ////////////////////////////
    private OffManagementSellerView view;

    public void start(){
        view = new OffManagementSellerView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.matches("view \\.+")) {
                ProgramManager.getProgramManagerInstance().showOff(ProgramManager.getProgramManagerInstance().getOffById(Integer.parseInt(command.split("\\s")[1])));
            }
            else if (command.matches("edit \\.+")) {
                //TODO
                //must send a request to manager fist
            }
            else if(command.equalsIgnoreCase("add off")){
                //TODO
                //must send a request to manager fist
            }
            else if (command.equals("back")) {
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to OffManagementSeller by view");
            }
        }
    }
}
