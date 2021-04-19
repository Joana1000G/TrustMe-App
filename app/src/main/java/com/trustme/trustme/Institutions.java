package com.trustme.trustme;

public class Institutions {

    private int id;
    private String nameInstitution;
    private String hoursInstitution;
    private String phoneInstitution;
    private String address;

    public Institutions() {}

    public Institutions(int id, String name, String hoursInstitution, String phoneInstitution,
                        String address) {
        this.id = id;
        this.nameInstitution = nameInstitution;
        this.hoursInstitution = hoursInstitution;
        this.phoneInstitution = phoneInstitution;
        this.address = address;
    }

    public int getId() {return  id;}
    public void setId(int id) {this.id = id;}

    public String getNameInstitution() {return nameInstitution;}
    public void setNameInstitution(String nameInstitution) {this.nameInstitution = nameInstitution;}

    public String getHoursInstitution() {return hoursInstitution;}
    public void setHoursInstitution(String hoursInstitution) {this.hoursInstitution =
            hoursInstitution;}

    public String getPhoneInstitution() {return phoneInstitution;}
    public void setPhoneInstitution(String phoneInstitution) {this.phoneInstitution =
            phoneInstitution;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

}
