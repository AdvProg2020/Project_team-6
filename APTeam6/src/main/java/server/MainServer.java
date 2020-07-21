package server;

import server.controller.ProgramManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class MainServer {

    public static int runningServer = 0;
    public static Socket bankSocket = null;

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("please wait...");
        ServerSocket serverSocket = new ServerSocket(0);
        //System.out.println("main server ip is : "+serverSocket.getLocalSocketAddress().toString());
        System.out.println("main server is running on port : " + serverSocket.getLocalPort() + "\n");

        System.out.println("please input bank information");
        System.out.println("please input bank ip address:");
        Scanner scanner = new Scanner(System.in);
        String ip = scanner.nextLine();
        System.out.println("please input bank port:");
        int port = Integer.parseInt(scanner.nextLine());
        bankSocket = new Socket(ip,port);
        System.out.println("connected to bank");

        long[] time = new long[5];
        Arrays.fill(time, 0);
        time[4] = System.currentTimeMillis();

        while (true) {
            if(runningServer==0){
                System.out.println("loading databases...");
                ProgramManager.getProgramManagerInstance().loadFromFiles();
                System.out.println("databases loaded successfully");
            }

            replayAttacks(time);

            Socket clientSocket = serverSocket.accept();
            runningServer++;
            System.out.println("new client connected");
            System.out.println("the number of connected client : "+runningServer);
            new Thread(new Server(clientSocket)).start();
        }
    }

    private static void replayAttacks(long[] time) throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            time[i] = time[i+1];
        }

        time[4] = System.currentTimeMillis();

        if(time[0]!=0){
            if(time[4]-time[0]<5000){
                System.out.println("server suspended for 10 second because too many request");
                Thread.sleep(10000);
            }
        }

    }
}
