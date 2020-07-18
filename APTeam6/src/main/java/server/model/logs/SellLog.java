package server.model.logs;

import server.controller.ProgramManager;
import server.model.account.Seller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class SellLog extends LogsInGeneral implements Comparable<SellLog>{
    private int receivedAmount;
    private ArrayList<Integer> soldProducts;
    protected String sellerUserName;

    public SellLog(Date date, int executedDiscount, int logId, int receivedAmount, String sellerUserName) {
        super(date, executedDiscount, logId , 2);
        this.receivedAmount = receivedAmount;
        this.soldProducts = new ArrayList<Integer>();
        this.sellerUserName = sellerUserName;
        Seller.sellLogIds.add(logId);
    }

    private static int field = 1;
    private static ArrayList<SellLog> sellLogArrayList;
    public int compareTo(SellLog sellLog) {
        switch (field) {
            case 1:
                return -(sellLog.date.compareTo(this.date));
            case 2:
                return -(sellLog.executedDiscount - this.executedDiscount);
            case 3:
                return -(sellLog.logId - this.logId);
            case 4:
                return -(sellLog.receivedAmount-this.receivedAmount);
            case 5:
                return -(sellLog.sellerUserName.compareTo(this.sellerUserName));
            default:
                return 0;
        }
    }

    public String getSellerUserName() {
        return sellerUserName;
    }

    @Override
    public String toString() {

        String string = "SellLog{" +
                "receivedAmount=" + receivedAmount +
                ", soldProducts=" ;
        for (Integer soldProduct : soldProducts) {
            string += soldProduct.toString();
        }
        string += ", date=" + date +
                ", executedDiscount=" + executedDiscount +
                '}';

        return string;
    }

    public static ArrayList<SellLog> sortBuyLog(int fieldSort) {
        sellLogArrayList = new ArrayList<>();
        /*
        How to use this method :
        fieldSort = 1 for date sort
        fieldSort = 2 for executedDiscount sort
        fieldSort = 3 for logId sort
        fieldSort = 4 for receivedAmount sort
        fieldSort = 5 for sellerName sort
         */
        field = fieldSort;
        Collection<LogsInGeneral> values = ProgramManager.getProgramManagerInstance().getAllLogs();
        ArrayList<LogsInGeneral> listOfValues = new ArrayList<>(values);
        for (int i = 0; i < listOfValues.size(); i++) {
            if (listOfValues.get(i).type==2) {
                sellLogArrayList.add((SellLog) listOfValues.get(i));
            }
        }
        Collections.sort(sellLogArrayList);
        return sellLogArrayList;
    }

    public ArrayList<Integer> getSoldProductIds(){
        return soldProducts;
    }

    public int getReceivedAmount() {
        return receivedAmount;
    }
}
