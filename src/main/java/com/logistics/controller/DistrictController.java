package com.logistics.controller;

import com.logistics.entity.District;
import com.logistics.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/districts")
public class DistrictController {
    
    @Autowired
    private DistrictService districtService;
    
    @PostMapping
    public ResponseEntity<District> createDistrict(
            @RequestParam String districtName,
            @RequestParam Long provinceId) {
        District savedDistrict = districtService.saveDistrict(districtName, provinceId);
        return ResponseEntity.ok(savedDistrict);
    }
    
    @GetMapping
    public ResponseEntity<List<District>> getAllDistricts() {
        List<District> districts = districtService.getAllDistricts();
        return ResponseEntity.ok(districts);
    }
}
