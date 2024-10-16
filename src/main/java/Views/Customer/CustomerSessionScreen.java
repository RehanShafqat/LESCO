package Views.Customer;

import Structures.Billing;
import Structures.CustomFrame;
import Views.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomerSessionScreen extends CustomFrame {

    GridBagConstraints gbc = new GridBagConstraints();
    CustomButton viewBillButton;
    CustomButton updateCnicButton;
    CustomButton logoutButton;

    public CustomerSessionScreen() {

        super(true);
        setTitle("Customer Session");
        setBackground(Color.darkGray);
        setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel HeadingPanel = new JPanel();
        HeadingPanel.setLayout(new GridBagLayout());
        HeadingPanel.setOpaque(false);

        JLabel headingLabel = new JLabel("Customer Session");
        headingLabel.setForeground(Color.WHITE);
        headingLabel.setOpaque(false);
        headingLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        HeadingPanel.add(headingLabel);

        addButtons();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(HeadingPanel, gbc);
    }

    private void addButtons() {

        JPanel OptionPanel = new JPanel();
        OptionPanel.setOpaque(false);
        OptionPanel.setLayout(new GridBagLayout());

        GridBagConstraints optionGbc = new GridBagConstraints();
        optionGbc.gridx = 0;
        optionGbc.fill = GridBagConstraints.HORIZONTAL;


        viewBillButton = new CustomButton("View Current Bill");
        viewBillButton.setFocusPainted(false);

        updateCnicButton = new CustomButton("Update CNIC Expiry");
        updateCnicButton.setFocusPainted(false);


        logoutButton = new CustomButton("Logout");
        logoutButton.setFocusPainted(false);

        optionGbc.gridy = 0; // Row 0
        viewBillButton.setMargin(new Insets(20, 20, 20, 20));
        viewBillButton.setFont(new Font("Arial", Font.BOLD, 15));
        OptionPanel.add(viewBillButton, optionGbc);

        optionGbc.gridy = 1; // Row 1
        updateCnicButton.setMargin(new Insets(20, 20, 20, 20));
        updateCnicButton.setFont(new Font("Arial", Font.BOLD, 15));
        OptionPanel.add(updateCnicButton, optionGbc);

        optionGbc.gridy = 2; // Row 2
        logoutButton.setMargin(new Insets(20, 20, 20, 20));
        logoutButton.setFont(new Font("Arial", Font.BOLD, 15));
        OptionPanel.add(logoutButton, optionGbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(30, 0, 0, 0);
        add(OptionPanel, gbc);
    }
    public void addViewBillButtonListener(ActionListener e) {
        viewBillButton.addActionListener(e);
    }
    public void addUpdateCnicButtonListener(ActionListener e) {
        updateCnicButton.addActionListener(e);
    }
    public void addlogoutButtonListener(ActionListener e) {
        logoutButton.addActionListener(e);
    }

    public void BillNotFount() {
        JOptionPane.showMessageDialog(this,"Bill Not Found");
    }
    public void ExitScreen() {
        this.dispose();
    }
    public void displayMessageForExpiryUpdation(String message){
        JOptionPane.showMessageDialog(this,message);
    }






}


