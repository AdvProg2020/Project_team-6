import controller.MainScreen;
import controller.ProgramManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {


    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ProgramManager programManager = ProgramManager.getProgramManagerInstance();
        MainScreen mainScreen = MainScreen.getMainScreenInstance();

        programManager.loadFromFiles();

        Thread commandLineThread = new Thread(() -> {
            mainScreen.start();
        });
        commandLineThread.start();


        try {
            mainScreen.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        programManager.saveToFiles();
    }
}
