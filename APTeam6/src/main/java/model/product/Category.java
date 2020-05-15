package model.product;

import java.util.ArrayList;

public class Category {
    private static int nextId = 0;
    private int id;
    private String name;
    private ArrayList<SubCategory> subCategories;
    private ArrayList<String> additionalAttributes;

    public Category(String name){
        this.name = name;
        this.id = nextId;
        nextId++;
        subCategories = new ArrayList<>();
        additionalAttributes = new ArrayList<>();
    }

    public void addField(String field){
        additionalAttributes.add(field);
    }

    public void removeField(String field){
        additionalAttributes.remove(field);
    }

    public void addSubcategory(SubCategory subCategory){
        subCategories.add(subCategory);
    }

    public void removeSubcategory(SubCategory subCategory){
        subCategories.remove(subCategory);
    }
}
