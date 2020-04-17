package NewVersion.DataManagement.Items;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aykut Ismailov on 17.4.2020 г.
 */
public class User {
    private int user_id;
    private List<LogItem> logItems;

    public User(int user_id, LogItem firstLog) {
        this.user_id = user_id;
        logItems = new ArrayList<>();
        logItems.add(firstLog);
    }

    public User(){user_id=-1;logItems=null;}

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public List<LogItem> getLogItems() {
        return logItems;
    }

    public void setLogItems(List<LogItem> logItems) {
        this.logItems = logItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return user_id == user.user_id;
    }

    @Override
    public int hashCode() {
        return user_id;
    }
}
