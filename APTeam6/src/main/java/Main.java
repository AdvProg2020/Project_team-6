import server.controller.MainScreen;
import server.controller.ProgramManager;
import javafx.application.Application;
import javafx.stage.Stage;
import client.view.old.MainScreenView;

public class Main extends Application {


    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ProgramManager programManager = ProgramManager.getProgramManagerInstance();
        //MainScreen mainScreen = MainScreen.getMainScreenInstance();
        MainScreenView mainScreenView = MainScreenView.getMainScreenViewInstance();

        programManager.loadFromFiles();

        //mainScreen.start();


        try {
            mainScreenView.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        programManager.saveToFiles();
        //System.exit(0);
    }
}
