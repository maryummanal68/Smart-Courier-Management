package com.courierms.model;

public abstract class Shipment {
    private String trackingID;
    private String senderName;
    private String receiverName;
    private double weight;
    private String status;
    private String shipmentDate;

    public Shipment(String trackingID, String senderName, String receiverName, double weight, String shipmentDate) {
        this.trackingID = trackingID;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.weight = weight;
        this.status = "Pending";
        this.shipmentDate = shipmentDate;
    }

    public abstract double cost_calculation();

    public abstract int delivery_days();
    public String getTrackingID()   { return trackingID; }
    public String getSenderName()   { return senderName; }
    public String getReceiverName() { return receiverName; }
    public double getWeight()       { return weight; }
    public String getStatus()       { return status; }
    public String getShipmentDate() { return shipmentDate; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "AbstractShipment{" +
                "trackingID='" + trackingID + '\'' +
                ", senderName='" + senderName + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", weight=" + weight +
                ", status='" + status + '\'' +
                ", shipmentDate='" + shipmentDate + '\'' +
                '}';
    }
}
