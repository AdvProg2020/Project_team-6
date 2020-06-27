package model.account;

import controller.ProgramManager;
import model.account.Buyer;
import model.product.Product;
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
        ArrayList<Product> productArrayList = new ArrayList<>();
        //productArrayList.add(new Product("chair","a","b"));
        //productArrayList.add(new Product("table","a","c"));
        //expectedBuyers.get(0).addProductToBuyBasket(productArrayList.get(0));
        //expectedBuyers.get(0).addProductToBuyBasket(productArrayList);
        Assert.assertNotEquals(expectedBuyers,Buyer.sortBuyers(0));
        Assert.assertNotEquals(expectedBuyers,Buyer.sortBuyers(1));
        Assert.assertNotEquals(expectedBuyers,Buyer.sortBuyers(2));
        Assert.assertNotEquals(expectedBuyers,Buyer.sortBuyers(3));
        Assert.assertNotEquals(expectedBuyers,Buyer.sortBuyers(4));
        Assert.assertEquals(expectedBuyers,Buyer.sortBuyers(5));
    }

    @Test
    public void buyerInformationTest(){
        ProgramManager.getProgramManagerInstance().createAllAccountHashMapForTest();
        Buyer buyer = new Buyer("user1","1234","ali","ali poor","ali@gmail.com","09356487591");
        Assert.assertEquals(0,buyer.getCredit());
        buyer.modifyCreditBy(10);
        Assert.assertEquals(10,buyer.getCredit());
        Assert.assertTrue(buyer.doesHaveEnoughMoney(10));
        Assert.assertEquals(1,buyer.getRole());
        Assert.assertTrue(buyer.checkPassword("1234"));
        buyer.setFirstName("hasan");
        Assert.assertEquals("hasan",buyer.getFirstName());
        buyer.setLastName("ali nejad");
        Assert.assertEquals("ali nejad",buyer.getLastName());
        buyer.setPassword("12345");
        Assert.assertEquals("12345",buyer.getPassword());
        buyer.setEmailAddress("ali1300@gmail.com");
        Assert.assertEquals("ali1300@gmail.com",buyer.getEmailAddress());
        buyer.setPhoneNumber("09123456789");
        Assert.assertEquals("09123456789",buyer.getPhoneNumber());
    }
}
