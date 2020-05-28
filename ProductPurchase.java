package net.sqlitetutorial;

import java.io.IOException;

public class ProductPurchase extends Purchase {
    private int productID;

    public ProductPurchase(int id) throws IOException{
        productID = id;
    }

}