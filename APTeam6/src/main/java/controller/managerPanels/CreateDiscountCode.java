package controller.managerPanels;
import controller.ProgramManager;
import model.product.DiscountCode;
import view.CreateDiscountCodeView;

import java.util.Date;

public class CreateDiscountCode {
    private static CreateDiscountCode instance;
    public static CreateDiscountCode getInstance(){
        if (instance == null)
            instance = new CreateDiscountCode();
        return instance;
    }
    /////////////////////
    private CreateDiscountCodeView view;

    public void start() {
        view = new CreateDiscountCodeView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if(command.startsWith("create discount code")){
                String[] input = command.split("\\s");
                ProgramManager instance = ProgramManager.getProgramManagerInstance();
                String[] discountDetails = view.getUserUsualData();
                DiscountCode discountCode = new DiscountCode(discountDetails[0], instance.parsingStringToData(discountDetails[1]),instance.parsingStringToData(discountDetails[2]),Integer.parseInt(discountDetails[3]),Integer.parseInt(discountDetails[4]));
                ProgramManager.getProgramManagerInstance().addDiscountCodeToArrayList(discountCode);
            }
        }
    }
}