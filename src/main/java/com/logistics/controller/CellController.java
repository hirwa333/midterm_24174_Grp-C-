package com.logistics.controller;

import com.logistics.entity.Cell;
import com.logistics.service.CellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cells")
public class CellController {
    
    @Autowired
    private CellService cellService;
    
    @PostMapping
    public ResponseEntity<Cell> createCell(
            @RequestParam String cellName,
            @RequestParam Long sectorId) {
        Cell savedCell = cellService.saveCell(cellName, sectorId);
        return ResponseEntity.ok(savedCell);
    }
    
    @GetMapping
    public ResponseEntity<List<Cell>> getAllCells() {
        List<Cell> cells = cellService.getAllCells();
        return ResponseEntity.ok(cells);
    }
}
