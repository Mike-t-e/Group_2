package net.sqlitetutorial;

import java.util.Scanner;

public class Employee {
    private int employeeID; 
    private String work; 
    private String Fname; 
    private String Lname; 
    private String Address; 
    private String Email; 
    private int phoneNr; 
    private String Station;
    private int id = 100;
    private int ssn;
    
    //konstruktor 
    public Employee() {
        Fname = setFname();
        Lname = setLname();
        work = setWork();
        Email = setEmail(); 
        Address = setAddress();
        phoneNr = setPhoneNr();
        Station = setStation(); 
        employeeID = setEmployeeID(); 
        ssn = setSsn();
        System.out.println( Fname + " " +Lname +" " + work + " " + Email + " " + Address + " " + phoneNr + " " + Station +" "+ employeeID + " " + ssn);
    }
    //EmployeeID, Work, First Name, Last Name, Address, Email, Phone Nr, Station 

    //set employeeId
    private int setEmployeeID() { 
        int pn = Integer.parseInt(Integer.toString(phoneNr).substring(0, 2));
        String plus = "";
        id++;
        plus = (pn +""+ id);
        int done = Integer.parseInt(plus);
        return done;

    }


    // set Fname 
    private String setFname() { 
        Scanner in = new Scanner(System.in); 
        System.out.println("Enter your Firstname.");
        String fn = in.nextLine(); 

        return fn; 
    }
    
    //set Lname 
    private String setLname() { 
        Scanner in = new Scanner(System.in); 
        System.out.println("Enter your Lastname.");
        String ln = in.nextLine(); 

        return ln; 

    }

    //set work 
    private String setWork() { 
        Scanner in = new Scanner(System.in); 
        System.out.println("Enter your workrole.");
        String wr = in.nextLine(); 

        return wr; 
    }
    
    //set address 
    private String setAddress() { 
        Scanner in = new Scanner(System.in); 
        System.out.println("Enter your address.");
        String ad = in.nextLine(); 

        return ad; 
    }

    // set emal 
    private String setEmail() { 
        Scanner in = new Scanner(System.in); 
        System.out.println("Enter your Email.");
        String mail = in.nextLine(); 

        return mail; 
    }
    //set phone nr 
    private int setPhoneNr() { 
        Scanner in = new Scanner(System.in); 
        System.out.println("Enter your Phonenumber.");
        int pn = in.nextInt(); 

        return pn; 
    }
    //set station 
    private String setStation() { 
        Scanner in = new Scanner(System.in); 
        System.out.println("Enter your Workstation.");
        String sta= in.nextLine(); 

        return sta; 
    }
    private int setSsn() { 
        Scanner in = new Scanner(System.in); 
        System.out.println("Type in your SSN");
        String Ssn= in.nextLine(); 
        int fin = Integer.parseInt(Ssn);

        return fin; 
    }

    public int getID () {
        return employeeID;
    }


}