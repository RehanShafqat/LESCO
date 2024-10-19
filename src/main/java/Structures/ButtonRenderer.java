package Structures;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof JButton) {
            JButton button = (JButton) value;
//            button.setBackground(Color.red);
            setText(button.getText());
            setIcon(button.getIcon());
        } else {
            setText("");
        }

        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        } else {
            setBackground(UIManager.getColor("Button.background"));
            setForeground(UIManager.getColor("Button.foreground"));
        }
        return this;
    }
}
