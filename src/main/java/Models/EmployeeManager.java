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
import Structures.Employee;

public class EmployeeManager {

    private ArrayList<Employee> employeeData;
    private final String FILE = "data/EmployeesData.txt" ;

    public EmployeeManager() {
        employeeData = new ArrayList<>();
        loadData();
    }

    public ArrayList<Employee> getEmployeeData() {
        return employeeData;
    }

    public void setEmployeeData(ArrayList<Employee> employeeData) {
        this.employeeData = employeeData;
    }

    public final void loadData() {
        this.employeeData.clear();

        try (BufferedReader bufferReader = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = bufferReader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 2) {
                    String username = values[0];
                    String password = values[1];
                    employeeData.add(new Employee(username, password));
                }
            }
            bufferReader.close();
        } catch (IOException e) {
            System.out.println("Error opening File for Reading Employee Data");
            System.out.println(e.getMessage());
        }
    }

    public void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE))) {
            for (Employee e : employeeData) {
                try {
                    writer.write(e.getUsername() + "," + e.getPassword());
                    writer.newLine();
                } catch (IOException ew) {
                    System.out.println("Error Writing Employee Data");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error Opening File for Writing Employee Data");
            System.out.println(e.getMessage());
        }
    }

    public void changePassword(String username, String oldPass, String newPass) {
        boolean isUpdated = false;
        for (Employee e : employeeData) {
            if (e.getUsername().equals(username) && e.getPassword().equals(oldPass)) {
                e.setPassword(newPass);
                isUpdated = true;
                break;
            }
        }
        if (isUpdated) {
            saveData();
        }
    }

    public boolean login(String username, String password) {

        for (Employee employee : employeeData) {
            if (employee.getUsername().equals(username) && employee.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }
}
