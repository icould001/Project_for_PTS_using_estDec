package Logic;

/*
import NewVersion.DataManagement.Items.LogItem;
import NewVersion.Util.Result;
import ca.pfv.spmf.algorithms.frequentpatterns.estDec.Algo_estDec;

import java.io.IOException;
import java.util.*;

*/

/**
 * Created by Aykut Ismailov on 22.3.2020 г.
 */
public class mainLogic {
    /*public static Map<String, Integer> nSetOfStrings = new HashMap<>();
    public static Map<Integer, String> nSetOfInts = new HashMap<>();
    public static List<LogItem> logItems = new ArrayList<>();
    public static int userId = 2;

    public static void main(String[] args) {
        double mins = 0.1;
        double minsig = 0.4 * mins;

        Algo_estDec algo = new Algo_estDec(mins, minsig);

        // To set the decay rate manually:
        algo.setDecayRate(2, 10000);

        loadData();
        loadInAlgorithm(algo);

        // To perform mining and save the result to memory:
        Hashtable<int[], Double> result = null;
        try {
            result = algo.performMining_saveResultToMemory();
            algo.performMining_saveResultToFile("output");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // print statistics
        algo.printStats();

        // Print the results to the console
        System.out.println("Itemsets found: ");
        for (Map.Entry<int[], Double> entry : result.entrySet()) {
            for (int item : entry.getKey()) {
                System.out.print(nSetOfInts.get(item) + "|  |");
            }
            System.out.println("#SUP: " + entry.getValue());
        }
    }

    private static Result loadInAlgorithm(Algo_estDec algo) {
        logItems.sort(Comparator.comparing(LogItem::getLogged_on));
        for (LogItem l : logItems) {
            if (getUserIdWhoDid(l) == userId) {
                algo.processTransaction(new int[]{
                        nSetOfStrings.get(l.getLogged_on()),        //date and time
                        nSetOfStrings.get(l.getEvent_context()),    //event context
                        //nSetOfStrings.get(l.getComponent()),        //component
                        nSetOfStrings.get(l.getEvent_name()),       //event name
                        //nSetOfStrings.get(l.getDescription()),      //description
                        //nSetOfStrings.get(l.getOrigin()),           //origin
                        //nSetOfStrings.get(l.getIp())                //ip address
                });
                try {
                    algo.performMining_saveResultToMemory();
                } catch (IOException e) {
                    e.printStackTrace();
                    return Result.NOK;
                }
            }
        }
        return Result.OK;
    }*/


}
