package net.sqlitetutorial;

import java.util.Scanner;

public class Tier {

    private int price; 
    private String location;
    private int TierID; 

    // konstruktor 
    public Tier() { 
        

    }
// set tierID
private int setTierID() {
    Scanner in = new Scanner(System.in); 
    System.out.println("How many tiers?");
    String amountT = in.nextLine(); 
    int T = Integer.parseInt(amountT); 

    return T; 

}

// set price for the different tiers 



// set location 
private String setLocation() { 

    Scanner in = new Scanner(System.in); 
    System.out.println("Which location? ");
    String loc = in.nextLine(); 

    return loc; 

    }
}