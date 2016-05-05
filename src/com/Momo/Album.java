package com.Momo;

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
}

