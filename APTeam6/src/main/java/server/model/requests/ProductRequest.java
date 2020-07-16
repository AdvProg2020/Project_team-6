package server.model.requests;

import server.controller.ProgramManager;
import server.model.product.Product;

public class ProductRequest implements Request{
    private Product product;
    private String newValue;
    private byte action;
    // 0 - create
    // 1 - change name
    // 2 - change description
    // 3 - delete
    // 4 - change price


    @Override
    public String toString() {
        String s = "ProductRequest{" +
                "product=" + product +
                ", newValue='" + newValue + '\'' +
                ", action=" ;
        if(action==0){
            s+="create";
        }else if (action==1){
            s+="change name";
        }else if(action==2){
            s+="change description";
        }else if(action==3){
            s+="delete";
        }else if(action==4){
            s+="change price";
        }
        s+='}';
        return s;
    }

    /**
     * @param product the product
     * @param action the action to do.<br/>0 - create<br/>1 - change name<br/>2 - change description<br/>3 - delete<br/>4 - change price
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
        else if (action == 1 || action == 2 || action==4){
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

    public String showDetails(int a) {
        String s = "";
        s += "the product's name :" + product.getName() + "\n";
        s += "the product's Id :" + product.getId() + "\n";
        s += "the product's description :" + product.getDescription() + "\n";
        s += "the product's categoryName :" + product.getCategoryName();
        return s;
    }

}
