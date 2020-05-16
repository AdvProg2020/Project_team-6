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
                String[] discountCommand = view.getUserUsualData();
                DiscountCode discountCode = new DiscountCode(discountCommand[0], instance.parsingStringToData(discountCommand[1]),instance.parsingStringToData(discountCommand[2]),Integer.parseInt(discountCommand[3]),Integer.parseInt(discountCommand[4]));
            }
        }
    }
}