package net.sqlitetutorial;

import java.io.IOException;
import java.util.Scanner;

public class Member {
    private int memberID;
    private int id1 = 222;
    private String fName;
    private String eName;
    private int phoneNr;
    private String eMail;
    private String address;
    private int billingID;
    private String memberTier;
    private int ssn;
    
    // Konstruktor
    public Member () throws IOException{
        id1 = startID(id1);
        ssn = setSsn();
        memberID = memID();
        fName = setFname(); //use boolean to devise if need to fetch from customer or nah
        eName = setEname();
        eMail = setEmail();
        phoneNr = setPhone();
        address = setAdress();
        memberTier = setTier();
    }

    // Generate memberID 
    private int memID () {
        String id;
        id = Integer.toString(id1);
        id1++;
        String laj = Integer.toString(ssn).substring(0, 2);
        int last2 = Integer.parseInt(laj);
        String fin = id + last2;
        int finale = Integer.parseInt(fin);
        return finale;
    }
    //set first name and last name OBS separat!

    private String setFname() {
        String Fname; 
        
        System.out.println("Vad är ditt förnam?");
        Scanner s = new Scanner(System.in); 
        Fname = s.nextLine(); 

        return fName = Fname; 
    }

    private String setEname(){

        System.out.println("Vad är ditt efternamn?");
        Scanner s = new Scanner(System.in); 
        
        return eName = s.nextLine();
    }

       

        

    
        // set email, phone and address seperatly

    private String setEmail() {
        String mail;
        System.out.println("Type your email adress");
        Scanner in = new Scanner(System.in);
        mail = in.nextLine();

        return mail;

    }

    private int setPhone() {
        int phone;
        System.out.println("Type your Phone Nr");
        Scanner in = new Scanner(System.in);
        phone = in.nextInt();

        return phone;
        
    }

    private String setAdress() {
        String Adress;
        System.out.println("Type your adress");
        Scanner in = new Scanner(System.in);
        Adress = in.nextLine();

        return Adress;
    }
    // set billingID 
    public int setBID(int bruttan){
        return billingID = bruttan;
    }
    // set mTier

    // set personalinfo

    private int setSsn () {

        String Ssn;
        System.out.println("Type in your SSN");
        Scanner input = new Scanner(System.in);
        Ssn = input.nextLine();
        int fin = Integer.parseInt(Ssn);

        return fin;
    }

    public int getID () {
        return memberID;
    }
    
    public String getFName(){
        return fName;
    }

    public String getEName(){
        return eName;
    }

    public String getEMail(){
        return eMail;
    }

    public int getPNR(){
        return phoneNr;
    }

    public String getAddress(){
        return address;
    }

    public int getSSN(){
        return ssn;
    }

    public String getTier() {
        return memberTier;
    }
    private String setTier() throws IOException{
        String set="";
        return set = Connect.getTier();
    }

    public static int startID(int start) throws IOException {
        //kalla på method i main som kollar högsta värdet på id:t
        int value = Connect.getHighestMID();
        //jämför värdet med startvärdet och returnera det högsta
        if (value > start) {
            String cut = Integer.toString(value);
            cut = cut.substring(0,3);
            int fin = Integer.parseInt(cut);
            int finel = fin + 1;
            return finel;
        }
        else{ 
            return start;
        }
    }
}