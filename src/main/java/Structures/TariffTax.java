/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Structures;

/**
 *
 * @author rehan
 */



import Enums.CustomerType;
import Enums.MeterType;

public class TariffTax {
    private CustomerType customerType;
    private MeterType meterType;
    private double regUnitPrice;
    private double peakUnitPrice;
    private double taxPercentage;
    private double fixedCharges;

    public TariffTax(CustomerType customerType, MeterType meterType, double regUnitPrice, double peakUnitPrice, double taxPercentage, double fixedCharges) {
        this.customerType = customerType;
        this.meterType = meterType;
        this.regUnitPrice = regUnitPrice;
        this.peakUnitPrice = peakUnitPrice;
        this.taxPercentage = taxPercentage;
        this.fixedCharges = fixedCharges;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public MeterType getMeterType() {
        return meterType;
    }

    public void setMeterType(MeterType meterType) {
        this.meterType = meterType;
    }

    public double getRegUnitPrice() {
        return regUnitPrice;
    }

    public void setRegUnitPrice(double regUnitPrice) {
        this.regUnitPrice = regUnitPrice;
    }

    public double getPeakUnitPrice() {
        return peakUnitPrice;
    }

    public void setPeakUnitPrice(double peakUnitPrice) {
        this.peakUnitPrice = peakUnitPrice;
    }

    public double getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public double getFixedCharges() {
        return fixedCharges;
    }

    public void setFixedCharges(double fixedCharges) {
        this.fixedCharges = fixedCharges;
    }
   
    
}

