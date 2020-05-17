package model.product;

import controller.ProgramManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Category implements Comparable<Category> {
    private static int nextId = 0;
    private int id;
    private String name;
    private ArrayList<SubCategory> subCategories;
    private ArrayList<String> additionalAttributes;

    public Category(String name) {
        this.name = name;
        this.id = nextId;
        nextId++;
        subCategories = new ArrayList<>();
        additionalAttributes = new ArrayList<>();
    }

    private static int field = 1;
    private static ArrayList<Category> categoryArrayList = new ArrayList<>();

    public int compareTo(Category category) {
        return -(category.name.compareTo(this.name));
    }

    public ArrayList<Category> sortCategory(int fieldSort) {
        /*
        How to use this method :
        fieldSort = 1 for name sort
         */
        field = fieldSort;
        Collection<Category> values = ProgramManager.getProgramManagerInstance().getAllCategories();
        ArrayList<Category> listOfValues = new ArrayList<>(values);
        for (int i = 0; i < listOfValues.size(); i++) {
            categoryArrayList.add(listOfValues.get(i));
        }
        Collections.sort(categoryArrayList);
        return categoryArrayList;
    }

    public void addField(String field) {
        if (!isExistThisAttribute(field)) {
            additionalAttributes.add(field);
        } else {
            //System.out.println("this attribute already exist");
            // TODO
        }
    }

    private boolean isExistThisAttribute(String field) {
        for (String additionalAttribute : additionalAttributes) {
            if (additionalAttribute.equalsIgnoreCase(field)) {
                return true;
            }
        }
        return false;
    }

    private boolean isExistThisSubCategories(SubCategory subCategory) {
        for (SubCategory category : subCategories) {
            if (category.getName().equalsIgnoreCase(subCategory.getName())) {
                return true;
            }
        }
        return false;
    }


    public void removeField(String field) {
        if (isExistThisAttribute(field)) {
            additionalAttributes.remove(field);
        } else {
            //System.out.println("this attribute not exist");
            // TODO
        }
    }

    public void addSubcategory(SubCategory subCategory) {
        if (!isExistThisSubCategories(subCategory)) {
            subCategories.add(subCategory);
        } else {
            //System.out.println("this subCategory already exist");
            // TODO
        }
    }

    public void removeSubcategory(SubCategory subCategory) {
        if (isExistThisSubCategories(subCategory)) {
            subCategories.remove(subCategory);
        } else {
            //System.out.println("this subCategory not exist");
            // TODO
        }
    }


}
