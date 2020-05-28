package net.sqlitetutorial;

import java.io.IOException;
import java.util.Scanner;

public class Purchase {
    //PaymentID, Type, Amount, Performed by, BillingID (Billing info,member ) 
    private int paymentID;
    private String type;
    private double amount;
    private int billingID;
    private static int id1 =100;



    public Purchase() throws IOException{
        id1 = startID(id1);
        type = setType();
        paymentID = setID();
        amount = setAmount();
    }

    private int setID(){
        int id;
        id1 = id1 + 2;
        id = id1;
        return id;
    }

    private String setType(){
        System.out.println("Paying with card? (Y/N)");
        Scanner in = new Scanner(System.in);
        String val = in.nextLine().toUpperCase();
        char valet = val.charAt(0);
        if (valet == 'Y'){
            return "Card";
        }
        else {

            return "Cash";
        }
    }

    private double setAmount() {
        System.out.println("Type in the amount paid.");
        Scanner in = new Scanner(System.in);
        double am = in.nextDouble();
        return am;
    }

    public int getID(){
        return paymentID;
    }

    public double getAmount(){
        return amount;
    }
    
    public String getType() {
        return type;
    }


    public static int startID(int start) throws IOException {
        //kalla på method i main som kollar högsta värdet på id:t
        int value = Connect.getHighestPID();
        //jämför värdet med startvärdet och returnera det högsta
        if (value > start) {
            return value;
        }
        else{ 
            return start;
        }
    }
}