package model.logs;

import model.product.Products;

import java.util.ArrayList;
import java.util.Date;

public class LogsInGeneral {
    protected static int logId;
    protected Date date;
    protected int paidAmount;
    protected int receivedAmount;
    protected int executedDiscount;
    protected ArrayList<Products> boughtProducts;
    protected ArrayList<Products> soldProducts;
    protected String buyerName;
    protected String sellerName;



}
