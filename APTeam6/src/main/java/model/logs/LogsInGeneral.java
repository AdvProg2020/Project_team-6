package model.logs;
import controller.ProgramManager;

import java.util.ArrayList;
import java.util.Date;

public class LogsInGeneral {
    protected static int logId;
    protected Date date;
    protected int executedDiscount;
    private static ArrayList<LogsInGeneral> allLogs = new ArrayList<LogsInGeneral>();

    public LogsInGeneral(Date date, int executedDiscount,int logId) {
        this.date = date;
        this.executedDiscount = executedDiscount;
        logId++;
        this.logId = logId;
        allLogs.add(this);
        ProgramManager.keepingLogs.put(logId,this);
    }
    private static LogsInGeneral getLogByLogId(int id){
        for (Integer integer : ProgramManager.keepingLogs.keySet()) {
            if(integer == id){
                return ProgramManager.keepingLogs.get(integer);
            }
        }
        return null;
    }
}
