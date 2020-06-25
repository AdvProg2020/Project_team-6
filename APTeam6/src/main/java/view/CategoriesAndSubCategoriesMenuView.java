package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.product.Category;
import model.product.Product;
import model.product.SubCategory;

import java.io.FileInputStream;
import java.util.ArrayList;

public class CategoriesAndSubCategoriesMenuView extends Application {
    //CategoriesAndSubCategoriesMenu categoriesAndSubCategoriesMenu = new CategoriesAndSubCategoriesMenu();
    public CategoriesAndSubCategoriesMenuView(){
        System.out.println("=== Categories menu");
/*
        ArrayList<Category> categories = (ArrayList<Category>) ProgramManager.getProgramManagerInstance().getAllCategories();
        if(categories.size() != 0) {
            for (Category category : categories) {
                System.out.println("\t" + category.getName());
            }
        }
        else{
            System.out.println("No Categories Yet!");
        }
*/
        ListView list = new ListView();
        vBox.getChildren().add(list);
        list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    /////////////////////////////////////////////////

    public String getInputCommandManagerCategory() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.matches("edit \\d+ \\S+") || command.matches("add \\d+") || command.matches("remove \\d+") || command.matches("open \\d+") || command.equals("back"))
                return command;
            else if (command.equals("help"))
                System.out.println("List of commands:\n\tadd [index]\n\tedit [index] [newName]\n\tremove [index]\n\topen [index]");
            else
                System.out.println("Invalid command");
        }
    }

    public String getInputCommandManagerProduct() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.matches("remove \\d+") || command.equals("back"))
                return command;
            else if (command.equals("help"))
                System.out.println("List of commands:\n\tremove [index]");
            else
                System.out.println("Invalid command");
        }
    }

    /////////////////////////////////////////////////

    public String getInputCommandSellerCategory() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.matches("open \\d+") || command.equals("back"))
                return command;
            else if (command.equals("help"))
                System.out.println("List of commands:\n\topen [index]");
            else
                System.out.println("Invalid command");
        }
    }

    public String getInputCommandSellerSubCategory() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.matches("addTo \\d+") || command.equals("back"))
                return command;
            else if (command.equals("help"))
                System.out.println("List of commands:\n\taddTo [index]");
            else
                System.out.println("Invalid command");
        }
    }

    /////////////////////////////////////////////////

    public String getInputCommandBuyer() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.matches("open \\d+") || command.equals("back"))
                return command;
            else if (command.equals("help"))
                System.out.println("List of commands:\n\topen [index]");
            else
                System.out.println("Invalid command");
        }
    }

    /////////////////////////////////////////////////

    public void showCategoriesList(ArrayList<Category> categories){
        System.out.println("List of Categories:");
        for (int i = 0, categoriesSize = categories.size(); i < categoriesSize; i++) {
            Category category = categories.get(i);
            System.out.println("\t" + i + ". " + category.getName());
        }
    }

    public void showSubCategoriesList(ArrayList<SubCategory> subCategories){
        System.out.println("List of SubCategories:");
        for (int i = 0, subCategoriesSize = subCategories.size(); i < subCategoriesSize; i++) {
            SubCategory subCategory = subCategories.get(i);
            System.out.println("\t" + i + ". " + subCategory.getName());
        }
    }

    public void showProductsList(ArrayList<Product> products){
        System.out.println("List of Products:");
        for (int i = 0, productsSize = products.size(); i < productsSize; i++) {
            Product product = products.get(i);
            System.out.println("\t" + i + ". " + product.getName());
        }
    }

    public void giveOutPut(String message){
        System.out.println(message);
    }

    // Graphical menu :
    // edit/add/remove/open
    /*public void manageCategories(PersonalInfoMenuView personalInfoMenuView){
        byte role = ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUserRole();
        //int subCategorySize = categoriesAndSubCategoriesMenu.getSubCategorySize();
//        TextField[] textFields = new TextField[subCategorySize];
//        Label[] labels = new Label[subCategorySize];
        Stage stage = new Stage();
        VBox vBox = new VBox(10);
        Label label = new Label("Enter Category Name");
        Label fill = new Label("please fill this field");
        fill.setVisible(false);
        fill.setTextFill(Color.RED);

        TextField categoryName = new TextField();
        categoryName.setPrefWidth(200);
        categoryName.setMaxWidth(200);
        categoryName.setMinWidth(200);

        Label nameLabel = new Label();
        TextField nameTextField = new TextField();

        nameLabel.setWrapText(true);
        Button open = new Button("Open");
        Button add = new Button("Add");
        Button edit = new Button("Edit");
        Button remove = new Button("Remove");
        Button back = new Button("Back");

        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(label, fill, categoryName, open, add, edit, remove, back, nameLabel,nameTextField);

        stage.setScene(new Scene(vBox, 400, 700));
        stage.setTitle("Manage Discounts");
        stage.show();

        edit.setOnAction(actionEvent -> {
            fill.setVisible(categoryName.getText().equals(""));
            fill.setVisible(!categoryName.getText().matches(".+"));
            if (!categoryName.getText().equals("") && categoryName.getText().matches(".+")) {
                if (ProgramManager.getProgramManagerInstance().getCategoryByName(categoryName.getText()) != null) {
                    nameLabel.setText("Enter your new name:");
                    nameTextField.setPromptText("Name");

                    //TODO

                    try {
                        new Alert().showAlert("", "Ok", 0, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        open.setOnAction(actionEvent -> {
            fill.setVisible(categoryName.getText().equals(""));
            fill.setVisible(!categoryName.getText().matches("\\.+"));
            if (!categoryName.getText().equals("") && categoryName.getText().matches("\\.+")) {
                if (ProgramManager.getProgramManagerInstance().getCategoryByName(categoryName.getText()) != null) {


                    //TODO
                    nameLabel.setText("");
                    categoryName.setText("");
                } else {
                    try {
                        new Alert().showAlert("", "Ok", 0, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        remove.setOnAction(actionEvent -> {
            fill.setVisible(categoryName.getText().equals(""));
            fill.setVisible(!categoryName.getText().matches("\\.+"));
            if (!categoryName.getText().equals("") && categoryName.getText().matches("\\.+")) {
                if (ProgramManager.getProgramManagerInstance().getCategoryByName(categoryName.getText()) != null) {

                    //TODO

                    nameLabel.setText("");
                    categoryName.setText("");
                    try {
                        new Alert().showAlert("", "Ok", 0, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {

                        //TODO

                        new Alert().showAlert("", "Ok", 0, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        edit.setOnAction(actionEvent -> {
            fill.setVisible(categoryName.getText().equals(""));
            fill.setVisible(!categoryName.getText().matches("\\.+"));
            if (!categoryName.getText().equals("") && categoryName.getText().matches(".+")) {
                if (ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(categoryName.getText()) != null) {
                    nameLabel.setText("Enter your new name:");
                    nameTextField.setPromptText("Name");
                    categoriesAndSubCategoriesMenu.edit();

                    startDateLabel.setText("Enter your new Start Date:");
                    startDateTextField.setPromptText("Start Date");
                    ShowDiscountCode.getShowDiscountCodeInstance().editDiscountCodeStartDate(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(categoryName.getText()), startDateTextField.getText());

                    endDateLabel.setText("Enter your new End Date:");
                    endDateTextField.setPromptText("End Date");
                    ShowDiscountCode.getShowDiscountCodeInstance().editDiscountCodeEndDate(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(categoryName.getText()), endDateTextField.getText());


                    percentageLabel.setText("Enter your new Percentage:");
                    percentageTextField.setPromptText("Percentage");
                    ShowDiscountCode.getShowDiscountCodeInstance().editDiscountCodePercentage(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(categoryName.getText()), Integer.parseInt(percentageTextField.getText()));


                    repetitionTimeLabel.setText("Enter your new Repetition Time:");
                    repetitionTimeTextField.setPromptText("RepetitionTime");
                    ShowDiscountCode.getShowDiscountCodeInstance().editDiscountCodeRepetitionTime(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(categoryName.getText()), Integer.parseInt(repetitionTimeTextField.getText()));
                    try {
                        new Alert().showAlert("DiscountCode with this Code doesn't exist", "Ok", 0, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        open.setOnAction(actionEvent -> {
            fill.setVisible(categoryName.getText().equals(""));
            fill.setVisible(!categoryName.getText().matches("\\.+"));
            if (!categoryName.getText().equals("") && categoryName.getText().matches("\\.+")) {
                if (ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(categoryName.getText()) != null) {
                    nameLabel.setText(viewDiscountCode1(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(categoryName.getText())));
                    startDateLabel.setText(viewDiscountCode2(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(categoryName.getText())));
                    endDateLabel.setText(viewDiscountCode3(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(categoryName.getText())));
                    percentageLabel.setText(viewDiscountCode4(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(categoryName.getText())));
                    repetitionTimeLabel.setText(viewDiscountCode5(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(categoryName.getText())));
                    nameLabel.setText("");
                    startDateLabel.setText("");
                    endDateLabel.setText("");
                    percentageLabel.setText("");
                    repetitionTimeLabel.setText("");
                    categoryName.setText("");
                } else {
                    try {
                        new Alert().showAlert("DiscountCode with this code doesnt exist", "Ok", 0, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        remove.setOnAction(actionEvent -> {
            fill.setVisible(categoryName.getText().equals(""));
            fill.setVisible(!categoryName.getText().matches("\\.+"));
            if (!categoryName.getText().equals("") && categoryName.getText().matches("\\.+")) {
                if (ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(categoryName.getText()) != null) {
                    removeDiscountCode(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(categoryName.getText()));
                    nameLabel.setText("");
                    categoryName.setText("");
                    try {
                        new Alert().showAlert("DiscountCode removed successfully!", "Ok", 0, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        new Alert().showAlert("Discount Code with this code doesnt exist", "Ok", 0, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            try {
                new Exit().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        back.setOnAction(actionEvent -> {
            stage.close();
            try {
                personalInfoMenuView.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }*/

    VBox vBox;

    @Override
    public void start(Stage stage) throws Exception {
        vBox = new VBox();
        stage.setTitle("Categories and SubCategories and product menu");
        stage.getIcons().add(new Image(new FileInputStream("src/main/java/view/pictures/icon.png")));
        Scene scene = new Scene(vBox,300,600);
        vBox.setAlignment(Pos.CENTER);
        stage.setScene(scene);
        stage.show();


        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            try {
                new Exit().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

}
