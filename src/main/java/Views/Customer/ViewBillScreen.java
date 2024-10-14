package Views.Customer;

import Structures.Billing;
import Structures.CustomFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewBillScreen extends CustomFrame {

    GridBagConstraints gbc = new GridBagConstraints();
    Billing bill ;
    JButton backButton = new JButton("Back");
    public ViewBillScreen() {
        setVisible(false);
    }

    public void initScreen(Billing bill) {
        getContentPane().removeAll();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        this.bill = bill;
        setTitle("Billing Information");
        setSize(500, 500);
        setLayout(new GridBagLayout());

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel headingLabel = new JLabel("Bill Details");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(headingLabel, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        DisplayData();
    }

    void DisplayData() {
        JLabel customerIdLabel = new JLabel("Customer ID:");
        customerIdLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(customerIdLabel, gbc);

        JLabel customerIdValue = new JLabel(String.valueOf(bill.getCustomerId()));
        customerIdValue.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        add(customerIdValue, gbc);

        JLabel billingMonthLabel = new JLabel("Billing Month:");
        billingMonthLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(billingMonthLabel, gbc);

        JLabel billingMonthValue = new JLabel(bill.getBillingMonth());
        billingMonthValue.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        add(billingMonthValue, gbc);

        JLabel totalAmountLabel = new JLabel("Total Amount:");
        totalAmountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(totalAmountLabel, gbc);

        JLabel totalAmountValue = new JLabel(String.format("%.2f", bill.getTotalBillingAmount()));
        totalAmountValue.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        add(totalAmountValue, gbc);

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(statusLabel, gbc);

        JLabel statusValue = new JLabel(bill.getBillStatus());
        statusValue.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        add(statusValue, gbc);

        JLabel dueDateLabel = new JLabel("Due Date:");
        dueDateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(dueDateLabel, gbc);

        JLabel dueDateValue = new JLabel(bill.getDueDate());
        dueDateValue.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        add(dueDateValue, gbc);

        JLabel paymentDateLabel = new JLabel("Payment Date:");
        paymentDateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(paymentDateLabel, gbc);

        JLabel paymentDateValue = new JLabel(bill.getBillPaymentDate() != null ? bill.getBillPaymentDate() : "Not Paid");
        paymentDateValue.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        add(paymentDateValue, gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        backButton.setFont(new Font("Arial", Font.BOLD, 15));
        add(backButton, gbc);

    }

    public void addActionListenerForBack(ActionListener e) {
        backButton.addActionListener(e);
    }
    public void Exit() {
        this.dispose();
    }
}
