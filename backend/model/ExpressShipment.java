package com.courierms.model;

public class ExpressShipment extends Shipment {
    public ExpressShipment(String tackingID,String senderName,String receiverName,double weight,String shipmentDate) {
        super(tackingID, senderName, receiverName, weight, shipmentDate);
    }

    @Override
    public double cost_calculation() {
        return getWeight()*120;
    }

    @Override
    public int delivery_days() {
        return 2;
    }
}
