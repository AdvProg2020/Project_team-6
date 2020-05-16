package model.product;

import java.time.LocalDateTime;

public class DiscountCode {
    private String code;
    private LocalDateTime start;
    private LocalDateTime end;
    private int percentage;
    private int repetitionTime;

    public DiscountCode(String code, LocalDateTime start, LocalDateTime end, int percentage, int repetitionTime) {
        this.code = code;
        this.start = start;
        this.end = end;
        this.percentage = percentage;
        this.repetitionTime = repetitionTime;
    }
}
