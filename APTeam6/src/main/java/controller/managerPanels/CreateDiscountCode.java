package controller.managerPanels;
import controller.ProgramManager;
import model.product.DiscountCode;
import view.CreateDiscountCodeView;

import java.util.Date;

public class CreateDiscountCode {
    CreateDiscountCodeView view;


    public void start() {
        view = new CreateDiscountCodeView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if(command.startsWith("create discount code")){
                String[] input = command.split("\\s");
                ProgramManager instance = ProgramManager.getProgramManagerInstance();
                DiscountCode discountCode = new DiscountCode(input[3], instance.parsingStringToData(input[4]),instance.parsingStringToData(input[5]),Integer.parseInt(input[6]),Integer.parseInt(input[7]));
            }
        }
    }
}