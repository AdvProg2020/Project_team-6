package model.logs;

import controller.ProgramManager;
import model.account.Buyer;
import model.product.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class BuyLog extends LogsInGeneral implements Comparable<BuyLog> {
    private int paidAmount;
    private ArrayList<Product> boughtProducts;
    private String buyerName;

    public BuyLog(Date date, int executedDiscount, int logId, int paidAmount, String buyerName) {
        super(date, executedDiscount, logId , 1);
        this.paidAmount = paidAmount;
        this.boughtProducts = new ArrayList<Product>();
        this.buyerName = buyerName;
        Buyer.buyLogIds.add(logId);
    }

    private static int field = 1;
    private static ArrayList<BuyLog> buyLogArrayList = new ArrayList<>();
    public int compareTo(BuyLog buyLog) {
        switch (field) {
            case 1:
                return -(buyLog.date.compareTo(this.date));
            case 2:
                return -(buyLog.executedDiscount - this.executedDiscount);
            case 3:
                return -(buyLog.logId - this.logId);
            case 4:
                return -(buyLog.paidAmount-this.paidAmount);
            case 5:
                return -(buyLog.buyerName.compareTo(this.buyerName));
            default:
                return 0;
        }
    }

    public ArrayList<BuyLog> sortBuyLog(int fieldSort) {
        /*
        How to use this method :
        fieldSort = 1 for date sort
        fieldSort = 2 for executedDiscount sort
        fieldSort = 3 for logId sort
        fieldSort = 4 for paidAmount sort
        fieldSort = 5 for buyerName sort
         */
        field = fieldSort;
        Collection<LogsInGeneral> values = ProgramManager.getProgramManagerInstance().getAllLogs();
        ArrayList<LogsInGeneral> listOfValues = new ArrayList<>(values);
        for (int i = 0; i < listOfValues.size(); i++) {
            if (listOfValues.get(i).type==1) {
                buyLogArrayList.add((BuyLog) listOfValues.get(i));
            }
        }
        Collections.sort(buyLogArrayList);
        return buyLogArrayList;
    }
}
