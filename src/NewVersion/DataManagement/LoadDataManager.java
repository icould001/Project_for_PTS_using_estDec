package NewVersion.DataManagement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import NewVersion.DataManagement.Items.LogItem;
import NewVersion.UserInterface.UIManager;
import NewVersion.Util.Result;
import NewVersion.Util.UnexpectedLogException;

/**
 * Created by Aykut Ismailov on 17.4.2020 г.
 */
public class LoadDataManager {

    public static Result loadData(UIManager ui) {
        Result r = Result.OK;
        try (FileInputStream in = new FileInputStream(ui.getPathToFile()); //
                Scanner reader = new Scanner(in)) {
            for (; reader.hasNext();) {
                String[] separatedLine = Arrays.stream(reader.nextLine().split(",")).peek(x -> x = x.trim())
                        .collect(Collectors.joining(",")).split(",");// trimming that apperantly is not working
                try {
                    if (!Character.isDigit(separatedLine[0].charAt(0))) {
                        throw new UnexpectedLogException(String.join(" , ", separatedLine));
                    }
                    process_log(separatedLine);
                } catch (UnexpectedLogException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            r = Result.NOK;
        }
        return r;
    }

    private static void process_log(String[] separatedLine) throws UnexpectedLogException {
        if (separatedLine.length == 8) {// log with ip
            DataHolder.manageLog(new LogItem(separatedLine[0].trim() + " " + separatedLine[1].trim(),
                    separatedLine[2].trim(), separatedLine[3].trim(), separatedLine[4].trim(), separatedLine[5].trim(),
                    separatedLine[6].trim(), separatedLine[7].trim()));
            putInMaps(separatedLine);

        } else if (separatedLine.length == 7) {// log without ip e.g from cli
            DataHolder.manageLog(new LogItem(separatedLine[0].trim() + " " + separatedLine[1].trim(),
                    separatedLine[2].trim(), separatedLine[3].trim(), separatedLine[4].trim(), separatedLine[5].trim(),
                    separatedLine[6].trim(), ""));
            putInMaps(separatedLine);

        } else {
            throw new UnexpectedLogException(String.join(" , ", separatedLine));
        }
    }

    private static void putInMaps(String[] separatedLine) {
        if (!DataHolder.nSetOfStrings.containsKey(separatedLine[0].trim() + " " + separatedLine[1].trim())) {
            DataHolder.nSetOfStrings.put(separatedLine[0].trim() + " " + separatedLine[1].trim(),
                    DataHolder.nSetOfStrings.size());
            DataHolder.nSetOfInts.put(DataHolder.nSetOfInts.size(),
                    separatedLine[0].trim() + " " + separatedLine[1].trim());
        }
        Arrays.stream(separatedLine).skip(2).forEach(x -> {
            if (!DataHolder.nSetOfStrings.containsKey(x.trim())) {
                DataHolder.nSetOfStrings.put(x.trim(), DataHolder.nSetOfStrings.size());
                DataHolder.nSetOfInts.put(DataHolder.nSetOfInts.size(), x.trim());
            }
        });
    }
}
