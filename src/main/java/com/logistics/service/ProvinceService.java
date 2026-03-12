package com.logistics.service;

import com.logistics.entity.Province;
import com.logistics.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceService {
    
    @Autowired
    private ProvinceRepository provinceRepository;
    
    public Province saveProvince(Province province) {
        return provinceRepository.save(province);
    }
    
    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }
    
    public boolean checkProvinceExists(String provinceCode) {
        return provinceRepository.existsByProvinceCode(provinceCode);
    }
}
