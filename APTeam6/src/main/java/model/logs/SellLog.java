package model.logs;

import model.product.Products;

import java.util.ArrayList;
import java.util.Date;

public class SellLog extends LogsInGeneral{
    private int receivedAmount;
    private ArrayList<Products> soldProducts;
    protected String sellerName;

    public SellLog(Date date, int executedDiscount, int logId, int receivedAmount, String sellerName) {
        super(date, executedDiscount, logId);
        this.receivedAmount = receivedAmount;
        soldProducts = new ArrayList<Products>();
        this.sellerName = sellerName;
    }
}
