package controller;

import model.product.Product;
import model.product.Score;
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

    public void start(Product product){
        view = new SingleProductScreenView(product.getName());
        ArrayList<Score> scores = product.getScores();
        double averageScore = 0.0;
        for (Score score : scores) {
            averageScore += score.getScore();
        }
        averageScore /= scores.size();
        view.printProductDetails(product.getName(), product.getSubCategoryName(), product.getCategoryName(), product.getDescription(), product.getCreationDate().toString(), product.getVisitCount(), averageScore);
        product.addVisitCount();
    }
}
