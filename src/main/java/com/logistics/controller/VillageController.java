package com.logistics.controller;

import com.logistics.entity.Village;
import com.logistics.service.VillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/villages")
public class VillageController {
    
    @Autowired
    private VillageService villageService;
    
    @PostMapping
    public ResponseEntity<Village> createVillage(
            @RequestParam String villageName,
            @RequestParam Long cellId) {
        Village savedVillage = villageService.saveVillage(villageName, cellId);
        return ResponseEntity.ok(savedVillage);
    }
    
    @GetMapping
    public ResponseEntity<List<Village>> getAllVillages() {
        List<Village> villages = villageService.getAllVillages();
        return ResponseEntity.ok(villages);
    }
    
    @GetMapping("/exists/{villageName}")
    public ResponseEntity<Boolean> checkVillageExists(@PathVariable String villageName) {
        boolean exists = villageService.checkVillageExists(villageName);
        return ResponseEntity.ok(exists);
    }
}
