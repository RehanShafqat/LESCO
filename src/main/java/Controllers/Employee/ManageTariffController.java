package Controllers.Employee;

import Enums.CustomerType;
import Enums.MeterType;
import Models.TariffTaxManager;
import Structures.ButtonEditor;
import Structures.Customer;
import Structures.TariffTax;
import Views.Employee.ManageCustomersPanel;
import Views.Employee.ManageTariffPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ManageTariffController {

    ManageTariffPanel manageTariffPanel;
    EmployeeSessionController employeeSessionController;
    TariffTaxManager tariffTaxManager;

    public ManageTariffController() {
        manageTariffPanel = new ManageTariffPanel();
        tariffTaxManager = new TariffTaxManager();
        manageTariffPanel.setTaxes(tariffTaxManager.getTaxes());
        addButtonActionListeners();



//        manageTariffPanel.addCancelButtonListener(e -> {
//            manageTariffPanel.showView("TaxManagement",null);
//        });

        addSearchListener();
    }

    public ManageTariffPanel getManageTariffPanel(EmployeeSessionController employeeSessionController) {
        this.employeeSessionController = employeeSessionController;
        manageTariffPanel.addBackButtonListener(e -> {
            addBackButtonListener();
        });
        return manageTariffPanel;
    }
    public void addBackButtonListener() {
        employeeSessionController.ShowDashBoard();
    }



    private void addButtonActionListeners() {
        int colCount = manageTariffPanel.tariffTable.getColumnModel().getColumnCount();

       manageTariffPanel.tariffTable.getColumnModel()
                .getColumn(colCount - 1)
                .setCellEditor(new ButtonEditor(new JCheckBox(), new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JTable table = (JTable) e.getSource();
                        int row = Integer.parseInt(e.getActionCommand());
                        DefaultTableModel model = (DefaultTableModel) manageTariffPanel.tariffTable.getModel();
                        SwingUtilities.invokeLater(()->{


                            CustomerType customerType = (CustomerType) table.getModel().getValueAt(row,0);
                            MeterType meterType = (MeterType) table.getModel().getValueAt(row,1);
                            Double regUnitPrice = (Double) table.getModel().getValueAt(row,2);
                            Double peakHourPrice = (Double) table.getModel().getValueAt(row,3);
                            Double percentageTax = (Double) table.getModel().getValueAt(row,4);
                            Double fixedCharges = (Double) table.getModel().getValueAt(row,5);
                            TariffTax tax = new TariffTax(customerType,meterType,regUnitPrice,peakHourPrice,percentageTax,fixedCharges);

                            manageTariffPanel.showView("updateTariff",tax);
                            if (customerType == CustomerType.DOMESTIC  ){
                                manageTariffPanel.disablePeakHourUnits();
                            }
                            manageTariffPanel.addOkButtonListener(f -> {
                                TariffTax newTax = updateTariffTax(tax);
                                System.out.println("After Change\n"+newTax.toString());
                                model.setValueAt(newTax.getCustomerType(),row,0);
                                model.setValueAt(newTax.getMeterType(),row,1);
                                model.setValueAt(newTax.getRegUnitPrice(),row,2);
                                model.setValueAt(newTax.getPeakUnitPrice(),row,3);
                                model.setValueAt(newTax.getTaxPercentage(),row,4);
                                model.setValueAt(newTax.getTaxPercentage(),row,5);

                            });
                            manageTariffPanel.addCancelButtonListener(g -> {
                                manageTariffPanel.showView("TaxManagement",null);

                            });
                        });

                    }
                }));


    }

    private void addSearchListener() {
        manageTariffPanel.addSearchListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchTariffTax();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchTariffTax();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchTariffTax();
            }
        });
    }
    private void searchTariffTax() {
        String searchText = manageTariffPanel.searchField.getText().toLowerCase();
        ArrayList<TariffTax> filteredTaxes = tariffTaxManager.getTaxes().stream()
                .filter(customer ->
                        customer.getCustomerType().toString().toLowerCase().contains(searchText) ||
                                customer.getMeterType().toString().toLowerCase().contains(searchText)
                )
                .collect(Collectors.toCollection(ArrayList::new));
        manageTariffPanel.setTaxes(filteredTaxes);
        addButtonActionListeners();
    }

    public TariffTax updateTariffTax(TariffTax tax) {
        // Checking for all Fields
        System.out.println("Before Change\n"+tax.toString());
        String regUnits = manageTariffPanel.regUnitPrice.getText();
        String peakUnits = manageTariffPanel.peakhourUnitPrice.getText();
        String perncetageTax = manageTariffPanel.perncetageTax.getText();
        String fixedCharges = manageTariffPanel.fixedCharges.getText();
        MeterType meterType = MeterType.valueOf(manageTariffPanel.meterType.getText());
        CustomerType customerType = CustomerType.valueOf(manageTariffPanel.customerType.getText());
        boolean isValid =
                !regUnits.isEmpty() &&
                        !(customerType == CustomerType.COMMERCIAL && peakUnits.isEmpty()) &&
                        !perncetageTax.isEmpty() &&
                        !fixedCharges.isEmpty();
        if (!isValid) {
            manageTariffPanel.showMessage("Please enter all credentials");
            return null;
        }


        TariffTax Newtax = new TariffTax(customerType,meterType,Double.parseDouble(regUnits),Double.parseDouble(peakUnits),Double.parseDouble(perncetageTax),Double.parseDouble(fixedCharges));

        String response = tariffTaxManager.updateTariffTaxDetails(Newtax);
        JOptionPane.showMessageDialog(null,response);
        manageTariffPanel.showView("TaxManagement",null);
        return Newtax;
    }
}
