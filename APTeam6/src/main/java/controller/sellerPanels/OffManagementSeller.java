package controller.sellerPanels;

import controller.Parent;
import controller.ProgramManager;
import model.product.Off;
import model.requests.OffRequest;
import model.requests.Request;
import view.userPanel.OffManagementSellerView;

public class OffManagementSeller implements Parent {
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
                OffRequest offRequest = new OffRequest(ProgramManager.getProgramManagerInstance().getOffById(Integer.parseInt(command.split("\\s")[1])), (byte) 1,command.split("\\s")[2]);
                ProgramManager.getProgramManagerInstance().addRequestToList(offRequest);
            }
            else if(command.equalsIgnoreCase("add off")){
                OffRequest offRequest = new OffRequest(ProgramManager.getProgramManagerInstance().getOffById(Integer.parseInt(command.split("\\s")[1])),(byte)0,null);
                ProgramManager.getProgramManagerInstance().addRequestToList(offRequest);
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
