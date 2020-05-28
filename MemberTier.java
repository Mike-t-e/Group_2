package net.sqlitetutorial;

import java.util.Scanner;

public class MemberTier {

    //hur många tiers ska fråga användaren (använda final för att förhindra förändringar kanske?)
    public MemberTier() {
        int amount = setAmount();
        int tier[] = new int[amount];
        String tierName[] = new String[amount];
        for(int i = 0; i < tier.length; i++){
            System.out.println("Enter the name of tier: " + i);
            Scanner lele = new Scanner(System.in);
            tier[i] = i;
            tierName[i] = lele.nextLine();
            System.out.println(tier[i] + tierName[i]);
        }
        
    }

    public int setAmount(){
        System.out.println("How many tiers do you need?");
        Scanner in = new Scanner(System.in);
        int antal = in.nextInt();
        return antal;
    }

    // skapa en method med en loop som kör igenom tier[] och ger ett värde från 1 och uppåt

    // skapa method get tier för att skicka vidare till member med mera

    
}
