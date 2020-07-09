package client.view.old;

import server.controller.CategoriesAndSubCategoriesMenu;
import server.controller.ProgramManager;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import server.model.account.Account;
import server.model.account.Buyer;
import server.model.account.Seller;
import client.view.userPanel.ShowCartView;

import java.io.FileInputStream;

public class PersonalInfoMenuView extends Application {

    public PersonalInfoMenuView() {
        Account currentUser = ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUser();
        System.out.println("Your personal info is as follows:" +
                "\n\tUsername: " + currentUser.getUsername() +
                "\n\tFirst name: " + currentUser.getFirstName() +
                "\n\tLast name: " + currentUser.getLastName() +
                "\n\tEmail address: " + currentUser.getEmailAddress() +
                "\n\tTelephone num: " + currentUser.getPhoneNumber() +
                "\n\tRole: " + currentUser.getRole());
        if (currentUser.getRole() == 2) {
            System.out.println("\tCredit: " + ((Seller) currentUser).getCredit());
        } else if (currentUser.getRole() == 1) {
            System.out.println("\tCredit: " + ((Buyer) currentUser).getCredit());
        }
    }

    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.equals("help")) {
                showHelp();
            } else if (command.equals("back")) {
                return command;
            } else if (command.matches("edit (password|firstName|lastName|phoneNumber|email)")) {
                return command;
            } else {
                System.out.println("Invalid command");
            }
        }
    }

    private void showHelp() {
        System.out.println("List of commands:\n\tedit [password/firstName/lastName/phoneNumber/email]\n\tback");
    }

    public String getNewValueForField(String field) {
        System.out.println("Enter your new " + field);
        return Input.getInput();
    }

    //---------

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Personal Information");
        stage.getIcons().add(new Image(new FileInputStream("src/main/java/client/view/pictures/icon.png")));
        VBox pane = new VBox();
        pane.setAlignment(Pos.CENTER);
        Font arial12 = new Font("Arial" , 12);

        Account currentlyLoggedInUser = ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUser();

        Label credit = new Label("Credit");
        Label credit2 = new Label();
        if (currentlyLoggedInUser.getRole() == 2) {
            Seller seller = (Seller) currentlyLoggedInUser;
            credit2.setText(" $ " + seller.getCredit());
        } else if (currentlyLoggedInUser.getRole() == 1) {
            Buyer buyer = (Buyer) currentlyLoggedInUser;
            credit2.setText(" $ " + buyer.getCredit());
        } else {
            credit.setVisible(false);
            credit2.setVisible(false);
        }

        credit.setFont(arial12);
        credit.setTextFill(Color.DARKBLUE);
        credit2.setFont(arial12);
        credit2.setTextFill(Color.DARKBLUE);

        Label company = new Label("Company");
        Label company2 = new Label();
        if (currentlyLoggedInUser.getRole() == 2) {
            Seller seller = (Seller) currentlyLoggedInUser;
            company2.setText(seller.getCompanyName());
        } else {
            company.setVisible(false);
            company2.setVisible(false);
        }

        company.setFont(arial12);
        company.setTextFill(Color.DARKBLUE);
        company2.setFont(arial12);
        company2.setTextFill(Color.DARKBLUE);


        Label role = new Label("your role is : ");
        Label role2 = new Label();
        if (currentlyLoggedInUser.getRole() == 1) {
            role2.setText("Buyer");
        } else if (currentlyLoggedInUser.getRole() == 2) {
            role2.setText("Seller");
        } else {
            role2.setText("Manager");
        }

        role.setFont(arial12);
        role.setTextFill(Color.DARKBLUE);
        role2.setFont(arial12);
        role2.setTextFill(Color.DARKBLUE);


        Label username = new Label("Username");
        Label usernameLabel = new Label("This username already exist!");
        Label usernameLabel2 = new Label("write your username here");
        usernameLabel.setVisible(false);
        usernameLabel2.setVisible(false);
        TextField usernameTextField = new TextField();
        usernameTextField.setEditable(false);
        usernameTextField.setPromptText("Username");
        usernameTextField.setText(currentlyLoggedInUser.getUsername());
        username.setFont(arial12);
        username.setTextFill(Color.DARKBLUE);

        Label password = new Label("Password");
        password.setFont(arial12);
        password.setTextFill(Color.DARKBLUE);
        Label passwordLabel = new Label("please fill this field");
        passwordLabel.setVisible(false);
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setText(currentlyLoggedInUser.getPassword());

        Label firstName = new Label("FirstName");
        firstName.setFont(arial12);
        firstName.setTextFill(Color.DARKBLUE);
        Label firstNameLabel = new Label("write your first name here");
        firstNameLabel.setVisible(false);
        TextField firstNameTextField = new TextField();
        firstNameTextField.setPromptText("FirstName");
        firstNameTextField.setText(currentlyLoggedInUser.getFirstName());

        Label lastName = new Label("LastName");
        lastName.setFont(arial12);
        lastName.setTextFill(Color.DARKBLUE);
        Label lastNameLabel = new Label("write your last name here");
        lastNameLabel.setVisible(false);
        TextField lastNameTextField = new TextField();
        lastNameTextField.setPromptText("LastName");
        lastNameTextField.setText(currentlyLoggedInUser.getLastName());

        Label email = new Label("Email");
        email.setFont(arial12);
        email.setTextFill(Color.DARKBLUE);
        Label emailAddressLabel = new Label("write your email here");
        emailAddressLabel.setVisible(false);
        TextField emailTextField = new TextField();
        emailTextField.setPromptText("Email");
        emailTextField.setText(currentlyLoggedInUser.getEmailAddress());

        Label phone = new Label("PhoneNumber");
        phone.setFont(arial12);
        phone.setTextFill(Color.DARKBLUE);
        Label phoneNumberLabel = new Label("write a PhoneNumber");
        phoneNumberLabel.setVisible(false);
        TextField phoneNumberTextField = new TextField();
        phoneNumberTextField.setPromptText("e.g. 09123456789");
        phoneNumberTextField.setText(currentlyLoggedInUser.getPhoneNumber());

        Button change = new Button("Change Information");
        change.setFont(arial12);
        change.setTextFill(Color.DARKBLUE);
        Button close = new Button("Back");
        close.setFont(arial12);
        close.setTextFill(Color.DARKBLUE);

        Button openCart = new Button("Go to cart");
        Button openBuyHistory = new Button("Go to buy history");
        Button showDiscountCode = new Button("Show my discount code");
        openCart.setFont(arial12);
        openCart.setTextFill(Color.DARKBLUE);
        openBuyHistory.setFont(arial12);
        openBuyHistory.setTextFill(Color.DARKBLUE);
        showDiscountCode.setFont(arial12);
        showDiscountCode.setTextFill(Color.DARKBLUE);

        if (currentlyLoggedInUser.getRole() != 1) {
            openCart.setVisible(false);
            openBuyHistory.setVisible(false);
            showDiscountCode.setVisible(false);
        }

        openCart.setOnAction(actionEvent -> {
            try {
                new ShowCartView().start(new Stage());
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        openBuyHistory.setOnAction(actionEvent -> {
            //TODO
        });

        showDiscountCode.setOnAction(actionEvent -> {
            try {
                new ShowDiscountCodeView().start(new Stage());
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        Button manageUsers = new Button("Manage users");
        Button manageAllProduct = new Button("Manage all product");
        Button createDiscountCode = new Button("Create discount code");
        Button viewDiscountCode = new Button("View Discount code");
        Button manageRequest = new Button("Manage request");
        Button manageCategories = new Button("Manage categories");
        Button SingleProductMenu = new Button("Single Product Menu");

        manageUsers.setFont(arial12);
        manageUsers.setTextFill(Color.DARKBLUE);
        manageAllProduct.setFont(arial12);
        manageAllProduct.setTextFill(Color.DARKBLUE);
        createDiscountCode.setFont(arial12);
        SingleProductMenu.setFont(arial12);
        createDiscountCode.setTextFill(Color.DARKBLUE);
        SingleProductMenu.setTextFill(Color.DARKBLUE);
        viewDiscountCode.setFont(arial12);
        viewDiscountCode.setTextFill(Color.DARKBLUE);
        manageRequest.setFont(arial12);
        manageRequest.setTextFill(Color.DARKBLUE);
        manageCategories.setFont(arial12);
        manageCategories.setTextFill(Color.DARKBLUE);

        if (currentlyLoggedInUser.getRole() != 3) {
            manageAllProduct.setVisible(false);
            manageRequest.setVisible(false);
            manageUsers.setVisible(false);
            createDiscountCode.setVisible(false);
            viewDiscountCode.setVisible(false);
            manageCategories.setVisible(false);
            SingleProductMenu.setVisible(false);
        }

        manageUsers.setOnAction(actionEvent -> {
            new ManageUsersView().manageUsers(this);
            stage.close();
        });

        manageAllProduct.setOnAction(actionEvent -> {
            new ManageAllProductsView().manageProducts(this);
            stage.close();
        });

        createDiscountCode.setOnAction(actionEvent -> {
            try {
                new CreateDiscountCodeView().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            stage.close();
        });

        viewDiscountCode.setOnAction(actionEvent -> {
            new ShowDiscountCodeView().manageDiscountCodePage(this);
            stage.close();
        });

        manageRequest.setOnAction(actionEvent -> {
            new ManageRequestsView().manageRequestPage(this);
            stage.close();
        });

        manageCategories.setOnAction(actionEvent -> {
            CategoriesAndSubCategoriesMenu.getInstance().start(this);
            stage.close();
        });
        SingleProductMenu.setOnAction(actionEvent -> {
            try {
                new Alert().showAlert("please select your product from category","ok",0,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //new SingleProductScreenView();
            //stage.close();
        });

        Button viewCompanyInfo = new Button("View company information");
        Button viewSalesHistory = new Button("View sales history");
        Button manageProducts = new Button("Manage products");
        Button addProduct = new Button("Add product");
        Button removeProduct = new Button("Remove Product");
        Button showCategories = new Button("Show categories");
        Button viewOffs = new Button("View offs");
        Button viewBalance = new Button("View balance");

        viewCompanyInfo.setFont(arial12);
        viewCompanyInfo.setTextFill(Color.DARKBLUE);
        viewSalesHistory.setFont(arial12);
        viewSalesHistory.setTextFill(Color.DARKBLUE);
        manageProducts.setFont(arial12);
        manageProducts.setTextFill(Color.DARKBLUE);
        addProduct.setFont(arial12);
        addProduct.setTextFill(Color.DARKBLUE);
        removeProduct.setFont(arial12);
        removeProduct.setTextFill(Color.DARKBLUE);
        showCategories.setFont(arial12);
        showCategories.setTextFill(Color.DARKBLUE);
        viewOffs.setFont(arial12);
        viewOffs.setTextFill(Color.DARKBLUE);
        viewBalance.setFont(arial12);
        viewBalance.setTextFill(Color.DARKBLUE);

        if (currentlyLoggedInUser.getRole() != 2) {
            viewBalance.setVisible(false);
            viewCompanyInfo.setVisible(false);
            viewSalesHistory.setVisible(false);
            manageProducts.setVisible(false);
            addProduct.setVisible(false);
            removeProduct.setVisible(false);
            showCategories.setVisible(false);
            viewOffs.setVisible(false);
        }

        viewBalance.setOnAction(actionEvent -> {
            //TODO
        });

        viewCompanyInfo.setOnAction(actionEvent -> {
            Seller seller = (Seller) currentlyLoggedInUser;
            try {
                new Alert().showAlert(seller.getCompanyName(), "Ok", 0, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        viewSalesHistory.setOnAction(actionEvent -> {
            //TODO
        });

        manageProducts.setOnAction(actionEvent -> {
            //TODO
        });

        addProduct.setOnAction(actionEvent -> {
            //TODO
        });

        removeProduct.setOnAction(actionEvent -> {
            //TODO
        });

        showCategories.setOnAction(actionEvent -> {
            CategoriesAndSubCategoriesMenu categoriesAndSubCategoriesMenu = CategoriesAndSubCategoriesMenu.getInstance();
            categoriesAndSubCategoriesMenu.start();
            stage.close();
        });

        viewOffs.setOnAction(actionEvent -> {
            //TODO
        });


        if (currentlyLoggedInUser.getRole() == 1) {
            pane.getChildren().addAll(role, role2, credit, credit2, username, usernameLabel, usernameLabel2, usernameTextField,
                    password, passwordLabel, passwordField, firstName, firstNameLabel, firstNameTextField, lastName,
                    lastNameLabel, lastNameTextField, email, emailAddressLabel, emailTextField,
                    phone, phoneNumberLabel, phoneNumberTextField, openCart, openBuyHistory, showDiscountCode, change, close);
        } else if (currentlyLoggedInUser.getRole() == 2) {
            pane.getChildren().addAll(role, role2, username, usernameLabel, usernameLabel2, usernameTextField,
                    password, passwordLabel, passwordField, firstName, firstNameLabel, firstNameTextField, lastName,
                    lastNameLabel, lastNameTextField, company, company2, email, emailAddressLabel, emailTextField,
                    phone, phoneNumberLabel, phoneNumberTextField, viewBalance, viewCompanyInfo, viewSalesHistory,
                    manageProducts, addProduct, removeProduct, showCategories, viewOffs, change, close);
        } else if (currentlyLoggedInUser.getRole() == 3) {
            pane.getChildren().addAll(role, role2, credit, credit2, username, usernameLabel, usernameLabel2, usernameTextField,
                    password, passwordLabel, passwordField, firstName, firstNameLabel, firstNameTextField, lastName,
                    lastNameLabel, lastNameTextField, email, emailAddressLabel, emailTextField, phone, phoneNumberLabel,
                    phoneNumberTextField, manageAllProduct, manageRequest, manageUsers, createDiscountCode, viewDiscountCode,
                    manageCategories, SingleProductMenu,change, close);
        }


        stage.setScene(new Scene(pane, 400, 750));
        stage.show();

        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            try {
                new Exit().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        close.setOnAction(actionEvent -> {
            stage.close();
            try {
                MainScreenView.getMainScreenViewInstance().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        change.setOnAction(actionEvent -> {

            //check field data:
            //usernameLabel2.setVisible(usernameTextField.getText().equals(""));
            //usernameLabel.setVisible(ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(usernameTextField.getText()));
            passwordLabel.setVisible(passwordField.getText().equals(""));
            firstNameLabel.setVisible(firstNameTextField.getText().equals(""));
            lastNameLabel.setVisible(lastNameTextField.getText().equals(""));
            emailAddressLabel.setVisible(emailTextField.getText().equals(""));
            phoneNumberLabel.setVisible(phoneNumberTextField.getText().equals(""));
            phoneNumberLabel.setVisible(!phoneNumberTextField.getText().matches("[0-9]+"));
            //----------------

            if (!(usernameTextField.getText().equals("") || passwordField.getText().equals("") || firstNameTextField.getText().equals("") || lastNameTextField.getText().equals("") || emailTextField.getText().equals("") || phoneNumberTextField.getText().equals("") || !phoneNumberTextField.getText().matches("[0-9]+"))) {

                if (ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(usernameTextField.getText())) {

                    currentlyLoggedInUser.setPassword(passwordField.getText());
                    currentlyLoggedInUser.setEmailAddress(emailTextField.getText());
                    currentlyLoggedInUser.setFirstName(firstNameTextField.getText());
                    currentlyLoggedInUser.setLastName(lastNameTextField.getText());
                    currentlyLoggedInUser.setPhoneNumber(phoneNumberTextField.getText());

                    try {
                        new Alert().showAlert("Information Changed", "Ok", 0, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.err.println("!!");
                }


            }

        });
    }
}
