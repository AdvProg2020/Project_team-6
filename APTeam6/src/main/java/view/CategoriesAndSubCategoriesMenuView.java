package view;

import controller.ProgramManager;
import model.product.Category;
import model.product.Product;
import model.product.SubCategory;

import java.util.ArrayList;

public class CategoriesAndSubCategoriesMenuView {
    public CategoriesAndSubCategoriesMenuView(){
        System.out.println("=== Categories menu");
        ArrayList<Category> categories = (ArrayList<Category>) ProgramManager.getProgramManagerInstance().getAllCategories();
        for (Category category : categories) {
            System.out.println("\t" + category.getName());
        }
    }

    /////////////////////////////////////////////////

    public String getInputCommandManagerCategory() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.matches("edit \\d+ \\S+") || command.matches("add \\d+") || command.matches("remove \\d+") || command.matches("open \\d+") || command.equals("back"))
                return command;
            else if (command.equals("help"))
                System.out.println("List of commands:\n\tadd [index]\n\tedit [index] [newName]\n\tremove [index]\n\topen [index]");
            else
                System.out.println("Invalid command");
        }
    }

    public String getInputCommandManagerProduct() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.matches("remove \\d+") || command.equals("back"))
                return command;
            else if (command.equals("help"))
                System.out.println("List of commands:\n\tremove [index]");
            else
                System.out.println("Invalid command");
        }
    }

    /////////////////////////////////////////////////

    //TODO: Seller view

    /////////////////////////////////////////////////

    public String getInputCommandBuyerCategory() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.matches("open \\d+") || command.equals("back"))
                return command;
            else if (command.equals("help"))
                System.out.println("List of commands:\n\tadd [index]\n\tedit [index] [newName]\n\tremove [index]\n\topen [index]");
            else
                System.out.println("Invalid command");
        }
    }

    public String getInputCommandBuyerProduct() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.matches("open \\d+") || command.equals("back"))
                return command;
            else if (command.equals("help"))
                System.out.println("List of commands:\n\topen [index]");
            else
                System.out.println("Invalid command");
        }
    }

    /////////////////////////////////////////////////

    public void showCategoriesList(ArrayList<Category> categories){
        System.out.println("List of Categories:");
        for (int i = 0, categoriesSize = categories.size(); i < categoriesSize; i++) {
            Category category = categories.get(i);
            System.out.println("\t" + i + ". " + category.getName());
        }
    }

    public void showSubCategoriesList(ArrayList<SubCategory> subCategories){
        System.out.println("List of SubCategories:");
        for (int i = 0, subCategoriesSize = subCategories.size(); i < subCategoriesSize; i++) {
            SubCategory subCategory = subCategories.get(i);
            System.out.println("\t" + i + ". " + subCategory.getName());
        }
    }

    public void showProductsList(ArrayList<Product> products){
        System.out.println("List of Products:");
        for (int i = 0, productsSize = products.size(); i < productsSize; i++) {
            Product product = products.get(i);
            System.out.println("\t" + i + ". " + product.getName());
        }
    }

    public void giveOutPut(String message){
        System.out.println(message);
    }
}
