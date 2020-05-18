package model.account;

import controller.ProgramManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class SellerTest {

    //sort seller test with accepting
    @Test
    public void sortSellerTest() {
        ProgramManager.getProgramManagerInstance().createAllRequestHashMapForTest();
        ProgramManager.getProgramManagerInstance().createAllAccountHashMapForTest();
        ArrayList<Seller> sellers = new ArrayList<Seller>();
        sellers.add(new Seller("ale", "ale:123", "hasan", "hasani", "hasani@gmail.com", "09191000100","c1"));
        sellers.add(new Seller("jack", "jafar:123", "jafar", "jafari", "jafari@gmail.com", "09030647951","c2"));
        sellers.add(new Seller("json", "ali:123", "ali", "ali poor", "alipoor@gmail.com", "09123456789","c3"));
        for (Seller expectedSeller : sellers) {
            expectedSeller.accept();
        }
        /*for (Seller expectedSeller : sellers) {
            System.out.println(expectedSeller.role);
        }*/
        Assert.assertEquals(sellers,Seller.sortSellers(5));
    }


    //sort seller test with decline
    @Test
    public void sortSellerTest2() {
        ProgramManager.getProgramManagerInstance().createAllRequestHashMapForTest();
        ProgramManager.getProgramManagerInstance().createAllAccountHashMapForTest();
        ArrayList<Seller> sellers = new ArrayList<Seller>();
        sellers.add(new Seller("ale", "ale:123", "hasan", "hasani", "hasani@gmail.com", "09191000100","c1"));
        sellers.add(new Seller("jack", "jafar:123", "jafar", "jafari", "jafari@gmail.com", "09030647951","c2"));
        Seller declineSeller = new Seller("json", "ali:123", "ali", "ali poor", "alipoor@gmail.com", "09123456789","c3");
        for (Seller expectedSeller : sellers) {
            expectedSeller.accept();
        }
        declineSeller.decline();
        /*for (Seller expectedSeller : sellers) {
            System.out.println(expectedSeller.role);
        }*/
        Assert.assertEquals(sellers,Seller.sortSellers(1));
    }
}
