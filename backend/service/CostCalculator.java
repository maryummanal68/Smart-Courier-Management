package com.courierms.service;
import com.courierms.model.Shipment;

public class CostCalculator {
    public double calculation(Shipment shipment){
        return shipment.cost_calculation();
    }
    public int getDeliveryDays(Shipment shipment){
        return shipment.delivery_days();
    }
}
