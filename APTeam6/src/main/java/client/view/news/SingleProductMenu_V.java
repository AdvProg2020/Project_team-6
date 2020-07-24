package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

import java.util.ArrayList;
import java.util.Arrays;

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
        //Categories and subCategories menu should call this
        senderReceiver.changeMenu(14);
        String receipt = senderReceiver.getMessage();
        String[] strings = receipt.split("---");
        reset(strings[0], strings[3], strings[4], Float.parseFloat(strings[5]), new ArrayList<String>(Arrays.asList(strings[6].split("==="))), new ArrayList<String>(Arrays.asList(strings[7].split("==="))));
    }

    public void reset(String name, String description, String price, float averageScore, ArrayList<String> comments, ArrayList<String> additional){
        nameLabel.setText(name + " " + price);
        descriptionLabel.setText(description);
        commentsListView.getItems().clear();
        commentsListView.getItems().addAll(comments);
        additionalInfoListView.getItems().clear();
        additionalInfoListView.getItems().addAll(additional);
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
