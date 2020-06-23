package controller.managerPanels;

import controller.ProgramManager;

import view.ManageAllProductsView;
import view.PersonalInfoMenuView;

public class ManageAllProducts {
    private static ManageAllProducts manageAllProductsInstance = null;
    public static ManageAllProducts getLoginMenuInstance() {
        if (manageAllProductsInstance == null)
            manageAllProductsInstance = new ManageAllProducts();
        return manageAllProductsInstance;
    }
    ////////////////////////////////////////
    private ManageAllProductsView view;

    public void start() {
        view = new ManageAllProductsView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.matches("remove \\.+")) {
                remove(Integer.parseInt(command.split("\\s")[1]));
            } else if (command.equalsIgnoreCase("back")) {
                return;
            } else {
                throw new RuntimeException("unknown command was passed to manageAllProducts");
            }
        }
    }
    public void remove(int productId) {
        ProgramManager.getProgramManagerInstance().removeProduct(productId);
    }
}
