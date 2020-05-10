package NewVersion.UserInterface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.xml.bind.ValidationException;

import NewVersion.InputValidation.UserInputValidator;
import NewVersion.Util.Result;
import NewVersion.Util.TimePair;
import ProjectUI.FilterWindow;

/**
 * Created by Aykut Ismailov on 17.4.2020 г.
 */
//TODO:global - add logging
public class ComProm implements UIManager {

    private int searched_user;
    private TimePair start_end_time;
    private String path_to_file;
    private Double min_sig;
    private List<String> filteredData;
    private String event_context;
    private String component;

    ComProm() {
        searched_user = -1;
        start_end_time = new TimePair();
        path_to_file = Init.PATH_TO_DEFAULT_LOG;
        min_sig = 0.5;
        event_context = "";
        component = "";
        filteredData = null;
        initialize_me();
    }

    private void initialize_me() {
        int i = FilterWindow.getChosenOption();
        try {
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
                input_for_min_sig_filter();
                break;
            case 5:
                input_for_event_context();
                break;
            case 6:
                input_for_component();
                break;
            default:
                System.out.println("Guess you are ready so we will go to the validation.");
                break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Guess you are ready so we will go to the validation.");
        }

    }

    private void input_for_component() {
        JTextField comp = FilterWindow.getComponent();
        String s = comp.getText();
        try {
            component = s;
            // TODO: exit more gracefully
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
        JTextField context = FilterWindow.getEventContent();
        String s = context.getText();
        try {
            event_context = s;
            // TODO: exit more gracefully
            if (UserInputValidator.validateFilePath(this) != Result.OK) {
                throw new ValidationException("");
            }
            System.out.println("Done setting event context.");
        } catch (ValidationException e) {
            System.out.println("This event context is invalid, event context is not set.");
            event_context = "";
        }
    }

    private void input_for_min_sig_filter() {
        JTextField minSig = FilterWindow.getMinSig();
        String s = minSig.getText();
        try {
            min_sig = Double.parseDouble(s);
            // TODO: exit more gracefully
            if (UserInputValidator.validateMinSig(this) != Result.OK) {
                throw new NumberFormatException();
            }
            System.out.println("Done setting min_sig.");
        } catch (NumberFormatException e) {
            System.out.println("This wasn't a number, min_sig is set to 0.5.");
            min_sig = 0.5;
        }
    }

    private void input_for_file_path() {
        JTextField filePath = FilterWindow.getPathToFile();
        String s = filePath.getText();
        try {
            // TODO: exit more gracefully
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
        JTextField time = FilterWindow.getTime();
        String s = time.getText();
        try {
            start_end_time.start = LocalDateTime.of(LocalDate.parse(s.split(" ")[0]), LocalTime.parse(s.split(" ")[1]));
            System.out.println("Done setting start date and time of the period.");
        } catch (DateTimeParseException e) {
            System.out.println("This wasn't a valid input," + " start time wasn't set");
            start_end_time.start = LocalDateTime.MIN;
        }
        System.out.println("Enter end date and time in format \"YYYY-MM-DD hh:mm:ss\":");
        s = Init.in.nextLine();
        try {
            start_end_time.end = LocalDateTime.of(LocalDate.parse(s.split(" ")[0]), LocalTime.parse(s.split(" ")[1]));
            System.out.println("Done setting end date and time of the period.");
        } catch (DateTimeParseException e) {
            System.out.println("This wasn't a valid input," + " end time wasn't set");
            start_end_time.end = LocalDateTime.MAX;
        }
        try {
            // TODO: exit more gracefully
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
        JTextField userId = FilterWindow.getUserID();
        try {
            searched_user = Integer.parseInt(userId.getText());
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
    public Double getMinSig() {
        return min_sig;
    }

    @Override
    public void tellToUser(String message) {
        System.out.println("\n\n" + message + "\n\n");
    }

    @Override
    public void tellToUser(String message, Object way) {
        tellToUser(message);
    }

    @Override
    public Result loadDataForUser(List<String> data) {
        Result r = Result.OK;
        filteredData = new ArrayList<>(data);
        FilterWindow.setFilteredData(filteredData);
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
