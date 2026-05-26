package com.courierms.model;

public class Customer extends Person{
    private String customerID;
    public Customer(String customerID, String name, String email, String phone_no, String password){
        super (name,email,phone_no,password);
        this.customerID=customerID;
    }

    @Override
    public String getRole(){
        return "Customer";
    }

    public String getCustomerID() {
        return customerID;
    }
}
