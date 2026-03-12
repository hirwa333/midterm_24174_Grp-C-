package com.logistics.controller;

import com.logistics.entity.Province;
import com.logistics.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provinces")
public class ProvinceController {
    
    @Autowired
    private ProvinceService provinceService;
    
    @PostMapping
    public ResponseEntity<Province> createProvince(@RequestBody Province province) {
        Province savedProvince = provinceService.saveProvince(province);
        return ResponseEntity.ok(savedProvince);
    }
    
    @GetMapping
    public ResponseEntity<List<Province>> getAllProvinces() {
        List<Province> provinces = provinceService.getAllProvinces();
        return ResponseEntity.ok(provinces);
    }
    
    @GetMapping("/exists/{provinceCode}")
    public ResponseEntity<Boolean> checkProvinceExists(@PathVariable String provinceCode) {
        boolean exists = provinceService.checkProvinceExists(provinceCode);
        return ResponseEntity.ok(exists);
    }
}
