package Views.Customer;

import Structures.Billing;
import Structures.CustomFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomerSessionScreen extends CustomFrame {

    GridBagConstraints gbc = new GridBagConstraints();
    JButton viewBillButton;
    JButton updateCnicButton;
    JButton exitButton;

    public CustomerSessionScreen() {
        setTitle("Customer Session");
        setBackground(Color.darkGray);
        setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel HeadingPanel = new JPanel();
        HeadingPanel.setLayout(new GridBagLayout());

        JLabel headingLabel = new JLabel("Customer Session");
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


        viewBillButton = new JButton("View Current Bill");
        viewBillButton.setFocusPainted(false);

        updateCnicButton = new JButton("Update CNIC Expiry");
        updateCnicButton.setFocusPainted(false);


        exitButton = new JButton("Exit");
        exitButton.setFocusPainted(false);

        optionGbc.gridy = 0; // Row 0
        viewBillButton.setMargin(new Insets(20, 20, 20, 20));
        viewBillButton.setFont(new Font("Arial", Font.BOLD, 15));
        OptionPanel.add(viewBillButton, optionGbc);

        optionGbc.gridy = 1; // Row 1
        updateCnicButton.setMargin(new Insets(20, 20, 20, 20));
        updateCnicButton.setFont(new Font("Arial", Font.BOLD, 15));
        OptionPanel.add(updateCnicButton, optionGbc);

        optionGbc.gridy = 2; // Row 2
        exitButton.setMargin(new Insets(20, 20, 20, 20));
        exitButton.setFont(new Font("Arial", Font.BOLD, 15));
        OptionPanel.add(exitButton, optionGbc);

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
    public void addExitButtonListener(ActionListener e) {
        exitButton.addActionListener(e);
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
    public void viewBill(Billing bill) {
        StringBuilder billInfo = new StringBuilder();
        billInfo.append("Customer ID: ").append(bill.getCustomerId()).append("\n")
                .append("Billing Month: ").append(bill.getBillingMonth()).append("\n")
                .append("Total Amount: ").append(String.format("%.2f", bill.getTotalBillingAmount())).append("\n")
                .append("Status: ").append(bill.getBillStatus()).append("\n")
                .append("Due Date: ").append(bill.getDueDate()).append("\n")
                .append("Payment Date: ").append(bill.getBillPaymentDate() != null ? bill.getBillPaymentDate() : "Not Paid").append("\n");

        JTextArea textArea = new JTextArea(billInfo.toString());
        textArea.setFont(new Font("Arial", Font.PLAIN, 18)); // Set larger font size here
        textArea.setEditable(false); // Make sure text is not editable
        textArea.setLineWrap(true);  // Enable line wrapping
        textArea.setWrapStyleWord(true);  // Wrap lines at word boundaries

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300)); // Control dialog size

        JOptionPane.showMessageDialog(null, scrollPane, "Billing Information", JOptionPane.INFORMATION_MESSAGE);
    }







}

