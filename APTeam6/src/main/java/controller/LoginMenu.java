package controller;

import model.account.Account;
import view.LoginMenuView;

public class LoginMenu {
    private static LoginMenu loginMenuInstance = null;

    public static LoginMenu getLoginMenuInstance() {
        if (loginMenuInstance == null)
            loginMenuInstance = new LoginMenu();
        return loginMenuInstance;
    }

    ///////////////////////////////////////////////////
    LoginMenuView view;

    public void start(){
        view = new LoginMenuView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.matches("login \\S+ \\S+")) {
                String[] splitCommand = command.split("\\s");
                login(splitCommand[0], splitCommand[1]);
            }
            else if (command.matches("register \\S+ (buyer|seller)")) {
                String[] splitCommand = command.split("\\s");
                register(splitCommand[0], splitCommand[1]);
            }
            else if (command.equals("logout")){
                logout();
            }
            else if (command.equals("back")){
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to LoginMenu by view");
            }
        }
    }

    public void login(String username, String password){
        if (!ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(username)){
            view.giveOutput("This username does not exist");
            return;
        }
        Account tempAccount = ProgramManager.getProgramManagerInstance().getAccountByUsername(username);
        if (!tempAccount.checkPassword(password)){
            view.giveOutput("Wrong password");
            return;
        }
        ProgramManager.getProgramManagerInstance().loginSuccessful(tempAccount);
    }

    public void register(String username, String role){
        if (ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(username)){
            view.giveOutput("This username is already occupied");
        }
        String[] userData = null;
        userData = view.getUserUsualData();


        /*if (role.equals("seller"))
            userData = view.getSellerCompany();*/

        // TODO: write something here
    }

    public void logout(){
        ProgramManager.getProgramManagerInstance().logoutSuccessful();
    }
}
