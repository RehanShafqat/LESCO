package Structures;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditor extends DefaultCellEditor {

    private JButton button;
    private String label;
    private ActionListener btnListener = null;
    private ActionListener actionListener; // Add ActionListener

    public ButtonEditor(JCheckBox checkBox, ActionListener actionListener) {
        super(checkBox);
        this.actionListener = actionListener; // Set the action listener
        button = new JButton();
        button.setOpaque(true);
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
        button.addActionListener(e -> {
            actionListener.actionPerformed(e); // Call the custom action listener
            fireEditingStopped(); // Stop editing
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value instanceof JButton) {
            JButton btn = (JButton) value;
            label = btn.getText(); // Store the button's label
            button.setText(label); // Set the button's text
            button.setIcon(btn.getIcon()); // If you're using icons, set them too
        }

        if(btnListener != null) {
            button.removeActionListener(btnListener);
        }

        btnListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionListener.actionPerformed(new ActionEvent(table, ActionEvent.ACTION_PERFORMED, String.valueOf(row)));
                fireEditingStopped(); // Stop editing
            }
        };


        button.addActionListener(btnListener);

//        button.addActionListener(e -> {
//            actionListener.actionPerformed(new ActionEvent(table, ActionEvent.ACTION_PERFORMED, String.valueOf(row))); // Call the custom action listener
//            fireEditingStopped(); // Stop editing
//        });
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return new JButton(label); // Return a new JButton with the label
    }
}
