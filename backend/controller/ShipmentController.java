package com.courierms.controller;
import com.courierms.model.Shipment;
import com.courierms.dao.ShipmentDAO;
public class ShipmentController {
    private ShipmentDAO shipmentDAO;
    public ShipmentController() {
        this.shipmentDAO=new ShipmentDAO();
    }
    public String book_shipment(Shipment shipment) {
        boolean saved = shipmentDAO.saveShipment(shipment);
        if (saved) {
            return "Shipment booked!";
        } else {
            return "Booking failed!";
        }
    }
        public Shipment trackShipment(String trackingID){
            return shipmentDAO.findTrackingID(trackingID);
        }
    }

