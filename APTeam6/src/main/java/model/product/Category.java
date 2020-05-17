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
        if(!isExistThisAttribute(field)) {
            additionalAttributes.add(field);
        }else{
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


    public void removeField(String field){
        if(isExistThisAttribute(field)) {
            additionalAttributes.remove(field);
        }else{
            //System.out.println("this attribute not exist");
            // TODO
        }
    }

    public void addSubcategory(SubCategory subCategory){
        if(!isExistThisSubCategories(subCategory)) {
            subCategories.add(subCategory);
        }else{
            //System.out.println("this subCategory already exist");
            // TODO
        }
    }

    public void removeSubcategory(SubCategory subCategory){
        if(isExistThisSubCategories(subCategory)) {
            subCategories.remove(subCategory);
        }else{
            //System.out.println("this subCategory not exist");
            // TODO
        }
    }
}
