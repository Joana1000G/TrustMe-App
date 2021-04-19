package com.trustme.trustme;

public class People {

    private int id;
    private String name;
    private String description;
    private Long phone;

    public People() {}

    public People(int id, String name, String description, Long phone) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.phone = phone;
    }

    public int getId() {return  id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public Long getPhone() {return phone;}
    public void setPhone(Long phone) {this.phone = phone;}




}
