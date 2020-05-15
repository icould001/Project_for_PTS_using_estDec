package NewVersion.UserInterface;

import NewVersion.DataFilter.FilterDataManager;
import NewVersion.DataManagement.LoadDataManager;
import NewVersion.InputValidation.UserInputValidator;
import NewVersion.Util.Result;
import ProjectUI.Login;

import java.util.Scanner;

/**
 * Created by Aykut Ismailov on 17.4.2020 г.
 */
public class Init {
    public final static Scanner in = new Scanner(System.in);
    public final static String PATH_TO_DEFAULT_LOG = "src/NewVersion/DataManagement/Items/logs_BCS37_20181103.csv";
    public final static int log_in_info = 1;

    public static void main(String[] args) {
        new Login();
    }

    /* return 1->success; -1->fail; 0 ->neutral */
    public static int login(int cur) {
        if (cur == log_in_info) {
            return 1;
        } else if (cur == -1) {
            return -1;
        }
        return 0;
    }

    public static void prepareToFilterData(UIManager ui) {
        Result r = Result.OK;
        r = UserInputValidator.validateAll(ui);
        if (r == Result.OK) {
            r = LoadDataManager.loadData(ui);
        } else {
            // TODO: exit more gracefully
            ui.tellToUser("Error with loading data.\nApp is exiting");
            System.exit(1);
        }
        if (r == Result.OK) {
            r = FilterDataManager.filter(ui);
        } else {
            // TODO: exit more gracefully
            ui.tellToUser("Error with filtering data.\nApp is exiting");
            System.exit(1);
        }

        if (r == Result.OK) {
            ui.showUser();
        } else {
            // TODO: exit more gracefully
            ui.tellToUser("Error with showing data.\nApp is exiting");
            System.exit(1);
        }
    }
}
