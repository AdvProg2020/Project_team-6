package model.account;

import controller.ProgramManager;
import model.account.Buyer;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class BuyerTest {
    @Test
    public void sortBuyersTest() {
        ProgramManager.getProgramManagerInstance().createAllAccountHashMapForTest();
        ArrayList<Buyer> expectedBuyers = new ArrayList<Buyer>();
        expectedBuyers.add(new Buyer("ale", "ale:123", "hasan", "hasani", "hasani@gmail.com", "09191000100"));
        expectedBuyers.add(new Buyer("jack", "jafar:123", "jafar", "jafari", "jafari@gmail.com", "09030647951"));
        expectedBuyers.add(new Buyer("json", "ali:123", "ali", "ali poor", "alipoor@gmail.com", "09123456789"));
        /*for (Buyer expectedBuyer : expectedBuyers) {
            System.out.println(expectedBuyer.username);
        }*/
        Assert.assertEquals(expectedBuyers,Buyer.sortBuyers(5));
    }
}
