package Controllers.Employee;

import Enums.CustomerType;
import Enums.MeterType;
import Models.CustomerManager;
import Structures.ButtonEditor;
import Structures.Customer;
import Views.Employee.ManageCustomersPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ManageCustomersController {
    ManageCustomersPanel manageCustomersPanel;
    EmployeeSessionController employeeSessionController;
    CustomerManager customerManager;

    public ManageCustomersController() {
        manageCustomersPanel = new ManageCustomersPanel();
        customerManager = new CustomerManager();
        manageCustomersPanel.setCustomers(customerManager.getAllCustomers());
        addButtonActionListeners();

        manageCustomersPanel.setAddButtonListener(e -> {
            manageCustomersPanel.showView("AddCustomer",null);
            manageCustomersPanel.addOkButtonListener(f -> {
                addCustomer();
            });
            manageCustomersPanel.addCancelButtonListener(g -> {
                manageCustomersPanel.showView("CustomerManagement",null);
            });

        });

        manageCustomersPanel.addOkButtonListener(e -> {
            addCustomer();
        });

        manageCustomersPanel.addCancelButtonListener(e -> {
            manageCustomersPanel.showView("CustomerManagement",null);
        });

        addSearchListener();
    }

    public ManageCustomersPanel getManageCustomersPanel(EmployeeSessionController employeeSessionController) {
        this.employeeSessionController = employeeSessionController;
        manageCustomersPanel.addBackButtonListener(e -> {
            addBackButtonListener();
        });
        return manageCustomersPanel;
    }

    public void addBackButtonListener() {
        employeeSessionController.ShowDashBoard();
    }

    public void addCustomer() {
        // Checking for all Fields
        String uniqueId = manageCustomersPanel.uniqueIdField.getText();
        String cnic = manageCustomersPanel.cnicField.getText();
        String name = manageCustomersPanel.nameField.getText();
        String address = manageCustomersPanel.addressField.getText();
        String phone = manageCustomersPanel.phoneField.getText();
        String connectionDate = manageCustomersPanel.connectionDateField.getText();
        String regUnits = manageCustomersPanel.regUnitsField.getText();
        String peakUnits = manageCustomersPanel.peakUnitsField.getText();
        CustomerType customerType = (CustomerType) manageCustomersPanel.customerTypeBox.getSelectedItem();
        MeterType meterType = (MeterType) manageCustomersPanel.meterTypeBox.getSelectedItem();
        boolean isValid = !uniqueId.isEmpty() &&
                !cnic.isEmpty() &&
                !name.isEmpty() &&
                !address.isEmpty() &&
                !phone.isEmpty() &&
                !connectionDate.isEmpty() &&
                !regUnits.isEmpty() &&
                !peakUnits.isEmpty() &&
                customerType != null &&
                meterType != null;

        if (!isValid) {
            manageCustomersPanel.showMessage("Please enter all credentials");
            return;
        } else if (cnic.length() != 13 || phone.length() != 11) {
            StringBuilder str = new StringBuilder();
            if (cnic.length() != 13) { str.append("CNIC must have 13 digits\n"); }
            if (phone.length() != 11) { str.append("Phone number must have 11 digits\n"); }
            manageCustomersPanel.showMessage(str.toString());
            return;
        }
        Customer newCustomer = new Customer(uniqueId, cnic, name, address, phone, customerType, meterType, connectionDate, Integer.parseInt(regUnits), Integer.parseInt(peakUnits));
        String response = customerManager.registerCustomer(newCustomer);
        manageCustomersPanel.showMessage(response);
        if (response.contains("successfully")) {
            manageCustomersPanel.setCustomers(customerManager.getAllCustomers());
            clearFields();
            manageCustomersPanel.showView("CustomerManagement",null);
        }
    }

    public void clearFields() {
        manageCustomersPanel.uniqueIdField.setText("");
        manageCustomersPanel.cnicField.setText("");
        manageCustomersPanel.nameField.setText("");
        manageCustomersPanel.addressField.setText("");
        manageCustomersPanel.phoneField.setText("");
        manageCustomersPanel.connectionDateField.setText("");
        manageCustomersPanel.regUnitsField.setText("");
        manageCustomersPanel.peakUnitsField.setText("");
        manageCustomersPanel.customerTypeBox.setSelectedItem(null);
        manageCustomersPanel.meterTypeBox.setSelectedItem(null);
        manageCustomersPanel.connectionDateField.setText("");
        manageCustomersPanel.regUnitsField.setText("");
        manageCustomersPanel.peakUnitsField.setText("");
    }


    private void addButtonActionListeners() {
        int colCount = manageCustomersPanel.customerTable.getColumnModel().getColumnCount();

        manageCustomersPanel.customerTable.getColumnModel()
                .getColumn(colCount - 2)
                .setCellEditor(new ButtonEditor(new JCheckBox(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JTable table = (JTable) e.getSource();
                int row = Integer.parseInt(e.getActionCommand());
                DefaultTableModel model = (DefaultTableModel) manageCustomersPanel.customerTable.getModel();
                SwingUtilities.invokeLater(()->{
                    String id = (String) table.getModel().getValueAt(row,0);
                    Customer customer = customerManager.getCustomerById(id);
                    manageCustomersPanel.showView("AddCustomer",customer);
                    manageCustomersPanel.addOkButtonListener(f -> {
                        updateCustomer();
                        model.setValueAt(customer.getUniqueId(),row,0);
                        model.setValueAt(customer.getCNIC(),row,1);
                        model.setValueAt(customer.getName(),row,2);
                        model.setValueAt(customer.getAddress(),row,3);
                        model.setValueAt(customer.getPhone(),row,4);
                        model.setValueAt(customer.getType(),row,5);
                        model.setValueAt(customer.getMeterType(),row,6);
                        model.setValueAt(customer.getConnectionDate(),row,7);
                        model.setValueAt(customer.getRegUnitsConsumed(),row,8);
                        model.setValueAt(customer.getPeakUnitsConsumed(),row,9);
                    });
                    manageCustomersPanel.addCancelButtonListener(g -> {
                        manageCustomersPanel.showView("CustomerManagement",null);

                    });
                });

            }
        }));

        manageCustomersPanel.customerTable.getColumnModel()
                .getColumn(colCount - 1)
                .setCellEditor(new ButtonEditor(new JCheckBox(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent act) {
                JTable table = (JTable) act.getSource();
                int row = Integer.parseInt(act.getActionCommand());
                DefaultTableModel model = (DefaultTableModel) manageCustomersPanel.customerTable.getModel();
                SwingUtilities.invokeLater(() -> {
                    String id = (String) table.getModel().getValueAt(row, 0);
                    int response = JOptionPane.showConfirmDialog(null,"Are you sure you want to Delete?");
                    if (response == JOptionPane.YES_OPTION) {
                    customerManager.deleteCustomer(id);
                    model.removeRow(row);
                    }
                });
            }
        }));
    }

    private void addSearchListener() {
        manageCustomersPanel.addSearchListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchCustomers();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchCustomers();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchCustomers();
            }
        });
    }

    private void searchCustomers() {
        String searchText = manageCustomersPanel.searchField.getText().toLowerCase();
        ArrayList<Customer> filteredCustomers = customerManager.getAllCustomers().stream()
                .filter(customer ->
                        customer.getName().toLowerCase().contains(searchText) ||
                                customer.getCNIC().toLowerCase().contains(searchText) ||
                                customer.getUniqueId().toLowerCase().contains(searchText) ||
                                customer.getPhone().toLowerCase().contains(searchText) ||
                                customer.getAddress().toLowerCase().contains(searchText)
                )
                .collect(Collectors.toCollection(ArrayList::new));

        manageCustomersPanel.setCustomers(filteredCustomers);
        addButtonActionListeners(); // Re-add button listeners to the filtered results
    }

    public void updateCustomer() {
        // Checking for all Fields
        String uniqueId = manageCustomersPanel.uniqueIdField.getText();
        String cnic = manageCustomersPanel.cnicField.getText();
        String name = manageCustomersPanel.nameField.getText();
        String address = manageCustomersPanel.addressField.getText();
        String phone = manageCustomersPanel.phoneField.getText();
        String connectionDate = manageCustomersPanel.connectionDateField.getText();
        String regUnits = manageCustomersPanel.regUnitsField.getText();
        String peakUnits = manageCustomersPanel.peakUnitsField.getText();
        CustomerType customerType = (CustomerType) manageCustomersPanel.customerTypeBox.getSelectedItem();
        MeterType meterType = (MeterType) manageCustomersPanel.meterTypeBox.getSelectedItem();
        boolean isValid = !uniqueId.isEmpty() &&
                !cnic.isEmpty() &&
                !name.isEmpty() &&
                !address.isEmpty() &&
                !phone.isEmpty() &&
                !connectionDate.isEmpty() &&
                !regUnits.isEmpty() &&
                !peakUnits.isEmpty() &&
                customerType != null &&
                meterType != null;

        if (!isValid) {
            manageCustomersPanel.showMessage("Please enter all credentials");
            return;
        } else if (cnic.length() != 13 || phone.length() != 11) {
            StringBuilder str = new StringBuilder();
            if (cnic.length() != 13) { str.append("CNIC must have 13 digits\n"); }
            if (phone.length() != 11) { str.append("Phone number must have 11 digits\n"); }
            manageCustomersPanel.showMessage(str.toString());
            return;
        }
        Customer customer = new Customer(uniqueId,cnic,name,address,phone,customerType,meterType,connectionDate,Integer.parseInt(regUnits),Integer.parseInt(peakUnits));
        String response = customerManager.updateCustomer(customer);
        JOptionPane.showMessageDialog(null,response);
        manageCustomersPanel.showView("CustomerManagement",null);
    }



}
