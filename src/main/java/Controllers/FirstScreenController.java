package Controllers;

import Views.CustomerLoginScreen;
import Views.FirstScreen;

public class FirstScreenController {

    FirstScreen MainScreen;


    public FirstScreenController() {
        MainScreen = new FirstScreen();
        MainScreen.addEmployeeButtonListener(e->{
            MainScreen.dispose();
            new EmployeeController();
        });
        MainScreen.addCustomerButtonListener(e->{
            MainScreen.dispose();

            new CustomerController();
        });
        MainScreen.addExitButtonListener(e->{
            MainScreen.dispose();
        });

    }







}
