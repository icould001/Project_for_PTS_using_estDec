package ProjectUI;

import NewVersion.InputValidation.UserInputValidator;
import NewVersion.UserInterface.Init;
import NewVersion.UserInterface.UIManager;
import NewVersion.Util.Result;
import NewVersion.Util.TimePair;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilterWindow extends JFrame implements UIManager {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    //fields
    private final JTextField userID;
    private final JTextField y_start;
    private final JTextField m_start;
    private final JTextField d_start;
    private final JTextField H_start;
    private final JTextField M_start;
    private final JTextField S_start;
    private final JTextField y_end;
    private final JTextField m_end;
    private final JTextField d_end;
    private final JTextField H_end;
    private final JTextField M_end;
    private final JTextField S_end;
    private final JTextField eventContext;
    private final JTextField component_Field;
    private final JTextField minSup;
    private final JTextField pathToFile;
    private final FilterWindow me;

    //values
    private int searched_user;
    private TimePair start_end_time;
    private String path_to_file;
    private Double min_sig;
    private List<String> filteredData;
    private String event_context;
    private String component;
    private boolean isAlgorithmWorking;

    public FilterWindow() {
        super("Filter Window");
        setDefaults();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        me = this;
        setPreferredSize(new Dimension(900, 400));
        pack();
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        setVisible(true);

        JButton button = new JButton("Filter");
        button.setBounds(50, 300, 600, 40);
        getContentPane().add(button);

        JLabel label = new JLabel();
        label.setFont(new Font("Serif", Font.BOLD, 15));
        label.setText("Filters");
        label.setBounds(5, 5, 55, 30);
        getContentPane().add(label);

        JLabel lblUserId = new JLabel("User ID:");
        lblUserId.setBounds(5, 45, 70, 20);
        getContentPane().add(lblUserId);

        JLabel lblTime = new JLabel("Time between:");
        lblTime.setBounds(5, 68, 100, 20);
        getContentPane().add(lblTime);

        JLabel lblStartTime = new JLabel("Start date/time:");
        lblStartTime.setBounds(5, 91, 100, 20);
        getContentPane().add(lblStartTime);

        JLabel lblEndTime = new JLabel("End date/time:");
        lblEndTime.setBounds(5, 114, 100, 20);
        getContentPane().add(lblEndTime);

        JLabel lblEventContext = new JLabel("Event Context:");
        lblEventContext.setBounds(5, 137, 100, 20);
        getContentPane().add(lblEventContext);

        JLabel lblComponent = new JLabel("Component:");
        lblComponent.setBounds(5, 160, 100, 20);
        getContentPane().add(lblComponent);

        JLabel lblMinSig = new JLabel("Min_sup:");
        lblMinSig.setBounds(5, 184, 100, 20);
        getContentPane().add(lblMinSig);

        JLabel lblPath = new JLabel("Path to file:");
        lblPath.setBounds(5, 207, 100, 20);
        getContentPane().add(lblPath);

        JTextArea filtered = new JTextArea();
        filtered.setBounds(320, 10, 530, 260);
        getContentPane().add(filtered);
        filtered.setEditable(false);

        JScrollPane filtered_scroll = new JScrollPane(filtered);
        getContentPane().add(filtered_scroll);
        filtered_scroll.setBounds(filtered.getBounds());


        userID = new JTextField();
        userID.setBounds(129, 45, 175, 20);
        getContentPane().add(userID);
        userID.setColumns(20);

        y_start = new JTextField();
        y_start.setBounds(129, 91, 40, 20);
        getContentPane().add(y_start);
        y_start.setColumns(20);

        JLabel lblsep1 = new JLabel("/");
        lblsep1.setBounds(170, 91, 10, 20);
        getContentPane().add(lblsep1);

        m_start = new JTextField();
        m_start.setBounds(175, 91, 20, 20);
        getContentPane().add(m_start);
        m_start.setColumns(20);

        JLabel lblsep2 = new JLabel("/");
        lblsep2.setBounds(195, 91, 10, 20);
        getContentPane().add(lblsep2);

        d_start = new JTextField();
        d_start.setBounds(200, 91, 20, 20);
        getContentPane().add(d_start);
        d_start.setColumns(20);

        H_start = new JTextField();
        H_start.setBounds(224, 91, 20, 20);
        getContentPane().add(H_start);
        H_start.setColumns(20);

        JLabel lblsep3 = new JLabel(":");
        lblsep3.setBounds(245, 91, 10, 20);
        getContentPane().add(lblsep3);

        M_start = new JTextField();
        M_start.setBounds(250, 91, 20, 20);
        getContentPane().add(M_start);
        M_start.setColumns(20);

        JLabel lblsep4 = new JLabel(":");
        lblsep4.setBounds(270, 91, 10, 20);
        getContentPane().add(lblsep4);

        S_start = new JTextField();
        S_start.setBounds(275, 91, 20, 20);
        getContentPane().add(S_start);
        S_start.setColumns(20);

        y_end = new JTextField();
        y_end.setBounds(129, 114, 40, 20);
        getContentPane().add(y_end);
        y_end.setColumns(20);

        JLabel lblsep5 = new JLabel("/");
        lblsep5.setBounds(170, 114, 10, 20);
        getContentPane().add(lblsep5);

        m_end = new JTextField();
        m_end.setBounds(175, 114, 20, 20);
        getContentPane().add(m_end);
        m_end.setColumns(20);

        JLabel lblsep6 = new JLabel("/");
        lblsep6.setBounds(195, 114, 10, 20);
        getContentPane().add(lblsep6);

        d_end = new JTextField();
        d_end.setBounds(200, 114, 20, 20);
        getContentPane().add(d_end);
        d_end.setColumns(20);

        H_end = new JTextField();
        H_end.setBounds(224, 114, 20, 20);
        getContentPane().add(H_end);
        H_end.setColumns(20);

        JLabel lblsep8 = new JLabel(":");
        lblsep8.setBounds(245, 114, 10, 20);
        getContentPane().add(lblsep8);

        M_end = new JTextField();
        M_end.setBounds(250, 114, 20, 20);
        getContentPane().add(M_end);
        M_end.setColumns(20);

        JLabel lblsep9 = new JLabel(":");
        lblsep9.setBounds(270, 114, 10, 20);
        getContentPane().add(lblsep9);

        S_end = new JTextField();
        S_end.setBounds(275, 114, 20, 20);
        getContentPane().add(S_end);
        S_end.setColumns(20);

        eventContext = new JTextField();
        eventContext.setBounds(129, 137, 175, 20);
        getContentPane().add(eventContext);
        eventContext.setColumns(20);

        component_Field = new JTextField();
        component_Field.setBounds(129, 160, 175, 20);
        getContentPane().add(component_Field);
        component_Field.setColumns(20);

        minSup = new JTextField();
        minSup.setBounds(129, 184, 175, 20);
        getContentPane().add(minSup);
        minSup.setColumns(20);

        pathToFile = new JTextField();
        pathToFile.setBounds(5, 230, 300, 20);
        getContentPane().add(pathToFile);
        pathToFile.setColumns(50);

        JButton bChooseFile = new JButton("Choose File");
        bChooseFile.setBounds(129, 207, 124, 20);
        getContentPane().add(bChooseFile);

        repaint();
        // action listener
        button.addActionListener(e -> {
            if (!isAlgorithmWorking) {
                filtered.setText("Loading...");
                Result r = convertAll();
                if (r == Result.OK) {

                    isAlgorithmWorking = true;
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {

                            Init.prepareToFilterData(me);
                            StringBuilder output = new StringBuilder();
                            for (String s : filteredData) {
                                output.append(s).append('\n');
                            }
                            filtered.setText(output.toString());
                            me.repaint();
                            me.isAlgorithmWorking = false;
                        }
                    });
                    t.start();
                } else {
                    filtered.setText("No output");
                    setDefaults();
                }
            } else {
                tellToUser("Currently algorithm is working. The output will be shown soon.");
            }
        });

        bChooseFile.addActionListener(a -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("D:\\Icould\\Java\\PTS_estDec\\src\\NewVersion\\DataManagement\\Items\\"));
            int value = chooser.showOpenDialog(null);
            if (value == JFileChooser.APPROVE_OPTION) {
                File f = chooser.getSelectedFile();
                String filename = f.getAbsolutePath();
                pathToFile.setText(filename);
            }
        });
    }

    public static void main(String[] args) {
        new FilterWindow();
    }

    private void setDefaults() {
        searched_user = -1;
        start_end_time = new TimePair();
        path_to_file = Init.PATH_TO_DEFAULT_LOG;
        min_sig = 0.5;
        event_context = "";
        component = "";
        filteredData = null;
        isAlgorithmWorking = false;
    }

    private Result convertAll() {
        Result r = convertUserID();
        if (r == Result.OK) {
            r = convertTimePair();
        }
        if (r == Result.OK) {
            r = convertPathToFile();
        }
        if (r == Result.OK) {
            r = convertMinSup();
        }
        if (r == Result.OK) {
            r = convertEventContext();
        }
        if (r == Result.OK) {
            r = convertComponent();
        }
        return r;
    }

    private Result convertUserID() {
        if (userID.getText().isEmpty()) {
            return Result.OK;
        }
        Result r = Result.OK;

        try {
            searched_user = Integer.parseInt(userID.getText());
        } catch (NumberFormatException e) {
            r = Result.NOK;
            tellToUser("Invalid user id.");
        }
        return r;
    }

    private Result convertTimePair() {
        Result r = Result.OK;
        if (!(y_start.getText().isEmpty() || m_start.getText().isEmpty() || d_start.getText().isEmpty() || H_start.getText().isEmpty() || M_start.getText().isEmpty() || S_start.getText().isEmpty())) {
            try {
                int y = Integer.parseInt(y_start.getText());
                int m = Integer.parseInt(m_start.getText());
                int d = Integer.parseInt(d_start.getText());
                int H = Integer.parseInt(H_start.getText());
                int M = Integer.parseInt(M_start.getText());
                int S = Integer.parseInt(S_start.getText());
                start_end_time.start = LocalDateTime.of(y, m, d, H, M, S);
            } catch (NumberFormatException | DateTimeException e) {
                r = Result.NOK;
                start_end_time.start = LocalDateTime.MIN;
                tellToUser("Invalid start time");
            }
        }
        if (r == Result.OK) {
            if (!(y_end.getText().isEmpty() || m_end.getText().isEmpty() || d_end.getText().isEmpty() || H_end.getText().isEmpty() || M_end.getText().isEmpty() || S_end.getText().isEmpty())) {
                try {
                    int y = Integer.parseInt(y_end.getText());
                    int m = Integer.parseInt(m_end.getText());
                    int d = Integer.parseInt(d_end.getText());
                    int H = Integer.parseInt(H_end.getText());
                    int M = Integer.parseInt(M_end.getText());
                    int S = Integer.parseInt(S_end.getText());
                    start_end_time.end = LocalDateTime.of(y, m, d, H, M, S);
                } catch (NumberFormatException | DateTimeException e) {
                    r = Result.NOK;
                    start_end_time.end = LocalDateTime.MAX;
                    tellToUser("Invalid end time");
                }
            }
        }
        if (r == Result.OK) {
            r = UserInputValidator.validateTime(this);
        }
        return r;
    }

    private Result convertPathToFile() {
        if (pathToFile.getText().isEmpty()) {
            return Result.OK;
        }

        Result r = Result.OK;

        path_to_file = pathToFile.getText();
        r = UserInputValidator.validateFilePath(this);
        if (r != Result.OK) {
            tellToUser("Invalid path.");
        }
        return r;
    }

    private Result convertMinSup() {
        if (minSup.getText().isEmpty()) {
            return Result.OK;
        }

        Result r = Result.OK;
        try {
            min_sig = Double.parseDouble(minSup.getText());
            r = UserInputValidator.validateMinSup(this);
            if (r != Result.OK) {
                tellToUser("Invalid min sig.");
            }
        } catch (NumberFormatException e) {
            r = Result.NOK;
            tellToUser("Invalid min sig.");
        }
        return r;
    }

    private Result convertEventContext() {
        if (eventContext.getText().isEmpty()) {
            return Result.OK;
        }

        Result r = Result.OK;
        try {
            event_context = eventContext.getText();
            r = UserInputValidator.validateEventContext(this);
            if (r != Result.OK) {
                tellToUser("Invalid event context.");
            }
        } catch (NumberFormatException e) {
            r = Result.NOK;
            tellToUser("Invalid event context.");
        }
        return r;
    }

    private Result convertComponent() {
        if (component_Field.getText().isEmpty()) {
            return Result.OK;
        }
        Result r = Result.OK;

        try {
            component = component_Field.getText();
            r = UserInputValidator.validateComponent(this);
            if (r != Result.OK) {
                tellToUser("Invalid component field.");
            }
        } catch (NumberFormatException e) {
            r = Result.NOK;
            tellToUser("Invalid component field.");
        }
        return r;
    }

 /*   public static void setFilteredData(List<String> filteredData) {
        FilterWindow.filteredData = filteredData;
    }*/

    public int getUser() {
        return searched_user;
    }

    public TimePair getStartEnd() {
        return start_end_time;
    }


    public String getComponent() {
        return component;
    }

    @Override
    public void tellToUser(String message) {
        JOptionPane.showMessageDialog(this, message);
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
    public Double getMinSup() {
        return min_sig;
    }

    @Override
    public String getEventContext() {
        return event_context;
    }

    @Override
    public String getPathToFile() {
        return path_to_file;
    }

}
