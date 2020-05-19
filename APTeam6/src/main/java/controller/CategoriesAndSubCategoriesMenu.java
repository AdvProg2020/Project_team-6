package controller;

public class CategoriesAndSubCategoriesMenu {
    private static CategoriesAndSubCategoriesMenu instance;
    public static CategoriesAndSubCategoriesMenu getInstance(){
        if (instance == null)
            instance = new CategoriesAndSubCategoriesMenu();
        return instance;
    }
    ///////////////////////////////

    public void startAsManager(){

    }

    public void startAsBuyer(){

    }
}
