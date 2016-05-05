package com.Momo;


/** Represents a vinyl record (LP)
 *  A LP has an artist, title, and condition and price
 */

public class LP extends Album{


    //LP objects can use the artist, title and price variables, because LP is an album.

    //We need to specialize LP to add a variable for condition.
    private int condition;   //1 = barely playable, 5 = mint

    public LP(String artist, String title, int condition, double price) {
        this.artist = artist;
        this.title = title;
        this.condition = condition;
        this.price = price;
    }

    @Override
    public String toString(){
        return "Format = LP, Artist = " + this.artist + ", Title = " + title + ", Condition = " + this.condition  + ", Price $" + this.price;
    }
}

