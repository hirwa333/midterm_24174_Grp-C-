package com.logistics.service;

import com.logistics.entity.Shipment;
import com.logistics.entity.Vehicle;
import com.logistics.repository.ShipmentRepository;
import com.logistics.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipmentService {
    
    @Autowired
    private ShipmentRepository shipmentRepository;
    
    @Autowired
    private VehicleRepository vehicleRepository;
    
    public Shipment saveShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }
    
    public Shipment assignVehiclesToShipment(Long shipmentId, List<Long> vehicleIds) {
        Shipment shipment = shipmentRepository.findById(shipmentId).get();
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        for (Long vehicleId : vehicleIds) {
            Vehicle vehicle = vehicleRepository.findById(vehicleId).get();
            vehicles.add(vehicle);
        }
        shipment.setVehicles(vehicles);
        return shipmentRepository.save(shipment);
    }
    
    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }
}
