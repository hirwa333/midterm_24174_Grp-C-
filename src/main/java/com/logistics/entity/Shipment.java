package com.logistics.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shipment")
public class Shipment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipmentId;
    
    @Column(unique = true, nullable = false)
    private String shipmentCode;
    
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    private Double weight;
    
    @Column(nullable = false)
    private String status;
    
    @ManyToMany
    @JoinTable(
        name = "shipment_vehicle",
        joinColumns = @JoinColumn(name = "shipment_id"),
        inverseJoinColumns = @JoinColumn(name = "vehicle_id")
    )
    private List<Vehicle> vehicles;
    
    public Shipment() {}
    
    public Shipment(String shipmentCode, String description, Double weight, String status) {
        this.shipmentCode = shipmentCode;
        this.description = description;
        this.weight = weight;
        this.status = status;
    }
    
    public Long getShipmentId() {
        return shipmentId;
    }
    
    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }
    
    public String getShipmentCode() {
        return shipmentCode;
    }
    
    public void setShipmentCode(String shipmentCode) {
        this.shipmentCode = shipmentCode;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Double getWeight() {
        return weight;
    }
    
    public void setWeight(Double weight) {
        this.weight = weight;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public List<Vehicle> getVehicles() {
        return vehicles;
    }
    
    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
