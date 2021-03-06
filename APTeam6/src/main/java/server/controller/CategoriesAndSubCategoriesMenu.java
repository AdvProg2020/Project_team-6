package server.controller;

import server.Server;
import server.model.product.Category;
import server.model.product.Comment;
import server.model.product.Product;
import server.model.product.SubCategory;
import client.view.old.CategoriesAndSubCategoriesMenuView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class CategoriesAndSubCategoriesMenu implements Parent {
    private Server server = null;

    @Override
    public void start(Server server) throws IOException {
        this.server = server;
        String message = ((Byte)server.getCurrentlyLoggedInUsers().getRole()).toString();
        for (int i = 0; i < allCategoriesArrayList.size(); i++) {
            message = message + "---" + i + ". " + allCategoriesArrayList.get(i);
        }
        sendMessage(message);
    }

    private void sendMessage(String message) throws IOException {
        server.sendMessage(message);
        //server.sendMessage("12-" + message);
    }

    ///////////////////////////////////////////
    private CategoriesAndSubCategoriesMenuView view;
    private Category currentCategory = null;
    private SubCategory currentSubCategory = null;

    private ArrayList<Category> allCategoriesArrayList;
    private ArrayList<SubCategory> allSubCategoriesArrayList;
    private ArrayList<Product> allProductsArrayList;

    public void getAdditionalInformationCategorySubCategory() throws IOException {
        if(currentCategory!=null && currentSubCategory!=null){
            StringBuilder result = new StringBuilder(currentCategory.getName() + "---" + currentSubCategory.getName() + "@");
            for (String additionalAttribute : currentCategory.getAdditionalAttributes()) {
                result.append("---").append(additionalAttribute);
            }
            result.append("@");
            for (String additionalAttribute : currentSubCategory.getAdditionalAttributes()) {
                result.append("---").append(additionalAttribute);
            }
            sendMessage(result.toString());
        }else{
            sendMessage("category or subCategory is null");
        }
    }

    private void updateCategoriesArrayList() {
        allCategoriesArrayList = new ArrayList<>(ProgramManager.getProgramManagerInstance().getAllCategories());

        allCategoriesArrayList = categorySort(allCategoriesArrayList);
    }

    private void updateSubCategoriesArrayList() {
        allSubCategoriesArrayList = new ArrayList<>(currentCategory.getAllSubCategories());

        allSubCategoriesArrayList = subCategorySort(allSubCategoriesArrayList);
    }

    private void updateProductsArrayList() {
        ArrayList<Integer> productIds = currentSubCategory.getAllProductIds();
        allProductsArrayList = new ArrayList<>();
        ProgramManager programManager = ProgramManager.getProgramManagerInstance();
        for (Integer id : productIds) {
            allProductsArrayList.add(programManager.getProductById(id));
        }
        Product.sortProducts(1, allProductsArrayList);
    }


    ///////////////////////////////////
    public void editCategory(int index, String newName) {
        if (server.getCurrentlyLoggedInUsers().getRole() == 3) {
            Category category = allCategoriesArrayList.get(index);
            category.setName(newName);
            updateCategoriesArrayList();
            try {
                sendMessage("edited");
            } catch (IOException e) {
                System.err.println("error occurred");
            }
        }
    }

    public void editSubCategory(int index, String newName) {
        if (server.getCurrentlyLoggedInUsers().getRole() == 3) {
            SubCategory subCategory = allSubCategoriesArrayList.get(index);
            subCategory.setName(newName);
            updateSubCategoriesArrayList();
            try {
                sendMessage("edited");
            } catch (IOException e) {
                System.err.println("error occurred");
            }
        }
    }

    public void addCategory(String Data) throws IOException {
        if (server.getCurrentlyLoggedInUsers().getRole() == 3) {
            if (!ProgramManager.getProgramManagerInstance().getAllCategories().contains(ProgramManager.getProgramManagerInstance().getCategoryByName(Data.split("---")[0]))) {
                ArrayList<String> additionalAttributes = new ArrayList<>();
                String[] dataSplit = Data.split("---");
                for (int i = 1; i < dataSplit.length; i++) {
                    additionalAttributes.add(dataSplit[i]);
                }
                allCategoriesArrayList.add(new Category(dataSplit[0], additionalAttributes));
                updateCategoriesArrayList();
                sendMessage("Added");
            } else
                sendMessage("duplicateCategory");
        }
    }

    public void addSubCategory(String Data) {
        if (server.getCurrentlyLoggedInUsers().getRole() == 3) {
            if (!currentCategory.getAllSubCategories().contains(currentCategory.getSubCategoryByName(Data.split("---")[0]))) {
                ArrayList<String> additionalAttributes = new ArrayList<>();
                String[] dataSplit = Data.split("---");
                for (int i = 1; i < dataSplit.length; i++) {
                    additionalAttributes.add(dataSplit[i]);
                }
                allSubCategoriesArrayList.add(new SubCategory(dataSplit[0], additionalAttributes));
                updateSubCategoriesArrayList();
                try {
                    sendMessage("Added");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    sendMessage("duplicateSubCategory");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void removeCategory(int index) {
        if (server.getCurrentlyLoggedInUsers().getRole() == 3) {
            allCategoriesArrayList.remove(index);
            updateCategoriesArrayList();
            try {
                sendMessage("removed");
            } catch (IOException e) {
                System.err.println("error occurred");
            }
        }
    }

    public void removeSubCategory(int index) {
        if (server.getCurrentlyLoggedInUsers().getRole() == 3) {
            allSubCategoriesArrayList.remove(index);
            updateSubCategoriesArrayList();
            try {
                sendMessage("removed");
            } catch (IOException e) {
                System.err.println("error occurred");
            }
        }
    }

    public void openCategory(int index) {
        Category tempCategory = allCategoriesArrayList.get(index);
        currentCategory = tempCategory;
        try {
            String categoriesMessage = "";
            for (SubCategory allSubCategory : currentCategory.getAllSubCategories()) {
                categoriesMessage = categoriesMessage + allSubCategory.getName() + "---";
            }
            categoriesMessage += "@";
            for (String additionalAttribute : currentCategory.getAdditionalAttributes()) {
                categoriesMessage = categoriesMessage + additionalAttribute + "---";
            }
            sendMessage(categoriesMessage);
        } catch (IOException e) {
            System.err.println("error occurred");
        }
    }
    public void openSubCategory(int index) {
        SubCategory tempSubCategory = allSubCategoriesArrayList.get(index);
        currentSubCategory = tempSubCategory;
        try {
            String subCategoriesMessage = "";
            for (Integer allProductId : currentSubCategory.getAllProductIds()) {
                subCategoriesMessage = subCategoriesMessage + ProgramManager.getProgramManagerInstance().getProductById(allProductId).getName() + "---";
            }
            for (String additionalAttribute : currentSubCategory.getAdditionalAttributes()) {
                subCategoriesMessage = subCategoriesMessage + additionalAttribute + "---";
            }
            sendMessage(subCategoriesMessage);
        } catch (IOException e) {
            System.err.println("error occurred");
        }
    }
    public void openProduct(int index){
        Product tempProduct = allProductsArrayList.get(index);
        try {
            //name---categoryName---SubCategoryName---description---price---average---comments---addition
            String message = tempProduct.getName() + "---" + tempProduct.getCategoryName() + "---" + tempProduct.getSubCategoryName() + "---" + tempProduct.getDescription() + "---" + tempProduct.getPrice() + "---";
            StringBuilder comments = new StringBuilder();
            for (Comment comment : tempProduct.getComments()) {
                comments.append(comment.getUsername()).append(": ").append(comment.getText()).append("===");
            }
            StringBuilder attributes = new StringBuilder();
            for (String key : tempProduct.getCategoryAdditionalInfo().keySet()) {
                attributes.append(key).append(": ").append(tempProduct.getCategoryAdditionalInfo().get(key)).append("===");
            }
            for (String key : tempProduct.getSubCategoryAdditionalInfo().keySet()) {
                attributes.append(key).append(": ").append(tempProduct.getSubCategoryAdditionalInfo().get(key)).append("===");
            }
            sendMessage(message + tempProduct.getAverageScore() + comments + "---" + attributes);
        } catch (IOException e) {
            System.err.println("error occurred");
        }
    }
    public void addToBuyBasket(int index,int count){
        Product tempProduct = allProductsArrayList.get(index);
        try {
            server.addToCurrentBuyBasket(tempProduct,count);
            sendMessage("added to buy basket");
        } catch (IOException e) {
            System.err.println("error occurred");
        }

    }

    public ArrayList<Category> categorySort(ArrayList<Category> allCategoriesArrayList) {

        Collections.sort(allCategoriesArrayList);

        return allCategoriesArrayList;

    }

    public ArrayList<SubCategory> subCategorySort(ArrayList<SubCategory> allSubCategoriesArrayList) {

        Collections.sort(allSubCategoriesArrayList);

        return allSubCategoriesArrayList;

    }


}
