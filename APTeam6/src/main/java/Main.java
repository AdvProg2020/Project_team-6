import controller.MainScreen;
import controller.ProgramManager;
import javafx.application.Application;
import javafx.stage.Stage;
import view.MainScreenView;

public class Main extends Application {


    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ProgramManager programManager = ProgramManager.getProgramManagerInstance();
        MainScreen mainScreen = MainScreen.getMainScreenInstance();
        MainScreenView mainScreenView = MainScreenView.getMainScreenViewInstance();

        programManager.loadFromFiles();

        Thread commandLineThread = new Thread(() -> {
            mainScreen.start();
        });
        commandLineThread.start();


        try {
            mainScreenView.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        programManager.saveToFiles();
    }
}
