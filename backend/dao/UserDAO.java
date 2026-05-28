package com.courierms.dao;

import com.courierms.model.Person;
import com.courierms.util.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public boolean saveUser(String name, String email, String phone_no, String password, String role) {
        try {
            Connection con = DBManager.getConnection();
            String sql_query = "INSERT INTO users(name,email,phone_no,password,role) VALUES(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql_query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone_no);
            ps.setString(4, password);
            ps.setString(5, role);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("error: "+e.getMessage());
            return false;
        }
    }
    public boolean loginCheck(String email,String password){
        try{
            Connection con=DBManager.getConnection();
            String sql_query="SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement ps= con.prepareStatement(sql_query);
            ps.setString(1,email);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("error "+e.getMessage());
            return false;
        }
    }

}
