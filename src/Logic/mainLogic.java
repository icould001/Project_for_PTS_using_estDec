package Logic;

import LogItems.LogItem;
import ca.pfv.spmf.algorithms.frequentpatterns.estDec.Algo_estDec;

import java.io.IOException;
import java.util.*;

import static logReader.LogReader.loadData;

/**
 * Created by Aykut Ismailov on 22.3.2020 г.
 */
public class mainLogic {
    public static Map<String,Integer> nSetOfStrings=new HashMap<>();
    public static Map<Integer,String> nSetOfInts=new HashMap<>();
    public static List<LogItem> logItems=new ArrayList<>();

    public static void main(String []args){
        double mins = 0.1;
        double minsig = 0.4 * mins;

        Algo_estDec algo = new Algo_estDec(mins, minsig);

        // To set the decay rate manually:
        algo.setDecayRate(2, 10000);

        loadData(algo);

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
}
