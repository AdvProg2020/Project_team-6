package view;

import controller.ProgramManager;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Alert extends Application {
    private String text = "";
    private String btnText = "";
    private int destination = 0;
    public void showAlert(String text,String buttonText,int destination) throws Exception {
        this.text = text;
        this.btnText = buttonText;
        this.destination = destination;
        this.start(new Stage());
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox vBox = new VBox(10);
        Label text = new Label(this.text);
        Button ok = new Button(this.btnText);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(text,ok);
        stage.setScene(new Scene(vBox,300,150));
        stage.show();
        ok.setOnAction(actionEvent -> {
            ProgramManager.getProgramManagerInstance().saveToFiles();
            stage.close();
            if(destination==1){
                try {
                    MainScreenView.getMainScreenViewInstance().start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
