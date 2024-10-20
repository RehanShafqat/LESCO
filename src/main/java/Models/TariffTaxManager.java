package Models;

import Enums.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import Structures.TariffTax;

public class TariffTaxManager {
    private ArrayList<TariffTax> taxes;
    private static final String FILE = "data/TariffTaxInfo.txt";

    public TariffTaxManager() {
        taxes = new ArrayList<>();
        loadData();
    }

    public final void loadData() {
        taxes.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line;
            for (int i = 0; (line = reader.readLine()) != null; i++) {
                String[] values = line.split(",");
                if (values.length == 5) {
                    CustomerType customerType = (i % 2 == 0) ? CustomerType.DOMESTIC : CustomerType.COMMERCIAL;
                    MeterType meterType = MeterType.valueOf(values[0]);
                    double regUnitPrice = Double.parseDouble(values[1]);
                    double peakUnitPrice = Double.parseDouble(values[2].isEmpty() ? "0" : values[2]);
                    double taxPercentage = Double.parseDouble(values[3]);
                    double fixedCharges = Double.parseDouble(values[4]);

                    TariffTax tax = new TariffTax(customerType, meterType, regUnitPrice, peakUnitPrice, taxPercentage, fixedCharges);
                    taxes.add(tax);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading Tax file: " + e.getMessage());
        }
    }

    public void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE))) {
            for (TariffTax tax : taxes) {
                String peakUnitPrice = tax.getMeterType() == MeterType.THREE_PHASE ? String.valueOf(tax.getPeakUnitPrice()) : "";
                String line = String.join(",",
                        tax.getMeterType().name(),
                        String.valueOf(tax.getRegUnitPrice()),
                        peakUnitPrice,
                        String.valueOf(tax.getTaxPercentage()),
                        String.valueOf(tax.getFixedCharges())
                );
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to Tax file: " + e.getMessage());
        }
    }

    
    
    
    public ArrayList<TariffTax> getTaxes() {
        return taxes;
    }

    public void setTaxes(ArrayList<TariffTax> taxes) {
        this.taxes = taxes;
    }

    public TariffTax getTariffTaxDetails(CustomerType customerType, MeterType meterType) {
        return taxes.stream()
                .filter(t -> t.getCustomerType() == customerType && t.getMeterType() == meterType)
                .findFirst()
                .orElse(null);
    }


    public String updateTariffTaxDetails(TariffTax updatedTax) {
        for (TariffTax tax : taxes) {
            if (tax.getCustomerType() == updatedTax.getCustomerType() && tax.getMeterType() == updatedTax.getMeterType()) {
                tax.setRegUnitPrice(updatedTax.getRegUnitPrice());
                tax.setPeakUnitPrice(updatedTax.getPeakUnitPrice());
                tax.setTaxPercentage(updatedTax.getTaxPercentage());
                tax.setFixedCharges(updatedTax.getFixedCharges());
                saveData();
                loadData();

                System.out.println("Tax updated successfully.");
                return "Tax upated Successfully";
            }
        }
        System.out.println("No matching tax found to update.");
        return "No matching tax found to update";
    }





    //Helper functions for ensuring right input
    
    private int getValidChoice(Scanner scanner, int min, int max) {
        int choice;
        do {
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Try again: ");
                scanner.next();
            }
            choice = scanner.nextInt();
        } while (choice < min || choice > max);
        return choice;
    }

    private double getValidDouble(Scanner scanner, String prompt) {
        double value;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextDouble()) {
                System.out.print("Invalid input. Try again: ");
                scanner.next();
            }
            value = scanner.nextDouble();
        } while (value < 0);
        return value;
    }

    private double getValidDouble(Scanner scanner, String prompt, double min, double max) {
        double value;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextDouble()) {
                System.out.print("Invalid input. Try again: ");
                scanner.next();
            }
            value = scanner.nextDouble();
        } while (value < min || value > max);
        return value;
    }
}
