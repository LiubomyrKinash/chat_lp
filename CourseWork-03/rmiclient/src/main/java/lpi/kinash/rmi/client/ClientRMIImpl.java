package lpi.kinash.rmi.client;

import lpi.kinash.rmi.client.commands.*;
import lpi.kinash.rmi.server.ServerRMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientRMIImpl implements ClientRMI {

    private Registry registry;
    private ServerRMI server;

    @Override
    public void start() {

        try {
            registry = LocateRegistry.getRegistry("127.1.0.1", ServerRMI.PORT);
            server = (ServerRMI) registry.lookup(ServerRMI.SERVER_NAME);
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Could not connect to RMI Server");
            return;
        }
        System.out.println("Connected to RMI Server");
        startCommandReader();
    }

    private void startCommandReader() {
        Scanner scanner = new Scanner(System.in);
        Command currentCommand = null;

        System.out.println("Please, enter your commands");

        while (!isExitCommand(currentCommand) ){
            String line = scanner.nextLine().trim();
            String[] arguments = line.split(" ");
            String command = arguments[0];
            try {
                currentCommand = CommandManager.getCommand(command);
                String[] argumentsWithoutCommand = line.replaceFirst(command, "").trim().split(" ");
                String resultOfExecution = currentCommand.execute(server, argumentsWithoutCommand);
                System.out.println(resultOfExecution);
            } catch (RemoteException e) {
                System.out.println("Server error: " + e.getMessage());
            } catch (CommandEmptyValue | CommandNotFoundException commandEmptyValue) {
                System.out.println(commandEmptyValue.getMessage());
            }

        }
    }

    private boolean isExitCommand(Command command) {
        return command != null && command.getClass().equals(ExitCommand.class);
    }
}
