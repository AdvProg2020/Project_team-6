package server.model.logs;
import server.controller.ProgramManager;

import java.util.Date;

public class LogsInGeneral {
    protected static int nextLogId = 0;
    protected int logId;
    protected Date date;
    protected int executedDiscount;
    protected int type;      // 1 : buyLog  -  2 : sellLog

    /**
     * Attention: this method automatically adds the log to the log list.
     */
    public LogsInGeneral(Date date, int executedDiscount,int logId,int type) {
        this.date = date;
        this.executedDiscount = executedDiscount;
        nextLogId++;
        this.logId = nextLogId;
        ProgramManager.getProgramManagerInstance().addLogToList(this);
        this.type = type;
    }

    public int getLogId() {
        return logId;
    }

    public int getType() {
        return type;
    }

    public static int getNextLogId() {
        return nextLogId;
    }

    public Date getDate() {
        return date;
    }

    public int getExecutedDiscount() {
        return executedDiscount;
    }
}
