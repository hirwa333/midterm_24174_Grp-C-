package com.logistics.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "village")
public class Village {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long villageId;
    
    @Column(nullable = false)
    private String villageName;
    
    @ManyToOne
    @JoinColumn(name = "cell_id", nullable = false)
    private Cell cell;
    
    @OneToOne(mappedBy = "village", cascade = CascadeType.ALL)
    private User user;
    
    @OneToMany(mappedBy = "village", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;
    
    public Village() {}
    
    public Village(String villageName, Cell cell) {
        this.villageName = villageName;
        this.cell = cell;
    }
    
    public Long getVillageId() {
        return villageId;
    }
    
    public void setVillageId(Long villageId) {
        this.villageId = villageId;
    }
    
    public String getVillageName() {
        return villageName;
    }
    
    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }
    
    public Cell getCell() {
        return cell;
    }
    
    public void setCell(Cell cell) {
        this.cell = cell;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public List<Vehicle> getVehicles() {
        return vehicles;
    }
    
    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
