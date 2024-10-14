package Controllers.Customer;

import Models.BillingManager;
import Models.NADRAManager;
import Structures.Billing;
import Views.Customer.CustomerSessionScreen;
import Views.Customer.ViewBillScreen;

import javax.swing.*;

public class CustomerSessionController {
    CustomerSessionScreen customerSessionScreen ;
    ViewBillScreen viewbill  ;
    BillingManager billingManager;
    NADRAManager nadraManager;


    public CustomerSessionController(String id, String CNIC) {
        billingManager = new BillingManager();
        customerSessionScreen = new CustomerSessionScreen();
        nadraManager = new NADRAManager();
        viewbill = new ViewBillScreen();
        //customer session screen Buttons
        customerSessionScreen.addViewBillButtonListener(e->{
            ViewBill(id);
        });
        customerSessionScreen.addUpdateCnicButtonListener(e->{
            updateCnic(CNIC);
        });
        customerSessionScreen.addExitButtonListener(e->{
            ExitCustomerSessionScreen();
        });


        //viewBill Screen Buttons
        viewbill.addActionListenerForBack(e->{
            ExitViewBillScreen();
        });




    }
    public void ViewBill(String id){
        //calling model
        Billing bill = billingManager.viewCurrentBill(id);
        if (bill!=null) {
            //calling view

            viewbill.initScreen(bill);
        }else{
            customerSessionScreen.BillNotFount();
        }
    }
    public void updateCnic(String CNIC) {
       String message =  nadraManager.updateCNICExpiry(CNIC);
       customerSessionScreen.displayMessageForExpiryUpdation( message );
    }
    public void ExitCustomerSessionScreen(){
        customerSessionScreen.ExitScreen();
    }
    public void ExitViewBillScreen(){
        viewbill.Exit();
    }

}
