package Controllers.Customer;

import Controllers.FirstScreenController;
import Models.CustomerManager;
import Views.Customer.CustomerLoginScreen;

public class CustomerLoginController {
    CustomerManager customerManager ;
    CustomerLoginScreen customerLoginScreen;

    public CustomerLoginController() {
        customerManager = new CustomerManager();
        customerLoginScreen = new CustomerLoginScreen();

        customerLoginScreen.addLoginButtonListener(e->{
            login(customerLoginScreen.getID(),customerLoginScreen.getCNIC());
        });
        customerLoginScreen.addBackButtonListener(e->{
            customerLoginScreen.ExitScreen();
            new FirstScreenController();
        });




    }

    public  boolean login(String Id, String CNIC){


        if (Id.isEmpty()||CNIC.isEmpty()){
            customerLoginScreen.SayForCredentials();
            return false;
        }


        if (customerManager.login(Id, CNIC)){
            customerLoginScreen.Confirmation();
            customerLoginScreen.dispose();
            new CustomerSessionController(Id,CNIC);
            return true;
        }
        else{
            customerLoginScreen.WrongCredentials();
        }


        return false;

    }


}
