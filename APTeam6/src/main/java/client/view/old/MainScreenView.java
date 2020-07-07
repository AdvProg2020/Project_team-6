package client.view.old;

import server.controller.ProgramManager;
import server.controller.buyerPanels.BuyerUserPanel;
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
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import server.model.account.Manager;

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
        logo.setImage(new Image(new FileInputStream("src/main/java/client/view/pictures/loading/loading (15).gif")));
        logo2.setImage(new Image(new FileInputStream("src/main/java/client/view/pictures/loading/loading (10).gif")));

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
        root2.setBackground(new Background(new BackgroundFill(new Color(0.964F, 0.964F, 0.964F, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        primaryStage.setTitle(ProgramManager.getProgramManagerInstance().PROGRAM_NAME.toUpperCase());
        primaryStage.getIcons().add(new Image(new FileInputStream("src/main/java/client/view/pictures/icon.png")));
        primaryStage.setScene(new Scene(root2, 450, 700));

        primaryStage.show();
        loadingPercent(primaryStage, percentOfProgress);

        primaryStage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
        });
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
        }, 10);
    }

    public void mainScreenPage(Stage window) throws FileNotFoundException {
        if (ProgramManager.getProgramManagerInstance().existManager || managerCreated) {
            window.setTitle("HOME");
            window.getIcons().add(new Image(new FileInputStream("src/main/java/client/view/pictures/icon.png")));
            VBox pane = new VBox(15);


            WebView webView = new WebView();
            webView.setPrefWidth(350);
            webView.setMinWidth(350);
            webView.setMaxWidth(350);
            webView.setMaxHeight(35);
            webView.setMinHeight(35);
            webView.setPrefHeight(35);

            String data = "";
            StringBuilder sb = new StringBuilder(data);
            sb.append("<body style=\"background-color: Yellow\">");
            sb.append("<marquee behavior=\"scroll\" direction=\"left\" scrollamount=\"5\">welcome to our market</marquee>");
            sb.append("</body>");

            data = sb.toString();
            webView.getEngine().loadContent(data);


            Button account = new Button("User Panel");
            account.setFont(new Font("Arial", 20));
            account.setTextFill(Color.DARKBLUE);

            Button products = new Button("Products");
            products.setFont(new Font("Arial", 12));
            products.setTextFill(Color.DARKBLUE);

            Button offs = new Button("Offs");
            offs.setFont(new Font("Arial", 12));
            offs.setTextFill(Color.DARKBLUE);

            Button loginMenu = new Button("loginMenu");
            loginMenu.setFont(new Font("Arial", 12));
            loginMenu.setTextFill(Color.DARKBLUE);

            window.setOnCloseRequest(windowEvent -> {
                windowEvent.consume();
                try {
                    new Exit().start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            account.setOnAction(actionEvent -> {
                //ProgramManager.getProgramManagerInstance().loginSuccessful(ProgramManager.getProgramManagerInstance().getAccountByUsername("a"));
                if (ProgramManager.getProgramManagerInstance().isAnyoneLoggedIn()) {
                    try {
                        new PersonalInfoMenuView().start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        new LoginMenuView(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                window.close();
            });

            loginMenu.setOnAction(actionEvent -> {
                try {
                    window.close();
                    new LoginMenuView().start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            pane.setBackground(new Background(new BackgroundImage(new Image(new FileInputStream("src/main/java/client/view/pictures/icon.png")), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(400, 500, false, false, true, false))));
            pane.getChildren().addAll(webView, account, products, offs,loginMenu);
            pane.setAlignment(Pos.CENTER);
            window.setScene(new Scene(pane, 400, 500));
            window.show();
        } else {
            window.setTitle("Create new manager");
            window.getIcons().add(new Image(new FileInputStream("src/main/java/client/view/pictures/icon.png")));
            VBox pane = new VBox(10);
            pane.setAlignment(Pos.CENTER);

            Label label1 = new Label("Username : ");
            Label usernameLabel = new Label("This username already exist!");
            Label usernameLabel2 = new Label("write your username here");
            usernameLabel.setTextFill(Color.RED);
            usernameLabel2.setTextFill(Color.RED);
            usernameLabel.setVisible(false);
            usernameLabel2.setVisible(false);
            TextField usernameTextField = new TextField();
            usernameTextField.setPromptText("Username");


            Label label2 = new Label("Password : ");
            Label passwordLabel = new Label("please fill this field");
            passwordLabel.setTextFill(Color.RED);
            passwordLabel.setVisible(false);
            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Password");

            Label label3 = new Label("FirstName : ");
            Label firstNameLabel = new Label("write your first name here");
            firstNameLabel.setTextFill(Color.RED);
            firstNameLabel.setVisible(false);
            TextField firstNameTextField = new TextField();
            firstNameTextField.setPromptText("FirstName");

            Label label4 = new Label("LastName : ");
            Label lastNameLabel = new Label("write your last name here");
            lastNameLabel.setTextFill(Color.RED);
            lastNameLabel.setVisible(false);
            TextField lastNameTextField = new TextField();
            lastNameTextField.setPromptText("LastName");

            Label label5 = new Label("Email : ");
            Label emailAddressLabel = new Label("write your email here");
            emailAddressLabel.setTextFill(Color.RED);
            emailAddressLabel.setVisible(false);
            TextField emailTextField = new TextField();
            emailTextField.setPromptText("Email");

            Label label6 = new Label("PhoneNumber : ");
            Label phoneNumberLabel = new Label("write a PhoneNumber");
            phoneNumberLabel.setTextFill(Color.RED);
            phoneNumberLabel.setVisible(false);
            TextField phoneNumberTextField = new TextField();
            phoneNumberTextField.setPromptText("e.g. 09123456789");

            Button create = new Button("Create");
            create.setFont(new Font("Arial", 12));
            create.setTextFill(Color.DARKBLUE);

            pane.getChildren().addAll(label1, usernameLabel, usernameLabel2, usernameTextField,
                    label2, passwordLabel, passwordField, label3, firstNameLabel, firstNameTextField,
                    label4, lastNameLabel, lastNameTextField, label5, emailAddressLabel, emailTextField,
                    label6, phoneNumberLabel, phoneNumberTextField, create);
            window.setScene(new Scene(pane, 400, 650));
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

                        managerCreated = true;
                        new Alert().showAlert("Account created!", "Ok", 1, null);
                        window.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            });
        }
    }

}
