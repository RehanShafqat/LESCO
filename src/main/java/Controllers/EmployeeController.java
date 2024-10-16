/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.EmployeeManager;
import Views.Employee.EmployeeLoginScreen;

/**
 *
 * @author rehan
 */
public class EmployeeController {
    
    
    EmployeeManager employeeManager;
    EmployeeLoginScreen employeeLoginScreen;

    public EmployeeController() {
        employeeManager=new EmployeeManager();
        employeeLoginScreen = new EmployeeLoginScreen();
        employeeLoginScreen.addLoginButtonListener(e->{
            login(employeeLoginScreen.getUserName(), employeeLoginScreen.getPassword());
        });
        employeeLoginScreen.addBackButtonListener(e->{
            employeeLoginScreen.ExitScreen();
            new FirstScreenController();
        });
    }
    public  boolean login(String userName,String Password){

        System.out.println("hello");
        if ((employeeLoginScreen.getUserName().isEmpty() || employeeLoginScreen.getPassword().isEmpty())) {
            employeeLoginScreen.SayForCredentials();
        }
        else{
            if (employeeManager.login(userName, Password)) {

                employeeLoginScreen.Confirmation();
                employeeLoginScreen.dispose();
                //to-do:
                //add the next Screen Controller
                return true;
            }
            else{
                employeeLoginScreen.WrongCredentials();
            }
        }
        return false;
    
    }


}
