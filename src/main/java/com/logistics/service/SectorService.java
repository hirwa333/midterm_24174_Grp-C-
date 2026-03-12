package com.logistics.service;

import com.logistics.entity.District;
import com.logistics.entity.Sector;
import com.logistics.repository.DistrictRepository;
import com.logistics.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorService {
    
    @Autowired
    private SectorRepository sectorRepository;
    
    @Autowired
    private DistrictRepository districtRepository;
    
    public Sector saveSector(String sectorName, Long districtId) {
        District district = districtRepository.findById(districtId).get();
        Sector sector = new Sector(sectorName, district);
        return sectorRepository.save(sector);
    }
    
    public List<Sector> getAllSectors() {
        return sectorRepository.findAll();
    }
}
