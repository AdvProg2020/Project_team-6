package model.requests;

import controller.ProgramManager;
import model.product.Off;

public class OffRequest implements Request {
    private Off off;
    private byte action;
    private String changingFiled;
    // 0 - add Off
    // 1 - OffStatus
    // 2 - startDate
    // 3 - endDate
    // 4 - offAmount

    public OffRequest(Off off, byte action,String changingFiled) {
        this.off = off;
        this.action = action;
        this.changingFiled = changingFiled;
    }

    @Override
    public void accept() {
        if(action == 0){
            ProgramManager.getProgramManagerInstance().addOffToList(off);
            ProgramManager.getProgramManagerInstance().removeRequest(this);
        }else if(action == 1 || action == 2| action == 3| action == 4){
            off.changeField(action,changingFiled);
            ProgramManager.getProgramManagerInstance().removeRequest(this);
        }

    }

    @Override
    public void decline() {
        ProgramManager.getProgramManagerInstance().removeRequest(this);
    }

    @Override
    public void showDetails() {
        //TODO
    }

}