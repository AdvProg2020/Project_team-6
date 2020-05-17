package model.product;

import java.util.ArrayList;

public class SubCategory {
    private String name;
    private ArrayList<Integer> productIds;
    private ArrayList<String> additionalAttributes;

    public SubCategory(String name){
        this.name = name;
        productIds = new ArrayList<>();
        additionalAttributes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addField(String field){
        additionalAttributes.add(field);
    }

    public void removeField(String field){
        additionalAttributes.remove(field);
    }

    public void addProduct(int productId){
        productIds.add(productId);
    }

    public void removeProduct(int productId){
        productIds.remove(productId);
    }
}
