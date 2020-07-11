package server.controller.managerPanels;

import server.Server;
import server.controller.Parent;
import server.controller.ProgramManager;
import server.model.account.Manager;

import java.io.IOException;

public class RegisterManager implements Parent {

    private Server server = null;

    @Override
    public void start(Server server) throws IOException {
        this.server = server;
        sendMessage("start");
    }

    private void sendMessage(String message) throws IOException {
        server.sendMessage("02-" + message);
    }

    public void registerNewManager(String data) throws IOException {
        if(data.split("-+-").length==6 && !data.split("-+-")[0].equals("") &&
                !data.split("-+-")[1].equals("") && !data.split("-+-")[2].equals("") &&
                !data.split("-+-")[3].equals("") && !data.split("-+-")[4].equals("") &&
                !data.split("-+-")[5].equals("")) {
            new Manager(data.split("-+-")[0], data.split("-+-")[1], data.split("-+-")[2],
                    data.split("-+-")[3], data.split("-+-")[4], data.split("-+-")[5]);

            ProgramManager.getProgramManagerInstance().saveToFiles();
            ProgramManager.getProgramManagerInstance().loadFromFiles();
            sendMessage("account_created");
        }else{
            sendMessage("error in data");
        }
    }
}
