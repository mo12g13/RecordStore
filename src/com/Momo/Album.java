package com.Momo;

import java.util.Date;

/**
 * Superclass of CD and LP
 * And the future 8-track class
 */

public class Album {

    //We need to share these variables with Album's subclasses
    //So we can make them protected
    //protected variables are accessible to other classes in the
    //same package, and this class's subclasses
    protected String artist;
    protected String title;
    protected double price;
    java.util.Date date;
    protected int conID;

    //Another way is to keep them private and use getters and setters.

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getConID() {
        return conID;
    }

    public void setConID(int conID) {
        this.conID = conID;
    }
}

