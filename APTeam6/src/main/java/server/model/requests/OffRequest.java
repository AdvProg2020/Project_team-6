package server.model.requests;

import server.controller.ProgramManager;
import server.model.product.Off;

public class OffRequest implements Request {
    private Off off;
    private String changingFiled;
    private byte action;
    // 0 - add Off
    // 1 - OffStatus
    // 2 - startDate
    // 3 - endDate
    // 4 - offAmount


    @Override
    public String toString() {
        String s = "OffRequest{" +
                "off=" + off +
                ", changingFiled='" + changingFiled + '\'' +
                ", action=";
        if(action==0){
            s+="add off";
        }else if (action==1){
            s+="offStatus";
        }else if(action==2){
            s+="startDate";
        }else if(action==3){
            s+="endDate";
        }else if(action==4){
            s+= "offAmount";
        }
        s += '}';
        return s;
    }

    public OffRequest(Off off, byte action, String changingFiled) {
        this.off = off;
        this.action = action;
        this.changingFiled = changingFiled;
    }

    @Override
    public void accept() {
        if (action == 0) {
            ProgramManager.getProgramManagerInstance().addOffToList(off);
            ProgramManager.getProgramManagerInstance().removeRequest(this);
        } else if (action == 1) {
            off.changeField(action, changingFiled);
            ProgramManager.getProgramManagerInstance().removeRequest(this);
        }

    }

    @Override
    public void decline() {
        ProgramManager.getProgramManagerInstance().removeRequest(this);
    }

    @Override
    public void showDetails() {
        ProgramManager.getProgramManagerInstance().showOff(off);
    }

    public String showDetails(int a) {
        return ProgramManager.getProgramManagerInstance().showOff(off, 1);
    }

}