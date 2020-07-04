package server;

import server.controller.ProgramManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

    public static int runningServer = 0;

    public static void main(String[] args) throws IOException {
        System.out.println("please wait...");
        ServerSocket serverSocket = new ServerSocket(0);
        //System.out.println("main server ip is : "+serverSocket.getLocalSocketAddress().toString());
        System.out.println("main server is running on port : " + serverSocket.getLocalPort() + "\n");
        while (true) {
            if(runningServer==0){
                System.out.println("loading databases");
                ProgramManager.getProgramManagerInstance().loadFromFiles();
            }
            Socket clientSocket = serverSocket.accept();
            runningServer++;
            System.out.println("new client connected");
            Server server = new Server(clientSocket);
            server.run();
        }
    }
}
