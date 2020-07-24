package server.controller.managerPanels;

import server.Server;
import server.controller.Parent;
import server.controller.ProgramManager;

import client.view.old.ManageAllProductsView;
import server.model.product.Comment;
import server.model.product.Product;

import java.io.IOException;

public class ManageAllProducts implements Parent {
    /*
    private static ManageAllProducts manageAllProductsInstance = null;
    public static ManageAllProducts getLoginMenuInstance() {
        if (manageAllProductsInstance == null)
            manageAllProductsInstance = new ManageAllProducts();
        return manageAllProductsInstance;
    }
    ////////////////////////////////////////
    private ManageAllProductsView view;

    public void start() {
        view = new ManageAllProductsView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.matches("remove \\.+")) {
                remove(Integer.parseInt(command.split("\\s")[1]));
            } else if (command.equalsIgnoreCase("back")) {
                return;
            } else {
                throw new RuntimeException("unknown command was passed to manageAllProducts");
            }
        }
    }
         */

    public void remove(int productId) {
        if(ProgramManager.getProgramManagerInstance().getProductById(productId) != null) {
            ProgramManager.getProgramManagerInstance().removeProduct(productId);
            try {
                sendMessage("removed");
            } catch (IOException e) {
                System.err.println("error occurred");
            }
        }else{
            try {
                sendMessage("the product does not exist");
            } catch (IOException e) {
                System.err.println("error occurred");
            }

        }
    }

    private Server server = null;

    @Override
    public void start(Server server) throws IOException {
        this.server = server;
        String result ="";
        for (Integer integer : ProgramManager.getProgramManagerInstance().getAllProducts().keySet()) {
            Product tempProduct = ProgramManager.getProgramManagerInstance().getAllProducts().get(integer);

            //Name---Description---Price---Category---SubCategory---Comment1===com2===com3...
            result = "@" + tempProduct.getName() + "---" + tempProduct.getDescription() + "---" + tempProduct.getPrice() +
                    "---" + tempProduct.getCategoryName() + "---" + tempProduct.getSubCategoryName() + "---";

            for (Comment comment : tempProduct.getComments()) {
                result += comment.getTitle();
                result += ":";
                result += comment.getText();
                result += "===";
            }
        }
        sendMessage(result);
    }

    private void sendMessage(String message) throws IOException {
        server.sendMessage("18-" + message);
    }

}
