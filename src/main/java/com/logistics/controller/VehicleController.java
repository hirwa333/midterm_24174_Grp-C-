package com.logistics.controller;

import com.logistics.entity.Vehicle;
import com.logistics.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    
    @Autowired
    private VehicleService vehicleService;
    
    @GetMapping("/sorted")
    public ResponseEntity<Page<Vehicle>> getVehiclesWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "vehicleId") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Page<Vehicle> vehicles = vehicleService.getVehiclesWithPaginationAndSorting(page, size, sortBy, direction);
        return ResponseEntity.ok(vehicles);
    }
    
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        Vehicle savedVehicle = vehicleService.saveVehicle(vehicle);
        return ResponseEntity.ok(savedVehicle);
    }
    
    @GetMapping("/exists/{vehicleNumber}")
    public ResponseEntity<Boolean> checkVehicleExists(@PathVariable String vehicleNumber) {
        boolean exists = vehicleService.checkVehicleExists(vehicleNumber);
        return ResponseEntity.ok(exists);
    }
}
