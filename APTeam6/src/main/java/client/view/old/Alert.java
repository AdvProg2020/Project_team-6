package client.view.old;

import server.controller.ProgramManager;
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
    private PersonalInfoMenuView personalInfoMenuView = null;
    public void showAlert(String text,String buttonText,int destination,PersonalInfoMenuView personalInfoMenuView) throws Exception {
        this.text = text;
        this.btnText = buttonText;
        this.destination = destination;
        this.personalInfoMenuView = personalInfoMenuView;
        this.start(new Stage());
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox vBox = new VBox(10);
        Label text = new Label(this.text);
        text.setWrapText(true);
        text.prefHeight(200);
        Button ok = new Button(this.btnText);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(text,ok);
        stage.setScene(new Scene(vBox,400,300));
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
            }else if(destination==0){
                //nothing
            }else if(destination==2){
                try {
                    new LoginMenuView().start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(destination==3){
                new ManageUsersView().manageUsers(this.personalInfoMenuView);
            }
        });
    }
}
