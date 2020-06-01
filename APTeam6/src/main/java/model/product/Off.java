package model.product;

import controller.ProgramManager;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Off {
    private int offId = 0;
    private ArrayList<Product> productsInOff;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private OffStatus offStatus;
    private int offAmount;

    public Off(ArrayList<Product> productsInOff, LocalDateTime startDate, LocalDateTime endDate, int offAmount) {
        this.productsInOff = productsInOff;
        this.startDate = startDate;
        this.endDate = endDate;
        this.offAmount = offAmount;
        offId++;
        offStatus = OffStatus.WaitingForBuild;
        ProgramManager.getProgramManagerInstance().addOffToHashMap(this);
    }
    public void changeField(byte field, String newValue){
        if(field == 1){
            if(newValue.equalsIgnoreCase("WaitingForBuild")){
                offStatus = OffStatus.WaitingForBuild;
            }else if(newValue.equalsIgnoreCase("WaitingForEdit")){
                offStatus = OffStatus.WaitingForEdit;

            }else if(newValue.equalsIgnoreCase("WaitingForConfirmation")){
                offStatus = OffStatus.WaitingForConfirmation;
            }
        }
        else if(field == 2){
            startDate = ProgramManager.getProgramManagerInstance().parsingStringToDate(newValue);
        }
        else if(field == 3){
            endDate = ProgramManager.getProgramManagerInstance().parsingStringToDate(newValue);
        }
        else if(field == 4) {
            offAmount = Integer.parseInt(newValue);
        }
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
    public int getOffAmount() {
        return offAmount;
    }

    public int getOffId() {
        return offId;
    }
}
