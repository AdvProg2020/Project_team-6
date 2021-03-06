package server.controller.managerPanels;

import server.Server;
import server.controller.Parent;
import client.view.old.ManageRequestsView;
import server.controller.ProgramManager;
import server.model.account.Seller;
import server.model.requests.Request;

import java.io.IOException;
import java.util.ArrayList;

public class ManageRequests implements Parent {

    private Server server = null;

    @Override
    public void start(Server server) throws IOException {
        this.server = server;
        sendMessage(ProgramManager.getProgramManagerInstance().showAllRequests());
        /*ArrayList<Request> requests = ProgramManager.getProgramManagerInstance().getAllRequests();
        String mess = "";
        for (Request request : requests) {
            mess += ((Seller)request).getFirstName();
        }*/

    }
    public void detailRequest(String data) throws IOException {
        sendMessage(ProgramManager.getProgramManagerInstance().detailsOfRequest(Integer.parseInt(data)));
    }
    private void sendMessage(String message) throws IOException {
        server.sendMessage("06-" + message);
    }

    public void acceptRequest(String data) throws IOException {

        if(!data.equals("")) {

            ProgramManager.getProgramManagerInstance().acceptRequests(Integer.parseInt(data));
            //sendMessage("accepted");

            sendMessage(ProgramManager.getProgramManagerInstance().showAllRequests());
        }
    }

    public void declineRequest(String data) throws IOException {


        if(!data.equals("")) {
            ProgramManager.getProgramManagerInstance().declineRequests(Integer.parseInt(data));

            //sendMessage("declined");
            sendMessage(ProgramManager.getProgramManagerInstance().showAllRequests());

        }
    }

    /*
    private static ManageRequests ManageRequestsInstance = null;

    public static ManageRequests getManageRequestsInstance() {
        if (ManageRequestsInstance == null)
            ManageRequestsInstance = new ManageRequests();
        return ManageRequestsInstance;
    }

    private ManageRequestsView view;

    public void start() {
        view = new ManageRequestsView();
        String command = null;
        while (true) {
            command = view.getInputCommand();
            if (command.matches("details \\.+")) {
                view.details(Integer.parseInt(command.split("\\s")[1]));
            }
            else if (command.matches("accept \\.+")) {
                view.accept(Integer.parseInt(command.split("\\s")[1]));

            }
            else if (command.matches("decline \\.+")) {
                view.decline(Integer.parseInt(command.split("\\s")[1]));

            }
            else if (command.equals("back")) {
                return;
            }
            else {
                throw new RuntimeException("Unknown command was passed to ManageRequests by client.view");
            }
        }
    }
         */
}

