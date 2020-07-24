package client.view.news;

import javafx.event.ActionEvent;
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
    }

    public void backButton(ActionEvent actionEvent) {
        //TODO (message) -
    }

    public void addButton(ActionEvent actionEvent) {
        //TODO (message) -
    }
}
