package Views.Employee;

import Controllers.Employee.EmployeeSessionController;
import Structures.Colors;
import Structures.CustomFrame;
import Structures.ImagePath;
import Views.CustomButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class EmployeeSessionScreen extends CustomFrame {

    private String employeeUserName;
    private JPanel cardPanel;
    CustomButton createBillingRecord;
    CustomButton manageCustomers;
    CustomButton manageTariffTax;
    CustomButton manageBills;
    CustomButton  listExpiringCnic ;
    CustomButton recordBillPayment;
    CustomButton generateReport;
    CustomButton logout;
    public String []  Views = {"ManageCustomersView", "CreateBillingRecordView" , "ManageTariffTaxView" , "ManageBillsView" ,"ListExpiringCnicView" , "RecordBillPaymentView" , "GenerateReportView" ,  "LogoutView"};

    //Design Related Functions
    public EmployeeSessionScreen(boolean gradient, String userName) {
        super(gradient);



        setTitle("Employee Session");
        this.employeeUserName = userName;
        setLayout(new BorderLayout());
        add(createHeadingPanel(), BorderLayout.NORTH);
        //CardPanel
        cardPanel = new JPanel(new CardLayout());
        cardPanel.setOpaque(false);
        add(cardPanel, BorderLayout.CENTER);
        //ButtonPanel
        JPanel buttonPanel = createButtonPanel();
        cardPanel.add(buttonPanel, "ButtonsView");
        createViews();
        showView("ButtonsView");

    }
    private JPanel createHeadingPanel() {
        JPanel headingPanel = new JPanel(new BorderLayout());
        headingPanel.setOpaque(false);
        JPanel labelPanel = new JPanel();
        labelPanel.setOpaque(false);
        JLabel dashBoardLabel = new JLabel("Admin Dashboard");
        dashBoardLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        dashBoardLabel.setForeground(Color.WHITE);
        labelPanel.add(dashBoardLabel);
        headingPanel.add(labelPanel, BorderLayout.CENTER);
        headingPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        headingPanel.setBorder(new EmptyBorder(20,0,70,0));
        return headingPanel;
    }
    private JPanel createButtonPanel() {
        ImageIcon image = getResizedImage(new ImageIcon(ImagePath.path + "customer.png"), 30, 30);

        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setOpaque(false);
        wrapperPanel.setPreferredSize(new Dimension(1200, 500));
        wrapperPanel.setMaximumSize(new Dimension(1200, 500));
        wrapperPanel.setMinimumSize(new Dimension(1200, 500));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 10, 10, 10);

        manageCustomers = new CustomButton("Manage Customers", image);
        manageCustomers.setPreferredSize(new Dimension(250, 80));
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(manageCustomers, gbc);

         createBillingRecord = new CustomButton("Create Billing Record", getResizedImage(new ImageIcon(ImagePath.path + "billing.png"), 40, 40));
        createBillingRecord.setPreferredSize(new Dimension(250, 80));
        gbc.gridx = 1;
        buttonPanel.add(createBillingRecord, gbc);

         manageTariffTax = new CustomButton("Manage Tariff Tax", getResizedImage(new ImageIcon(ImagePath.path + "Tax.png"), 40, 40));
        manageTariffTax.setPreferredSize(new Dimension(250, 80));
        gbc.gridx = 2;
        buttonPanel.add(manageTariffTax, gbc);

         manageBills = new CustomButton("Manage Bills", getResizedImage(new ImageIcon(ImagePath.path + "billing.png"), 40, 40));
        manageBills.setPreferredSize(new Dimension(250, 80));
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(manageBills, gbc);

         listExpiringCnic = new CustomButton("List Expiring Cnic", getResizedImage(new ImageIcon(ImagePath.path + "cnic.png"), 40, 40));
        listExpiringCnic.setPreferredSize(new Dimension(250, 80));
        gbc.gridx = 1;
        buttonPanel.add(listExpiringCnic, gbc);

         recordBillPayment = new CustomButton("Record Bill Payment", getResizedImage(new ImageIcon(ImagePath.path + "billing.png"), 40, 40));
        recordBillPayment.setPreferredSize(new Dimension(250, 80));
        gbc.gridx = 2;
        buttonPanel.add(recordBillPayment, gbc);

         generateReport = new CustomButton("Generate Report", getResizedImage(new ImageIcon(ImagePath.path + "report.png"), 40, 40));
        generateReport.setPreferredSize(new Dimension(250, 80));
        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(generateReport, gbc);

         logout = new CustomButton("Logout", getResizedImage(new ImageIcon(ImagePath.path + "logout.png"), 25, 25));
        logout.setPreferredSize(new Dimension(250, 80));
        gbc.gridx = 1;
        buttonPanel.add(logout, gbc);

        wrapperPanel.add(buttonPanel);
        return wrapperPanel;
    }
    private void createViews() {
        String[] viewNames = {"Manage Customers", "Create Billing Record", "Manage Tariff Tax", "Manage Bills",
                "List Expiring Cnic", "Record Bill Payment", "Generate Report", "Logout"};
        Color[] colors = {Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA,
                Color.ORANGE, Color.PINK, Color.GRAY};

        for (int i = 0; i < Views.length; i++) {
            cardPanel.add(createViewPanel(viewNames[i], colors[i]), Views[i]);
        }
    }
    private JPanel createViewPanel(String title, Color color) {
        JPanel viewPanel = new JPanel();
        viewPanel.setBackground(color);
        viewPanel.add(new JLabel(title));
        return viewPanel;
    }
    public void showView(String viewName) {
        CardLayout layout = (CardLayout) cardPanel.getLayout();
        layout.show(cardPanel, viewName);
    }
    public ImageIcon getResizedImage(ImageIcon image,int width,int height) {
        Image img = image.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }



    //ActionListeners
    public void addBillingRecordActionListener(ActionListener e) {
        createBillingRecord.addActionListener(e);
    }

    public void addManageCustomersActionListener(ActionListener e) {
        manageCustomers.addActionListener(e);
    }

    public void addManageTariffTaxActionListener(ActionListener e) {
        manageTariffTax.addActionListener(e);
    }

    public void addManageBillsActionListener(ActionListener e) {
        manageBills.addActionListener(e);
    }

    public void addListExpiringCnicActionListener(ActionListener e) {
        listExpiringCnic.addActionListener(e);
    }

    public void addRecordBillPaymentActionListener(ActionListener e) {
        recordBillPayment.addActionListener(e);
    }

    public void addGenerateReportActionListener(ActionListener e) {
        generateReport.addActionListener(e);
    }

    public void addLogoutActionListener(ActionListener e) {
        logout.addActionListener(e);
    }






    public static void main(String[] args) {
        new EmployeeSessionScreen(true, "Rehan");
    }

}