package server.controller.managerPanels;

import server.controller.Parent;
import server.controller.ProgramManager;

import client.view.old.ManageAllProductsView;

public class ManageAllProducts implements Parent {
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
