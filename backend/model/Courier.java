package com.courierms.model;

public class Courier extends Person {
    private String courierID;
    public Courier(String courierID, String name, String email, String phone_no, String password){
        super (name, email, phone_no, password);
    }
    @Override
    public String getRole(){
        return "Courier";
    }

    public String getCourierID() {
        return courierID;
    }
}
