package server.controller.managerPanels;

import server.Server;
import server.controller.Parent;
import server.controller.ProgramManager;
import server.model.account.Manager;
import server.model.product.DiscountCode;
import client.view.old.ShowDiscountCodeView;

import java.io.IOException;

public class ShowDiscountCode implements Parent {
/*
    private static ShowDiscountCode showDiscountCode = null;
    public static ShowDiscountCode getShowDiscountCodeInstance() {
        if (showDiscountCode == null)
            showDiscountCode= new ShowDiscountCode();
        return showDiscountCode;
    }
    //////////////////////////
    private ShowDiscountCodeView view;

    public void start(){
        view = new ShowDiscountCodeView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.matches("view discount code \\.+")) {
                viewDiscountCode(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[3]));
            }
            else if (command.matches("edit discount code \\.+")) {
                if(command.split("\\s")[3].equalsIgnoreCase("code")) {
                    editDiscountCodeCode(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[4]),command.split("\\s")[5]);
                }
                else if(command.split("\\s")[3].equalsIgnoreCase("startDate")){
                    editDiscountCodeStartDate(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[4]),command.split("\\s")[5]);
                }
                else if(command.split("\\s")[3].equalsIgnoreCase("endDate")){
                    editDiscountCodeEndDate(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[4]),command.split("\\s")[5]);
                }
                else if(command.split("\\s")[3].equalsIgnoreCase("percentage")){
                    editDiscountCodePercentage(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[4]),Integer.parseInt(command.split("\\s")[5]));
                }
                else{
                    editDiscountCodeRepetitionTime(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[4]),Integer.parseInt(command.split("\\s")[5]));
                }
            }
            else if (command.equals("remove discount code \\.+")){
                removeDiscountCode(ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(command.split("\\s")[3]));
            }
            else if (command.equals("back")){
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to DiscountCode by client.view");
            }
        }
    }


    public void viewDiscountCode(DiscountCode discountCode){
        view.viewDiscountCode1(discountCode);
        view.viewDiscountCode2(discountCode);
        view.viewDiscountCode3(discountCode);
        view.viewDiscountCode4(discountCode);
        view.viewDiscountCode5(discountCode);
        view.viewDiscountCode6(discountCode);
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
    public void editDiscountCodeRepetitionTime(DiscountCode discountCode, int repetitionTime){
        view.editDiscountCodeRepetitionTime(discountCode,repetitionTime);
    }
*/

    private Server server = null;

    @Override
    public void start(Server server) throws IOException {
        this.server = server;
        sendMessage("start");
    }

    private void sendMessage(String message) throws IOException {
        server.sendMessage("02-" + message);
    }
    public void showDiscountCode(String data) throws IOException {
            DiscountCode tempDiscountCode = ProgramManager.getProgramManagerInstance().getDiscountCodeByCode(data.split("-+-")[0]);
            sendMessage("id :" + tempDiscountCode.getId() + "\nStart Data :" + tempDiscountCode.getStart() + " \nEnd Date :" + tempDiscountCode.getEnd() + "\nRepetition Time :" + tempDiscountCode.getRepetitionTime() + "\nPercentage :" + tempDiscountCode.getPercentage());
    }
}
