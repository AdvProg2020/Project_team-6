package model.product;

import java.time.LocalDateTime;

public class DiscountCode {
    private static int nextId = 0;
    private int id;
    private String code;
    private LocalDateTime start;
    private LocalDateTime end;
    private int percentage;
    private int repetitionTime;

    public DiscountCode(String code, LocalDateTime start, LocalDateTime end, int percentage, int repetitionTime) {
        id = nextId;
        nextId++;
        this.code = code;
        this.start = start;
        this.end = end;
        this.percentage = percentage;
        this.repetitionTime = repetitionTime;
    }
}
