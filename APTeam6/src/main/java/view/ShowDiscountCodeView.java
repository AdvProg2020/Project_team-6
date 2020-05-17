package view;

import controller.ProgramManager;
import model.product.DiscountCode;

public class ShowDiscountCodeView {

    public void giveOutput(String message){
        System.out.println(message);
    }
    public ShowDiscountCodeView() {
        System.out.println("=== Login/register menu");
    }

    public String getInputCommand() {
        String command;
        while (true) {
            command = Input.getInput();
            if (command.equals("back")) {
                return command;
            }
            else if (command.matches("view discount code \\.+")) {
                return command;
            }
            else if (command.matches("edit discount code \\.+")) {
                return command;
            }
            else if (command.matches("remove discount code \\.+")) {
                return command;
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }
    public void viewDiscountCode(DiscountCode discountCode){
        System.out.println("the discountCode's ID is " + discountCode.getId());
        System.out.println("the discountCode's code is " + discountCode.getCode());
        System.out.println("the discountCode's startDate is " + discountCode.getStart());
        System.out.println("the discountCode's endDate is " + discountCode.getEnd());
        System.out.println("the discountCode's percentage is " + discountCode.getPercentage());
        System.out.println("the discountCode's repetitionTime is " + discountCode.getRepetitionTime());
    }
    public void editDiscountCode(DiscountCode discountCode,String code){
        discountCode.setCode(code);
    }
    public void removeDiscountCode(DiscountCode discountCode){
        ProgramManager.getProgramManagerInstance().deleteDiscountCode(discountCode);
    }
}
