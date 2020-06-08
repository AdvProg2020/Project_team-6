package controller.managerPanels;

import controller.LoginMenu;
import view.LoginMenuView;
import view.ManageCategoriesView;

public class ManageCategories {
    private static ManageCategories manageCategoriesInstance = null;

    public static ManageCategories getLoginMenuInstance() {
        if (manageCategoriesInstance == null)
            manageCategoriesInstance = new ManageCategories();
        return manageCategoriesInstance;
    }
    ManageCategoriesView manageCategoriesView;
    public void start() {
        manageCategoriesView = new ManageCategoriesView();
        String command = null;
        while (true) {
            command = manageCategoriesView.getInputCommand();
            if (command.matches("login \\S+ \\S+")) {
                String[] splitCommand = command.split("\\s");
                //login(splitCommand[1], splitCommand[2]);
            }
            else if (command.matches("create account (buyer|seller|manager) \\S+")) {
                String[] splitCommand = command.split("\\s");
                if (splitCommand[1].equals("manager"))
                    System.out.println("You can't make managers here. get away");
                //else
                    //register(splitCommand[3], splitCommand[2]);
            }
            else if (command.equals("logout")) {
                //logout();
            }
            else if (command.equals("back")) {
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to LoginMenu by view");
            }
        }
    }
}
