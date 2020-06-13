package view;

import controller.MainScreen;
import controller.ProgramManager;
import controller.buyerPanels.BuyerUserPanel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

public class MainScreenView extends Application {
    private static MainScreenView mainScreenViewInstance = null;

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
        //TODO
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
        root2.setBackground(new Background(new BackgroundFill(new Color(0.964,0.964,0.964,1), CornerRadii.EMPTY, Insets.EMPTY)));
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

                });
            }
        }, 7150);
    }
}
