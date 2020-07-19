package server.controller;

import javafx.stage.Stage;
import server.Server;
import server.model.product.Product;
import server.model.product.Score;
import client.view.old.SingleProductScreenView;

import java.io.IOException;
import java.util.ArrayList;

public class SingleProductScreen implements Parent{
    Product product;
    public SingleProductScreen(Product product){
        this.product = product;
    }
    private Server server = null;
    @Override
    public void start(Server server) throws IOException {
        this.server = server;
        sendMessage("start");
    }

    private void sendMessage(String message) throws IOException {
        server.sendMessage("02-" + message);
    }

    public void start(Product product) throws Exception {
        /*
        view = new SingleProductScreenView(product.getId());
        view.start(new Stage());
        ArrayList<Score> scores = product.getScores();
        double averageScore = 0.0;
        for (Score score : scores) {
            averageScore += score.getScore();
        }
        averageScore /= scores.size();
        view.printProductDetails(product.getName(), product.getSubCategoryName(), product.getCategoryName(), product.getDescription(), product.getCreationDate().toString(), product.getVisitCount(), averageScore);
        product.addVisitCount();
        String command = null;
        while (true) {
            //command = client.view.getInputCommand();
            if(command.matches("add to buy basket \\w+")) {
//                addToBuyBasket(ProgramManager.getProgramManagerInstance().getProductById(Integer.parseInt(command.split("\\s")[4])));
            }
            else if(command.matches("Compare \\w+ \\w+")){
                compare(ProgramManager.getProgramManagerInstance().getProductById(Integer.parseInt(command.split("\\s")[1])),ProgramManager.getProgramManagerInstance().getProductById(Integer.parseInt(command.split("\\s")[2])));
            }
            else if (command.equals("back")){
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to ManageUsersMenu by client.view");
            }
        }
         */
        this.server = server;
        // Name---CategoryName---SubCategoryName---description
        sendMessage(product.getName() + "---" + product.getCategoryName() + "---" + product.getSubCategoryName() + "---" + product.getDescription());
    }
    public void addToBuyBasket(Product product){



    }

}
