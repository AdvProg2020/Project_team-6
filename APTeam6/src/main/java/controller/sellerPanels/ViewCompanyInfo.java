package controller.sellerPanels;

import view.LoginMenuView;
import view.userPanel.ViewCompanyInfoViewSection;

public class ViewCompanyInfo {
    ViewCompanyInfoViewSection view;
    public void start(){
        view = new ViewCompanyInfoViewSection();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.matches("login \\S+ \\S+")) {
                String[] splitCommand = command.split("\\s");
                login(splitCommand[0], splitCommand[1]);
            }
            else if (command.equals("back")){
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to LoginMenu by view");
            }
        }
    }

}
