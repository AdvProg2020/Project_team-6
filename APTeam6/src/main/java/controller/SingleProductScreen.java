package controller;

import view.SingleProductScreenView;

public class SingleProductScreen {
    private static SingleProductScreen instance;
    public static SingleProductScreen getInstance() {
        if (instance == null)
            instance = new SingleProductScreen();
        return instance;
    }
    ////////////////////////////////////////
    private SingleProductScreenView view = new SingleProductScreenView();

    //TODO: Pass the product stuff to view
    //TODO: add to buyBasket for buyer
    //TODO: Compare products ToT 😭
}
