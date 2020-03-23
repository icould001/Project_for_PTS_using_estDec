package logReader;

import LogItems.LogItem;
import ca.pfv.spmf.algorithms.frequentpatterns.estDec.Algo_estDec;

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

    public static void loadData(Algo_estDec algo) {

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
                    algo.processTransaction(new int[]{
                            nSetOfStrings.get(separatedLine[0]),    //date
                            nSetOfStrings.get(separatedLine[1]),    //time
                            nSetOfStrings.get(separatedLine[2]),    //event context
                            nSetOfStrings.get(separatedLine[3]),    //component
                            nSetOfStrings.get(separatedLine[4]),    //event name
                            nSetOfStrings.get(separatedLine[5]),    //description
                            nSetOfStrings.get(separatedLine[6]),    //origin
//                            nSetOfStrings.get(separatedLine[7])     //ip address
                    });
                } else {
                    logItems.add(new LogItem(separatedLine[0], separatedLine[1], separatedLine[2], separatedLine[3], separatedLine[4], separatedLine[5], separatedLine[6], ""));
                    Arrays.stream(separatedLine).forEach(x -> {
                        if (!nSetOfStrings.containsKey(x)) {
                            nSetOfStrings.put(x, nSetOfStrings.size());
                            nSetOfInts.put(nSetOfInts.size(), x);
                        }
                    });
                    algo.processTransaction(new int[]{
                            nSetOfStrings.get(separatedLine[0]),    //date
                            nSetOfStrings.get(separatedLine[1]),    //time
                            nSetOfStrings.get(separatedLine[2]),    //event context
                            nSetOfStrings.get(separatedLine[3]),    //component
                            nSetOfStrings.get(separatedLine[4]),    //event name
                            nSetOfStrings.get(separatedLine[5]),    //description
                            nSetOfStrings.get(separatedLine[6]),    //origin
//                            nSetOfStrings.get(separatedLine[7])     //ip address
                    });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
