package controller.managerPanels;

import controller.ProgramManager;
import model.product.DiscountCode;
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
                viewDiscountCode(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[3]));
            }
            else if (command.matches("edit discount code \\.+")) {
                editDiscountCode(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[3]),command.split("\\s")[4]);
            }
            else if (command.equals("remove discount code \\.+")){
                removeDiscountCode(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[3]));
            }
            else if (command.equals("back")){
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to DiscountCode by view");
            }
        }
    }

    public void viewDiscountCode(DiscountCode discountCode){
        view.viewDiscountCode(discountCode);
    }
    public void editDiscountCode(DiscountCode discountCode,String code){
        view.editDiscountCode(discountCode,code);
    }
    public void removeDiscountCode(DiscountCode discountCode){
        view.removeDiscountCode(discountCode);
    }

}
