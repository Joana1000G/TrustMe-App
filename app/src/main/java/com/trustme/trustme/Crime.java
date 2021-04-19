package com.trustme.trustme;

import android.widget.Button;

public class Crime {

    private int id;
    private String crime;

    public Crime() {}

    public Crime(int id, String Crime) {
        this.id = id;
        this.crime = crime;
    }

    public int getId() {return  id;}
    public void setId(int id) {this.id = id;}

    public String getCrime() {return crime;}
    public void  setCrime(String crime) {this.crime = crime;}
}
