package com.courierms.service;
import com.courierms.dao.ShipmentDAO;
import com.courierms.model.Shipment;

public class TrackingService {
    private ShipmentDAO shipmentDAO;
    public TrackingService(){
        this.shipmentDAO=new ShipmentDAO();
    }
    public String getStatus(String trackingID){
        Shipment s= shipmentDAO.findTrackingID(trackingID);
        if(s!=null){
            return "Status: "+s.getStatus();
        }
        else{
            return "Shipment failed";
        }
    }
}
