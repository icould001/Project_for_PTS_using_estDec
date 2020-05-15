package NewVersion.UserInterface;

import NewVersion.InputValidation.UserInputValidator;
import NewVersion.Util.Result;
import NewVersion.Util.TimePair;

import javax.xml.bind.ValidationException;
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


    private int searched_user;
    private TimePair start_end_time;
    private String path_to_file;
    private Double min_sup;
    private List<String> filteredData;
    private String event_context;
    private String component;

    public ComProm() {
        searched_user = -1;
        start_end_time = new TimePair();
        path_to_file = Init.PATH_TO_DEFAULT_LOG;
        min_sup = 0.5;
        event_context = "";
        component = "";
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
                            "Enter 5 to set filter for Event context;\n" +
                            "Enter 6 to set filter for Component;\n" +
                            "Enter something else to finish with changes but you have to know that I am kind of dumb right now and you can't return here unless you restart me\n");
            String s = Init.in.nextLine();
            try {
                int i = Integer.parseInt(s);
                switch (i) {
                    case 1:
                        input_for_user_filter();
                        break;
                    case 2:
                        input_for_time_filter();
                        break;
                    case 3:
                        input_for_file_path();
                        break;
                    case 4:
                        input_for_min_sup_filter();
                        break;
                    case 5:
                        input_for_event_context();
                        break;
                    case 6:
                        input_for_component();
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

    private void input_for_component() {
        String s;
        System.out.println("Entered 6\n" +
                "Let's set component value\n" +
                "Enter component:");
        s = Init.in.nextLine();
        try {
            component = s;
            //TODO: exit more gracefully
            if (UserInputValidator.validateComponent(this) != Result.OK) {
                throw new ValidationException("");
            }
            System.out.println("Done setting event context.");
        } catch (ValidationException e) {
            System.out.println("This component is invalid, component is not set.");
            component = "";
        }
    }

    private void input_for_event_context() {
        String s;
        System.out.println("Entered 5\n" +
                "Let's set event context value\n" +
                "Enter event context:");
        s = Init.in.nextLine();
        try {
            event_context = s;
            //TODO: exit more gracefully
            if (UserInputValidator.validateFilePath(this) != Result.OK) {
                throw new ValidationException("");
            }
            System.out.println("Done setting event context.");
        } catch (ValidationException e) {
            System.out.println("This event context is invalid, event context is not set.");
            event_context = "";
        }
    }

    private void input_for_min_sup_filter() {
        String s;
        System.out.println("Entered 4\n" +
                "Let's set min_sig value\n" +
                "Enter new min_sig in the range between 0 and 1:");
        s = Init.in.nextLine();
        try {
            min_sup = Double.parseDouble(s);
            //TODO: exit more gracefully
            if (UserInputValidator.validateMinSup(this) != Result.OK) {
                throw new NumberFormatException();
            }
            System.out.println("Done setting min_sig.");
        } catch (NumberFormatException e) {
            System.out.println("This wasn't a number, min_sig is set to 0.5.");
            min_sup = 0.5;
        }
    }

    private void input_for_file_path() {
        String s;
        System.out.println("Entered 3\n" +
                "Let's set the path to the file with the data.\n" +
                "Enter the path to the file with the data:");
        s = Init.in.nextLine();
        try {
            //TODO: exit more gracefully
            path_to_file = s;
            if (UserInputValidator.validateFilePath(this) != Result.OK) {
                throw new ValidationException("Invalid path");
            }
            System.out.println("Done setting path.");
        } catch (ValidationException e) {
            System.out.println("This wasn't a path, path is set to default.");
            path_to_file = Init.PATH_TO_DEFAULT_LOG;
        }
    }

    private void input_for_time_filter() {
        String s;
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
    }

    private void input_for_user_filter() {
        String s;
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
    public Double getMinSup() {
        return min_sup;
    }

    @Override
    public void tellToUser(String message) {
        System.out.println("\n\n" + message + "\n\n");
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
            filteredData.forEach(System.out::println);
        }
        return r;
    }

    @Override
    public String getEventContext() {
        return event_context;
    }

    @Override
    public String getComponent() {
        return component;
    }
}
