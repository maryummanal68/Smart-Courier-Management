package com.courierms.controller;
import com.courierms.dao.UserDAO;

public class AuthController {
    private UserDAO userDAO;
    public AuthController(){
        this.userDAO=new UserDAO();
    }
    public String register(String name,String email,String phone_no,String password,String role){
        boolean saved= userDAO.saveUser(name, email, phone_no, password, role);
        if (saved){
            return "Registration successful!";
        }
        else{
            return "Registration failed!";
        }
    }
    public String login(String email,String passowrd){
        boolean found= userDAO.loginCheck(email, passowrd);
        if (found){
            return "Login successful!";
        }
        else{
            return "Login failed!";
        }
    }
}
