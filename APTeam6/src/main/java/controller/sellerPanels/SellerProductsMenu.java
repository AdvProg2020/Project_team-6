package controller.sellerPanels;

import view.SellerProductsMenuView;

public class SellerProductsMenu {
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
        //TODO: make this page
    }
}
