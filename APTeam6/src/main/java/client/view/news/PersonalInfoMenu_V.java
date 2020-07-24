package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

public class PersonalInfoMenu_V extends GeneralController_V{
    public Label userNameLabel;
    public Label passwordLabel;
    public Label firstNameLabel;
    public Label emailLabel;
    public Label lastNameLabel;
    public Label phoneLabel;
    public Label roleLabel;
    public Label creditLabel;
    public Button B_cartButton;
    public Button B_historyButton;
    public Button B_discountButton;
    public Button M_usersButton;
    public Button M_productsButton;
    public Button M_discountCodeButton;
    public Button M_requestButton;
    public Button M_categoriesButton;
    public Button S_companyButton;
    public Button S_historyButton;
    public Button S_categoriesButton;
    public Button S_offsButton;
    public Button B_categoriesButton;

    String companyName;

    @Override
    public void start() {
        senderReceiver.changeMenu(10);
        senderReceiver.sendMessage("01-0");
        String receipt = senderReceiver.getMessage();
        reset(receipt);
    }

    public void reset(String receipt){
        String[] strings = receipt.split("---");
        userNameLabel.setText("UserName: " + strings[1]);
        passwordLabel.setText("PassWord: " + strings[2]);
        firstNameLabel.setText("First Name: " + strings[3]);
        lastNameLabel.setText("Last Name: " + strings[4]);
        emailLabel.setText("Email: " + strings[5]);
        phoneLabel.setText("Phone: " + strings[6]);

        if (receipt.contains("buyer")){
            setButtonsByRole(1);
            roleLabel.setText("Role: Buyer");
            creditLabel.setText("credit: " + strings[7]);
        }
        else if (receipt.contains("seller")){
            setButtonsByRole(2);
            roleLabel.setText("Role: Seller");
            creditLabel.setText("credit: " + strings[7]);
            companyName = strings[8];
        }
        else if (receipt.contains("manager")){
            setButtonsByRole(3);
            roleLabel.setText("Role: Manager");
        }
        else if (receipt.contains("support")){
            setButtonsByRole(4);
            roleLabel.setText("Role: Suppressor");
        }
        else {
            throw new RuntimeException("The hell kind of account?!");
        }

        System.out.println("Personal menu info");
    }

    public void changePasswordButton(ActionEvent actionEvent) {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setHeaderText("New passWord");
        String result = inputDialog.getResult();
        senderReceiver.sendMessage("4" + result);
    }

    public void changeFirstNameButton(ActionEvent actionEvent) {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setHeaderText("New first name");
        String result = inputDialog.getResult();
        senderReceiver.sendMessage("0" + result);
    }

    public void ChangeEmailButton(ActionEvent actionEvent) {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setHeaderText("New Email");
        String result = inputDialog.getResult();
        senderReceiver.sendMessage("2" + result);
    }

    public void changeLastNameButton(ActionEvent actionEvent) {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setHeaderText("New last name");
        String result = inputDialog.getResult();
        senderReceiver.sendMessage("1" + result);
    }

    public void ChangePhoneButton(ActionEvent actionEvent) {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setHeaderText("New phone");
        String result = inputDialog.getResult();
        senderReceiver.sendMessage("3" + result);
    }

    ///////////////////////////////////////////////////////////////////////////////

    public void B_cartButtonAction(ActionEvent actionEvent) {
        senderReceiver.allControllers.get(15).start();
    }

    public void B_historyButtonAction(ActionEvent actionEvent) {
        //TODO: (message)
    }

    public void B_discountButtonAction(ActionEvent actionEvent) {
        //TODO: (message)
    }

    public void M_usersButtonAction(ActionEvent actionEvent) {
        senderReceiver.allControllers.get(9).start();
    }

    public void M_productsButtonAction(ActionEvent actionEvent) {
        senderReceiver.allControllers.get(6).start();
    }

    public void M_discountCodeButtonAction(ActionEvent actionEvent) {
        senderReceiver.allControllers.get(7).start();
    }

    public void M_requestButtonAction(ActionEvent actionEvent) {
        senderReceiver.allControllers.get(8).start();
    }

    public void M_categoriesButtonAction(ActionEvent actionEvent) {
        senderReceiver.allControllers.get(0).start();
    }

    public void S_companyButtonAction(ActionEvent actionEvent) {
        new Alert(Alert.AlertType.INFORMATION,"Your chompany is called " + companyName + " in case you forgot 😁").showAndWait();
    }

    public void S_historyButtonAction(ActionEvent actionEvent) {
        //TODO: (message)
    }

    public void S_categoriesButtonAction(ActionEvent actionEvent) {
        senderReceiver.allControllers.get(0).start();
    }

    public void S_offsButtonAction(ActionEvent actionEvent) {
        senderReceiver.allControllers.get(12).start();
    }

    public void B_categoriesButtonAction(ActionEvent actionEvent) {
        senderReceiver.allControllers.get(0).start();
    }

    public void backButton(ActionEvent actionEvent) {
        senderReceiver.allControllers.get(5).start();
    }

    ////////////////////////////////

    private void setButtonsByRole(int role){
        if (role == 1){
            B_cartButton.setDisable(false);
            B_historyButton.setDisable(false);
            B_discountButton.setDisable(false);
            B_categoriesButton.setDisable(false);
            M_usersButton.setDisable(true);
            M_productsButton.setDisable(true);
            M_discountCodeButton.setDisable(true);
            M_requestButton.setDisable(true);
            M_categoriesButton.setDisable(true);
            S_companyButton.setDisable(true);
            S_historyButton.setDisable(true);
            S_categoriesButton.setDisable(true);
            S_offsButton.setDisable(true);
            //TODO: Support man
        }
        else if (role == 2){
            B_cartButton.setDisable(true);
            B_historyButton.setDisable(true);
            B_discountButton.setDisable(true);
            B_categoriesButton.setDisable(true);
            M_usersButton.setDisable(true);
            M_productsButton.setDisable(true);
            M_discountCodeButton.setDisable(true);
            M_requestButton.setDisable(true);
            M_categoriesButton.setDisable(true);
            S_companyButton.setDisable(false);
            S_historyButton.setDisable(false);
            S_categoriesButton.setDisable(false);
            S_offsButton.setDisable(false);
            //TODO: Support man
        }
        else if (role == 3){
            B_cartButton.setDisable(true);
            B_historyButton.setDisable(true);
            B_discountButton.setDisable(true);
            B_categoriesButton.setDisable(true);
            M_usersButton.setDisable(false);
            M_productsButton.setDisable(false);
            M_discountCodeButton.setDisable(false);
            M_requestButton.setDisable(false);
            M_categoriesButton.setDisable(false);
            S_companyButton.setDisable(true);
            S_historyButton.setDisable(true);
            S_categoriesButton.setDisable(true);
            S_offsButton.setDisable(true);
            //TODO: Support man
        }
        else if (role == 4){
            B_cartButton.setDisable(true);
            B_historyButton.setDisable(true);
            B_discountButton.setDisable(true);
            B_categoriesButton.setDisable(true);
            M_usersButton.setDisable(true);
            M_productsButton.setDisable(true);
            M_discountCodeButton.setDisable(true);
            M_requestButton.setDisable(true);
            M_categoriesButton.setDisable(true);
            S_companyButton.setDisable(true);
            S_historyButton.setDisable(true);
            S_categoriesButton.setDisable(true);
            S_offsButton.setDisable(true);
            //TODO: Support man
        }
    }
}
