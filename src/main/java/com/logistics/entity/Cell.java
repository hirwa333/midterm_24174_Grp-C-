package com.logistics.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cell")
public class Cell {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cellId;
    
    @Column(nullable = false)
    private String cellName;
    
    @ManyToOne
    @JoinColumn(name = "sector_id", nullable = false)
    private Sector sector;
    
    @OneToMany(mappedBy = "cell", cascade = CascadeType.ALL)
    private List<Village> villages;
    
    public Cell() {}
    
    public Cell(String cellName, Sector sector) {
        this.cellName = cellName;
        this.sector = sector;
    }
    
    public Long getCellId() {
        return cellId;
    }
    
    public void setCellId(Long cellId) {
        this.cellId = cellId;
    }
    
    public String getCellName() {
        return cellName;
    }
    
    public void setCellName(String cellName) {
        this.cellName = cellName;
    }
    
    public Sector getSector() {
        return sector;
    }
    
    public void setSector(Sector sector) {
        this.sector = sector;
    }
    
    public List<Village> getVillages() {
        return villages;
    }
    
    public void setVillages(List<Village> villages) {
        this.villages = villages;
    }
}
