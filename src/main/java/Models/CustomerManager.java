/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author rehan
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Structures.Customer;
import Enums.*;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public String registerCustomer(Customer customer) {
        NADRAManager nadraDb = new NADRAManager();
        if (!nadraDb.isCNICExists(customer.getCNIC())) {
            return "CNIC not found in NADRA DB";
        }
        if (getCustomerCountByCNIC(customer.getCNIC()) > 2) {
            System.out.println("Error: Only 3 meters are allowed per CNIC");
            return "Error: Only 3 meters are allowed per CNIC";
        }
        customerData.add(customer);
        saveData();
        loadData();
        System.out.println("Meter added successfully!");
        return "Meter added successfully!";
    }

    public String updateCustomer(Customer updatedCustomer) {
        if (updatedCustomer != null) {
            Customer existingCustomer = getCustomerById(updatedCustomer.getUniqueId());
            if (existingCustomer != null) {
                existingCustomer.setUniqueId(updatedCustomer.getUniqueId());
                existingCustomer.setName(updatedCustomer.getName());
                existingCustomer.setAddress(updatedCustomer.getAddress());
                existingCustomer.setPhone(updatedCustomer.getPhone());
                existingCustomer.setType(updatedCustomer.getType());
                existingCustomer.setMeterType(updatedCustomer.getMeterType());
                existingCustomer.setConnectionDate(updatedCustomer.getConnectionDate());
                existingCustomer.setRegUnitsConsumed(updatedCustomer.getRegUnitsConsumed());
                existingCustomer.setPeakUnitsConsumed(updatedCustomer.getPeakUnitsConsumed());
                existingCustomer.setConnectionDate(updatedCustomer.getConnectionDate());
                // Save the updated customer data
                this.saveData();
                this.loadData();

                System.out.println("Customer Updated Successfully");
                return "Customer Updated Successfully";
            } else {
                System.out.println("Customer not found");
                return "Customer Not Found";
            }
        } else {
            System.out.println("Provided customer is null");
            return "Customer not found";
        }
    }


    public ArrayList<Customer> getAllCustomer(){
        return customerData;
    }

    public boolean login(String customerId, String CNIC) {
        System.out.println("ID: " + customerId + "   CNIC" + CNIC);
        return customerData.stream().anyMatch(c -> c.authenticate(customerId, CNIC));
    }
    public void deleteCustomer(String customerId) {

        customerData =  customerData.stream().filter(e->!e.getUniqueId().equals(customerId))
                .collect(Collectors.toCollection(ArrayList::new));
        saveData();
        loadData();
    }
    public Customer getCustomerById(String customerId) {
        return customerData
                .stream()
                .filter(e->e.getUniqueId()
                        .equals(customerId))
                .findFirst().orElse(null);
    }
}