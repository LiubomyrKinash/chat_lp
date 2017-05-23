package lpi.kinash.rmi.client.commands;

import lpi.kinash.rmi.server.ServerRMI;

import java.rmi.RemoteException;

public class StopCommand implements Command{


    @Override
    public String execute(ServerRMI server, String... args) throws RemoteException {
        server.stop();
        return "'stop' command successfully executed";
    }
}
