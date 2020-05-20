package controller.buyerPanels;

import view.userPanel.ShowCartView;

public class ShowCart {
    private static ShowCart showCartInstance;
    public static ShowCart getShowCartInstance() {
        if (showCartInstance == null)
            showCartInstance = new ShowCart();
        return showCartInstance;
    }

    //////////////////////////
    private ShowCartView view;

    public void start(){
        view = new ShowCartView();
    }
}
