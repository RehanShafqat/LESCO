/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Structures;

/**
 *
 * @author rehan
 */

public class Billing {
    private String customerId;
    private String billingMonth;
    private int currentMeterReading;
    private int currentMeterReadingPeak;
    private String readingEntryDate;
    private double electricityCost;
    private double salesTax;
    private double fixedCharges;
    private double totalBillingAmount;
    private String dueDate;
    private String billStatus;
    private String billPaymentDate;

    public Billing(String customerId, String billingMonth, int currentMeterReading, int currentMeterReadingPeak, String readingEntryDate, double electricityCost, double salesTax, double fixedCharges, double totalBillingAmount, String dueDate, String billStatus, String billPaymentDate) {
        this.customerId = customerId;
        this.billingMonth = billingMonth;
        this.currentMeterReading = currentMeterReading;
        this.currentMeterReadingPeak = currentMeterReadingPeak;
        this.readingEntryDate = readingEntryDate;
        this.electricityCost = electricityCost;
        this.salesTax = salesTax;
        this.fixedCharges = fixedCharges;
        this.totalBillingAmount = totalBillingAmount;
        this.dueDate = dueDate;
        this.billStatus = billStatus;
        this.billPaymentDate = billPaymentDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBillingMonth() {
        return billingMonth;
    }

    public void setBillingMonth(String billingMonth) {
        this.billingMonth = billingMonth;
    }

    public int getCurrentMeterReading() {
        return currentMeterReading;
    }

    public void setCurrentMeterReading(int currentMeterReading) {
        this.currentMeterReading = currentMeterReading;
    }

    public int getCurrentMeterReadingPeak() {
        return currentMeterReadingPeak;
    }

    public void setCurrentMeterReadingPeak(int currentMeterReadingPeak) {
        this.currentMeterReadingPeak = currentMeterReadingPeak;
    }

    public String getReadingEntryDate() {
        return readingEntryDate;
    }

    public void setReadingEntryDate(String readingEntryDate) {
        this.readingEntryDate = readingEntryDate;
    }

    public double getElectricityCost() {
        return electricityCost;
    }

    public void setElectricityCost(double electricityCost) {
        this.electricityCost = electricityCost;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public double getFixedCharges() {
        return fixedCharges;
    }

    public void setFixedCharges(double fixedCharges) {
        this.fixedCharges = fixedCharges;
    }

    public double getTotalBillingAmount() {
        return totalBillingAmount;
    }

    public void setTotalBillingAmount(double totalBillingAmount) {
        this.totalBillingAmount = totalBillingAmount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public String getBillPaymentDate() {
        return billPaymentDate;
    }

    public void setBillPaymentDate(String billPaymentDate) {
        this.billPaymentDate = billPaymentDate;
    }
    
        public void print() {
        System.out.println("Billing Details:");
        System.out.println("Customer ID: " + customerId);
        System.out.println("Billing Month: " + billingMonth);
        System.out.println("Current Meter Reading: " + currentMeterReading);
        System.out.println("Current Meter Reading Peak: " + currentMeterReadingPeak);
        System.out.println("Reading Entry Date: " + readingEntryDate);
        System.out.println("Electricity Cost: " + electricityCost);
        System.out.println("Sales Tax: " + salesTax);
        System.out.println("Fixed Charges: " + fixedCharges);
        System.out.println("Total Billing Amount: " + totalBillingAmount);
        System.out.println("Due Date: " + dueDate);
        System.out.println("Bill Status: " + billStatus);
        System.out.println("Bill Payment Date: " + billPaymentDate);
    }

     public void printSummary() {
        System.out.println("Billing Summary:");
        System.out.println("Customer ID: " + customerId + ", Billing Month: " + billingMonth +
                           ", Total Amount: " + totalBillingAmount + ", Status: " + billStatus +
                           ", Due Date: " + dueDate + ", Payment Date: " + billPaymentDate);
    }
  
}
