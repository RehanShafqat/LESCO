package Structures;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class CustomField extends JComponent {
    private JTextComponent field;

    public CustomField(String type, int cols, int height) {
        if (type.equalsIgnoreCase("password")) {
            field = new JPasswordField(cols);
        } else {
            field = new JTextField(cols);
        }
        decorate(height);
        setLayout(new BorderLayout());
        add(field, BorderLayout.CENTER);
    }

    private void decorate(int height) {
        field.setPreferredSize(new Dimension(200, height)); // Adjust width as needed
        field.setMaximumSize(new Dimension(200, height));
        field.setMinimumSize(new Dimension(200, height));
        field.setBorder(new EmptyBorder(5, 5, 5, 5));
        field.setBackground(new Color(211, 211, 211));
        field.setForeground(Color.BLACK);
        field.setFont(new Font("Arial", Font.PLAIN, 13));
    }
    public String getText() {
        return field.getText();
    }

    // Getter method for password input
    public char[] getPassword() {
        if (field instanceof JPasswordField) {
            return ((JPasswordField) field).getPassword();
        }
        return new char[0]; // Return an empty char array if not a password field
    }
}
