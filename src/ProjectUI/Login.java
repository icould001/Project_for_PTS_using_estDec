package ProjectUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import NewVersion.UserInterface.*;

public class Login extends JFrame {

    /**
    	 * 
    	 */
    private static final long serialVersionUID = 1L;

    public Login() {
        JFrame f = new JFrame("Login Window");

        JButton b = new JButton("Login");
        b.setBounds(100, 100, 140, 40);
        f.getContentPane().add(b);

        JLabel label = new JLabel();
        label.setText("Barcode :");
        label.setBounds(10, 10, 100, 100);
        f.getContentPane().add(label);

        JLabel label1 = new JLabel();
        label1.setBounds(10, 160, 266, 50);
        f.getContentPane().add(label1);

        JTextField textfield = new JTextField();
        textfield.setBounds(110, 50, 130, 30);
        f.getContentPane().add(textfield);

        f.setSize(300, 300);
        f.getContentPane().setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // action listener
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean isInteger = Pattern.matches("^\\d*$", textfield.getText());
                if (isInteger) {
                    int pass = Integer.parseInt(textfield.getText());
                    int loginResult = Init.login(pass);
                    if (loginResult == 1) {
                        new FilterWindow().setVisible(true);
                        f.setVisible(false);
                    } else if (loginResult != 1) {
                        label1.setText("Invalid barcode");
                    }
                } else {
                    label1.setText("Barcode should contains only numbers!!!");
                }
            }
        });
    }

}
