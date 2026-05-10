package com.courierms.model;

public class StandardShipment extends Shipment{
    public StandardShipment(String tackingID,String senderName,String receiverName,double weight,String shipmentDate){
        super (tackingID,senderName,receiverName,weight,shipmentDate);

    }

    @Override
    public double cost_calculation() {
        return getWeight()*50;
    }

    @Override
    public int delivery_days() {
        return 5;
    }
}
