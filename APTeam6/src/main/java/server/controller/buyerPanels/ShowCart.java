package server.controller.buyerPanels;

import server.controller.Parent;
import client.view.userPanel.ShowCartView;

public class ShowCart implements Parent {
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
