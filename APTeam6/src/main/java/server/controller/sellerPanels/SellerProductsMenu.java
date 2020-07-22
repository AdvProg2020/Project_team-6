package server.controller.sellerPanels;

import server.Log;
import server.Server;
import server.controller.Parent;
import client.view.old.SellerProductsMenuView;
import server.controller.ProgramManager;
import server.model.logs.LogsInGeneral;
import server.model.product.DiscountCode;
import server.model.product.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SellerProductsMenu implements Parent {
    private Server server = null;
    @Override
    public void start(Server server) throws IOException {
        this.server = server;
        sendMessage("start");
    }

    private void sendMessage(String message) throws IOException {
        server.sendMessage("11-" + message);
    }

    public void viewProduct(String message) throws IOException {
        Product tempProduct = ProgramManager.getProgramManagerInstance().getProductById(Integer.parseInt(message));
        //Name---Description---Price---Category---SubCategory---Comments
        sendMessage(tempProduct.getName() + "---" + tempProduct.getDescription() + "---" + tempProduct.getPrice() + "---" + tempProduct.getCategoryName() + "---" + tempProduct.getSubCategoryName() + "---" + tempProduct.getComments());
    }

    public void editProduct(String message) throws IOException {
        HashMap<Integer,Product> allProducts = ProgramManager.getProgramManagerInstance().getAllProducts();
        if(allProducts.containsKey(Integer.parseInt(message.split("---")[0]))) {
            Product tempProduct = ProgramManager.getProgramManagerInstance().getProductById(Integer.parseInt(message.split("---")[0]));
            //TODO check data validation
            //name---categoryName---subCategoryName---price
            tempProduct.setName(message.split("---")[1]);
            tempProduct.setCategoryName(message.split("---")[2]);
            tempProduct.setSubCategoryName(message.split("---")[3]);
            tempProduct.setPrice(Long.parseLong(message.split("---")[4]));
        }else{
            sendMessage("incorrectId");
        }
        sendMessage("");
    }

    public void addProduct(String message) throws IOException {
        //name---categoryName---subCategoryName---Date---price
        String[] dataSplit = message.split("---");
        new Product(dataSplit[0],dataSplit[1],dataSplit[2],dataSplit[3],Long.parseLong(dataSplit[4]));
        sendMessage("created");
    }

    public void viewBuyersOfProduct(String message) throws IOException {
        for (Integer integer : ProgramManager.getProgramManagerInstance().getLogsInGeneralHashMap().keySet()) {

        }

        sendMessage("");

    }

    public void removeProduct(String message) throws IOException {
        ProgramManager.getProgramManagerInstance().removeProduct(Integer.parseInt(message));
        sendMessage("removed");
    }
    /*
    private static SellerProductsMenu instance;
    public static SellerProductsMenu getInstance(){
        if (instance == null)
            instance = new SellerProductsMenu();
        return instance;
    }
    ////////////////////////////////
    private SellerProductsMenuView view;

    public void start(){
        view = new SellerProductsMenuView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.equals("back")) {
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to LoginMenu by client.view");
            }
        }
    }
    */
}
