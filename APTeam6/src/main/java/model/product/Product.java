package model.product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Product {
    static int nextId = 0;
    private int id;
    private String name;
    private String description;
    private HashMap<String, String> CategoryAdditionalInfo;
    private HashMap<String, String> SubCategoryAdditionalInfo;
    private int visitCount = 0;
    private LocalDateTime creationDate;

    private String categoryName;
    private String subCategoryName;

    private ArrayList<Score> scores;
    private ArrayList<Comment> comments;

    public Product(String name, String categoryName, String subCategoryName){
        this.name = name;
        this.categoryName = categoryName;
        this.subCategoryName = subCategoryName;
        id = nextId;
        nextId++;
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }

    public void addScore(Score score){
        scores.add(score);
    }

    /**
     * this method changes name or description
     * @param field 1 - name<br/>2 - description
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
        }
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

    public ArrayList<Score> getScores() {
        return scores;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}
