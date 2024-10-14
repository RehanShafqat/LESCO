/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author rehan
 */


import Date.Util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import Structures.Customer;
import Enums.*;
import java.util.Random;

public class CustomerManager {

    private ArrayList<Customer> customerData;
    
    private final String FILE = "data/CustomerInfo.txt" ;

    
    
    
    //Main functions
    public CustomerManager() {
        this.customerData = new ArrayList<>();
        loadData();
    }

    public CustomerManager(ArrayList<Customer> customerData) {
        this.customerData = customerData;
    }

    public ArrayList<Customer> getAllCustomers() {
        return this.customerData;
    }

    public void setCustomerData(ArrayList<Customer> customerData) {
        this.customerData = customerData;
    }

    public final void loadData() {
        this.customerData.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length != 10) {
                    continue;
                }
                Customer customer = new Customer(values[0], values[1], values[2], values[3],
                        values[4], CustomerType.valueOf(values[5]), MeterType.valueOf(values[6]),
                        values[7], Integer.parseInt(values[8]), Integer.parseInt(values[9]));
                customerData.add(customer);
            }
        } catch (IOException e) {
            System.out.println("Error while reading customer file: " + e.getMessage());
        }
    }

    public void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE))) {
            for (Customer customer : customerData) {
                writer.write(String.join(",", customer.getUniqueId(), customer.getCNIC(),
                        customer.getName(), customer.getAddress(), customer.getPhone(),
                        customer.getType().name(), customer.getMeterType().name(),
                        customer.getConnectionDate(), String.valueOf(customer.getRegUnitsConsumed()),
                        String.valueOf(customer.getPeakUnitsConsumed())));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error while writing customer data: " + e.getMessage());
        }
    }

    private boolean isUniqueIdExists(String uniqueId) {
        return customerData.stream().anyMatch(c -> c.getUniqueId().equals(uniqueId));
    }

    private int getCustomerCountByCNIC(String CNIC) {
        return (int) customerData.stream().filter(c -> c.getCNIC().equals(CNIC)).count();
    }

    public void printCustomerSummary() {
        customerData.forEach(Customer::printSummary);
    }

    public void registerCustomer(Scanner scanner) {
    Random random = new Random();
    String uniqueId = String.format("%04d", 1000 + random.nextInt(9000)); 
    while (isUniqueIdExists(uniqueId)) {
        uniqueId = String.format("%04d", 1000 + random.nextInt(9000)); 
    }
   
    System.out.println("Generated Unique ID: " + uniqueId);

    System.out.println("Enter Customer CNIC:");
    String CNIC = scanner.nextLine();

    while (CNIC.length() != 13 || !Util.numsOnly(CNIC)) {
        System.out.println("Error: CNIC must have 13 digits. Enter again: ");
        CNIC = scanner.nextLine();
    }

    NADRAManager nadraDb = new NADRAManager();
    while (!nadraDb.isCNICExists(CNIC)) {
        System.out.println("CNIC not found in NADRA Record. Try Again");
        CNIC = scanner.nextLine();
    }

    if (getCustomerCountByCNIC(CNIC) > 2) {
        System.out.println("Error: Only 3 meters are allowed per CNIC");
        return;
    }

    System.out.println("Enter Customer Name:");
    String name = scanner.nextLine();

    System.out.println("Enter  Address:");
    String address = scanner.nextLine();

    System.out.println("Enter Phone:");
    String phone = scanner.nextLine();
    while (phone.length() != 11) {
        System.out.println("Error: phone-no must have 11 digits. Enter again: ");
        phone = scanner.nextLine();
    }

    System.out.println("Enter Customer Type (1 for Commercial, 2 for Domestic):");
    int typeOption = Integer.parseInt(scanner.nextLine());
    CustomerType type = (typeOption == 1) ? CustomerType.COMMERCIAL : CustomerType.DOMESTIC;

    System.out.println("Enter Meter Type (1 for Single Phase, 2 for Three Phase):");
    int meterOption = Integer.parseInt(scanner.nextLine());
    MeterType meterType = (meterOption == 1) ? MeterType.SINGLE_PHASE : MeterType.THREE_PHASE;

    System.out.println("Enter Connection Date (DD/MM/YYYY):");
    String connectionDate = scanner.nextLine();

    int regUnitsConsumed = 0;
    int peakUnitsConsumed = 0;

    Customer newCustomer = new Customer(uniqueId, CNIC, name, address, phone,
            type, meterType, connectionDate, regUnitsConsumed,
            peakUnitsConsumed);

    customerData.add(newCustomer);

    saveData();
    loadData();

    System.out.println("Meter added successfully!");
}

    public void updateCustomer(Scanner scanner) {
    if (customerData.isEmpty()) {
        System.out.println("No Customer Data found");
        return;
    }

    this.printCustomerSummary();
    System.out.println("Enter Index of Customer to Update: ");
    String index = scanner.nextLine();
    while (Integer.parseInt(index) < 1 || Integer.parseInt(index) > customerData.size()
            || !Util.numsOnly(index)) {
        System.out.println("Error: Please enter valid index: ");
        index = scanner.nextLine();
    }

    System.out.println("Enter Customer Name:");
    String name = scanner.nextLine();

    System.out.println("Enter Address:");
    String address = scanner.nextLine();

    System.out.println("Enter Phone:");
    String phone = scanner.nextLine();
    while (phone.length() != 11) {
        System.out.println("Error: Phone number must have 11 digits. Enter again: ");
        phone = scanner.nextLine();
    }

    System.out.println("Enter Customer Type (1 for commercial, 2 for domestic):");
    int typeOption = Integer.parseInt(scanner.nextLine());
    CustomerType type = (typeOption == 1) ? CustomerType.COMMERCIAL : CustomerType.DOMESTIC;

    System.out.println("Enter Meter Type (1 for Single_Phase, 2 for Three_Phase):");
    int meterOption = Integer.parseInt(scanner.nextLine());
    MeterType meterType = (meterOption == 1) ? MeterType.SINGLE_PHASE : MeterType.THREE_PHASE;
    Customer existingCustomer = customerData.get(Integer.parseInt(index) - 1);
    Customer newCustomer = new Customer(existingCustomer.getUniqueId(),
            existingCustomer.getCNIC(), name, address, phone,
            type, meterType, existingCustomer.getConnectionDate(),
            existingCustomer.getRegUnitsConsumed(), existingCustomer.getPeakUnitsConsumed());
    
    customerData.set(Integer.parseInt(index) - 1, newCustomer);
    this.saveData();
    this.loadData();
    System.out.println("Customer Updated Successfully");
}


    public boolean login(String customerId, String CNIC) {
        return customerData.stream().anyMatch(c -> c.authenticate(customerId, CNIC));
    }
}
