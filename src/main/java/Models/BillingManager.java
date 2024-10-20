package Models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import Enums.CustomerType;
import Enums.MeterType;
import Date.Date;
import Date.Util;
import Structures.TariffTax;
import java.util.Scanner;
import Structures.Billing;
import Structures.Customer;

public class BillingManager {

    private ArrayList<Billing> billings;
    private final String FILE = "data/BillingInfo.txt";

    public BillingManager() {
        billings = new ArrayList<>();
        loadData();
    }

    public final void loadData() {
        billings.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 12) {
                    Billing bill = new Billing(
                        values[0], values[1], Integer.parseInt(values[2]),
                        Integer.parseInt(values[3]), values[4], 
                        Double.parseDouble(values[5]), Double.parseDouble(values[6]),
                        Double.parseDouble(values[7]), Double.parseDouble(values[8]),
                        values[9], values[10], values[11].equals("-") ? "" : values[11]
                    );
                    billings.add(bill);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading Billing file: " + e.getMessage());
        }
    }

    public void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE))) {
            for (Billing bill : billings) {
                StringBuilder line = new StringBuilder()
                    .append(bill.getCustomerId()).append(",")
                    .append(bill.getBillingMonth()).append(",")
                    .append(bill.getCurrentMeterReading()).append(",")
                    .append(bill.getCurrentMeterReadingPeak()).append(",")
                    .append(bill.getReadingEntryDate()).append(",")
                    .append(bill.getElectricityCost()).append(",")
                    .append(bill.getSalesTax()).append(",")
                    .append(bill.getFixedCharges()).append(",")
                    .append(bill.getTotalBillingAmount()).append(",")
                    .append(bill.getDueDate()).append(",")
                    .append(bill.getBillStatus()).append(",")
                    .append(bill.getBillPaymentDate().isEmpty() ? "-" : bill.getBillPaymentDate());
                
                writer.write(line.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error while writing Billing file: " + e.getMessage());
        }
    }

    public String getLastDate() {
        if (billings.isEmpty()) return null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate maxDate = LocalDate.MIN;

        for (Billing bill : billings) {
            try {
                LocalDate date = LocalDate.parse(bill.getReadingEntryDate(), formatter);
                if (date.isAfter(maxDate)) {
                    maxDate = date;
                }
            } catch (Exception e) {
                System.err.println("Invalid date format encountered: " + bill.getReadingEntryDate());
            }
        }
        return maxDate.equals(LocalDate.MIN) ? null : maxDate.format(formatter);
    }

    public void createBillForCustomer(Customer customer, TariffTax tax) {
        String[] today = Date.todaysDate().split("/");

        String uniqueId = customer.getUniqueId();
        String billingMonth = today[1] + "/" + today[2];
        int currMeterReading = Util.getRandomNumber();
        int currMeterPeak = customer.getMeterType() == MeterType.THREE_PHASE ? Util.getRandomNumber() : 0;
        String readingEntryDate = Date.todaysDate();
        
        double electricityCost = currMeterReading * tax.getRegUnitPrice() +
            (customer.getMeterType() == MeterType.THREE_PHASE ? currMeterPeak * tax.getPeakUnitPrice() : 0);
        double salesTax = tax.getTaxPercentage();
        double fixedCharges = tax.getFixedCharges();
        double totalBillingAmount = electricityCost + (salesTax / 100) * electricityCost + fixedCharges;
        
        String dueDate = Date.dateWeekLater();
        String billStatus = "unpaid";
        String billPaymentDate = "";

        Billing newBill = new Billing(uniqueId, billingMonth, currMeterReading, currMeterPeak, 
            readingEntryDate, electricityCost, salesTax, fixedCharges, totalBillingAmount, 
            dueDate, billStatus, billPaymentDate);

        billings.add(newBill);
    }
public Billing viewCurrentBill(String customerId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate latestDate = LocalDate.MIN;
        Billing latestBill = null;

        for (Billing bill : billings) {
            if (bill.getCustomerId().equals(customerId)) {
                try {
                    LocalDate date = LocalDate.parse(bill.getReadingEntryDate(), formatter);
                    if (date.isAfter(latestDate)) {
                        latestDate = date;
                        latestBill = bill;
                    }
                } catch (Exception e) {
                    System.err.println("Invalid date format encountered: " + bill.getReadingEntryDate());
                }
            }
        }
        return latestBill;
    }
    public void viewBill(String customerId) {
        boolean billFound = false;

        for (Billing bill : billings) {
            if (bill.getCustomerId().equals(customerId)) {
                bill.printSummary();
                billFound = true;
            }
        }
        
        if (!billFound) {
            System.out.println("No bill found for this ID.");
        }
    }

    public void printAll() {
        if (billings.isEmpty()) {
            System.out.println("No Bills Found.");
        } else {
            for (Billing bill : billings) {
                bill.print();
            }
        }
    }
     public void recordPayment(Scanner scanner) {
        if (billings.isEmpty()) {
            System.out.println("No Bills Available for Payment.");
            return;
        }

        for (int i = 0; i < billings.size(); i++) {
            System.out.println((i + 1) + ". ");
            billings.get(i).printSummary();
        }

        System.out.println("Select Index to pay bill:");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index >= 0 && index < billings.size()) {
            Billing bill = billings.get(index);

            if (bill.getBillStatus().equals("paid")) {
                System.out.println("Bill Already Paid.");
            } else {
                bill.setBillStatus("paid");
                bill.setBillPaymentDate(Date.todaysDate());
                saveData();
                System.out.println("Bill Paid Successfully.");
            }
        } else {
            System.out.println("Invalid Index.");
        }
    }

   

    
     public String generateReport() {
        if (billings.isEmpty()) {
            System.out.println("No Bills Found.");
            return "No Bills found";
        }

        int paidCount = 0;
        int unpaidCount = 0;

        for (Billing bill : billings) {
            if (bill.getBillStatus().equals("paid")) {
                paidCount++;
            } else {
                unpaidCount++;
            }
        }
         System.out.println("Report Summary");
         System.out.println("Paid bills: " + paidCount);
         System.out.println("Unpaid bills: " + unpaidCount);

         return "Paid bills: " + paidCount+  "\n Unpaid Bills: " + unpaidCount;

    }

   
}
