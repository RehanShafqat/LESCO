package Views;

import Structures.Colors;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {

    public CustomButton(String text) {
            super(text);
            setBackground(Colors.getBaseColor());
            setForeground(Color.WHITE);
            setFont(new Font("Arial", Font.PLAIN, 13));
            setFocusPainted(false);
    }



}
