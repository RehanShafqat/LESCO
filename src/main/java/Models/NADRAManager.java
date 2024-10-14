/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author rehan
 */

import Date.Date;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Structures.NADRA;

public class NADRAManager {

    private ArrayList<NADRA> NADRAData;
    private static final String FILE = "data/NADRADB.txt" ;

    public NADRAManager() {
        NADRAData = new ArrayList<>();
        loadData();
    }
     public final void loadData() {
        this.NADRAData.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line;
            for (line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] values = line.split(",");

                if (values.length == 3) {
                    String CNIC = values[0];
                    String startDate = values[1];
                    String endDate = values[2];
                    this.NADRAData.add(new NADRA(CNIC, startDate, endDate));
                }
            }
        } catch (IOException e) {
            System.out.println("Error Opening NadraDB file for Reading");
            System.out.println(e.getMessage());
        }
    }

    public void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE))) {
            for (NADRA entity : NADRAData) {
                String line = String.join(",", entity.getCNIC(), entity.getStartDate(), entity.getEndDate());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error Opening NadraDB for writing");
            System.out.println(e.getMessage());
        }
    }

    public boolean isCNICExists(String CNIC) {
        return NADRAData.stream().anyMatch(nadra -> nadra.getCNIC().equals(CNIC));
    }

    public void listExpiringCNICs() {
        long expiringCount = NADRAData.stream()
            .filter(entity -> Date.checkCNICExpiry(entity.getEndDate()) == 1)
            .peek(entity -> System.out.println("CNIC: " + entity.getCNIC() + " is about to expire"))
            .count();
        
        if (expiringCount == 0) {
            System.out.println("No CNIC is about to expire in the next 30 days");
        }
    }
    public NADRAManager(ArrayList<NADRA> NADRAData) {
        this.NADRAData = NADRAData;
    }

    public ArrayList<NADRA> getNADRAData() {
        return NADRAData;
    }

    public void setNADRAData(ArrayList<NADRA> NADRAData) {
        this.NADRAData = NADRAData;
    }

    public void displayRecords() {
        NADRAData.forEach(entity -> {
            System.out.println("CNIC: " + entity.getCNIC() + ", exp from " + entity.getStartDate() + " to " + entity.getEndDate());
        });
    }

    public String updateCNICExpiry(String CNIC) {
        String today = Date.todaysDate();
        String expiryDate = Date.dateYearLater();
        boolean isUpdated = NADRAData.stream()
            .filter(entity -> entity.getCNIC().equals(CNIC))
            .peek(entity -> entity.reNew(today, expiryDate))
            .findFirst()
            .isPresent();
        
        if (isUpdated) {

            System.out.println("Expiry Date extended for 1 year");
            saveData();
            return "Expiry Date extended for 1 year";
        } else {
            System.out.println("No CNIC Found");
            return "No CNIC Found";
        }
    }
}
