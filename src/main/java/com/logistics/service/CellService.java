package com.logistics.service;

import com.logistics.entity.Cell;
import com.logistics.entity.Sector;
import com.logistics.repository.CellRepository;
import com.logistics.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CellService {
    
    @Autowired
    private CellRepository cellRepository;
    
    @Autowired
    private SectorRepository sectorRepository;
    
    public Cell saveCell(String cellName, Long sectorId) {
        Sector sector = sectorRepository.findById(sectorId).get();
        Cell cell = new Cell(cellName, sector);
        return cellRepository.save(cell);
    }
    
    public List<Cell> getAllCells() {
        return cellRepository.findAll();
    }
}
