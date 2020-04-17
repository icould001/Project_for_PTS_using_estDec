package logReader;
/*
import NewVersion.DataManagement.Items.LogItem;
import NewVersion.Util.UnexpectedLogException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import static Logic.mainLogic.*;

/**
 * Created by Aykut Ismailov on 22.3.2020 г.
 */
/*
public class LogReader {
    static String log = "src/logReader/logs_BCS37_20181103.csv";

    public static void loadData() {

        try (FileInputStream in = new FileInputStream(log)) {
            Scanner reader = new Scanner(in);
            for (; reader.hasNext(); ) {
                String[] separatedLine = Arrays.stream(reader.nextLine().split(",")).peek(x -> x = x.trim()).collect(Collectors.joining(",")).split(",");//trimming
                try {
                    if (!Character.isDigit(separatedLine[0].charAt(0))) {
                        throw new UnexpectedLogException(String.join(" , ", separatedLine));
                    }
                    if (separatedLine.length == 8) {//log with ip
                        logItems.add(new LogItem(separatedLine[0] + " " + separatedLine[1], separatedLine[2], separatedLine[3], separatedLine[4], separatedLine[5], separatedLine[6], separatedLine[7]));
                        putInMaps(separatedLine);

                    } else if (separatedLine.length == 7) {//log without ip e.g from cli
                        logItems.add(new LogItem(separatedLine[0] + " " + separatedLine[1], separatedLine[2], separatedLine[3], separatedLine[4], separatedLine[5], separatedLine[6], ""));
                        putInMaps(separatedLine);
                    } else {
                        throw new UnexpectedLogException(String.join(" , ", separatedLine));
                    }
                }catch (UnexpectedLogException e){
                    System.err.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void putInMaps(String[] separatedLine) {
        if (!nSetOfStrings.containsKey(separatedLine[0] + " " + separatedLine[1])) {
            nSetOfStrings.put(separatedLine[0] + " " + separatedLine[1], nSetOfStrings.size());
            nSetOfInts.put(nSetOfInts.size(), separatedLine[0] + " " + separatedLine[1]);
        }
        Arrays.stream(separatedLine).skip(2).forEach(x -> {
            if (!nSetOfStrings.containsKey(x)) {
                nSetOfStrings.put(x.trim(), nSetOfStrings.size());
                nSetOfInts.put(nSetOfInts.size(), x);
            }
        });
    }
}
*/
