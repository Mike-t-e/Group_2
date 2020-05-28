package net.sqlitetutorial;

import java.util.Scanner;

public class Session {
    private int SessionID;
    private String type; 
    private String location;
    private int availableSlots; 
    private int id = 100; 

//konstruktor
public Session() {
    SessionID = setSessionID();
    type = setType();
    availableSlots = setSlots();
    location = setLocal();
}

//set sessionid 
private int setSessionID() {
    id++;
    return id;
}

// set type
private String setType() {
    System.out.println("Insert Type");
    Scanner ad = new Scanner(System.in);
    String type = ad.nextLine(); 
        return type;
}

// sätt upp en funkion som hämtar info från databasen/ get employeeID


// set available slots 
private int setSlots() { 
    System.out.println("Insert Available slots for the session.");
    Scanner svar = new Scanner(System.in);
    int fin = svar.nextInt(); 
    return fin; 
}

private String setLocal() {
    System.out.println("Insert location");
    Scanner ad = new Scanner(System.in);
    String local = ad.nextLine(); 
        return local;
}

public String getLocal() {
    return location;
}

public int getID() {
    return SessionID;
}

public String getType(){
    return type;
}

public int getSlots(){
    return availableSlots;
}
// SessionID, Type, Location, EmployeeID, Available slots
}