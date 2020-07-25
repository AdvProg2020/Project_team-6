package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

import java.util.ArrayList;

public class SingleProductMenu_V extends GeneralController_V{
    public Label nameLabel;
    public Label descriptionLabel;
    public ProgressBar scoreProgressBar;
    public Label scoreLabel;
    public ListView<String> commentsListView;
    public ListView<String> additionalInfoListView;
    public Button addButtonId;
    private int role;

    @Override
    public void start() {
        senderReceiver.changeMenu(14);
        senderReceiver.sendMessage("12-9");
        String receipt = senderReceiver.getMessage();
        //TODO: server should send additionals too
        reset();
    }

    public void reset(String name, String description, ArrayList<String> comments, float averageScore){
        nameLabel.setText(name);
        descriptionLabel.setText(description);
        commentsListView.getItems().clear();
        commentsListView.getItems().addAll(comments);
        scoreProgressBar.setProgress(averageScore / 10);
        senderReceiver.sendMessage("20-0");
        role = Integer.parseInt(senderReceiver.getMessage());
        if (role == 1 || role == 2)
            addButtonId.setDisable(false);
        else
            addButtonId.setDisable(true);
    }

    public void backButton(ActionEvent actionEvent) {
        senderReceiver.allControllers.get(0).start();
    }

    public void addButton(ActionEvent actionEvent) {

    }
}
