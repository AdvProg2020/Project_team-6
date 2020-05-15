package model.product;

import java.util.ArrayList;

public class SubCategory {
    private String name;
    private ArrayList<Product> products;
    private ArrayList<String> additionalAttributes;

    public SubCategory(String name){
        this.name = name;
        products = new ArrayList<>();
        additionalAttributes = new ArrayList<>();
    }

    public void addField(String field){
        additionalAttributes.add(field);
    }

    public void removeField(String field){
        additionalAttributes.remove(field);
    }

    public void addSubcategory(Product product){
        products.add(product);
    }

    public void removeSubcategory(Product product){
        products.remove(product);
    }
}
