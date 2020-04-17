package NewVersion.DataFilter;

import NewVersion.DataManagement.DataHolder;
import NewVersion.DataManagement.Items.LogItem;
import NewVersion.DataManagement.Items.User;
import NewVersion.UserInterface.UIManager;
import NewVersion.Util.Result;
import ca.pfv.spmf.algorithms.frequentpatterns.estDec.Algo_estDec;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * Created by Aykut Ismailov on 17.4.2020 г.
 */
public class FilterDataManager {

    public static Result filter(UIManager ui) {
        Result r = Result.OK;

        double mins = 0.1;
        double minsig = ui.getMinSig() * mins;

        Algo_estDec algo = new Algo_estDec(mins, minsig);

        // To set the decay rate manually:
        algo.setDecayRate(2, 10000);

        List<LogItem> filteredLogs = new ArrayList<>();

        if (r == Result.OK) {
            r = filterByUser(ui, filteredLogs);
        }

        if (r == Result.OK) {
            r = filterByTime(ui, filteredLogs);
        } else {
            ui.tellToUser("Error filtering by user");
            filteredLogs.clear();
            filteredLogs.addAll(DataHolder.logItems);
            r = filterByTime(ui, filteredLogs);
        }


        if (r == Result.OK) {
            r = loadInAlgorithm(algo, filteredLogs);
        } else {
            ui.tellToUser("Error filtering by time");
            filteredLogs.clear();
            r = filterByUser(ui, filteredLogs);
            if (r != Result.OK) {
                filteredLogs.addAll(DataHolder.logItems);
            }
            r = loadInAlgorithm(algo, filteredLogs);
        }
        if (r == Result.OK) {
            // To perform mining and save the result to memory:
            Hashtable<int[], Double> result = null;
            try {
                result = algo.performMining_saveResultToMemory();
                algo.performMining_saveResultToFile("output");
            } catch (IOException e) {
                e.printStackTrace();
            }

            List<String> results = new ArrayList<>();
            // print statistics
            results.add(algo.getStats());

            // Print the results to the console
            results.add("Itemsets found: ");
            for (Map.Entry<int[], Double> entry : result.entrySet()) {
                StringBuilder s = new StringBuilder();
                for (int item : entry.getKey()) {
                    s.append(DataHolder.nSetOfInts.get(item) + "|  |");
                }
                s.append("#SUP: " + entry.getValue());
                results.add(s.toString());
            }
            r = ui.loadDataForUser(results);
        }
        return r;
    }

    private static Result loadInAlgorithm(Algo_estDec algo, List<LogItem> logItems) {
        logItems.sort(Comparator.comparing(LogItem::getLogged_on));
        for (LogItem l : DataHolder.logItems) {
            algo.processTransaction(new int[]{
                    DataHolder.nSetOfStrings.get(l.getLogged_on()),        //date and time
                    DataHolder.nSetOfStrings.get(l.getEvent_context()),    //event context
                    //DataHolder.nSetOfStrings.get(l.getComponent()),        //component
                    DataHolder.nSetOfStrings.get(l.getEvent_name()),       //event name
                    //DataHolder.nSetOfStrings.get(l.getDescription()),      //description
                    //DataHolder.nSetOfStrings.get(l.getOrigin()),           //origin
                    //DataHolder.nSetOfStrings.get(l.getIp())                //ip address
            });
            try {
                algo.performMining_saveResultToMemory();
            } catch (IOException e) {
                e.printStackTrace();
                return Result.NOK;
            }

        }
        return Result.OK;
    }

    private static Result filterByUser(UIManager ui, List<LogItem> logs) {
        Result r = Result.OK;

        if (ui.getUser() != -1) {
            User u = new User();
            r = DataHolder.getUserById(ui.getUser(), u);
            if (r == Result.OK) {
                logs.addAll(u.getLogItems());
            } else {
                ui.tellToUser("User wasn't found");
                r = Result.OK;
            }
        } else {
            logs.addAll(DataHolder.logItems);
            r = Result.OK;
        }

        return r;
    }

    private static Result filterByTime(UIManager ui, List<LogItem> logs) {
        Result r = Result.OK;

        for (LogItem log : logs) {
            try {
                int year = Integer.parseInt(log.getLogged_on().split(" ")[0].split("/")[2]);
                int month = Integer.parseInt(log.getLogged_on().split(" ")[0].split("/")[1]);
                int day = Integer.parseInt(log.getLogged_on().split(" ")[0].split("/")[0]);
                int hour = Integer.parseInt(log.getLogged_on().split(" ")[1].split(":")[0]);
                int minute = Integer.parseInt(log.getLogged_on().split(" ")[1].split(":")[1]);

                if (ui.getStartEnd().start.compareTo(
                        LocalDateTime.of(
                            year, month, day, hour, minute)) > 0
                        ||
                    ui.getStartEnd().end.compareTo(
                        LocalDateTime.of(
                            year, month, day, hour, minute)) < 0) {

                    logs.remove(log);
                }
            } catch (DateTimeParseException e) {
                r = Result.NOK;
                System.out.println(log.getLogged_on());
            }
        }

        return r;
    }


}
