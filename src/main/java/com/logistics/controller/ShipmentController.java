package com.logistics.controller;

import com.logistics.entity.Shipment;
import com.logistics.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {
    
    @Autowired
    private ShipmentService shipmentService;
    
    @PostMapping
    public ResponseEntity<Shipment> createShipment(@RequestBody Shipment shipment) {
        Shipment savedShipment = shipmentService.saveShipment(shipment);
        return ResponseEntity.ok(savedShipment);
    }
    
    @PostMapping("/{shipmentId}/assign-vehicles")
    public ResponseEntity<Shipment> assignVehicles(
            @PathVariable Long shipmentId,
            @RequestBody List<Long> vehicleIds) {
        Shipment updatedShipment = shipmentService.assignVehiclesToShipment(shipmentId, vehicleIds);
        return ResponseEntity.ok(updatedShipment);
    }
    
    @GetMapping
    public ResponseEntity<List<Shipment>> getAllShipments() {
        List<Shipment> shipments = shipmentService.getAllShipments();
        return ResponseEntity.ok(shipments);
    }
}
