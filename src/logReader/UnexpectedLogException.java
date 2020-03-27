package logReader;

/**
 * Created by Aykut Ismailov on 27.3.2020 г.
 */
public class UnexpectedLogException extends Throwable {
    public UnexpectedLogException(String log) {
        super("Did not expect this kind of log:\n" + log + "\n");
    }
}
