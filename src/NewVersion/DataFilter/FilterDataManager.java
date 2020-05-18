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

        double mins = ui.getMinSup();
        double minsig = 0.4 * mins;

        Algo_estDec algo = new Algo_estDec(mins, minsig);

        // To set the decay rate manually:
        algo.setDecayRate(2, 10000);

        List<LogItem> filteredLogs = new ArrayList<>();

        r = proper_filter_by_user(ui, r, filteredLogs);

        r = proper_filter_by_time(ui, r, filteredLogs);

        r = proper_filter_by_event_context(ui, r, filteredLogs);

        r = proper_filter_by_component(ui, r, filteredLogs);

        r = loadInAlgorithm(algo, filteredLogs);

        if (r == Result.OK) {
            // To perform mining and save the result to memory:
            Map<Double,int[] > result = null;
            try {
                result = htToMap(algo.performMining_saveResultToMemory());
                algo.performMining_saveResultToFile("output");
            } catch (IOException e) {
                e.printStackTrace();
            }

            List<String> results = new ArrayList<>();
            // print statistics
            results.add(algo.getStats());

            // Print the results to the console
            results.add("Itemsets found: ");
            assert result != null;
            List<Double> keys = new ArrayList<>(result.keySet());
            keys.sort(new Comparator<Double>() {
                @Override
                public int compare(Double o1, Double o2) {
                    return o2.compareTo(o1);
                }
            });
            for (Double key : keys) {
                StringBuilder s = new StringBuilder();
                for (int item : result.get(key)) {
                    s.append(DataHolder.nSetOfInts.get(item)).append("|  |");
                }
                s.append("#SUP: ").append(key);
                results.add(s.toString());
            }
            r = ui.loadDataForUser(results);
        } else {
            ui.tellToUser("Error loading data to algorithm.");
        }
        return r;
    }

    private static Map<Double, int[]> htToMap(Hashtable<int[], Double> ht) {
        Map<Double, int[]> result=new HashMap<>();
        for(Map.Entry<int[],Double> entry:ht.entrySet()){
            result.put(entry.getValue(),entry.getKey());
        }
        return result;
    }

    private static Result proper_filter_by_component(UIManager ui, Result r, List<LogItem> filteredLogs) {
        r = filterByComponent(ui, filteredLogs);

        if (r != Result.OK) {
            ui.tellToUser("Error filtering by component");
            filteredLogs.clear();
            r = proper_filter_by_user(ui, Result.OK, filteredLogs);
            r = proper_filter_by_time(ui, r, filteredLogs);
            r = proper_filter_by_event_context(ui, r, filteredLogs);
        }

        return r;
    }

    private static Result proper_filter_by_event_context(UIManager ui, Result r, List<LogItem> filteredLogs) {

        r = filterByEventContext(ui, filteredLogs);

        if (r != Result.OK) {
            ui.tellToUser("Error filtering by event context.");
            filteredLogs.clear();
            r = proper_filter_by_user(ui, Result.OK, filteredLogs);
            r = proper_filter_by_time(ui, r, filteredLogs);
        }
        return r;
    }

    private static Result proper_filter_by_time(UIManager ui, Result r, List<LogItem> filteredLogs) {

        r = filterByTime(ui, filteredLogs);

        if (r != Result.OK) {
            ui.tellToUser("Error filtering by time.");
            filteredLogs.clear();
            r = proper_filter_by_user(ui, Result.OK, filteredLogs);
        }

        return r;
    }

    private static Result proper_filter_by_user(UIManager ui, Result r, List<LogItem> filteredLogs) {
        if (r == Result.OK) {
            r = filterByUser(ui, filteredLogs);
        }
        if (r != Result.OK) {
            System.out.println("Error filtering by user.");
            filteredLogs.clear();
            filteredLogs.addAll(DataHolder.logItems);
            r = Result.OK;
        }
        return r;
    }

    private static Result loadInAlgorithm(Algo_estDec algo, List<LogItem> logItems) {
        logItems.sort(Comparator.comparing(LogItem::getLogged_on));
        for (LogItem l : logItems) {
            algo.processTransaction(new int[]{
                    //DataHolder.nSetOfStrings.get(l.getLogged_on()),        //date and time
                    DataHolder.nSetOfStrings.get(l.getEvent_context()),    //event context
                    DataHolder.nSetOfStrings.get(l.getComponent()),        //component
                    DataHolder.nSetOfStrings.get(l.getEvent_name()),       //event name
                    DataHolder.nSetOfStrings.get(l.getDescription()),      //description
                    //DataHolder.nSetOfStrings.get(l.getOrigin()),           //origin
                    //DataHolder.nSetOfStrings.get(l.getIp())                //ip address
            });
           /* try { // това забавя супер много
                algo.performMining_saveResultToMemory();
            } catch (IOException e) {
                e.printStackTrace();
                return Result.NOK;
            }*/

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
                ui.tellToUser("User wasn't found. Will continue with all users");
                r = Result.OK;
            }
        } else {
            logs.addAll(DataHolder.logItems);
            r = Result.OK;
        }

        return r;
    }

    private static Result filterByEventContext(UIManager ui, List<LogItem> logs) {
        Result r = Result.OK;
        if (!ui.getEventContext().equals("")) {
            logs.removeIf(log -> !ui.getEventContext().equals(log.getEvent_context()));
            System.err.println(logs.size());
        }
        return r;
    }

    private static Result filterByComponent(UIManager ui, List<LogItem> logs) {
        Result r = Result.OK;
        if (!"".equals(ui.getComponent())) {
            logs.removeIf(log -> !ui.getComponent().equals(log.getComponent()));
            System.err.println(logs.size());
        }
        return r;
    }

    private static Result filterByTime(UIManager ui, List<LogItem> logs) {
        Result r = Result.OK;
        for (Iterator<LogItem> i = logs.iterator(); i.hasNext(); ) {
            LogItem log = i.next();
            try {
                if (is_log_in_time_period(ui, log)) {
                    i.remove();
                }
            } catch (DateTimeParseException e) {
                r = Result.NOK;
                System.out.println(log.getLogged_on());
            }
        }

        return r;
    }

    private static boolean is_log_in_time_period(UIManager ui, LogItem log) throws DateTimeParseException {
        int year = Integer.parseInt(log.getLogged_on().split(" ")[0].split("/")[2]);
        int month = Integer.parseInt(log.getLogged_on().split(" ")[0].split("/")[1]);
        int day = Integer.parseInt(log.getLogged_on().split(" ")[0].split("/")[0]);
        int hour = Integer.parseInt(log.getLogged_on().split(" ")[1].split(":")[0]);
        int minute = Integer.parseInt(log.getLogged_on().split(" ")[1].split(":")[1]);

        return ui.getStartEnd().start.compareTo(
                LocalDateTime.of(
                        year, month, day, hour, minute)) > 0
                ||
                ui.getStartEnd().end.compareTo(
                        LocalDateTime.of(
                                year, month, day, hour, minute)) < 0;
    }

}
