package com.logistics.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vehicle")
public class Vehicle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;
    
    @Column(unique = true, nullable = false)
    private String vehicleNumber;
    
    @Column(nullable = false)
    private String vehicleType;
    
    @Column(nullable = false)
    private Double capacity;
    
    @ManyToOne
    @JoinColumn(name = "village_id", nullable = false)
    private Village village;
    
    @ManyToMany(mappedBy = "vehicles")
    private List<Shipment> shipments;
    
    public Vehicle() {}
    
    public Vehicle(String vehicleNumber, String vehicleType, Double capacity, Village village) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.capacity = capacity;
        this.village = village;
    }
    
    public Long getVehicleId() {
        return vehicleId;
    }
    
    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
    
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
    
    public String getVehicleType() {
        return vehicleType;
    }
    
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    
    public Double getCapacity() {
        return capacity;
    }
    
    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }
    
    public Village getVillage() {
        return village;
    }
    
    public void setVillage(Village village) {
        this.village = village;
    }
    
    public List<Shipment> getShipments() {
        return shipments;
    }
    
    public void setShipments(List<Shipment> shipments) {
        this.shipments = shipments;
    }
}
