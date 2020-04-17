package NewVersion.DataManagement;

import NewVersion.DataManagement.Items.LogItem;
import NewVersion.DataManagement.Items.User;
import NewVersion.Util.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Aykut Ismailov on 17.4.2020 г.
 */
public class DataHolder {

    public final static Map<String, Integer> nSetOfStrings = new HashMap<>();
    public final static Map<Integer, String> nSetOfInts = new HashMap<>();
    public final static List<LogItem> logItems = new ArrayList<>();
    public final static List<User> users = new ArrayList<>();

    public static Result manageLog(LogItem log) {
        IntRef id = new IntRef();
        Result r = Result.OK;

        r = getUserIdWhoDid(log, id);
        if (r == Result.OK) {
            r = addLogToUser(id.value, log);
        }
        if (r == Result.OK) {
            logItems.add(log);
        }

        return r;
    }


    public static Result getUserById(int id, User user) {
        for (User u : users) {
            if (u.getUser_id() == id) {
                user.setUser_id(u.getUser_id());
                user.setLogItems(u.getLogItems());
                return Result.OK;
            }
        }
        return Result.NOK;
    }


    private static Result addLogToUser(int user_id, LogItem log) {
        Result r = Result.OK;
        User u = new User();
        r = getUserById(user_id, u);
        if (r == Result.OK) {
            u.getLogItems().add(log);
        } else {
            u.setLogItems(new ArrayList<>());
            u.setUser_id(user_id);
            users.add(u);
            r = Result.OK;
        }
        return r;
    }


    //TODO: needs modifications
    private static Result getUserIdWhoDid(LogItem l, IntRef id) {
        if (l.getDescription().matches(".*The user with id '[0-9]+'.*")) {
            id.value = Integer.parseInt(l.getDescription().split("'")[1]);
            return Result.OK;
        } else {
            System.out.println(l.getDescription());
        }
        return Result.NOK;
    }

    public static void clearData(){
        nSetOfInts.clear();
        nSetOfStrings.clear();;
        logItems.clear();
        users.clear();
    }

    //Class for reference to integer
    private static class IntRef {
        int value;
    }
}
