package model.product;

import java.util.Date;

public class DiscountCode {
    private String code;
    private String start;
    private String end;
    private int percentage;
    private int repetitionTime;

    public DiscountCode(String code, String start, String end, int percentage, int repetitionTime) {
        this.code = code;
        this.start = start;
        this.end = end;
        this.percentage = percentage;
        this.repetitionTime = repetitionTime;
    }
}
