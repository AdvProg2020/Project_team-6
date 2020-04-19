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

    /**
     * This menu works differently from other menus. this menu doesn't get anything back from view.
     */
    public void start(){
        LoginMenuView view = new LoginMenuView();
    }

    public void login(String username, String password){
        if (!ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(username)){
            System.out.println("This username does not exist");
            return;
        }
        Account tempAccount = ProgramManager.getProgramManagerInstance().getAccountByUsername(username);
        if (!tempAccount.checkPassword(password)){
            System.out.println("Wrong password");
            return;
        }
        ProgramManager.getProgramManagerInstance().loginSuccessful(tempAccount);
    }

    public void register(String username, String role){
        if (ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(username)){
            System.out.println("This username is already occupied");
        }

    }

    public void logout(){
        ProgramManager.getProgramManagerInstance().logoutSuccessful();
    }
}
