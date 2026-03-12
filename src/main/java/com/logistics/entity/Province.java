package com.logistics.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "province")
public class Province {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long provinceId;
    
    @Column(unique = true, nullable = false)
    private String provinceCode;
    
    @Column(nullable = false)
    private String provinceName;
    
    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    private List<District> districts;
    
    public Province() {}
    
    public Province(String provinceCode, String provinceName) {
        this.provinceCode = provinceCode;
        this.provinceName = provinceName;
    }
    
    public Long getProvinceId() {
        return provinceId;
    }
    
    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }
    
    public String getProvinceCode() {
        return provinceCode;
    }
    
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
    
    public String getProvinceName() {
        return provinceName;
    }
    
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    
    public List<District> getDistricts() {
        return districts;
    }
    
    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
}
