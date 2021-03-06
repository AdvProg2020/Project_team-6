package server.controller.sellerPanels;

import server.Log;
import server.Server;
import server.controller.Parent;
import client.view.old.SellerProductsMenuView;
import server.controller.ProgramManager;
import server.model.logs.BuyLog;
import server.model.logs.LogsInGeneral;
import server.model.product.Comment;
import server.model.product.DiscountCode;
import server.model.product.Product;
import server.model.requests.ProductRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
        //Name---Description---Price---Category---SubCategory---Comment1===com2===com3...
        String result = tempProduct.getName() + "---" + tempProduct.getDescription() + "---" + tempProduct.getPrice() +
                "---" + tempProduct.getCategoryName() + "---" + tempProduct.getSubCategoryName() + "---";

        for (Comment comment : tempProduct.getComments()) {
            result += comment.getTitle();
            result += ":";
            result += comment.getText();
            result += "===";
        }
        sendMessage(result);
    }

    public void editProduct(String message) throws IOException {
        HashMap<Integer, Product> allProducts = ProgramManager.getProgramManagerInstance().getAllProducts();
        if (allProducts.containsKey(Integer.parseInt(message.split("---")[0]))) {
            if (!message.split("---")[0].equals("") &&
                    !message.split("---")[1].equals("") &&
                    !message.split("---")[2].equals("") &&
                    !message.split("---")[3].equals("") &&
                    !message.split("---")[4].equals("")) {
                Product tempProduct = ProgramManager.getProgramManagerInstance().getProductById(Integer.parseInt(message.split("---")[0]));

                //name---categoryName---subCategoryName---price
                tempProduct.setName(message.split("---")[1]);
                tempProduct.setCategoryName(message.split("---")[2]);
                tempProduct.setSubCategoryName(message.split("---")[3]);
                tempProduct.setPrice(Long.parseLong(message.split("---")[4]));
            }
            else {
                sendMessage("incorrectId");
            }
        }
    }

    public void addProduct(String message) throws IOException {
        //name---categoryName---subCategoryName---price---sadas
        String[] dataSplit = message.split("---");
        if (!dataSplit[0].equals("") && !dataSplit[1].equals("") && !dataSplit[2].equals("") && !dataSplit[3].equals("")) {
            HashMap<String, String> addit = new HashMap<>();
            ArrayList<String> names = ProgramManager.getProgramManagerInstance().getCategoryByName(dataSplit[1]).getAdditionalAttributes();
            names.addAll(ProgramManager.getProgramManagerInstance().getCategoryByName(dataSplit[1]).getSubCategoryByName(dataSplit[2]).getAdditionalAttributes());
            for (int i = 0; i < names.size(); i++) {
                addit.put(names.get(i), dataSplit[4].split("@")[i]);
            }
            Product product = new Product(dataSplit[0], dataSplit[1], dataSplit[2], Long.parseLong(dataSplit[3]), addit, new HashMap<>());
            new ProductRequest(product, (byte) 0, null);
            sendMessage("created");
        }
        else {
            sendMessage("empty input");
        }
    }

    public void viewBuyersOfProduct(String message) throws IOException {
        ArrayList<String> buyersName = new ArrayList<>();

        for (LogsInGeneral log : ProgramManager.getProgramManagerInstance().getAllLogs()) {

            if (log.getType() == 1) {

                BuyLog buyLog = (BuyLog) log;
                for (Integer boughtProduct : buyLog.getBoughtProducts()) {

                    if (boughtProduct == Integer.parseInt(message)) {
                        buyersName.add(buyLog.getBuyerUserName());
                    }
                }
            }
        }
        String result = "";

        for (String s : buyersName) {
            result += s;
            result += "---";
        }

        sendMessage(result);
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
