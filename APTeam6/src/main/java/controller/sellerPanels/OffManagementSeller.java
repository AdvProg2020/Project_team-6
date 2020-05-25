package controller.sellerPanels;

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

            }
            else if (command.matches("edit \\.+")) {

            }
            else if(command.equalsIgnoreCase("add off")){
                //TODO
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
