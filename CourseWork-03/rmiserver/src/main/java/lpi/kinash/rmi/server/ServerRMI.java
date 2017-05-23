package lpi.kinash.rmi.server;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerRMI extends Remote {

    int PORT = 8070;
    String SERVER_NAME = "rmi_server";

    void start() throws RemoteException;

    void stop()  throws RemoteException;;

    String ping()  throws RemoteException;;

    void echo(String echoString)  throws RemoteException;;

    String process(File sourcePath, File targetPath)  throws RemoteException;

}
