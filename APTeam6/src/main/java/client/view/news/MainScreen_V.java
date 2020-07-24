package client.view.news;

import javafx.event.ActionEvent;

public class MainScreen_V extends GeneralController_V{
    @Override
    public void start() {
        senderReceiver.changeMenu(5);
        senderReceiver.sendMessage("00-0");
        String receipt = senderReceiver.getMessage();
    }

    public void userPanelButton(ActionEvent actionEvent) {
        //TODO: (message) -
    }

    public void productsButton(ActionEvent actionEvent) {
        //TODO: (message) -
    }

    public void offsButton(ActionEvent actionEvent) {
        //TODO: (message) -
    }

    public void loginMenuButton(ActionEvent actionEvent) {
        //TODO: (message) -
    }
}
