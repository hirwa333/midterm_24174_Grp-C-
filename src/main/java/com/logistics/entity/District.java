package com.logistics.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "district")
public class District {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long districtId;
    
    @Column(nullable = false)
    private String districtName;
    
    @ManyToOne
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;
    
    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    private List<Sector> sectors;
    
    public District() {}
    
    public District(String districtName, Province province) {
        this.districtName = districtName;
        this.province = province;
    }
    
    public Long getDistrictId() {
        return districtId;
    }
    
    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }
    
    public String getDistrictName() {
        return districtName;
    }
    
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
    
    public Province getProvince() {
        return province;
    }
    
    public void setProvince(Province province) {
        this.province = province;
    }
    
    public List<Sector> getSectors() {
        return sectors;
    }
    
    public void setSectors(List<Sector> sectors) {
        this.sectors = sectors;
    }
}
