package com.trustme.trustme;

public class MoreInformation {

    private int id;
    private String moreInformation;

    public MoreInformation() {}

    public MoreInformation(int id, String moreInformation) {
        this.id = id;
        this.moreInformation = moreInformation;
    }

    public int getId() {return  id;}
    public void setId(int id) {this.id = id;}

    public String getMoreInformation() {return moreInformation;}
    public void  setMoreInformation(String moreInformation) {this.moreInformation =
            moreInformation;}

}
