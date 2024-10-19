package Controllers.Employee;

import Models.EmployeeManager;
import Views.Employee.EmployeeSessionScreen;
import Views.Employee.ManageCustomersPanel;

public class EmployeeSessionController   {

    EmployeeSessionScreen employeeSessionScreen;
    EmployeeManager employeeManager;
    //Panels
    ManageCustomersController manageCustomersController;


    public String []  Views = {"ManageCustomersView", "CreateBillingRecordView" , "ManageTariffTaxView" , "ManageBillsView" ,"ListExpiringCnicView" , "RecordBillPaymentView" , "GenerateReportView" ,  "LogoutView"};

    public EmployeeSessionController(String userName) {
        employeeSessionScreen = new EmployeeSessionScreen(true, userName);
        employeeManager = new EmployeeManager();
        manageCustomersController = new ManageCustomersController();
        initActionListeners();
    }
    public void initActionListeners() {
        // ActionListeners
            employeeSessionScreen.addManageCustomersActionListener(e -> {
                employeeSessionScreen.createViews(manageCustomersController.getManageCustomersPanel(this),Views[0]);
                employeeSessionScreen.showView(Views[0]); // ManageCustomersView

        });

        employeeSessionScreen.addBillingRecordActionListener(e -> {
            employeeSessionScreen.showView(Views[1]); // CreateBillingRecordView
        });

        employeeSessionScreen.addManageTariffTaxActionListener(e -> {
            employeeSessionScreen.showView(Views[2]); // ManageTariffTaxView
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
            employeeSessionScreen.showView(Views[6]); // GenerateReportView
        });

        employeeSessionScreen.addLogoutActionListener(e -> {
            employeeSessionScreen.showView(Views[7]); // LogoutView
        });




    }

//    public void createViews() {
//        employeeSessionScreen.createViews(manageCustomersController.getManageCustomersPanel(),Views[0]);
//    }


    public void ShowDashBoard() {
        employeeSessionScreen.showView("ButtonsView");
    }





}
