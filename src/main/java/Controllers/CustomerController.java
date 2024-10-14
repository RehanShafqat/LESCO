package Controllers;

import Models.CustomerManager;
import Views.CustomerLoginScreen;

public class CustomerController {
    CustomerManager customerManager ;
    CustomerLoginScreen customerLoginScreen;

    public CustomerController() {
        customerManager = new CustomerManager();
        customerLoginScreen = new CustomerLoginScreen();

        customerLoginScreen.addLoginButtonListener(e->{
            loginController(customerLoginScreen.getID(),customerLoginScreen.getCNIC());
        });




    }



    public  boolean loginController(String Id,String CNIC){


        if (Id.isEmpty()||CNIC.isEmpty()){
            customerLoginScreen.SayForCredentials();
            return false;
        }


        if (customerManager.login(Id, CNIC)){
            customerLoginScreen.Confirmation();
            customerLoginScreen.dispose();
            //to-do:
            //prompt to other Screen
            return true;
        }
        else{
            customerLoginScreen.WrongCredentials();
        }


        return false;

    }


}
