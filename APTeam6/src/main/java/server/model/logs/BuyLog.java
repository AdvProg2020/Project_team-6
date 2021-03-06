package server.model.logs;

import server.controller.ProgramManager;
import server.model.account.Buyer;
import server.model.product.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class BuyLog extends LogsInGeneral implements Comparable<BuyLog> {
    private int paidAmount;
    private ArrayList<Integer> boughtProductsId;
    private String buyerUserName;

    public BuyLog(int executedDiscount, int logId, int paidAmount, String buyerUserName,ArrayList<Integer> productIds) {
        super(LocalDateTime.now(), executedDiscount, logId , 1);
        this.paidAmount = paidAmount;
        this.buyerUserName = buyerUserName;
        Buyer.buyLogIds.add(logId);
        boughtProductsId = productIds;
    }

    public int getPaidAmount() {
        return paidAmount;
    }

    public ArrayList<Integer> getBoughtProducts() {
        return boughtProductsId;
    }

    public String getBuyerUserName() {
        return buyerUserName;
    }

    private static int field = 1;
    private static ArrayList<BuyLog> buyLogArrayList;
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
                return -(buyLog.buyerUserName.compareTo(this.buyerUserName));
            default:
                return 0;
        }
    }

    public static ArrayList<BuyLog> sortBuyLog(int fieldSort) {
        buyLogArrayList = new ArrayList<>();
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
