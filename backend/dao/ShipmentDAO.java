package com.courierms.dao;
import com.courierms.model.Shipment;
import com.courierms.util.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ShipmentDAO {
    public boolean saveShipment(Shipment shipment){
        try{
            Connection con= DBManager.getConnection();
            String sql_query="INSERT INTO shipments(tracking_id, sender_name, receiver_name, weight, shipment_date, status)VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql_query);
            ps.setString(1, shipment.getTrackingID());
            ps.setString(2, shipment.getSenderName());
            ps.setString(3, shipment.getReceiverName());
            ps.setDouble(4, shipment.getWeight());
            ps.setString(5, shipment.getStatus());
            ps.setString(6, shipment.getShipmentDate());
            ps.executeUpdate();
            return true;
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    public Shipment findTrackingID(String trackingID) {
        try {
            Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM shipments WHERE tracking_id=?"
            );
            ps.setString(1, trackingID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Shipment s = new Shipment(
                        rs.getString("tracking_id"),
                        rs.getString("sender_name"),
                        rs.getString("receiver_name"),
                        rs.getDouble("weight"),
                        rs.getString("shipment_date")
                ) {
                    @Override
                    public double cost_calculation() { return 0; }
                    @Override
                    public int delivery_days() { return 0; }
                };
                s.setStatus(rs.getString("status"));
                return s;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}



