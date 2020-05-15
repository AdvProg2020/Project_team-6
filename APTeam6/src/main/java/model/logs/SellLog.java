package model.logs;

import model.account.Seller;
import model.product.Product;

import java.util.ArrayList;
import java.util.Date;

public class SellLog extends LogsInGeneral{
    private int receivedAmount;
    private ArrayList<Product> soldProducts;
    protected String sellerName;

    public SellLog(Date date, int executedDiscount, int logId, int receivedAmount, String sellerName) {
        super(date, executedDiscount, logId);
        this.receivedAmount = receivedAmount;
        this.soldProducts = new ArrayList<Product>();
        this.sellerName = sellerName;
        Seller.sellLogIds.add(logId);
    }
}
