package com.logistics.service;

import com.logistics.entity.District;
import com.logistics.entity.Province;
import com.logistics.repository.DistrictRepository;
import com.logistics.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {
    
    @Autowired
    private DistrictRepository districtRepository;
    
    @Autowired
    private ProvinceRepository provinceRepository;
    
    public District saveDistrict(String districtName, Long provinceId) {
        Province province = provinceRepository.findById(provinceId).get();
        District district = new District(districtName, province);
        return districtRepository.save(district);
    }
    
    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }
}
