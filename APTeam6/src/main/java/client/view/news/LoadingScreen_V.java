package client.view.news;

import javafx.scene.control.ProgressBar;

public class LoadingScreen_V extends GeneralController_V{
    public ProgressBar loadProgress;
    public void initialize(){
        for (int i = 0; i < 100; i++) {
            loadProgress.setProgress(i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //TODO: test if this works and then call main screen from here
    }
}
