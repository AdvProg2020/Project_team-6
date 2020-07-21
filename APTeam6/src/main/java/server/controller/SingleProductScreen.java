package server.controller;

import client.view.old.CategoriesAndSubCategoriesMenuView;
import javafx.stage.Stage;
import server.Server;
import server.model.product.*;
import client.view.old.SingleProductScreenView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SingleProductScreen {
//    Product product;
//    public SingleProductScreen(Product product){
//        this.product = product;
//    }
//    private Server server = null;
//    @Override
//    public void start(Server server) throws IOException {
//        this.server = server;
//        sendMessage("start");
//    }
//
//    private void sendMessage(String message) throws IOException {
//        server.sendMessage("02-" + message);
//    }
//
//    public void start(Product product) throws Exception {
//    }
//private Server server = null;
//
//    @Override
//    public void start(Server server) throws IOException {
//        this.server = server;
//        String message = "";
//        for (int i = 0; i < allCategoriesArrayList.size(); i++) {
//            message = message + "---" + i + ". " + allCategoriesArrayList.get(i);
//        }
//        sendMessage(message);
//    }
//
//    private void sendMessage(String message) throws IOException {
//        server.sendMessage("12-" + message);
//    }
//
//    ///////////////////////////////////////////
//    private CategoriesAndSubCategoriesMenuView view;
//    private Category currentCategory = null;
//    private SubCategory currentSubCategory = null;
//
//    private ArrayList<Category> allCategoriesArrayList;
//    private ArrayList<SubCategory> allSubCategoriesArrayList;
//    private ArrayList<Product> allProductsArrayList;
//
//    private void updateCategoriesArrayList() {
//        allCategoriesArrayList = new ArrayList<>(ProgramManager.getProgramManagerInstance().getAllCategories());

//        categorySort(allCategoriesArrayList);
//    }
//
//    private void updateSubCategoriesArrayList() {
//        allSubCategoriesArrayList = new ArrayList<>(currentCategory.getAllSubCategories());

//        subCategorySort(allSubCategoriesArrayList);
//    }
//
//    private void updateProductsArrayList() {
//        ArrayList<Integer> productIds = currentSubCategory.getAllProductIds();
//        allProductsArrayList = new ArrayList<>();
//        ProgramManager programManager = ProgramManager.getProgramManagerInstance();
//        for (Integer id : productIds) {
//            allProductsArrayList.add(programManager.getProductById(id));
//        }

//    }
//
//
//    ///////////////////////////////////
//    public void editCategory(int index, String newName) {
//        if (server.getCurrentlyLoggedInUsers().getRole() == 3) {
//            Category category = allCategoriesArrayList.get(index);
//            category.setName(newName);
//            updateCategoriesArrayList();
//            try {
//                sendMessage("edited");
//            } catch (IOException e) {
//                System.err.println("error occurred");
//            }
//        }
//    }
//
//    public void editSubCategory(int index, String newName) {
//        if (server.getCurrentlyLoggedInUsers().getRole() == 3) {
//            SubCategory subCategory = allSubCategoriesArrayList.get(index);
//            subCategory.setName(newName);
//            updateSubCategoriesArrayList();
//            try {
//                sendMessage("edited");
//            } catch (IOException e) {
//                System.err.println("error occurred");
//            }
//        }
//    }
//
//    public void addCategory(String Data) throws IOException {
//        if (server.getCurrentlyLoggedInUsers().getRole() == 3) {
//            if (!ProgramManager.getProgramManagerInstance().getAllCategories().contains(ProgramManager.getProgramManagerInstance().getCategoryByName(Data.split("---")[0]))) {
//                ArrayList<String> additionalAttributes = new ArrayList<>();
//                String[] dataSplit = Data.split("---");
//                for (int i = 1; i < dataSplit.length; i++) {
//                    additionalAttributes.add(dataSplit[i]);
//                }
//                allCategoriesArrayList.add(new Category(dataSplit[0], additionalAttributes));
//                updateCategoriesArrayList();
//                sendMessage("Added");
//            } else
//                sendMessage("duplicateCategory");
//        }
//    }
//
//    public void addSubCategory(String Data) {
//        if (server.getCurrentlyLoggedInUsers().getRole() == 3) {
//            if (!currentCategory.getAllSubCategories().contains(currentCategory.getSubCategoryByName(Data.split("---")[0]))) {
//                ArrayList<String> additionalAttributes = new ArrayList<>();
//                String[] dataSplit = Data.split("---");
//                for (int i = 1; i < dataSplit.length; i++) {
//                    additionalAttributes.add(dataSplit[i]);
//                }
//                allSubCategoriesArrayList.add(new SubCategory(dataSplit[0], additionalAttributes));
//                updateSubCategoriesArrayList();
//                try {
//                    sendMessage("Added");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                try {
//                    sendMessage("duplicateSubCategory");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public void removeCategory(int index) {
//        if (server.getCurrentlyLoggedInUsers().getRole() == 3) {
//            allCategoriesArrayList.remove(index);
//            updateCategoriesArrayList();
//            try {
//                sendMessage("removed");
//            } catch (IOException e) {
//                System.err.println("error occurred");
//            }
//        }
//    }
//
//    public void removeSubCategory(int index) {
//        if (server.getCurrentlyLoggedInUsers().getRole() == 3) {
//            allSubCategoriesArrayList.remove(index);
//            updateSubCategoriesArrayList();
//            try {
//                sendMessage("removed");
//            } catch (IOException e) {
//                System.err.println("error occurred");
//            }
//        }
//    }
//
//    public void openCategory(int index) {
//        Category tempCategory = allCategoriesArrayList.get(index);
//        currentCategory = tempCategory;
//        try {
//            String categoriesMessage = "";
//            for (SubCategory allSubCategory : currentCategory.getAllSubCategories()) {
//                categoriesMessage = categoriesMessage + allSubCategory.getName() + "---";
//            }
//            for (String additionalAttribute : currentCategory.getAdditionalAttributes()) {
//                categoriesMessage = categoriesMessage + additionalAttribute + "---";
//            }
//            sendMessage(categoriesMessage);
//        } catch (IOException e) {
//            System.err.println("error occurred");
//        }
//    }
//    public void openSubCategory(int index) {
//        SubCategory tempSubCategory = allSubCategoriesArrayList.get(index);
//        currentSubCategory = tempSubCategory;
//        try {
//            String subCategoriesMessage = "";
//            for (Integer allProductId : currentSubCategory.getAllProductIds()) {
//                subCategoriesMessage = subCategoriesMessage + ProgramManager.getProgramManagerInstance().getProductById(allProductId).getName() + "---";
//            }
//            for (String additionalAttribute : currentSubCategory.getAdditionalAttributes()) {
//                subCategoriesMessage = subCategoriesMessage + additionalAttribute + "---";
//            }
//            sendMessage(subCategoriesMessage);
//        } catch (IOException e) {
//            System.err.println("error occurred");
//        }
//    }
//    public void openProduct(int index){
//        Product tempProduct = allProductsArrayList.get(index);
//        try {
//            //name---categoryName---SubCategoryName---description---price
//            sendMessage(tempProduct.getName() + "---" + tempProduct.getCategoryName() + "---" + tempProduct.getSubCategoryName() + "---" + tempProduct.getDescription() + "---" + tempProduct.getPrice());
//        } catch (IOException e) {
//            System.err.println("error occurred");
//        }
//    }
//    public void addToBuyBasket(int index,int count){
//        Product tempProduct = allProductsArrayList.get(index);
//        try {
//            server.addToCurrentBuyBasket(tempProduct,count);
//            sendMessage("added to buy basket");
//        } catch (IOException e) {
//            System.err.println("error occurred");
//        }
//
//    }
//
//    public void categorySort(ArrayList<Category> allCategoriesArrayList) {
//
//    }
//    public void subCategorySort(ArrayList<SubCategory> allSubCategoriesArrayList) {
//        //TOD
//    }
    /*
    /*
    private static ShowDiscountCode showDiscountCode = null;
    public static ShowDiscountCode getShowDiscountCodeInstance() {
        if (showDiscountCode == null)
            showDiscountCode= new ShowDiscountCode();
        return showDiscountCode;
    }
    //////////////////////////
    private ShowDiscountCodeView view;

    public void start(){
        view = new ShowDiscountCodeView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.matches("view discount code \\.+")) {
                viewDiscountCode(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[3]));
            }
            else if (command.matches("edit discount code \\.+")) {
                if(command.split("\\s")[3].equalsIgnoreCase("code")) {
                    editDiscountCodeCode(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[4]),command.split("\\s")[5]);
                }
                else if(command.split("\\s")[3].equalsIgnoreCase("startDate")){
                    editDiscountCodeStartDate(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[4]),command.split("\\s")[5]);
                }
                else if(command.split("\\s")[3].equalsIgnoreCase("endDate")){
                    editDiscountCodeEndDate(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[4]),command.split("\\s")[5]);
                }
                else if(command.split("\\s")[3].equalsIgnoreCase("percentage")){
                    editDiscountCodePercentage(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[4]),Integer.parseInt(command.split("\\s")[5]));
                }
                else{
                    editDiscountCodeRepetitionTime(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[4]),Integer.parseInt(command.split("\\s")[5]));
                }
            }
            else if (command.equals("remove discount code \\.+")){
                removeDiscountCode(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[3]));
            }
            else if (command.equals("back")){
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to DiscountCode by client.view");
            }
        }
    }


    public void viewDiscountCode(DiscountCode discountCode){
        view.viewDiscountCode1(discountCode);
        view.viewDiscountCode2(discountCode);
        view.viewDiscountCode3(discountCode);
        view.viewDiscountCode4(discountCode);
        view.viewDiscountCode5(discountCode);
        view.viewDiscountCode6(discountCode);
    }
    public void editDiscountCodeCode(DiscountCode discountCode, String code){
        view.editDiscountCodeCode(discountCode,code);
    }
    public void removeDiscountCode(DiscountCode discountCode){
        view.removeDiscountCode(discountCode);
    }
    public void editDiscountCodeStartDate(DiscountCode discountCode,String startDate){
        view.editDiscountCodeStartDate(discountCode,startDate);
    }
    public void editDiscountCodeEndDate(DiscountCode discountCode,String endDate){
        view.editDiscountCodeEndDate(discountCode,endDate);
    }
    public void editDiscountCodePercentage(DiscountCode discountCode,int percentage){
        view.editDiscountCodePercentage(discountCode,percentage);
    }
    public void editDiscountCodeRepetitionTime(DiscountCode discountCode, int repetitionTime){
        view.editDiscountCodeRepetitionTime(discountCode,repetitionTime);
    }


    private Server server = null;

    @Override
    public void start(Server server) throws IOException {
        this.server = server;
        StringBuilder string = new StringBuilder();
        HashMap<String, DiscountCode> allDiscountCodes = ProgramManager.getProgramManagerInstance().getAllDiscountCodes();
        for (String discountCode : allDiscountCodes.keySet()) {
            string.append(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(discountCode).toString());
        }
        sendMessage(string.toString());
    }

    private void sendMessage(String message) throws IOException {
        server.sendMessage("04-" + message);
    }

    public void showDiscountCode(String data) throws IOException {
        HashMap<String,DiscountCode> discountCodeHashMap = ProgramManager.getProgramManagerInstance().getAllDiscountCodes();
        if(discountCodeHashMap.containsKey(data)) {
            DiscountCode tempDiscountCode = ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(data);
            sendMessage("id :" + tempDiscountCode.getId() + "\nStart Data :" + tempDiscountCode.getStart() + " \nEnd Date :" + tempDiscountCode.getEnd() + "\nRepetition Time :" + tempDiscountCode.getRepetitionTime() + "\nPercentage :" + tempDiscountCode.getPercentage());
        }else{
            sendMessage("incorrectCode");
        }
    }

    public void removeDiscountCode(String data) throws IOException {
        HashMap<String,DiscountCode> discountCodeHashMap = ProgramManager.getProgramManagerInstance().getAllDiscountCodes();
        if(discountCodeHashMap.containsKey(data)) {
            ProgramManager.getProgramManagerInstance().deleteDiscountCode(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(data));
        }else{
            sendMessage("incorrectCode");
        }
    }

    public void changeDataByCode(String data) throws IOException {
        HashMap<String,DiscountCode> discountCodeHashMap = ProgramManager.getProgramManagerInstance().getAllDiscountCodes();
        if(discountCodeHashMap.containsKey(data)) {
            DiscountCode tempDiscountCode = ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(data.split("---")[0]);
            //TODO check data validation
            //code---startDate---endDate---percentage---repetitionTime
            tempDiscountCode.setStart(ProgramManager.getProgramManagerInstance().parsingStringToDate(data.split("---")[1]));
            tempDiscountCode.setEnd(ProgramManager.getProgramManagerInstance().parsingStringToDate(data.split("---")[2]));
            tempDiscountCode.setPercentage(Integer.parseInt(data.split("---")[3]));
            tempDiscountCode.setRepetitionTime(Integer.parseInt(data.split("---")[4]));
        }else{
            sendMessage("incorrectCode");
        }

    }

     */
}
