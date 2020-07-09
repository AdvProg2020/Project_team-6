package server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

    public String fileName = "";

    public Log(String name) {
        this.fileName = name;
        try {
            File file = new File("src\\main\\java\\server\\logs\\" + name + ".txt");
            if (file.createNewFile()) {
                System.out.println("Log file created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred in logs.");
        }
    }

    public void addLog(String log, int mode) {
        try {
            FileWriter myWriter = new FileWriter("src\\main\\java\\server\\logs\\" + this.fileName + ".txt", true);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd--HH:mm:ss   ");
            LocalDateTime now = LocalDateTime.now();
            if (mode==0) {
                myWriter.write("send    " + dtf.format(now) + log + "\n");
            } else if(mode==1){
                myWriter.write("receive " + dtf.format(now) + log + "\n");
            }else{
                myWriter.write("server  " + dtf.format(now) + log + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred in logs.");
        }
    }
}
