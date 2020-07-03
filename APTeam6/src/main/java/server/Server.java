package server;

import java.net.Socket;

public class Server implements Runnable {

    Socket clientSocket;

    public Server(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        System.out.println("its first run");
    }
}
