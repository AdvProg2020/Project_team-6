package controller.managerPanels;
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
                DiscountCode discountCode = new DiscountCode(input[3],input[4],input[5],Integer.parseInt(input[6]),Integer.parseInt(input[7]));
            }
        }
    }
}