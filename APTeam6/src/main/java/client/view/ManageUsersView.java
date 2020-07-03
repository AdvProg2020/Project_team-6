package client.view;

import server.controller.ProgramManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import server.model.account.Account;
import server.model.account.Buyer;
import server.model.account.Manager;
import server.model.account.Seller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ManageUsersView {
    public ManageUsersView() {
        System.out.println("=== Manage Users Menu");
    }

    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.matches("view \\S+")) {
                return command;
            } else if (command.equals("help")) {
                showHelp();
            } else if (command.matches("delete user \\S+")) {
                return command;
            } else if (command.equals("back")) {
                return command;
            } else {
                System.out.println("Invalid command");
            }

        }
    }

    private void showHelp() {
        System.out.println("List of commands:\n\tclient.view [username]\n\tdelete user [username]");
    }

    public void giveOutput(String message) {
        System.out.println(message);
    }


    //---------------

    public void manageUsers(PersonalInfoMenuView personalInfoMenuView) {
        Stage stage = new Stage();
        Button viewUser = new Button("View user");
        Button deleteUser = new Button("Delete user");
        Button createManager = new Button("Create manager");
        Button back = new Button("Back");

        VBox vBox = new VBox(5);
        vBox.setStyle("-fx-background-color: #00ffa6");
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(viewUser, deleteUser, createManager, back);

        Scene scene = new Scene(vBox, 250, 450);
        stage.setScene(scene);
        stage.show();

        viewUser.setOnAction(actionEvent -> {
            stage.close();
            viewUser(personalInfoMenuView);
        });

        deleteUser.setOnAction(actionEvent -> {
            stage.close();
            deleteUser(personalInfoMenuView);
        });

        createManager.setOnAction(actionEvent -> {
            stage.close();
            try {
                createManager(personalInfoMenuView);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
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
    }

    public void createManager(PersonalInfoMenuView personalInfoMenuView) throws FileNotFoundException {
        Stage window = new Stage();
        window.setTitle("Create manager");
        window.getIcons().add(new Image(new FileInputStream("src/main/java/client/view/pictures/icon.png")));
        VBox pane = new VBox(11);
        pane.setAlignment(Pos.CENTER);

        Label usernameLabel = new Label("This username already exist!");
        Label usernameLabel2 = new Label("write a username here");
        usernameLabel.setTextFill(Color.RED);
        usernameLabel2.setTextFill(Color.RED);
        usernameLabel.setVisible(false);
        usernameLabel2.setVisible(false);
        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("Username");

        Label passwordLabel = new Label("please fill this field ");
        passwordLabel.setTextFill(Color.RED);
        passwordLabel.setVisible(false);
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Label firstNameLabel = new Label("write a first name here");
        firstNameLabel.setTextFill(Color.RED);
        firstNameLabel.setVisible(false);
        TextField firstNameTextField = new TextField();
        firstNameTextField.setPromptText("FirstName");

        Label lastNameLabel = new Label("write a last name here");
        lastNameLabel.setTextFill(Color.RED);
        lastNameLabel.setVisible(false);
        TextField lastNameTextField = new TextField();
        lastNameTextField.setPromptText("LastName");

        Label emailAddressLabel = new Label("write an email here");
        emailAddressLabel.setTextFill(Color.RED);
        emailAddressLabel.setVisible(false);
        TextField emailTextField = new TextField();
        emailTextField.setPromptText("Email");

        Label phoneNumberLabel = new Label("write a PhoneNumber");
        phoneNumberLabel.setTextFill(Color.RED);
        phoneNumberLabel.setVisible(false);
        TextField phoneNumberTextField = new TextField();
        phoneNumberTextField.setPromptText("e.g. 09123456789");

        Button create = new Button("Create manager");
        pane.getChildren().addAll(usernameLabel, usernameLabel2, usernameTextField, passwordLabel, passwordField, firstNameLabel, firstNameTextField, lastNameLabel, lastNameTextField, emailAddressLabel, emailTextField, phoneNumberLabel, phoneNumberTextField, create);
        window.setScene(new Scene(pane, 350, 550));
        window.show();

        window.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            try {
                new Exit().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        create.setOnAction(actionEvent -> {
            //check field data:
            usernameLabel2.setVisible(usernameTextField.getText().equals(""));
            passwordLabel.setVisible(passwordField.getText().equals(""));
            firstNameLabel.setVisible(firstNameTextField.getText().equals(""));
            lastNameLabel.setVisible(lastNameTextField.getText().equals(""));
            emailAddressLabel.setVisible(emailTextField.getText().equals(""));
            phoneNumberLabel.setVisible(phoneNumberTextField.getText().equals(""));
            phoneNumberLabel.setVisible(!phoneNumberTextField.getText().matches("[0-9]+"));
            //----------------

            if (!(usernameTextField.getText().equals("") || passwordField.getText().equals("") || firstNameTextField.getText().equals("") || lastNameTextField.getText().equals("") || emailTextField.getText().equals("") || phoneNumberTextField.getText().equals("") || !phoneNumberTextField.getText().matches("[0-9]+"))) {

                new Manager(usernameTextField.getText(), passwordField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(), phoneNumberTextField.getText());

                try {
                    new Alert().showAlert("Account created!", "Ok", 3, personalInfoMenuView);
                    window.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }

    public void deleteUser(PersonalInfoMenuView personalInfoMenuView) {
        Stage stage = new Stage();
        TextField username = new TextField();
        username.setPromptText("Username");
        Button delete = new Button("Delete");
        Button back = new Button("Back");

        VBox vBox = new VBox(7);
        vBox.setAlignment(Pos.BOTTOM_CENTER);
        vBox.getChildren().addAll(username, delete, back);

        Scene scene = new Scene(vBox, 300, 450);
        stage.setScene(scene);
        stage.show();

        delete.setOnAction(actionEvent -> {
            if (username.getText().equals("")) {
                try {
                    new Alert().showAlert("Please fill username text field", "Ok", 0, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                if (ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(username.getText())) {
                    stage.close();
                    ProgramManager.getProgramManagerInstance().deleteAccount(username.getText());
                    try {
                        new Alert().showAlert("Deleted!", "Ok", 3, personalInfoMenuView);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        new Alert().showAlert("There is no user with this username. Please select another", "Ok", 0, null);
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
            manageUsers(personalInfoMenuView);
        });
    }

    public void viewUser(PersonalInfoMenuView personalInfoMenuView) {
        Stage stage = new Stage();
        TextField username = new TextField();
        username.setPromptText("Username");
        Button view = new Button("client/view");
        Button back = new Button("Back");

        VBox vBox = new VBox(6);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(username, view, back);

        Scene scene = new Scene(vBox, 250, 450);
        stage.setScene(scene);
        stage.show();

        view.setOnAction(actionEvent -> {
            if (username.getText().equals("")) {
                try {
                    new Alert().showAlert("Please fill username text field", "Ok", 0, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                if (ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(username.getText())) {
                    stage.close();
                    try {
                        viewUserWithUsername(username.getText(), personalInfoMenuView);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        new Alert().showAlert("There is no user with this username. Please search another", "Ok", 0, null);
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
            manageUsers(personalInfoMenuView);
        });
    }

    public void viewUserWithUsername(String username, PersonalInfoMenuView personalInfoMenuView) throws FileNotFoundException {
        Stage stage = new Stage();
        stage.setTitle(username + " Information");
        stage.getIcons().add(new Image(new FileInputStream("src/main/java/client/view/pictures/icon.png")));
        VBox pane = new VBox();
        pane.setAlignment(Pos.CENTER);

        Account user = ProgramManager.getProgramManagerInstance().getAccountByUsername(username);

        Label credit = new Label("Credit:");
        Label credit2 = new Label("");
        if (2 == user.getRole()) {
            Seller seller = (Seller) user;
            credit2.setText("  $ " + seller.getCredit());
        } else if (user.getRole() == 1) {
            Buyer buyer = (Buyer) user;
            credit2.setText("  $ " + buyer.getCredit());
        } else {
            credit.setVisible(false);
            credit2.setVisible(false);
        }

        Label company = new Label("Company:");
        Label company2 = new Label();
        if (user.getRole() == 2) {
            Seller seller = (Seller) user;
            company2.setText(seller.getCompanyName());
        } else {
            company.setVisible(false);
            company2.setVisible(false);
        }


        Label role = new Label("role is : ");
        Label role2 = new Label("");
        if (user.getRole() == 1) {
            role2.setText("Buyer");
        } else if (user.getRole() == 2) {
            role2.setText("Seller");
        } else {
            role2.setText("Manager");
        }


        Label usernameLabel = new Label("Username");
        TextField usernameTextField = new TextField();
        usernameTextField.setEditable(false);
        usernameTextField.setText(user.getUsername());

        Label password = new Label("Password");
        TextField passwordField = new TextField();
        passwordField.setEditable(false);
        passwordField.setText(user.getPassword());

        Label firstName = new Label("FirstName");
        TextField firstNameTextField = new TextField();
        firstNameTextField.setEditable(false);
        firstNameTextField.setText(user.getFirstName());

        Label lastName = new Label("LastName");
        TextField lastNameTextField = new TextField();
        lastNameTextField.setEditable(false);
        lastNameTextField.setText(user.getLastName());

        Label email = new Label("Email");
        TextField emailTextField = new TextField();
        emailTextField.setEditable(false);
        emailTextField.setText(user.getEmailAddress());

        Label phone = new Label("PhoneNumber");
        TextField phoneNumberTextField = new TextField();
        phoneNumberTextField.setEditable(false);
        phoneNumberTextField.setText(user.getPhoneNumber());

        Button back = new Button("Back");


        if (user.getRole() == 1) {
            pane.getChildren().addAll(role, role2, credit, credit2, usernameLabel, usernameTextField,
                    password, passwordField, firstName, firstNameTextField, lastName,
                    lastNameTextField, email, emailTextField,
                    phone, phoneNumberTextField, back);
        } else if (user.getRole() == 2) {
            pane.getChildren().addAll(role, role2, usernameLabel, usernameTextField,
                    password, passwordField, firstName, firstNameTextField, lastName,
                    lastNameTextField, company, company2, email, emailTextField,
                    phone, phoneNumberTextField, back);
        } else if (user.getRole() == 3) {
            pane.getChildren().addAll(role, role2, credit, credit2, usernameLabel, usernameTextField,
                    password, passwordField, firstName, firstNameTextField, lastName,
                    lastNameTextField, email, emailTextField, phone,
                    phoneNumberTextField, back);
        }


        stage.setScene(new Scene(pane, 350, 600));
        stage.show();

        back.setOnAction(actionEvent -> {
            stage.close();
            try {
                this.manageUsers(personalInfoMenuView);
            } catch (Exception e) {
                e.printStackTrace();
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
    }

}
