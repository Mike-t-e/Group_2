package net.sqlitetutorial;

import java.io.IOException;

public class MemberPurchase extends Purchase {
    private int MPID;
    
    //konstruktor
    public MemberPurchase(int memIDT) throws IOException {
        MPID = memIDT;
    }

}