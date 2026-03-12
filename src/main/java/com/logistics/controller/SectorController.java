package com.logistics.controller;

import com.logistics.entity.Sector;
import com.logistics.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sectors")
public class SectorController {
    
    @Autowired
    private SectorService sectorService;
    
    @PostMapping
    public ResponseEntity<Sector> createSector(
            @RequestParam String sectorName,
            @RequestParam Long districtId) {
        Sector savedSector = sectorService.saveSector(sectorName, districtId);
        return ResponseEntity.ok(savedSector);
    }
    
    @GetMapping
    public ResponseEntity<List<Sector>> getAllSectors() {
        List<Sector> sectors = sectorService.getAllSectors();
        return ResponseEntity.ok(sectors);
    }
}
