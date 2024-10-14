/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.EmployeeManager;

/**
 *
 * @author rehan
 */
public class EmployeeController {
    
    
    EmployeeManager employeeManager = new EmployeeManager();
    
    public  boolean loginController(String userName,String Password){
        
        if (employeeManager.login(userName, Password)) {
             
         return true;
        
        }
        return false;
    
    }
}
