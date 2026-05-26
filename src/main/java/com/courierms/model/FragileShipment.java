package com.courierms.model;

public class FragileShipment extends Shipment {
    public FragileShipment(String tackingID, String senderName, String receiverName, double weight, String shipmentDate) {
        super(tackingID, senderName, receiverName, weight, shipmentDate);
    }

    @Override
    public double cost_calculation() {
        return getWeight()*80;
    }

    @Override
    public int delivery_days() {
        return 4;
    }
}
