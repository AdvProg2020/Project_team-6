package controller.managerPanels;

import controller.LoginMenu;
import model.product.Category;
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
            if (command.matches("edit \\.+")) {
                String[] splitCommand = command.split("\\s");
                edit((Category)splitCommand[1]);
            }
            else if (command.matches("add \\.+")) {
                String[] splitCommand = command.split("\\s");
                add((Category)splitCommand[1]);
            }
            else if (command.matches("remove \\.+")) {
                String[] splitCommand = command.split("\\s");
                remove((Category)splitCommand[1]);
            }
            else if (command.equals("back")) {
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to ManageCategories by view");
            }
        }
    }
    public void edit(Category category){

    }
    public void add(Category category){

    }
    public void remove(Category category){

    }
}
