package net.sqlitetutorial;

import java.util.Scanner;

public class Consumable {

    private int consumableID; 
    private double price; 
    private String name; 
    private int id = 100;
    //kontsruktor 
    public Consumable() {
        name = setName();
        price = setPrice();
        consumableID = setConsumbleID();
        System.out.println(name + " " + price + " " +consumableID);

    }
    
    // set consumableID
    private int setConsumbleID() {
        double tva = Double.parseDouble(Double.toString(price).substring(0, 2));
        int ny = (int)tva;
        String plus = "";
        id++;
        plus = (ny +""+id);
        int done = Integer.parseInt(plus);
        return done;
    } 


    // set price 
    private double setPrice(){
        System.out.println("What's the price for the product?");
        Scanner sc = new Scanner(System.in);
        double pris = sc.nextDouble();
        sc.close();
        return pris;

    }


    // set name for consumable 
    private String setName() {

        Scanner in = new Scanner(System.in); 
        System.out.println("Please enter consumable-productname? ");
        String name = in.nextLine(); 
        in.close();

        return name; 
    }


}   