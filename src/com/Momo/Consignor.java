package com.Momo;

/**
 * Created by Momo Johnson on 5/11/2016.
 */
public class Consignor extends Album {
    //Variables of the consignor
    private String consignorName;
    private String consignorPhone;
    private double moneyOwed;

    public Consignor (String consignorName, int conID, String consignorPhone, double moneyOwed){
        this.conID = conID;
        this.consignorName = consignorName;
        this.consignorPhone = consignorPhone;
        this.moneyOwed = moneyOwed;
    }

    public String getConsignorName() {
        return consignorName;
    }

    public int getConID() {
        return conID;
    }

    public String getConsignorPhone() {
        return consignorPhone;
    }

    public double getMoneyOwed() {
        return moneyOwed;
    }
}
