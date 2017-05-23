package lpi.kinash.rmi.client.commands;

import lpi.kinash.rmi.server.ServerRMI;

import java.io.File;
import java.rmi.RemoteException;

public class ProcessCommand implements Command{


    @Override
    public String execute(ServerRMI server, String... args) throws RemoteException, CommandEmptyValue {
        if(args.length < 2) throw new CommandEmptyValue("There should be 2 arguments to execute 'process' command");
        String sourceFilePath = args[0];
        String targetFilePath = args[1];
        validateArgument(sourceFilePath, "source file path");
        validateArgument(targetFilePath, "target file path");

        String processResult = server.process(new File(sourceFilePath), new File(targetFilePath));
        return processResult;
    }
}
