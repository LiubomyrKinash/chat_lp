package lpi.kinash.rmi.client.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private static final Map<String, Command> commands = init();

    private static Map<String, Command> init(){
        Map<String, Command> commands = new HashMap<String, Command>(){{
            put("ping", new PingCommand());
            put("echo", new EchoCommand());
            put("stop", new StopCommand());
            put("process", new ProcessCommand());
            put("exit", new ExitCommand());
            put("generate", new GenerateCommand());
        }};
        return commands;
    }

    public static Command getCommand(String commandName) throws CommandNotFoundException {
        Command command = commands.get(commandName);
        if(command == null){
            throw new CommandNotFoundException("Command: " + commandName + " not found");
        }
        return command;
    }

}
