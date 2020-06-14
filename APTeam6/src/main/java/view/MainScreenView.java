package view;

import controller.MainScreen;
import controller.ProgramManager;
import controller.buyerPanels.BuyerUserPanel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.account.Manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

public class MainScreenView extends Application {
    private static MainScreenView mainScreenViewInstance = null;
    private boolean managerCreated = false;

    public static MainScreenView getMainScreenViewInstance() {
        if (mainScreenViewInstance == null)
            mainScreenViewInstance = new MainScreenView();
        return mainScreenViewInstance;
    }

    private void showHelp() {
        System.out.println("List of commands:" +
                "\n\tlogin menu" +
                "\n\tproducts" +
                "\n\texit");
    }

    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.equalsIgnoreCase("exit")) {
                return command;
            } else if (command.equalsIgnoreCase("help")) {
                showHelp();
            } else if (command.equalsIgnoreCase("login menu")) {
                return command;
            } else {
                System.out.println("Invalid command");
            }
            if (ProgramManager.getProgramManagerInstance().isAnyoneLoggedIn()) {
                byte role = ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUser().getRole();
                if (role == 1)
                    BuyerUserPanel.getBuyerUserPanelInstance().start();
            }
        }
    }


    public void giveOutput(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        launch(args);
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------------------------
    //  Graphical menu :

    @Override
    public void start(Stage stage) throws Exception {
        loadingPage(stage);
    }

    private void loadingPage(Stage primaryStage) throws FileNotFoundException {
        Label percentOfProgress = new Label("0%");

        ImageView logo = new ImageView();
        ImageView logo2 = new ImageView();
        logo.setImage(new Image(new FileInputStream("src/main/java/view/pictures/loading/loading (15).gif")));
        logo2.setImage(new Image(new FileInputStream("src/main/java/view/pictures/loading/loading (10).gif")));

        logo.setFitWidth(450);
        logo.setFitHeight(330);
        logo2.setFitHeight(150);
        logo2.setPreserveRatio(true);
        VBox root2 = new VBox(1);
        Label space = new Label("+");
        space.setFont(new Font(30));
        percentOfProgress.setFont(new Font(30));
        space.setVisible(false);
        root2.setAlignment(Pos.CENTER);
        root2.getChildren().addAll(logo, logo2, space, percentOfProgress);
        root2.setBackground(new Background(new BackgroundFill(new Color(0.964, 0.964, 0.964, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        primaryStage.setTitle(ProgramManager.getProgramManagerInstance().PROGRAM_NAME.toUpperCase());
        primaryStage.getIcons().add(new Image(new FileInputStream("src/main/java/view/pictures/icon.png")));
        primaryStage.setScene(new Scene(root2, 450, 700));

        primaryStage.show();
        loadingPercent(primaryStage, percentOfProgress);


    }

    private void loadingPercent(Stage primaryStage, Label percentOfProgress) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> percentOfProgress.setText("10%"));
            }
        }, 500);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> percentOfProgress.setText("20%"));
            }
        }, 1000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> percentOfProgress.setText("50%"));
            }
        }, 2000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> percentOfProgress.setText("75%"));
            }
        }, 3000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> percentOfProgress.setText("90%"));
            }
        }, 4300);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> percentOfProgress.setText("95%"));
            }
        }, 6100);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> percentOfProgress.setText("100%"));
            }
        }, 6950);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    primaryStage.close();
                    try {
                        mainScreenPage(new Stage());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                });
            }
        }, 500);
    }

    public void mainScreenPage(Stage window) throws FileNotFoundException {
        if (ProgramManager.getProgramManagerInstance().existManager || managerCreated) {
            window.setTitle("HOME");
            window.getIcons().add(new Image(new FileInputStream("src/main/java/view/pictures/icon.png")));
            VBox pane = new VBox(15);

            Button account = new Button("ناحیه کاربری");
            Button products = new Button("محصولات");
            Button offs = new Button("حراج ها");

            account.setOnAction(actionEvent -> {
                if (ProgramManager.getProgramManagerInstance().isAnyoneLoggedIn()) {
                    try {
                        new PersonalInfoMenuView().start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        new LoginMenuView().start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                window.close();
            });

            pane.setBackground(new Background(new BackgroundImage(new Image(new FileInputStream("src/main/java/view/pictures/icon.png")), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(400, 500, false, false, true, false))));
            pane.getChildren().addAll(account, products, offs);
            pane.setAlignment(Pos.CENTER);
            window.setScene(new Scene(pane, 400, 500));
            window.show();
        } else {
            window.setTitle("Create new manager");
            window.getIcons().add(new Image(new FileInputStream("src/main/java/view/pictures/icon.png")));
            VBox pane = new VBox(10);
            pane.setAlignment(Pos.CENTER);

            Label usernameLabel = new Label("This username already exist!");
            Label usernameLabel2 = new Label("write your username here");
            usernameLabel.setVisible(false);
            usernameLabel2.setVisible(false);
            TextField usernameTextField = new TextField();
            usernameTextField.setPromptText("Username");

            Label passwordLabel = new Label("این فیلد نباید خالی باشد");
            passwordLabel.setVisible(false);
            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Password");

            Label firstNameLabel = new Label("write your first name here");
            firstNameLabel.setVisible(false);
            TextField firstNameTextField = new TextField();
            firstNameTextField.setPromptText("FirstName");

            Label lastNameLabel = new Label("write your last name here");
            lastNameLabel.setVisible(false);
            TextField lastNameTextField = new TextField();
            lastNameTextField.setPromptText("LastName");

            Label emailAddressLabel = new Label("write your email here");
            emailAddressLabel.setVisible(false);
            TextField emailTextField = new TextField();
            emailTextField.setPromptText("Email");

            Label phoneNumberLabel = new Label("write a PhoneNumber");
            phoneNumberLabel.setVisible(false);
            TextField phoneNumberTextField = new TextField();
            phoneNumberTextField.setPromptText("e.g. 09123456789");

            Button create = new Button("Create");
            pane.getChildren().addAll(usernameLabel, usernameLabel2, usernameTextField, passwordLabel, passwordField, firstNameLabel, firstNameTextField, lastNameLabel, lastNameTextField, emailAddressLabel, emailTextField, phoneNumberLabel, phoneNumberTextField, create);
            window.setScene(new Scene(pane, 400, 500));
            window.show();

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

                        managerCreated = true;
                        new Alert().showAlert("Account created!", "Ok", 1);
                        window.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            });
        }
    }

}
