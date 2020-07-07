package client.view.news;

import client.Client;

public abstract class GeneralController_V {
    protected static Client senderReceiver;

    static void setSenderReceiver(Client client){
        senderReceiver = client;
    }
}
