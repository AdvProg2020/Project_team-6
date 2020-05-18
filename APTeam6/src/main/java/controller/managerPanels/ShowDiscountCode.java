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
                if(command.split("\\s")[4].equalsIgnoreCase("code")) {
                    editDiscountCodeCode(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[3]),command.split("\\s")[5]);
                }
                else if(command.split("\\s")[4].equalsIgnoreCase("startDate")){
                    editDiscountCodeStartDate(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[3]),command.split("\\s")[5]);
                }
                else if(command.split("\\s")[4].equalsIgnoreCase("endDate")){
                    editDiscountCodeEndDate(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[3]),command.split("\\s")[5]);
                }
                else if(command.split("\\s")[4].equalsIgnoreCase("percentage")){
                    editDiscountCodePercentage(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[3]),Integer.parseInt(command.split("\\s")[5]));
                }
                else{
                    editDiscountCodeCRepetitionTime(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[3]),Integer.parseInt(command.split("\\s")[5]));
                }
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
    public void editDiscountCodeCode(DiscountCode discountCode, String code){
        view.editDiscountCodeCode(discountCode,code);
    }
    public void removeDiscountCode(DiscountCode discountCode){
        view.removeDiscountCode(discountCode);
    }
    public void editDiscountCodeStartDate(DiscountCode discountCode,String startDate){
        view.editDiscountCodeStartDate(discountCode,startDate);
    }
    public void editDiscountCodeEndDate(DiscountCode discountCode,String endDate){
        view.editDiscountCodeEndDate(discountCode,endDate);
    }
    public void editDiscountCodePercentage(DiscountCode discountCode,int percentage){
        view.editDiscountCodePercentage(discountCode,percentage);
    }
    public void editDiscountCodeCRepetitionTime(DiscountCode discountCode,int repetitionTime){
        view.editDiscountCodeRepetitionTime(discountCode,repetitionTime);
    }


}
