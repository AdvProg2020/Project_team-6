package server.controller.managerPanels;

import server.controller.Parent;
import client.view.ManageRequestsView;

public class ManageRequests implements Parent {
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
}

