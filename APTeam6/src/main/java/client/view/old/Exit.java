package client.view.old;

import server.controller.ProgramManager;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Exit extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Label text = new Label("Are you want to exit?");
        Button yes = new Button("yes, I am");
        Button no = new Button("no, I'm not");

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(text,yes,no);

        stage.setScene(new Scene(vBox,300,150));
        stage.show();

        yes.setOnAction(actionEvent -> {
            ProgramManager.getProgramManagerInstance().saveToFiles();
            System.exit(0);
        });

        no.setOnAction(actionEvent -> {
            stage.close();
        });

    }
}
