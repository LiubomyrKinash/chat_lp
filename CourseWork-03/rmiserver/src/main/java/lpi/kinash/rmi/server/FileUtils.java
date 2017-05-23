package lpi.kinash.rmi.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtils {

    private static final String SPACE_SIGN = " ";

    public List<String> convertCSVFileToString(File csvFilePath){
        String readedLine;
        List<String> values = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {

            while ((readedLine = br.readLine()) != null) {
                String[] lineValues = readedLine.split(SPACE_SIGN);
                values.addAll(Arrays.asList(lineValues));
            }

        } catch (Exception e) {
            System.out.println("Could not read CSV file from path: " + csvFilePath + "; cause: " + e);
        }
        return values;

    }

    public File writeValuesToFile(String values, File targetFilePath){
        File targetFile = targetFilePath;
        try (FileWriter fileWriter = new FileWriter(targetFile)) {
            fileWriter.write(values);
        } catch (Exception e) {
            System.out.println("Could not write CSV file to path: " + targetFilePath + "; cause: " + e);
        }
        return targetFile;
    }

    public int[] sort(List<String> values){
        int[] integerArray = values.stream().mapToInt(value -> Integer.valueOf(value)).toArray();

        boolean swapped;
        do {
            swapped = false;
            for (int i =0; i<=  integerArray.length  - 2;i++) {
                if (integerArray[ i ] > integerArray[ i + 1 ]) {
                    //test whether the two elements are in the wrong order
                    int temp = integerArray[i];
                    integerArray[i] = integerArray[i+1];
                    integerArray[i+1]=temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                //we can exit the outer loop here if no swaps occurred.
                break;
            }
            swapped = false;
            for (int i= integerArray.length - 2;i>=0;i--) {
                if (integerArray[ i ] > integerArray[ i + 1 ]) {
                    int temp = integerArray[i];
                    integerArray[i] = integerArray[i+1];
                    integerArray[i+1]=temp;
                    swapped = true;
                }
            }
            //if no elements have been swapped, then the list is sorted
        } while (swapped);

        return integerArray;
    }


}
