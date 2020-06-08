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
    public void giveOutPut(String message){
        System.out.println(message);

    }
}
