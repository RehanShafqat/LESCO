/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Structures;

/**
 *
 * @author rehan
 */


public class NADRA {
    private String CNIC;
    private String startDate;
    private String endDate;

    public NADRA(String CNIC, String startDate, String endDate) {
        this.CNIC = CNIC;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public void reNew (String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
}

