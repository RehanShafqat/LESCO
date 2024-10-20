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
    private ActionListener actionListener;

    public ButtonEditor(JCheckBox checkBox, ActionListener actionListener) {
        super(checkBox);
        this.actionListener = actionListener;
        button = new JButton();
        button.setOpaque(true);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value instanceof JButton) {
            JButton btn = (JButton) value;
            label = btn.getText();
            button.setText(label);
            button.setIcon(btn.getIcon());
        }

        if(btnListener != null) {
            button.removeActionListener(btnListener);
        }

        btnListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionListener.actionPerformed(new ActionEvent(table, ActionEvent.ACTION_PERFORMED, String.valueOf(row)));
                fireEditingStopped();
            }
        };
        button.addActionListener(btnListener);
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return new JButton(label);
    }
}
