package controller;

import model.product.Category;
import model.product.Product;
import model.product.SubCategory;
import view.CategoriesAndSubCategoriesMenuView;

import java.util.ArrayList;


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

    private ArrayList<Category> allCategoriesArrayList;
    private ArrayList<SubCategory> allSubCategoriesArrayList;
    private ArrayList<Product> allProductsArrayList;

    public void startAsManager() {
        view = new CategoriesAndSubCategoriesMenuView();
        String command = null;
        state = 0;
        while (true) {
            switch (state) {
                case 0:
                    command = view.getInputCommandManagerCategory();
                    if (command.matches("edit \\d+ \\S+")) {
                        String[] splitCommand = command.split("\\s");
                        edit(Integer.parseInt(splitCommand[1]), splitCommand[2]);
                    }
                    else if (command.matches("add \\S+")) {
                        String[] splitCommand = command.split("\\s");
                        add(splitCommand[1]);
                    }
                    else if (command.matches("remove \\d+")) {
                        String[] splitCommand = command.split("\\s");
                        remove(Integer.parseInt(splitCommand[1]));
                    }
                    else if (command.matches("open \\d+")) {
                        String[] splitCommand = command.split("\\s");
                        open(Integer.parseInt(splitCommand[1]));
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
                    if (command.matches("edit \\d+ \\S+")) {
                        String[] splitCommand = command.split("\\s");
                        edit(Integer.parseInt(splitCommand[1]), splitCommand[2]);
                    }
                    else if (command.matches("add \\S+")) {
                        String[] splitCommand = command.split("\\s");
                        add(splitCommand[1]);
                    }
                    else if (command.matches("remove \\d+")) {
                        String[] splitCommand = command.split("\\s");
                        remove(Integer.parseInt(splitCommand[1]));
                    }
                    else if (command.matches("open \\d+")) {
                        String[] splitCommand = command.split("\\s");
                        open(Integer.parseInt(splitCommand[1]));
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
                    if (command.matches("remove \\d+")) {
                        String[] splitCommand = command.split("\\s");
                        remove(Integer.parseInt(splitCommand[1]));
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
            if (command.matches("open \\d+")) {

            }
            else if (command.equals("back")) {
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to ManageCategories by view");
            }
        }

    }

    private void updateCategoriesArrayList() {
        allCategoriesArrayList = new ArrayList<>(ProgramManager.getProgramManagerInstance().getAllCategories());
        //TODO: maybe do some sorting?
    }

    private void updateSubCategoriesArrayList() {
        allSubCategoriesArrayList = new ArrayList<>(currentCategory.getAllSubCategories());
        //TODO: maybe do some sorting?
    }

    private void updateProductsArrayList() {
        ArrayList<Integer> productIds = currentSubCategory.getAllProductIds();
        allProductsArrayList = new ArrayList<>();
        ProgramManager programManager = ProgramManager.getProgramManagerInstance();
        for (Integer id : productIds) {
            allProductsArrayList.add(programManager.getProductById(id));
        }
        //TODO: maybe do some sorting?
    }

    ///////////////////////////////////
    private void edit(int index, String newName) {
        Category category = allCategoriesArrayList.get(index);
        ProgramManager.getProgramManagerInstance().editCategoryName(category, newName);
    }

    private void add(String name) {
        if (ProgramManager.getProgramManagerInstance().getCategoryByName(name) == null)
            ProgramManager.getProgramManagerInstance().addCategory(new Category(name));
        else
            view.giveOutPut("Repeated name");
    }

    private void remove(int index) {
        Category category = allCategoriesArrayList.get(index);
        ProgramManager.getProgramManagerInstance().removeCategory(category);
    }

    private void open(int index) {

    }
}
