package net.sqlitetutorial;

import java.util.Scanner;


public class BillingInfo {

    private int billingID; 
    private int customerID; 
    private String invoiceAddress; 
    private int memberID;

    private int ID1 = 1; 
    

    // los konstruktos 
    public BillingInfo (int custID) { 
        customerID = custID; 
        invoiceAddress = setAddress();
        billingID = generateID(custID);
    }
    
    private String setAddress() { 
        System.out.println("Write your address!" );
        Scanner in = new Scanner(System.in); 
        return in.nextLine(); 
    }
    // slumpa ID 

    private int generateID(int cust) {
        Integer.toString(cust);
        String bID = (cust + "" + ID1);
        ID1++;
        int billing = Integer.parseInt(bID);
        return billing;
        }

    // connect billingID with memebrID 

    // connect billingID with customerID 

    // return billingID
    public int returnID(){
        return billingID;
    }
    public String getInvoice(){
        return invoiceAddress;
    }
}