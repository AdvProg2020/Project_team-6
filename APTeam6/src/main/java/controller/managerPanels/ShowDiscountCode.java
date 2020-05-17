package controller.managerPanels;

import controller.LoginMenu;
import view.LoginMenuView;
import view.ShowDiscountCodeView;

public class ShowDiscountCode {
    ShowDiscountCodeView view;
    private static ShowDiscountCode showDiscountCode = null;
    public static ShowDiscountCode getShowDiscountCodeInstance() {
        if (showDiscountCode == null)
            showDiscountCode= new ShowDiscountCode();
        return showDiscountCode;
    }
    public void start(){
        view = new ShowDiscountCodeView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.matches("view discount code \\.+")) {
                String[] splitCommand = command.split("\\s");

            }
            else if (command.matches("edit discount code \\.+")) {
                String[] splitCommand = command.split("\\s");

            }
            else if (command.equals("remove discount code \\.+")){

            }
            else if (command.equals("back")){
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to DiscountCode by view");
            }
        }
    }
    public void viewDiscountCodeCode(){

    }
    public void editDiscountCode(){

    }
    public void removeDiscountCode(){

    }

}
