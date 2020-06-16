package model.product;

import java.util.ArrayList;

public class SubCategory {
    private String name;
    private ArrayList<Integer> productIds;
    private ArrayList<String> additionalAttributes;

    public SubCategory(String name) {
        this.name = name;
        productIds = new ArrayList<>();
        additionalAttributes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getAllProductIds() {
        return productIds;
    }

    private boolean isExistThisAttribute(String field) {
        for (String additionalAttribute : additionalAttributes) {
            if (additionalAttribute.equalsIgnoreCase(field)) {
                return true;
            }
        }
        return false;
    }

    public void addField(String field) {
        if (!isExistThisAttribute(field)) {
            additionalAttributes.add(field);
        } else {
            //System.out.println("this attribute already exist");
            // TODO
        }
    }

    public void removeField(String field) {
        if (isExistThisAttribute(field)) {
            additionalAttributes.remove(field);
        } else {
            //System.out.println("this attribute not exist");
            // TODO
        }
    }

    public void addProduct(int productId) {
        if (!isExistThisProduct(productId)) {
            productIds.add(productId);
        } else {
            //System.out.println("this product already exist in this sub category");
            // TODO
        }
    }

    private boolean isExistThisProduct(int productId) {
        for (Integer id : productIds) {
            if (id == productId) {
                return true;
            }
        }
        return false;
    }

    public void removeProduct(int productId) {
        if (isExistThisProduct(productId)) {
            productIds.remove((Integer) productId);
        } else {
            //System.out.println("this product not exist in this sub category");
            // TODO
        }
    }
}
