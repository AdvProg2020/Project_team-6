package server.model.account;

import server.controller.ProgramManager;
import server.model.product.Product;

import java.util.*;

public class Buyer extends Account implements Comparable<Buyer> {
    private long credit;
    public static ArrayList<Integer> buyLogIds = new ArrayList<Integer>();
    private HashMap<Integer, Integer> buyBasket = new HashMap<>();
    //The HashMap is productId to count

    public Buyer(String username, String password, String firstName, String lastName, String emailAddress, String phoneNumber) {
        super(username, password, firstName, lastName, emailAddress, phoneNumber);
        this.role = 1;
        credit = 0;
        ProgramManager.getProgramManagerInstance().addAccountToList(username, this);
    }

    public boolean doesHaveEnoughMoney(long price) {
        return credit >= price;
    }

    public void modifyCreditBy(long amount) {
        credit += amount;
    }

    public long getCredit() {
        return credit;
    }

    public void setCredit(long credit) {
        this.credit = credit;
    }

    public int compareTo(Buyer buyer) {
        switch (field) {
            case 1:
                return -(buyer.firstName.compareTo(this.firstName));
            case 2:
                return -(buyer.lastName.compareTo(this.lastName));
            case 3:
                return -(buyer.phoneNumber.compareTo(this.phoneNumber));
            case 4:
                return -(buyer.emailAddress.compareTo(this.emailAddress));
            case 5:
                return -(buyer.username.compareTo(this.username));
            default:
                return 0;
        }
    }

    private static int field = 1;
    private static ArrayList<Buyer> buyerArrayList;

    public static ArrayList<Buyer> sortBuyers(int fieldSort) {
        buyerArrayList = new ArrayList<>();
        /*
        How to use this method :
        fieldSort = 1 for firstName sort
        fieldSort = 2 for lastName sort
        fieldSort = 3 for phoneNumber sort
        fieldSort = 4 for emailAddress sort
        fieldSort = 5 for username sort
         */
        field = fieldSort;
        Collection<Account> values = ProgramManager.getProgramManagerInstance().getAllAccounts();
        ArrayList<Account> listOfValues = new ArrayList<>(values);
        for (int i = 0; i < listOfValues.size(); i++) {
            if (listOfValues.get(i).role == 1) {
                buyerArrayList.add((Buyer) listOfValues.get(i));
            }
        }
        Collections.sort(buyerArrayList);
        return buyerArrayList;
    }

    /**
     * Adds the product to this Buyer's buyBasket. Increases the count if the product is already in the buyBasket.
     */
    public void addProductToBuyBasket(Product product, int count) {
        if (buyBasket.containsKey(product.getId()))
            buyBasket.replace(product.getId(), buyBasket.get(product.getId() + count));
        else
            buyBasket.put(product.getId(), count);
    }

    /**
     * Adds the product to this Buyer's buyBasket. Increases the count if the product is already in the buyBasket.
     */
    public void addProductToBuyBasket(int productId, int count) {
        if (buyBasket.containsKey(productId))
            buyBasket.replace(productId, buyBasket.get(productId + count));
        else
            buyBasket.put(productId, count);
    }

    public void addProductToBuyBasket(HashMap<Product, Integer> products) {
        Set<Product> productsOnly = products.keySet();
        for (Product product : productsOnly) {
            if (buyBasket.containsKey(product.getId()))
                buyBasket.replace(product.getId(), buyBasket.get(product.getId() + products.get(product)));
            else
                buyBasket.put(product.getId(), products.get(product));
        }
    }

    public ArrayList<Integer> getBuyBasketProductIds() {
        return new ArrayList<>(buyBasket.keySet());
    }

    public HashMap<Integer, Integer> getBuyBasket() {
        return buyBasket;
    }

    //TODO: Whoever made this method please delete it
    /*
    public void increaseProductInBuyBasketBy(int productId, int count) {
        if (buyBasket.containsKey(productId)) {
            count += buyBasket.get(productId);
            buyBasket.replace(productId, count);
        }
    }
     */
}