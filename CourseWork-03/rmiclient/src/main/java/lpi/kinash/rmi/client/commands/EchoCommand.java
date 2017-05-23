package lpi.kinash.rmi.client.commands;

import lpi.kinash.rmi.server.ServerRMI;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class EchoCommand implements Command{
    @Override
    public String execute(ServerRMI server, String... args) throws RemoteException, CommandEmptyValue {
        if(args.length < 1) throw new CommandEmptyValue("There should be argument to execute 'echo' command");
        String echoValue = Arrays.stream(args).collect(Collectors.joining(" "));
        validateArgument(echoValue, "echo");
        server.echo(echoValue);
        return "'echo' command successfully executed on the server";
    }
}
