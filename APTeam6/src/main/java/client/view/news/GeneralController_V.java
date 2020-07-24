package client.view.news;

import client.Client;

public abstract class GeneralController_V {
    protected Client senderReceiver;

    public void setSenderReceiver(Client client){
        senderReceiver = client;
    }

    public abstract void start();
}
