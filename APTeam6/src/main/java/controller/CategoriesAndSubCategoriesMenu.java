package controller;

import view.CategoriesAndSubCategoriesMenuView;


public class CategoriesAndSubCategoriesMenu {
    CategoriesAndSubCategoriesMenuView categoriesAndSubCategoriesMenuView = new CategoriesAndSubCategoriesMenuView();
    private static CategoriesAndSubCategoriesMenu instance;
    public static CategoriesAndSubCategoriesMenu getInstance(){
        if (instance == null)
            instance = new CategoriesAndSubCategoriesMenu();
        return instance;
    }
    public void startAsManager(){
            String command = null;
            while (true) {
                command = categoriesAndSubCategoriesMenuView.getInputCommand();
                if (command.matches("edit \\.+")) {
                    String[] splitCommand = command.split("\\s");
                    edit(splitCommand[1],splitCommand[2]);
                }
                else if (command.matches("add \\.+")) {
                    String[] splitCommand = command.split("\\s");
                    add(splitCommand[1]);
                }
                else if (command.matches("remove \\.+")) {
                    String[] splitCommand = command.split("\\s");
                    remove(splitCommand[1]);
                }
                else if (command.equals("back")) {
                    return;
                }
                else {
                    throw new RuntimeException("Unknown command was passed to ManageCategories by view");
                }
            }
    }

    public void startAsBuyer(){
        String command = null;
        while (true) {
            command = categoriesAndSubCategoriesMenuView.getInputCommand();
            if (command.equals("show categories")) {
                categoriesAndSubCategoriesMenuView.showCategories();
            }
            else if (command.equals("back")) {
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to ManageCategories by view");
            }
        }

    }

    public void edit(String name,String newName){
        ProgramManager.getProgramManagerInstance().editCategoryName(ProgramManager.getProgramManagerInstance().getCategoryByName(name),newName);
    }
    public void add(String name){
        ProgramManager.getProgramManagerInstance().addCategory(ProgramManager.getProgramManagerInstance().getCategoryByName(name));
    }
    public void remove(String name){
        ProgramManager.getProgramManagerInstance().removeCategory(ProgramManager.getProgramManagerInstance().getCategoryByName(name));
    }

}
