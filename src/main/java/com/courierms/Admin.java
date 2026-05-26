package com.courierms;

import com.courierms.model.Person;

public class Admin extends Person {
    private String adminID;
    public Admin(String adminID, String name, String email, String phone_no, String password){
        super (name, email, phone_no, password);
    }
    @Override
    public String getRole(){
        return "Admin";
    }
    public String getAdminID(){
        return adminID;
    }
}
