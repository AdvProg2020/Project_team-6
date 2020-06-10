package controller;

import controller.buyerPanels.BuyerUserPanel;
import controller.managerPanels.ManagerUserPanel;
import controller.sellerPanels.SellerUserPanel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.LoginMenuView;
import view.MainScreenView;
import view.*;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class MainScreen extends Application {
    private static MainScreen mainScreenInstance = null;

    public static MainScreen getMainScreenInstance() {
        if (mainScreenInstance == null)
            mainScreenInstance = new MainScreen();
        return mainScreenInstance;
    }

    public void start() {
        MainScreenView view = new MainScreenView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.equalsIgnoreCase("login menu")) {
                LoginMenu.getLoginMenuInstance().start();
            }
            else if (command.equalsIgnoreCase("products")) {
                CategoriesAndSubCategoriesMenu.getInstance().startAsBuyer();
            }
            else if (command.equalsIgnoreCase("exit")){
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to MainScreen by view");
            }
            ////////////////////
            if (ProgramManager.getProgramManagerInstance().isAnyoneLoggedIn()){
                byte role = ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUser().getRole();
                if (role == 1)
                    BuyerUserPanel.getBuyerUserPanelInstance().start();
                else if (role == 2)
                    SellerUserPanel.getSellerUserPanelInstance().start();
                else if (role == 3)
                    ManagerUserPanel.getManagerUserPanelInstance().start();
            }
        }
    }


    //--------------------------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------------------------
    //  Graphical menu :

    @Override
    public void start(Stage stage) throws Exception {
        loadingPage(stage);
    }

    private void loadingPage(Stage primaryStage) {
        Label percentOfProgress = new Label("0%");
        ImageView logo = new ImageView();
        ImageView logo2 = new ImageView();
        //logo.setImage(new Image(getClass().getResourceAsStream("java/view/pictures/icon.png")));
        //logo2.setImage(new Image(getClass().getResourceAsStream("pictures/loading/loading.gif")));
        logo2.setFitHeight(250);
        logo2.setPreserveRatio(true);
        VBox root2 = new VBox(1);
        root2.setAlignment(Pos.CENTER);
        root2.getChildren().addAll(logo, logo2, percentOfProgress);
        root2.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        primaryStage.setTitle(ProgramManager.getProgramManagerInstance().PROGRAM_NAME.toUpperCase());
        //primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("pictures/icon.png")));
        primaryStage.setScene(new Scene(root2, 450, 700));

        primaryStage.show();
        loadingPercent(primaryStage, percentOfProgress);
        //---------load faster
        //primaryStage.close();
        //mainMenu();
        //----------

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
        }, 3300);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> percentOfProgress.setText("95%"));
            }
        }, 5500);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> percentOfProgress.setText("100%"));
            }
        }, 5800);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    primaryStage.close();

                });
            }
        }, 5950);
    }
}