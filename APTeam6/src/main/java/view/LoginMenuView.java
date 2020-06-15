package view;

import controller.LoginMenu;
import controller.ProgramManager;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.account.Account;
import model.account.Buyer;
import model.account.Seller;

public class LoginMenuView extends Application {
    public LoginMenuView() {
        System.out.println("=== Login/register menu");
    }

    private int destination = 0;

    public void start(int destination) throws Exception {
        this.destination = destination;
        start(new Stage());
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox vBox = new VBox(10);

        Button login = new Button("login");
        Button register = new Button("register");
        Button logout = new Button("logout");
        Button back = new Button("back");

        if (ProgramManager.getProgramManagerInstance().isAnyoneLoggedIn()) {
            login.setVisible(false);
            register.setVisible(false);
        } else {
            logout.setVisible(false);
        }

        vBox.getChildren().addAll(login, register, logout, back);
        stage.setScene(new Scene(vBox, 250, 350));
        stage.show();

        login.setOnAction(actionEvent -> {
            stage.close();
            loginPanel();
        });

        register.setOnAction(actionEvent -> {
            stage.close();
            register();
        });

        logout.setOnAction(actionEvent -> {
            ProgramManager.getProgramManagerInstance().logoutSuccessful();
            try {
                new Alert().showAlert("logout successful","Ok",2);
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        back.setOnAction(actionEvent -> {
            stage.close();
            if(destination==1){
                try {
                    MainScreenView.getMainScreenViewInstance().start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void register() {
        Stage stage = new Stage();
        VBox vBox = new VBox(10);

        Button buyer = new Button("register as buyer");
        Button seller = new Button("register as seller");
        Button back = new Button("back");

        buyer.setOnAction(actionEvent -> {
            stage.close();
            registerBuyer();
        });

        seller.setOnAction(actionEvent -> {
            stage.close();
            registerSeller();
        });

        back.setOnAction(actionEvent -> {
            try {
                stage.close();
                this.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        vBox.getChildren().addAll(buyer, seller, back);
        Scene scene = new Scene(vBox, 200, 300);
        vBox.setAlignment(Pos.CENTER);

        stage.setScene(scene);
        stage.setTitle("Register");
        stage.show();

    }

    public void registerSeller() {
        Stage window = new Stage();
        VBox vBox = new VBox(6);
        vBox.setAlignment(Pos.CENTER);

        Label username = new Label("Username");
        Label usernameLabel = new Label("This username already exist!");
        Label usernameLabel2 = new Label("write your username here");
        usernameLabel.setVisible(false);
        usernameLabel2.setVisible(false);
        usernameLabel.setTextFill(Color.RED);
        usernameLabel2.setTextFill(Color.RED);
        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("Username");

        Label password = new Label("Password");
        Label passwordLabel = new Label("please fill this field");
        passwordLabel.setVisible(false);
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordLabel.setTextFill(Color.RED);

        Label firstName = new Label("FirstName");
        Label firstNameLabel = new Label("write your first name here");
        firstNameLabel.setVisible(false);
        TextField firstNameTextField = new TextField();
        firstNameTextField.setPromptText("FirstName");
        firstNameLabel.setTextFill(Color.RED);

        Label lastName = new Label("LastName");
        Label lastNameLabel = new Label("write your last name here");
        lastNameLabel.setVisible(false);
        TextField lastNameTextField = new TextField();
        lastNameTextField.setPromptText("LastName");
        lastNameLabel.setTextFill(Color.RED);

        Label email = new Label("Email");
        Label emailAddressLabel = new Label("write your email here");
        emailAddressLabel.setVisible(false);
        TextField emailTextField = new TextField();
        emailTextField.setPromptText("Email");
        emailAddressLabel.setTextFill(Color.RED);

        Label phone = new Label("PhoneNumber");
        Label phoneNumberLabel = new Label("write a PhoneNumber");
        phoneNumberLabel.setVisible(false);
        TextField phoneNumberTextField = new TextField();
        phoneNumberTextField.setPromptText("e.g. 09123456789");
        phoneNumberLabel.setTextFill(Color.RED);

        Label company = new Label("Company");
        Label companyLabel = new Label("write your company name here");
        companyLabel.setVisible(false);
        TextField companyTextField = new TextField();
        companyTextField.setPromptText("Company");
        companyLabel.setTextFill(Color.RED);

        Button register = new Button("Register and wait for accept");
        Button cancel = new Button("Cancel");

        vBox.getChildren().addAll(username, usernameLabel, usernameLabel2, usernameTextField, password, passwordLabel, passwordField,
                firstName, firstNameLabel, firstNameTextField, lastName, lastNameLabel, lastNameTextField, email, emailAddressLabel, emailTextField,
                phone, phoneNumberLabel, phoneNumberTextField, company, companyLabel, companyTextField, register, cancel);

        Scene scene = new Scene(vBox, 400, 700);
        window.setScene(scene);
        window.setTitle("Register new Seller");
        window.show();

        cancel.setOnAction(actionEvent -> {
            window.close();
            try {
                this.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        register.setOnAction(actionEvent -> {
            //check field data:
            usernameLabel2.setVisible(usernameTextField.getText().equals(""));
            usernameLabel.setVisible(ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(usernameTextField.getText()));
            firstNameLabel.setVisible(firstNameTextField.getText().equals(""));
            passwordLabel.setVisible(passwordField.getText().equals(""));
            lastNameLabel.setVisible(lastNameTextField.getText().equals(""));
            emailAddressLabel.setVisible(emailTextField.getText().equals(""));
            phoneNumberLabel.setVisible(phoneNumberTextField.getText().equals(""));
            companyLabel.setVisible(companyTextField.getText().equals(""));
            phoneNumberLabel.setVisible(!phoneNumberTextField.getText().matches("[0-9]+"));
            //----------------

            if (!(usernameTextField.getText().equals("") || passwordField.getText().equals("") || firstNameTextField.getText().equals("") || lastNameTextField.getText().equals("") || emailTextField.getText().equals("") || phoneNumberTextField.getText().equals("") || companyTextField.getText().equals("") || !phoneNumberTextField.getText().matches("[0-9]+"))) {

                if (!ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(usernameTextField.getText())) {

                    new Seller(usernameTextField.getText(),passwordField.getText(),firstNameTextField.getText(),lastNameTextField.getText(),emailTextField.getText(),phoneNumberTextField.getText(),companyTextField.getText());

                    try {
                        new Alert().showAlert("Register successfully. Please wait for accept", "Ok", 2);
                        window.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });


    }

    public void registerBuyer() {
        Stage stage = new Stage();
        VBox vBox = new VBox(5);
        vBox.setAlignment(Pos.CENTER);

        Label username = new Label("Username");
        Label usernameLabel = new Label("This username already exist!");
        Label usernameLabel2 = new Label("write your username here");
        usernameLabel.setVisible(false);
        usernameLabel2.setVisible(false);
        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("Username");
        usernameLabel2.setTextFill(Color.RED);
        usernameLabel.setTextFill(Color.RED);

        Label password = new Label("Password");
        Label passwordLabel = new Label("please fill this field");
        passwordLabel.setVisible(false);
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordLabel.setTextFill(Color.RED);

        Label firstName = new Label("FirstName");
        Label firstNameLabel = new Label("write your first name here");
        firstNameLabel.setVisible(false);
        TextField firstNameTextField = new TextField();
        firstNameTextField.setPromptText("FirstName");
        firstNameLabel.setTextFill(Color.RED);

        Label lastName = new Label("LastName");
        Label lastNameLabel = new Label("write your last name here");
        lastNameLabel.setVisible(false);
        TextField lastNameTextField = new TextField();
        lastNameTextField.setPromptText("LastName");
        lastNameLabel.setTextFill(Color.RED);

        Label email = new Label("Email");
        Label emailAddressLabel = new Label("write your email here");
        emailAddressLabel.setVisible(false);
        TextField emailTextField = new TextField();
        emailTextField.setPromptText("Email");
        emailAddressLabel.setTextFill(Color.RED);

        Label phone = new Label("PhoneNumber");
        Label phoneNumberLabel = new Label("write a PhoneNumber");
        phoneNumberLabel.setVisible(false);
        TextField phoneNumberTextField = new TextField();
        phoneNumberTextField.setPromptText("e.g. 09123456789");
        phoneNumberLabel.setTextFill(Color.RED);

        Button register = new Button("Register");
        Button cancel = new Button("Cancel");

        vBox.getChildren().addAll(username, usernameLabel, usernameLabel2, usernameTextField, password, passwordLabel, passwordField,
                firstName, firstNameLabel, firstNameTextField, lastName, lastNameLabel, lastNameTextField, email, emailAddressLabel, emailTextField,
                phone, phoneNumberLabel, phoneNumberTextField, register, cancel);

        Scene scene = new Scene(vBox, 400, 700);
        stage.setScene(scene);
        stage.setTitle("Register new Buyer");
        stage.show();

        cancel.setOnAction(actionEvent -> {
            stage.close();
            try {
                this.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        register.setOnAction(actionEvent -> {
            //check field data:
            usernameLabel2.setVisible(usernameTextField.getText().equals(""));
            usernameLabel.setVisible(ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(usernameTextField.getText()));
            firstNameLabel.setVisible(firstNameTextField.getText().equals(""));
            passwordLabel.setVisible(passwordField.getText().equals(""));
            lastNameLabel.setVisible(lastNameTextField.getText().equals(""));
            emailAddressLabel.setVisible(emailTextField.getText().equals(""));
            phoneNumberLabel.setVisible(phoneNumberTextField.getText().equals(""));
            phoneNumberLabel.setVisible(!phoneNumberTextField.getText().matches("[0-9]+"));
            //----------------

            if (!(usernameTextField.getText().equals("") || passwordField.getText().equals("") || firstNameTextField.getText().equals("") || lastNameTextField.getText().equals("") || emailTextField.getText().equals("") || phoneNumberTextField.getText().equals("") || !phoneNumberTextField.getText().matches("[0-9]+"))) {

                if (!ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(usernameTextField.getText())) {

                    new Buyer(usernameTextField.getText(), passwordField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(), phoneNumberTextField.getText());

                    try {
                        new Alert().showAlert("Register successfully", "Ok", 2);
                        stage.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });


    }

    public void loginPanel() {
        Stage stage = new Stage();
        VBox vBox = new VBox(10);

        Label doesntExistUsername = new Label("Username doesnt exist");
        doesntExistUsername.setVisible(false);
        doesntExistUsername.setTextFill(Color.RED);

        Label passwordFalse = new Label("Password is incorrect");
        passwordFalse.setVisible(false);
        passwordFalse.setTextFill(Color.RED);

        Label usernameIsNull = new Label("please fill this field");
        usernameIsNull.setVisible(false);
        usernameIsNull.setTextFill(Color.RED);

        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("Username");


        Label passwordIsNull = new Label("please fill this field");
        passwordIsNull.setVisible(false);
        passwordIsNull.setTextFill(Color.RED);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button login = new Button("Login");
        Button close = new Button("Close");

        vBox.getChildren().addAll(doesntExistUsername, passwordFalse, usernameIsNull, usernameTextField, passwordIsNull, passwordField, login, close);


        close.setOnAction(actionEvent -> {
            try {
                stage.close();
                this.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        login.setOnAction(actionEvent -> {
            doesntExistUsername.setVisible(false);
            passwordFalse.setVisible(false);
            usernameIsNull.setVisible(usernameTextField.getText().equals(""));
            passwordIsNull.setVisible(passwordField.getText().equals(""));

            if (!(usernameTextField.getText().equals("") || passwordField.getText().equals(""))) {
                if (ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(usernameTextField.getText())) {
                    if (ProgramManager.getProgramManagerInstance().getAccountByUsername(usernameTextField.getText()).checkPassword(passwordField.getText())) {
                        ProgramManager.getProgramManagerInstance().loginSuccessful(ProgramManager.getProgramManagerInstance().getAccountByUsername(usernameTextField.getText()));
                        try {
                            stage.close();
                            new Alert().showAlert("login successful", "Ok", 2);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        passwordFalse.setVisible(true);
                    }
                } else {
                    doesntExistUsername.setVisible(true);
                }
            }
        });

        Scene scene = new Scene(vBox, 250, 450);
        stage.setScene(scene);
        stage.setTitle("login/logout/register");
        stage.show();

    }

    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.equals("help")) {
                showHelp();
                continue;
            }
            if (command.equals("back")) {
                return command;
            }
            if (ProgramManager.getProgramManagerInstance().isAnyoneLoggedIn()) {
                if (command.equals("logout")) {
                    return command;
                }
            } else if (command.matches("login \\S+ \\S+") || command.matches("create account (buyer|seller|manager) \\S+")) {
                return command;
            } else {
                System.out.println("Invalid command");
            }
        }
    }

    private void showHelp() {
        if (!ProgramManager.getProgramManagerInstance().isAnyoneLoggedIn())
            System.out.println("List of commands:\n\tlogin [username] [password]\n\tcreate account [role] [username]");
        else
            System.out.println("List of commands:\n\tlogout\n\tcreate account [role] [username]");
    }

    public void giveOutput(String message) {
        System.out.println(message);
    }

    public String[] getUserUsualData() {
        String[] data = new String[5];
        System.out.println("Enter your first name:");
        data[0] = Input.getInput();
        System.out.println("Enter your last name:");
        data[1] = Input.getInput();
        while (true) {
            System.out.println("Enter your phone number:");
            data[2] = Input.getInput();
            if (data[2].matches("[0-9]+")) {
                break;
            }
            System.out.println("Wrong phone number");
        }
        System.out.println("Enter your email address:");
        data[3] = Input.getInput();
        System.out.println("Enter your password:");
        data[4] = Input.getInput();
        return data;
    }

    public String getSellerCompany() {
        System.out.println("Enter your company name:");
        return Input.getInput();
    }
}
