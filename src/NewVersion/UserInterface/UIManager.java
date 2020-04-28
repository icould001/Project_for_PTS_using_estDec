package NewVersion.UserInterface;

import NewVersion.Util.Result;
import NewVersion.Util.TimePair;

import java.util.List;

/**
 * Created by Aykut Ismailov on 13.4.2020 г.
 */
public interface UIManager {
    int getUser();

    TimePair getStartEnd();

    String getPathToFile();

    Double getMinSig();

    String getEventContext();

    String getComponent();

    void tellToUser(String message);

    void tellToUser(String message,Object way);

    Result loadDataForUser(List<String> data);

    Result showUser();
}
