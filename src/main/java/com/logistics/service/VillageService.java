package com.logistics.service;

import com.logistics.entity.Cell;
import com.logistics.entity.Village;
import com.logistics.repository.CellRepository;
import com.logistics.repository.VillageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VillageService {
    
    @Autowired
    private VillageRepository villageRepository;
    
    @Autowired
    private CellRepository cellRepository;
    
    public Village saveVillage(String villageName, Long cellId) {
        Cell cell = cellRepository.findById(cellId).get();
        Village village = new Village(villageName, cell);
        return villageRepository.save(village);
    }
    
    public List<Village> getAllVillages() {
        return villageRepository.findAll();
    }
    
    public boolean checkVillageExists(String villageName) {
        return villageRepository.existsByVillageName(villageName);
    }
}
