package controller;

import model.Account;

public class LoginMenu {
    private static LoginMenu loginMenuInstance = null;

    public static LoginMenu getLoginMenuInstance() {
        if (loginMenuInstance == null)
            loginMenuInstance = new LoginMenu();
        return loginMenuInstance;
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
        ProgramManager.getProgramManagerInstance().
        // TODO: fill here
    }

    public void register(String username, String role){
        if (ProgramManager.getProgramManagerInstance().isThereAccountWithUsername(username)){
            System.out.println("This username is already occupied");
        }
        // TODO: fill here
    }
}
