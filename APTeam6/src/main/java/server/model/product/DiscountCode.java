package server.model.product;

import server.controller.ProgramManager;
import server.model.account.Account;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DiscountCode {
    private static int nextId = 0;
    private int id;
    private String code;
    private LocalDateTime start;
    private LocalDateTime end;
    private int percentage;
    private int repetitionTime;
    private ArrayList <String> usersIncludedInDiscountCode = new ArrayList<>();

    public DiscountCode(String code, LocalDateTime start, LocalDateTime end, int percentage, int repetitionTime) {
        id = nextId;
        nextId++;
        this.code = code;
        this.start = start;
        this.end = end;
        this.percentage = percentage;
        this.repetitionTime = repetitionTime;
        ProgramManager.getProgramManagerInstance().addDiscountCodeToArrayList(this);
    }

    @Override
    public String toString() {
        return "DiscountCode{" +
                "code='" + code + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", percentage=" + percentage +
                ", repetitionTime=" + repetitionTime +
                ", usersIncludedInDiscountCode=" + usersIncludedInDiscountCode +
                '}';
    }

    public String getCode() {
        return code;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public void setRepetitionTime(int repetitionTime) {
        this.repetitionTime = repetitionTime;
    }

    public int getRepetitionTime() {
        return repetitionTime;
    }

    public void addToArrayList(Account account){
        usersIncludedInDiscountCode.add(account.getUsername());
    }
}
