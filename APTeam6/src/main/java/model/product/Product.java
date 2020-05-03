package model.product;

import java.util.Date;
import java.util.HashMap;

public class Product {
    static int nextId = 0;
    private int id;
    private String name;
    private HashMap<String, String> additionalInfo;
    private int visitCount = 0;
    private Date creationDate;

    public Product(){

    }
}
