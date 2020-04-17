package NewVersion.DataManagement.Items;

/**
 * Created by Aykut Ismailov on 22.3.2020 г.
 */
public class LogItem {
    String logged_on;
    String event_context;
    String component;
    String event_name;
    String description;
    String origin;
    String ip;

    public LogItem(String logged_on, String event_context, String component, String event_name, String description, String origin, String ip) {
        this.logged_on = logged_on;
        this.event_context = event_context;
        this.component = component;
        this.event_name = event_name;
        this.description = description;
        this.origin = origin;
        this.ip = ip;
    }

    public String getLogged_on() {
        return logged_on;
    }

    public String getEvent_context() {
        return event_context;
    }

    public String getComponent() {
        return component;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getDescription() {
        return description;
    }

    public String getOrigin() {
        return origin;
    }

    public String getIp() {
        return ip;
    }
}
