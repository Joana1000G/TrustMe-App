package com.trustme.trustme;

public class Comments {

    private int id;
    private String user;
    private String date;
    private String commentary;
    //TODO preguntar como modelar los datos de las estrellas

    public Comments() {}

    public Comments(int id, String user, String date, String commentary) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.commentary = commentary;
    }

    public int getId() {return  id;}
    public void setId(int id) {this.id = id;}

    public String getUser() {return user;}
    public void setUser(String user) {this.user = user;}

    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}

    public String getCommentary() {return commentary;}
    public void setCommentary(String commentary) {this.commentary = commentary;}
}
