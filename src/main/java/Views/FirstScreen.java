package Views;

import Controllers.FirstScreenController;
import Structures.CustomFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class FirstScreen extends CustomFrame {

    GridBagConstraints gbc = new GridBagConstraints();
    JButton Employeebutton;
    JButton Customerbutton;
    JButton ExitButton;
    public FirstScreen() {
        setTitle("LESCO Billing System");
        setBackground(Color.darkGray);
        setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel HeadingPanel = new JPanel();
        HeadingPanel.setLayout(new GridBagLayout());

        JLabel headingLabel = new JLabel("LESCO BILLING SYSTEM");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        HeadingPanel.add(headingLabel);



        addButtons();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(HeadingPanel, gbc);
    }


    private void addButtons() {

        JPanel OptionPanel = new JPanel();
        OptionPanel.setLayout(new GridBagLayout());

        GridBagConstraints optionGbc = new GridBagConstraints();
        optionGbc.gridx = 0;
        optionGbc.fill = GridBagConstraints.HORIZONTAL;

        // Employee Login
        Employeebutton = new JButton("Employee Login");
        Employeebutton.setFocusPainted(false);


        // Customer Login
         Customerbutton = new JButton("Customer Login");
        Customerbutton.setFocusPainted(false);

        // Exit
        ExitButton = new JButton("Exit");
        ExitButton.setFocusPainted(false);



        optionGbc.gridy = 0; // Row 0
        Employeebutton.setMargin(new Insets(20, 20, 20, 20));
        Employeebutton.setFont(new Font("Arial", Font.BOLD, 15));
        OptionPanel.add(Employeebutton, optionGbc);

        optionGbc.gridy = 1; // Row 1
        Customerbutton.setMargin(new Insets(20, 20, 20, 20));
        Customerbutton.setFont(new Font("Arial", Font.BOLD, 15));
        OptionPanel.add(Customerbutton, optionGbc);


        optionGbc.gridy = 2; // Row 2
        ExitButton.setMargin(new Insets(20, 20, 20, 20));
        ExitButton.setFont(new Font("Arial", Font.BOLD, 15));
        OptionPanel.add(ExitButton, optionGbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(30, 0, 0, 0);
        add(OptionPanel, gbc);


    }

    public void addCustomerButtonListener(ActionListener e) {
        Customerbutton.addActionListener(e);
    }
    public void addExitButtonListener(ActionListener e) {
        ExitButton.addActionListener(e);
    }
    public void addEmployeeButtonListener(ActionListener e){
        Employeebutton.addActionListener(e);
    }


}
