package model.product;

import java.util.ArrayList;
import java.util.HashMap;

public class Category {
    private String name;
    private HashMap<String, SubCategory> subCategories;
    private ArrayList<String> additionalAttributes;

    public Category(String name){
        this.name = name;
        subCategories = new HashMap<>();
        additionalAttributes = new ArrayList<>();
    }

    public void addField(String field){
        additionalAttributes.add(field);
    }

    public void removeField(String field){
        additionalAttributes.remove(field);
    }

    public void addSubcategory(SubCategory subCategory){
        subCategories.put(subCategory.getName(), subCategory);
    }

    public void removeSubcategory(SubCategory subCategory){
        subCategories.remove(subCategory);
    }

    public String getName(){
        return name;
    }

    public SubCategory getSubCategoryByName(String subName){
        return subCategories.get(subName);
    }
}
