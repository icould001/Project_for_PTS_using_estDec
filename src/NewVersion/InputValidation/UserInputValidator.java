package NewVersion.InputValidation;

import NewVersion.UserInterface.UIManager;
import NewVersion.Util.Result;

/**
 * Created by Aykut Ismailov on 17.4.2020 г.
 */
public class UserInputValidator {
    public static Result validateAll(UIManager ui) {
        Result r = Result.OK;
        if (r == Result.OK) {
            r = validateTime(ui);
        }
        if (r == Result.OK) {
            r = validateFilePath(ui);
        }
        if (r == Result.OK) {
            r = validateMinSig(ui);
        }
        return r;
    }

    public static Result validateTime(UIManager ui) {
        Result r = Result.OK;
        if (ui.getStartEnd().start.compareTo(ui.getStartEnd().end) > 0) {
            r = Result.NOK;
        }
        return r;
    }

    public static Result validateFilePath(UIManager ui) {
        Result r = Result.OK;
        //TODO: do validation
        return r;
    }

    public static Result validateEventContext(UIManager ui) {
        Result r = Result.OK;
        //TODO: do validation
        return r;
    }

    public static Result validateComponent(UIManager ui) {
        Result r = Result.OK;
        //TODO: do validation
        return r;
    }

    public static Result validateMinSig(UIManager ui) {
        Result r = Result.OK;
        if (ui.getMinSig() < 0 || ui.getMinSig() > 1) {
            r = Result.NOK;
        }
        return r;
    }
}
