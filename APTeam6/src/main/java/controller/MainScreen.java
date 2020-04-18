package controller;

import view.MainScreenView;

public class MainScreen {
    private static MainScreen mainScreenInstance = null;

    public static MainScreen getMainScreenInstance(){
        if (mainScreenInstance == null)
            mainScreenInstance = new MainScreen();
        return mainScreenInstance;
    }

    public void start(){
        MainScreenView view = new MainScreenView();
    }
}
