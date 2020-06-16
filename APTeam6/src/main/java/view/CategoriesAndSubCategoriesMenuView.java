package view;

import controller.ProgramManager;
import model.product.Category;

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
            if (command.matches("remove \\d+") || command.equals("back"))
                return command;
            else if (command.equals("help"))
                System.out.println("List of commands:\n\tremove [index]");
            else
                System.out.println("Invalid command");
        }
    }

    /////////////////////////////////////////////////

    public void giveOutPut(String message){
        System.out.println(message);
    }
}
