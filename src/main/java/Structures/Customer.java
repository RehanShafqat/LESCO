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
public class Customer {
    private String uniqueId;
    private String CNIC;
    private String name;
    private String address;
    private String phone;
    private CustomerType type;
    private MeterType meterType;
    private String connectionDate;
    private int regUnitsConsumed;
    private int peakUnitsConsumed;

    public Customer(String uniqueId, String CNIC, String name, String address, String phone, CustomerType type, MeterType meterType, String connectionDate, int regUnitsConsumed, int peakUnitsConsumed) {
        this.uniqueId = uniqueId;
        this.CNIC = CNIC;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.type = type;
        this.meterType = meterType;
        this.connectionDate = connectionDate;
        this.regUnitsConsumed = regUnitsConsumed;
        this.peakUnitsConsumed = peakUnitsConsumed;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        if(uniqueId.length() == 4)
            this.uniqueId = uniqueId;
        else
            System.out.println("Invalid Unique Id");
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        if(CNIC.length() == 13)
            this.CNIC = CNIC;
        else
            System.out.println("Invalid CNIC");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public MeterType getMeterType() {
        return meterType;
    }

    public void setMeterType(MeterType meterType) {
        this.meterType = meterType;
    }

    public String getConnectionDate() {
        return connectionDate;
    }

    public void setConnectionDate(String connectionDate) {
        this.connectionDate = connectionDate;
    }

    public int getRegUnitsConsumed() {
        return regUnitsConsumed;
    }

    public void setRegUnitsConsumed(int regUnitsConsumed) {
        this.regUnitsConsumed = regUnitsConsumed;
    }

    public int getPeakUnitsConsumed() {
        return peakUnitsConsumed;
    }

    public void setPeakUnitsConsumed(int peakUnitsConsumed) {
        this.peakUnitsConsumed = peakUnitsConsumed;
    }
    
    public void printData() {
        System.out.println("Customer Details:");
        System.out.println("Unique ID: " + uniqueId);
        System.out.println("CNIC: " + CNIC);
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("Customer Type: " + (type == CustomerType.COMMERCIAL ? "Commercial" : "Domestic"));
        System.out.println("Meter Type: " + (meterType == MeterType.SINGLE_PHASE ? "Single Phase" : "Three Phase"));
        System.out.println("Connection Date: " + connectionDate);
        System.out.println("Regular Units Consumed: " + regUnitsConsumed);
        System.out.println("Peak Units Consumed: " + peakUnitsConsumed);
        System.out.println("------------------------");
    }
    
     public void printSummary() {
        System.out.printf("ID: %s | CNIC: %s | Name: %s | Type: %s | Meter: %s%n", 
                          uniqueId, CNIC, name, 
                          (type == CustomerType.COMMERCIAL ? "Commercial" : "Domestic"),
                          (meterType == MeterType.SINGLE_PHASE ? "Single Phase" : "Three Phase"));
    }

     public boolean authenticate(String uniqueId, String CNIC) {
         return this.uniqueId.equals(uniqueId) && this.CNIC.equals(CNIC);
     }
}
