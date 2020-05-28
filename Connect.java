package net.sqlitetutorial;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Statement;

public class Connect {
     /** Author sqlitetutorial.net (connect method and directory structure)(a few edits have been made from the authors original work)
     * Connect to a sample database
     */
    public static String url = "jdbc:sqlite:/Users/mike.t.e/sqlite/db/chinook.db";
    public static void connect() {
        Connection conn = null;
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("____   ____      ___  ___  ___           ___\n|    |  |  |\\  ||    |    |        /\\   |   |\n|--  |  |  | \\ ||---  ---  ---    /__\\  |---\n|    |  |  |  \\||___  ___| ___|	 /    \\ |___|\n");
        connect();
        String val = "";
        while (true){
            System.out.println("------------------------------------\nSelect one of the following options:\n------------------------------------\n Y. For membership/product purchase \n N. Alter membership \n B. To book a workout session \n M. To manage an active booking \n D. To manage or delete a session \n C. To create a new workout session \n R. to print ticket \n Q. To quit \n------------------------------------");
            Scanner in = new Scanner(System.in);
            val = in.nextLine();
            switch (val.toUpperCase()){
                case "Y": 
                    createPurchase();
                    break;
                case "N":
                    alterMembership();
                    break;
                case "Q":
                    System.exit(0);
                case "B":
                    bookSession();
                    break;
                case "M":
                    manageSessionCust();
                    break;
                case "D":
                    manageSession();
                    break;
                case "C":
                    createSession();
                    break;
                case "R":
                    System.out.println("\n--------------------------\n|	Ticket		 |\n|			 |\n| Class: fire_yoga	 |\n|			 |\n|this ticket is made from|\n|    recycled bit(s)!    |\n--------------------------\n");
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }
    //payment skapar objekt customer som sedan skapar member o billingID
    // SALES/MEM_MANAGEMENT SYSTEM START
    public static void createPurchase() throws IOException {  //sett parametrar
        int memID;
        System.out.println("Does the customer wish to purchase a membership? (Y/N)");
        Scanner que = new Scanner(System.in);
        String svar = que.nextLine();
        String svaret = svar.toUpperCase();
        switch (svaret) {
            case "Y":
                // skapa alla nödvändinga klasser + ta fram all info från klasser
                //skapar en customer
                
                Customer c1 = new Customer(); 
                int custID = c1.returnID();
                System.out.println(custID);
                String cfName = c1.getFName();
                String ceName = c1.getEName();

                // skapar billing info 
                BillingInfo b1 = new BillingInfo(custID);
                int bID = b1.returnID();
                String invoiceAddress = b1.getInvoice();

                // Skapar en member 
                Member m1 = new Member(); 
                memID = m1.getID();
                System.out.println(memID);
                String mfName = m1.getFName();
                String meName = m1.getEName();
                String meMail = m1.getEMail();
                String mAddress = m1.getAddress();
                int mpNr = m1.getPNR();
                int mSSN = m1.getSSN();
                String tier = m1.getTier();

                // Skapar purchase och member purchase via subclass
                MemberPurchase nems = new MemberPurchase(memID);
                int nID = nems.getID();
                double nAmount = nems.getAmount();
                String nType = nems.getType();

                    // ALLA INSERTS OSV SKA GÖRAS OM TILL EGNA METHODER
                //kalla på alla methoder som interagerar med DB med deras parametrar och använd info från ovan

                insertCustomer(custID, cfName, ceName, nID, bID);
                insertMember(memID, mfName, meName, mpNr, meMail, mAddress, bID, nID, mSSN, tier);
                insertMPurchase(nID, nType, nAmount, bID, memID);
                insertBilling(bID, custID, invoiceAddress);
                break;

            case "N":

                Customer c2 = new Customer(); 
                int cID = c2.returnID();
                System.out.println(cID);
                String cfiName = c2.getFName();
                String clName = c2.getEName();

                BillingInfo b2 = new BillingInfo(cID);
                int bID2 = b2.returnID();
                String invoiceAddress2 = b2.getInvoice();

                //kalla på method för att hämta consumable från DB
                int productTest = getConsumable();
                ProductPurchase p2 = new ProductPurchase(productTest);
                int prodID = p2.getID();
                double prodAmount = p2.getAmount();
                String prodType = p2.getType();

                insertCustomer(cID, cfiName, clName, prodID, bID2);
                insertBilling(bID2, cID, invoiceAddress2);
                insertPPurchase(prodID, prodType, prodAmount, bID2, productTest);
                break;
        }
    }

    public static void alterMembership() throws IOException {
        //meny på möjliga ändringar 
        boolean alter = true;
        do {
             
            System.out.println("----------------------------\n What do you need to change? \n----------------------------\n U. for upgrade \n C. for personal information \n T. to terminate membership \n----------------------------");
            Scanner input = new Scanner(System.in);
            String s = input.nextLine(); 
            switch(s.toUpperCase()) {

                case "U": 
                    int memID = getMemberID();
                    //skapa en method för att hämta billingID i slutetet av denna fil
                    //kalla på methoden här
                    // kbk Java Louise

                    MemberPurchase nems = new MemberPurchase(memID);
                    int nID = nems.getID();
                    double nAmount = nems.getAmount();
                    String nType = nems.getType();
                    int bID = getExistingBillingID();
                    insertMPurchase(nID, nType, nAmount, bID, memID);
                    String tier = getTier();
                    updateMember(memID, tier);
                    System.out.println("Uppgrade succesfull");
                    break;
    
                case "C":
                    System.out.println("Fill in the following new information \n Name, Phonenummer, Address, Email. ");
                    Scanner in = new Scanner(System.in);
                        System.out.println("Enter the new name.");
                        String fName = in.next();
                        String eName = in.next();
                        System.out.println("Enter the phonenumber.");
                        int phone = in.nextInt();
                        System.out.println("Enter the address.");
                        Scanner in2 = new Scanner(System.in);
                        String address = in2.nextLine();
                        System.out.println("Enter the email.");
                        String email = in2.nextLine();
                        // fråga om personnummer
                        int fin = getMemberID();
                        // sök upp info från membershipInfo i db
                        
                        // använd memid i update satsen :)
                        try {
                            Connection conn = DriverManager.getConnection(url);
                            Statement stmt = conn.createStatement(); 
                            //  sql är string som motsvarar commando
                            stmt.executeUpdate("update Member set firstName = "+ "\"" + fName + "\", lastName = " + "\"" + eName + "\", phonenr = " + phone + ", Address = " + "\"" + address + "\"" + ", email = " + "\"" + email + "\"" + " where MemberID =" + fin +";");
                            System.out.println("Your information has been updated!");
                        }
                        catch (SQLException e) {
                                System.out.println(e.getMessage());   
                        }
                    break;

                case "T":
                    int ID = getMemberID();
                    deleteMembership(ID);
                    break;
                default:
                    break;
            }
            
        System.out.println("Do you wish to continue? Y/N");
        String sve = input.nextLine().toUpperCase();
        char sv = sve.charAt(0);
        if (sv != 'Y'){
            alter = false;
        }
        } while (alter == true);
        // göra de olika ändringarna 
        // - Uppgradera 
        // - Nergradera 
        // - Ändra personlig info 
        // - säga upp membership (ev spara i databsen ett tag, frysa medlemskap)

    }

    public static void insertCustomer(int custID, String fName, String eName, int pID, int bID) throws IOException {
        String efName = ("\"" + fName +"\"");
        String eeName = ("\"" + eName +"\"");
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement(); 
            //  sql är string som motsvarar commando
            stmt.executeUpdate("insert into Customer values (" + custID +", " + efName + ", " + eeName + ", " + pID + ", " + bID + ");");
            System.out.println("Customer inserted!");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());   
        }
    }
    
    public static void insertMember(int memID, String fName, String eName, int pNr, String eMail, String address, int bID, int pID, int SSN, String tier) throws IOException{
        String efName = ("\"" + fName +"\"");
        String eeName = ("\"" + eName +"\"");
        String eeMail = ("\"" + eMail +"\"");
        String eAddress = ("\"" + address +"\"");
        String eTier = ("\"" + tier +"\"");
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement(); 
            //  sql är string som motsvarar commando
            stmt.executeUpdate("insert into Member values (" + memID +", " + efName + ", " + eeName + ", " + pNr + ", " + eeMail + ", "+ eAddress + ", "+ bID + ", " + pID + ", " + eTier +");");
            stmt.executeUpdate("insert into MemberInfo values (" + memID + ", " + SSN + ");");
            System.out.println("Member inserted!");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());   
        }
    }

    public static void insertMPurchase(int pID, String type, double amount, int bID, int memID)throws IOException {
        String eType = ("\"" + type + "\"");
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement(); 
            //  sql är string som motsvarar commando
            stmt.executeUpdate("insert into Purchase values (" + pID +", " + eType + ", " + amount + ", " + bID + ");");
            stmt.executeUpdate("insert into MemberPurchase values (" + pID +", " + eType + ", " + amount + ", " + bID + ", " + memID +");");
            System.out.println("Purchase (member) inserted!");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage()); 
        }
    }

    public static void insertPPurchase(int pID, String type, double amount, int bID, int conID)throws IOException {
        String eType = ("\"" + type + "\"");
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement(); 
            //  sql är string som motsvarar commando
            stmt.executeUpdate("insert into Purchase values (" + pID +", " + eType + ", " + amount + ", " + bID + ");");
            stmt.executeUpdate("insert into ProductPurchase values (" + pID +", " + eType + ", " + amount + ", " + bID + ", " + conID +");");
            System.out.println("Purchase (member) inserted!");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage()); 
        }
    }

    public static void insertBilling(int bID, int cID, String invoiceAddress) throws IOException{
        String eAddress = ( "\"" + invoiceAddress + "\"");
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement(); 
            //  sql är string som motsvarar commando
            stmt.executeUpdate("insert into BillingID values (" + bID +", " + cID + ", " + eAddress + ");");
            System.out.println("BillingInfo inserted!");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage()); 
        }
    }
    // SALES/MEM_MANAGEMENT SYSTEM STOPP
    //OBS!! ALLA METODER SOM INTERAGERAR MED DB MÅSTE HA PARAMERAR SKICKADE TILL SIG OBS!!
    
    // BOOKING SYSTEM START

    public static void createSession() throws IOException{
        Session s1 = new Session();
        int sID = s1.getID();
        String local = s1.getLocal();
        String type = s1.getType();
        int slots = s1.getSlots();

        insertSession(sID, local, type, slots);

    }
    
    public static void bookSession() throws IOException {
        int memID = getMemberID();
        listAllSessions();
            // Call all sessions from database and list in sysout

            // ask for the session you want to book
            System.out.println("Enter the SessionID for the class you want to book");
            Scanner ses = new Scanner(System.in);
            int choosen = ses.nextInt();
            // insert into activeSession in DB
            insertActiveSession(memID, choosen);

    }
    
    public static void manageSessionCust() throws IOException {
        // Get memberID 
        int memID = getMemberID();
        System.out.println(memID);
        listActivSessions(memID);
        // call function with the id to be removed
        System.out.println("Do you wish to delete a booking? Y/N");
        Scanner svar = new Scanner(System.in);
        String svaret = svar.nextLine().toUpperCase();
        switch (svaret) {
            case "Y":
                deleteActiveSession(memID);
                break;
            default:
                break;
        }
    }

    public static void manageSession() throws IOException {
        boolean valet = true;
        listAllSessions();
        while (valet == true) {

            System.out.println("Enter the ID of the session you want to remove");
            Scanner edit = new Scanner(System.in);
            int sID = edit.nextInt();
            // call function with parameter SID to remove said session
            deleteSession(sID);
            // create do while loop to allow multiple actions at a time
            
            System.out.println("Need to remove another session? Y/N");
            Scanner test = new Scanner(System.in);
            String svar = test.nextLine().toUpperCase();
            char svar1 = svar.charAt(0);
            if (svar1 != 'Y') {
                valet = false;
            }
        } 
        
    }
    // BOOKING SYSTEM STOPP

    public static void createMember() throws IOException {
        Member m1 = new Member(); 
        int m = m1.getID();
        System.out.println(m);

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement(); 
            //  sql är string som motsvarar commando
            stmt.execute("insert into Member (MemberID, firstName, lastName, phoneNr, Email, Address, PaymentID) values (1001, \"Gul-Blnd\", \"Tantn\", 010101, \"gla.blnd@blend.se\", \", näsa, hals\", 1002);");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());   
        }
    }
    // send billingInfo into database
    public static void createBilling() throws IOException { 

    try {
        Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement(); 
        //  sql är string som motsvarar commando
        stmt.execute("insert into BillingInfo (BillingID, CustomerID, invoiceAddress) values (1234, 1212, \"bohus\");");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());   
        }
    }
    // send Employee into database
    public static void createEmployee() throws IOException {
        Employee e1 = new Employee();
        int em = e1.getID();
        System.out.println(em);

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement(); 
            //  sql är string som motsvarar commando
            stmt.execute("insert into Employee (EmployeeID, work, firstName, lastName, location, phoneNr, Email, Address) values (1000, \"Gula-Blend\", \"Tanten\", \"blend\", 010101, \"gula.blend@blend.se\", \"öron, näsa, hals\", 1001);");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());   
        }

    }

    public static void insertSession(int sID, String local, String type, int slots) throws IOException{
        String eLocal = ("\"" + local + "\"");
        String eType = ("\"" + type + "\"");
        System.out.println(eType +" " + eLocal);
        System.out.println("enter employeeID");
        Scanner in = new Scanner(System.in);
        int eID = in.nextInt(); // default value since management system handels registration of emplyoees.
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement(); 
            //  sql är string som motsvarar commando
            stmt.executeUpdate("insert into session values (" + sID + ", " + eLocal + ", " + eType + ", " + eID + ", " + slots + ");");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());   
        }
    }

    public static void insertActiveSession(int memID, int sID) throws IOException {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement(); 
            //  sql är string som motsvarar commando
            stmt.executeUpdate("insert into activeSession values (" + sID + ", " + memID + ");");
            System.out.println("You have now booked session: " + sID);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());   
        } 

    }

    public static int getConsumable() throws IOException {
            // hämta consumableID vid purchase
            System.out.println("What product is purchased?");
            Scanner in = new Scanner(System.in);
            //Spara input från användaren i en variabel
            String sd = in.nextLine();
            // använd variabel för att söka i databasen
            int fin = 0;
            try {
                Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select ConsumableID from Consumable where name =" + sd +";");
                fin = rs.getInt("ConsumableID");
            } catch (Exception e) {
                System.out.println(e.getMessage());   
            }
            return fin;
    }

    public static int getMemberID() {
        System.out.println("Enter your social security number.");
        Scanner in = new Scanner(System.in);
        int SSN = in.nextInt(); 
        int fin = 0;
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("Select MemberID from MemberInfo where SSN =" + SSN + ";");
            fin = rs.getInt("MemberID");
            rs.close();
            
        }
        catch (SQLException e) {
        System.out.println(e.getMessage());   
        }
        return fin;
    }
    
    public static void deleteMembership(int id ) { 
        System.out.println("Are you sure you want to delete : " + id + "?");
        Scanner svar = new Scanner(System.in); 
        String svaret = svar.nextLine().toUpperCase();
        switch (svaret) {
            case "Y":
                try {
                    Connection conn = DriverManager.getConnection(url);
                    Statement stmt = conn.createStatement(); 
                    stmt.executeUpdate("DELETE from Member where MemberID = " + id +";" );
                    stmt.executeUpdate("DELETE from MemberInfo where MemberID = " + id +";" );
                    System.out.println("Membership deleted");
                }
                catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;
        
            default:
                System.out.println("Membership not deleted");
                break;
        }
    }

    public static int getExistingBillingID() throws IOException {
        //fråga efter personnummer på medlem 
        System.out.println("Please enter your Social Security Number");
        Scanner answer = new Scanner(System.in);
        int SSN = answer.nextInt();
        // söka efter personnummeret i db där de matchar billingid 
        // söka efter billing id där memberid matchar (välj member id där det matchar angivet pnr)
        int fin = 0;
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT BillingID from Member where MemberID =(SELECT MemberID from MemberInfo where SSN= " + SSN + ");" );
            fin = rs.getInt("BillingID");
            rs.close();
        }
        catch (SQLException e) {
        System.out.println(e.getMessage());   
        }
        return fin;
    }

    public static void listAllSessions() throws IOException {        
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement(); 
            //  sql är string som motsvarar commando
            ResultSet rs = stmt.executeQuery("select SessionID, type from Session");
            while(rs.next()) {
                int sID = rs.getInt("SessionID");
                String Type = rs.getString("Type");
                System.out.println("Session: " + Type + " SessionID: " + sID);
            }
            conn.close();
            rs.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public static void listActivSessions(int memID) throws IOException{
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement(); 
            //  sql är string som motsvarar commando
            ResultSet rs = stmt.executeQuery("select * from activeSession where MemberID = " + memID + ";");
            while(rs.next()) {
                int sID = rs.getInt("SessionID");
                String mID = rs.getString("MemberID");
                System.out.println(mID + " is booked on the following" + " SessionID: " + sID);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());   
        }
        
    }

    public static void deleteActiveSession(int memID) throws IOException { 
            listActivSessions(memID);
            System.out.println("Enter the sessionID of the booking you'd want to delete");
            Scanner svar = new Scanner(System.in);
            int sID = svar.nextInt();
            try {
                Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement(); 
                //  sql är string som motsvarar commando
                stmt.executeUpdate("delete from activeSession where SessionID = "+ sID + " and MemberID in (select MemberID from activeSession where MemberID = " + memID + ");");
                System.out.println("You have now deleted session: " + sID);
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());   
            } 
    }
        // ask which booking shall be removed
        // remove said booking from DB

    public static void deleteSession(int sID) throws IOException {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement(); 
            //  sql är string som motsvarar commando
            stmt.executeUpdate("delete from Session where SessionID = " + sID + ";");
            stmt.executeUpdate("delete from activeSession where SessionID = " + sID + ";");
            System.out.println("Session: " + sID + " is now deleted.");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());   
        }
    }
    public static int getHighestPID() throws IOException {
        int fin = 0;
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("select PaymentID from Purchase where PaymentID in (SELECT MAX(PaymentID) from Purchase);" );
            fin = rs.getInt("PaymentID");
            System.out.println(fin);
            rs.close();
        }
        catch (SQLException e) {
        System.out.println(e.getMessage());   
        }
        return fin;
    }

    public static int getHighestCID() throws IOException {
        int fin = 0;
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("select customerID from customer where customerID in (SELECT MAX(customerID) from customer);" );
            fin = rs.getInt("customerID");
            System.out.println(fin);
            rs.close();
        }
        catch (SQLException e) {
        System.out.println(e.getMessage());   
        }
        return fin;
    }

    public static int getHighestMID() throws IOException {
        int fin = 0;
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("select MemberID from member where memberID in (SELECT MAX(memberID) from member);" );
            fin = rs.getInt("memberID");
            System.out.println(fin);
            rs.close();
        }
        catch (SQLException e) {
        System.out.println(e.getMessage());   
        }
        return fin;
    }

    public static void updateMember(int memID, String tier) throws IOException {
        String eTier = ("\"" + tier + "\"");
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement(); 
            //  sql är string som motsvarar commando
            stmt.executeUpdate("update Member set membershipTier = " + eTier + " where MemberID = " + memID + ";");
            System.out.println("Tier is now upgraded to: " + tier);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());   
        }
    }
    
    public static String getTier() throws IOException {
        String tier = " ";
        // lista tiers
        listTiers();
        // ta emot input från användaren vilket hen väljer
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the id of the chosen tier");
        int val = in.nextInt();
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("select location from Tier where TierID = "+ val + ";" );
            tier = rs.getString("location");
            rs.close();
        }
        catch (SQLException e) {
        System.out.println(e.getMessage());   
        }
        // koppla sedan in till upgrade och ny medlem
        System.out.println(tier);
        return tier;
    }

    public static void listTiers() throws IOException {
        String fin = "";
        String fin1 = "";
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery("select location, tierid from Tier;" );
            while (rs.next()){
                fin = rs.getString("location");
                fin1 = rs.getString("TierID");
            System.out.println("TierID: " + fin1 + " Tier: " + fin);
            }
            rs.close();
        }
        catch (SQLException e) {
        System.out.println(e.getMessage());   
        }
    }
}