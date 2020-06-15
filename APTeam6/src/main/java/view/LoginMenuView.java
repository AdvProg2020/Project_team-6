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
import javafx.stage.Stage;

public class LoginMenuView extends Application {
    public LoginMenuView() {
        System.out.println("=== Login/register menu");
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
            //TODO
        });

        back.setOnAction(actionEvent -> {
            //TODO
        });

    }

    public void register(){
        Stage stage = new Stage();
        VBox vBox = new VBox(10);

        Button buyer = new Button("register as buyer");
        Button seller = new Button("register as seller");
        Button back = new Button("back");

        buyer.setOnAction(actionEvent -> {
            //TODO
        });

        seller.setOnAction(actionEvent -> {
            //TODO
        });

        back.setOnAction(actionEvent -> {
            //TODO
        });

        vBox.getChildren().addAll(buyer,seller,back);
        Scene scene = new Scene(vBox,200,300);
        vBox.setAlignment(Pos.CENTER);

        stage.setScene(scene);
        stage.setTitle("Register");
        stage.show();

    }

    public void loginPanel() {
        Stage stage = new Stage();
        VBox vBox = new VBox(10);

        Label doesntExistUsername = new Label("Username doesnt exist");
        doesntExistUsername.setVisible(false);

        Label passwordFalse = new Label("Password is incorrect");
        passwordFalse.setVisible(false);

        Label usernameIsNull = new Label("please fill this field");
        usernameIsNull.setVisible(false);

        TextField usernameTextField = new TextField();

        Label passwordIsNull = new Label("please fill this field");
        passwordIsNull.setVisible(false);

        PasswordField passwordField = new PasswordField();

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

            if(!(usernameTextField.getText().equals("")||passwordField.getText().equals(""))) {
                if (ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(usernameTextField.getText())) {
                    if(ProgramManager.getProgramManagerInstance().getAccountByUsername(usernameTextField.getText()).checkPassword(passwordField.getText())){
                        ProgramManager.getProgramManagerInstance().loginSuccessful(ProgramManager.getProgramManagerInstance().getAccountByUsername(usernameTextField.getText()));
                        try {
                            stage.close();
                            new Alert().showAlert("login successful","Ok",2);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        passwordFalse.setVisible(true);
                    }
                }else{
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
