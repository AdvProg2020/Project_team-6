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
        //TODO: Write here
    }
}
