package Controllers.Employee;

import Models.EmployeeManager;
import Views.Employee.EmployeeSessionScreen;

public class EmployeeSessionController {

    EmployeeSessionScreen employeeSessionScreen;
    EmployeeManager employeeManager;

    public EmployeeSessionController(String userName) {
        employeeSessionScreen = new EmployeeSessionScreen(true, userName);
        employeeManager = new EmployeeManager();
        initActionListeners();
    }
    public void initActionListeners() {
        // ActionListeners
        employeeSessionScreen.addManageCustomersActionListener(e -> {
            employeeSessionScreen.showView(employeeSessionScreen.Views[0]); // ManageCustomersView
        });

        employeeSessionScreen.addBillingRecordActionListener(e -> {
            employeeSessionScreen.showView(employeeSessionScreen.Views[1]); // CreateBillingRecordView
        });

        employeeSessionScreen.addManageTariffTaxActionListener(e -> {
            employeeSessionScreen.showView(employeeSessionScreen.Views[2]); // ManageTariffTaxView
        });

        employeeSessionScreen.addManageBillsActionListener(e -> {
            employeeSessionScreen.showView(employeeSessionScreen.Views[3]); // ManageBillsView
        });

        employeeSessionScreen.addListExpiringCnicActionListener(e -> {
            employeeSessionScreen.showView(employeeSessionScreen.Views[4]); // ListExpiringCnicView
        });

        employeeSessionScreen.addRecordBillPaymentActionListener(e -> {
            employeeSessionScreen.showView(employeeSessionScreen.Views[5]); // RecordBillPaymentView
        });

        employeeSessionScreen.addGenerateReportActionListener(e -> {
            employeeSessionScreen.showView(employeeSessionScreen.Views[6]); // GenerateReportView
        });

        employeeSessionScreen.addLogoutActionListener(e -> {
            employeeSessionScreen.showView(employeeSessionScreen.Views[7]); // LogoutView
        });
    }
}
