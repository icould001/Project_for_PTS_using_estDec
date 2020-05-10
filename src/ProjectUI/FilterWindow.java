package ProjectUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import NewVersion.UserInterface.Init;

public class FilterWindow extends JFrame {

    /**
    	 * 
    	 */
    private static final long serialVersionUID = 1L;
    public static JTextField userID;
    public static JTextField time;
    public static JTextField eventContent;
    public static JTextField component;
    public static JTextField minSig;
    public static JTextField pathToFile;
    public static int chosenOption;
    public static List<String> filteredData;

    public static void setFilteredData(List<String> filteredData) {
        FilterWindow.filteredData = filteredData;
    }

    public static JTextField getUserID() {
        return userID;
    }

    public static JTextField getTime() {
        return time;
    }

    public static JTextField getEventContent() {
        return eventContent;
    }

    public static JTextField getComponent() {
        return component;
    }

    public static JTextField getMinSig() {
        return minSig;
    }

    public static JTextField getPathToFile() {
        return pathToFile;
    }

    public static int getChosenOption() {
        return chosenOption;
    }

    public FilterWindow() {
        JFrame jframe = new JFrame("Filter Window");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jframe.setPreferredSize(new Dimension(750, 350));
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.getContentPane().setLayout(null);
        jframe.setVisible(true);

        JButton b = new JButton("Filter");
        b.setBounds(38, 217, 121, 39);
        jframe.getContentPane().add(b);

        JLabel label = new JLabel();
        label.setFont(new Font("Serif", Font.BOLD, 15));
        label.setText("Filters");
        label.setBounds(5, 5, 53, 30);
        jframe.getContentPane().add(label);

        JLabel lblNewLabel = new JLabel("User ID:");
        lblNewLabel.setBounds(5, 45, 69, 13);
        jframe.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Time:");
        lblNewLabel_1.setBounds(5, 68, 69, 13);
        jframe.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Event Context:");
        lblNewLabel_2.setBounds(5, 91, 69, 13);
        jframe.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Component:");
        lblNewLabel_3.setBounds(5, 114, 69, 13);
        jframe.getContentPane().add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Min_sig:");
        lblNewLabel_4.setBounds(5, 138, 69, 13);
        jframe.getContentPane().add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Path to file:");
        lblNewLabel_5.setBounds(5, 161, 69, 13);
        jframe.getContentPane().add(lblNewLabel_5);

        JTextArea filtered = new JTextArea();
        filtered.setBounds(195, 28, 531, 258);
        jframe.getContentPane().add(filtered);
        filtered.setEditable(false);

        userID = new JTextField();
        userID.setBounds(89, 42, 96, 19);
        jframe.getContentPane().add(userID);
        userID.setColumns(10);

        time = new JTextField();
        time.setBounds(89, 65, 96, 19);
        jframe.getContentPane().add(time);
        time.setColumns(10);

        eventContent = new JTextField();
        eventContent.setBounds(89, 88, 96, 19);
        jframe.getContentPane().add(eventContent);
        eventContent.setColumns(10);

        component = new JTextField();
        component.setBounds(89, 111, 96, 19);
        jframe.getContentPane().add(component);
        component.setColumns(10);

        minSig = new JTextField();
        minSig.setBounds(89, 135, 96, 19);
        jframe.getContentPane().add(minSig);
        minSig.setColumns(10);

        pathToFile = new JTextField();
        pathToFile.setBounds(89, 158, 96, 19);
        jframe.getContentPane().add(pathToFile);
        pathToFile.setColumns(10);

        // action listener
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!userID.getText().equals("")) {
                    chosenOption = 1;
                } else if (!time.getText().equals("")) {
                    chosenOption = 2;
                } else if (!eventContent.getText().equals("")) {
                    chosenOption = 5;
                } else if (!component.getText().equals("")) {
                    chosenOption = 6;
                } else if (!minSig.getText().equals("")) {
                    chosenOption = 4;
                } else if (!pathToFile.getText().equals("")) {
                    chosenOption = 3;
                }
                Init.prepareToFilterData();
                String output = "";
                for (String s : filteredData) {
                    output += s + '\n';
                }
                filtered.append(output);
            }
        });
    }
}
