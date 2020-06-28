package controller.sellerPanels;

import controller.Parent;
import view.SellerProductsMenuView;

public class SellerProductsMenu implements Parent {
    private static SellerProductsMenu instance;
    public static SellerProductsMenu getInstance(){
        if (instance == null)
            instance = new SellerProductsMenu();
        return instance;
    }
    ////////////////////////////////
    private SellerProductsMenuView view;

    public void start(){
        view = new SellerProductsMenuView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.equals("back")) {
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to LoginMenu by view");
            }
        }
        //TODO: make this page
    }
}
