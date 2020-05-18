package model.requests;

import model.product.Product;

public class ProductRequest implements Request{
    private Product newProduct;
    private Product oldProduct;
    private byte action;
    // 0 - create
    // 1 - change
    // 2 - delete

    /**
     * @param newProduct the new product. null in case of delete
     * @param oldProduct the old product. null in case of create
     * @param action the action to do.<br/>0 - create<br/>1 - change<br/>2 - delete
     */
    public ProductRequest(Product newProduct, Product oldProduct, byte action) {
        this.newProduct = newProduct;
        this.oldProduct = oldProduct;
        this.action = action;
    }

    @Override
    public void accept() {
        if (action == 0){

        }
    }

    @Override
    public void reject() {

    }
}
