package com.courierms.model;

public abstract class Person {
    private String name;
    private String email;
    private String phone_no;
    private String password;
    public Person(String namme,String email,String phone_no,String password) {
        this.name = name;
        this.email = email;
        this.phone_no=phone_no;
        this.password=password;
    }
    public abstract String getRole();

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getPassword() {
        return password;
    }
}
