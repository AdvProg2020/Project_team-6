package model.product;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Product {
    static int nextId = 0;
    private int id;
    private String name;
    private HashMap<String, String> CategoryAdditionalInfo;
    private HashMap<String, String> SubCategoryAdditionalInfo;
    private int visitCount = 0;
    private LocalDateTime creationDate;

    public Product(String name){
        this.name = name;
        id = nextId;
        nextId++;
    }
}
