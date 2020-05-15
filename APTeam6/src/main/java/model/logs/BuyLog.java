package model.logs;

import model.account.Buyer;
import model.product.Product;

import java.util.ArrayList;
import java.util.Date;

public class BuyLog extends LogsInGeneral{
    private int paidAmount;
    private ArrayList<Product> boughtProducts;
    private String buyerName;

    public BuyLog(Date date, int executedDiscount, int logId, int paidAmount, String buyerName) {
        super(date, executedDiscount, logId);
        this.paidAmount = paidAmount;
        this.boughtProducts = new ArrayList<Product>();
        this.buyerName = buyerName;
        Buyer.buyLogIds.add(logId);
    }
}
