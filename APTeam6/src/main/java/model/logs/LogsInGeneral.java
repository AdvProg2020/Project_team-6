package model.logs;
import controller.ProgramManager;

import java.util.ArrayList;
import java.util.Date;

public class LogsInGeneral {
    protected static int nextLogId = 0;
    protected int logId;
    protected Date date;
    protected int executedDiscount;
    public static ArrayList<LogsInGeneral> allLogs = new ArrayList<LogsInGeneral>();

    /**
     * Attention: this method automatically adds the log to the log list.
     */
    public LogsInGeneral(Date date, int executedDiscount,int logId) {
        this.date = date;
        this.executedDiscount = executedDiscount;
        nextLogId++;
        this.logId = nextLogId;
        allLogs.add(this);
        ProgramManager.getProgramManagerInstance().addLogToList(this);
    }

    public int getLogId() {
        return logId;
    }
}
