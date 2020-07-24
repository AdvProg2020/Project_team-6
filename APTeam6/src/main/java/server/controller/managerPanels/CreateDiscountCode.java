package server.controller.managerPanels;
import server.Server;
import server.controller.Parent;
import server.controller.ProgramManager;
import server.model.account.Account;
import server.model.product.DiscountCode;
import client.view.old.CreateDiscountCodeView;

import java.io.IOException;
import java.util.HashMap;

public class CreateDiscountCode implements Parent {
    /*
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

     */

    private Server server = null;

    @Override
    public void start(Server server) throws IOException {
        this.server = server;
        String message = "";
        for (Account allAccount : ProgramManager.getProgramManagerInstance().getAllAccounts()) {
            if(allAccount.getRole() == 1){
                message = message + allAccount.getUsername() + "---";
            }

        }
        sendMessage(message);
    }

    private void sendMessage(String message) throws IOException {
        server.sendMessage("08-" + message);
    }

    public void createDiscountCodeByData(String data) throws IOException {
        HashMap<String,DiscountCode> discountCodeHashMap = ProgramManager.getProgramManagerInstance().getAllDiscountCodes();
        if(!discountCodeHashMap.containsKey(data)) {

            //TODO check data validation
            //code---startDate---endDate---percentage---repetitionTime
            new DiscountCode(data.split("---")[0],
                    ProgramManager.getProgramManagerInstance().parsingStringToDate(data.split("---")[1]),
                    ProgramManager.getProgramManagerInstance().parsingStringToDate(data.split("---")[2]),
                    Integer.parseInt(data.split("---")[3]),Integer.parseInt(data.split("---")[4]));
            sendMessage("created");

        }else{
            sendMessage("duplicateCode");
        }
    }

}