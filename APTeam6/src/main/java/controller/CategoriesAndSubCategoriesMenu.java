package controller;

import javafx.stage.Stage;
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
        try {
            view.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateCategoriesArrayList();
        String command = null;
        state = 0;
        while (true) {
            switch (state) {
                case 0:
                    System.out.println("a");
                    view.showCategoriesList(allCategoriesArrayList);
                    System.out.println("b");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    command = view.getInputCommandManagerCategory();
                    System.out.println("c");
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
                    view.showSubCategoriesList(allSubCategoriesArrayList);
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
                    view.showProductsList(allProductsArrayList);
                    command = view.getInputCommandManagerProduct();
                    if (command.matches("remove \\d+")) {
                        String[] splitCommand = command.split("\\s");
                        remove(Integer.parseInt(splitCommand[1]));
                    }
                    else if (command.matches("open \\d+")) {
                        String[] splitCommand = command.split("\\s");
                        open(Integer.parseInt(splitCommand[1]));
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

    public void startAsSeller() {
        String command = null;
        state = 0;
        while (true) {
            switch (state) {
                case 0:
                    view.showCategoriesList(allCategoriesArrayList);
                    command = view.getInputCommandSellerCategory();
                    if (command.matches("open \\d+")) {
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
                    view.showSubCategoriesList(allSubCategoriesArrayList);
                    command = view.getInputCommandSellerSubCategory();
                    if (command.matches("addTo \\d+")) {
                        String[] splitCommand = command.split("\\s");
                        addProduct(Integer.parseInt(splitCommand[1]));
                    }
                    else if (command.equals("back")) {
                        state = 0;
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
                    view.showCategoriesList(allCategoriesArrayList);
                    command = view.getInputCommandBuyer();
                    if (command.matches("open \\d+")) {
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
                    view.showSubCategoriesList(allSubCategoriesArrayList);
                    command = view.getInputCommandBuyer();
                    if (command.matches("open \\d+")) {
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
                    view.showProductsList(allProductsArrayList);
                    command = view.getInputCommandBuyer();
                    if (command.matches("open \\d+")) {
                        String[] splitCommand = command.split("\\s");
                        open(Integer.parseInt(splitCommand[1]));
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
        if (state == 0) {
            Category category = allCategoriesArrayList.get(index);
            category.setName(newName);
            updateCategoriesArrayList();
        }
        else if (state == 1) {
            SubCategory subCategory = allSubCategoriesArrayList.get(index);
            subCategory.setName(newName);
            updateSubCategoriesArrayList();
        }
    }

    private void add(String name) {
        if (state == 0) {
            if (ProgramManager.getProgramManagerInstance().getCategoryByName(name) == null) {
                ProgramManager.getProgramManagerInstance().addCategory(new Category(name));
                updateCategoriesArrayList();
            }
            else
                view.giveOutPutError("Repeated name");
        }
        else if (state == 1) {
            if (currentCategory.getSubCategoryByName(name) == null) {
                currentCategory.addSubcategory(new SubCategory(name));
                updateSubCategoriesArrayList();
            }
            else
                view.giveOutPutError("Repeated name");
        }
    }

    private void remove(int index) {
        if (state == 0) {
            Category category = allCategoriesArrayList.get(index);
            ProgramManager.getProgramManagerInstance().removeCategory(category);
            updateCategoriesArrayList();
        }
        if (state == 1) {
            SubCategory subCategory = allSubCategoriesArrayList.get(index);
            currentCategory.removeSubcategory(subCategory);
            updateSubCategoriesArrayList();
        }
        if (state == 2) {
            Product product = allProductsArrayList.get(index);
            currentSubCategory.removeProduct(product.getId());
            updateProductsArrayList();
        }
    }

    private void open(int index) {
        if (state == 0) {
            currentCategory = allCategoriesArrayList.get(index);
            state = 1;
        }
        else if (state == 1) {
            currentSubCategory = allSubCategoriesArrayList.get(index);
            state = 2;
        }
        else if (state == 2) {
            Product product = allProductsArrayList.get(index);
            SingleProductScreen.getInstance().start(product);
        }
    }

    private void addProduct(int index) {
        SubCategory subCategory = allSubCategoriesArrayList.get(index);
        //TODO: add a new menu called CreateNewProductMenu or maybe SellerProductsMenu
    }

    //TODO: check for index out of bound in all methods
}
