package client.view.news;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;

public class LoadingScreen_V extends GeneralController_V{
    public ProgressBar loadProgress;
    public Image gifImage;

    public void reset(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(loadProgress.getProgress());
                    loadProgress.setProgress(i);
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //TODO: test if this works and then call main screen from here
        senderReceiver.sendMessage("00-0");
        senderReceiver.changeMenu(5);
    }
}
