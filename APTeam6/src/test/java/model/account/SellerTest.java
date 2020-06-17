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
        Assert.assertNotEquals(sellers,Seller.sortSellers(0));
        Assert.assertNotEquals(sellers,Seller.sortSellers(1));
        Assert.assertNotEquals(sellers,Seller.sortSellers(2));
        Assert.assertNotEquals(sellers,Seller.sortSellers(3));
        Assert.assertNotEquals(sellers,Seller.sortSellers(4));
        Assert.assertEquals(sellers,Seller.sortSellers(5));
        sellers.get(0).modifyCreditBy(10);
        Assert.assertEquals(10,sellers.get(0).getCredit());
    }


    //sort seller test with decline
    @Test
    public void sellerTest2() {
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
        //sellers.get(0).showDetails();
        Assert.assertNotEquals(sellers.get(0).getCompanyName(),sellers.get(0).getSellLogIds());
        /*for (Seller expectedSeller : sellers) {
            System.out.println(expectedSeller.role);
        }*/
        Assert.assertEquals(sellers,Seller.sortSellers(1));
    }
}
