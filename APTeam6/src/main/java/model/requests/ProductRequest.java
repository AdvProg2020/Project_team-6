package model.requests;

import controller.ProgramManager;
import model.product.Product;

public class ProductRequest implements Request{
    private Product newProduct;
    private Product oldProduct;
    private byte action;
    private String newValue;
    // 0 - create
    // 1 - change name
    // 2 - change description
    // 3 - delete

    /**
     * @param newProduct the new product. null in case of delete
     * @param oldProduct the old product. null in case of create
     * @param action the action to do.<br/>0 - create<br/>1 - change name<br/>2 - change description<br/>3 - delete<br/>
     * @param newValue new text for change. null otherwise
     */
    public ProductRequest(Product newProduct, Product oldProduct, byte action, String newValue) {
        this.newProduct = newProduct;
        this.oldProduct = oldProduct;
        this.action = action;
        this.newValue = newValue;
    }

    @Override
    public void accept() {
        if (action == 0){
            //ProgramManager.getProgramManagerInstance().add
        }
    }

    @Override
    public void reject() {

    }
}
