package server.model.product;

import server.controller.ProgramManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;
import java.util.Collections;

public class Category implements Comparable<Category> {
    private String name;
    private HashMap<String, SubCategory> subCategories;
    private ArrayList<String> additionalAttributes;

    /*
    public Category(String name) {
        this.name = name;
        subCategories = new HashMap<>();
        additionalAttributes = new ArrayList<>();
        ProgramManager.getProgramManagerInstance().addCategory(this);
    }
     */

    public Category(String name, ArrayList<String> additionalAttributes) {
        this.name = name;
        this.subCategories = new HashMap<>();
        this.additionalAttributes = additionalAttributes;
    }

    private static int field = 1;
    private static ArrayList<Category> categoryArrayList;

    public ArrayList<String> getAdditionalAttributes() {
        return additionalAttributes;
    }

    public int compareTo(Category category) {
        return -(category.name.compareTo(this.name));
    }

    public static ArrayList<Category> sortCategory(int fieldSort) {
        categoryArrayList = new ArrayList<>();
        /*
        How to use this method :
        fieldSort = 1 for name sort
         */
        field = fieldSort;
        Collection<Category> values = ProgramManager.getProgramManagerInstance().getAllCategories();
        ArrayList<Category> listOfValues = new ArrayList<>(values);
        categoryArrayList.addAll(listOfValues);
        Collections.sort(categoryArrayList);
        return categoryArrayList;
    }

    public ArrayList<SubCategory> getAllSubCategories(){
        Collection<SubCategory> values = subCategories.values();
        ArrayList<SubCategory> subCategoryArrayList = new ArrayList<>(values);
        return subCategoryArrayList;
    }

    public void addField(String field) {
        if (!doesThisAttributeExist(field)) {
            additionalAttributes.add(field);
        }
    }

    private boolean doesThisAttributeExist(String field) {
        for (String additionalAttribute : additionalAttributes) {
            if (additionalAttribute.equalsIgnoreCase(field)) {
                return true;
            }
        }
        return false;
    }

    private boolean doesThisSubCategoryExist(SubCategory subCategory) {
        return (subCategories.containsKey(subCategory.getName()));
    }

    public void removeField(String field) {
        if (doesThisAttributeExist(field)) {
            additionalAttributes.remove(field);
        }
    }

    public void addSubcategory(SubCategory subCategory) {
        if (!doesThisSubCategoryExist(subCategory)) {
            subCategories.put(subCategory.getName(), subCategory);
        }
    }

    public void removeSubcategory(SubCategory subCategory) {
        if (doesThisSubCategoryExist(subCategory)) {
            subCategories.remove(subCategory.getName());
        }
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SubCategory getSubCategoryByName(String subName){
        return subCategories.get(subName);
    }
}
