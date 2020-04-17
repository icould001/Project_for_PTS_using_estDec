package NewVersion.Util;

import java.time.LocalDateTime;

/**
 * Created by Aykut Ismailov on 13.4.2020 г.
 */
public class TimePair {
    public LocalDateTime start;
    public LocalDateTime end;

    public TimePair() {
        start = LocalDateTime.MIN;
        end = LocalDateTime.MAX;
    }

}
