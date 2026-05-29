package com.courierms.controller;
import com.courierms.dao.ShipmentDAO;
import com.courierms.dao.UserDAO;
import com.courierms.model.Shipment;
public class AdminController {
    private ShipmentDAO shipmentDAO;
    private UserDAO userDAO;
    public AdminController(){
        this.shipmentDAO=new ShipmentDAO();
        this.userDAO=new UserDAO();
    }
    public String updateStatus(String trackingID,String newStatus){
        Shipment s=shipmentDAO.findTrackingID(trackingID);
        if(s!=null){
            s.setStatus(newStatus);
            return "status updated!";
        }
        else{
            return "shipment failed!";
        }
    }
    public String addUser(String name,String email,String phone_no,String password,String role){
        boolean saved=userDAO.saveUser(name,email,phone_no,password,role);
        if (saved) {

            return "User added!";}
            else{
                return "User not added!";
            }
        }
    }

