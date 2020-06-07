package view;

import controller.LoginMenu;
import controller.ProgramManager;
import controller.buyerPanels.BuyerUserPanel;

public class MainScreenView {

    private void showHelp(){
        System.out.println("List of commands:" +
                "\n\tlogin menu" +
                "\n\tproducts" +
                "\n\texit");
    }

    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.equalsIgnoreCase("exit")){
                return command;
            }
            else if (command.equalsIgnoreCase("help")){
                showHelp();
            }
            else if (command.equalsIgnoreCase("login menu")){
                return command;
            }
            else {
                System.out.println("Invalid command");
            }

            if (ProgramManager.getProgramManagerInstance().isAnyoneLoggedIn()){
                byte role = ProgramManager.getProgramManagerInstance().getCurrentlyLoggedInUser().getRole();
                if (role == 1)
                    BuyerUserPanel.getBuyerUserPanelInstance().start();
            }
        }
    }

    public void giveOutput(String message){
        System.out.println(message);
    }
}
