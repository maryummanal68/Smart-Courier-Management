package com.courierms.model;

public class InternationalShipment extends Shipment{
    public InternationalShipment(String tackingID, String senderName, String receiverName, double weight, String shipmentDate) {
        super(tackingID, senderName, receiverName, weight, shipmentDate);
    }

    @Override
    public double cost_calculation() {
        return getWeight()*200;
    }

    @Override
    public int delivery_days() {
        return 10;
    }
}
