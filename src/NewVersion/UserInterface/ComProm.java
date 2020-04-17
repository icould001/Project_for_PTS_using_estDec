package NewVersion.UserInterface;

import NewVersion.InputValidation.UserInputValidator;
import NewVersion.Util.Result;
import NewVersion.Util.TimePair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aykut Ismailov on 17.4.2020 г.
 */
//TODO:global - add logging
public class ComProm implements UIManager {


    int searched_user;
    TimePair start_end_time;
    String path_to_file;
    Double min_sig;
    List<String> filteredData;

    ComProm() {
        searched_user = -1;
        start_end_time = new TimePair();
        path_to_file = Init.PATH_TO_DEFAULT_LOG;
        min_sig = 0.5;
        filteredData = null;
        initialize_me();
    }

    private void initialize_me() {
        System.out.println("Good day your majesty,");
        for (boolean l = true; l; ) {
            System.out.println(
                    "Enter 1 to set filter for users;\n" +
                            "Enter 2 to set filter for time period;\n" +
                            "Enter 3 to set path to the file with data;\n" +
                            "Enter 4 to set min_sig value;\n" +
                            "Enter something else to finish with changes but you have to know that I am kind of dumb right now and you can't return here unless you restart me\n");
            String s = Init.in.nextLine();
            try {
                int i = Integer.parseInt(s);
                switch (i) {
                    case 1:
                        System.out.println("Entered 1\n" +
                                "Let's set user to filter\n" +
                                "Enter user id:");
                        s = Init.in.nextLine();
                        try {
                            searched_user = Integer.parseInt(s);
                            System.out.println("Filter for user is set.");
                        } catch (NumberFormatException e) {
                            System.out.println("This wasn't a valid input, no filter was set");
                            searched_user = -1;
                        }
                        break;
                    case 2:
                        System.out.println("Entered 2\n" +
                                "Let's set time period filter\n" +
                                "Enter start date and time in format \"YYYY-MM-DD hh:mm:ss\":");
                        s = Init.in.nextLine();
                        try {
                            start_end_time.start = LocalDateTime.of(
                                    LocalDate.parse(s.split(" ")[0]),
                                    LocalTime.parse(s.split(" ")[1]));
                            System.out.println("Done setting start date and time of the period.");
                        } catch (DateTimeParseException e) {
                            System.out.println("This wasn't a valid input," +
                                    " start time wasn't set");
                            start_end_time.start = LocalDateTime.MIN;
                        }
                        System.out.println(
                                "Enter end date and time in format \"YYYY-MM-DD hh:mm:ss\":");
                        s = Init.in.nextLine();
                        try {
                            start_end_time.end = LocalDateTime.of(
                                    LocalDate.parse(s.split(" ")[0]),
                                    LocalTime.parse(s.split(" ")[1]));
                            System.out.println("Done setting end date and time of the period.");
                        } catch (DateTimeParseException e) {
                            System.out.println("This wasn't a valid input," +
                                    " end time wasn't set");
                            start_end_time.end = LocalDateTime.MAX;
                        }
                        try {
                            //TODO: exit more gracefully
                            if (UserInputValidator.validateTime(this) != Result.OK) {
                                throw new Exception();
                            }
                            System.out.println("Done setting time period.");
                        } catch (Exception e) {
                            start_end_time.start = LocalDateTime.MIN;
                            start_end_time.end = LocalDateTime.MAX;
                            System.out.println("Time period is invalid. Time period was not set.");
                        }
                        break;
                    case 3:
                        System.out.println("Entered 3\n" +
                                "Let's set the path to the file with the data.\n" +
                                "Enter the path to the file with the data:");
                        s = Init.in.nextLine();
                        try {
                            //TODO: exit more gracefully
                            path_to_file = s;
                            if (UserInputValidator.validateFilePath(this) != Result.OK) {
                                throw new NumberFormatException();
                            }
                            System.out.println("Done setting path.");
                        } catch (NumberFormatException e) {
                            System.out.println("This wasn't a number, path is set to default.");
                            path_to_file = Init.PATH_TO_DEFAULT_LOG;
                        }
                        break;
                    case 4:
                        System.out.println("Entered 4\n" +
                                "Let's set min_sig value\n" +
                                "Enter new min_sig in the range between 0 and 1:");
                        s = Init.in.nextLine();
                        try {
                            min_sig = Double.parseDouble(s);
                            //TODO: exit more gracefully
                            if (UserInputValidator.validateMinSig(this) != Result.OK) {
                                throw new NumberFormatException();
                            }
                            System.out.println("Done setting min_sig.");
                        } catch (NumberFormatException e) {
                            System.out.println("This wasn't a number, min_sig is set to 0.5.");
                            min_sig = 0.5;
                        }
                        break;
                    default:
                        System.out.println("Guess you are ready so we will go to the validation.");
                        l = false;
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Guess you are ready so we will go to the validation.");
                l = false;
            }
        }
    }

    @Override
    public int getUser() {
        return searched_user;
    }

    @Override
    public TimePair getStartEnd() {
        return start_end_time;
    }

    @Override
    public String getPathToFile() {
        return path_to_file;
    }

    @Override
    public Double getMinSig() {
        return min_sig;
    }

    @Override
    public void tellToUser(String message) {
        System.out.println("\n\n" + message + "\n\n");
    }

    @Override
    public void tellToUser(String message,Object way) {
        tellToUser(message);
    }

    @Override
    public Result loadDataForUser(List<String> data) {
        Result r = Result.OK;
        filteredData = new ArrayList<>(data);
        return r;
    }

    @Override
    public Result showUser() {
        Result r = Result.OK;
        if (filteredData == null) {
            r = Result.NOK;
        } else {
            filteredData.forEach(line -> System.out.println(line));
        }
        return r;
    }


}
