package server.controller.managerPanels;
import server.controller.Parent;
import server.controller.ProgramManager;
import server.model.product.DiscountCode;
import client.view.CreateDiscountCodeView;

public class CreateDiscountCode implements Parent {
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
                DiscountCode discountCode = new DiscountCode(null,null,null,0,0);
                String[] input = command.split("\\s");
                ProgramManager instance = ProgramManager.getProgramManagerInstance();
                String[] discountDetails = view.getUserUsualData(discountCode);
                discountCode = new DiscountCode(discountDetails[0], instance.parsingStringToDate(discountDetails[1]),instance.parsingStringToDate(discountDetails[2]),Integer.parseInt(discountDetails[3]),Integer.parseInt(discountDetails[4]));
                ProgramManager.getProgramManagerInstance().addDiscountCodeToArrayList(discountCode);
            }
        }
    }
}