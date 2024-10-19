package Views.Employee;

import Enums.CustomerType;
import Enums.MeterType;
import Structures.ButtonEditor;
import Structures.ButtonRenderer;
import Structures.Colors;
import Structures.Customer;
import Views.CustomButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ManageCustomersPanel extends JPanel {
    //components+Data
    public ArrayList<Customer> customers;
    public JTable customerTable;
    public DefaultTableModel tModel;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    public JTextField searchField;
    private JPanel formPanel;

    //buttons
    private CustomButton backButton;
    private CustomButton addButton;
    private CustomButton okButton;
    private CustomButton cancelButton;

    //fields
    public JTextField uniqueIdField;
    public JTextField cnicField;
    public JTextField nameField;
    public JTextField addressField;
    public JTextField phoneField;
    public JTextField connectionDateField;
    public JTextField regUnitsField;
    public JTextField peakUnitsField;
    public JComboBox<CustomerType> customerTypeBox;
    public JComboBox<MeterType>    meterTypeBox;

    //UI Functions
    public ManageCustomersPanel() {
        setOpaque(false);
        setBackground(Colors.getBaseColor());
        setLayout(new BorderLayout());
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setOpaque(false);
        cardPanel.add(createCustomerManagementView(), "CustomerManagement");
        cardPanel.add(CustomerForm(null), "AddCustomer");
        add(cardPanel, BorderLayout.CENTER);
        cardLayout.show(cardPanel, "CustomerManagement");


    }
    private JPanel createCustomerManagementView() {
        JPanel managementPanel = new JPanel();
        managementPanel.setOpaque(false);
        managementPanel.setLayout(new BoxLayout(managementPanel, BoxLayout.Y_AXIS));
        JPanel headingPanel = new JPanel();
        headingPanel.setOpaque(false);
        JLabel headingLabel = new JLabel("Customer Manager");
        headingLabel.setForeground(Color.WHITE);
        headingLabel.setFont(new Font("SansSerif", Font.BOLD, 23));
        headingPanel.add(headingLabel);

        JPanel searchPanel = new JPanel();
        searchPanel.setOpaque(false);
        searchField = new JTextField(20);
        searchField.setPreferredSize(new Dimension(200, 30));
        JLabel searchLabel = new JLabel("Search: ");
        searchLabel.setForeground(Color.WHITE);
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);



        managementPanel.add(headingPanel);
        managementPanel.add(searchPanel);

        MakeTable();
        backButton = new CustomButton("Back");
        addButton = new CustomButton("Add Customer");
        JPanel buttons = new JPanel(new BorderLayout());
        buttons.add(backButton, BorderLayout.WEST);
        buttons.add(addButton, BorderLayout.EAST);
        managementPanel.add(new JScrollPane(customerTable));
        managementPanel.add(buttons);
        return managementPanel;
    }
    private JPanel CustomerForm(Customer customer) {
        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));
        wrapperPanel.setBackground(Colors.getBaseColor());
        JPanel headingPanel = new JPanel();
        headingPanel.setOpaque(false);
        JLabel headingLabel = new JLabel("Add Customer");
        headingLabel.setForeground(Color.WHITE);
        headingLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headingPanel.add(headingLabel);
        wrapperPanel.add(headingPanel);
        wrapperPanel.add(Box.createVerticalStrut(10));
        formPanel = new JPanel();
        formPanel.setBackground(Colors.getBaseColor());
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
         uniqueIdField = new JTextField();
         cnicField = new JTextField();
         nameField = new JTextField();
         addressField = new JTextField();
         phoneField = new JTextField();
         connectionDateField = new JTextField();
         regUnitsField = new JTextField();
         peakUnitsField = new JTextField();
         customerTypeBox = new JComboBox<>(CustomerType.values());
         meterTypeBox = new JComboBox<>(MeterType.values());
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
         String date = simpleDateFormat.format(new Date());
         connectionDateField.setText(date);
         regUnitsField.setText("0");
        peakUnitsField.setText("0");
        if(customer != null) {
            uniqueIdField.setText(customer.getUniqueId());
            uniqueIdField.setEnabled(false);
            cnicField.setText(customer.getCNIC());
            nameField.setText(customer.getName());
            addressField.setText(customer.getAddress());
            phoneField.setText(customer.getPhone());
            connectionDateField.setText(customer.getConnectionDate());
            regUnitsField.setText(String.valueOf(customer.getRegUnitsConsumed()));
            peakUnitsField.setText(String.valueOf(customer.getPeakUnitsConsumed()));
            customerTypeBox.setSelectedItem(customer.getType());
            meterTypeBox.setSelectedItem(customer.getMeterType());
            regUnitsField.setText(customer.getRegUnitsConsumed()+"");
            peakUnitsField.setText(customer.getPeakUnitsConsumed()+"");
            headingLabel.setText("Update Customer");
            cnicField.setEnabled(false);
        }

        JPanel uniqueIdPanel = createFieldPanel("Unique ID:", uniqueIdField);
        JPanel cnicPanel = createFieldPanel("CNIC:", cnicField);
        JPanel namePanel = createFieldPanel("Name:", nameField);
        JPanel addressPanel = createFieldPanel("Address:", addressField);
        JPanel phonePanel = createFieldPanel("Phone:", phoneField);
        JPanel customerTypePanel = createFieldPanel("Customer Type:", customerTypeBox);
        JPanel meterTypePanel = createFieldPanel("Meter Type:", meterTypeBox);
        JPanel connectionDatePanel = createFieldPanel("Connection Date:", connectionDateField);
        JPanel regUnitsPanel = createFieldPanel("Regular Units Consumed:", regUnitsField);
        JPanel peakUnitsPanel = createFieldPanel("Peak Units Consumed:", peakUnitsField);
        formPanel.add(uniqueIdPanel);
        formPanel.add(cnicPanel);
        formPanel.add(namePanel);
        formPanel.add(addressPanel);
        formPanel.add(phonePanel);
        formPanel.add(customerTypePanel);
        formPanel.add(meterTypePanel);
        formPanel.add(connectionDatePanel);
        formPanel.add(regUnitsPanel);
        formPanel.add(peakUnitsPanel);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        okButton = new CustomButton("OK");
        cancelButton = new CustomButton("Cancel");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        wrapperPanel.add(formPanel);
        wrapperPanel.add(buttonPanel);

        return wrapperPanel;
    }
    private JPanel createFieldPanel(String labelText, Component field) {
        Dimension fieldSize = new Dimension(200, 10);
        field.setPreferredSize(fieldSize);
        field.setMaximumSize(fieldSize);
        JPanel fieldPanel = new JPanel(new BorderLayout());
        fieldPanel.setBorder(new EmptyBorder(5,0,5,0));
        fieldPanel.setBackground(Colors.getBaseColor());
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(150, 30));
        fieldPanel.add(label, BorderLayout.WEST);
        fieldPanel.add(field, BorderLayout.CENTER);
        fieldPanel.setPreferredSize(new Dimension(850, 200));
        fieldPanel.setMaximumSize(new Dimension(850, 200));
        fieldPanel.setOpaque(false);
        return fieldPanel;
    }
    public void MakeTable() {
        tModel = new DefaultTableModel();
        tModel.addColumn("Unique ID");
        tModel.addColumn("CNIC");
        tModel.addColumn("Name");
        tModel.addColumn("Address");
        tModel.addColumn("Phone");
        tModel.addColumn("Customer Type");
        tModel.addColumn("Meter Type");
        tModel.addColumn("Connection Date");
        tModel.addColumn("Reg Units");
        tModel.addColumn("Peak Units");
        tModel.addColumn("Update");
        tModel.addColumn("Delete");
        customerTable = new JTable(tModel);
        JScrollPane scrollPane = new JScrollPane(customerTable);
        this.add(scrollPane);
    }
    public void populateTable() {
        tModel.setRowCount(0); // Clear the table
        int columnCount = tModel.getColumnCount();

        if (columnCount > 3) {
            customerTable.getColumnModel().getColumn(columnCount - 2).setCellRenderer(new ButtonRenderer());
            customerTable.getColumnModel().getColumn(columnCount - 1).setCellRenderer(new ButtonRenderer());
        } else {
            System.err.println("Table model does not have enough columns to set the button renderers.");
        }

        // Populate table rows
        for (Customer customer : customers) {
            JButton updateButton = new JButton("Update");
            JButton deleteButton = new JButton("Delete");

            tModel.addRow(new Object[]{
                    customer.getUniqueId(),
                    customer.getCNIC(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getPhone(),
                    customer.getType(),
                    customer.getMeterType(),
                    customer.getConnectionDate(),
                    customer.getRegUnitsConsumed(),
                    customer.getPeakUnitsConsumed(),
                    updateButton,  // New button instance
                    deleteButton   // New button instance
            });
        }
    }




    public void addSearchListener(DocumentListener listener) {
        searchField.getDocument().addDocumentListener(listener);
    }









    public void showView(String name, Customer customer) {
        if (name.equals("AddCustomer")) {
            if (formPanel!=null){
                cardPanel.remove(formPanel);
            }
            formPanel = CustomerForm(customer);
            cardPanel.add(formPanel,"AddCustomer");
        }
        cardLayout.show(cardPanel, name);
    }




    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
        populateTable();


    }

    //Form Functions
    public void addOkButtonListener(ActionListener e) {
        okButton.addActionListener(e);

    }
    public void addCancelButtonListener(ActionListener e) {
        cancelButton.addActionListener(e);
    }
    public void addBackButtonListener(ActionListener e) {
        backButton.addActionListener(e);
    }
    public void setAddButtonListener(ActionListener e) {

        addButton.addActionListener(e);

    }


    public void showMessage(String Message) {
        JOptionPane.showMessageDialog(this,Message);
    }


}



























