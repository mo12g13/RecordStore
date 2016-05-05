package com.Momo;

/** Represents a CD at the record store
 *  A CD has an artist, and title and price */

public class CD extends Album {

    //CD objects can use the artist, title and price variables, because CD is an album.

    public CD(String artist, String title, double price) {
        this.artist = artist;
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Format = CD, Artist = " + this.artist + ", Title = " + title + ", Price $" + this.price;
    }
}

