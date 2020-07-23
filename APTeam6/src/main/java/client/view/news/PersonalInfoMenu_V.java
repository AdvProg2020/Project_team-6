package client.view.news;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

public class PersonalInfoMenu_V extends GeneralController_V{
    public Label passwordLabel;
    public Label firstNameLabel;
    public Label emailLabel;
    public Label lastNameLabel;
    public Label PhoneLabel;
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

    public void reset(){
        //TODO get role and disable buttons and credit.
    }

    public void changePasswordButton(ActionEvent actionEvent) {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setHeaderText("New passWord");
        String result = inputDialog.getResult();
        //TODO: (message) result
    }

    public void changeFirstNameButton(ActionEvent actionEvent) {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setHeaderText("New first name");
        String result = inputDialog.getResult();
        //TODO: (message) result
    }

    public void ChangeEmailButton(ActionEvent actionEvent) {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setHeaderText("New Email");
        String result = inputDialog.getResult();
        //TODO: (message) result
    }

    public void changeLastNameButton(ActionEvent actionEvent) {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setHeaderText("New last name");
        String result = inputDialog.getResult();
        //TODO: (message) result
    }

    public void ChangePhoneButton(ActionEvent actionEvent) {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setHeaderText("New phone");
        String result = inputDialog.getResult();
        //TODO: (message) result
    }

    public void B_cartButtonAction(ActionEvent actionEvent) {
        //TODO: (message)
    }

    public void B_historyButtonAction(ActionEvent actionEvent) {
        //TODO: (message)
    }

    public void B_discountButtonAction(ActionEvent actionEvent) {
        //TODO: (message)
    }

    public void M_usersButtonAction(ActionEvent actionEvent) {
        //TODO: (message)
    }

    public void M_productsButtonAction(ActionEvent actionEvent) {
        //TODO: (message)
    }

    public void M_discountCodeButtonAction(ActionEvent actionEvent) {
        //TODO: (message)
    }

    public void M_requestButtonAction(ActionEvent actionEvent) {
        //TODO: (message)
    }

    public void M_categoriesButtonAction(ActionEvent actionEvent) {
        //TODO: (message)
    }

    public void S_companyButtonAction(ActionEvent actionEvent) {
        //TODO: (message)
    }

    public void S_historyButtonAction(ActionEvent actionEvent) {
        //TODO: (message)
    }

    public void S_categoriesButtonAction(ActionEvent actionEvent) {
        //TODO: (message)
    }

    public void S_offsButtonAction(ActionEvent actionEvent) {
        //TODO: (message)
    }

    public void B_categoriesButtonAction(ActionEvent actionEvent) {
        //TODO: (message)
    }

    public void backButton(ActionEvent actionEvent) {
        //TODO: (message)
    }
}
