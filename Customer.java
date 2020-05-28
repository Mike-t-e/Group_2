package net.sqlitetutorial;

import java.io.IOException;
import java.util.Scanner;

public class Customer {
    private int CustomerID;
    private String fName;
    private String eName;
    private int billingID; // nödvändingt??
    private int ID1 = 111;


    // konstruktor
    public Customer() throws IOException{
        ID1 = startID(ID1);
        fName = setFname();
        eName = setEname();
        CustomerID = setCustomerID();
        // skicka vidare till billing info?? alltså skapa nytt object
    }

    // set customerID

    private int setCustomerID() {

        System.out.println("Enter the last two digit of your social security number!");
        Scanner s = new Scanner(System.in);
        String bas = s.nextLine();
        String sID = Integer.toString(ID1);
        String end = sID + bas;
        int fin = Integer.parseInt(end);
        ID1++;
        return fin;
    }

    // set fname
    private String setFname() {

        System.out.println("Vad är ditt förnam?");
        Scanner s = new Scanner(System.in);
        String Fname = s.nextLine();

        return Fname;

    }

    // set ename
    private String setEname() {

        System.out.println("Vad är ditt efternam?");
        Scanner s = new Scanner(System.in);
        String Ename = s.nextLine();

        return Ename;

    }
    public int returnID(){
        return CustomerID;
    }
    // get billingID
    public int setBillingID(int betty){
        return billingID = betty;
    }

    public String getFName(){
        return fName;
    }
    
    public String getEName(){
        return eName;
    }

    public static int startID(int start) throws IOException {
        //kalla på method i main som kollar högsta värdet på id:t
        int value = Connect.getHighestCID();
        //jämför värdet med startvärdet och returnera det högsta
        if (value > start) {
            String cut = Integer.toString(value);
            cut = cut.substring(0,3);
            int fin = Integer.parseInt(cut);
            int finel = fin + 1;
            System.out.println(finel);
            return finel;
        }
        else{ 
            return start;
        }
    }
}