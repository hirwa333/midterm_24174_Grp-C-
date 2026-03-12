package com.logistics.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sector")
public class Sector {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sectorId;
    
    @Column(nullable = false)
    private String sectorName;
    
    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    private District district;
    
    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL)
    private List<Cell> cells;
    
    public Sector() {}
    
    public Sector(String sectorName, District district) {
        this.sectorName = sectorName;
        this.district = district;
    }
    
    public Long getSectorId() {
        return sectorId;
    }
    
    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
    }
    
    public String getSectorName() {
        return sectorName;
    }
    
    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }
    
    public District getDistrict() {
        return district;
    }
    
    public void setDistrict(District district) {
        this.district = district;
    }
    
    public List<Cell> getCells() {
        return cells;
    }
    
    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }
}
