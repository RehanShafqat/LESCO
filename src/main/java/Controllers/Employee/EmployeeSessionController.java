package Controllers.Employee;

import Controllers.FirstScreenController;
import Date.Date;
import Models.BillingManager;
import Models.CustomerManager;
import Models.EmployeeManager;
import Models.TariffTaxManager;
import Structures.TariffTax;
import Views.Employee.EmployeeSessionScreen;

public class EmployeeSessionController   {

    EmployeeSessionScreen employeeSessionScreen;
    EmployeeManager employeeManager;
    BillingManager billingManager;
    CustomerManager customerManager;



    ManageCustomersController manageCustomersController;
    ManageTariffController manageTariffController;
    TariffTaxManager tariffTaxManager;

    public String []  Views = {"ManageCustomersView", "CreateBillingRecordView" , "ManageTariffTaxView" , "ManageBillsView" ,"ListExpiringCnicView" , "RecordBillPaymentView" , "GenerateReportView" ,  "LogoutView"};

    public EmployeeSessionController(String userName) {
        employeeSessionScreen = new EmployeeSessionScreen(true, userName);
        employeeManager = new EmployeeManager();
        manageCustomersController = new ManageCustomersController();
        manageTariffController = new ManageTariffController();
        billingManager = new BillingManager();
        tariffTaxManager = new TariffTaxManager();
        customerManager = new CustomerManager();
        initActionListeners();
    }
    public void initActionListeners() {
        // ActionListeners
            employeeSessionScreen.addManageCustomersActionListener(e -> {
                employeeSessionScreen.createViews(manageCustomersController.getManageCustomersPanel(this),Views[0]);
                employeeSessionScreen.showView(Views[0]);

        });

        employeeSessionScreen.addBillingRecordActionListener(e -> {
            billingManager.loadData();
            generateBilling();
        });

        employeeSessionScreen.addManageTariffTaxActionListener(e -> {
            employeeSessionScreen.createViews(manageTariffController.getManageTariffPanel(this),Views[2]);
            employeeSessionScreen.showView(Views[2]);
        });

        employeeSessionScreen.addManageBillsActionListener(e -> {
            employeeSessionScreen.showView(Views[3]); // ManageBillsView
        });

        employeeSessionScreen.addListExpiringCnicActionListener(e -> {
            employeeSessionScreen.showView(Views[4]); // ListExpiringCnicView
        });

        employeeSessionScreen.addRecordBillPaymentActionListener(e -> {
            employeeSessionScreen.showView(Views[5]); // RecordBillPaymentView
        });

        employeeSessionScreen.addGenerateReportActionListener(e -> {
            generateReport();
        });

        employeeSessionScreen.addLogoutActionListener(e -> {
            int isLoggedOut = employeeSessionScreen.Logout();
            if (isLoggedOut==1)
                new FirstScreenController();
        });
    }
    public void ShowDashBoard() {
        billingManager.loadData();
        customerManager.loadData();
        employeeManager.loadData();
        employeeSessionScreen.showView("ButtonsView");
    }
    public void generateBilling() {

        String lastGenerationDate = billingManager.getLastDate();
        String todayDate = Date.todaysDate();

        if (lastGenerationDate != null) {
            String[] lastDateParts = lastGenerationDate.split("/");
            String[] currentDateParts = todayDate.split("/");
            if (currentDateParts[1].equals(lastDateParts[1]) && currentDateParts[2].equals(lastDateParts[2])) {
                employeeSessionScreen.showMessage("Bills for this month have already been created");
                return;
            }
        }
        if (customerManager.getAllCustomers().isEmpty()) {
            employeeSessionScreen.showMessage("No Customers found");
            return;
        }

        customerManager.getAllCustomers().forEach(customer -> {
            TariffTax tariffTax = tariffTaxManager.getTariffTaxDetails(customer.getType(), customer.getMeterType());
            billingManager.createBillForCustomer(customer, tariffTax);
        });

        billingManager.saveData();
        billingManager.loadData();
        employeeSessionScreen.showMessage("Bills Created Successfuly");
    }
    public void generateReport() {
        String response = billingManager.generateReport();
        employeeSessionScreen.showMessage(response);
        employeeManager.loadData();
        billingManager.loadData();

    }




}
