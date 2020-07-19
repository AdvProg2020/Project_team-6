package server.controller;

import javafx.stage.Stage;
import server.Server;
import server.model.product.Category;
import server.model.product.Product;
import server.model.product.SubCategory;
import client.view.old.CategoriesAndSubCategoriesMenuView;
import client.view.old.PersonalInfoMenuView;
import client.view.old.SellerProductsMenuView;
import client.view.old.SingleProductScreenView;

import java.io.IOException;
import java.util.ArrayList;


public class CategoriesAndSubCategoriesMenu implements Parent{
    /*
    private static CategoriesAndSubCategoriesMenu instance;

    public static CategoriesAndSubCategoriesMenu getInstance() {
        if (instance == null)
            instance = new CategoriesAndSubCategoriesMenu();
        return instance;
    }
    public void startAsManager() {
        client.view = new CategoriesAndSubCategoriesMenuView();
        try {
            client.view.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateCategoriesArrayList();
        String command = null;
        state = 0;
        while (true) {
            switch (state) {
                case 0:
                    System.out.println("a");
                    client.view.showCategoriesList(allCategoriesArrayList);
                    System.out.println("b");
                    command = client.view.getInputCommandManagerCategory();
                    System.out.println("c");
                    if (command.matches("edit \\d+ \\S+")) {
                        String[] splitCommand = command.split("\\s");
                        edit(Integer.parseInt(splitCommand[1]), splitCommand[2]);
                    }
                    else if (command.matches("add \\S+")) {
                        String[] splitCommand = command.split("\\s");
                        add(splitCommand[1]);
                    }
                    else if (command.matches("remove \\d+")) {
                        String[] splitCommand = command.split("\\s");
                        remove(Integer.parseInt(splitCommand[1]));
                    }
                    else if (command.matches("open \\d+")) {
                        String[] splitCommand = command.split("\\s");
                        open(Integer.parseInt(splitCommand[1]));
                    }
                    else if (command.equals("back")) {
                        return;
                    }
                    else {
                        throw new RuntimeException("Unknown command was passed to ManageCategories by client.view");
                    }
                    break;
                case 1:
                    client.view.showSubCategoriesList(allSubCategoriesArrayList);
                    command = client.view.getInputCommandManagerCategory();
                    if (command.matches("edit \\d+ \\S+")) {
                        String[] splitCommand = command.split("\\s");
                        edit(Integer.parseInt(splitCommand[1]), splitCommand[2]);
                    }
                    else if (command.matches("add \\S+")) {
                        String[] splitCommand = command.split("\\s");
                        add(splitCommand[1]);
                    }
                    else if (command.matches("remove \\d+")) {
                        String[] splitCommand = command.split("\\s");
                        remove(Integer.parseInt(splitCommand[1]));
                    }
                    else if (command.matches("open \\d+")) {
                        String[] splitCommand = command.split("\\s");
                        open(Integer.parseInt(splitCommand[1]));
                    }
                    else if (command.equals("back")) {
                        state = 0;
                    }
                    else {
                        throw new RuntimeException("Unknown command was passed to ManageCategories by client.view");
                    }
                    break;
                case 2:
                    client.view.showProductsList(allProductsArrayList);
                    command = client.view.getInputCommandManagerProduct();
                    if (command.matches("remove \\d+")) {
                        String[] splitCommand = command.split("\\s");
                        remove(Integer.parseInt(splitCommand[1]));
                    }
                    else if (command.matches("open \\d+")) {
                        String[] splitCommand = command.split("\\s");
                        open(Integer.parseInt(splitCommand[1]));
                    }
                    else if (command.equals("back")) {
                        state = 1;
                    }
                    else {
                        throw new RuntimeException("Unknown command was passed to ManageCategories by client.view");
                    }
                    break;
            }
        }
    }
    public void startAsSeller() {
        String command = null;
        state = 0;
        while (true) {
            switch (state) {
                case 0:
                    client.view.showCategoriesList(allCategoriesArrayList);
                    command = client.view.getInputCommandSellerCategory();
                    if (command.matches("open \\d+")) {
                        String[] splitCommand = command.split("\\s");
                        open(Integer.parseInt(splitCommand[1]));
                    }
                    else if (command.equals("back")) {
                        return;
                    }
                    else {
                        throw new RuntimeException("Unknown command was passed to ManageCategories by client.view");
                    }
                    break;
                case 1:
                    client.view.showSubCategoriesList(allSubCategoriesArrayList);
                    command = client.view.getInputCommandSellerSubCategory();
                    if (command.matches("addTo \\d+")) {
                        String[] splitCommand = command.split("\\s");
                        addProduct(Integer.parseInt(splitCommand[1]));
                    }
                    else if (command.equals("back")) {
                        state = 0;
                    }
                    else {
                        throw new RuntimeException("Unknown command was passed to ManageCategories by client.view");
                    }
                    break;
            }
        }
    }

    public void startAsBuyer() {
        String command = null;
        state = 0;
        while (true) {
            switch (state) {
                case 0:
                    client.view.showCategoriesList(allCategoriesArrayList);
                    command = client.view.getInputCommandBuyer();
                    if (command.matches("open \\d+")) {
                        String[] splitCommand = command.split("\\s");
                        open(Integer.parseInt(splitCommand[1]));
                    }
                    else if (command.equals("back")) {
                        return;
                    }
                    else {
                        throw new RuntimeException("Unknown command was passed to ManageCategories by client.view");
                    }
                    break;
                case 1:
                    client.view.showSubCategoriesList(allSubCategoriesArrayList);
                    command = client.view.getInputCommandBuyer();
                    if (command.matches("open \\d+")) {
                        String[] splitCommand = command.split("\\s");
                        open(Integer.parseInt(splitCommand[1]));
                    }
                    else if (command.equals("back")) {
                        state = 0;
                    }
                    else {
                        throw new RuntimeException("Unknown command was passed to ManageCategories by client.view");
                    }
                    break;
                case 2:
                    client.view.showProductsList(allProductsArrayList);
                    command = client.view.getInputCommandBuyer();
                    if (command.matches("open \\d+")) {
                        String[] splitCommand = command.split("\\s");
                        open(Integer.parseInt(splitCommand[1]));
                    }
                    else if (command.equals("back")) {
                        state = 1;
                    }
                    else {
                        throw new RuntimeException("Unknown command was passed to ManageCategories by client.view");
                    }
                    break;
            }
        }

    }*/
    /*
    public void start(PersonalInfoMenuView personalInfoMenuView){
        this.personalInfoMenuView = personalInfoMenuView;
        view = new CategoriesAndSubCategoriesMenuView(this);
        try {
            view.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateCategoriesArrayList();
        view.showCategoriesList(allCategoriesArrayList);
        state = 0;
    }
    */
    private Server server = null;

    @Override
    public void start(Server server) throws IOException {
        this.server = server;
        String message = "";
        for(int i = 0;i < allCategoriesArrayList.size();i++){
            message = message + "---" + i + ". " + allCategoriesArrayList.get(i);
        }
        sendMessage(message);
    }

    private void sendMessage(String message) throws IOException {
        server.sendMessage("12-" + message);
    }

    ///////////////////////////////////////////
    private CategoriesAndSubCategoriesMenuView view;
    private byte state = 0;
    private Category currentCategory = null;
    private SubCategory currentSubCategory = null;

    private ArrayList<Category> allCategoriesArrayList;
    private ArrayList<SubCategory> allSubCategoriesArrayList;
    private ArrayList<Product> allProductsArrayList;
    private void updateCategoriesArrayList() {
        allCategoriesArrayList = new ArrayList<>(ProgramManager.getProgramManagerInstance().getAllCategories());
        //TODO : Kamali please Do some Sort:)
        categorySort(allCategoriesArrayList);
    }

    private void updateSubCategoriesArrayList() {
        allSubCategoriesArrayList = new ArrayList<>(currentCategory.getAllSubCategories());
        //TODO : Kamali please Do some Sort:)
        subCategorySort(allSubCategoriesArrayList);
    }

    private void updateProductsArrayList() {
        ArrayList<Integer> productIds = currentSubCategory.getAllProductIds();
        allProductsArrayList = new ArrayList<>();
        ProgramManager programManager = ProgramManager.getProgramManagerInstance();
        for (Integer id : productIds) {
            allProductsArrayList.add(programManager.getProductById(id));
        }
        //TODO: maybe do some sorting?
    }

    ///////////////////////////////////
    public void edit(int index, String newName) {
        /*if (state == 0 && ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUserRole() == 3) {
            Category category = allCategoriesArrayList.get(index);
            category.setName(newName);
            updateCategoriesArrayList();
            view.showCategoriesList(allCategoriesArrayList);
        }
        else if (state == 1 && ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUserRole() == 3) {
            SubCategory subCategory = allSubCategoriesArrayList.get(index);
            subCategory.setName(newName);
            updateSubCategoriesArrayList();
            view.showSubCategoriesList(allSubCategoriesArrayList);
        }
         */
        Category category = allCategoriesArrayList.get(index);
        category.setName(newName);
        updateCategoriesArrayList();
        try {
            sendMessage("edited");
        } catch (IOException e) {
            System.err.println("error occurred");
        }

    }

    public void add(String Data) throws IOException {
//        if (state == 0 && ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUserRole() == 3) {
//            if (ProgramManager.getProgramManagerInstance().getCategoryByName(name) == null) {
//                ProgramManager.getProgramManagerInstance().addCategory(new Category(name));
//                updateCategoriesArrayList();
//                view.showCategoriesList(allCategoriesArrayList);
//            }
//            else
//                view.giveOutPutError("Repeated name");
//        }
//        else if (state == 1 && ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUserRole() == 3) {
//            if (currentCategory.getSubCategoryByName(name) == null) {
//                currentCategory.addSubcategory(new SubCategory(name));
//                updateSubCategoriesArrayList();
//                view.showSubCategoriesList(allSubCategoriesArrayList);
//            }
//            else
//                view.giveOutPutError("Repeated name");
//        }
//        else if (state == 2 && ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUserRole() == 2) {
//            try {
//                new SellerProductsMenuView().start(new Stage());
//            } catch (Exception e) {
//                System.err.println("Zahr e mar");
//                e.printStackTrace();
//            }
        if(!ProgramManager.getProgramManagerInstance().getAllCategories().contains(ProgramManager.getProgramManagerInstance().getCategoryByName(Data.split("---")[0]))) {
            ArrayList<String> additionalAttributes = new ArrayList<>();
            String[] dataSplit = Data.split("---");
            for (int i = 1; i < dataSplit.length; i++) {
                additionalAttributes.add(dataSplit[i]);
            }
            allCategoriesArrayList.add(new Category(dataSplit[0], additionalAttributes));
            updateCategoriesArrayList();
            sendMessage("created");
        }else
            sendMessage("duplicateCode");
    }

    public void remove(int index) {
        /*if (state == 0 && ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUserRole() == 3) {
            Category category = allCategoriesArrayList.get(index);
            ProgramManager.getProgramManagerInstance().removeCategory(category);
            updateCategoriesArrayList();
            view.showCategoriesList(allCategoriesArrayList);
        }
        if (state == 1 && ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUserRole() == 3) {
            SubCategory subCategory = allSubCategoriesArrayList.get(index);
            currentCategory.removeSubcategory(subCategory);
            updateSubCategoriesArrayList();
            view.showSubCategoriesList(allSubCategoriesArrayList);
        }
        if (state == 2 && ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUserRole() == 3) {
            Product product = allProductsArrayList.get(index);
            currentSubCategory.removeProduct(product.getId());
            updateProductsArrayList();
            view.showProductsList(allProductsArrayList);
        }
         */
        allCategoriesArrayList.remove(index);
        updateCategoriesArrayList();
        try {
            sendMessage("removed");
        } catch (IOException e) {
            System.err.println("error occurred");
        }
    }

    public void open(int index) {
//        if (state == 0) {
//            currentCategory = allCategoriesArrayList.get(index);
//            updateSubCategoriesArrayList();
//            state = 1;
//            view.showSubCategoriesList(allSubCategoriesArrayList);
//        }
//        else if (state == 1) {
//            currentSubCategory = allSubCategoriesArrayList.get(index);
//            updateProductsArrayList();
//            state = 2;
//            view.showProductsList(allProductsArrayList);
//        }
//        else if (state == 2) {
//            Product product = allProductsArrayList.get(index);
//            try {
//                new SingleProductScreenView(product.getId()).start(new Stage());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        Category tempCategory = allCategoriesArrayList.get(index);
        try {
            sendMessage(tempCategory.toString());
        } catch (IOException e) {
            System.err.println("error occurred");
        }

    }

    public void categorySort(ArrayList<Category> allCategoriesArrayList){
        //TODO ostad kamali

    }
    public void subCategorySort(ArrayList<SubCategory> allSubCategoriesArrayList){
        //TODO ostad kamali
    }
}
