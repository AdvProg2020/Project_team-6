package controller;

import javafx.stage.Stage;
import model.product.Product;
import model.product.Score;
import view.ManageUsersView;
import view.SingleProductScreenView;

import java.util.ArrayList;

public class SingleProductScreen {
    private static SingleProductScreen instance;
    public static SingleProductScreen getInstance() {
        if (instance == null)
            instance = new SingleProductScreen();
        return instance;
    }
    ////////////////////////////////////////
    private SingleProductScreenView view;

    //TODO: Pass the product stuff to view
    //TODO: add to buyBasket for buyer
    //TODO: Compare products ToT

    public void start(Product product) throws Exception {
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
            //command = view.getInputCommand();
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
                throw new RuntimeException("Unknown command was passed to ManageUsersMenu by view");
            }
        }
    }
    public void compare(Product product1,Product product2){

    }
}
