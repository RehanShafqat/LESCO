package Views.Employee;

import Enums.CustomerType;
import Structures.ButtonRenderer;
import Structures.Colors;
import Structures.Customer;
import Structures.TariffTax;
import Views.CustomButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManageTariffPanel extends JPanel {

    //components+Data
    public ArrayList<TariffTax> taxes;
    public JTable tariffTable;
    public DefaultTableModel tModel;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    public JTextField searchField;
    private JPanel formPanel;

    //buttons
    private CustomButton backButton;
    private CustomButton okButton;
    private CustomButton cancelButton;

    //fields
    public JTextField meterType;
    public JTextField regUnitPrice;
    public JTextField peakhourUnitPrice;
    public JTextField fixedCharges;
    public JTextField perncetageTax;
    public JTextField customerType;


    //UI Functions
    public ManageTariffPanel() {
        setOpaque(false);
        setBackground(Colors.getBaseColor());
        setLayout(new BorderLayout());
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setOpaque(false);
        cardPanel.add(createTaxManagementView(), "TaxManagement");
        add(cardPanel, BorderLayout.CENTER);
        cardLayout.show(cardPanel, "TaxManagement");
        cardPanel.setOpaque(false);

    }
    private JPanel createTaxManagementView() {
        JPanel managementPanel = new JPanel();
        managementPanel.setOpaque(false);
        managementPanel.setLayout(new BoxLayout(managementPanel, BoxLayout.Y_AXIS));
        JPanel headingPanel = new JPanel();
        headingPanel.setOpaque(false);
        JLabel headingLabel = new JLabel("Tariff Tax Manager");
        headingLabel.setForeground(Color.WHITE);
        headingLabel.setFont(new Font("SansSerif", Font.BOLD, 23));
        headingPanel.add(headingLabel);

        JPanel searchPanel = new JPanel();
        searchPanel.setOpaque(false);
        searchField = new JTextField(20);
        searchField.setPreferredSize(new Dimension(200, 20));
        JLabel searchLabel = new JLabel("Search: ");
        searchLabel.setForeground(Color.WHITE);
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);



        managementPanel.add(headingPanel);
        managementPanel.add(searchPanel);

        MakeTable();
        backButton = new CustomButton("Back");
        JPanel buttons = new JPanel(new BorderLayout());
        buttons.add(backButton, BorderLayout.WEST);
        managementPanel.add(new JScrollPane(tariffTable));
        managementPanel.add(buttons);
        return managementPanel;
    }
    private JPanel TariffTaxForm(TariffTax tax) {
        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));
        wrapperPanel.setBackground(Colors.getBaseColor());
        JPanel headingPanel = new JPanel();
        headingPanel.setOpaque(false);
        JLabel headingLabel = new JLabel("Update TariffTax");
        headingLabel.setForeground(Color.WHITE);
        headingLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headingPanel.add(headingLabel);
        wrapperPanel.add(headingPanel);
        wrapperPanel.add(Box.createVerticalStrut(10));
        formPanel = new JPanel();
        formPanel.setBackground(Colors.getBaseColor());
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        perncetageTax = new JTextField();
        meterType = new JTextField();
        regUnitPrice = new JTextField();
        peakhourUnitPrice = new JTextField();
        fixedCharges = new JTextField();
        customerType = new JTextField();



        perncetageTax.setText(tax.getTaxPercentage()+"");
        meterType.setText(tax.getMeterType()+"");
        regUnitPrice.setText(tax.getRegUnitPrice()+"");
        peakhourUnitPrice.setText(tax.getPeakUnitPrice()+"");
        System.out.println(customerType.getText());
//        if (CustomerType.valueOf(customerType.getText()) == CustomerType.DOMESTIC){
//            peakhourUnitPrice.setEnabled(false);
//        }


        customerType.setText(tax.getCustomerType()+"");
        customerType.setEditable(false);
        fixedCharges.setText(tax.getFixedCharges()+"");
        meterType.setEditable(false);






        JPanel customerTypePanel = createFieldPanel("Customer Type: " , customerType);
        JPanel MeterTypePanel = createFieldPanel("Meter Type:", meterType);
        JPanel regUnitPanel = createFieldPanel("Reg.unit Price:", regUnitPrice);
        JPanel peakHourPanel= createFieldPanel("Peak.unit Price:", peakhourUnitPrice);
        JPanel percentageTaxPanel = createFieldPanel("%TAX:", perncetageTax);
        JPanel fixedChargesPanel = createFieldPanel("Fixed Charges: ",fixedCharges);

        formPanel.add(customerTypePanel);
        formPanel.add(MeterTypePanel);
        formPanel.add(regUnitPanel);
        formPanel.add(peakHourPanel);
        formPanel.add(percentageTaxPanel);
        formPanel.add(fixedChargesPanel);
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
        tModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row,int column){
                return column>=getColumnCount()-2 || column>=getColumnCount()-1 ;

            }
        };
        tModel.addColumn("Customer Type");
        tModel.addColumn("Meter Type");
        tModel.addColumn("Regular Units Price");
        tModel.addColumn("Peak Hour Units Price");
        tModel.addColumn("%Tax");
        tModel.addColumn("Fixed Charges");
        tModel.addColumn("Update");

        tariffTable = new JTable(tModel) ;
        //Designing Table
        tariffTable.setBackground(Colors.getBaseColor());
        tariffTable.setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(tariffTable);
        scrollPane.setBackground(Colors.getBaseColor());
        this.add(scrollPane);
    }
    public void populateTable() {
        tModel.setRowCount(0);
        int columnCount = tModel.getColumnCount();
        if (columnCount > 3) {
            tariffTable.getColumnModel().getColumn(columnCount - 1).setCellRenderer(new ButtonRenderer());
        } else {
            System.err.println("Table model does not have enough columns to set the button renderers.");
        }
        for (TariffTax tax : taxes) {
            JButton updateButton = new JButton("Update");
            tModel.addRow(new Object[]{
                    tax.getCustomerType(),
                    tax.getMeterType(),
                    tax.getRegUnitPrice(),
                    tax.getPeakUnitPrice(),
                    tax.getTaxPercentage(),
                    tax.getFixedCharges(),
                    updateButton,

            });

        }
        if (tariffTable.isEditing()) {
            System.out.println("EDITING");
        }
    }



    public void addSearchListener(DocumentListener listener) {
        searchField.getDocument().addDocumentListener(listener);
    }









    public void showView(String name, TariffTax tariffTax) {
        if (name.equals("updateTariff")) {
            if (formPanel!=null){
                cardPanel.remove(formPanel);
            }
            formPanel = TariffTaxForm(tariffTax);
            cardPanel.add(formPanel,"updateTariff");
        }
        cardLayout.show(cardPanel, name);
    }




    public void setTaxes(ArrayList<TariffTax> taxes) {
        this.taxes = taxes;
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
    public void showMessage(String Message) {
        JOptionPane.showMessageDialog(this,Message);
    }
    public void disablePeakHourUnits() {
        this.peakhourUnitPrice.setEnabled(false);
    }


}
