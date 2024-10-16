package Views;

import Structures.Colors;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {
    public CustomButton(String text) {
        super(text);
        initializeButton();
    }
    public CustomButton(String text, ImageIcon icon) {
        super(text, icon);
        initializeButton();
    }
    private void initializeButton() {
        setBackground(Colors.getBaseColor());
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.PLAIN, 13));
        setFocusPainted(false);
        setHorizontalTextPosition(SwingConstants.RIGHT);
        setVerticalTextPosition(SwingConstants.CENTER);
//        setPreferredSize(new Dimension(250, 80));
    }
}
