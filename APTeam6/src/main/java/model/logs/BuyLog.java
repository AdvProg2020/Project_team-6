package model.logs;

import model.account.Buyer;
import model.product.Products;

import java.util.ArrayList;
import java.util.Date;

public class BuyLog extends LogsInGeneral{
    private int paidAmount;
    private ArrayList<Products> boughtProducts;
    private String buyerName;

    public BuyLog(Date date, int executedDiscount, int logId, int paidAmount, String buyerName) {
        super(date, executedDiscount, logId);
        this.paidAmount = paidAmount;
        boughtProducts = new ArrayList<Products>();
        this.buyerName = buyerName;
        Buyer.buyLogIds.add(logId);
    }
}
