package model.requests;

import controller.ProgramManager;
import model.product.Product;

public class ProductRequest implements Request{
    private Product product;
    private byte action;
    private String newValue;
    // 0 - create
    // 1 - change name
    // 2 - change description
    // 3 - delete

    /**
     * @param product the product
     * @param action the action to do.<br/>0 - create<br/>1 - change name<br/>2 - change description<br/>3 - delete<br/>
     * @param newValue new text for change. null otherwise
     */
    public ProductRequest(Product product, byte action, String newValue) {
        this.product = product;
        this.action = action;
        this.newValue = newValue;
    }

    @Override
    public void accept() {
        if (action == 0){
            ProgramManager.getProgramManagerInstance().addProductToList(product);
        }
        else if (action == 1 || action == 2){
            product.changeField(action, newValue);
        }
        else if (action == 3){
            ProgramManager.getProgramManagerInstance().removeProduct(product);
        }
        ProgramManager.getProgramManagerInstance().removeRequest(this);
    }

    @Override
    public void decline() {
        ProgramManager.getProgramManagerInstance().removeRequest(this);
    }

    @Override
    public void showDetails() {
        System.out.println("the product's name :" + product.getName());
        System.out.println("the product's Id :" + product.getId());
        System.out.println("the product's description :" + product.getDescription());
        System.out.println("the product's categoryName :" + product.getCategoryName());
    }

}
