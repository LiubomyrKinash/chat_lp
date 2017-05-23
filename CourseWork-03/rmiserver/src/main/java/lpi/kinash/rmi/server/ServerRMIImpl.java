package lpi.kinash.rmi.server;

import java.io.File;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ServerRMIImpl implements ServerRMI {

    private static ServerRMI serverInstance;
    private static Registry registry;

    @Override
    public void start() {

        try {
            this.serverInstance = (ServerRMI) UnicastRemoteObject.exportObject(new ServerRMIImpl(), this.PORT);

            this.registry = LocateRegistry.createRegistry(this.PORT);
            this.registry.bind(SERVER_NAME, this.serverInstance);

            System.out.println("The RMI server was started successfully on the port " + this.PORT +" with name: " + this.SERVER_NAME);
        } catch (RemoteException | AlreadyBoundException e) {
            throw new RuntimeException("Failed to start server", e);
        }

    }

    @Override
    public void stop() throws RemoteException {
        try {
            ServerRMIImpl.registry.unbind(SERVER_NAME);
            UnicastRemoteObject.unexportObject(this,true);
        } catch (RemoteException | NotBoundException e) {
            throw new RuntimeException("Failed to stop the server", e);
        }
    }

    @Override
    public String ping() {
        return "Server " + this.SERVER_NAME + " is up on port: " + this.PORT;
    }

    @Override
    public void echo(String echoString) {
        System.out.println("ECHO: " + echoString);
    }

    @Override
    public String process(File sourceFile, File targetFile) {
        FileUtils csvFileUtils = new FileUtils();
        List<String> valuesString = csvFileUtils.convertCSVFileToString(sourceFile);
        Instant start = Instant.now();
        int[] sortedValues = csvFileUtils.sort(valuesString);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        String commaSeparatedValues = Arrays.stream(sortedValues).mapToObj(String::valueOf).collect(Collectors.joining(","));
        csvFileUtils.writeValuesToFile(commaSeparatedValues, targetFile);
        commaSeparatedValues += ";\nExecution time: " + duration.getNano() + " (nanoseconds)";
        return commaSeparatedValues;
    }
}
