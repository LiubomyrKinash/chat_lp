package lpi.kinash.rmi.client.commands;

import lpi.kinash.rmi.server.ServerRMI;

import java.io.File;
import java.io.FileWriter;
import java.rmi.RemoteException;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateCommand implements Command {

    int maxNumberRange = 1000000000;
    int minNumberRange = 0;
    int maxNumbers = 10000;

    @Override
    public String execute(ServerRMI server, String... args) throws RemoteException, CommandEmptyValue {
        if(args.length < 1) throw new CommandEmptyValue("There should be argument to execute 'generate' command");
        String filePath = args[0];
        validateArgument(filePath, "file path");
        File createdFile = new File(filePath);

        try (FileWriter writer = new FileWriter(createdFile)) {
            createdFile.createNewFile();
            String resultString = generateStringWithRandomNumbers();
            writer.write(resultString);
        } catch (Exception e) {
            return "Could not create a file: " + e;
        }

        return "File with random numbers is generated successfully";
    }

    private String generateStringWithRandomNumbers() {
        String resultString = "";
        int counter = 0;
        while(counter <= maxNumbers){
            int randomNumber = ThreadLocalRandom.current().nextInt(minNumberRange, maxNumberRange);
            resultString += randomNumber + " ";
            counter++;
        }
        return resultString;
    }
}
