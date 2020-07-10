package server.controller;

import server.Server;

import java.io.IOException;

public interface Parent {
    public void start(Server server) throws IOException;
}
