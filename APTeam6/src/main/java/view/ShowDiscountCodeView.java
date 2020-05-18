package view;

import controller.ProgramManager;
import model.product.DiscountCode;
import model.requests.Request;

import java.util.ArrayList;

public class ShowDiscountCodeView {

    public void giveOutput(String message) {
        System.out.println(message);
    }

    public ShowDiscountCodeView() {
        System.out.println("=== Discount codes view menu\n\tCurrent codes:");
        ArrayList<Request> requests = ProgramManager.getProgramManagerInstance().getAllRequests();
        for (Request request : requests) {
            System.out.println("\t\t" + request);
        }
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

    public void viewDiscountCode(DiscountCode discountCode) {
        System.out.println("the discountCode's ID is " + discountCode.getId());
        System.out.println("the discountCode's code is " + discountCode.getCode());
        System.out.println("the discountCode's startDate is " + discountCode.getStart());
        System.out.println("the discountCode's endDate is " + discountCode.getEnd());
        System.out.println("the discountCode's percentage is " + discountCode.getPercentage());
        System.out.println("the discountCode's repetitionTime is " + discountCode.getRepetitionTime());
    }

    public void editDiscountCodeCode(DiscountCode discountCode, String code) {
        discountCode.setCode(code);
    }

    public void editDiscountCodeStartDate(DiscountCode discountCode, String startDate) {
        discountCode.setStart(ProgramManager.getProgramManagerInstance().parsingStringToData(startDate));
    }

    public void editDiscountCodeEndDate(DiscountCode discountCode, String endDate) {
        discountCode.setEnd(ProgramManager.getProgramManagerInstance().parsingStringToData(endDate));
    }

    public void editDiscountCodePercentage(DiscountCode discountCode, int percentage) {
        discountCode.setPercentage(percentage);
    }

    public void editDiscountCodeRepetitionTime(DiscountCode discountCode, int repetitionTime) {
        discountCode.setRepetitionTime(repetitionTime);
    }

    public void removeDiscountCode(DiscountCode discountCode) {
        ProgramManager.getProgramManagerInstance().deleteDiscountCode(discountCode);
    }
}
