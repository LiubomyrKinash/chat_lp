package lpi.kinash.rmi.server;

import java.rmi.RemoteException;

public class Main {

    public static void main(String[] args) {
        startServer();
    }

    public static void startServer(){
        Runnable serverThread = new Runnable() {
            @Override
            public void run() {
                ServerRMI server = new ServerRMIImpl();
                try {
                    server.start();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        };

        serverThread.run();
    }
}
