package controller;

import model.product.Category;
import model.product.SubCategory;
import view.CategoriesAndSubCategoriesMenuView;


public class CategoriesAndSubCategoriesMenu {
    private static CategoriesAndSubCategoriesMenu instance;

    public static CategoriesAndSubCategoriesMenu getInstance() {
        if (instance == null)
            instance = new CategoriesAndSubCategoriesMenu();
        return instance;
    }
    ///////////////////////////////////////////
    private CategoriesAndSubCategoriesMenuView view;
    private byte state = 0;
    private Category currentCategory = null;
    private SubCategory currentSubCategory = null;

    public void startAsManager() {
        view = new CategoriesAndSubCategoriesMenuView();
        String command = null;
        state = 0;
        while (true) {
            switch (state) {
                case 0:
                    command = view.getInputCommandManagerCategory();
                    if (command.matches("edit \\S+ \\S+")) {
                        String[] splitCommand = command.split("\\s");
                        edit(splitCommand[1], splitCommand[2]);
                    }
                    else if (command.matches("add \\S+")) {
                        String[] splitCommand = command.split("\\s");
                        add(splitCommand[1]);
                    }
                    else if (command.matches("remove \\S+")) {
                        String[] splitCommand = command.split("\\s");
                        remove(splitCommand[1]);
                    }
                    else if (command.matches("open \\S+")) {
                        String[] splitCommand = command.split("\\s");
                        remove(splitCommand[1]);
                    }
                    else if (command.equals("back")) {
                        return;
                    }
                    else {
                        throw new RuntimeException("Unknown command was passed to ManageCategories by view");
                    }
                    break;
                case 1:
                    command = view.getInputCommandManagerCategory();
                    if (command.matches("edit \\S+ \\S+")) {
                        String[] splitCommand = command.split("\\s");
                        edit(splitCommand[1], splitCommand[2]);
                    }
                    else if (command.matches("add \\S+")) {
                        String[] splitCommand = command.split("\\s");
                        add(splitCommand[1]);
                    }
                    else if (command.matches("remove \\S+")) {
                        String[] splitCommand = command.split("\\s");
                        remove(splitCommand[1]);
                    }
                    else if (command.equals("back")) {
                        state = 0;
                    }
                    else {
                        throw new RuntimeException("Unknown command was passed to ManageCategories by view");
                    }
                    break;
                case 2:
                    command = view.getInputCommandManagerProduct();
                    if (command.matches("remove \\S+")) {
                        String[] splitCommand = command.split("\\s");
                        remove(splitCommand[1]);
                    }
                    else if (command.equals("back")) {
                        state = 1;
                    }
                    else {
                        throw new RuntimeException("Unknown command was passed to ManageCategories by view");
                    }
                    break;
            }
        }
    }

    public void startAsBuyer() {
        String command = null;
        state = 0;
        while (true) {
            switch (state) {
                case 0:
                    command = view.getInputCommandBuyerCategory();
                    break;
                case 1:
                    command = view.getInputCommandBuyerProduct();
                    break;
                case 2:
                    command = view.getInputCommandBuyerProduct();
                    break;
            }
            if (command.matches("open \\S+")){

            }
            else if (command.equals("back")) {
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to ManageCategories by view");
            }
        }

    }

    public void edit(String name, String newName) {
        ProgramManager.getProgramManagerInstance().editCategoryName(ProgramManager.getProgramManagerInstance().getCategoryByName(name), newName);
    }

    public void add(String name) {
        ProgramManager.getProgramManagerInstance().addCategory(ProgramManager.getProgramManagerInstance().getCategoryByName(name));
    }

    public void remove(String name) {
        ProgramManager.getProgramManagerInstance().removeCategory(ProgramManager.getProgramManagerInstance().getCategoryByName(name));
    }

}
