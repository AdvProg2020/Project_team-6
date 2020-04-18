import controller.MainScreen;
import controller.ProgramManager;

public class Main {
    public static void main(String[] args) {
        ProgramManager.getProgramManagerInstance();
        MainScreen mainScreen = MainScreen.getMainScreenInstance();
        mainScreen.start();
        //--------------------
    }
}
