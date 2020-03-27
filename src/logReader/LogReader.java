package logReader;

import LogItems.LogItem;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import static Logic.mainLogic.*;

/**
 * Created by Aykut Ismailov on 22.3.2020 г.
 */
public class LogReader {
    static String log = "src/logReader/logs_BCS37_20181103.csv";

    public static void loadData() {

        try (FileInputStream in = new FileInputStream(log)) {
            Scanner reader = new Scanner(in);
            for (; reader.hasNext(); ) {
                String[] separatedLine = reader.nextLine().split(",");
                if (separatedLine.length == 8) {
                    logItems.add(new LogItem(separatedLine[0], separatedLine[1], separatedLine[2], separatedLine[3], separatedLine[4], separatedLine[5], separatedLine[6], separatedLine[7]));
                    Arrays.stream(separatedLine).forEach(x -> {
                        if (!nSetOfStrings.containsKey(x)) {
                            nSetOfStrings.put(x, nSetOfStrings.size());
                            nSetOfInts.put(nSetOfInts.size(), x);
                        }
                    });

                } else {
                    logItems.add(new LogItem(separatedLine[0], separatedLine[1], separatedLine[2], separatedLine[3], separatedLine[4], separatedLine[5], separatedLine[6], ""));
                    Arrays.stream(separatedLine).forEach(x -> {
                        if (!nSetOfStrings.containsKey(x)) {
                            nSetOfStrings.put(x, nSetOfStrings.size());
                            nSetOfInts.put(nSetOfInts.size(), x);
                        }
                    });

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
