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
        senderReceiver.allControllers.get(10).start();
    }

    public void productsButton(ActionEvent actionEvent) {
        //TODO: (message) -_-
    }

    public void offsButton(ActionEvent actionEvent) {
        //TODO: (message) -_-
    }

    public void loginMenuButton(ActionEvent actionEvent) {
        senderReceiver.allControllers.get(4).start();
    }
}
