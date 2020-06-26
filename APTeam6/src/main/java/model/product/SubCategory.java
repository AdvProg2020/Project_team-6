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

    public void setName(String name) {
        this.name = name;
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

    public boolean addField(String field) {
        if (!isExistThisAttribute(field)) {
            additionalAttributes.add(field);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeField(String field) {
        if (isExistThisAttribute(field)) {
            additionalAttributes.remove(field);
            return true;
        } else {
            return false;
        }
    }

    public boolean addProduct(int productId) {
        if (!isExistThisProduct(productId)) {
            productIds.add(productId);
            return true;
        } else {
            return false;
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

    public boolean removeProduct(int productId) {
        if (isExistThisProduct(productId)) {
            productIds.remove((Integer) productId);
            return true;
        } else {
            return false;
        }
    }
}
