package server.model.product;

import server.controller.ProgramManager;
import server.model.account.Account;
import server.model.account.Buyer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class Product implements Comparable<Product>{
    static int nextId = 0;
    private int id;
    private String name;
    private String description;
    private HashMap<String, String> CategoryAdditionalInfo;
    private HashMap<String, String> SubCategoryAdditionalInfo;
    private int visitCount = 0;
    private LocalDateTime creationDate;
    private long price = 0;

    private String categoryName;
    private String subCategoryName;

    private ArrayList<Score> scores;
    private float averageScore = 0;
    private int numberOfScore = 0;
    private ArrayList<Comment> comments;

    public Product(String name, String categoryName, String subCategoryName, long price){
        this.name = name;
        this.categoryName = categoryName;
        this.subCategoryName = subCategoryName;
        this.creationDate = LocalDateTime.now();
        this.price = price;
        id = nextId;
        nextId++;
    }


    private static int field = 1;
    private static ArrayList<Product> productArrayList;

    public static ArrayList<Product> sortProducts(int fieldSort,ArrayList<Product> productArrayList) {

        /*
        How to use this method :
        fieldSort = 1 for Name sort
        fieldSort = 2 for visitCount sort
        fieldSort = 3 for price sort
        fieldSort = 4 for averageScore sort
        fieldSort = 5 for date sort
         */
        field = fieldSort;

        Collections.sort(productArrayList);
        return productArrayList;
    }

    public int compareTo(Product product) {
        switch (field) {
            case 1:
                return -(product.name.compareTo(this.name));
            case 2:
                return -(product.visitCount - this.visitCount);
            case 3:
                return (int) -(product.price - this.price);
            case 4:
                return (int) -(product.averageScore - this.averageScore);
            case 5:
                return -(product.creationDate.compareTo(this.creationDate));
            default:
                return 0;
        }
    }

    //------------------test---------
    void createAllArrayLists(){
        scores = new ArrayList<>();
        comments = new ArrayList<>();
    }
    //------------------test---------

    public void addComment(Comment comment){
        comments.add(comment);
    }

    public void addScore(Score score){
        scores.add(score);
        if (numberOfScore==0){
            averageScore=score.getScore();
            numberOfScore++;
        }else {
            averageScore = (numberOfScore*averageScore+score.getScore())/numberOfScore;
        }
    }

    /**
     * this method changes name or description or price
     * @param field 1 - name<br/>2 - description<br/>4 - price
     * @param newValue the new text
     */
    public void changeField(byte field, String newValue){
        switch (field) {
            case 1:
                name = newValue;
                break;
            case 2:
                description = newValue;
                break;
            case 4:
                price = Long.parseLong(newValue);
                break;
        }
    }

    public long getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public HashMap<String, String> getCategoryAdditionalInfo() {
        return CategoryAdditionalInfo;
    }

    public HashMap<String, String> getSubCategoryAdditionalInfo() {
        return SubCategoryAdditionalInfo;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void addVisitCount(){
        visitCount++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", price=" + price +
                ", categoryName='" + categoryName + '\'' +
                ", subCategoryName='" + subCategoryName + '\'' +
                '}';
    }
}
