/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;
/**
 *
 * @author rehan
 */
import java.util.Scanner;
import Date.Date;
import Structures.TariffTax;
import Structures.Customer;

public class LESCO_BILLING_SYSTEM {
    private final Scanner scanner;
    private final EmployeeManager employeeManager = new EmployeeManager();
    private final BillingManager billingManager = new BillingManager();
    private final NADRAManager nadraManager = new NADRAManager();
    private final CustomerManager customerManager = new CustomerManager();
    private final TariffTaxManager tariffTaxManager = new TariffTaxManager();

    public LESCO_BILLING_SYSTEM() {
        scanner = new Scanner(System.in);
    }
    private void displayBill() {
        System.out.println("Enter Customer ID to view bill: ");
        String customerId = scanner.nextLine();

        while (customerId.length() != 4) {
            System.out.println("Invalid ID. Try again: ");
            customerId = scanner.nextLine();
        }

        billingManager.viewBill(customerId);
    }
     private void generateMonthlyBilling() {
        String lastGenerationDate = billingManager.getLastDate();
        String todayDate = Date.todaysDate();

        if (lastGenerationDate != null) {
            String[] lastDateParts = lastGenerationDate.split("/");
            String[] currentDateParts = todayDate.split("/");
            if (currentDateParts[1].equals(lastDateParts[1]) && currentDateParts[2].equals(lastDateParts[2])) {
                System.out.println("Bills for this month have already been generated.");
                return;
            }
        }

        if (customerManager.getAllCustomers().isEmpty()) {
            System.out.println("No customers found.");
            return;
        }

        customerManager.getAllCustomers().forEach(customer -> {
            TariffTax tariffTax = tariffTaxManager.getTariffTaxDetails(customer.getType(), customer.getMeterType());
            billingManager.createBillForCustomer(customer, tariffTax);
        });

        billingManager.saveData();
        System.out.println("Monthly bills created successfully.");
    }
    
    public void displayMainMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Main Menu:\n1. Employee Login\n2. Customer Login\n3. Exit");
            switch (scanner.nextLine()) {
                case "1" -> handleEmployeeLogin();
                case "2" -> handleCustomerLogin();
                case "3" -> exit = true;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void handleEmployeeLogin() {
        System.out.println("Employee Login:\nUsername: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();

        if (employeeManager.login(username, password)) {
            employeeSession();
        } else {
            System.out.println("Login failed. Check your credentials.");
        }
    }

    private void employeeSession() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("""
                    Employee Menu:
                    1. Register New Meter
                    2. Modify Existing Meter
                    3. Create Billing Record
                    4. Update Tariff Information
                    5. Display Bill
                    6. Generate Report
                    7. List Expiring CNICs
                    8. Record Bill Payment
                    9. Logout""");
            switch (scanner.nextLine()) {
                case "1" -> customerManager.registerCustomer(scanner);
                case "2" -> customerManager.updateCustomer(scanner);
                case "3" -> generateMonthlyBilling();
                case "4" -> tariffTaxManager.updateTariffTaxDetails(scanner);
                case "5" -> displayBill();
                case "6" -> billingManager.generateReport();
                case "7" -> nadraManager.listExpiringCNICs();
                case "8" -> billingManager.recordPayment(scanner);
                case "9" -> loggedIn = false;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
    private void customerSession(String customerId, String cnic) {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("""
                    Customer Menu:
                    1. View Current Bill
                    2. Update CNIC Expiry Date
                    3. Logout""");
            switch (scanner.nextLine()) {
                case "1" -> billingManager.viewCurrentBill(customerId);
                case "2" -> nadraManager.updateCNICExpiry(cnic);
                case "3" -> loggedIn = false;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
    
    
    private void handleCustomerLogin() {
        System.out.println("Customer Login:\nCustomer ID: ");
        String customerId = scanner.nextLine();
        System.out.println("CNIC: ");
        String cnic = scanner.nextLine();

        if (customerManager.login(customerId, cnic)) {
            customerSession(customerId, cnic);
        } else {
            System.out.println("Invalid ID or CNIC. Try again.");
        }
    }
   

   
}

